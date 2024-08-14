package com.example.Mini.Project.Prodemi.Convert;

import com.example.Mini.Project.Prodemi.Dto.ProductDto;
import com.example.Mini.Project.Prodemi.Entity.Product;

public class ProductConverter {
    public static Product dtoToEntity (ProductDto productDto){
        return new Product(
                productDto.getTitle(),
                productDto.getPrice(),
                productDto.getImage(),
                productDto.getCategoryId()
        );
    }

    public static ProductDto entityToDto (Product product){
        return new ProductDto(
                product.getTitle(),
                product.getPrice(),
                product.getImage(),
                product.getCategory()
        );
    }
}
