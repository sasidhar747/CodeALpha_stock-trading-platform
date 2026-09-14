import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction.java
 * Represents a single buy or sell transaction.
 * Records the type, stock, quantity, price, total amount, and timestamp.
 * 
 * @author CodeAlpha Intern
 */
public class Transaction {

    private String transactionType; // "BUY" or "SELL"
    private String stockSymbol;
    private String companyName;
    private int quantity;
    private double pricePerShare;
    private double totalAmount;
    private LocalDateTime dateTime;

    /**
     * Constructor to create a new Transaction.
     * 
     * @param transactionType "BUY" or "SELL"
     * @param stockSymbol     The stock ticker symbol
     * @param companyName     The full company name
     * @param quantity        Number of shares traded
     * @param pricePerShare   Price per share at the time of transaction
     */
    public Transaction(String transactionType, String stockSymbol, String companyName,
                       int quantity, double pricePerShare) {
        this.transactionType = transactionType;
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.totalAmount = quantity * pricePerShare;
        this.dateTime = LocalDateTime.now();
    }

    // ==================== Getters ====================

    public String getTransactionType() {
        return transactionType;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // ==================== Display ====================

    /**
     * Displays the transaction in a formatted line.
     */
    public void displayTransaction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.printf("%-8s %-8s %5d     $%10.2f   $%10.2f   %s%n",
                transactionType, stockSymbol, quantity, pricePerShare, totalAmount,
                dateTime.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("%s %d shares of %s at $%.2f (Total: $%.2f)",
                transactionType, quantity, stockSymbol, pricePerShare, totalAmount);
    }
}
