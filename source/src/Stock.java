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
    public boolean equals(Object object) { // nadpisana metoda equals, wygenerowana przez IDE
        if (!(object instanceof Stock stock)) return false; // jezeli objekt jest instancja klasy to zwraca false 
        return Objects.equals(symbol, stock.symbol); // jezeli obiekt jest typu Stock porowniuje symbol obiektu z symbolem biezacego obiektu, zwraca true jezeli sa takie same
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }
}
