package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        boolean flag = true;
        for (var i : items) {
            if (i.getProduct().getId().equals(item.getProduct().getId())) {
                i.setAmount(i.getAmount() + item.getAmount());
                flag = false;
            }
        }
        if (flag) {
            items.add(item);
        }
        return true;
    }

    public void clear() {
        items.clear();
    }

    public boolean removeItem(String productId, int amount) {
        for (var item : items) {
            if (item.getProduct().getId().equals(productId)) {
                if (item.getAmount() - amount < 0) {
                    return items.remove(item);
                } else {
                    item.setAmount(item.getAmount() - amount);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (items.size() == 0) {
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n");

        for (Item item : items) {
            stringBuilder.append(item.toString()).append("\n");
            total += item.getAmount() * item.getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n");

        stringBuilder.append("Total...\t\t\t").append(total);

        return stringBuilder.toString();
    }
}
