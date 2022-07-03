package com.vegitable.store.services;

import com.vegitable.store.dto.ProductDto;
import com.vegitable.store.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, long productId);

    List<ProductDto> getAllProduct();

    void deleteProductId(long productId);
}
