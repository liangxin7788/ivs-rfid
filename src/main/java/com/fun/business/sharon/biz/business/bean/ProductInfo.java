package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品类型id
     */
    private Integer productTypeId;

    /**
     * 产品中文名
     */
    private String cnName;

    /**
     * 产品英文名
     */
    private String enName;

    /**
     * 产品描述信息
     */
    private String description;

    /**
     * 规格参数
     */
    private String model;

    /**
     * 应用领域
     */
    private String application;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    @TableField(exist = false)
    private ProductType productType;

    @TableField(exist = false)
    private ProductPic productPic;

}
