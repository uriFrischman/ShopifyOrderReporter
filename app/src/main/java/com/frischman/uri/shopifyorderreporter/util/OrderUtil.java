package com.frischman.uri.shopifyorderreporter.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.frischman.uri.shopifyorderreporter.R;

import java.util.Iterator;

import okhttp3.Request;

import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.generateJsonRootNodeFromJsonString;
import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.getJsonNodeFromRootNode;
import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.getJsonNodeIterator;
import static com.frischman.uri.shopifyorderreporter.util.OkHTTPUtil.generateGetRequest;
import static com.frischman.uri.shopifyorderreporter.util.OkHTTPUtil.getStringResponseFromRequest;

public class OrderUtil {

    public static Iterator<JsonNode> getAllOrdersFromRequest(String url) {
        Request request = generateGetRequest(url);
        String source = getStringResponseFromRequest(request);
        JsonNode rootNode = generateJsonRootNodeFromJsonString(source);
        JsonNode orderNodes = getJsonNodeFromRootNode(rootNode, StringUtil.getString(R.string.list_of_orders_key_orders));
        return getJsonNodeIterator(orderNodes);
    }

    public static Iterator<JsonNode> getAllOrderLineItems(JsonNode order) {
        JsonNode lineOrders = getJsonNodeFromRootNode(order, StringUtil.getString(R.string.order_key_line_items));
        return getJsonNodeIterator(lineOrders);
    }

    public static double getOrderTotalPrice(JsonNode order) {
        JsonNode totalPrice = getJsonNodeFromRootNode(order, StringUtil.getString(R.string.order_key_total_price));
        return totalPrice != null ? totalPrice.asDouble() : 0;
    }
}
