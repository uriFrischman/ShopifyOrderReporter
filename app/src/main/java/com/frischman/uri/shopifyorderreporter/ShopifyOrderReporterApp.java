package com.frischman.uri.shopifyorderreporter;

import android.app.Application;

import com.frischman.uri.shopifyorderreporter.util.StringUtil;


public class ShopifyOrderReporterApp extends Application {

    private static StringUtil mStringUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppStringUtil();
    }

    private void initAppStringUtil() {
        mStringUtil = new StringUtil(this);
    }

    public static StringUtil getAppStringUtil() {
        return mStringUtil;
    }
}
