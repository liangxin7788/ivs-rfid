package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.ProductInfo;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
public interface ProductInfoService extends IService<ProductInfo> {
    IPage<ProductInfo> getProductList(ProductListSearchVo vo);

    Integer addProduct(AddProductVo vo);
}
