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

    public double calculateStockValue(){
        for(Stock i : stocksInPortfolio.keySet()){
            return 2.333; // TODO: implement this method
        }
        return 0;
    }
    public double calculateTotalValue(){
        return calculateStockValue() + getCash();
    }
}

