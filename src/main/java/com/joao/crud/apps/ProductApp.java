package com.joao.crud.apps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.crud.repositories.ProductRepository;

@Service
public class ProductApp {
    
    @Autowired
    private ProductRepository productRepository;

    

}
