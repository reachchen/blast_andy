package com.yhbc.base.common.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

import greendao.dao.DaoMaster;

/**
 * 数据库升级辅助类
 *
 * @author xuhaijiang on 2018/7/7.
 */
public class UpdateHelper {
    /**
     * 升级
     *
     * @param db         db
     * @param oldVersion 旧版本
     * @param newVersion 新版本
     */
    public void update(SQLiteDatabase db, int oldVersion, int newVersion) {
        Database database = new StandardDatabase(db);
        db.beginTransaction();
        try {
            DaoMaster.createAllTables(database, true);
//            if (oldVersion < 4043 && newVersion >= 4043) {
            updateVersion(db);
//            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    private void updateVersion(SQLiteDatabase db) throws Exception {
        if (findTable("com_yhbc_tablet_bean_STest", db) && !isColumnExists("com_yhbc_tablet_bean_STest", "xx_modifyTime", db)) {
            db.execSQL("alter table com_yhbc_tablet_bean_STest add column xx_modifyTime INTEGER ");

        }
    }


    /**
     * 表中列是否存在
     *
     * @param tableName tableName
     * @param column    列名
     * @return true:存在
     * @throws Exception
     */
    public static boolean isColumnExists(String tableName, String column, SQLiteDatabase db) throws Exception {
        Cursor cursor = null;
        boolean isFound = false;
        try {
            cursor = db.rawQuery(
                    "SELECT name FROM sqlite_master " +
                            "WHERE name='" + tableName + "' and sql like'%" + column + "%'", null);

            if (cursor.moveToFirst()) {
                String str = cursor.getString(0);
                System.out.println("tableName:" + str);
                isFound = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return isFound;
    }

    /**
     * 表是否存在
     *
     * @param tableName TableName
     * @return true:存在
     * @throws Exception
     */
    public static boolean findTable(String tableName, SQLiteDatabase db) throws Exception {
        Cursor cursor = null;
        boolean isFound = false;
        try {
            cursor = db.rawQuery(
                    "SELECT name FROM sqlite_master " +
                            "WHERE type='table' and name='" + tableName + "'", null);
            if (cursor.moveToFirst()) {
                String str = cursor.getString(0);
                System.out.println("tableName:" + str);
                isFound = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return isFound;
    }


}
