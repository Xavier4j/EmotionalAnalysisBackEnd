package club.doyoudo.emotional.service;

import club.doyoudo.emotional.model.User;
import club.doyoudo.emotional.pojo.ResponseWrapper;

public interface UserService {
    ResponseWrapper logIn(User user);

    ResponseWrapper signUp(User user);

    User selectUserById(String id);

    ResponseWrapper validateUsername(String username);

    ResponseWrapper selectUser(String likeNickName,Integer pageNum, Integer pageSize);

    int updateUser(User user);

    ResponseWrapper updateUserStatus(User user);

    ResponseWrapper updatePassword(String userId, String oldPassword, String newPassword);
}
