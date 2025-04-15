public class StockMarketSimStage1 {
    public static void main(String[] args){
        Stock stock1 = new Stock("CDR","CD Project", 300);
        Stock stock2 = new Stock("PKO", "PKO BP", 40);
        System.out.println(stock1);
        System.out.println(stock2);
        Portfolio portfolio = new Portfolio(1000);
        portfolio.addStock(stock1, 5);
        portfolio.addStock(stock2, 10);
        System.out.println("Cash: " + portfolio.getCash());

    }
}
