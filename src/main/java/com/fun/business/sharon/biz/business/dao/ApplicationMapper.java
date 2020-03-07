package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.Application;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2020-03-07
 */
@Repository
public interface ApplicationMapper extends BaseMapper<Application> {

    @Select("SELECT app_type FROM application")
    List<String> selectAppTypes();
}
