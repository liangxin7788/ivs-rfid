package com.fun.business.sharon.biz.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: AddProductVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/12/13 10:45
 * @UpdateDate: 2019/12/13 10:45
 */
@Data
public class AddProductVo {

    @ApiModelProperty("产品所属分类")
    private Integer productTypeId;

    @ApiModelProperty("产品中文名")
    private String productCnName;

    @ApiModelProperty("产品英文名")
    private String productEnName;

    @ApiModelProperty("产品描述信息")
    private String description;

    @ApiModelProperty("产品规格")
    private String model;

    @ApiModelProperty("产品应用领域")
    private String application;

    @ApiModelProperty("产品样品图")
    private MultipartFile file;

    @ApiModelProperty("样品图名称")
    private String fileName;

}
