package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.AnalysisCommentTagMapper;
import club.doyoudo.emotional.model.AnalysisCommentTag;
import club.doyoudo.emotional.model.AnalysisCommentTagExample;
import club.doyoudo.emotional.service.AnalyseCommentTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseCommentTagServiceImpl implements AnalyseCommentTagService {

    AnalysisCommentTagMapper analysisCommentTagMapper;

    @Autowired(required = false)
    public AnalyseCommentTagServiceImpl(AnalysisCommentTagMapper analysisCommentTagMapper) {
        this.analysisCommentTagMapper = analysisCommentTagMapper;
    }

    @Override
    public int insertAnalysisCommentTag(AnalysisCommentTag analysisCommentTag) {
        return analysisCommentTagMapper.insertSelective( analysisCommentTag );
    }

    @Override
    public List<AnalysisCommentTag> selectAnalysisCommentTag(String phoneId, String commentId) {
        AnalysisCommentTagExample analysisCommentTagExample = new AnalysisCommentTagExample();
        analysisCommentTagExample.createCriteria().andPhoneIdEqualTo( phoneId ).andCommentIdEqualTo( commentId );
        return analysisCommentTagMapper.selectByExample( analysisCommentTagExample );
    }
}
