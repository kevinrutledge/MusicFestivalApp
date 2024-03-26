package main;

import java.io.*;
import java.util.*;

public class DataLoader {
    static void populateFestivals(BST<Festival> festivalsByName, BST<Festival> festivalsByStartDateCity) {
        try {
            Scanner scanner = new Scanner(new File("festivals.txt"));
            NameComparator nameComparator = new NameComparator();
            StartDateCityComparator startDateCityComparator = new StartDateCityComparator();
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String startDate = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                String city = scanner.nextLine();
                String state = scanner.nextLine();
                int ticketsRemaining = Integer.parseInt(scanner.nextLine());
                int numGenre = Integer.parseInt(scanner.nextLine());
                // Genre
                ArrayList<String> genre = new ArrayList<>();
                for (int i = 0; i < numGenre; i++) {
                    genre.add(scanner.nextLine());
                }
                // Artists
                int numArtists = Integer.parseInt(scanner.nextLine());
                ArrayList<String> artists = new ArrayList<>();
                for (int i = 0; i < numArtists; i++) {
                    artists.add(scanner.nextLine());
                }
                // Create festivals and seed them into BST.
                Festival festival = new Festival(name, startDate, price, city, state, ticketsRemaining, genre, artists);
                festivalsByName.insert(festival, nameComparator);
                festivalsByStartDateCity.insert(festival, startDateCityComparator);
                // consume white space.
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: ");
        }
    }

    static void populateUsers(HashTable<User> users, HashTable<User> employees, BST<Customer> customersByName) {
        UserNameComparator userNameComparator = new UserNameComparator();
        // Load user data from file
        try {
            Scanner scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNextLine()) {
                // [first, last]
                String[] name = scanner.nextLine().split(" ");
                String email = scanner.nextLine();
                String password = scanner.nextLine();
                boolean isEmployee = Boolean.parseBoolean(scanner.nextLine());
                if (!isEmployee) {
                    // customer
                    String address = scanner.nextLine();
                    String city = scanner.nextLine();
                    String state = scanner.nextLine();
                    String zip = scanner.nextLine();
                    Customer customer = new Customer(
                            name[0],
                            name[1],
                            email,
                            password,
                            isEmployee,
                            address,
                            city,
                            state,
                            zip
                    );
                    users.add(customer);
                    customersByName.insert(customer, userNameComparator);
                } else {
                    boolean isManager = Boolean.parseBoolean(scanner.nextLine());
                    if (isManager) {
                        Manager manager = new Manager(
                                name[0],
                                name[1],
                                email,
                                password,
                                isEmployee,
                                isManager
                        );
                        users.add(manager);
                        employees.add(manager);
                    } else {
                        Employee employee = new Employee(
                                name[0],
                                name[1],
                                email,
                                password,
                                isEmployee,
                                isManager
                        );
                        users.add(employee);
                        employees.add(employee);
                    }
                }

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: ");
        }
    }

    static void authenticateUsers(Scanner scanner) {
        // Authenticate users based on loaded data
    }

    static void populateOrders(
            Heap<Order> shippedOrders,
            Heap<Order> unShippedOrders,
            BST<Festival> festivals,
            BST<Customer> customersByName
    ) {
        UserNameComparator<Customer> userNameComparator = new UserNameComparator<>();
        try {
            Scanner scanner = new Scanner(new File("orders.txt"));
            while (scanner.hasNextLine()) {
                String[] names = scanner.nextLine().split(" ");
                String dateOfPurchase = scanner.nextLine();
                int numFestivals = Integer.parseInt(scanner.nextLine());
                LinkedList<Festival> festivalList = new LinkedList<>();

                for (int i = 0; i < numFestivals; i++) {
                    NameComparator comparator = new NameComparator();
                    String festivalName = scanner.nextLine();
                    Festival placeholderFestival = new Festival(festivalName);
                    Festival festival = festivals.search(placeholderFestival, comparator);
                    if (festival != null) {
                        festivalList.addLast(festival);
                    }
                }

                boolean isShipped = Boolean.parseBoolean(scanner.nextLine());
                int shippingCode = Integer.parseInt(scanner.nextLine());
                Order.ShippingSpeed shippingSpeed = Order.ShippingSpeed.fromCode(shippingCode);

                /**
                 * Set up customer in order to populate their
                 * unshippedOrders or shippedOrders variables
                 */
                Customer customerPlaceholder = new Customer(
                        names[0],
                        names[1],
                        "",
                        "",
                        false,
                        "",
                        "",
                        "",
                        ""
                );
                Customer customer = customersByName.search(customerPlaceholder, userNameComparator);
                if (customer == null) {
                    throw new IllegalArgumentException("populateOrders(): customer with name " + customerPlaceholder.getFullName() + " does not exist.");
                }

                Order order = new Order(
                        names[0],
                        names[1],
                        dateOfPurchase,
                        festivalList,
                        shippingSpeed,
                        isShipped
                );

                order.setOrderID("" + Order.generateOrderID());

                if (isShipped) {
                    customer.addShippedOrder(order);
                    shippedOrders.insert(order);
                } else {
                    customer.addUnshippedOrder(order);
                    unShippedOrders.insert(order);
                }

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: ");
        }
    }
}