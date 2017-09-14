package com.frischman.uri.shopifyorderreporter.util;


import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHTTPUtil {

    private static final String TAG = "OkHTTPUtil";

    public static Request generateGetRequest(String url) {
        return new Request.Builder().url(url).build();
    }

    public static String getStringResponseFromRequest(Request request) {
        try {
            return new OkHttpClient().newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Error with call execution");
            return null;
        }
    }
}
