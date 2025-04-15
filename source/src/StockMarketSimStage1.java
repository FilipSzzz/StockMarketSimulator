import java.util.Map;

public class StockMarketSimStage1 {
    public static void main(String[] args){
        Stock stock1 = new Stock("CDR","CD Project", 300);
        Stock stock2 = new Stock("PKO", "PKO BP", 40);
        Portfolio portfolio = new Portfolio(10000.0);
        portfolio.addStock(stock1, 10);
        portfolio.addStock(stock2, 50);
        System.out.println("--- Stan Portfela ---");
        System.out.println("Gotówka: " + portfolio.getCash());
        System.out.println("Posiadane akcje:");
        for (Map.Entry<Stock, Integer> entry : portfolio.getStocksInPortfolio().entrySet()) {
            Stock stock = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(stock.getSymbol() + " " + stock.getName() + " " + quantity);
        }
        System.out.println("Wartość akcji: " + portfolio.calculateStockValue());
        System.out.println("Wartosc calkowita portfela: " + portfolio.calculateTotalValue());
    }
}
