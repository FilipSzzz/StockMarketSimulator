package com.stockmarket.persistence;

import com.stockmarket.model.*;
import java.io.*;
import java.util.*;

public class CsvAssetRepository implements AssetRepository {

    @Override
    public Map<String, Asset> loadAssetDefinitions() throws IOException {
        Map<String, Asset> map = new HashMap<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("assets.csv");
        if (inputStream == null) {
            throw new FileNotFoundException("Nie znaleziono pliku assets.csv w resources!");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = bufferedReader.readLine(); // zczytanie pierwszej linii czyli naglowka, wszystko pozostale to dane
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.err.println("Błędny format wiersza w assets.csv: " + line);
                    continue;
                }
                String symbol = parts[0];
                String name = parts[1];
                String type = parts[2];
                double price;
                try {
                    price = Double.parseDouble(parts[3]);
                } catch (NumberFormatException ex) {
                    System.err.println("Nieprawidłowa cena w wierszu: " + line);
                    continue;
                }
                Asset asset = switch (type) {
                    case "stock" -> new Stock(symbol, name, price);
                    case "bond"  -> new Bond(symbol, name, price, 0.01);
                    default -> throw new IllegalArgumentException("Nieznany typ aktywa: " + type);
                };
                map.put(symbol, asset);
            }
        }
        return map;
    }

}