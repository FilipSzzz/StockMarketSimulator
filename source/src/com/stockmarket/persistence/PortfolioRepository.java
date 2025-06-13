package com.stockmarket.persistence;

import com.stockmarket.portfolio.Portfolio;

import java.io.IOException;
import java.util.Optional;

public interface PortfolioRepository {
    void save(Portfolio p) throws IOException;
    Optional<Portfolio> load() throws IOException;
}
