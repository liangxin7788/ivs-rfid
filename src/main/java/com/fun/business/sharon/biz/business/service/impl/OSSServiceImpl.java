package com.fun.business.sharon.biz.business.service.impl;

import com.fun.business.sharon.biz.business.service.OSSService;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.OSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 测试接口文档生成
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/1/19 11:02
 * @UpdateDate: 2021/1/19 11:02
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */
@Service
public class OSSServiceImpl implements OSSService {

    @Override
    public String updateHead(MultipartFile file) throws OperateException {
        if (file == null || file.getSize() <= 0) {
            throw new OperateException("file不能为空");
        }
        OSSUtil ossClient=new OSSUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

}
