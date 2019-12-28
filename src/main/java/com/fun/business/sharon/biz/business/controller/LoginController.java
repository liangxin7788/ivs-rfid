package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description 登录相关接口
 * @Author liangixin
 * @Date 2019-12-28 15:32
 */
//@RequestMapping("/login")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public GlobalResult<?> login(@RequestBody User user){
        if (StringUtil.isNotEmpty(user.getUserName()) && StringUtil.isNotEmpty(user.getPassword())) {
            try {
                user = userService.login(user);
            } catch (Exception e) {
                log.error("登录失败：" + e.getMessage(), e);
                return GlobalResult.newError("登录失败：" + e.getMessage());
            }
        }
        return GlobalResult.newSuccess(user);
    }

}
