/**
 * Stock.java
 * Represents a stock in the market with symbol, company name, and current price.
 * 
 * @author CodeAlpha Intern
 */
public class Stock {

    private String symbol;
    private String companyName;
    private double currentPrice;

    /**
     * Constructor to create a new Stock.
     * 
     * @param symbol      The stock ticker symbol (e.g., AAPL)
     * @param companyName The full company name
     * @param currentPrice The current market price per share
     */
    public Stock(String symbol, String companyName, double currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    // ==================== Getters ====================

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    // ==================== Setters ====================

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    // ==================== Display ====================

    /**
     * Displays stock information in a formatted line.
     */
    public void displayInfo() {
        System.out.printf("%-10s %-22s $%.2f%n", symbol, companyName, currentPrice);
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", symbol, companyName, currentPrice);
    }
}
