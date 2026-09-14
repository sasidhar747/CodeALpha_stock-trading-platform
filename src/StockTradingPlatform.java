import java.util.ArrayList;
import java.util.HashMap;

/**
 * StockTradingPlatform.java
 * Central engine managing market data, user account, and all trading operations.
 * 
 * @author CodeAlpha Intern
 */
public class StockTradingPlatform {

    private HashMap<String, Stock> marketStocks;
    private User user;
    private ArrayList<Transaction> transactionHistory;

    /**
     * Constructor to initialize the trading platform.
     * 
     * @param userName      The name of the user
     * @param startingCash  The starting cash balance
     */
    public StockTradingPlatform(String userName, double startingCash) {
        this.marketStocks = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
        this.user = new User(userName, startingCash);
        initializeMarket();
    }

    // ==================== Market Setup ====================

    /**
     * Initializes the market with predefined stocks.
     */
    private void initializeMarket() {
        addStock(new Stock("AAPL",  "Apple Inc.",      180.00));
        addStock(new Stock("GOOGL", "Alphabet Inc.",   150.00));
        addStock(new Stock("MSFT",  "Microsoft",       420.00));
        addStock(new Stock("AMZN",  "Amazon",          180.00));
        addStock(new Stock("TSLA",  "Tesla",           250.00));
        addStock(new Stock("META",  "Meta Platforms",  480.00));
        addStock(new Stock("NFLX",  "Netflix",         620.00));
        addStock(new Stock("NVDA",  "NVIDIA",          870.00));
    }

    /**
     * Adds a stock to the market.
     * 
     * @param stock The stock to add
     */
    private void addStock(Stock stock) {
        marketStocks.put(stock.getSymbol(), stock);
    }

    // ==================== Getters ====================

    public User getUser() {
        return user;
    }

    public HashMap<String, Stock> getMarketStocks() {
        return marketStocks;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    // ==================== Display Market ====================

    /**
     * Displays all available stocks in the market.
     */
    public void displayMarketData() {
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("                  STOCK MARKET");
        System.out.println("  ================================================");
        System.out.printf("  %-10s %-22s %10s%n", "Symbol", "Company", "Price");
        System.out.println("  " + "-".repeat(44));

        for (Stock stock : marketStocks.values()) {
            System.out.printf("  %-10s %-22s $%9.2f%n",
                    stock.getSymbol(), stock.getCompanyName(), stock.getCurrentPrice());
        }

        System.out.println("  ================================================");
    }

    // ==================== Buy Stock ====================

    /**
     * Processes a stock purchase.
     * 
     * @param symbol   The stock ticker symbol
     * @param quantity Number of shares to buy
     * @return A message describing the result
     */
    public String buyStock(String symbol, int quantity) {
        symbol = symbol.toUpperCase().trim();

        // Validate stock exists
        Stock stock = marketStocks.get(symbol);
        if (stock == null) {
            return "  ERROR: Stock symbol '" + symbol + "' not found in the market.";
        }

        // Validate quantity
        if (quantity <= 0) {
            return "  ERROR: Quantity must be greater than 0.";
        }

        double totalCost = stock.getCurrentPrice() * quantity;

        // Check balance
        if (totalCost > user.getCashBalance()) {
            return String.format(
                "  ERROR: Insufficient funds.%n" +
                "  Required : $%,.2f%n" +
                "  Available: $%,.2f",
                totalCost, user.getCashBalance());
        }

        // Execute purchase
        user.buyStock(stock, quantity);

        // Record transaction
        Transaction transaction = new Transaction("BUY", stock.getSymbol(),
                stock.getCompanyName(), quantity, stock.getCurrentPrice());
        transactionHistory.add(transaction);

        return String.format(
            "%n  ✓ Purchase Successful!%n" +
            "  Bought %d shares of %s (%s)%n" +
            "  Price per share : $%,.2f%n" +
            "  Total Cost      : $%,.2f%n" +
            "  Remaining Cash  : $%,.2f",
            quantity, symbol, stock.getCompanyName(),
            stock.getCurrentPrice(), totalCost, user.getCashBalance());
    }

    // ==================== Sell Stock ====================

    /**
     * Processes a stock sale.
     * 
     * @param symbol   The stock ticker symbol
     * @param quantity Number of shares to sell
     * @return A message describing the result
     */
    public String sellStock(String symbol, int quantity) {
        symbol = symbol.toUpperCase().trim();

        // Validate stock exists
        Stock stock = marketStocks.get(symbol);
        if (stock == null) {
            return "  ERROR: Stock symbol '" + symbol + "' not found in the market.";
        }

        // Validate quantity
        if (quantity <= 0) {
            return "  ERROR: Quantity must be greater than 0.";
        }

        // Check if user owns enough shares
        int owned = user.getPortfolio().getSharesOwned(symbol);
        if (owned == 0) {
            return "  ERROR: You do not own any shares of " + symbol + ".";
        }
        if (quantity > owned) {
            return String.format(
                "  ERROR: Insufficient shares.%n" +
                "  You own %d shares of %s but tried to sell %d.",
                owned, symbol, quantity);
        }

        double totalValue = stock.getCurrentPrice() * quantity;

        // Execute sale
        user.sellStock(stock, quantity);

        // Record transaction
        Transaction transaction = new Transaction("SELL", stock.getSymbol(),
                stock.getCompanyName(), quantity, stock.getCurrentPrice());
        transactionHistory.add(transaction);

        return String.format(
            "%n  ✓ Sale Successful!%n" +
            "  Sold %d shares of %s (%s)%n" +
            "  Price per share : $%,.2f%n" +
            "  Total Proceeds  : $%,.2f%n" +
            "  Remaining Cash  : $%,.2f",
            quantity, symbol, stock.getCompanyName(),
            stock.getCurrentPrice(), totalValue, user.getCashBalance());
    }

    // ==================== View Portfolio ====================

    /**
     * Displays the user's complete portfolio.
     */
    public void viewPortfolio() {
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("                  MY PORTFOLIO");
        System.out.println("  ================================================");

        user.getPortfolio().displayPortfolio(marketStocks);

        System.out.printf("%n  Cash Balance       : $%,.2f%n", user.getCashBalance());
        double stockValue = user.getPortfolio().calculateTotalValue(marketStocks);
        System.out.printf("  Total Stock Value  : $%,.2f%n", stockValue);
        System.out.printf("  Total Portfolio    : $%,.2f%n", user.getCashBalance() + stockValue);
        System.out.println("  ================================================");
    }

    // ==================== View Performance ====================

    /**
     * Displays portfolio performance.
     */
    public void viewPerformance() {
        user.displayPerformance(marketStocks);
    }

    // ==================== Transaction History ====================

    /**
     * Displays all recorded transactions.
     */
    public void displayTransactionHistory() {
        System.out.println();
        System.out.println("  =====================================================================");
        System.out.println("                        TRANSACTION HISTORY");
        System.out.println("  =====================================================================");

        if (transactionHistory.isEmpty()) {
            System.out.println("\n  No transactions recorded yet.");
            System.out.println("  =====================================================================");
            return;
        }

        System.out.printf("  %-8s %-8s %5s     %12s   %12s   %s%n",
                "Type", "Stock", "Qty", "Price", "Total", "Date & Time");
        System.out.println("  " + "-".repeat(65));

        for (Transaction t : transactionHistory) {
            System.out.print("  ");
            t.displayTransaction();
        }

        System.out.println("  =====================================================================");
    }

    // ==================== View Balance ====================

    /**
     * Displays the user's account balance.
     */
    public void viewBalance() {
        user.displayBalance();
    }
}
