package com.fun.business.sharon.biz.business.service;

import com.fun.business.sharon.common.OperateException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 测试接口文档生成
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/1/19 11:01
 * @UpdateDate: 2021/1/19 11:01
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */

public interface OSSService {

    String updateHead(MultipartFile file)throws OperateException;

}
