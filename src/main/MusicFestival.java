package main;

import java.io.*;
import java.util.*;

import main.Order.ShippingSpeed;

public class MusicFestival {
    // Static data structures for the application
    private static BST<Festival> festivalsByName = new BST<>(); // BST for primary key.
    private static BST<Festival> festivalsByStartDateCity = new BST<>(); // BST for secondary key.
    private static HashTable<User> users = new HashTable<>(100); // HashTable for all users.
    private static HashTable<User> employees = new HashTable<>(100); // HashTable for employees
    // Used to add orders to Customers (since orders are stored by first+last name)
    // Can also be used by Employees to search for orders by name.
    // 1. Find customer by name
    // 2. Get their shipped / unshipped orders.
    // Can also be used when shipping an order since the Customer's orders need to be updated here
    // Order contains the customer name, so we can find the Customer here based on that name
    // and call the shipOrder method to move it from one list to the other.
    private static BST<Customer> customerByName = new BST<>(new Customer[]{}, new UserNameComparator<>());
    private static Heap<Order> shippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator()); // Heap for
                                                                                                        // shipped
                                                                                                        // orders.
    private static Heap<Order> unshippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator()); // Heap for
                                                                                                          // unshipped
                                                                                                          // orders.

    public static void main(String[] args) throws IOException {
        // Create a Scanner object to read from the console
        Scanner scanner = new Scanner(System.in);

        // Load data from files
        DataLoader.populateFestivals(festivalsByName, festivalsByStartDateCity);
        DataLoader.populateUsers(users, employees, customerByName);
        DataLoader.authenticateUsers(scanner);
        DataLoader.populateOrders(shippedOrders, unshippedOrders, festivalsByName, customerByName);

        System.out.println("Welcome to MusicFestivalApp\n");
        // log in, login(scanner) returns a
        User user = login(scanner);
        // determines if user is a customer or an employee and gives appropriate menu
        // options
        if (!user.getIsEmployee()) {
            customerMenu(scanner, (Customer) user); // gives customer menu
        } else if (!((Employee) user).getIsManager()) {
            // gives employee menu
        } else {
            // gives manager menu
        }
    }

    public static User login(Scanner scanner) {
        boolean loggedin = false;
        int loginChoice;
        String firstName, lastName, email, password, address, city, state, zip;
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
                        System.out.printf("Welcome %s %s\n", user.getFirstName(), user.getLastName());
                        loggedin = true;
                        return user;
                    }
                    System.out.println("Invalid email password combination");

                    break;
                case 2: // create new account
                    System.out.println("Creating new account");
                    System.out.print("Enter your first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter your last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    address = scanner.nextLine();
                    System.out.print("Enter your city: ");
                    city = scanner.nextLine();
                    System.out.print("Enter your state initials: ");
                    state = scanner.nextLine();
                    System.out.print("Enter your zip: ");
                    zip = scanner.nextLine();
                    user = new Customer(firstName, lastName, email, password, false, address, city, state, zip);
                    // adds new account to hashtable
                    users.add(user);
                    customerByName.insert((Customer) user, new UserNameComparator<>());
                    // writes new account to users.txt
                    try (FileWriter writer = new FileWriter("users.txt", true)) {
                        writer.write("\n");
                        writer.write(firstName + " " + lastName + "\n");
                        writer.write(email + "\n");
                        writer.write(password + "\n");
                        writer.write("false\n");
                        writer.write(address + "\n");
                        writer.write(city + "\n");
                        writer.write(state + "\n");
                        writer.write(zip + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Welcome %s %s\n", user.getFirstName(), user.getLastName());
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

    public static void customerMenu(Scanner scanner, Customer user) {
        boolean quit = false;
        int menuChoice;
        String line;
        do { // loops until quit

            System.out.print(
                    "Enter 1 to search by name, 2 to search by date + city, 3 to place order, 4 to view purchases, 5 to quit: ");
            menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1: // search by name
                    System.out.print("Enter name to search by: ");
                    line = scanner.nextLine();
                    break;
                case 2: // search by date + city
                    System.out.print("Enter date + city(example here) to search by: ");
                    line = scanner.nextLine();
                    break;
                case 3: // place order for non-guests only
                    if (((User) user).getEmail() == "guest@email.com") {
                        System.out.println("Ordering is not available to guests");
                        break;
                    }
                    // display festivals by name
                    // input by name
                    // place an order of new Order(String orderID, String emailAddress, String
                    // datePurchased, LinkedList<Festival> orderContents, ShippingSpeed
                    // shippingSpeed, boolean isShipped)
                    // customer.addUnshippedOrder(order); always unshipped because it just got
                    // ordered
                    // display details
                    break;
                case 4: // view purchase for non-guests only
                    if (((User) user).getEmail() == "guest@email.com") {
                        System.out.println("Ordering is not available to guests");
                        break;
                    }
                    // prints shipped then unshipped orders
                    System.out.println("Shipped Orders:");
                    user.getShippedOrderByCustomerName();
                    System.out.println("Unshipped Orders:");
                    user.getShippedOrderByCustomerName();
                    break;
                case 5: // quit
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!quit);
    }
}