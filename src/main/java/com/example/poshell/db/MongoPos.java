package com.example.poshell.db;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoPos implements PosDB {
    private Cart cart;

    private final PosRepository posRepository;

    @Override
    public List<Product> getProducts() {
        var ret = new ArrayList<Product>();
        posRepository.findAll().forEach(ret::add);
        return ret;
    }

    @Override
    public Cart saveCart(Cart cart) {
        this.cart = cart;
        return this.cart;
    }

    @Override
    public Cart getCart() {
        return this.cart;
    }

    @Override
    public Product getProduct(String productId) {
        return posRepository.findById(productId).orElse(null);
    }

    @Autowired
    private MongoPos(PosRepository repo) {
        posRepository = repo;
    }
}
