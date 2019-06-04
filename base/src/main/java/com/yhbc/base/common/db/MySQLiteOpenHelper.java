package com.yhbc.base.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.yhbc.base.BaseAppication;

import org.greenrobot.greendao.database.Database;

import greendao.dao.DaoMaster;
import greendao.dao.UserInfoDao;


/**
 * Created by wb on 2017/3/30.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            BaseAppication.dbFlag = true;
        }
//        new UpdateHelper().update(db,oldVersion,newVersion);

        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {


                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                },

                UserInfoDao.class
        );


    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            BaseAppication.dbFlag = true;
        }

        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                },

                UserInfoDao.class);//32
    }
}