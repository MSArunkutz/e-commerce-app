package com.arun.ecommerceapp.services;

import com.arun.ecommerceapp.dtos.GenericProductDto;
import com.arun.ecommerceapp.exceptions.NotFoundException;
import com.arun.ecommerceapp.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.arun.ecommerceapp.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClientImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductServiceClientImpl fakeStoreProductServiceClientImpl;
    public FakeStoreProductService(FakeStoreProductServiceClientImpl fakeStoreProductServiceClientImpl){
        this.fakeStoreProductServiceClientImpl=fakeStoreProductServiceClientImpl;
    }

    private GenericProductDto responseConverter(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());
        return product;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return responseConverter(fakeStoreProductServiceClientImpl.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return responseConverter(fakeStoreProductServiceClientImpl.createProduct(genericProductDto));
    }
    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        return responseConverter(fakeStoreProductServiceClientImpl.updateProduct(genericProductDto,id));
    }
    @Override
    public GenericProductDto deleteProduct(Long id) {
        return responseConverter(fakeStoreProductServiceClientImpl.deleteProduct(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClientImpl.getAllProducts();
        List<GenericProductDto> res = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            res.add(responseConverter(fakeStoreProductDto));
        }
        return res;
    }
}
