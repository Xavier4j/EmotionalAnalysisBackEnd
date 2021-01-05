package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.model.PhoneExample;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PhoneService {
    ResponseWrapper insertPhone(Phone phone);

    ResponseWrapper updatePhone(Phone phone);

    int updatePhoneCommentNum(String id, boolean flag);

    PageInfo<Phone> searchPhone(String search, Integer pageNum, Integer pageSize);

    ResponseWrapper selectPhoneById(String phoneId);

    Phone selectPhoneById1(String phoneId);

    List<Phone> selectPhoneListByExample(PhoneExample phoneExample);

    List<Phone> selectPhoneListById(List<String> phoneIdList);
}
