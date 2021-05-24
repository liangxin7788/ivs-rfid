package com.fun.business.sharon.biz.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.fun.business.sharon.biz.business.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 阿里云OSS控制层
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/1/19 11:00
 * @UpdateDate: 2021/1/19 11:00
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */
@Slf4j
@RestController
public class OSSController {

    @Autowired
    private OSSService ossService;

    @RequestMapping(value = "/headImgUpload", produces = "text/html;charset=UTF-8")
    public String headImgUpload(MultipartFile file) {
        Map<String, Object> value = new HashMap<>();
        try {
            String url = ossService.updateHead(file);
            log.debug("图片路径{}",url);
            value.put("data", url);
            value.put("code", 200);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            log.error("图片上传失败！" + e.getMessage(), e);
            value.put("code", 600);
            value.put("msg", "图片上传失败！");
        }
        return JSONObject.toJSONString(value);
    }

}
