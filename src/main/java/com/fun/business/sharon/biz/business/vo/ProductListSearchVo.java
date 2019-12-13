package com.fun.business.sharon.biz.business.vo;

import com.fun.business.sharon.common.Page;
import lombok.Data;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: ProductListSearchVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/12/13 9:51
 * @UpdateDate: 2019/12/13 9:51
 */
@Data
public class ProductListSearchVo extends Page {

    private Integer productTypeId;

    private String productTitle;

}
