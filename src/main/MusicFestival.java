package main;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Main class for the Music Festival App, orchestrating user interactions,
 * data loading, and application logic.
 * It handles user authentication, displays menus based on user roles, and
 * allows for managing festivals, orders, and user information.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class MusicFestival {
    // Static data structures for the application
    private static BST<Festival> festivalsByName = new BST<>(); // BST for storing by name
    private static BST<Festival> festivalsByStartDateCity = new BST<>(); // BST for storing by start date and city
    private static HashTable<User> users = new HashTable<>(100); // HashTable for storing all users
    private static HashTable<User> employees = new HashTable<>(100); // HashTable for storing employees
    private static LinkedList<Customer> customers = new LinkedList<>(); // LinkedList for storing customer data
    private static Heap<Order> shippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator());
    private static Heap<Order> unshippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator());
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
        } else {
            employeeMenu(scanner, (Employee) user);
        }
        System.out.println("Thank you for using MusicFestivalApp\n");
    }

    /**
     * Handles user login and new account creation, returning an authenticated or new User object.
     *
     * @param scanner Scanner for reading user input from the console.
     * @return User The authenticated user or a new user object.
     */
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

    /**
     * Displays and handles the customer menu options allowing customers to interact with the application.
     *
     * @param scanner Scanner for reading user input from the console.
     * @param user The authenticated customer user.
     */
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
                            continue;
                        } else if(toOrder.getTicketsRemaining() == 0) {
                        	System.out.println("\n"+  toOrder.getName() + " is sold out. Please try again.\n");
                        	continue;
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
                    System.out.println();
                    scanner.nextLine();
                    switch (shippingChoice) {
                        case 1:
                            System.out.println("Standard shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), user.getEmail(),
                                    LocalDate.now().toString() , orders, Order.ShippingSpeed.STANDARD, false);
                            break;
                        case 2:
                            System.out.println("Rush shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), user.getEmail(),
                                    LocalDate.now().toString() , orders, Order.ShippingSpeed.RUSH, false);
                            break;
                        case 3:
                            System.out.println("Overnight shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), user.getEmail(),
                                    LocalDate.now().toString() , orders, Order.ShippingSpeed.OVERNIGHT, false);
                            break;
                        case 4:
                            System.out.println("Digital shipping selected");
                            order = new Order(user.getFirstName(), user.getLastName(), user.getEmail(),
                                    LocalDate.now().toString() , orders, Order.ShippingSpeed.DIGITAL, false);
                            break;
                        default:
                            System.out.println("Invalid input, Standard shipping selected");
                            shippingChoice = 4;
                            order = new Order(user.getFirstName(), user.getLastName(), user.getEmail(),
                                    LocalDate.now().toString() , orders, Order.ShippingSpeed.STANDARD, false);
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
     * Displays and handles the employee menu options, including manager-specific functionalities,
     * allowing employees and managers to interact with the application.
     *
     * @param scanner Scanner for reading user input from the console.
     * @param user The authenticated employee or manager user.
     */
    public static void employeeMenu(Scanner scanner, Employee user) {
        boolean quit = false;
        int menuChoice;
        do {
            System.out.println("Please enter your choice: ");
            System.out.println("1. Search for an Order by ID");
            System.out.println("2. Search by Customer Name");
            System.out.println("3. View Order with Highest Priority");
            System.out.println("4. View All Orders Sorted by Priority");
            System.out.println("5. Ship an Order");
            if (user.getIsManager()) {
                System.out.println("6. Add a Festival (Manager Only)");
                System.out.println("7. Update a Festival (Manager Only)");
                System.out.println("8. Remove a Festival (Manager Only)");    
            }
            System.out.println("9. Quit and Write to a File");
            System.out.print("Your Choice: ");

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
                    LinkedList<Order> foundOrders = new LinkedList<>();
                    LinkedList<Order> ordersTrueFlag = user.getOrdersByName(firstName, lastName, customers, true);
                    LinkedList<Order> ordersFalseFlag = null;
                    if (ordersTrueFlag != null) {
                        foundOrders = ordersTrueFlag;
                        System.out.print(foundOrders);
                    } else {
                        ordersFalseFlag = user.getOrdersByName(firstName, lastName, customers, false);
                        if (ordersFalseFlag != null) {
                            foundOrders = ordersFalseFlag;
                            System.out.print(foundOrders);
                        } else {
                            System.out.println("No orders found for the customer.");
                        }
                    }
                    break;
                case 3: // View Order with Highest Priority
                    Order highestPriorityOrder = null;
                    // TODO - should we only show unshipped orders?
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        Order order = shippedOrders.getElement(i);
                        if (highestPriorityOrder == null
                                || new PriorityComparator().compare(order, highestPriorityOrder) < 0) {
                            highestPriorityOrder = order;
                        }
                    }
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        Order order = unshippedOrders.getElement(i);
                        if (highestPriorityOrder == null
                                || new PriorityComparator().compare(order, highestPriorityOrder) < 0) {
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
                    ArrayList<Order> allOrders = new ArrayList<>();
                    // Add shipped orders to the list
                    // TODO - should we only show unshipped orders?
                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        allOrders.add(shippedOrders.getElement(i));
                    }
                    // Add unshipped orders to the list
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        allOrders.add(unshippedOrders.getElement(i));
                    }
                    // Sort the list using PriorityComparator
                    Collections.sort(allOrders, new PriorityComparator());
                    // Display the sorted orders
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
                            unshippedOrders.remove(i);
                            shippedOrders.insert(order);
                            String email = order.getEmail();
                            User placeholderCustomer = new Customer(email);
                            Customer customer = (Customer) users.get(placeholderCustomer);
                            if (customer != null) {
                                customer.shipOrder(order);
                            }
                            System.out.println("Order shipped successfully.");
                        }
                    }
                    // no orders
                    if (!orderShipped) {
                        System.out.println("Order not found.");
                    }
                    break;
                case 6:
                    FestivalUi.addFestival(scanner, festivalsByName, festivalsByStartDateCity, user);
                    break;
                case 7:
                    FestivalUi.updateFestival(scanner, festivalsByName, festivalsByStartDateCity, user);
                    break;
                case 8:
                    FestivalUi.removeFestival(scanner, festivalsByName, festivalsByStartDateCity, user);
                    break;
                case 9:
                    // Quit and Write to a File
                    // Code to write to file goes here
                    System.out.println("Writing to file and quitting...");
                    quit = true;

                    // need to get orders sorted by orderID
                    ArrayList<Order> orders = new ArrayList<>();

                    for (int i = 1; i <= shippedOrders.getHeapSize(); i++) {
                        orders.add(shippedOrders.getElement(i));
                    }
                    for (int i = 1; i <= unshippedOrders.getHeapSize(); i++) {
                        orders.add(unshippedOrders.getElement(i));
                    }

                    orders.sort(new OrderIdComparator());
                    try (FileWriter writer = new FileWriter("orders.txt", false)) {
                        writer.write("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < orders.size(); i++) {
                        Order order = orders.get(i);
                        try (FileWriter writer = new FileWriter("orders.txt", true)) {
                            writer.write(order.getFirstName() + " " + order.getLastName() + "\n");
                            writer.write(order.getEmail() + "\n");
                            writer.write(order.getDatePurchased() + "\n");
                            LinkedList<Festival> contents = order.getOrderContents();
                            writer.write(contents.getLength() + "\n");
                            contents.positionIterator();
                            while (!contents.offEnd()) {
                                writer.write(contents.getIterator().getName() + "\n");
                                contents.advanceIterator();
                            }
                            writer.write(order.getIsShipped() + "\n");
                            writer.write(order.getShippingSpeed().getShippingCode() + "\n");
                            if (i != orders.size() - 1) {
                                writer.write("\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }

                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (!quit);
    }
}
