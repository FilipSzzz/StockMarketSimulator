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
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol nie moze byc pusty");
        }
        Optional<Asset> optionalAsset = market.getAsset(symbol);
        if (optionalAsset.isEmpty()) {
            throw new AssetNotFoundException("Nie znaleziono aktywa o symbolu: " + symbol);
        }
        Asset asset = optionalAsset.get();
        if (!(asset instanceof Tradable)) {
            throw new AssetNotFoundException("Aktywem nie jest implementacja Tradeable: " + symbol);
        }
        double price = ((Tradable) asset).getCurrentPrice();
        double cost = quantity * price;
        if (cost > cash) {
            throw new InsufficientFundsException("Niewystarczajace srodki na zakup aktywa: " + symbol);
        }
        cash -= cost;
        addAsset(asset, quantity);
    }

    public void sell(String symbol, int quantity, Market market) throws InsufficientAssetsException, AssetNotFoundException {
        if (!positions.containsKey(symbol)) {
            throw new AssetNotFoundException("Nie znaleziono w portfolio : " + symbol);
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
        double cena = ((Tradable) asset).getCurrentPrice();
        double zysk = quantity * cena;
        cash += zysk;
        removeAsset(asset, quantity);
    }
    public void addAsset(Asset asset, int quantity) {
        String symbol = asset.getSymbol();
        if (positions.containsKey(symbol)) {
            PortfolioPosition existingPosition = positions.get(symbol);
            int updatedQuantity = existingPosition.quantity() + quantity;
            positions.put(symbol, new PortfolioPosition(asset, updatedQuantity));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }
    public void removeAsset(Asset asset, int quantity) throws AssetNotFoundException {
        String symbol = asset.getSymbol();
        if(positions.containsKey(symbol)) {
            PortfolioPosition existingPosition = positions.get(symbol);
            int updatedQuantity = existingPosition.quantity() - quantity;
            if (updatedQuantity <= 0) {
                positions.remove(symbol);
            } else {
                positions.put(symbol, new PortfolioPosition(asset, updatedQuantity));
            }
        } else {
            throw new AssetNotFoundException("Nie znaleziono aktywa w portfelu: " + symbol);
        }
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
        return cash + calculateAssetsValue();
    }
}
