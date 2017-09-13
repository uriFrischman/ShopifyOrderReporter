package com.frischman.uri.shopifyorderreporter.util;


import android.content.Context;

public class StringUtil {

    private static Context mContext;

    public StringUtil(Context context) {
        mContext = context;
    }

    public static String getString(int id) {
        return mContext.getString(id);
    }
}
