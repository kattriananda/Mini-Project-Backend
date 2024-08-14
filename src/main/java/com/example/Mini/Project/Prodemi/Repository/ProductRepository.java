package com.example.Mini.Project.Prodemi.Repository;

import com.example.Mini.Project.Prodemi.Entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainingIgnoreCase(String title, Sort sort);

    List<Product> findByCategoryId(Integer category, Sort sort);
}
