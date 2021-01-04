package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.AnalysisCommentTag;

import java.util.List;

public interface AnalyseCommentTagService {
    int insertAnalysisCommentTag(AnalysisCommentTag analysisCommentTag);

    /**
     * @param phoneId
     * @param commentId
     * @return
     */
    List<AnalysisCommentTag> selectAnalysisCommentTag(String phoneId, String commentId);
}
