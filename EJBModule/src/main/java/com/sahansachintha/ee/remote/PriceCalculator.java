package com.sahansachintha.ee.remote;

import jakarta.ejb.Remote;

@Remote
public interface PriceCalculator {
    double getPrice();
    double getQuantity();
    double getTotalPrice();
    double calculateTotalPrice(double quantity, double price);
    String calculateTotalPriceString(double quantity, double price);
}
