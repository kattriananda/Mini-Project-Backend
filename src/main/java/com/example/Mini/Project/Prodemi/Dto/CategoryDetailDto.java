package com.example.Mini.Project.Prodemi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailDto {
    private int id;
    private String name;
    private long productCount;

    public CategoryDetailDto(int id, String name,long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

}
