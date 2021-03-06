package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fun.business.sharon.biz.business.bean.ProductInfo;
import com.fun.business.sharon.biz.business.service.ProductInfoService;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.CheckParamUtil;
import com.fun.business.sharon.utils.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/productInfo")
@Api(description = "产品信息相关接口")
@Slf4j
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @PostMapping("/getProductList")
    @ApiOperation("列表查询")
    public GlobalResult<?> getProductList(@RequestBody ProductListSearchVo vo){
        return GlobalResult.newSuccess(productInfoService.getProductList(vo));
    }

    @PostMapping("/addProduct")
    @ApiOperation("添加产品")
    public GlobalResult<?> addProduct(HttpServletRequest request){
            Integer result = 0;
        try {
            result = productInfoService.addProduct(request);
//            result = productInfoService.addProduct(vo);
        }catch (Exception e){
            log.error("添加产品失败！" + e.getMessage(), e);
            return GlobalResult.newError("添加产品失败！");
        }
        return GlobalResult.newSuccess(result);
    }

    @GetMapping("/delProduct")
    @ApiOperation("删除产品")
    public GlobalResult<?> delProduct(@RequestParam(value = "productId", required = true)Integer productId){
        return GlobalResult.newSuccess(productInfoService.delProduct(productId));
    }

    @GetMapping("/getProductDetail")
    @ApiOperation("获取产品详情")
    public GlobalResult<?> getProductDetail(@RequestParam(value = "productId", required = true)Integer productId){
        return GlobalResult.newSuccess(productInfoService.getProductDetail(productId));
    }

    @GetMapping("/getHomeProducts")
    @ApiOperation("获取首页推送的产品")
    public GlobalResult<?> getHomeProducts(){
        return GlobalResult.newSuccess(productInfoService.getHomeProducts());
    }

    @GetMapping("/downloadPDF")
    @ApiOperation("下载产品PDF文件信息")
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response, Integer productId){
        ProductInfo productInfo = productInfoService.getById(productId);
        if (ObjectUtil.isNotEmpty(productInfo)) {
            productInfoService.downloadPDF(request, response, productInfo.getPdfUrl());
        }else {
            throw new OperateException("Download record no longer exists, please confirm!");
        }
    }

}
