package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.ShoppingCart;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.PostActivate;
import jakarta.ejb.PrePassivate;
import jakarta.ejb.Stateful;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
Stateful session beans can manage, hold states. Because there is one session bean for each client in the session pool.
For User/login sessions, cart behavior, temporary data storage. NOT THREAD SAFE, less scalable
*/
@Stateful
public class ShoppingCartBean implements ShoppingCart, Serializable {

    private List<String> items = new ArrayList<String>();

    @PrePassivate // Before stateful sessions beans passivated to
    public void passivate() {
        System.out.println("Passivating ShoppingCartBean");
    }

    @PostActivate // After passivated stateful session beans getting redeployed
    public void activate() {
        System.out.println("Activating ShoppingCartBean");
    }

    @PostConstruct
    public void init() {
        System.out.println("Init ShoppingCartBean");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying ShoppingCartBean");
    }

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
