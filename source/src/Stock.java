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
    public boolean equals(Object object) {
        if (!(object instanceof Stock stock)) return false;
        return Objects.equals(symbol, stock.symbol);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }
}
