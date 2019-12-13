package com.fun.business.sharon.biz.business.controller;


import com.fun.business.sharon.biz.business.service.CompanyService;
import com.fun.business.sharon.common.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangxin
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getCompanyInfo")
    public GlobalResult<?> getCompanyInfo(){
        return GlobalResult.newSuccess(companyService.getById(1));
    }

    @GetMapping("/getNews")
    public GlobalResult<?> getNews(@RequestParam(value = "newsId", required = false)Integer newsId){
        return GlobalResult.newSuccess(companyService.getNews(newsId));
    }

}
