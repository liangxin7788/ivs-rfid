package com.fun.business.sharon.biz.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.AboutUs;
import com.fun.business.sharon.biz.business.dao.AboutUsMapper;
import com.fun.business.sharon.biz.business.service.AboutUsService;
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
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2020-04-06
 */
@Service
@Slf4j
public class AboutUsServiceImpl extends ServiceImpl<AboutUsMapper, AboutUs> implements AboutUsService {

    @Autowired
    private AboutUsMapper aboutUsMapper;

    @Value("${sharon.file_path}")
    private String filePath;

    @Value("${sharon.file_url}")
    private String fileUrl;

    @Override
    public boolean addAboutUs(HttpServletRequest request) {

        MultipartFile image = ((MultipartHttpServletRequest) request).getFile("image"); // partner
        String companyProfile = request.getParameter("companyProfile");
        String advantage = request.getParameter("advantage");

        AboutUs aboutUs = new AboutUs();
        aboutUs.setAdvantage(advantage);
        aboutUs.setCompanyProfile(companyProfile);
        aboutUs.setCreateAt(new Date());
        aboutUs.setUpdateAt(new Date());

        if (ObjectUtil.isNotEmpty(image)) {
            String filename = image.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));

            String unitId = UUID.randomUUID().toString().replaceAll("-", "");
            filename = unitId + suffixName;
            File dest = new File(filePath + "picture/" + filename);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
                image.transferTo(dest);
            } catch (Exception e) {
                log.error("添加公司简介上传图片失败！" + e.getMessage(), e);
                throw new OperateException("添加公司简介上传图片失败！");
            }
            aboutUs.setPartner(fileUrl + filename);
        }
        return aboutUsMapper.insert(aboutUs) == 1 ? true : false;
    }
}
