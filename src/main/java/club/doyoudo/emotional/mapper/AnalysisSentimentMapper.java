package club.doyoudo.emotional.mapper;

import club.doyoudo.emotional.model.AnalysisSentiment;
import club.doyoudo.emotional.model.AnalysisSentimentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnalysisSentimentMapper {
    long countByExample(AnalysisSentimentExample example);

    int deleteByExample(AnalysisSentimentExample example);

    int deleteByPrimaryKey(String id);

    int insert(AnalysisSentiment record);

    int insertSelective(AnalysisSentiment record);

    List<AnalysisSentiment> selectByExample(AnalysisSentimentExample example);

    AnalysisSentiment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AnalysisSentiment record, @Param("example") AnalysisSentimentExample example);

    int updateByExample(@Param("record") AnalysisSentiment record, @Param("example") AnalysisSentimentExample example);

    int updateByPrimaryKeySelective(AnalysisSentiment record);

    int updateByPrimaryKey(AnalysisSentiment record);
}