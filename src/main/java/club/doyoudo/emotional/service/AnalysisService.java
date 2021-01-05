package club.doyoudo.emotional.service;

import club.doyoudo.emotional.pojo.ResponseWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public interface AnalysisService {
    /**
     * 对某天的所有评论以及回复进行情感倾向分析，用作定时任务，每天分析
     *
     * @param date
     * @return
     */
    void analyseSentimentDaily(LocalDate date);

    /**
     * 对某时间段的所有评论以及回复进行情感倾向分析
     *
     * @param start
     * @param end
     * @return
     */
    ResponseWrapper analyseWholeSentiment(LocalDateTime start, LocalDateTime end);

    /**
     * 对某时间段的所有评论进行情感倾向分析，找出情感倾向最偏向于消极的内容
     *
     * @param start
     * @param end
     * @return
     */
    ResponseWrapper analyseNegative(LocalDateTime start, LocalDateTime end, Integer num);

    /**
     * 对手机评论回复进行情感倾向分析
     *
     * @param phoneId
     * @return
     */
    Map<String, Double> analyseSentiment(String phoneId);

    /**
     * 对某天的所有手机进行评论观点抽取，用作定时任务，每天分析
     *
     * @param date
     * @return
     */
    void analyseCommentTagDaily(LocalDate date);
}
