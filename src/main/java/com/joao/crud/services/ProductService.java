package com.joao.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joao.crud.controllers.ProductController;
import com.joao.crud.dtos.ProductRecordDto;
import com.joao.crud.models.ProductModel;
import com.joao.crud.repositories.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ProductModel> saveProduct(ProductRecordDto productRecordDto){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = productRepository.findAll();
        if (!productsList.isEmpty()) {
            for (ProductModel productModel : productsList) {
                UUID id = productModel.getIdProduct();
                productModel.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products list"));
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
