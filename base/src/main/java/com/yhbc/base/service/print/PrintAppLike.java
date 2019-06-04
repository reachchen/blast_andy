package com.yhbc.base.service.print;

import com.yhbc.base.service.IApplicationLike;

/**
 * @author xuhaijiang on 2018/10/26.
 */
public class PrintAppLike implements IApplicationLike{
    @Override
    public void onCreate() {
//        ManagerFactory.getManage().registerService(PrintService.class,PrintServiceImpl.class);
//        PrintService service= ManagerFactory.getService(PrintService.class)  ;
    }

    @Override
    public void onStop() {
//        IManager.ManagerFactory.getManage().unRegisterService(PrintService.class);
    }
}
