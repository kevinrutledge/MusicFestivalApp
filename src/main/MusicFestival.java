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
    private static LinkedList<Customer> customers = new LinkedList<>();
    private static Heap<Order> shippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator()); // Heap for
                                                                                                        // shipped
                                                                                                        // orders.
    private static Heap<Order> unshippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator()); // Heap for
                                                                                                          // unshipped
                                                                                                          // orders.
    private static final NameComparator NAME_COMPARATOR = new NameComparator();

    public static void main(String[] args) throws IOException {
        // Create a Scanner object to read from the console
        Scanner scanner = new Scanner(System.in);

        // Load data from files
        DataLoader.populateFestivals(festivalsByName, festivalsByStartDateCity);
        DataLoader.populateUsers(users, employees, customers);
        DataLoader.authenticateUsers(scanner);
        DataLoader.populateOrders(shippedOrders, unshippedOrders, festivalsByName, customers);

        System.out.println("Welcome to MusicFestivalApp\n");
        // login(scanner) returns a user object which can be an employee or customer
        User user = login(scanner);
        // determines if user is a customer or an employee and gives menu options
        if (!user.getIsEmployee()) {
            customerMenu(scanner, (Customer) user); // gives customer menu
        } else if (!((Employee) user).getIsManager()) {
            // gives employee menu
        } else {
            // gives manager menu
        }
        System.out.println("Thank you for using MusicFestivalApp\n");
    }

    public static User login(Scanner scanner) {
        boolean loggedin = false;
        int loginChoice;
        String firstName, lastName, email, password, address, city, state, zip;
        User user = null;
        // loops until user is logged in
        do {
            System.out.print(
                    "Enter 1 to log in as a customer, 2 to create a new account, 3 to log in as guest, 4 to log in as employee, 5 to log in as manager: ");
            loginChoice = scanner.nextInt();
            scanner.nextLine();
            switch (loginChoice) {
                case 1: // log in as customer
                    System.out.println("Logging in as customer");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    User placeholderUser = new Customer(email);
                    user = users.get(placeholderUser);
                    if (user != null && user.passwordMatch(password)) {
                        System.out.printf("Welcome %s %s, ", user.getFirstName(), user.getLastName());
                        loggedin = true;
                        return user;
                    } else {
                        System.out.println("Invalid email password combination");
                        user = null;
                    }
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
                    customers.addLast((Customer) user);
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
                        writer.write(zip + "\n" + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Welcome %s %s, ", user.getFirstName(), user.getLastName());
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
                    System.out.printf("Welcome employee %s %s, ", user.getFirstName(), user.getLastName());
                    loggedin = true;
                    return user;
                }
                System.out.println("Invalid email password combination");

                break;
                case 5: // log in as manager
                    System.out.println("Logging in as manager");
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    password = scanner.nextLine();
                    user = new Manager(email, password);
                    user = employees.get(user);
                    if (user != null) {
                        System.out.printf("Welcome manager %s %s, ", user.getFirstName(), user.getLastName());
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
        int menuChoice, shippingChoice;
        Order order;
        String line;
        do { // loops until quit

            System.out.print(
                    "Enter 1 to search for a festival, 2 to display festivals, 3 to place order, 4 to view purchases, 5 to quit: ");
            menuChoice = scanner.nextInt();
            System.out.println();
            scanner.nextLine();
            switch (menuChoice) {
                case 1: // search by name or date + city
                    FestivalUi.searchFestival(scanner, festivalsByName, festivalsByStartDateCity);
                    break;
                case 2: // display festivals
                    FestivalUi.displayFestival(scanner, festivalsByName, festivalsByStartDateCity);
                    break;
                case 3: // place order for non-guests only
                    if (((User) user).getEmail().equals("guest@email.com")) {
                        System.out.println("Ordering is not available to guests");
                        break;
                    }
                    // display festivals by name
                    FestivalUi.displayFestival(scanner, festivalsByName, festivalsByStartDateCity);
                    // input by name
                    LinkedList<Festival> orders = new LinkedList<>(); // stores festivals to order
                    do {
                        System.out.print("Enter name of festival to place an order or type NEXT: ");
                        line = scanner.nextLine();
                        if (line.equals("NEXT")) {
                            System.out.println();
                            break;
                        }
                        Festival searchFestival = new Festival(line);
                        Festival toOrder = festivalsByName.search(searchFestival, NAME_COMPARATOR);
                        if (toOrder == null) {
                            System.out.println("Invalid name");
                            break;
                        } else {
                            System.out.println("Festival details:");
                            System.out.println(toOrder.toString());
                            orders.addLast(toOrder); // adds festival to orders
                        }
                    } while (!line.equals("EXIT"));
                    // select shipping and create new order object
                    System.out.println("1. Standard shipping ");
                    System.out.println("2. Rush shipping  ");
                    System.out.println("3. Overnight shipping ");
                    System.out.println("4. Digital shipping ");
                    System.out.print("Enter value for shipping: ");
                    shippingChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (shippingChoice) {
                        case 1:
                            System.out.println("Standard shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), "3-28-2024", orders,
                                    Order.ShippingSpeed.STANDARD, false);
                            break;
                        case 2:
                            System.out.println("Rush shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), "3-28-2024", orders,
                                    Order.ShippingSpeed.RUSH, false);
                            break;
                        case 3:
                            System.out.println("Overnight shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), "3-28-2024", orders,
                                    Order.ShippingSpeed.OVERNIGHT, false);
                            break;
                        case 4:
                            System.out.println("Digital shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), "3-28-2024", orders,
                                    Order.ShippingSpeed.DIGITAL, false);
                            break;
                        default:
                            System.out.println("Invalid input, Standard shipping selected");
                            shippingChoice = 4;
                            order = new Order(user.getFirstName(), user.getLastName(), "3-28-2024", orders,
                                    Order.ShippingSpeed.STANDARD, false);
                            break;
                    }
                    // place a new order
                    order.setOrderID("" + Order.generateOrderID());
                    user.addUnshippedOrder(order);
                    unshippedOrders.insert(order);
                    // display details of order
                    System.out.println("Order placed for:");
                    System.out.println(orders.toString());
                    // write new order to file
                    try (FileWriter writer = new FileWriter("orders.txt", true)) {
                        writer.write(user.getFirstName() + " " + user.getLastName() + "\n");
                        writer.write("3-28-2024\n");
                        writer.write(orders.getLength() + "\n");
                        orders.positionIterator();
                        for (int i = 0; i < orders.getLength(); i++) {
                            orders.advanceIteratorToIndex(i);
                            writer.write(orders.getIterator().getName() + "\n");
                        }
                        writer.write("false\n");
                        writer.write(shippingChoice + "\n" + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4: // view purchase for non-guests only
                    if (((User) user).getEmail().equals("guest@email.com")) {
                        System.out.println("Ordering is not available to guests");
                        break;
                    }
                    // prints shipped then unshipped orders
                    System.out.println("Shipped Orders:");
                    System.out.print(user.getShippedOrders());
                    System.out.println("Unshipped Orders:");
                    System.out.print(user.getUnshippedOrders());
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

    /**
     * Author: Nelson Ngo
     */
    public static void employeeMenu(Scanner scanner, Employee user) {
        boolean quit = false;
        int menuChoice;
        do {
            System.out.print(
                    "Enter 1 to Search for an Order by ID, 2 to Search by Customer Name, " +
                            "3 to View Order with Highest Priority, 4 to View All Orders Sorted by Priority, " +
                            "5 to Ship an Order, 6 to Quit and Write to a File: ");
            menuChoice = scanner.nextInt();
            scanner.nextLine();
            switch (menuChoice) {
                case 1: // Search for an Order by ID
                    System.out.print("Enter the order ID to search: ");
                    String orderID = scanner.nextLine();
                    // Search shipped orders
                    Order foundOrder = null;
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        if (shippedOrders.getElement(i).getOrderID().equals(orderID)) {
                            foundOrder = shippedOrders.getElement(i);
                            break;
                        }
                    }
                    // Search unshipped orders if not found
                    if (foundOrder == null) {
                        for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                            if (unshippedOrders.getElement(i).getOrderID().equals(orderID)) {
                                foundOrder = unshippedOrders.getElement(i);
                                break;
                            }
                        }
                    }
                    if (foundOrder != null) {
                        System.out.println("Order found: " + foundOrder);
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 2: // Search by Customer Name
                    System.out.print("Enter the customer's first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter the customer's last name: ");
                    String lastName = scanner.nextLine();
                    boolean found = false;
                    // Search shipped orders
                    List<Order> matchingOrders = new ArrayList<>();
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        Order order = shippedOrders.getElement(i);
                        if (order.getFirstName().equals(firstName) && order.getLastName().equals(lastName)) {
                            matchingOrders.add(order);
                        }
                    }
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        Order order = unshippedOrders.getElement(i);
                        if (order.getFirstName().equals(firstName) && order.getLastName().equals(lastName)) {
                            matchingOrders.add(order);
                        }
                    }
                    if (matchingOrders.isEmpty()) {
                        System.out.println("No orders found for the customer.");
                    } else {
                        System.out.println("Found orders for the customer:");
                        for (Order order : matchingOrders) {
                            System.out.println(order);
                        }
                    }
                    break;
                case 3: // View Order with Highest Priority
                    Order highestPriorityOrder = null;
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        Order order = shippedOrders.getElement(i);
                        if (highestPriorityOrder == null || new PriorityComparator().compare(order, highestPriorityOrder) < 0) {
                            highestPriorityOrder = order;
                        }
                    }
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        Order order = unshippedOrders.getElement(i);
                        if (highestPriorityOrder == null || new PriorityComparator().compare(order, highestPriorityOrder) < 0) {
                            highestPriorityOrder = order;
                        }
                    }
                    if (highestPriorityOrder != null) {
                        System.out.println("Order with highest priority: " + highestPriorityOrder);
                    } else {
                        System.out.println("No orders found.");
                    }
                    break;
                case 4: // View All Orders Sorted by Priority
                    List<Order> allOrders = new ArrayList<>();
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        allOrders.add(shippedOrders.getElement(i));
                    }
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        allOrders.add(unshippedOrders.getElement(i));
                    }
                    Collections.sort(allOrders, new PriorityComparator());
                    System.out.println("All Orders Sorted by Priority:");
                    for (Order order : allOrders) {
                        System.out.println(order);
                    }
                    break;
                case 5: // Ship an Order
                    System.out.println("Enter the order ID to ship: ");
                    String orderID1 = scanner.nextLine();
                    boolean orderShipped = false;
                    // Check and update shipped orders
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        Order order = shippedOrders.getElement(i);
                        if (order.getOrderID().equalsIgnoreCase(orderID1)) {
                            order.setIsShipped(true);
                            orderShipped = true;
                            System.out.println("Order already shipped.");
                        }
                    }
                    // Check and update unshipped orders
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        Order order = unshippedOrders.getElement(i);
                        if (order.getOrderID().equalsIgnoreCase(orderID1)) {
                            order.setIsShipped(true);
                            orderShipped = true;
                            System.out.println("Order shipped successfully.");
                        }
                    }
                    // no orders
                    if (!orderShipped) {
                        System.out.println("Order not found.");
                    }
                    break;
                case 6: // Quit and Write to a File
                    // Code to write to file goes here
                    System.out.println("Writing to file and quitting...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!quit);
    }
}