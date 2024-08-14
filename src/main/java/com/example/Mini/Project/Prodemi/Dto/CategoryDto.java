package com.example.Mini.Project.Prodemi.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Product title can only contain letters, numbers and space")
    @Size(max = 50)
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(String name) {
        this.name = name;
    }

}
