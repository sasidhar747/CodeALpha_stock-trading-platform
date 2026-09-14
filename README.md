# 📈 Stock Trading Platform

> **CodeAlpha Java Programming Internship — Task 2**

A console-based stock trading simulation built entirely in Java. This application allows users to view market data, buy and sell stocks, manage a portfolio, track performance, and review transaction history — all using virtual money.

---

## 📋 Project Description

This is an **educational stock trading simulator** designed as part of the CodeAlpha Java Programming Internship. It demonstrates core Java programming and Object-Oriented Programming concepts through a realistic, menu-driven console application.

> ⚠️ **Disclaimer:** This is a simulation only. No real money, real stock market data, or external APIs are used. All stock prices are predefined and virtual.

---

## 🎯 Objective

Create a basic stock trading simulation where a user can:
- View real-time (simulated) market data
- Buy and sell stocks with virtual currency
- Manage and track their portfolio
- Monitor profit/loss and portfolio performance
- Review complete transaction history

---

## ✨ Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | **Market Data** | View 8 predefined stocks with symbol, company name, and price |
| 2 | **Buy Stocks** | Purchase shares with input validation and balance checking |
| 3 | **Sell Stocks** | Sell owned shares with quantity validation |
| 4 | **Portfolio View** | Detailed breakdown of holdings with current values |
| 5 | **Performance Tracking** | View profit/loss and return percentage |
| 6 | **Transaction History** | Complete log of all buy/sell transactions with timestamps |
| 7 | **Account Balance** | Check current cash balance at any time |
| 8 | **Input Validation** | Robust error handling for all user inputs |

---

## 🛠️ Technologies Used

- **Language:** Java (JDK 11+)
- **IDE:** Any Java-compatible IDE or terminal
- **Build:** Manual compilation with `javac`
- **No external libraries or dependencies**

---

## 📚 Java / OOP Concepts Used

| Concept | Where Used |
|---------|------------|
| **Classes & Objects** | `Stock`, `User`, `Portfolio`, `Transaction`, `StockTradingPlatform` |
| **Encapsulation** | Private fields with public getters/setters in all classes |
| **Constructors** | Parameterized constructors in every class |
| **Methods** | Well-organized methods for each operation |
| **ArrayList** | Used in `StockTradingPlatform` for transaction history |
| **HashMap** | Used in `Portfolio` (holdings) and `StockTradingPlatform` (market stocks) |
| **Loops** | `while` loop for menu, `for-each` loops for displaying data |
| **Conditionals** | Input validation, buy/sell logic, profit/loss calculation |
| **Exception Handling** | `try-catch` for `NumberFormatException` on user input |
| **Scanner** | Reading user input from console |
| **String Formatting** | `System.out.printf()` and `String.format()` throughout |
| **Java Time API** | `LocalDateTime` and `DateTimeFormatter` in `Transaction` |
| **Composition** | `User` has a `Portfolio`, `StockTradingPlatform` has a `User` |

---

## 📁 Project Structure

```
StockTradingPlatform/
├── src/
│   ├── Main.java                  # Entry point with menu-driven UI
│   ├── Stock.java                 # Stock entity (symbol, name, price)
│   ├── User.java                  # User account (name, balance, portfolio)
│   ├── Portfolio.java             # Portfolio management (holdings, value)
│   ├── Transaction.java           # Transaction record (buy/sell log)
│   └── StockTradingPlatform.java  # Platform engine (market, operations)
├── README.md                      # Project documentation
└── .gitignore                     # Git ignore rules
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 11 or later installed
- Terminal / Command Prompt access

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sasidhar747/CodeALpha_stock-trading-platform.git
   cd CodeALpha_stock-trading-platform
   ```

2. **Compile all Java files:**
   ```bash
   cd src
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java Main
   ```

---

## 📸 Sample Console Output

```
  ╔══════════════════════════════════════════════╗
  ║                                              ║
  ║       STOCK TRADING PLATFORM                 ║
  ║       CodeAlpha Internship - Task 2          ║
  ║                                              ║
  ║       Educational Simulation Only            ║
  ║       No Real Money Involved                 ║
  ║                                              ║
  ╚══════════════════════════════════════════════╝

  Enter your name: John

  Welcome, John! You start with $10,000.00 in virtual cash.
  Happy trading!

  ============================================
         STOCK TRADING PLATFORM - MENU
  ============================================
    1. View Market Data
    2. Buy Stock
    3. Sell Stock
    4. View Portfolio
    5. View Portfolio Performance
    6. View Transaction History
    7. View Account Balance
    8. Exit
  ============================================
  Enter your choice: 1

  ================================================
                  STOCK MARKET
  ================================================
  Symbol     Company                     Price
  --------------------------------------------
  AAPL       Apple Inc.              $   180.00
  GOOGL      Alphabet Inc.           $   150.00
  MSFT       Microsoft               $   420.00
  AMZN       Amazon                  $   180.00
  TSLA       Tesla                   $   250.00
  META       Meta Platforms          $   480.00
  NFLX       Netflix                 $   620.00
  NVDA       NVIDIA                  $   870.00
  ================================================

  Enter your choice: 2

  ---------- BUY STOCK ----------
  Available Cash: $10,000.00

  Enter stock symbol: AAPL
  Enter quantity: 5

  ✓ Purchase Successful!
  Bought 5 shares of AAPL (Apple Inc.)
  Price per share : $180.00
  Total Cost      : $900.00
  Remaining Cash  : $9,100.00

  Enter your choice: 4

  ================================================
                  MY PORTFOLIO
  ================================================

  Symbol     Company                  Quantity       Price          Value
  --------------------------------------------------------------------
  AAPL       Apple Inc.                    5   $   180.00   $      900.00
  --------------------------------------------------------------------
  Total Stock Value: $900.00

  Cash Balance       : $9,100.00
  Total Stock Value  : $900.00
  Total Portfolio    : $10,000.00
  ================================================
```

