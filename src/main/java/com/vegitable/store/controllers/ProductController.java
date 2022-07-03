package com.vegitable.store.controllers;

import com.vegitable.store.dto.ProductDto;
import com.vegitable.store.entities.Product;
import com.vegitable.store.services.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/")
    public String Home() {
        return "Hello Product";
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productServiceImpl.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductDto> updateUser(@Valid @RequestBody ProductDto productDto,
                                                 @PathVariable("productId") long productId) {

        return new ResponseEntity<>(productServiceImpl.updateProduct(productDto, productId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productServiceImpl.getAllProduct(), HttpStatus.OK);
    }


    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProductbyProductId(@PathVariable("productId") long productId) {
        productServiceImpl.deleteProductId(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
