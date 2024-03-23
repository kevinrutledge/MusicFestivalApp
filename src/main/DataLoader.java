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

    static void populateUsers(HashTable<User> users, HashTable<User> employees) {
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
                            name[1],email,
                            password,
                            isEmployee,
                            address,
                            city,
                            state,
                            zip
                    );
                    users.add(customer);
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

    static void populateOrders(Scanner scanner) {
        // Load or input order data
    }
}