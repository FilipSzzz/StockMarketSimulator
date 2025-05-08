import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Portfolio {
    private double cash;
    private Map<String, PortfolioPosition> positions;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.positions = new HashMap<>();
    }

    public void addAsset(Asset asset, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        String symbol = asset.getSymbol();
        if (positions.containsKey(symbol)) {
            PortfolioPosition existingPosition = positions.get(symbol);
            int updatedQuantity = existingPosition.quantity() + quantity;
            positions.put(symbol, new PortfolioPosition(asset, updatedQuantity));
        } else {
            positions.put(symbol, new PortfolioPosition(asset, quantity));
        }
    }

    public double getCash() {
        return cash;
    }

    public Map<String, PortfolioPosition> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    public double calculateAssetsValue() {
        double totalValue = 0.0;
        for (PortfolioPosition position : positions.values()) {
            totalValue += position.quantity() * position.asset().getCurrentPrice(); // Używamy getCurrentPrice()
        }
        return totalValue;
    }

    public double calculateTotalValue() {
        return calculateAssetsValue() + getCash();
    }

    public double calculateStockValue() {
        return 0;
    }
}
