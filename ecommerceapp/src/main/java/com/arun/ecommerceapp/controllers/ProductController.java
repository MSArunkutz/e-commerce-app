package com.arun.ecommerceapp.controllers;

import com.arun.ecommerceapp.dtos.GenericProductDto;
import com.arun.ecommerceapp.models.Product;
import com.arun.ecommerceapp.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
//    @Value("${productservice.type}")
//    private String productServiceType;
    private ProductService productService;
    // CONSTRUCTOR INJECTION
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id")Long id){
        return new ResponseEntity<GenericProductDto>(
                productService.deleteProduct(id), HttpStatus.ACCEPTED
        );
    }
    @PutMapping("{id}")
    public GenericProductDto updateProduct(@RequestBody GenericProductDto genericProductDto,@PathVariable("id")Long id){
        return productService.updateProduct(genericProductDto,id);
    }
}
