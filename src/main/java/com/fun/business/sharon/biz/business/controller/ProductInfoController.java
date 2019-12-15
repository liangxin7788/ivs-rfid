package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fun.business.sharon.biz.business.service.ProductInfoService;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public GlobalResult<?> addProduct(AddProductVo vo){
            Integer result = 0;
        try {
            CheckParamUtil.checkParamForCommit(vo, new String[]{"productTypeId", "productEnName", "description", "application"});
            result = productInfoService.addProduct(vo);
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

}
