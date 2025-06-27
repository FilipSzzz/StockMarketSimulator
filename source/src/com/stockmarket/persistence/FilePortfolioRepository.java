package com.stockmarket.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.portfolio.Portfolio;
import java.io.IOException;
import java.nio.file.*;

import java.util.Optional;

public class FilePortfolioRepository implements PortfolioRepository {
    private final Path path = Paths.get("portfolio.json");
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(Portfolio p) throws IOException { // zapis obiektu do pliku
        mapper.writeValue(path.toFile(), p);
    }
    @Override
    public Optional<Portfolio> load() throws IOException {
        if (Files.exists(path)) {
            Portfolio p = mapper.readValue(path.toFile(), Portfolio.class);
            return Optional.of(p);
        }
        return Optional.empty();
    }
}