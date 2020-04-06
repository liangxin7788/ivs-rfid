package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.bean.AboutUs;
import com.fun.business.sharon.biz.business.service.AboutUsService;
import com.fun.business.sharon.common.GlobalResult;
import com.fun.business.sharon.utils.CheckParamUtil;
import com.fun.business.sharon.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/aboutUs")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping("/addAboutUs")
    @ApiOperation("添加一条公司介绍信息")
    public GlobalResult<?> addAboutUs(HttpServletRequest request){
        boolean save = aboutUsService.addAboutUs(request);
        return GlobalResult.newSuccess(save == true?"添加成功！":"添加失败！");
    }

    @GetMapping("/useAboutUsInfo")
    @ApiOperation("页面使用第一条信息")
    public GlobalResult<?> useAboutUsInfo(@RequestParam Integer id){
        return GlobalResult.newSuccess(aboutUsService.getById(id));
    }

    @GetMapping("/delAboutUsInfo")
    @ApiOperation("删除一条信息")
    public GlobalResult<?> delAboutUsInfo(@RequestParam Integer id){
        return GlobalResult.newSuccess(aboutUsService.removeById(id));
    }

}
