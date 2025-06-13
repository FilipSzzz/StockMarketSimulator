package com.stockmarket.persistence;

import com.stockmarket.portfolio.Portfolio;

import java.util.Optional;

public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void savePortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    public Optional<Portfolio> loadPortfolio() {
        return portfolioRepository.load();
    }

    public void save(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    public Optional<Portfolio> load() {
        return portfolioRepository.load();
    }
}