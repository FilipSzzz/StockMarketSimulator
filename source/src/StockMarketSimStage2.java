import java.util.ArrayList;
import java.util.List;

public class StockMarketSimStage2 {
    public static void main(String[] args) {
        List<Asset> marketAssets = new ArrayList<>();
        marketAssets.add(new Stock("ABC", "Akcje ABC", 100));
        marketAssets.add(new Stock("XYZ", "Akcje XYZ", 200));
        marketAssets.add(new Bond("BOND1", "Obligacje 1", 150, 5));
        Portfolio portfolio = new Portfolio(5000);
        portfolio.addAsset(marketAssets.get(0), 10);
        portfolio.addAsset(marketAssets.get(1), 50); // 50 akcji
        portfolio.addAsset(marketAssets.get(2), 5); // 5 obligacji

        for (int step = 1; step <= 10; step++) {
            marketAssets.forEach(asset -> asset.updatePrice());

            double portfolioValue = portfolio.calculateAssetsValue();

            System.out.println("Krok " + step + " - Wartość portfela: " + portfolioValue);
            marketAssets.forEach(asset ->
                    System.out.println(asset.getSymbol() + " (" + asset.getName() + ") - Cena: " + asset.getCurrentPrice())
            );
            System.out.println("-----------------------------------------");
        }

        System.out.println("Stan końcowy portfela:");
        portfolio.getPositions().forEach((symbol, position) ->
                System.out.println(symbol + ": " + position.quantity() + " jednostek, aktualna cena: " + position.asset().getCurrentPrice())
        );
    }
}
