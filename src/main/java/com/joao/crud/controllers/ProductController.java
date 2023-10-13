package com.joao.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joao.crud.apps.ProductApp;
import com.joao.crud.dtos.ProductRecordDto;
import com.joao.crud.models.ProductModel;

import jakarta.validation.Valid;

@RestController
public class ProductController {
 
    @Autowired
    private ProductApp productApp;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto product){
        return productApp.saveProduct(product);
    }
    
}
