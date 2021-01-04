package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.UserMapper;
import club.doyoudo.emotional.model.Profile;
import club.doyoudo.emotional.model.User;
import club.doyoudo.emotional.model.UserExample;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.pojo.UserWithProfile;
import club.doyoudo.emotional.service.ProfileService;
import club.doyoudo.emotional.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final ProfileService profileServiceImpl;

    @Autowired(required = false)
    public UserServiceImpl(UserMapper userMapper, ProfileService profileServiceImpl) {
        this.userMapper = userMapper;
        this.profileServiceImpl = profileServiceImpl;
    }

    @Override
    public ResponseWrapper logIn(User user) {
        if (user.getUsername() == null) {
            return new ResponseWrapper( false, 200, "登录失败，用户名为空！", null );
        } else if (user.getPassword() == null) {
            return new ResponseWrapper( false, 200, "登录失败，登陆密码为空！", null );
        } else {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo( user.getUsername() ).andPasswordEqualTo( user.getPassword() );
            List<User> list = userMapper.selectByExample( userExample );
            if (list != null && list.size() > 0) {
                User user1 = list.get( 0 );
                //如果用户被封号
                if (user1.getStatus() == 2) {
                    return new ResponseWrapper( false, 403, "当前用户已被永久封号，禁止登录！", null );
                }
                //使用json将user转换为UserWithProfile
                UserWithProfile userWithProfile = JSONObject.parseObject( JSONObject.toJSONString( user1 ), UserWithProfile.class );

                userWithProfile.setUserProfile( profileServiceImpl.selectProfile1( userWithProfile.getId() ) );
                return new ResponseWrapper( true, 200, "登录成功！", userWithProfile );
            } else {
                return new ResponseWrapper( false, 403, "登录失败，账号或者密码有误！", null );
            }
        }
    }

    @Override
    public ResponseWrapper signUp(User user) {
        if (user.getUsername() == null) {
            return new ResponseWrapper( false, 200, "注册失败，用户名为空！", null );
        } else if (user.getPassword() == null) {
            return new ResponseWrapper( false, 200, "注册失败，登陆密码为空！", null );
        } else {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo( user.getUsername() );
            String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );
            if (userMapper.selectByExample( userExample ).size() > 0) {
                return new ResponseWrapper( false, 200, "注册失败，当前用户名已存在！", null );
            }
            user.setId( uuid );
            user.setUsername( user.getUsername() );
            user.setPassword( user.getPassword() );
            int i = userMapper.insertSelective( user );
            Profile profile = new Profile();
            profile.setUserId( uuid );
            profile.setNickname( "新人" + user.getUsername() );
            profile.setGender( 2 );
            //设置默认头像
            profile.setAvatar( "http://doyoudo.club:8080/images/2020/05/05/d17bb867-029b-4178-b0aa-6c533a3633c5.jpg" );
            profile.setNote( "别以为会有简介，哼！" );
            i += profileServiceImpl.insertProfile( profile );
            if (i == 2) {
                //使用json将user转换为UserWithProfile
                UserWithProfile userWithProfile = JSONObject.parseObject( JSONObject.toJSONString( user ), UserWithProfile.class );
                //设置新属性
                userWithProfile.setUserProfile( profileServiceImpl.selectProfile1( userWithProfile.getId() ) );

                return new ResponseWrapper( true, 200, "注册成功！", userWithProfile );
            } else {
                return new ResponseWrapper( false, 200, "注册失败,请稍后重试！", null );
            }
        }
    }

    @Override
    public User selectUserById(String id) {
        return userMapper.selectByPrimaryKey( id );
    }

    @Override
    public ResponseWrapper validateUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo( username );
        List<User> list = userMapper.selectByExample( userExample );
        if (list != null && list.size() > 0) {
            return new ResponseWrapper( false, 200, "用户名已经存在！", null );
        } else {
            return new ResponseWrapper( true, 200, "用户名可以使用！", null );
        }
    }

    @Override
    public ResponseWrapper selectUser(String likeNickName, Integer pageNum, Integer pageSize) {
        UserExample userExample = new UserExample();
        if (likeNickName != null && !likeNickName.equals( "" )) {
            userExample.createCriteria().andUsernameLike( "%" + likeNickName + "%" );
        }

        //分页：只对其后的第一个查询有效
        Page<User> page = PageHelper.startPage( pageNum, pageSize );
        List<User> userList = userMapper.selectByExample( userExample );
        PageInfo<User> pageInfo = page.toPageInfo();

        return new ResponseWrapper( true, 200, "成功！", pageInfo );
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective( user );
    }

    @Override
    public ResponseWrapper updateUserStatus(User user) {
        int i = userMapper.updateByPrimaryKeySelective( user );
        if (i == 1) {
            return new ResponseWrapper( true, 200, "成功！", null );
        } else {
            return new ResponseWrapper( false, 200, "失败,请稍后重试！", null );
        }
    }

    @Override
    public ResponseWrapper updatePassword(String userId, String oldPassword, String newPassword) {
        User user = userMapper.selectByPrimaryKey( userId );
        //如果旧密码输入不正确
        if (!user.getPassword().equals( oldPassword )) {
            return new ResponseWrapper( false, 403, "旧密码输入不正确，请重试！", null );
        } else {
            user.setPassword( newPassword );
            int i = userMapper.updateByPrimaryKeySelective( user );
            if (i == 1) {
                return new ResponseWrapper( true, 200, "修改成功！", null );
            } else {
                return new ResponseWrapper( false, 500, "失败,请稍后重试！", null );
            }
        }
    }
}
