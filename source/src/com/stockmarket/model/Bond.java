package com.stockmarket.model;

public class Bond extends Asset {
    private int interestRate;

    public Bond(String symbol, String name, double currentPrice, int interestRate) {
        super(symbol, name, currentPrice);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Stopa procentowa nie może być ujemna");
        }
        this.interestRate = interestRate;
    }
    @Override
    public void updatePrice() {
        this.currentPrice += this.currentPrice * (interestRate / 100.0);
    }
}
