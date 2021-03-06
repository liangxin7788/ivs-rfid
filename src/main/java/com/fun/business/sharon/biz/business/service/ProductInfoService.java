package com.fun.business.sharon.biz.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.business.sharon.biz.business.bean.ProductInfo;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

//    Integer addProduct(AddProductVo vo);

    String delProduct(Integer productId);

    ProductInfo getProductDetail(Integer productId);

    Integer addProduct(HttpServletRequest request);

    List<ProductInfo> getHomeProducts();

    void downloadPDF(HttpServletRequest request, HttpServletResponse response, String pdfUrl);
}
