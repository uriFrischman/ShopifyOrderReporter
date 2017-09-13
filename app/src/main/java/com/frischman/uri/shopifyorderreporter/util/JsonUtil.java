package com.frischman.uri.shopifyorderreporter.util;


import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

public class JsonUtil {

    private static final String TAG = "JsonUtil";

    public static boolean isNodeValid(JsonNode node) {
        if (!node.isMissingNode() && !node.isNull()) {
            return true;
        }
        return false;
    }

    public static JsonNode generateJsonRootNodeFromJsonString(String jsonString) {
        try {
            return new ObjectMapper().readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Error in Json Parsing");
            return null;
        }
    }

    public static JsonNode getJsonNodeFromRootNode(JsonNode rootNode, String key) {
        if (isNodeValid(rootNode)) {
            return rootNode.path(key);
        }
        return null;

    }

    public static Iterator<JsonNode> getJsonNodeIterator(JsonNode node) {
        if (isNodeValid(node)) {
            return node.elements();
        }
        return null;
    }
}
