package com.example.crud.crud.controllers;

import com.example.crud.crud.domain.Product;
import com.example.crud.crud.domain.RequestProduct;
import com.example.crud.crud.service.ServiceProduct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/product")
public class ProductController {
    @Autowired
    private ServiceProduct serviceProduct;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = serviceProduct.pegaProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product newProduct = new Product(data);
        serviceProduct.salvaNovoProduto(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
        Optional<Product> optionalProduct = serviceProduct.pegaProdutsId(data.id());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        serviceProduct.deletaProdutsId(id);
        return ResponseEntity.noContent().build();
    }
}