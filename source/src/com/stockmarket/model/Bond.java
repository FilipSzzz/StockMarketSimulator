package com.stockmarket.model;

import com.stockmarket.market.Tradable;
import com.stockmarket.model.Asset;

public class Bond extends Asset implements Tradable {

    private double interestRate;

    public Bond(String symbol, String name, double currentPrice, double interestRate) {
        super(symbol, name, currentPrice);
        this.interestRate = interestRate;
        if (interestRate <= 0) {
            throw new IllegalArgumentException("Stopa procentowa musi być dodatnia: " + interestRate);
        }
    }

    public Bond() {
        super("", "", 0);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Stopa procentowa musi być dodatnia: " + interestRate);
        }
    }

    @Override
    public void updatePrice() {
        currentPrice *= (1 + interestRate);
        if (currentPrice < 0) {
            currentPrice = 0;
        }
    }

    @Override
    public String toString() {
        return "Bond{" +
                "currentPrice=" + String.format("%.2f", currentPrice) +
                ", interestRate=" +  interestRate +
                '}';
    }

    @Override public String getSymbol() { return symbol; }
    @Override public double getCurrentPrice() { return currentPrice; }
}