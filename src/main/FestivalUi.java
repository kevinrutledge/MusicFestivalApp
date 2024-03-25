package main;

import java.util.Scanner;

/**
 * Helper class for searching and printing festivals.
 */
public class FestivalUi {

    private static final NameComparator NAME_COMPARATOR = new NameComparator();
    private static final StartDateCityComparator START_DATE_CITY_COMPARATOR = new StartDateCityComparator();    

    static void searchFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity) {

        int choice = -1;
        while (choice != 3) {
            System.out.println("How would you like to search for festivals?");
            System.out.println("1. Search by name");
            System.out.println("2. Search by start date and city");
            System.out.println("3. To the previous menu\n");

            System.out.print("Please enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Let's search by name.");
                    System.out.print("Please enter the name of the festival: ");
                    String festivalName = scanner.nextLine();
                    Festival festival = new Festival(festivalName);
                    Festival foundByName = byName.search(festival, NAME_COMPARATOR);
                    if (foundByName != null) {
                        System.out.println("\nHere is the information of the festival: ");
                        System.out.println();
                        System.out.println(foundByName);
                    } else {
                        System.out.println("The festival doesn't exist in our system.");
                    }
                    break;
                case 2:
                    System.out.println("Let's search by start date and city.");
                    System.out.print("Please enter the start date of the festival (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Please enter the city: ");
                    String city = scanner.nextLine();
                    Festival festivalToSearch = new Festival(startDate, city);
                    Festival foundByStartDateCity = byStartDateCity.search(festivalToSearch, START_DATE_CITY_COMPARATOR);
                    if (foundByStartDateCity != null) {
                        System.out.println("\nHere is the information of the festival: ");
                        System.out.println();
                        System.out.println(foundByStartDateCity);
                    } else {
                        System.out.println("The festival doesn't exist in our system.");
                    }
                    break;
                case 3:
                    System.out.println("Ok. Back to the previous menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    static void displayFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity) {
        int choice = -1;
        while (choice != 3) {
            System.out.println("How would you like to see the festival list?");
            System.out.println("1. List festivals by name");
            System.out.println("2. List festivals by start date and city");
            System.out.println("3. To the previous menu\n");

            System.out.print("Please enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Here is the list of the festival by name.");
                    System.out.println();
                    System.out.println(byName.inOrderString());
                    break;
                case 2:
                    System.out.println("Here is the list of the festival by start date and city.");
                    System.out.println();
                    System.out.println(byStartDateCity.inOrderString());
                    break;
                case 3:
                    System.out.println("Ok. Back to the previous menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    static void addFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // 1. Check if user is manager
        // 2. Read in user input for the new festival product.
        // 3. Insert new festival in both BSTs.
    }

    static void updateFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // 1. Check if user is manager
        // 2. Read in user input for the name of festival to update.
        // 3. Call findAndRemove. The returned Festival object is the search result. Make sure to check null for not_found cases.
        // 4. Mutate the found festival using Festival copy constructor (see Festival.java).
        // 5. Insert festival to both BSTs to finish update.
    }

    static void removeFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // 1. Check if user is manager
        // 2. Read in user input for the name of festival to remove.
        // 3. Call findAndRemove. The returned Festival object is the search result. Make sure to check null for not_found cases.
    }

    @SuppressWarnings("unused")
    private static Festival findAndRemove(String festivalName, BST<Festival> byName, BST<Festival> byStartDateCity) {
        Festival found = byName.search(new Festival(festivalName), NAME_COMPARATOR);
        if (found != null) {
            byName.remove(found, NAME_COMPARATOR);
            byStartDateCity.remove(found, START_DATE_CITY_COMPARATOR);
        }
        return found;   
    }
}
