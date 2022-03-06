package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public String checkout() {
        var cart = this.getCart();
        if (cart == null) return "No cart available";
        return cart.toString();
    }

    @Override
    public double total(Cart cart) {
        return cart.getItems().stream().mapToDouble(Item::getPrice).sum();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {
        if (amount < 0) {
            return false;
        }
        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        return this.getCart().addItem(new Item(product, amount));
    }

    @Override
    public boolean remove(String productId, int amount) {
        if (amount < 0) {
            return false;
        }
        var product = posDB.getProduct(productId);
        var cart = this.getCart();
        if (product == null || cart == null) return false;
        return cart.removeItem(productId, amount);
    }

    @Override
    public boolean clear() {
        var cart = this.getCart();
        if (cart == null) return false;
        cart.clear();
        return true;
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
