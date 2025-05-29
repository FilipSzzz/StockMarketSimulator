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
        market.addAsset(new Stock("CDP", "CD PROJECT", 2700.0));
        market.addAsset(new Bond("ABC", "ABC", 100.0,2));

        Portfolio portfolio = new Portfolio(10000.0);

        try {
            portfolio.buy("AAPL", 10, market);
            System.out.println("Kupiono 10 akcji AAPL");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy zakupie AAPL: " + e.getMessage());
        }

        try {
            portfolio.buy("GOOGL", 2, market);
            System.out.println("Kupiono 2 akcje GOOGL");
        } catch (InsufficientFundsException | AssetNotFoundException e) {
            System.out.println("Błąd przy zakupie GOOGL: " + e.getMessage());
        }

        try {
            portfolio.sell("AAPL", 5, market);
            System.out.println("Sprzedano 5 akcji AAPL");
        } catch (InsufficientAssetsException | AssetNotFoundException e) {
            System.out.println("Błąd przy sprzedaży AAPL: " + e.getMessage());
        }

        // Symulacja zmian cen i obserwacja wartości portfela
        for (int i = 0; i < 5; i++) {
            market.updatePrices();
            double totalValue = portfolio.calculateTotalValue();
            System.out.printf("Po aktualizacji %d: Wartość portfela: %.2f zł\n", i + 1, totalValue);
        }
    }
}
