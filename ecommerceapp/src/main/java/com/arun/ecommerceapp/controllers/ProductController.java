package com.arun.ecommerceapp.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {
    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Product with id "+id+" returned !! ";
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id")String id){

    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id")String id){

    }
}
