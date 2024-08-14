package com.example.Mini.Project.Prodemi.Service;

import com.example.Mini.Project.Prodemi.Convert.CategoryConverter;
import com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto;
import com.example.Mini.Project.Prodemi.Dto.CategoryDto;
import com.example.Mini.Project.Prodemi.Entity.Category;
import com.example.Mini.Project.Prodemi.Exception.CategoryNotFoundException;
import com.example.Mini.Project.Prodemi.Repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory (CategoryDto categoryDto){
        Category category = CategoryConverter.dtoToEntity(categoryDto);
        Category saveCategory = categoryRepository.save(category);
        CategoryConverter.entityToDto(saveCategory);
    }

    public void updateCategory (int id, CategoryDto categoryDto){
        Category searchCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category NOt Found"));
        Category category = CategoryConverter.dtoToEntity(categoryDto);
        category.setId(searchCategory.getId());
        category = categoryRepository.save(category);
        CategoryConverter.entityToDto(category);
    }

    public void deleteCategory (int id) {
        categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category Not Found"));
        categoryRepository.deleteById(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> detailCategory (int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("");
        }
        return categoryRepository.findAllById(Collections.singleton(id));
    }

    public List<CategoryDetailDto> getAllCategory () {
        return categoryRepository.findAllCategoriesProductCOunt();
    }

    public List <CategoryDetailDto> getDetailById(int categoryId) {
        List<CategoryDetailDto> category = categoryRepository.findCategoryDetailDtoById(categoryId);
        return category;
    }
    
}
