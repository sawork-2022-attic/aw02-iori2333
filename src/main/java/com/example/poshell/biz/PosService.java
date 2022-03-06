package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public String checkout();

    public double total(Cart cart);

    public boolean add(Product product, int amount);

    public boolean add(String productId, int amount);

    boolean remove(String productId, int amount);

    boolean clear();

    public List<Product> products();
}
