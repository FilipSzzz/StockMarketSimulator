package com.stockmarket.market;

import com.stockmarket.model.Asset;
import com.stockmarket.persistence.AssetRepository;
import java.util.*;

public class Market {
    private final Map<String, Asset> assetMap;

    public Market(AssetRepository repo) throws Exception {
        this.assetMap = repo.loadAssetDefinitions();
    }

    public Optional<Asset> getAsset(String symbol) {
        return Optional.ofNullable(assetMap.get(symbol));
    }
    public void updatePrices() {
        assetMap.values().forEach(Asset::updatePrice);
    }
    public Map<String, Asset> getAllAssets() {
        return Collections.unmodifiableMap(assetMap);
    }
}