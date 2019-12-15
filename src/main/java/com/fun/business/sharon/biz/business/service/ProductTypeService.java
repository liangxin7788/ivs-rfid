package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.ProductType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
public interface ProductTypeService extends IService<ProductType> {

    List<ProductType> getTypeList();

    Integer deleteType(Integer typeId);
}
