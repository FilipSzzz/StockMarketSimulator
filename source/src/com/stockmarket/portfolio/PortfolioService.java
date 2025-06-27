package com.stockmarket.portfolio;
import com.stockmarket.persistence.PortfolioRepository;

import java.io.IOException;
import java.util.Optional;

public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void save(Portfolio p) throws IOException {
        portfolioRepository.save(p);
    }

    public Optional<Portfolio> load() throws IOException {
        return portfolioRepository.load();
    }
}