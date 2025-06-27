package com.stockmarket.model;

import com.stockmarket.market.Tradable;

public class Stock extends Asset implements Tradable {
    private double initialPrice;

    public Stock(String symbol, String name, double initialPrice) {
        super(symbol, name, initialPrice);
        this.initialPrice = initialPrice;
    }

    public Stock() {
        super("", "", 0.0);
    }
    public String getName() {
        return name;
    }
    public double getInitialPrice() {
        return initialPrice;
    }
    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }
    @Override
    public void updatePrice() {
        currentPrice *= 1.0 + (Math.random() - 0.3) * 0.1;

        if (currentPrice < 0) {
            currentPrice = 0;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (Double.compare(stock.initialPrice, initialPrice) != 0) return false;
        if (symbol != null ? !symbol.equals(stock.symbol) : stock.symbol != null) return false;
        return name != null ? name.equals(stock.name) : stock.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(initialPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "currentPrice=" + String.format("%.2f", currentPrice) +
                ", initialPrice=" + String.format("%.2f", initialPrice) +
                '}';
    }

    @Override public String getSymbol() { return symbol; }
    @Override public double getCurrentPrice() { return currentPrice; }

}