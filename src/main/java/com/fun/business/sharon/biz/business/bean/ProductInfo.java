package com.fun.business.sharon.biz.business.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.business.sharon.biz.business.vo.SimilarApplicationVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private String productTypeCodes;

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
     * 尺寸
     */
    private String size;

    /**
     * 应用领域
     */
    private String application;

    /**
     * 图片
     */
    private String images;

    /**
     * 芯片类型
     */
    private String chipType;

    /**
     * 读距
     */
    private String readingRange;

    /**
     * 详细参数
     */
    private String detailParam;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    @TableField(exist = false)
    private List<SimilarApplicationVo> productSimilars;


}
