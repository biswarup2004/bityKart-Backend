package com.bity.bitykart.controller;

import com.bity.bitykart.model.Product;
import com.bity.bitykart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;
@GetMapping
public List<Product> getAllProduct(){
    return productService.getAllProduct();
}
    @GetMapping("/{productId}")
public Product findByProductId(@PathVariable Long productId){
    return productService.findByProductId(productId);
}
@PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping("/bulk")
    public List<Product> addAllProducts(@RequestBody List<Product> products) {
        return productService.addAllProducts(products);
    }


@DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId){
    productService.deleteProductById(productId);
    }
}
