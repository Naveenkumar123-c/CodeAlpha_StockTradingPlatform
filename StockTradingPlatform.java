import java.util.Scanner;

class Stock {
    String name;
    double price;
    int available;

    Stock(String name, double price, int available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock[] stocks = {
                new Stock("TCS", 3500, 100),
                new Stock("Infosys", 1500, 100),
                new Stock("Reliance", 2800, 100)
        };

        int tcsOwned = 0, infosysOwned = 0, relianceOwned = 0;
        double balance = 100000;

        while (true) {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("Balance: ₹" + balance);
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks");
                    for (int i = 0; i < stocks.length; i++) {
                        System.out.println((i + 1) + ". " + stocks[i].name
                                + "  Price: Rs.1" + stocks[i].price
                                + "  Available: " + stocks[i].available);
                    }
                    break;

                case 2:
                    System.out.print("Enter Stock Number: ");
                    int buy = sc.nextInt();

                    if (buy >= 1 && buy <= 3) {
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        double cost = qty * stocks[buy - 1].price;

                        if (qty <= stocks[buy - 1].available && cost <= balance) {
                            balance -= cost;
                            stocks[buy - 1].available -= qty;

                            if (buy == 1)
                                tcsOwned += qty;
                            else if (buy == 2)
                                infosysOwned += qty;
                            else
                                relianceOwned += qty;

                            System.out.println("Stock Purchased Successfully.");
                        } else {
                            System.out.println("Insufficient Balance or Stock.");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Stock Number: ");
                    int sell = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();

                    if (sell == 1 && sellQty <= tcsOwned) {
                        tcsOwned -= sellQty;
                        balance += sellQty * stocks[0].price;
                        stocks[0].available += sellQty;
                        System.out.println("Stock Sold Successfully.");
                    } else if (sell == 2 && sellQty <= infosysOwned) {
                        infosysOwned -= sellQty;
                        balance += sellQty * stocks[1].price;
                        stocks[1].available += sellQty;
                        System.out.println("Stock Sold Successfully.");
                    } else if (sell == 3 && sellQty <= relianceOwned) {
                        relianceOwned -= sellQty;
                        balance += sellQty * stocks[2].price;
                        stocks[2].available += sellQty;
                        System.out.println("Stock Sold Successfully.");
                    } else {
                        System.out.println("You don't own enough shares.");
                    }
                    break;

                case 4:
                    System.out.println("\n===== Portfolio =====");
                    System.out.println("TCS Shares      : " + tcsOwned);
                    System.out.println("Infosys Shares  : " + infosysOwned);
                    System.out.println("Reliance Shares : " + relianceOwned);
                    System.out.println("Balance         : Rs.1" + balance);
                    break;

                case 5:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}