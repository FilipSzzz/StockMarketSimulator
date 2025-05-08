public record PortfolioPosition(Asset asset, int quantity) {
    public PortfolioPosition{
        if (asset == null || quantity <= 0) {
            throw new IllegalArgumentException("Niepoprawne dane");
        }
    }
}
