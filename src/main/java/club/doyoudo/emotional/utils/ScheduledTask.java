package club.doyoudo.emotional.utils;


import club.doyoudo.emotional.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    @Resource
    AnalysisService analysisServiceImpl;

    @Scheduled(cron = "0 0 4 * * ?") //每天4点触发，分析前一天的数据
    public void analyseSentimentDailyTask() {
        Logger logger = LoggerFactory.getLogger( ScheduledTask.class );
        logger.info( "analyseSentimentDailyTask 开始 ByCorn：" + LocalDateTime.now() );
        analysisServiceImpl.analyseSentimentDaily( LocalDate.now().minusDays( 1 ) );//对昨天的评论数据进行分析
        logger.info( "analyseSentimentDailyTask 结束 ByCorn：" + LocalDateTime.now() );
    }

    @Scheduled(cron = "0 0 4 * * ?") //每天4点触发，分析前一天的数据
    public void analyseCommentTagDailyTask() {
        Logger logger = LoggerFactory.getLogger( ScheduledTask.class );
        logger.info( "analyseCommentTagDailyTask 开始 ByCorn：" + LocalDateTime.now() );
        analysisServiceImpl.analyseCommentTagDaily( LocalDate.now().minusDays( 1 ) );//对昨天的评论数据进行分析
        logger.info( "analyseCommentTagDailyTask 结束 ByCorn：" + LocalDateTime.now() );
    }
}
