package com.frischman.uri.shopifyorderreporter.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.frischman.uri.shopifyorderreporter.R;

import java.util.Iterator;

import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.getJsonNodeFromRootNode;
import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.isNodeValid;
import static com.frischman.uri.shopifyorderreporter.util.NameUtil.generateFullName;
import static com.frischman.uri.shopifyorderreporter.util.OrderUtil.getOrderTotalPrice;

public class CustomerUtil {

    public static JsonNode getCustomerFromOrder(JsonNode order) {
        return getJsonNodeFromRootNode(order, StringUtil.getString(R.string.order_key_customer));
    }

    public static String getCustomerFullNameFromOrder(JsonNode orderCustomer) {
        String orderCustomerFirstName = getJsonNodeFromRootNode(orderCustomer, StringUtil.getString(R.string.order_key_first_name)).asText();
        String orderCustomerLastName = getJsonNodeFromRootNode(orderCustomer, StringUtil.getString(R.string.order_key_last_name)).asText();
        return generateFullName(orderCustomerFirstName, orderCustomerLastName);
    }

    public static double getAmountCustomerSpentFromOrders(Iterator<JsonNode> orders, String customerFullName) {
        double amount = 0.0;
        while (orders.hasNext()) {
            JsonNode order = orders.next();
            JsonNode orderCustomer = getCustomerFromOrder(order);
            if (isNodeValid(orderCustomer)) {
                String orderFullName = getCustomerFullNameFromOrder(orderCustomer);
                if (orderFullName.equals(customerFullName)) {
                    amount+= getOrderTotalPrice(order);
                }
            }
        }
        return amount;
    }
}
