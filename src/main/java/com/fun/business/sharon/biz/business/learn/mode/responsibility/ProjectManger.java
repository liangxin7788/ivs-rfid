package com.fun.business.sharon.biz.business.learn.mode.responsibility;

/**
 * @Description: 具体处理者-项目经理审批
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/2/18 17:31
 * @UpdateDate: 2021/2/18 17:31
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */
public class ProjectManger extends AuditHandler{

    @Override
    public void handleLeaveDay(String user, Integer day) {
        if (day <= 3) {
            System.out.println("项目经理审批：同意【"+user+"】,请假【"+day+"】天");
        }else {
            System.out.println("项目经理无权审批");
            // 下放到下一个审批者处理
            if (getSuccessor() != null){
                getSuccessor().handleLeaveDay(user,day);
            }
        }
    }
}
