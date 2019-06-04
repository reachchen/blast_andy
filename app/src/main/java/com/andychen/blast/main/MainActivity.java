package com.andychen.blast.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andychen.blast.R;
import com.andychen.blast.bean.GrayInfo;
import com.andychen.blast.main.contract.MainContract;
import com.yhbc.base.BaseActivity;

import okhttp3.ResponseBody;

/**
*@author AndyChen
*@time 2019/6/4 14:44
*/
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }


    @Override
    public void getGrayInfoCallback(GrayInfo grayInfo) {

    }

    @Override
    public void getDownLoadFileCallback(ResponseBody result) {

    }


    @Override
    public void onFailed(String msg) {

    }
}
