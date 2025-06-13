package com.stockmarket.model;

import com.stockmarket.market.Tradable;

public class Bond extends Asset implements Tradable {
    private int interestRate;

    public Bond(String symbol, String name, double currentPrice, int interestRate) {
        super(symbol, name, currentPrice);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Stopa procentowa nie może być ujemna");
        }
        this.interestRate = interestRate;
    }
    public Bond() {
        super();
        this.interestRate = 0;
    }
    @Override
    public void updatePrice() {
        this.currentPrice += this.currentPrice * (interestRate / 100.0);
    }
    public String getSymbol() {
        return symbol;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
}
