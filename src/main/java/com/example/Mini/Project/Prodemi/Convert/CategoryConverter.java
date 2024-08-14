package com.example.Mini.Project.Prodemi.Convert;

import com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto;
import com.example.Mini.Project.Prodemi.Dto.CategoryDto;
import com.example.Mini.Project.Prodemi.Entity.Category;

public class CategoryConverter {
    public static CategoryDto entityToDto (Category category){
        return new CategoryDto(category.getName());
    }

    public static Category dtoToEntity (CategoryDto categoryDto){
        return new Category(categoryDto.getName());
    }

    public static CategoryDetailDto entityToDetailDto (Category category, Long productCount){
        if (category == null) {
            System.out.println("Category is null"); 
            return null; 
        }
        
        System.out.printf("Converting Category(id=%d, name=%s) with productCount=%d to CategoryDetailDto%n",
        category.getId(), category.getName(), productCount); 
        
        CategoryDetailDto dto = new CategoryDetailDto(category.getId(), category.getName(), productCount);
        System.out.printf("Created CategoryDetailDto(id=%d, name=%s, productCount=%d)%n",
                dto.getId(), dto.getName(), dto.getProductCount()); 
        
        return dto;
    }
}
