package com.kahl.chatmart.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class Category {
    private String name;
    private Drawable illustration;
    private String description;

    public Category(String name, Drawable illustration, String description) {
        this.name = name;
        this.illustration = illustration;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Drawable getIllustration() {
        return illustration;
    }

    public String getDescription() {
        return description;
    }
}
