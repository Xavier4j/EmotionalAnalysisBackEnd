package club.doyoudo.emotional.mapper;

import club.doyoudo.emotional.model.CommentTag;
import club.doyoudo.emotional.model.CommentTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentTagMapper {
    long countByExample(CommentTagExample example);

    int deleteByExample(CommentTagExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommentTag record);

    int insertSelective(CommentTag record);

    List<CommentTag> selectByExample(CommentTagExample example);

    CommentTag selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommentTag record, @Param("example") CommentTagExample example);

    int updateByExample(@Param("record") CommentTag record, @Param("example") CommentTagExample example);

    int updateByPrimaryKeySelective(CommentTag record);

    int updateByPrimaryKey(CommentTag record);
}