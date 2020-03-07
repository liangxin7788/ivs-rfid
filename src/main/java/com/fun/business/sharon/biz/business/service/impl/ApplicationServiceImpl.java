package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.Application;
import com.fun.business.sharon.biz.business.dao.ApplicationMapper;
import com.fun.business.sharon.biz.business.service.ApplicationService;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2020-03-07
 */
@Service
@Slf4j
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;


    @Value("${sharon.file_path}")
    private String filePath;

    @Value("${sharon.file_url}")
    private String fileUrl;

    @Override
    public List<String> selectAppTypes() {
        return applicationMapper.selectAppTypes();
    }

    @Override
    public String addApp(HttpServletRequest request) {
//        List<String> types = this.selectAppTypes();

        List<MultipartFile> images = ((MultipartHttpServletRequest) request).getFiles("images");
        String id = request.getParameter("id");
        String appType = request.getParameter("appType");
        String description = request.getParameter("description");

        Application application = new Application();
        application.setAppType(appType);
        application.setDescription(description);
        // 图片处理
        if (CollectionUtils.isNotEmpty(images)) {
            StringBuilder builder = new StringBuilder();
            for (MultipartFile file : images) {
                String filename = file.getOriginalFilename();
                String suffixName = filename.substring(filename.lastIndexOf("."));

                String unitId = UUID.randomUUID().toString().replaceAll("-", "");
                filename = unitId + suffixName;
                File dest = new File(filePath + "picture/" + filename);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
                    file.transferTo(dest);
                } catch (Exception e) {
                    log.error("添加产品文件上传失败！" + e.getMessage(), e);
                    throw new OperateException("添加产品文件上传失败！");
                }
                builder.append(fileUrl + filename).append(",");
            }
            String imageStr = builder.toString();
            application.setImages(imageStr.substring(0, imageStr.length() - 1));
        }
        if (ObjectUtil.isNotEmpty(id)) {
            application.setId(Integer.valueOf(id));
            log.info("修改应用领域：" + JSON.toJSONString(application));
            applicationMapper.updateById(application);
            return "修改成功！";
        }else {
            applicationMapper.insert(application);
            log.info("添加应用领域：" + JSON.toJSONString(application));
            return "添加成功！";
        }
    }
}
