package com.frischman.uri.shopifyorderreporter.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.frischman.uri.shopifyorderreporter.R;

import java.util.Iterator;

import static com.frischman.uri.shopifyorderreporter.util.JsonUtil.getJsonNodeFromRootNode;
import static com.frischman.uri.shopifyorderreporter.util.OrderUtil.getAllOrderLineItems;

public class LineItemUtil {

    public static String getLineItemTitle(JsonNode lineItem) {
        return getJsonNodeFromRootNode(lineItem, StringUtil.getString(R.string.line_item_key_title)).asText();
    }

    public static int getLineItemQuantity(JsonNode lineItem) {
        return getJsonNodeFromRootNode(lineItem, StringUtil.getString(R.string.line_item_key_quantity)).asInt();
    }

   public static int getTotalLineItemSoldFromOrders(Iterator<JsonNode> orders, String item) {
       int quantity = 0;
       while (orders.hasNext()) {
           JsonNode order = orders.next();
           Iterator<JsonNode> lineItems = getAllOrderLineItems(order);
           while (lineItems.hasNext()) {
               JsonNode lineItem = lineItems.next();
               if (getLineItemTitle(lineItem).equals(item)) {
                   quantity += getLineItemQuantity(lineItem);
               }
           }
       }
       return quantity;
   }
}
