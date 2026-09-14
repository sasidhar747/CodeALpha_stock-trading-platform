import java.util.HashMap;
import java.util.Map;

/**
 * Portfolio.java
 * Manages the user's stock holdings.
 * Tracks which stocks are owned and in what quantity.
 * 
 * @author CodeAlpha Intern
 */
public class Portfolio {

    // Maps stock symbol to quantity owned
    private HashMap<String, Integer> holdings;

    /**
     * Constructor to create an empty Portfolio.
     */
    public Portfolio() {
        this.holdings = new HashMap<>();
    }

    // ==================== Core Methods ====================

    /**
     * Adds shares of a stock to the portfolio.
     * 
     * @param symbol   The stock ticker symbol
     * @param quantity Number of shares to add
     */
    public void addStock(String symbol, int quantity) {
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
    }

    /**
     * Removes shares of a stock from the portfolio.
     * 
     * @param symbol   The stock ticker symbol
     * @param quantity Number of shares to remove
     * @return true if successful, false if insufficient shares
     */
    public boolean removeStock(String symbol, int quantity) {
        int currentQuantity = holdings.getOrDefault(symbol, 0);

        if (currentQuantity < quantity) {
            return false;
        }

        int remaining = currentQuantity - quantity;
        if (remaining == 0) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
        }
        return true;
    }

    /**
     * Gets the number of shares owned of a specific stock.
     * 
     * @param symbol The stock ticker symbol
     * @return Number of shares owned
     */
    public int getSharesOwned(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    /**
     * Checks if the portfolio is empty.
     * 
     * @return true if no stocks are held
     */
    public boolean isEmpty() {
        return holdings.isEmpty();
    }

    /**
     * Gets all holdings as a map.
     * 
     * @return Map of symbol to quantity
     */
    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }

    /**
     * Calculates the total market value of all stocks in the portfolio.
     * 
     * @param marketStocks Map of all available stocks in the market
     * @return Total portfolio stock value
     */
    public double calculateTotalValue(HashMap<String, Stock> marketStocks) {
        double totalValue = 0.0;

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            Stock stock = marketStocks.get(symbol);

            if (stock != null) {
                totalValue += stock.getCurrentPrice() * quantity;
            }
        }
        return totalValue;
    }

    /**
     * Displays the portfolio in a clean formatted table.
     * 
     * @param marketStocks Map of all available stocks in the market
     */
    public void displayPortfolio(HashMap<String, Stock> marketStocks) {
        if (holdings.isEmpty()) {
            System.out.println("\n  Your portfolio is empty. Start by buying some stocks!");
            return;
        }

        System.out.println();
        System.out.printf("  %-10s %-22s %8s   %10s   %12s%n",
                "Symbol", "Company", "Quantity", "Price", "Value");
        System.out.println("  " + "-".repeat(68));

        double totalStockValue = 0.0;

        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            Stock stock = marketStocks.get(symbol);

            if (stock != null) {
                double value = stock.getCurrentPrice() * quantity;
                totalStockValue += value;

                System.out.printf("  %-10s %-22s %8d   $%9.2f   $%11.2f%n",
                        symbol, stock.getCompanyName(), quantity,
                        stock.getCurrentPrice(), value);
            }
        }

        System.out.println("  " + "-".repeat(68));
        System.out.printf("  Total Stock Value: $%.2f%n", totalStockValue);
    }
}
