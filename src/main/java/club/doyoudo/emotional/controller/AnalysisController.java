package club.doyoudo.emotional.controller;

import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.AnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Api(tags = "情感分析相关接口")
@RestController
@CrossOrigin
public class AnalysisController {

    AnalysisService analysisServiceImpl;

    @Autowired
    public AnalysisController(AnalysisService analysisServiceImpl) {
        this.analysisServiceImpl = analysisServiceImpl;
    }

    @ApiOperation(value = "分析论坛整体情感倾向", notes = "分析论坛整体情感倾向", produces = "application/json", httpMethod = "GET")
    @RequestMapping("/analyse-whole-sentiment")
    public ResponseWrapper analyseWholeSentiment(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 @RequestParam(required = false) LocalDate start,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 @RequestParam(required = false) LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.plusDays( 1 ).atStartOfDay();
        return analysisServiceImpl.analyseWholeSentiment( startDateTime, endDateTime );
    }

    @ApiOperation(value = "分析论坛负面信息排行", notes = "分析一段时间内的情感倾向最偏向负面的评论与回复", produces = "application/json", httpMethod = "GET")
    @RequestMapping("/analyse-negative")
    public ResponseWrapper analyseNegative(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                           @RequestParam(required = false) LocalDate start,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd")
                                           @RequestParam(required = false) LocalDate end,
                                           @RequestParam(required = false, defaultValue = "10") Integer num) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.plusDays( 1 ).atStartOfDay();
        return analysisServiceImpl.analyseNegative( startDateTime, endDateTime, num );
    }

    @ApiOperation(value = "分析评论情感倾向", notes = "分析评论情感倾向", produces = "application/json", httpMethod = "GET")
    @RequestMapping("/analyse-sentiment")
    public ResponseWrapper analyseSentiment(String postId) {
        return new ResponseWrapper( true, 200, "成功", analysisServiceImpl.analyseSentiment( postId ) );
    }
    
}
