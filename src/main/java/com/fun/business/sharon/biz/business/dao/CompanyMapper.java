package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.Company;
import com.fun.business.sharon.biz.business.vo.IdAndTitle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-12-11
 */
@Repository
public interface CompanyMapper extends BaseMapper<Company> {

    List<IdAndTitle> getNewsTitle();
}
