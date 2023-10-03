package com.arun.ecommerceapp.services;

import com.arun.ecommerceapp.dtos.FakeStoreProductDto;
import com.arun.ecommerceapp.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String requestURL="https://fakestoreapi.com/products/{id}";
    private String createProductRequestURL = "https://fakestoreapi.com/products";
    private String updateProductRequestURL = "https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestURL = "https://fakestoreapi.com/products/{id}";

    private GenericProductDto responseConverter(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());
        return product;
    }

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(requestURL, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return responseConverter(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestURL,genericProductDto,GenericProductDto.class);
        return response.getBody();
    }
    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(genericProductDto, headers);
        return restTemplate.exchange(updateProductRequestURL, HttpMethod.PUT, entity, GenericProductDto.class,id).getBody();
    }
    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(deleteProductRequestURL, HttpMethod.DELETE, entity, GenericProductDto.class,id).getBody();
    }
}
