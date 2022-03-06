package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "Error adding product: " + productId;
    }

    @ShellMethod(value = "Remove a Product from Cart", key = "r")
    public String removeFromCart(String productId, @ShellOption(defaultValue = "1") int amount) {
        if (posService.remove(productId, amount)) {
            return posService.getCart().toString();
        }
        return "Error removing product: " + productId + "not found";
    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        return posService.checkout();
    }

    @ShellMethod(value = "Clear Cart", key = "cl")
    public String clearCart() {
        return posService.clear() ? "Cleared cart OK" : "Error clearing cart: no cart found";
    }
}
