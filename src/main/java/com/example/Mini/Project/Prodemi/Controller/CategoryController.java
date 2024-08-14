package com.example.Mini.Project.Prodemi.Controller;

import com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto;
import com.example.Mini.Project.Prodemi.Dto.CategoryDto;
import com.example.Mini.Project.Prodemi.Validation.ResponseData;
import com.example.Mini.Project.Prodemi.Entity.Category;
import com.example.Mini.Project.Prodemi.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pos/api")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addcategory")
    public ResponseEntity<ResponseData<CategoryDto>> addCategory(@Valid @RequestBody CategoryDto categoryDto,
            Errors errors) {
        ResponseData<CategoryDto> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus("failed");
            // responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        categoryService.addCategory(categoryDto);
        responseData.setStatus("ok");
        responseData.setMessages(Collections.singletonList("success"));
        // responseData.setPayload(categoryService.addCategory(categoryDto));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/detailCategory/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable String id) {
        int categoryId;
        try {
            categoryId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            ResponseData<Optional<Category>> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa angka"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        if (categoryId <= 0) {
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa bilangan positif"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        List<Category> category = categoryService.detailCategory(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> category = categoryService.getAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDetailDto>> getAllCategoriesProductCount() {
        List<CategoryDetailDto> category = categoryService.getAllCategory();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getDetailById(@PathVariable String id) {
        int categoryId;
        try {
            categoryId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            ResponseData<Optional<CategoryDetailDto>> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa angka"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        if (categoryId <= 0) {
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa bilangan positif"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        List<CategoryDetailDto> category = categoryService.getDetailById(categoryId);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<ResponseData<String>> deleteCategory(@PathVariable(required = false) String id) {
        int categoryId;
        try {
            categoryId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa angka"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        if (categoryId <= 0) {
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Id harus berupa bilangan positif"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        } else if (id == null || id.isEmpty()) {
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus("failed");
            responseData.setMessages(Collections.singletonList("Masukkan Id yang akan di hapus"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }
        categoryService.deleteCategory(categoryId);
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus("ok");
        responseData.setMessages(Collections.singletonList("success"));
        return ResponseEntity.ok(responseData);
    }
}
