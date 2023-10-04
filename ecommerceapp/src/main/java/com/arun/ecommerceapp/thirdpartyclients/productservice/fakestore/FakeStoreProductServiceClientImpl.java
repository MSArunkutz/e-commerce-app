package com.arun.ecommerceapp.thirdpartyclients.productservice.fakestore;

import com.arun.ecommerceapp.dtos.GenericProductDto;
import com.arun.ecommerceapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This is the wrapper class for all the implementations related to fake store apis
 * */

@Service
public class FakeStoreProductServiceClientImpl {
    private RestTemplateBuilder restTemplateBuilder;
    private String fakeStoreApiUrl;
    private String productUrl;
    private String baseUrl;
    private String getProductByIdRequestURL;
    private String createProductRequestURL;
    private String updateProductRequestURL;
    private String deleteProductRequestURL;
    private String getAllProductsRequestURL;


    public FakeStoreProductServiceClientImpl(RestTemplateBuilder restTemplateBuilder,
                                             @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                             @Value("${fakestore.api.path.product}") String productUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiUrl = fakeStoreApiUrl;
        this.productUrl = productUrl;
        this.baseUrl = this.fakeStoreApiUrl + this.productUrl;
        this.getProductByIdRequestURL = baseUrl + "/{id}";
        this.createProductRequestURL = baseUrl;
        this.updateProductRequestURL = baseUrl + "/{id}";
        this.deleteProductRequestURL = baseUrl + "/{id}";
        this.getAllProductsRequestURL = baseUrl;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductByIdRequestURL, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id : " + id + " doesn't exist");
        }
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequestURL, genericProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(genericProductDto, headers);
        return restTemplate.exchange(updateProductRequestURL, HttpMethod.PUT, entity, FakeStoreProductDto.class, id).getBody();
    }

    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<GenericProductDto> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(deleteProductRequestURL, HttpMethod.DELETE, entity, FakeStoreProductDto.class, id).getBody();
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(getAllProductsRequestURL, FakeStoreProductDto[].class);
        List<FakeStoreProductDto> ans = new ArrayList<>(Arrays.stream(response.getBody()).toList());
        return ans;
    }
}
