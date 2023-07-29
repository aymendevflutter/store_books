package com.example.aymen.objects;

public class Book {
    private String title;
    private String author;
    private String category;
    private double price;
    private String desc;

    public int getResourcimage() {
        return resourcimage;
    }

    private int resourcimage;

    public String getDesc() {
        return desc;
    }

    public Book(String title, String author, String category, double price, int resourcimage, String desc) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.resourcimage =resourcimage;
        this.desc =desc;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
