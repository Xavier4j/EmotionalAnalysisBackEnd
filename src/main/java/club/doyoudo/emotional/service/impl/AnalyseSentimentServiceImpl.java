package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.AnalysisSentimentMapper;
import club.doyoudo.emotional.model.AnalysisSentiment;
import club.doyoudo.emotional.model.AnalysisSentimentExample;
import club.doyoudo.emotional.service.AnalyseSentimentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalyseSentimentServiceImpl implements AnalyseSentimentService {

    @Resource
    AnalysisSentimentMapper analysisSentimentMapper;

    @Override
    public int insertAnalysisSentiment(AnalysisSentiment analysisSentiment) {
        return analysisSentimentMapper.insert( analysisSentiment );
    }

    @Override
    public List<AnalysisSentiment> selectAnalysisSentimentByPhoneId(String phoneId) {
        AnalysisSentimentExample analysisSentimentExample = new AnalysisSentimentExample();
        analysisSentimentExample.createCriteria().andPhoneIdEqualTo( phoneId );
        return analysisSentimentMapper.selectByExample( analysisSentimentExample );
    }
}
