package com.fun.business.sharon.biz.business.learn.mode.responsibility;

/**
 * @Description: 具体处理者-CEO审批
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/2/18 17:35
 * @UpdateDate: 2021/2/18 17:35
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */
public class CeoManger extends AuditHandler{
    @Override
    public void handleLeaveDay(String user, Integer day) {
        if (day > 5){
            System.out.println("CEO审批：同意【"+user+"】,请假【"+day+"】天");
        } else {
            if (getSuccessor() != null){
                getSuccessor().handleLeaveDay(user,day);
            }
        }
    }
}
