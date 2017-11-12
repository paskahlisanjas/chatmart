package com.kahl.chatmart.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class Product {
    private Drawable image;
    private String name;
    private String description;

    public Product(Drawable image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
