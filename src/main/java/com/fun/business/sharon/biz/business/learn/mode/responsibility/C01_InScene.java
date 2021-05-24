package com.fun.business.sharon.biz.business.learn.mode.responsibility;

/**
 * @Description: 测试接口文档生成
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/2/18 17:29
 * @UpdateDate: 2021/2/18 17:29
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu main入口
 */
public class C01_InScene {

    public static void main(String[] args) {
        // 组装责任链
        AuditHandler h1 = new CeoManger();
        AuditHandler h2 = new DeptManger();
        AuditHandler h3 = new ProjectManger();
        h3.setSuccessor(h2);
        h2.setSuccessor(h1);
        /*
         * 测试输出
         * 项目经理无权审批
         * 部门经理无权审批
         * CEO审批：同意【Cicada】,请假【6】天
         */
        h3.handleLeaveDay("Cicada",6);
    }
}
