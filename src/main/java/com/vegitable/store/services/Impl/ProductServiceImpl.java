package com.vegitable.store.services.Impl;

import com.vegitable.store.dto.ProductDto;
import com.vegitable.store.entities.Category;
import com.vegitable.store.entities.Product;
import com.vegitable.store.exception.CustomException;
import com.vegitable.store.mapper.MapStructMapper;
import com.vegitable.store.repositories.CategoryRepository;
import com.vegitable.store.repositories.ProductRepository;
import com.vegitable.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private MapStructMapper mapStructMapper;


    @Override
    public Product createProduct(ProductDto productDto) {

//        Category category = new Category();
//        category.setCategory_Name("a");
//        category.setProductList(productDto.getCategory().getProductList());
//        productDto.setCategory(category);
        Product product = mapStructMapper.productDtotoProduct(productDto);
        //product.setCategoryList(List.of(product.getCategoryList().toArray(new Category[0])));
        Product productResponse = productRepository.save(product);

        return productResponse;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product Id is Not found"));
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new CustomException("Category is not found"));
        category.setCategory_Name(productDto.getCategory().getCategory_Name());
        product.setCategory(category);
        product.setImageURL(productDto.getImageURL());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        Product productResponse = productRepository.save(product);
        return mapStructMapper.producttoProductDto(productResponse);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productList.stream()
                .map(product -> mapStructMapper.producttoProductDto(product))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public void deleteProductId(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product Id is Not found"));
        productRepository.delete(product);
    }
}
