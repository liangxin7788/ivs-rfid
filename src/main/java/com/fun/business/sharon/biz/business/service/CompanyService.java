package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.Company;
import com.fun.business.sharon.biz.business.vo.IdAndTitle;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-11
 */
public interface CompanyService extends IService<Company> {

    List<Company> getNews(Integer newsId);

    List<IdAndTitle> getNewsTitle();
}
