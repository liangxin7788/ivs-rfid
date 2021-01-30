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
import com.fun.business.sharon.biz.business.vo.ProductListSearchVo;
import com.fun.business.sharon.biz.business.vo.SimilarApplicationVo;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Autowired
    RedisUtil redisUtil;

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

    @Override
    @Transactional
    public String delProduct(Integer productId) {
        ProductInfo productInfo = productInfoMapper.selectById(productId);
        log.info("删除产品 " + JSON.toJSONString(productInfo));
        return productInfoMapper.deleteById(productInfo) == 1?"删除成功！":"删除失败！";
    }

    @Override
    public ProductInfo getProductDetail(Integer productId) {
        Object o = redisUtil.get("ProductDetailId=" + productId);
        ProductInfo productDetail = null;
        if (ObjectUtil.isNotEmpty(o)) {
            productDetail = (ProductInfo)o;
        }else {
            productDetail = productInfoMapper.getProductDetail(productId);
            redisUtil.set("ProductDetailId=" + productId, productDetail);
        }
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
        MultipartFile pdf = ((MultipartHttpServletRequest) request).getFile("pdf");
        String id = request.getParameter("id");
        Integer updateId = null;
        if (StringUtils.isNotEmpty(id)) {
            updateId = Integer.valueOf(id);
        }
        String oldImages = request.getParameter("oldImages");
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

        String detailParam = request.getParameter("detailParam");

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

        productInfo.setDetailParam(detailParam);
        String imageStr = "";
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
            imageStr = builder.toString();
            productInfo.setImages(imageStr.substring(0, imageStr.length() - 1));
        }

        if (!pdf.isEmpty()) {
            String filename = pdf.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));

            String unitId = UUID.randomUUID().toString().replaceAll("-", "");
            filename = unitId + suffixName;
            File dest = new File(filePath + "picture/" + filename);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                pdf.transferTo(dest);
                productInfo.setPdfUrl(fileUrl + "picture/" + filename);
            } catch (Exception e) {
                log.error("添加产品文件上传pdf失败！" + e.getMessage(), e);
                throw new OperateException("添加产品文件上传pdf失败！");
            }
        }

        if (ObjectUtils.isNotEmpty(updateId)) {
            productInfo.setId(updateId);
            productInfo.setImages(imageStr + "," + oldImages);
            return productInfoMapper.updateById(productInfo);
        }
        return productInfoMapper.insert(productInfo);
    }

    @Override
    public List<ProductInfo> getHomeProducts() {
        return productInfoMapper.getHomeProducts();
    }

    @Override
    public void downloadPDF(HttpServletRequest req, HttpServletResponse resp, String pdfUrl) {
        if (StringUtils.isNotEmpty(pdfUrl)) {
            File file = new File(pdfUrl);
//            File file = new File("C:\\ITextTest-image.pdf");
            String fileName = file.getName();
            try {
                // 设置响应的头部信息
                resp.setHeader("content-disposition", "attachment;filename=" + fileName);
                // 设置响应内容的类型
                resp.setContentType(getFileContentType(fileName)+"; charset=UTF-8");
                // 设置响应内容的长度
                resp.setContentLength((int) file.length());
                // 输出
                outStream(new FileInputStream(file), resp.getOutputStream());
                System.out.println("输出完毕！");
            }catch (Exception e){
                log.error("文件下载失败！" + e.getMessage(), e);
            }

            // ======================================================

            // 设置下载文件的mineType，告诉浏览器下载文件类型
//            String mineType = request.getServletContext().getMimeType(filename);
//            response.setContentType(mineType);
            // 设置一个响应头，无论是否被浏览器解析，都下载
//            response.setHeader("Content-disposition", "attachment; filename=" + filename);

            // 以流的形式下载文件
//            InputStream fis = null;
//            try {
//                fis = new BufferedInputStream(new FileInputStream(pdfUrl));
//                byte[] buffer = new byte[fis.available()];
//                fis.read(buffer);
//
//                fis.close();
//                // 清空response
//                resp.reset();
//                // 设置response的Header
//                resp.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes()));
//                OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
//                resp.setContentType("application/pdf;charset=UTF-8");
//                toClient.write(buffer);
//                toClient.flush();
//                toClient.close();
//                System.out.println("下载成功");
//            }catch (Exception e){
//                log.error("文件下载失败！" + e.getMessage(), e);
//            }finally {
//                try {
//                    if (fis != null) {
//                        fis.close();
//                    }
//                }catch (Exception e){
//                    log.error("文件下载后未关流！" + e.getMessage(), e);
//                }
//            }
        }else {
            throw new OperateException("There is no file for this product!");
        }
    }

    /**
     * 基础字节数组输出
     */
    private static void outStream(InputStream is, OutputStream os) {
        try {
            byte[] buffer = new byte[10240];
            int length = -1;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                os.flush();
            }
        } catch (Exception e) {
            System.out.println("执行 outStream 发生了异常：" + e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
            }
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 文件的内容类型
     */
    private static String getFileContentType(String name){
        String result = "";
        String fileType = name.toLowerCase();
        if (fileType.endsWith(".png")) {
            result = "image/png";
        } else if (fileType.endsWith(".gif")) {
            result = "image/gif";
        } else if (fileType.endsWith(".jpg") || fileType.endsWith(".jpeg")) {
            result = "image/jpeg";
        } else if(fileType.endsWith(".svg")){
            result = "image/svg+xml";
        }else if (fileType.endsWith(".doc")) {
            result = "application/msword";
        } else if (fileType.endsWith(".xls")) {
            result = "application/x-excel";
        } else if (fileType.endsWith(".zip")) {
            result = "application/zip";
        } else if (fileType.endsWith(".pdf")) {
            result = "application/pdf";
        } else {
            result = "application/octet-stream";
        }
        return result;
    }

}
