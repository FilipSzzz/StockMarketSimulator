import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Portfolio{
    private double cash;
    private Map<Stock, Integer> stocksInPortfolio;

    public Portfolio(double initialCash){
        this.cash = initialCash;
        this.stocksInPortfolio = new HashMap<>();
    }
    public void addStock(Stock stock, int quantity){
        if(stocksInPortfolio.containsKey(stock)){ // spradza czy stock jest w mapie, jezeli jest to dodaje quantity. Jezeli nie to dodaje nowy wpisy do mapy(stock, quantity)
            quantity += quantity;
        }else{
            stocksInPortfolio.put(stock, quantity);
        }
    }
    public double getCash(){
        return cash;
    }
    public Map<Stock, Integer> getStocksInPortfolio(){ // zwraca kopie mapy, by zapobiec modyfikacji stanu portfela z zewnatrz
        return Collections.unmodifiableMap(stocksInPortfolio);
    }

    public double calculateStockValue() {
        double totalValue = 0.0;
        for (Map.Entry<Stock, Integer> entry : stocksInPortfolio.entrySet()) { // iteruje po mapie, kazdy wpis mnozy przez cene poczatkowa akcji 
            totalValue += entry.getValue() * entry.getKey().getInitialPrice();
        }
        return totalValue;
    }
    public double calculateTotalValue(){
        return calculateStockValue() + getCash();
    }
}

