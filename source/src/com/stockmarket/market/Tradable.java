package com.stockmarket.market;

public interface Tradable {
    String getSymbol();
    double getCurrentPrice();
}