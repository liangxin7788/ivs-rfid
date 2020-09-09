package com.fun.business.sharon.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.common
 * @ClassName: EmailRecieverList
 * @Description: 客户线上留言时，邮件接收者列表
 * @Author: liangxin
 * @CreateDate: 2019/10/31 18:08
 * @UpdateDate: 2019/10/31 18:08
 */
@Data
public class EmailRecieverList implements Serializable {

    private List<String> toMail;

    public EmailRecieverList() {
        toMail = new ArrayList<>();
        toMail.add("liukechao1@bngrp.com");
        toMail.add("liangxin1@bngrp.com");
//        toMail.add("Sharon.loo@outlook.com");
//        toMail.add("lx__job@163.com");
    }

}
