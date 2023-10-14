package com.joao.crud.apps;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joao.crud.dtos.ProductRecordDto;
import com.joao.crud.models.ProductModel;
import com.joao.crud.repositories.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductApp {
    
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDto productRecordDto){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    public ResponseEntity<Object> updateProduct(UUID id, @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        ProductModel newProduct = product.get();
        BeanUtils.copyProperties(productRecordDto, newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(newProduct));
    }

    public ResponseEntity<Object> deleteProduct(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    } 


}
