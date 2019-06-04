package com.yhbc.base.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.yhbc.base.BaseAppication;

import greendao.dao.DaoMaster;
import greendao.dao.DaoSession;

public class DBUtils {
    public DaoSession daoSession = null;
    public SQLiteDatabase db = null;
    private MySQLiteOpenHelper helper = null;
    private static DaoMaster daoMaster = null;
    private static DBUtils dbUtils;

    public static DBUtils getInstanse() {
        if (dbUtils == null) {
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }

    public boolean initDB() {
        setupDatabase();
        return true;
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            setupDatabase();
        }
        return db;
    }


    /**
     * 下面代码仅仅需要执行一次，一般会放在application
     * initial Database
     */
    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        //if(null == helper) {
            helper = new MySQLiteOpenHelper(new GreenDaoContext(), "tablet.db", null);
        //}
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
//        if (daoMaster == null) {
            daoMaster = new DaoMaster(db);
//        }
//        if (daoSession == null) {
            daoSession = daoMaster.newSession();
//        }
        BaseAppication.baseApp.setDb(db);
        BaseAppication.baseApp.setDaoSession(daoSession);
    }

}
