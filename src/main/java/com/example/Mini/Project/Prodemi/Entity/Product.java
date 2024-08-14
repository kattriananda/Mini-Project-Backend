package com.example.Mini.Project.Prodemi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "title", length = 50)
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, Integer price, String image, Category category) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public Product() {

    }
}
