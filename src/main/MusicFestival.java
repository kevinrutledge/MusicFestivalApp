package main;

import java.io.*;
import java.util.*;

public class MusicFestival {
    // Static data structures for the application
    private static BST<Festival> festivalsByName = new BST<>(); // BST for primary key.
    private static BST<Festival> festivalsByStartDateCity = new BST<>(); // BST for secondary key.
    private static HashTable<User> users = new HashTable<>(100); // HashTable for all users.
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
                double price = Double.parseDouble(scanner.nextLine()) ;
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
}
