package com.sahansachintha.ee.remote;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ShoppingCart {
    void addItem(String itemName);
    void removeItem(String itemName);
    List<String> getItems();
    String getCartName();
}
