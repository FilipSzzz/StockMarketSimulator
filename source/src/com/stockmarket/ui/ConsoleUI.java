package com.stockmarket.ui;

import com.stockmarket.market.Market;
import com.stockmarket.model.Asset;
import com.stockmarket.persistence.PortfolioService;
import com.stockmarket.portfolio.Portfolio;
import com.stockmarket.portfolio.PortfolioPosition;
import com.stockmarket.exception.AssetNotFoundException;
import com.stockmarket.exception.InsufficientAssetsException;
import com.stockmarket.exception.InsufficientFundsException;

import java.util.Scanner;

public class ConsoleUI {
    private final PortfolioService portfolioService;
    private final Market market;
    private Portfolio currentPortfolio;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(PortfolioService portfolioService, Market market) {
        this.portfolioService = portfolioService;
        this.market = market;
        this.currentPortfolio = portfolioService.load().orElse(new Portfolio(30000));
    }

    public void start() {
        boolean exit = false;
        System.out.println("STOCK MARKET SIMULATOR");
        while (!exit) {
            printMenu();
            int choice = readInt("Wybierz opcję: ");
            switch (choice) {
                case 1 -> displayMarket();
                case 2 -> handleBuyAction();
                case 3 -> handleSellAction();
                case 4 -> displayPortfolio();
                case 5 -> handleUpdatePrices();
                case 6 -> handleSaveAction();
                case 7 -> exit = handleExit();
                default -> System.out.println("Nieznana opcja!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMENU:");
        System.out.println("1. Wyświetl rynek");
        System.out.println("2. Kup akcje/obligacje");
        System.out.println("3. Sprzedaj akcje/obligacje");
        System.out.println("4. Wyświetl portfel");
        System.out.println("5. Aktualizuj ceny na rynku");
        System.out.println("6. Zapisz stan portfela");
        System.out.println("7. Wyjdź");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Niepoprawna liczba! Spróbuj jeszcze raz.");
            }
        }
    }

    private void displayMarket() {
        System.out.println("\nAKTYWA NA RYNKU:");
        for (Asset asset : market.getAssetMap().values()) {
            System.out.printf("Symbol: %s | Nazwa: %s | Cena: %.2f%n",
                    asset.getSymbol(), asset.getName(), asset.getCurrentPrice());
        }
    }

    private void handleBuyAction() {
        System.out.print("Podaj symbol aktywa do zakupu: ");
        String symbol = scanner.nextLine().trim();
        int quantity = readInt("Podaj ilość do zakupu: ");
        try {
            currentPortfolio.buy(symbol, quantity, market);
            System.out.println("Zakupiono " + quantity + " sztuk " + symbol);
        } catch (InsufficientFundsException | AssetNotFoundException | IllegalArgumentException e) {
            System.out.println("Błąd przy zakupie: " + e.getMessage());
        }
    }

    private void handleSellAction() {
        System.out.print("Podaj symbol aktywa do sprzedaży: ");
        String symbol = scanner.nextLine().trim();
        int quantity = readInt("Podaj ilość do sprzedaży: ");
        try {
            currentPortfolio.sell(symbol, quantity, market);
            System.out.println("Sprzedano " + quantity + " sztuk " + symbol);
        } catch (InsufficientAssetsException | AssetNotFoundException | IllegalArgumentException e) {
            System.out.println("Błąd przy sprzedaży: " + e.getMessage());
        }
    }

    private void displayPortfolio() {
        System.out.printf("Środki dostępne: %.2f%n", currentPortfolio.getCash());
        System.out.println("Aktywa w portfelu:");
        if (currentPortfolio.getPositions().isEmpty()) {
            System.out.println("(Brak aktywów w portfelu)");
        } else {
            for (PortfolioPosition position : currentPortfolio.getPositions().values()) {
                System.out.printf("%s: %d sztuk%n", position.asset().getSymbol(), position.quantity());
            }
        }
    }

    private void handleUpdatePrices() {
        market.updateAssetPrices();
        System.out.println("Ceny aktywów zostały zaktualizowane.");
    }

    private void handleSaveAction() {
        try {
            portfolioService.save(currentPortfolio);
            System.out.println("Portfel zapisany pomyślnie.");
        } catch (Exception e) {
            System.out.println("Błąd przy zapisie portfela: " + e.getMessage());
        }
    }

    private boolean handleExit() {
        System.out.print("Czy chcesz zapisać portfel przed wyjściem? (T/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (!response.equals("n")) {
            handleSaveAction();
        }
        System.out.println("Do widzenia!");
        return true;
    }
}