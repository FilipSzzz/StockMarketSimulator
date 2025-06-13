package com.stockmarket.market;
import com.stockmarket.model.Asset;
import com.stockmarket.persistence.AssetRepository;

import java.io.FileNotFoundException;
import java.util.*;

public class Market {
    private Map<String, Asset> assetMap; // przechowujemy asset by moc wywolac updatePrice
    public Market() {
        assetMap = new HashMap<>();
    }
    public Market(AssetRepository assetRepository) throws FileNotFoundException {
        this.assetMap = assetRepository.loadAssetDefinitions(); // laduje aktywa z repozytorium
    }

    public Map<String, Asset> getAssetMap() {
        return assetMap;
    }

    public Optional<Asset> getAsset(String symbol) {
        return Optional.ofNullable(assetMap.get(symbol));
    }

    public void updateAssetPrices() {
        for (Asset asset : assetMap.values()) {
            if (asset instanceof Tradable tradable) {
                tradable.updatePrice();
            }
        }
    }
}
