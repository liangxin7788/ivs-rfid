package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-12-28
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserList")
    @ApiOperation("获取用户列表")
    public GlobalResult<?> getUserList(@RequestParam(value = "userName", required = false) String userName){
        return GlobalResult.newSuccess(userService.getUserList(userName));
    }

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public GlobalResult<?> addUser(@RequestBody User user){
        try {
            if(user.getUserName().length() < 2){
                return GlobalResult.newError("添加用户失败! 用户名长度太短！");
            }
            if(user.getPassword().length() < 6){
                return GlobalResult.newError("添加用户失败! 密码安全性较低！");
            }
            userService.addUser(user);
        }catch (Exception e){
            log.error("添加用户失败" + e.getMessage(), e);
            return GlobalResult.newError("添加失败！" + e.getMessage());
        }
        return GlobalResult.newSuccess("添加成功！");
    }

    @GetMapping("/deleteUser")
    @ApiOperation("删除用户")
    public GlobalResult<?> deleteUser(@RequestParam(value = "userId", required = true)Integer userId){
        userService.deleteUser(userId);
        return GlobalResult.newSuccess("删除成功！");
    }

}
