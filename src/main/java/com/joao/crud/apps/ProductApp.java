package com.joao.crud.apps;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joao.crud.dtos.ProductRecordDto;
import com.joao.crud.models.ProductModel;
import com.joao.crud.repositories.ProductRepository;

@Service
public class ProductApp {
    
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDto product){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    } 


}
