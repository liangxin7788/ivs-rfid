package com.fun.business.sharon.biz.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.fun.business.sharon.biz.business.vo
 * @ClassName: AddMassageVo
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/10/31 17:03
 * @UpdateDate: 2019/10/31 17:03
 */
@Data
public class AddMassageVo {

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("come from")
    private String comeFrom;

    @ApiModelProperty("Massage")
    private String massage;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("E-mail")
    private String customerEmail;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("地址")
    private String address;

}
