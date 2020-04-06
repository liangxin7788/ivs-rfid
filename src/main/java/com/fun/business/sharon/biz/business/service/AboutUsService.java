package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.AboutUs;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2020-04-06
 */
public interface AboutUsService extends IService<AboutUs> {

    boolean addAboutUs(HttpServletRequest request);
}
