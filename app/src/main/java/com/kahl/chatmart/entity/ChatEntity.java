package com.kahl.chatmart.entity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kahl.chatmart.ChatInFragment;
import com.kahl.chatmart.ChatOutFragment;
import com.kahl.chatmart.R;

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

    public ChatEntity(int chatCategory, @Nullable String contentText) {
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
                break;
            case LIST_OF_SHIPMENT:
                type = LIST_OF_SHIPMENT;
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
}
