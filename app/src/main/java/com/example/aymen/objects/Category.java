package com.example.aymen.objects;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;
    private int imageResource;

    public Category(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
