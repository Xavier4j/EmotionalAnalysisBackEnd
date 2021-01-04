package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.Profile;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import com.github.pagehelper.PageInfo;

public interface ProfileService {
    int insertProfile(Profile profile);

    String selectAvatar(String userId);

    ResponseWrapper updateProfile(Profile profile);

    ResponseWrapper selectProfile(String userId);

    PageInfo<Profile> searchUserList(String search, Integer pageNum, Integer pageSize);

    Profile selectProfile1(String userId);

    ResponseWrapper validateNickName(String nickName);
}
