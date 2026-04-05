package com.royalfashionmart.entity;

import jakarta.persistence.*;

@Entity
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
private double price;
private String img;
private String category;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public double getPrice() { return price; }
public void setPrice(double price) { this.price = price; }

public String getImg() { return img; }
public void setImg(String img) { this.img = img; }

public String getCategory() { return category; }
public void setCategory(String category) { this.category = category; }
}