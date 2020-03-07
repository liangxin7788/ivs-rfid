package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.Application;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2020-03-07
 */
public interface ApplicationService extends IService<Application> {

    List<String> selectAppTypes();

    String addApp(HttpServletRequest request);
}
