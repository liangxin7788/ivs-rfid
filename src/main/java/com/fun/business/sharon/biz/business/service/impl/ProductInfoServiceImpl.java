package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.ProductInfo;
import com.fun.business.sharon.biz.business.dao.ProductInfoMapper;
import com.fun.business.sharon.biz.business.service.ProductInfoService;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;
import com.fun.business.sharon.biz.business.vo.SimilarApplicationVo;
import com.fun.business.sharon.common.OperateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Value("${sharon.file_path}")
    private String filePath;

    @Value("${sharon.file_url}")
    private String fileUrl;

    @Override
    public IPage<ProductInfo> getProductList(ProductListSearchVo vo) {

        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();

        pageIndex = pageIndex==0?1:pageIndex;
        pageSize = pageSize==0?10:pageSize;
        int offset = (pageIndex-1)*pageSize;

        IPage<ProductInfo> page = new Page<>(pageIndex, pageSize);
        int total = productInfoMapper.getProductListCount(vo);
        List<ProductInfo> list = productInfoMapper.getProductList(vo, offset, pageSize);

        page.setCurrent(pageIndex);
        page.setPages(pageSize);
        page.setRecords(list);
        page.setTotal(total);
        return page;
    }

//    @Override
//    @Transactional
//    public Integer addProduct(AddProductVo vo) {
//        ProductInfo productInfo = new ProductInfo();
////        String productTypeCodes = vo.getProductTypeCodes();
////        String[] codes = productTypeCodes.split(",");
////        String joinCodes = "";
////        for (int i = 0; i < codes.length; i++) {
////
////            if (i != codes.length){
////                joinCodes += codes[i] + ",";
////            }else {
////                joinCodes += codes[i];
////            }
////        }
//
//        productInfo.setProductTypeCodes(vo.getProductTypeCodes());
//        productInfo.setCnName(vo.getProductCnName());
//        productInfo.setEnName(vo.getProductEnName());
//
//        productInfo.setDescription(vo.getDescription());
//        productInfo.setModel(vo.getModel());
//        productInfo.setApplication(vo.getApplication());
//        Date date = new Date();
//        productInfo.setCreateAt(date);
//        productInfo.setUpdateAt(date);
//
//        log.info("添加产品：" + JSON.toJSONString(productInfo));
//        productInfoMapper.insert(productInfo);
//
//        MultipartFile file = vo.getFile();
//        String fileName = vo.getFileName();
//
//        if (ObjectUtils.isEmpty(file)) {
//            throw new OperateException("必须上传样品图!");
//        }
//        ProductPic productPic = new ProductPic();
//        productPic.setCreateAt(date);
//        productPic.setUpdateAt(date);
//        productPic.setImageName(fileName);
//        productPic.setProductInfoId(productInfo.getId());
//
//        String filename = file.getOriginalFilename();
//        String suffixName = filename.substring(filename.lastIndexOf("."));
//        String unitId = UUID.randomUUID().toString().replaceAll("-", "");
//
//        filename = unitId + suffixName;
//
//        File dest = new File(filePath + filename);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
//            file.transferTo(dest);
//        } catch (Exception e) {
//            log.error("添加产品文件上传失败！" + e.getMessage(), e);
//            throw new OperateException("添加产品文件上传失败！");
//        }
//        productPic.setImageUrl(fileUrl + filename);
//        return productPicMapper.insert(productPic);
//    }

    @Override
    @Transactional
    public String delProduct(Integer productId) {
        ProductInfo productInfo = productInfoMapper.selectById(productId);
        log.info("删除产品 " + JSON.toJSONString(productInfo));
        return productInfoMapper.deleteById(productInfo) == 1?"删除成功！":"删除失败！";
    }

    @Override
    public ProductInfo getProductDetail(Integer productId) {
        ProductInfo productDetail = productInfoMapper.getProductDetail(productId);
        String applications = productDetail.getApplication();
        if (StringUtils.isNotEmpty(applications)) {
            String[] split = applications.split(",");
            List<SimilarApplicationVo> similarList = productInfoMapper.selectSimilarByApplication(productDetail.getId(), split[0]);
            if (CollectionUtils.isNotEmpty(similarList)) {
                productDetail.setProductSimilars(similarList);
            }
        }
        return productDetail;
    }

    @Override
    @Transactional
    public Integer addProduct(HttpServletRequest request) {

        List<MultipartFile> images = ((MultipartHttpServletRequest) request).getFiles("images");
        String cnName = request.getParameter("cnName");
        String enName = request.getParameter("enName");
        String description = request.getParameter("description");
        // 产品类型多选
        String productTypeCodes = request.getParameter("productTypeCodes");

        String model = request.getParameter("model");
        String size = request.getParameter("size");
        String application = request.getParameter("application");
        String chipType = request.getParameter("chipType");
        String readingRange = request.getParameter("readingRange");

        ProductInfo productInfo = new ProductInfo();
        productInfo.setCreateAt(new Date());
        productInfo.setUpdateAt(new Date());

        productInfo.setEnName(enName);
        productInfo.setCnName(cnName);
        productInfo.setDescription(description);
        productInfo.setProductTypeCodes(productTypeCodes);

        productInfo.setModel(model);
        productInfo.setSize(size);
        productInfo.setApplication(application);
        productInfo.setChipType(chipType);
        productInfo.setReadingRange(readingRange);

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
            productInfo.setImages(imageStr.substring(0, imageStr.length() - 1));
        }
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public List<ProductInfo> getHomeProducts() {
        return productInfoMapper.getHomeProducts();
    }


}
