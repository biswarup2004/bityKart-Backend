package com.bity.bitykart.service;

import com.bity.bitykart.model.Product;
import com.bity.bitykart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    public Product findByProductId(Long productId) {
       return productRepository.findById(productId).orElse(null);

    }


    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
}
