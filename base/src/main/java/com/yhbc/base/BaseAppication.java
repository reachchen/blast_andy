package com.yhbc.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.beta.Beta;
import com.yhbc.base.common.db.DBUtils;
import com.yhbc.base.common.utils.FileUtil;

import java.io.File;
import java.util.Map;

import greendao.dao.DaoSession;

/**
 * Base
 * Created by xuhaijiang on 2018/5/4.
 */
public class BaseAppication extends MultiDexApplication {
    public static BaseAppication baseApp;
    public static DaoSession daoSession = null;
    public static SQLiteDatabase db = null;
    /**
     * 是否初始化了各组件
     */
    private boolean isInitComponent;
    //数据库是否升级标志
    public static boolean dbFlag = false;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 安装tinker
        MultiDex.install(this);
        Beta.installTinker();
    }

    /**
     * 初始化组件服务
     */
    public void initComponentService() {
        isInitComponent = true;
    }

    public boolean isInitComponent() {
        return isInitComponent;
    }

    public void setDb(SQLiteDatabase sQLiteDatabase) {
        db = sQLiteDatabase;
    }

    public void setDaoSession(DaoSession daoSessions) {
        daoSession = daoSessions;
    }

    public SQLiteDatabase getDb() {
        if (null == db || !getIfdb()) {
            DBUtils.getInstanse().initDB();
        }
        return db;
    }

    public static DaoSession getDaoSession() {
        if (null == daoSession || !getIfdb()) {
            DBUtils.getInstanse().initDB();
        }
        return daoSession;
    }

    private static boolean getIfdb() {
        File detail = new File(FileUtil.fileDirPath + "/winboxcash/tablet.db");
        if (detail.exists()) {
            return true;
        }
        return false;
    }

    public boolean getIsDebug() {
        return false;
    }

    public Map<String, String> getApiConfigMap() {
        //在BaseApp中实现
        return null;
    }

    public void setApiConfigMap(Map<String, String> apiConfigMap) {
        //在BaseApp中实现
    }
}
