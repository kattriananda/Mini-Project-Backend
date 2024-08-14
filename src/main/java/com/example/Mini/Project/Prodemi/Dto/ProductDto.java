package com.example.Mini.Project.Prodemi.Dto;

import com.example.Mini.Project.Prodemi.Entity.Category;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {
    @NotEmpty(message = "Product title is required")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9.\\s]+$", message = "Product title can only contain letters, numbers and space")
    @Size(max = 50)
    private String title;

 //   @Pattern(regexp = "^[0-9]+$", message = "Price must be a whole number")
    @Positive
    private Integer price;

    private String image;

    @NotNull(message = "Product title is required")
    private Category categoryId;

    public ProductDto(String title, Integer price, String image, Category categoryId) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.categoryId = categoryId;
    }
}
