package com.sahansachintha.ee.impl;

import com.sahansachintha.ee.remote.PriceCalculator;
import jakarta.ejb.Stateless;

/*
Stateless session cannot manage any states, because container can reuse any beans from the pool randomly (Shared accross clients).
No State, For Reusable, fast operations (eg: calculations, utility services, business logic, backend APIs) - Highly scalable
*/
@Stateless
public class PriceCalculatorBean implements PriceCalculator {
    int count;

    @Override
    public String calculateTotalPriceString(double quantity, double price) {
        count++;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.format("%.2f LKR", price*quantity) + " : " + count;
    }

    @Override
    public double getPrice() {
        return 120;
    }

    @Override
    public double getQuantity() {
        return 12;
    }

    @Override
    public double getTotalPrice() {
        return 120 * 12;
    }

    @Override
    public double calculateTotalPrice(double quantity, double price) {
        return quantity * price;
    }
}
