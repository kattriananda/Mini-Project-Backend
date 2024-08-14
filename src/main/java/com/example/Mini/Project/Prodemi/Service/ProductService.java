package com.example.Mini.Project.Prodemi.Service;

import com.example.Mini.Project.Prodemi.Convert.ProductConverter;
import com.example.Mini.Project.Prodemi.Dto.ProductDto;
import com.example.Mini.Project.Prodemi.Entity.Category;
import com.example.Mini.Project.Prodemi.Entity.Product;
import com.example.Mini.Project.Prodemi.Exception.CategoryNotFoundException;
import com.example.Mini.Project.Prodemi.Exception.ProductNotFoundException;
import com.example.Mini.Project.Prodemi.Repository.CategoryRepository;
import com.example.Mini.Project.Prodemi.Repository.ProductRepository;
import com.example.Mini.Project.Prodemi.Repository.TransactionDetailRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionDetailRepo transactionDetailRepo;


    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, TransactionDetailRepo transactionDetailRepo) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.transactionDetailRepo= transactionDetailRepo;
    }

    public void addProduct (ProductDto productDto){
        Product product = ProductConverter.dtoToEntity(productDto);
        Category category = categoryRepository.findById(productDto.getCategoryId().getId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("");
        }
        product.setCategory(category);
        Product saveProduct = productRepository.save(product);
        ProductConverter.entityToDto(saveProduct);
    }
    public List<ProductDto> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductConverter::entityToDto).collect(Collectors.toList());
    }

    public void updateProduct (int id, ProductDto productDto){
        Product searchProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
        Product product = ProductConverter.dtoToEntity(productDto);
        product.setId(searchProduct.getId());
        product = productRepository.save(product);
        ProductConverter.entityToDto(product);
    }

    public void deleteProduct (int id){
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        boolean productInTransaction = transactionDetailRepo.existsByProductId(id);
        if(productInTransaction) {
            throw new IllegalStateException("sudah terjadi transaksi pada produk");
        }
        productRepository.deleteById(id);
    }

    public Product detailProduct(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not found"));
        // if (products == null) {
        //     throw new ProductNotFoundException("");
        // }
        // return productRepository.findAllById(Collections.singleton(id));
    }
    public List<Product> detailProduct() {
        return productRepository.findAll();
    }


    public List<Product> listProducts(Sort sort) {
        return productRepository.findAll(sort);
    }


    public List<Product> findProductByTitle(String title, String sort_by, String sort_order) {
        Sort.Direction sortOrder = sort_order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortOrder, sort_by);

        return productRepository.findByTitleContainingIgnoreCase(title, sort);
    }

    public List<Product> findProductByCategory(Integer category, String sort_by, String sort_order) {
        Sort.Direction sortOrder = sort_order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortOrder, sort_by);

        return productRepository.findByCategoryId(category, sort);
    }

}
