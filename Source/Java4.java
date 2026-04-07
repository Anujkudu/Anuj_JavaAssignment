import java.io.*;
import java.util.Scanner;

// this is creating my own exception 
class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) {
        super(msg);
    }
}

public class Java4 {

    static Scanner sc = new Scanner(System.in);

    // Create Account
    static void createAccount() {
        try {
            System.out.print("Enter Customer ID (1-20): ");
            int cid = sc.nextInt();

            if (cid < 1 || cid > 20) {
                throw new Exception("Invalid Customer ID (must be 1-20)");
            }

            System.out.print("Enter Customer Name: ");
            String cname = sc.next();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be positive");
            }

            if (amount < 1000) {
                throw new Exception("Minimum balance should be 1000");
            }

            // File Writing
            FileWriter fw = new FileWriter("customer.txt", true);
            fw.write(cid + " " + cname + " " + amount + "\n");
            fw.close();

            System.out.println("Account Created Successfully!");

        } catch (InvalidAmountException e) {
            System.out.println("Custom Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Withdraw Amount
    static void withdraw() {
        try {
            System.out.print("Enter Current Balance: ");
            double balance = sc.nextDouble();

            System.out.print("Enter Withdrawal Amount: ");
            double wamt = sc.nextDouble();

            if (wamt > balance) {
                throw new Exception("Insufficient Balance");
            }

            balance = balance - wamt;
            System.out.println("Remaining Balance: " + balance);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 3);

        sc.close();
    }
}
