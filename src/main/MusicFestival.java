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
        populateFestivals();
        populateUsers(scanner);
        authenticateUsers(scanner);
        populateOrders(scanner);

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

    private static void populateFestivals() {
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

    private static void populateUsers(Scanner scanner) {
        // Load or input user data
    }

    private static void authenticateUsers(Scanner scanner) {
        // Authenticate users based on loaded data
    }

    private static void populateOrders(Scanner scanner) {
        // Load or input order data
    }
}
