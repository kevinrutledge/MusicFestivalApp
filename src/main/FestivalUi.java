package main;

import java.util.Scanner;

/**
 * Helper class for searching and printing festivals.
 */
public class FestivalUi {

    static void searchFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity) {
        NameComparator nameComparator = new NameComparator();
        StartDateCityComparator startDateCityComparator = new StartDateCityComparator();
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
                    Festival foundByName = byName.search(festival, nameComparator);
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
                    Festival foundByStartDateCity = byStartDateCity.search(festivalToSearch, startDateCityComparator);
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
        // TODO - heejung
        // 1. Check if user is manager
        // 2. Insert new festival in both BSTs.
    }

    static void updateFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // TODO - heejung
        // 1. Check if user is manager
        // 2. Search by primary key (name)
        // 3. Remove the found festival in both BSTs.
        // 4. Mutate the found festival.
        // 5. Insert festival to both BSTs.
    }

    static void removeFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // TODO - heejung
        // 1. Check if user is manager
        // 2. Search by primary key (name)
        // 3. Remove the found festival in both BSTs.
        // 4. Mutate the found festival.
        // 5. Insert festival to both BSTs.        
    }

    @SuppressWarnings("unused")
    private static Festival findAndRemove(BST<Festival> byName, BST<Festival> byStartDateCity) {
        // TODO - heejung
        // Helper method to be used by update and remove methods for.
        // - Search by primary key (name)
        // - Remove the found festival in both BSTs.
        return null;   
    }
}