---

## 🔮 Future Enhancements

- 📈 **Dynamic Stock Prices** — Simulate random price fluctuations
- 💾 **File I/O** — Save and load portfolio and transactions from files
- 📊 **Stock Price History** — Track price changes over sessions
- 🏆 **Leaderboard** — Compare performance across multiple users
- 📱 **GUI Version** — Build a JavaFX or Swing desktop interface
- 🔐 **User Authentication** — Login system with password protection
- 📉 **Short Selling** — Allow selling stocks not owned (advanced)
- 🧪 **Unit Tests** — Add JUnit test coverage

---

## ❓ Viva Questions and Answers

**Q1: What is encapsulation and where is it used?**
> Encapsulation is bundling data (fields) and methods that operate on that data within a single class, hiding internal details. In this project, all fields are `private` with public getters/setters (e.g., `Stock.java`, `User.java`).

**Q2: Why did you use HashMap instead of ArrayList for the portfolio?**
> HashMap provides O(1) lookup by stock symbol, making it efficient to check holdings and update quantities. ArrayList would require linear search O(n) to find a specific stock.

**Q3: What is the difference between composition and inheritance?**
> Composition is a "has-a" relationship (User HAS a Portfolio). Inheritance is an "is-a" relationship. This project uses composition — `User` contains a `Portfolio` object, and `StockTradingPlatform` contains a `User` and market data.

**Q4: How do you handle invalid user input?**
> We use `try-catch` blocks to catch `NumberFormatException` when parsing integers, validate stock symbols against the market HashMap, check for positive quantities, and verify sufficient cash/shares before transactions.

**Q5: Explain the project architecture.**
> The project follows a layered design: `Stock` is the data entity, `Portfolio` manages holdings, `User` represents the trader, `StockTradingPlatform` is the business logic engine, and `Main` handles user interaction. Each class has a single responsibility.

**Q6: What Java collections did you use and why?**
> `HashMap<String, Stock>` for market data (fast symbol lookup), `HashMap<String, Integer>` for portfolio holdings (fast quantity lookup), and `ArrayList<Transaction>` for transaction history (ordered, sequential access).

**Q7: What is the purpose of the Transaction class?**
> It records every buy/sell operation with details like type, stock symbol, quantity, price, total amount, and timestamp. This creates an audit trail for the user's trading history.

**Q8: How would you add dynamic stock prices?**
> Use `java.util.Random` to periodically adjust prices by a small percentage (e.g., ±5%) using a method like `simulatePriceChange()` in the `StockTradingPlatform` class.

**Q9: What is the difference between `printf` and `println`?**
> `println` prints a string followed by a newline. `printf` allows formatted output with format specifiers like `%s` (string), `%d` (integer), `%.2f` (decimal with 2 places), making it ideal for aligned table displays.

**Q10: Could this project be extended to use a database?**
> Yes, JDBC could be used to connect to MySQL or SQLite to persist users, portfolios, transactions, and stock data. The current in-memory design would be replaced with database queries.

---

## 👥 Contributors

| Name | Role |
|------|------|
| **Gamini Sasidhar Sai Varma** | Developer — CodeAlpha Java Internship |

---

## 📝 LinkedIn Post

> 🚀 Excited to share my latest project from the **CodeAlpha Java Programming Internship**!
>
> 📈 **Stock Trading Platform** — A console-based stock trading simulator built entirely in Java.
>
> 🔑 Key Features:
> • View simulated market data for 8 major stocks
> • Buy & sell stocks with real-time portfolio tracking
> • Monitor profit/loss and portfolio performance
> • Complete transaction history with timestamps
> • Robust input validation & error handling
>
> 💡 Built using core OOP principles: Encapsulation, Composition, HashMap, ArrayList, Java Time API, and clean separation of concerns across 6 classes.
>
> This project strengthened my understanding of Java fundamentals and object-oriented design patterns.
>
> 🔗 GitHub: github.com/sasidhar747/CodeALpha_stock-trading-platform
>
> #Java #OOP #CodeAlpha #Internship #StockTrading #Programming #SoftwareDevelopment

---

## 📄 License

This project is for educational purposes as part of the CodeAlpha Java Programming Internship.

---

**Recommended GitHub Repository Name:** `CodeALpha_stock-trading-platform`
