package com.stockmarket.persistence;
import com.stockmarket.persistence.PortfolioRepository;
import com.stockmarket.portfolio.Portfolio;

import java.io.*;
import java.util.Optional;

public class FilePortfolioRepository implements PortfolioRepository {
    private static final String FILE_NAME = "portfolio.json";

    @Override
    public void save(Portfolio p) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(p.toString());
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
        }
    }

    @Override
    public Optional<Portfolio> load() {

    }
}
