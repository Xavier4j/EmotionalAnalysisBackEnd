package club.doyoudo.emotional.mapper;

import club.doyoudo.emotional.model.AnalysisCommentTag;
import club.doyoudo.emotional.model.AnalysisCommentTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnalysisCommentTagMapper {
    long countByExample(AnalysisCommentTagExample example);

    int deleteByExample(AnalysisCommentTagExample example);

    int deleteByPrimaryKey(String id);

    int insert(AnalysisCommentTag record);

    int insertSelective(AnalysisCommentTag record);

    List<AnalysisCommentTag> selectByExample(AnalysisCommentTagExample example);

    AnalysisCommentTag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AnalysisCommentTag record, @Param("example") AnalysisCommentTagExample example);

    int updateByExample(@Param("record") AnalysisCommentTag record, @Param("example") AnalysisCommentTagExample example);

    int updateByPrimaryKeySelective(AnalysisCommentTag record);

    int updateByPrimaryKey(AnalysisCommentTag record);
}