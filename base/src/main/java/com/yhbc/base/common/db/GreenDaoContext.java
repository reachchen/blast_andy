package com.yhbc.base.common.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.yhbc.base.BaseAppication;

import java.io.File;
import java.io.IOException;

public class GreenDaoContext extends ContextWrapper {

    public GreenDaoContext() {
        super(BaseAppication.baseApp);
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     */
    @Override
    public File getDatabasePath(String dbName) {
        String dbDir ="/mnt/sdcard/winboxcash/";
        if (TextUtils.isEmpty(dbDir)){
            return null;
        }
        File baseFile = new File(dbDir);

        // 目录不存在则自动创建目录
        if (!baseFile.exists()){
            baseFile.mkdirs();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        buffer.append(File.separator);
        dbDir = buffer.toString();
        buffer.append(File.separator);
        buffer.append(dbName);
        String dbPath = buffer.toString();
        File dirFile = new File(dbDir);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        boolean isFileCreateSuccess = false;
        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isFileCreateSuccess = true;
        }
        if (isFileCreateSuccess) {
            return dbFile;
        }
        else {
            return super.getDatabasePath(dbName);
        }
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     * SQLiteDatabase.CursorFactory,
     * DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

    public static Context getContext() {
        return BaseAppication.baseApp;
    }

}