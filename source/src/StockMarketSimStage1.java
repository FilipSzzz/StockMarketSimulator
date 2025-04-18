public class StockMarketSimStage1 {
    public static void main(String[] args){
        Stock stock1 = new Stock("CDR","CD Project", 300);
        Stock stock2 = new Stock("PKO", "PKO BP", 40);
        Stock stock3 = new Stock("XTB", "XTB", 70);
        Portfolio portfolio = new Portfolio(5000);
        portfolio.addStock(stock1, 10);
        portfolio.addStock(stock2, 50);
        portfolio.addStock(stock3, 100);
        double stockValue = portfolio.calculateStockValue();
        double totalValue = portfolio.calculateTotalValue();
        if(portfolio.getCash() < 0){
            System.out.println("Nie masz wystarczającej ilości gotówki na koncie!");
            return;
        }
        System.out.println("--- Stan Portfela ---");
        System.out.println("Gotówka: " + portfolio.getCash());
        System.out.println("Posiadane akcje:");
        for(Stock stock: portfolio.getStocksInPortfolio().keySet()){
            int quantity = portfolio.getStocksInPortfolio().get(stock);
            double stockLineValue = stock.getInitialPrice() * quantity;
            System.out.println(" - " + stock.getSymbol() + " (" + stock.getName() + "): " + quantity  +" szt. @ "+ stock.getInitialPrice()+ "PLN/szt. = " + stockLineValue + " PLN");
        }
        System.out.println("Wartość akcji: " + stockValue);
        System.out.println("Wartosc calkowita portfela: " + totalValue);
    }
}
