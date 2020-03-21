package com.fun.business.sharon.biz.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fun.business.sharon.biz.business.bean.Application;
import com.fun.business.sharon.biz.business.service.ApplicationService;
import com.fun.business.sharon.common.GlobalResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/getAppNames")
    @ApiOperation("获取应用名字")
    public GlobalResult<?> getAppNames(){
        List<String> list = applicationService.selectAppTypes();
        return GlobalResult.newSuccess(list);
    }

    @GetMapping("/getAppList")
    @ApiOperation("获取应用信息")
    public GlobalResult<?> getAppList(@RequestParam(value = "appType", required = false)String appType){
        return GlobalResult.newSuccess(applicationService.getOne(new QueryWrapper<Application>().eq("app_type", appType)));
    }

    @PostMapping("/addApp")
    @ApiOperation("添加一个应用领域信息")
    public GlobalResult<?> addApp(HttpServletRequest request){
        return GlobalResult.newSuccess(applicationService.addApp(request));
    }

    @PostMapping("/delApp")
    @ApiOperation("删除一个应用领域信息")
    public GlobalResult<?> delApp(@RequestParam Integer appId){
        return GlobalResult.newSuccess(applicationService.removeById(appId));
    }

}
