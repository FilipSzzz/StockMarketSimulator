package com.stockmarket.model;

import java.util.Objects;

public abstract class Asset {
    protected String symbol;
    protected String name;
    protected double currentPrice;

    public Asset(String symbol, String name, double currentPrice) { // konstruktor ktory rzuca IllegalArgumentException dla blednych danych
        if (symbol == null || symbol.isEmpty() || name == null || name.isEmpty() || currentPrice <= 0) {
            throw new IllegalArgumentException("Niepoprawne dane");

        }
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
    }
    public Asset(){
        this.symbol = "";
        this.name = "";
        this.currentPrice = 0.0;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public abstract void updatePrice(); //Oznacza, że każda podklasa musi zaimplementować tę metodę,
    // w której będzie określony sposób aktualizacji ceny (np. losowy dla akcji, procentowy dla obligacji).


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Asset asset)) return false;
        return Objects.equals(symbol, asset.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }
}
