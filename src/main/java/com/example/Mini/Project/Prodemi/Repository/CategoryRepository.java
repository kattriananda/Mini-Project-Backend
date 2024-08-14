package com.example.Mini.Project.Prodemi.Repository;

import com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto;
import com.example.Mini.Project.Prodemi.Entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT new com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto(c.id, c.name, COUNT(p)) " +
           "FROM Category c LEFT JOIN Product p ON c.id = p.category.id " +
           "GROUP BY c.id, c.name")
    List<CategoryDetailDto> findAllCategoriesProductCOunt();
 
    // @Query("SELECT c, COUNT(p) AS productCount FROM Category c LEFT JOIN c.products p WHERE c.id = :categoryId GROUP BY c")
    // Object[] findCategoryDetailDtoById (@Param("categoryId") int categoryId);

      @Query("SELECT new com.example.Mini.Project.Prodemi.Dto.CategoryDetailDto(c.id, c.name, COUNT(p)) " +
           "FROM Category c LEFT JOIN Product p ON c.id = p.category.id " +
           "WHERE c.id = :categoryId " +
           "GROUP BY c.id, c.name")
    List<CategoryDetailDto> findCategoryDetailDtoById (@Param("categoryId") int categoryId);
}
