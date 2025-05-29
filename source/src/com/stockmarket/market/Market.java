package com.stockmarket.market;
import com.stockmarket.model.Asset;

import java.util.*;

public class Market {
    private Map<String, Asset> assetMap; // przechowujemy asset by moc wywolac updatePrice
    public Market() {
        assetMap = new HashMap<>();
    }
    public Market(List<Asset>assets){
        assetMap = new HashMap<>();
        for(Asset asset : assets){
            assetMap.put(asset.getSymbol(), asset);
        }
    }

    public Optional<Asset> getAsset(String symbol){      // szuka asset po symbolu, assetMap laczy wartosc z obiektami Asset
        return Optional.ofNullable(assetMap.get(symbol)); // pobiera symbol z assetMap, jezeli nie znajdzie takiego zwraca null
    } // jezeli aktywo o podanym symbolu znajduje sie w mapie ta metoda zwraca to aktywo
    public void updatePrices(){
        for(Asset asset : assetMap.values()){
            asset.updatePrice();
        }
    }
    public Map<String, Asset> getAllAssets() { // zwraca kopie mapy z aktywami, by uniknac modyfikacji
        return Collections.unmodifiableMap(new HashMap<>(assetMap));
    }

    public void addAsset(Asset asset) {
        assetMap.put(asset.getSymbol(), asset);
    }
}
