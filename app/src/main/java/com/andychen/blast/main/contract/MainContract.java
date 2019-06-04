package com.andychen.blast.main.contract;

import com.andychen.blast.bean.GrayInfo;
import com.yhbc.base.widget.BaseView;
import okhttp3.ResponseBody;

/**
 * Created by hjw on 2018/12/8 0008.
 * 邮箱：786567342@qq.com
 * 电话：13221002723
 */
public interface MainContract {

    interface View extends BaseView {

        void getGrayInfoCallback(GrayInfo grayInfo);

        void getDownLoadFileCallback(ResponseBody result);
    }

    interface Presenter {
        void getGrayInfomsg(View view, String clientName, String versionNum, String shopperId);

        void getUpdateInfo(View view, String url);

        void downloadFile(View view, String url);

        void makeRootDirectory(String filePath);
    }

}
