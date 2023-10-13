package com.joao.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.joao.crud.apps.ProductApp;

@RestController
public class ProductController {
 
    @Autowired
    private ProductApp productApp;

    
    
}
