package com.fun.business.sharon.biz.business.service;

import com.fun.business.sharon.biz.business.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-28
 */
public interface UserService extends IService<User> {

    List<User> getUserList(String userName);

    void addUser(User user) throws Exception;

    void deleteUser(Integer userId);

    User login(User user) throws Exception;
}
