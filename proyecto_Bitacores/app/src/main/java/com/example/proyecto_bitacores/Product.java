package com.example.proyecto_bitacores;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int imageResourcePath; // ID de recurso de la imagen (puedes cambiar el tipo según tus necesidades)
    private double price;

    public Product(String name, int imageResourcePath, double price) {
        this.name = name;
        this.imageResourcePath = imageResourcePath;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourcePath;
    }


    public double getPrice() {
        return price;
    }
}
