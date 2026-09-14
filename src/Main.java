import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Stock Trading Platform application.
 * Provides a menu-driven console interface for the user.
 * 
 * CodeAlpha Java Programming Internship — Task 2
 * 
 * @author CodeAlpha Intern
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StockTradingPlatform platform;

    public static void main(String[] args) {

        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║                                              ║");
        System.out.println("  ║       STOCK TRADING PLATFORM                 ║");
        System.out.println("  ║       CodeAlpha Internship - Task 2          ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ║       Educational Simulation Only            ║");
        System.out.println("  ║       No Real Money Involved                 ║");
        System.out.println("  ║                                              ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println();

        // Get user name
        System.out.print("  Enter your name: ");
        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) {
            userName = "Trader";
        }

        // Initialize platform with $10,000 starting balance
        platform = new StockTradingPlatform(userName, 10000.00);

        System.out.printf("%n  Welcome, %s! You start with $10,000.00 in virtual cash.%n", userName);
        System.out.println("  Happy trading!");

        // Main loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    platform.displayMarketData();
                    break;
                case 2:
                    handleBuyStock();
                    break;
                case 3:
                    handleSellStock();
                    break;
                case 4:
                    platform.viewPortfolio();
                    break;
                case 5:
                    platform.viewPerformance();
                    break;
                case 6:
                    platform.displayTransactionHistory();
                    break;
                case 7:
                    platform.viewBalance();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("\n  Invalid choice. Please enter a number between 1 and 8.");
            }
        }

        // Exit message
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("    Thank you for using the Stock Trading Platform!");
        System.out.printf("    Final Cash Balance: $%,.2f%n", platform.getUser().getCashBalance());
        System.out.println("  ================================================");
        System.out.println();

        scanner.close();
    }

    // ==================== Menu ====================

    /**
     * Displays the main menu.
     */
    private static void displayMenu() {
        System.out.println();
        System.out.println("  ============================================");
        System.out.println("         STOCK TRADING PLATFORM - MENU");
        System.out.println("  ============================================");
        System.out.println("    1. View Market Data");
        System.out.println("    2. Buy Stock");
        System.out.println("    3. Sell Stock");
        System.out.println("    4. View Portfolio");
        System.out.println("    5. View Portfolio Performance");
        System.out.println("    6. View Transaction History");
        System.out.println("    7. View Account Balance");
        System.out.println("    8. Exit");
        System.out.println("  ============================================");
    }

    /**
     * Gets a valid menu choice from the user.
     * 
     * @return The user's menu choice (integer)
     */
    private static int getMenuChoice() {
        System.out.print("  Enter your choice: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }

    // ==================== Buy Handler ====================

    /**
     * Handles the buy stock interaction.
     */
    private static void handleBuyStock() {
        System.out.println();
        System.out.println("  ---------- BUY STOCK ----------");
        System.out.printf("  Available Cash: $%,.2f%n%n", platform.getUser().getCashBalance());

        // Get stock symbol
        System.out.print("  Enter stock symbol: ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        if (symbol.isEmpty()) {
            System.out.println("  ERROR: Stock symbol cannot be empty.");
            return;
        }

        // Get quantity
        System.out.print("  Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Please enter a valid number for quantity.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("  ERROR: Quantity must be greater than 0.");
            return;
        }

        // Execute buy
        String result = platform.buyStock(symbol, quantity);
        System.out.println(result);
    }

    // ==================== Sell Handler ====================

    /**
     * Handles the sell stock interaction.
     */
    private static void handleSellStock() {
        System.out.println();
        System.out.println("  ---------- SELL STOCK ----------");

        // Check if portfolio is empty
        if (platform.getUser().getPortfolio().isEmpty()) {
            System.out.println("  You don't own any stocks to sell.");
            return;
        }

        // Show current holdings
        System.out.println("  Your current holdings:");
        for (var entry : platform.getUser().getPortfolio().getHoldings().entrySet()) {
            System.out.printf("    %s: %d shares%n", entry.getKey(), entry.getValue());
        }
        System.out.println();

        // Get stock symbol
        System.out.print("  Enter stock symbol: ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        if (symbol.isEmpty()) {
            System.out.println("  ERROR: Stock symbol cannot be empty.");
            return;
        }

        // Get quantity
        System.out.print("  Enter quantity to sell: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Please enter a valid number for quantity.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("  ERROR: Quantity must be greater than 0.");
            return;
        }

        // Execute sell
        String result = platform.sellStock(symbol, quantity);
        System.out.println(result);
    }
}
