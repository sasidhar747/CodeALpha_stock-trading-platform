import java.util.HashMap;

/**
 * User.java
 * Represents the trader/user with a name, cash balance, and portfolio.
 * 
 * @author CodeAlpha Intern
 */
public class User {

    private String name;
    private double cashBalance;
    private double initialInvestment;
    private Portfolio portfolio;

    /**
     * Constructor to create a new User.
     * 
     * @param name           The user's name
     * @param initialBalance The starting cash balance
     */
    public User(String name, double initialBalance) {
        this.name = name;
        this.cashBalance = initialBalance;
        this.initialInvestment = initialBalance;
        this.portfolio = new Portfolio();
    }

    // ==================== Getters ====================

    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // ==================== Core Methods ====================

    /**
     * Deducts cash from the user's balance.
     * 
     * @param amount The amount to deduct
     * @return true if successful, false if insufficient funds
     */
    public boolean deductCash(double amount) {
        if (amount > cashBalance) {
            return false;
        }
        cashBalance -= amount;
        return true;
    }

    /**
     * Adds cash to the user's balance.
     * 
     * @param amount The amount to add
     */
    public void addCash(double amount) {
        cashBalance += amount;
    }

    /**
     * Buys stock: deducts cash and adds shares to portfolio.
     * 
     * @param stock    The stock to buy
     * @param quantity Number of shares to buy
     * @return true if purchase was successful
     */
    public boolean buyStock(Stock stock, int quantity) {
        double totalCost = stock.getCurrentPrice() * quantity;

        if (totalCost > cashBalance) {
            return false;
        }

        cashBalance -= totalCost;
        portfolio.addStock(stock.getSymbol(), quantity);
        return true;
    }

    /**
     * Sells stock: adds cash and removes shares from portfolio.
     * 
     * @param stock    The stock to sell
     * @param quantity Number of shares to sell
     * @return true if sale was successful
     */
    public boolean sellStock(Stock stock, int quantity) {
        int owned = portfolio.getSharesOwned(stock.getSymbol());

        if (owned < quantity) {
            return false;
        }

        double totalValue = stock.getCurrentPrice() * quantity;
        portfolio.removeStock(stock.getSymbol(), quantity);
        cashBalance += totalValue;
        return true;
    }

    /**
     * Displays the user's current account balance.
     */
    public void displayBalance() {
        System.out.println();
        System.out.println("  ======================================");
        System.out.println("           ACCOUNT BALANCE");
        System.out.println("  ======================================");
        System.out.printf("  Account Holder : %s%n", name);
        System.out.printf("  Cash Balance   : $%,.2f%n", cashBalance);
        System.out.println("  ======================================");
    }

    /**
     * Displays portfolio performance: initial investment, current value, P/L.
     * 
     * @param marketStocks Map of all available stocks in the market
     */
    public void displayPerformance(HashMap<String, Stock> marketStocks) {
        double stockValue = portfolio.calculateTotalValue(marketStocks);
        double currentTotal = cashBalance + stockValue;
        double profitLoss = currentTotal - initialInvestment;
        double profitLossPercent = (profitLoss / initialInvestment) * 100;

        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("          PORTFOLIO PERFORMANCE");
        System.out.println("  ==========================================");
        System.out.printf("  Initial Investment : $%,.2f%n", initialInvestment);
        System.out.printf("  Cash Balance       : $%,.2f%n", cashBalance);
        System.out.printf("  Stock Value        : $%,.2f%n", stockValue);
        System.out.printf("  Current Value      : $%,.2f%n", currentTotal);
        System.out.println("  ------------------------------------------");

        if (profitLoss >= 0) {
            System.out.printf("  Profit             : $%,.2f%n", profitLoss);
        } else {
            System.out.printf("  Loss               : -$%,.2f%n", Math.abs(profitLoss));
        }

        System.out.printf("  Return             : %.2f%%%n", profitLossPercent);
        System.out.println("  ==========================================");
    }
}
