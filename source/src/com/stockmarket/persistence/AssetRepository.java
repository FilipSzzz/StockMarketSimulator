package com.stockmarket.persistence;

import com.stockmarket.model.Asset;

import java.util.Map;

public interface AssetRepository  {
    Map<String, Asset> loadAssetDefinitions() throws Exception;
}