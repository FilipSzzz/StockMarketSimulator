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
    @Override                                                   // wygenerowane przez IDE nadpisanie metody equals i hashCode
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Stock stock = (Stock) object;
        return Double.compare(initialPrice, stock.initialPrice) == 0 && Objects.equals(symbol, stock.symbol) && Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() { // zwraca unikalna wartość liczbową typu int dla każdego unikalnego obiektu
        return Objects.hash(symbol, name, initialPrice);
    }
}
