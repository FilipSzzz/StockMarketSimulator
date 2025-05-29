package com.stockmarket.model;

import com.stockmarket.portfolio.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class StockMarketSimStage2 { // ZAAOKRAGLI WARTOSCI WYSWIETLANE DO 2 MIEJSC PO PRZECINKU
    public static void main(String[] args) {
        List<Asset> marketAssets = new ArrayList<>();
        marketAssets.add(new Stock("XTB", "XTB", 90));
        marketAssets.add(new Stock("CDP", "CD Project", 150));
        marketAssets.add(new Bond("EDO", "Take-Two", 100, 6));
        Portfolio portfolio = new Portfolio(5000);
        portfolio.addAsset(marketAssets.get(0), 10);
        portfolio.addAsset(marketAssets.get(1), 50); // 50 akcji
        portfolio.addAsset(marketAssets.get(2), 500); // 500 obligacji

        for (int i = 1; i <= 10; i++) {
            for(Asset asset: marketAssets){
                asset.updatePrice();
            }
            double portfolioValue = portfolio.calculateAssetsValue();

            System.out.println("Krok " + i + " - Wartość portfela: " + portfolioValue);
            marketAssets.forEach(asset ->
                    System.out.println(asset.getSymbol() + " (" + asset.getName() + ") - Cena: " + asset.getCurrentPrice())
            );
            System.out.println("-----------------------------------------");
        }

        System.out.println("Stan portfela:");
        portfolio.getPositions().forEach((symbol, position) ->
                System.out.println(symbol + ": " + position.quantity() + " jednostek, aktualna cena: " + position.asset().getCurrentPrice())
        );
    }
}

