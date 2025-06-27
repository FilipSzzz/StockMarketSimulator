package com.stockmarket.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

// umozliwia serializacje do json
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Stock.class, name = "STOCK"),
        @JsonSubTypes.Type(value = Bond.class, name = "BOND")
})
public abstract class Asset {
    protected String symbol;
    protected String name;
    protected double currentPrice;

    public Asset(String symbol, String name, double currentPrice) throws IllegalArgumentException {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;

        if (currentPrice < 0) {
            throw new IllegalArgumentException("Cena akcji nie może być ujemna: " + currentPrice);
        }
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
    public abstract void updatePrice();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;
        return Objects.equals(symbol, asset.symbol);
    }
    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }


}