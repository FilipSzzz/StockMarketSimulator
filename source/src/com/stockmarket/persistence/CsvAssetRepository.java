package com.stockmarket.persistence;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.stockmarket.model.Asset;
import com.stockmarket.model.Stock;
import com.stockmarket.model.Bond;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementacja zarządzania aktywami z plików CSV
 */
public class CsvAssetRepository implements AssetRepository {

    private static final String CSV_FILE = "assets.csv";
    private final Path csvFilePath;

    public CsvAssetRepository() {
        this(CSV_FILE);
    }

    public CsvAssetRepository(String csvFileName) {
        this.csvFilePath = Paths.get(csvFileName);
    }

    @Override
    public Map<String, Asset> loadAssetDefinitions()   {
        Map<String, Asset> assets = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath.toFile()))) {
            List<String[]> records = reader.readAll();

            // Pomijamy nagłówek (pierwsza linia)
            if (records.isEmpty()) {
                return assets;
            }

            // Sprawdzamy format nagłówka
            String[] header = records.get(0);
            validateHeader(header);

            // Przetwarzamy każdy wiersz
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                if (record.length < 4) {
                    continue; // Pomijamy niepełne wiersze
                }

                try {
                    Asset asset = createAssetFromRecord(record);
                    assets.put(asset.getSymbol(), asset);
                } catch (Exception e) {
                    System.err.println("Błąd podczas przetwarzania wiersza " + (i + 1) + ": " + e.getMessage());
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return assets;
    }

    private void validateHeader(String[] header)  {

        String[] expectedHeaders = {"symbol", "name", "type", "initial_price"};
        for (int i = 0; i < expectedHeaders.length; i++) {

        }
    }

    private Asset createAssetFromRecord(String[] record) {
        String symbol = record[0].trim();
        String name = record[1].trim();
        String type = record[2].trim().toUpperCase();


        switch (type) {
            case "STOCK":
                return new Stock(symbol, name, currentPrice);
            case "BOND":
                return new Bond(symbol, name, initialPrice,3);
            default:
                throw new IllegalArgumentException("Nieznany typ aktywa: " + type);
        }
    }

    @Override
    public boolean assetFileExists() {
        return Files.exists(csvFilePath) && Files.isRegularFile(csvFilePath);
    }
}
