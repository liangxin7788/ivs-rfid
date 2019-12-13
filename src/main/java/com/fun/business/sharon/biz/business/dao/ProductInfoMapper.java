package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.ProductInfo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@Repository
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

    int getProductListCount(@Param("vo") ProductListSearchVo vo);

    List<ProductInfo> getProductList(@Param("vo")ProductListSearchVo vo, int offset, int pageSize);
}
