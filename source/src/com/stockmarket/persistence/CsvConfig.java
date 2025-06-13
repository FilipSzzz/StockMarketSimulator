package com.stockmarket.persistence;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class CsvConfig {
    private static final String ASSETS_CSV = "assets.csv";
    private static final Path ASSETS_PATH = Paths.get("source/src/" + ASSETS_CSV);

    public static Path getAssetsPath() {
        return ASSETS_PATH;
    }

    public static void initializeAssetsCsv() throws IOException {
        if (!Files.exists(ASSETS_PATH)) {
            Files.createFile(ASSETS_PATH);
            String header = "symbol,name,type,initial_price\n";
            Files.writeString(ASSETS_PATH, header);
        }
    }
}

