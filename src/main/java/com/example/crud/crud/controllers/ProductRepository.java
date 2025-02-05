package com.example.crud.crud.controllers;

import com.example.crud.crud.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
