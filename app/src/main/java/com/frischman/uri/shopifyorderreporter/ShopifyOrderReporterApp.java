package com.frischman.uri.shopifyorderreporter;

import android.app.Application;
import android.content.Context;


public class ShopifyOrderReporterApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppContext();
    }

    private void initAppContext() {
        ShopifyOrderReporterApp.mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return ShopifyOrderReporterApp.mContext;
    }
}
