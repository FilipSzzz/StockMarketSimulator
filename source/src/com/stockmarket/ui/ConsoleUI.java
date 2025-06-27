package com.stockmarket.ui;

import com.stockmarket.portfolio.*;
import com.stockmarket.market.Market;
import java.util.Scanner;
public class ConsoleUI {
    private Portfolio portfolio;
    private final Market market;
    private final PortfolioService portfolioService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Portfolio portfolio, Market market, PortfolioService service) {
        this.portfolio = portfolio;
        this.market = market;
        this.portfolioService = service;
    }

    public void start() {
        while (true) {
            System.out.println("""
                1. Pokaż rynek
                2. Pokaż portfel
                3. Kup aktywo
                4. Sprzedaj aktywo
                5. Zapisz portfel
                6. Wczytaj portfel
                7. Aktualizuj ceny
                0. Wyjdź
                """);
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> displayMarket();
                case "2" -> displayPortfolio();
                case "3" -> handleBuy();
                case "4" -> handleSell();
                case "5" -> handleSave();
                case "6" -> handleLoad();
                case "7" -> handleUpdatePrices();
                case "0" -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieznana opcja!");
            }
        }
    }

    private void displayMarket() {
        market.getAllAssets().values().forEach(a ->
                System.out.printf("%s (%s): %.2f PLN%n", a.getName(), a.getSymbol(), a.getCurrentPrice()));
    }
    private void displayPortfolio() {
        System.out.printf("Gotówka: %.2f PLN%n", portfolio.getCash());
        portfolio.getPositions().values().forEach(pos ->
                System.out.printf("%s: %d szt. x %.2f PLN = %.2f PLN%n",
                        pos.asset().getSymbol(), pos.quantity(), pos.asset().getCurrentPrice(),
                        pos.quantity() * pos.asset().getCurrentPrice()));
    }

    private void handleBuy() {
        System.out.print("Podaj symbol aktywa: ");
        String symbol = scanner.nextLine();
        System.out.print("Ilość: ");
        String quantityStr = scanner.nextLine();
        if (quantityStr.isBlank()) {
            System.out.println("Nie podano ilości!");
            return;
        }
        int quantity = Integer.parseInt(quantityStr);
        try {
            portfolio.buy(symbol, quantity, market);
            System.out.println("Kupiono!");
        } catch (Exception e) {
            System.out.println("Błąd przy kupnie: " + e.getMessage());
        }
    }

    private void handleSell() {
        System.out.print("Podaj symbol aktywa: ");
        String symbol = scanner.nextLine();
        System.out.print("Ilość: ");
        String quantityStr = scanner.nextLine();
        if (quantityStr.isBlank()) {
            System.out.println("Nie podano ilości!");
            return;
        }
        int quantity = Integer.parseInt(quantityStr);
        try {
            portfolio.sell(symbol, quantity, market);
            System.out.println("Sprzedano!");
        } catch (Exception e) {
            System.out.println("Błąd przy sprzedaży: " + e.getMessage());
        }
    }

    private void handleSave() {
        try {
            portfolioService.save(portfolio);
            System.out.println("Portfel zapisany.");
        } catch (Exception e) {
            System.out.println("Błąd przy zapisie: " + e.getMessage());
        }
    }

    private void handleLoad() {
        try {
            Portfolio p = portfolioService.load().orElse(null);
            if (p != null) {
                this.portfolio = p;
                System.out.println("Portfel wczytany.");
            } else {
                System.out.println("Brak zapisanego portfela.");
            }
        } catch (Exception e) {
            System.out.println("Błąd przy wczytywaniu: " + e.getMessage());
        }
    }


    private void handleUpdatePrices() {
        market.updatePrices();
        System.out.println("Ceny zostały zaktualizowane.");
    }
}