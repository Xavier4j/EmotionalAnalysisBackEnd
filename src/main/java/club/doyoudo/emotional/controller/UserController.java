package club.doyoudo.emotional.controller;

import club.doyoudo.emotional.model.User;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.UserService;
import club.doyoudo.emotional.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Api(tags = "用户接口")
@RestController
@CrossOrigin
public class UserController {
    UserService userServiceImpl;

    @Autowired(required = false)
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @ApiOperation(value = "登录", notes = "只需填写username与password", produces = "application/json", httpMethod = "POST")
    @RequestMapping("/login")
    public ResponseWrapper logIn(@RequestBody User user) {
        return userServiceImpl.logIn( user );
    }

    @ApiOperation(value = "注册", notes = "只需填写username与password", produces = "application/json", httpMethod = "POST")
    @RequestMapping("/signup")
    public ResponseWrapper signUp(@RequestBody User user) {
        return userServiceImpl.signUp( user );
    }

    @ApiOperation(value = "查询用户名是否可用", notes = "查询用户名是否可用", produces = "application/json", httpMethod = "GET")
    @RequestMapping("/validate-username")
    public ResponseWrapper validateUsername(String username) {
        return userServiceImpl.validateUsername( username );
    }

    @ApiOperation(value = "模糊查询用户", notes = "不传参时，查询所有用户", produces = "application/json", httpMethod = "GET")
    @RequestMapping("/select-user")
    public ResponseWrapper selectUser(@RequestParam(required = false, defaultValue = "") String likeNickName,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
        return userServiceImpl.selectUser( likeNickName, pageNum, pageSize );
    }

    @ApiOperation(value = "修改用户状态", notes = "禁言或者封号", produces = "application/json", httpMethod = "POST")
    @RequestMapping("/update-user")
    public ResponseWrapper updateUserStatus(@RequestBody User user) {
        if (user.getStatus() != 0) {
            Timer timer = new Timer();// 实例化Timer类
            //设置定时任务，定时解除用户禁言
            timer.schedule( new TimerTask() {
                public void run() {
                    user.setStatus( 0 );
                    user.setDeadline( null );
                    userServiceImpl.updateUser( user );
                }
            }, Date.from( user.getDeadline().atZone( ZoneOffset.ofHours( 8 ) ).toInstant() ) );
        }
        return userServiceImpl.updateUserStatus( user );
    }

    @ApiOperation(value = "修改用户密码", notes = "修改用户密码", produces = "application/json", httpMethod = "POST")
    @RequestMapping("/update-password")
    public ResponseWrapper updatePassword(String userId, String oldPassword, String newPassword) {
        return userServiceImpl.updatePassword( userId, oldPassword, newPassword );
    }
}
