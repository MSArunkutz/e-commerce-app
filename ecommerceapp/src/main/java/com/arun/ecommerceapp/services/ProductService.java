package com.arun.ecommerceapp.services;

import com.arun.ecommerceapp.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id);

    GenericProductDto deleteProduct(Long id);
    List<GenericProductDto> getAllProducts();

}
