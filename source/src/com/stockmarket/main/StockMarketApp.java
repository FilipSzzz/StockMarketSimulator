package com.stockmarket.main;

import com.stockmarket.market.*;
import com.stockmarket.persistence.*;
import com.stockmarket.portfolio.*;
import com.stockmarket.ui.ConsoleUI;

public class StockMarketApp {
    public static void main(String[] args) throws Exception {

        AssetRepository assetRepo = new CsvAssetRepository();
        PortfolioRepository portfolioRepo = new FilePortfolioRepository();
        PortfolioService portfolioService = new PortfolioService(portfolioRepo);
        Market market = new Market(assetRepo);
        Portfolio portfolio = portfolioService.load().orElse(new Portfolio(10_000));
        ConsoleUI ui = new ConsoleUI(portfolio, market, portfolioService);
        ui.start();
    }
}