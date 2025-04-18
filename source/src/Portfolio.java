import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Portfolio{
    private double cash;
    private Map<Stock, Integer> stocksInPortfolio;

    public Portfolio(double initialCash){
        this.cash = cash;
        this.stocksInPortfolio = new HashMap<>();
    }
    void addStock(Stock stock, int quantity){
        if(stocksInPortfolio.containsKey(stock)){
            quantity += quantity;
        }else{
            stocksInPortfolio.put(stock, quantity);
        }
    }
    public double getCash(){
        return cash;
    }
    public Map<Stock, Integer> getStocksInPortfolio(){
        return Collections.unmodifiableMap(stocksInPortfolio);
    }

    public double calculateStockValue() {
        double totalValue = 0.0;
        for (Map.Entry<Stock, Integer> entry : stocksInPortfolio.entrySet()) {
            totalValue += entry.getValue() * entry.getKey().getInitialPrice();
        }
        return totalValue;
    }
    public double calculateTotalValue(){
        return calculateStockValue() + getCash();
    }
}

