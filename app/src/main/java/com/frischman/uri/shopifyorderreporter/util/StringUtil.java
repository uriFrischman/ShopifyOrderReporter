package com.frischman.uri.shopifyorderreporter.util;


import static com.frischman.uri.shopifyorderreporter.ShopifyOrderReporterApp.getAppContext;

public class StringUtil {

    public static String getString(int id) {
        return getAppContext().getString(id);
    }
}
