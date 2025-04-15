import java.util.Objects;

class Stock {
    private String symbol;
    private String name;
    private double initialPrice;

    public Stock(String symbol, String name, double initialPrice ){
        this.symbol = symbol;
        this.name = name;
        this.initialPrice = initialPrice;
    }
    public String getSymbol(){
        return this.symbol;
    }
    public String getName(){
        return this.name;
    }
    public double getInitialPrice(){
        return this.initialPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Stock stock = (Stock) obj;
        return symbol.equals(stock.symbol);
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}