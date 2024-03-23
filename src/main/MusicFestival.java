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
        User user = login(scanner);
        // determines if user is a customer or an employee
        int userType;
        if (!user.isEmployee) {
            userType = 1;
        } else {
            userType = 2;
        }
    }

    public static User login(Scanner scanner) {
        boolean loggedin = false;
        int loginChoice;
        String first, last, email, password, address, city, state, zip;
        User user;
        // loops until user is logged in
        do {
            System.out.print(
                    "Enter 1 to log in as a customer, 2 to create a new account, 3 to log in as guest, 4 to log in as employee: ");
            loginChoice = scanner.nextInt();
            scanner.nextLine();
            switch (loginChoice) {
                case 1: // log in as customer
                    System.out.println("Logging in as customer");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    user = new Customer(email, password);
                    user = users.get(user);
                    if (user != null) {
                        System.out.printf("Welcome %s %s", user.getFirstName(), user.getLastName());
                        loggedin = true;
                        return user;
                    }
                    System.out.println("Invalid email password combination");

                    break;
                case 2: // create new account
                    System.out.println("Creating new account");
                    System.out.print("Enter your first name: ");
                    first = scanner.nextLine();
                    System.out.print("Enter your last name: ");
                    last = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    address = scanner.nextLine();
                    System.out.print("Enter your city: ");
                    city = scanner.nextLine();
                    System.out.print("Enter your state: ");
                    state = scanner.nextLine();
                    System.out.print("Enter your zip: ");
                    zip = scanner.nextLine();
                    user = new Customer(first, last, email, password, false, address, city, state, zip);
                    // adds new account to hashtable
                    users.add(user);
                    // writes new account to users.txt
                    try (FileWriter writer = new FileWriter("users.txt", true)) {
                        writer.write(user.getFirstName() + " " + user.getLastName() + "\n");
                        writer.write(user.getEmail() + "\n");
                        writer.write(user.getPassword() + "\n");
                        writer.write("false\n");
                        writer.write(((Customer) user).getAddress() + "\n");
                        writer.write(((Customer) user).getCity() + "\n");
                        writer.write(((Customer) user).getState() + "\n");
                        writer.write(((Customer) user).getZip() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Welcome %s %s", user.getFirstName(), user.getLastName());
                    loggedin = true;
                    return user;
                case 3: // log in as guest
                    System.out.println("Logging in as guest");
                    user = new Customer("guest@email.com");
                    System.out.println("Welcome Guest");
                    return user;

                case 4: // log in as employee
                    System.out.println("Logging in as employee");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    user = new Employee(email, password);
                    user = employees.get(user);
                    if (user != null) {
                        System.out.printf("Welcome employee %s %s", user.getFirstName(), user.getLastName());
                        loggedin = true;
                        return user;
                    }
                    System.out.println("Invalid email password combination");

                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!loggedin);
        // same as case 3, put here to get rid of error of no return value
        System.out.println("Logging in as guest");
        user = new Customer("guest@email.com");
        System.out.println("Welcome Guest");
        return user;

    }

    // Method stubs for the functionalities mentioned in the project prompt
    private static void mergeSort() {
        // Implement merging sort if necessary
    }
}
