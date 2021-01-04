package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.AnalysisSentiment;

import java.util.List;

public interface AnalyseSentimentService {

    int insertAnalysisSentiment(AnalysisSentiment analysisSentiment);

    List<AnalysisSentiment> selectAnalysisSentimentByPhoneId(String phoneId);
}
