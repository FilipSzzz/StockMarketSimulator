package com.stockmarket.portfolio;
import com.stockmarket.exception.AssetNotFoundException;
import com.stockmarket.exception.InsufficientAssetsException;
import com.stockmarket.exception.InsufficientFundsException;
import com.stockmarket.market.Market;
import com.stockmarket.market.Tradable;
import com.stockmarket.model.Asset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Portfolio {
    private double cash;
    private Map<String, PortfolioPosition> positions;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.positions = new HashMap<>();
    }

    public void buy(String symbol, int quantity, Market market) throws InsufficientFundsException, AssetNotFoundException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilosc musi byc wieksza od 0");
        }
        Optional<Asset> optionalAsset = market.getAsset(symbol);
        if (optionalAsset.isEmpty()) {
            throw new AssetNotFoundException("Nie znaleziono aktywa o symbolu: " + symbol);
        }
        Asset asset = optionalAsset.get();
        if (!(asset instanceof Tradable)) {
            throw new AssetNotFoundException("Asset is not tradable: " + symbol);
        }
        double price = ((Tradable) asset).getCurrentPrice();
        double cost = quantity * price;
        if (cost > cash) {
            throw new InsufficientFundsException("Niewystarczajace srodki na zakup aktywa: " + symbol);
        }
        cash -= cost;
        addToPositions(asset, quantity);
    }

    public void sell(String symbol, int quantity, Market market) throws InsufficientAssetsException, AssetNotFoundException {
        if (!positions.containsKey(symbol)) {
            throw new AssetNotFoundException("Asset not found in portfolio: " + symbol);
        }
        PortfolioPosition position = positions.get(symbol);
        if (quantity <= 0 || quantity > position.quantity()) {
            throw new InsufficientAssetsException("Niewystarczajaca ilosc aktywow do sprzedazy: " + symbol);
        }
        Optional<Asset> optionalAsset = market.getAsset(symbol);
        if (optionalAsset.isEmpty()) {
            throw new AssetNotFoundException("Nie znaleziono aktywa na rynku: " + symbol);
        }
        Asset asset = optionalAsset.get();
        if (!(asset instanceof Tradable)) {
            throw new AssetNotFoundException("Asset is not tradable: " + symbol);
        }
        double price = ((Tradable) asset).getCurrentPrice();
        double proceeds = quantity * price;
        cash += proceeds;
        removeFromPositions(symbol, quantity);
    }

    private void addToPositions(Asset asset, int quantity) {
        String symbol = asset.getSymbol();
        if (positions.containsKey(symbol)) {
            PortfolioPosition existingPosition = positions.get(symbol);
            int updatedQuantity = existingPosition.quantity() + quantity;
            positions.put(symbol, new PortfolioPosition(asset, updatedQuantity));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }

    private void removeFromPositions(String symbol, int quantity) {
        PortfolioPosition position = positions.get(symbol);
        int remaining = position.quantity() - quantity;
        if (remaining > 0) {
            positions.put(symbol, new PortfolioPosition(position.asset(), remaining));
        } else {
            positions.remove(symbol);
        }
    }

    public void addAsset(Asset asset, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        String symbol = asset.getSymbol();
        if (positions.containsKey(symbol)) {
            PortfolioPosition existingPosition = positions.get(symbol);
            int updatedQuantity = existingPosition.quantity() + quantity;
            positions.put(symbol, new PortfolioPosition(asset, updatedQuantity));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }

    public double getCash() {
        return cash;
    }

    public Map<String, PortfolioPosition> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    public double calculateAssetsValue() {
        double totalValue = 0.0;
        for (PortfolioPosition position : positions.values()) {
            totalValue += position.quantity() * position.asset().getCurrentPrice(); // Używamy getCurrentPrice()
        }
        return totalValue;
    }

    public double calculateTotalValue() {
        return calculateAssetsValue() + getCash();
    }

    public double calculateStockValue() {
        return 0;
    }
}
