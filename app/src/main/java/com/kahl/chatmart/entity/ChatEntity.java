package com.kahl.chatmart.entity;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class ChatEntity {
    public static final int DEFAULT_IN = -1;
    public static final int DEFAULT_OUT = 0;
    public static final int LIST_OF_PRODUCT = 1;
    public static final int CART = 2;
    public static final int PAYMENT_METHOD = 3;
    public static final int LIST_OF_CATEGORY = 4;
    public static final int LIST_OF_SHIPMENT = 5;
    public static final int PRODUCT_SHIPMENT_STATUS = 6;

    private int type;

    private String message;
    private List<Product> productList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    public ChatEntity(int chatCategory,
                      @Nullable String contentText,
                      @Nullable List<Product> products,
                      @Nullable List<Category> categories) {
        switch (chatCategory) {
            case DEFAULT_IN:
                message = contentText;
                type = DEFAULT_IN;
                break;
            case DEFAULT_OUT:
                message = contentText;
                type = DEFAULT_OUT;
                break;
            case LIST_OF_PRODUCT:
                type = LIST_OF_PRODUCT;
                break;
            case CART:
                type = CART;
                break;
            case PAYMENT_METHOD:
                type = PAYMENT_METHOD;
                break;
            case LIST_OF_CATEGORY:
                type = LIST_OF_CATEGORY;
                categoryList.addAll(categories);
                break;
            case LIST_OF_SHIPMENT:
                type = LIST_OF_SHIPMENT;
                productList.addAll(products);
                break;
            case PRODUCT_SHIPMENT_STATUS:
                type = PRODUCT_SHIPMENT_STATUS;
                break;
        }
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
