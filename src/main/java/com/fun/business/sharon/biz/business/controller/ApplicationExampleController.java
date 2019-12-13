package com.fun.business.sharon.biz.business.controller;

import com.fun.business.sharon.common.GlobalResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.fun.business.sharon.biz.business.controller
 * @ClassName: ApplicationExampleController
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/12/11 10:07
 * @UpdateDate: 2019/12/11 10:07
 */
@RestController
@RequestMapping("/applicationExample")
public class ApplicationExampleController {

    @GetMapping("/getTitle")
    public GlobalResult<?> getTitle(){
        return GlobalResult.newSuccess("产品示例");
    }


}
