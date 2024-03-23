package main;

import java.io.*;
import java.util.*;

public class MusicFestival {
    // Static data structures for the application
    private static BST<Festival> festivalsByName = new BST<>(); // BST for primary key.
    private static BST<Festival> festivalsByStartDateCity = new BST<>(); // BST for secondary key.
    private static HashTable<User> users = new HashTable<>(100); // HashTable for all users.
    private static HashTable<User> employees = new HashTable<>(100); // HashTable for employees
    private static Heap<Order> shippedOrders; // Heap for shipped orders.
    private static Heap<Order> unshippedOrders; // Heap for unshipped orders.

    public static void main(String[] args) throws IOException {
        // Create a Scanner object to read from the console
        Scanner scanner = new Scanner(System.in);

        // Load data from files
        mergeSort();
        DataLoader.populateFestivals(festivalsByName, festivalsByStartDateCity);
        DataLoader.populateUsers(users, employees);
        DataLoader.authenticateUsers(scanner);
        DataLoader.populateOrders(scanner);

        System.out.println("Welcome to MusicFestivalApp\n");
        // log in
        boolean loggedin = false;
        int loginChoice = -1;
        do {
            System.out.print(
                    "Enter 1 to log in as a customer, 2 to create a new account, 3 to log in as guest, 4 to log in as employee: ");
            loginChoice = scanner.nextInt();
            scanner.nextLine();
            switch (loginChoice) {
                case 1: // log in as customer
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    Customer user = new Customer(email, password);
                    user = (Customer) users.get(user);
                    if (users.get(user) != null) {
                        System.out.printf("Welcome %s %s", user.getFirstName(), user.getLastName());
                        loggedin = true;
                    }
                    break;
                case 2: // create new account

                    break;

                case 3: // log in as guest

                    break;

                case 4: // log in as employee

                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!loggedin);
    }

    // Method stubs for the functionalities mentioned in the project prompt
    private static void mergeSort() {
        // Implement merging sort if necessary
    }
}
