package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.User;
import com.fun.business.sharon.biz.business.dao.UserMapper;
import com.fun.business.sharon.biz.business.service.UserService;
import com.fun.business.sharon.common.Const;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList(String userName) {
        if (StringUtil.isNotEmpty(userName)) {
            return userMapper.selectList(new QueryWrapper<User>().like("user_name", userName));
        }
        return userMapper.selectList(null);
    }

    @Override
    @Transactional
    public void addUser(User user) throws Exception{
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (CollectionUtils.isNotEmpty(userList)) {
            throw new OperateException("用户名已存在！请换一个重试！");
        }
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        String password = user.getPassword();
        String str = password + Const.IVS_RFID;

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newPassword = base64en.encode(md5.digest(str.getBytes("utf-8")));
        user.setPassword(newPassword);

        userMapper.insert(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        User user = userMapper.selectById(userId);
        if (ObjectUtil.isNotEmpty(user)) {
            log.info("删除用户：" + JSON.toJSONString(user));
            userMapper.deleteById(user);
        }
    }

    @Override
    public User login(User user) throws Exception{
        User userByName = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (ObjectUtil.isNotEmpty(userByName)) {
            // 数据库已存在的密码
            String dataPassword = userByName.getPassword();

            String password = user.getPassword();
            String str = password + Const.IVS_RFID;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newPassword = base64en.encode(md5.digest(str.getBytes("utf-8")));
            if (!dataPassword.equals(newPassword)) {
                throw new OperateException("用户密码错误！");
            }
        }else {
            throw new OperateException("用户不存在！");
        }
        return userByName;
    }
}
