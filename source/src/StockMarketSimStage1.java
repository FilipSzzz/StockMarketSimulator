public class StockMarketSimStage1 {
    public static void main(String[] args){
        Stock stock1 = new Stock("CDR","CD Project", 300);
        Stock stock2 = new Stock("PKO", "PKO BP", 40);
        Portfolio portfolio = new Portfolio(10000.0);
        portfolio.addStock(stock1, 10);
        portfolio.addStock(stock2, 50);
        double stockValue = portfolio.calculateStockValue();
        double totalValue = portfolio.calculateTotalValue();
        System.out.println("--- Stan Portfela ---");
        System.out.println("Gotówka: " + portfolio.getCash());
        System.out.println("Posiadane akcje:");
        for(Stock stock: portfolio.getStocksInPortfolio().keySet()){
            System.out.println(" - " + stock.getSymbol() + " (" + stock.getName() + "): " + portfolio.getStocksInPortfolio().get(stock) + " szt. @ "+ stock.getInitialPrice()+ "PLN/szt. = " + portfolio.calculateStockValue());
        }
        System.out.println("Wartość akcji: " + stockValue);
        System.out.println("Wartosc calkowita portfela: " + totalValue);
    }
}
