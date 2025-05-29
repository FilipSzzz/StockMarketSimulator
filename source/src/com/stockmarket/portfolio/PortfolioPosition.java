package com.stockmarket.portfolio;
import com.stockmarket.model.Asset;

public record PortfolioPosition(Asset asset, int quantity) { // record -> definiowanie niezmiennych obiektow,
    // generuje konstruktory, metody equals, hashCode i toString
    public PortfolioPosition{
        if (asset == null || quantity <= 0) {
            throw new IllegalArgumentException("Niepoprawne dane");
        }
    }
}
