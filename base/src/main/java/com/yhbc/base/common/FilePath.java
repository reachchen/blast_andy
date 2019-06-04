package com.yhbc.base.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by chengwen on 2017-12-14.
 */
public class FilePath {

    public static final String DATA_PATH = Environment.getExternalStorageDirectory() + File.separator
            + "winboxcash";

    public static final String configPath = Environment.getExternalStorageDirectory() + File.separator
            + "winboxcash" + File.separator + "config.xml";

    public static final String ShopperPath = Environment.getExternalStorageDirectory() + File.separator
            + "winboxcash" + File.separator + "shopperDetail.data";

    public static final String machinePath = Environment.getExternalStorageDirectory() + File.separator
            + "winboxcash" + File.separator + "machineDetail.data";

}
