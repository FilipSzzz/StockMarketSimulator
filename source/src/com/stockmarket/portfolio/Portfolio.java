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

public class Portfolio {

    private double cash;
    private Map<String, PortfolioPosition> positions;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.positions = new HashMap<>();
    }

    public Portfolio() {
        this.positions = new HashMap<>();
    }


    public void addAsset(Asset asset, int quantity) {
        String symbol = asset.getSymbol();

        if (positions.containsKey(symbol)) {
            PortfolioPosition existing = positions.get(symbol);
            positions.put(symbol, new PortfolioPosition(asset, existing.quantity() + quantity));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) { this.cash = cash; }
    public void setPositions(Map<String, PortfolioPosition> positions) { this.positions = positions; }


    public Map<String, PortfolioPosition> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    public double calculateAssetsValue() {
        double total = 0.0;
        for (PortfolioPosition pos : positions.values()) {
            total += pos.asset().getCurrentPrice() * pos.quantity();
        }
        return total;
    }

    public double calculateTotalValue() {
        return calculateAssetsValue() + getCash();
    }

    public void buy(String symbol, int quantity, Market market)
            throws InsufficientFundsException, AssetNotFoundException {
        Asset asset = market.getAsset(symbol)
                .orElseThrow(() -> new AssetNotFoundException("Nie znaleziono aktywa " + symbol));
        if (!(asset instanceof Tradable tAsset)) throw new AssetNotFoundException("Niehandlowalne: " + symbol);
        double price = tAsset.getCurrentPrice();
        double cost = price * quantity;
        if (cost > cash) throw new InsufficientFundsException("Za mało gotówki na zakup " + symbol);
        cash -= cost;
        positions.merge(symbol,
                new PortfolioPosition(asset, quantity),
                (oldPos, newPos) -> new PortfolioPosition(asset, oldPos.quantity() + quantity));
    }

    public void sell(String symbol, int quantity, Market market)
            throws InsufficientAssetsException, AssetNotFoundException {
        PortfolioPosition pos = positions.get(symbol);
        if (pos == null) throw new AssetNotFoundException("Nie masz aktywa: " + symbol);
        if (quantity > pos.quantity()) throw new InsufficientAssetsException("Za mało do sprzedaży: " + symbol);
        Asset asset = market.getAsset(symbol)
                .orElseThrow(() -> new AssetNotFoundException("Brak na rynku: " + symbol));
        if (!(asset instanceof Tradable tAsset)) throw new AssetNotFoundException("Niehandlowalne: " + symbol);
        double value = tAsset.getCurrentPrice() * quantity;
        cash += value;
        int left = pos.quantity() - quantity;
        if (left == 0) positions.remove(symbol);
        else positions.put(symbol, new PortfolioPosition(asset, left));
    }

}