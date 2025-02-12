package com.example.crud.crud.repository;

import com.example.crud.crud.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}