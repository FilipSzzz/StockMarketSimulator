package com.stockmarket.market;
import com.stockmarket.model.Asset;
import com.stockmarket.model.Stock;

import java.util.*;

public class Market {
    private Map<String, Asset> assetMap;
    public Market() {
        assetMap = new HashMap<>();
    }
    public Market(List<Asset>assets){
        assetMap = new HashMap<>();
        for(Asset asset : assets){
            assetMap.put(asset.getSymbol(), asset);
        }
    }

    public Optional<Asset> getAsset(String symbol){
        return Optional.ofNullable(assetMap.get(symbol));
    }
    public void updatePrices(){
        for(Asset asset : assetMap.values()){
            asset.updatePrice();
        }
    }
    public Map<String, Asset> getAllAssets() {
        return Collections.unmodifiableMap(new HashMap<>(assetMap));
    }

    public void addAsset(Asset asset) {
        assetMap.put(asset.getSymbol(), asset);
    }
}
