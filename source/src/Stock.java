
class Stock extends Asset {
    public Stock(String symbol, String name, double currentPrice) {
        super(symbol, name, currentPrice);
    }

    @Override
    public void updatePrice() {
        this.currentPrice *= (1.0 + (Math.random() - 0.5) * 0.1);
        if (this.currentPrice < 0) {
            this.currentPrice = 0;
        }
    }
}
