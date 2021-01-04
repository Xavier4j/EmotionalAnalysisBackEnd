package club.doyoudo.emotional.mapper;

import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.model.PhoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneMapper {
    long countByExample(PhoneExample example);

    int deleteByExample(PhoneExample example);

    int deleteByPrimaryKey(String id);

    int insert(Phone record);

    int insertSelective(Phone record);

    List<Phone> selectByExample(PhoneExample example);

    Phone selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Phone record, @Param("example") PhoneExample example);

    int updateByExample(@Param("record") Phone record, @Param("example") PhoneExample example);

    int updateByPrimaryKeySelective(Phone record);

    int updateByPrimaryKey(Phone record);
}