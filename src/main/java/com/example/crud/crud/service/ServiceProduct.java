package com.example.crud.crud.service;

import com.example.crud.crud.domain.Product;
import com.example.crud.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduct {

    @Autowired
    private ProductRepository repository;

    public List<Product> pegaProducts() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado");
        } else {
            return products;
        }
    }

    public Product salvaNovoProduto(Product novoProduct){
        Product productSave = repository.save(novoProduct);

        if (productSave == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar produto.");
        }else {
            return productSave;
        }
    }

    public Optional<Product> pegaProdutsId(String data){
        Optional<Product> products = repository.findById(data);
        return products;
    }

    public Optional<Product> deletaProdutsId(String id){
        Optional<Product> produtsDelete = repository.findById(id);
        if (produtsDelete.isPresent()){
            repository.deleteById(id);
        }
        return produtsDelete;
    }
}
