package com.fun.business.sharon.biz.business.learn.mode.responsibility;

/**
 * @Description: 抽象处理对象
 * @Company: 宝能汽车销售有限公司
 * @Author: 梁新
 * @CreateDate: 2021/2/18 17:30
 * @UpdateDate: 2021/2/18 17:30
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu
 */
public abstract class AuditHandler {

    //持有下一个处理请求的对象
    protected AuditHandler successor = null;
    public AuditHandler getSuccessor() {
        return successor;
    }
    public void setSuccessor(AuditHandler successor) {
        this.successor = successor;
    }

    public abstract void handleLeaveDay (String user,Integer day);

}
