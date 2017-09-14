package com.frischman.uri.shopifyorderreporter.util;


public class NameUtil {

    public static String generateFullName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }
}
