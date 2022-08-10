package com.example.mongoexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PostMapping("/products/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProduct(products);
    }

    @GetMapping("/products/fetchAll")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/products/fetchById")
    public Product retrieveProductById(@RequestParam("id") int id){
        return productService.getProductById(id);
    }

    @GetMapping("/products/fetchByName{name}")
    public Product retrieveProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @DeleteMapping("/products/delete{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/products/update")
    public Product retrieveProductById(@RequestBody Product product){
        return productService.updateProduct(product);
    }

}
