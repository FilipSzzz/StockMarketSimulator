public record PortfolioPosition(Asset asset, int quantity) { // wskazuje że każda instancja tej klasy musi mieć konkretny obiekt aktywa i liczbę sztuk. wiaze kazde aktywo z dana iloscia
    public PortfolioPosition{
        if (asset == null || quantity <= 0) {
            throw new IllegalArgumentException("Niepoprawne dane");
        }
    }
}
