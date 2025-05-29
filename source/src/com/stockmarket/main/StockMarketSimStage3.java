package com.stockmarket.main;

import com.stockmarket.market.Market;
import com.stockmarket.model.Stock;
import com.stockmarket.model.Bond;
import com.stockmarket.portfolio.Portfolio;
import com.stockmarket.exception.AssetNotFoundException;
import com.stockmarket.exception.InsufficientAssetsException;
import com.stockmarket.exception.InsufficientFundsException;

public class StockMarketSimStage3 {
    public static void main(String[] args) {
        Market market = new Market();
        market.addAsset(new Stock("NVI", "Nvidia", 180.0));
        market.addAsset(new Stock("CDP", "CD PROJECT", 250));
        market.addAsset(new Stock("APL", "Apple", 300));
        market.addAsset(new Bond("EDO", "EDO", 100.0,5));

        Portfolio portfolio = new Portfolio(30000);

        try {
            portfolio.buy("NVI", 10, market);
            System.out.println("Kupiono 10 akcji Nvidia");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy zakupie NVI: " + e.getMessage());
        }
        try{
            portfolio.buy("APL",10,market);
            System.out.println("Kupiono 10 akcji Apple");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy zakupie APL: " + e.getMessage());
        }

        try {
            portfolio.buy("CDP", 20, market);
            System.out.println("Kupiono 20 akcji Cd Project");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy zakupie CDP: " + e.getMessage());
        }

        try {
            portfolio.sell("NVI", 5, market);
            System.out.println("Sprzedano 5 akcji Nvidia");
        } catch (InsufficientAssetsException | AssetNotFoundException e) {
            System.out.println("Błąd przy sprzedaży NVI: " + e.getMessage());
        }
        try {
            portfolio.buy("EDO", 10, market);
            System.out.println("Kupiono 10 obligacji EDO");
        } catch ( InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy sprzedaży EDO: " + e.getMessage());
        }
        try{
            portfolio.sell("APL", 3, market);
            System.out.println("Sprzedano 3 akcje Apple");
        } catch (InsufficientAssetsException | AssetNotFoundException e) {
            System.out.println("Błąd przy sprzedaży APL: " + e.getMessage());
        }
        try {
            portfolio.sell("CDP", 5, market);
            System.out.println("Sprzedano 5 akcji Cd Project");
        } catch (InsufficientAssetsException | AssetNotFoundException e) {
            System.out.println("Błąd przy sprzedaży CDP: " + e.getMessage());
        }
        for (int i = 0; i < 10; i++) {
            market.updatePrices();
            double totalValue = portfolio.calculateTotalValue();
            System.out.printf("Po aktualizacji %d: Wartość portfela: %.2f zł\n", i + 1, totalValue);
        }
    }
}
