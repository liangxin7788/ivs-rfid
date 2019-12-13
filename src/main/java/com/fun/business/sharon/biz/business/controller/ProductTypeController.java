package com.fun.business.sharon.biz.business.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fun.business.sharon.biz.business.bean.ProductType;
import com.fun.business.sharon.biz.business.service.ProductTypeService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@RestController
@Api(description = "产品类型维护")
@RequestMapping("/productType")
@Slf4j
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping("/addOrEditType")
    @ApiOperation("添加/编辑 产品类型")
    public GlobalResult<?> addOrEditType(@RequestBody ProductType productType){
        CheckParamUtil.checkParamForCommit(productType, new String[]{"typeEn", "typeCn"});
        boolean save = false;
        productType.setUpdateAt(new Date());
        if (ObjectUtils.isNotEmpty(productType.getId())) {
            log.info("编辑产品类型--> {}", JSON.toJSONString(productType));
            save = productTypeService.updateById(productType);
        }else {
            productType.setCreateAt(new Date());
            save = productTypeService.save(productType);
        }
        return GlobalResult.newSuccess(save);
    }

    @PostMapping("/deleteType")
    @ApiOperation("删除产品类型")
    public GlobalResult<?> deleteType(@RequestBody ProductType productType){
        CheckParamUtil.checkParamForCommit(productType, new String[]{"typeEn", "typeCn"});
        productType.setCreateAt(new Date());
        productType.setUpdateAt(new Date());
        return GlobalResult.newSuccess(productTypeService.save(productType));
    }

    @PostMapping("/getTypeList")
    @ApiOperation("获取产品类型列表")
    public GlobalResult<?> getTypeList(){
        return GlobalResult.newSuccess(productTypeService.getTypeList());
    }

}
