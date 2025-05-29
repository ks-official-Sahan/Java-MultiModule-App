package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.ShoppingCart;
import jakarta.ejb.Stateful;

import java.util.ArrayList;
import java.util.List;

/*
Stateful session beans can manage, hold states. Because there is one session bean for each client in the session pool.
For User/login sessions, cart behavior, temporary data storage. NOT THREAD SAFE, less scalable
*/
@Stateful
public class ShoppingCartBean implements ShoppingCart {

    private List<String> items = new ArrayList<String>();

    @Override
    public void addItem(String itemName) {
        items.add(itemName);
    }

    @Override
    public void removeItem(String itemName) {
        items.remove(itemName);
    }

    @Override
    public List<String> getItems() {
        return items;
    }

    int count;

    @Override
    public String getCartName() {
        count++;
        return "Cart : " + count;
    }
}
