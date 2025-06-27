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
    public static boolean ifExists(){
        return Files.exists(ASSETS_PATH);
    }

    public static void initializeAssetsCsv() throws IOException {
        if(!ifExists()) {
            Files.createDirectories(ASSETS_PATH.getParent());
            Files.createFile(ASSETS_PATH);
        }else{

        }
    }
}

