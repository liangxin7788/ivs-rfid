package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.Company;
import com.fun.business.sharon.biz.business.dao.CompanyMapper;
import com.fun.business.sharon.biz.business.service.CompanyService;
import com.fun.business.sharon.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-11
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getNews(Integer newsId) {
        List<Company> list = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(newsId)) {
            list.add(companyMapper.selectById(newsId));
            return list;
        }
        return companyMapper.selectList(null);
    }
}
