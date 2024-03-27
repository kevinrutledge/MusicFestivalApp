package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides user interface functionalities for managing festivals within the Music Festival App.
 * Includes capabilities for searching, displaying, adding, updating, and removing festivals.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class FestivalUi {

    private static final NameComparator NAME_COMPARATOR = new NameComparator();
    private static final StartDateCityComparator START_DATE_CITY_COMPARATOR = new StartDateCityComparator();

    /**
     * Searches for festivals based on user input criteria (name or start date and city).
     *
     * @param scanner A Scanner object for reading user input.
     * @param byName A binary search tree of festivals sorted by name.
     * @param byStartDateCity A binary search tree of festivals sorted by start date and city.
     */
    static void searchFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity) {

        int choice = -1;
        while (choice != 3) {
            System.out.println("How would you like to search for festivals?");
            System.out.println("1. Search by name");
            System.out.println("2. Search by start date and city");
            System.out.println("3. To the previous menu\n");
            choice = getValidInput(1, 3, scanner);

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
                    Festival foundByStartDateCity = byStartDateCity.search(festivalToSearch,
                            START_DATE_CITY_COMPARATOR);
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

    /**
     * Displays festivals sorted either by name or by start date and city based on user choice.
     *
     * @param scanner A Scanner object for reading user input.
     * @param byName A binary search tree of festivals sorted by name.
     * @param byStartDateCity A binary search tree of festivals sorted by start date and city.
     */
    static void displayFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity) {
        int choice = -1;
        while (choice != 3) {
            System.out.println("How would you like to see the festival list?");
            System.out.println("1. List festivals by name");
            System.out.println("2. List festivals by start date and city");
            System.out.println("3. To the previous menu\n");

            choice = getValidInput(1, 3, scanner);

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

    /**
     * Allows managers to add a new festival to the system after validating user credentials.
     *
     * @param scanner A Scanner object for reading user input.
     * @param byName A binary search tree of festivals sorted by name.
     * @param byStartDateCity A binary search tree of festivals sorted by start date and city.
     * @param user The current user attempting to add a festival.
     */
    static void addFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        if (isManager(user)) {
            System.out.println("USER AUTHENTICATED. You are authorized to add a new festival.");
            System.out.println("Press enter to continue: ");
            scanner.nextLine();
            System.out.println("Enter the name of the festival: ");
            String name = scanner.nextLine();

            System.out.print("Please enter the start date of the festival (YYYY-MM-DD): ");
            String startDate = scanner.nextLine();

            System.out.println("Enter the price per ticket: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter the city of the festival:");
            String city = scanner.nextLine();

            System.out.println("Enter the state initials of the festival:");
            String state = scanner.nextLine();

            System.out.println("Enter the amount of ticket remaining: ");
            int tickets = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter featured artists seperated by a comma (John Doe,Jane Doe): ");
            String artistInput = scanner.nextLine();
            // Splits the names by comma
            String[] artists = artistInput.split(",");
            ArrayList<String> featuredArtists = new ArrayList<>();
            for (String artist : artists) {
                featuredArtists.add(artist.trim());
            }

            System.out.println("Enter the genres seperated by a comma (Rock,Hip Hop,Jazz): ");
            String genreInput = scanner.nextLine();
            // Splits the genres by comma
            String[] genreArray = genreInput.split(",");
            ArrayList<String> genres = new ArrayList<>();

            for (String genre : genreArray) {
                genres.add(genre.trim());
            }

            Festival newFestival = new Festival(name, startDate, price, city, state, tickets, genres, featuredArtists);

            byName.insert(newFestival, NAME_COMPARATOR);
            byStartDateCity.insert(newFestival, START_DATE_CITY_COMPARATOR);
            System.out.println();
            System.out.println("Festival added successfully!");
            System.out.println("Here is the information of the festival: ");
            System.out.println();
            System.out.println(newFestival);
            System.out.println("Moving back to the previous menu.");
            System.out.println();
            
            //Write to festivals.txt
            try (FileWriter writer = new FileWriter("festivals.txt", true)) {
                writer.write("\n" + newFestival.getName() + "\n");
                writer.write(newFestival.getDate() + "\n");
                writer.write(newFestival.getPrice() + "\n");
                writer.write(newFestival.getLocation() + "\n");
                writer.write(newFestival.getState() + "\n");
                writer.write(newFestival.getTicketsRemaining() + "\n");
                writer.write(newFestival.getGenre().size() + "\n");
                writer.write(String.join("\n", newFestival.getGenre()) + "\n");
                writer.write(newFestival.getArtistLineup().size() + "\n");
                writer.write(String.join("\n", newFestival.getArtistLineup()) + "\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("USER AUTHENTICATION FAILED. You are not authorized to add a festival.");
        }
    }

    /**
     * Allows managers to update existing festivals in the system after validating user credentials.
     * Updates can include price changes and ticket count adjustments.
     *
     * @param scanner A Scanner object for reading user input.
     * @param byName A binary search tree of festivals sorted by name.
     * @param byStartDateCity A binary search tree of festivals sorted by start date and city.
     * @param user The current user attempting to update a festival.
     */
    static void updateFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        if (isManager(user)) {
            System.out.println("USER AUTHENTICATED. You are authorized to update a new festival.");
            System.out.println("Press enter to continue: ");
            scanner.nextLine();
            System.out.println("Here is the current festival.");
            System.out.println();
            System.out.println(byName.inOrderString());

            int input = -1;
            while (input != 3) {
                System.out.println("Enter the name of the festival you would like to update: ");
                String name = scanner.nextLine();

                Festival removed = findAndRemove(name, byName, byStartDateCity);
                if (removed == null) {
                    System.out.println("Festival not found. Please try again.");
                } else {
                    System.out.println("What would you like to update?");
                    System.out.println("1. Update price of the festival");
                    System.out.println("2. Update remaining ticket count of the festival");
                    System.out.println("3. To the previous menu\n");
                    input = getValidInput(1, 3, scanner);
                    switch (input) {
                        case 1:
                            System.out.println("The current price: ");
                            System.out.println(String.format("%.2f", removed.getPrice()));
                            System.out.println();
                            System.out.println("Enter the new price: ");
                            double price = Double.parseDouble(scanner.nextLine());
                            Festival newFestivalWithPrice = new Festival(removed, price);
                            byName.insert(newFestivalWithPrice, NAME_COMPARATOR);
                            byStartDateCity.insert(newFestivalWithPrice, START_DATE_CITY_COMPARATOR);
                            System.out.println("Price update completed! Moving back to the previous menu.");
                            input = 3;
                            break;
                        case 2:
                            System.out.println("The current ticket count: ");
                            System.out.println(removed.getTicketsRemaining());
                            System.out.println();
                            System.out.println("Enter the new ticket count: ");
                            int count = Integer.parseInt(scanner.nextLine());
                            Festival newFestivalWithCount = new Festival(removed, count);
                            byName.insert(newFestivalWithCount, NAME_COMPARATOR);
                            byStartDateCity.insert(newFestivalWithCount, START_DATE_CITY_COMPARATOR);
                            System.out.println("Ticket count update completed! Moving back to the previous menu.");
                            input = 3;
                            break;
                        case 3:
                            System.out.println("Ok. Back to the previous menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                            break;
                    }
                }
            }

        } else {
            System.out.println("USER AUTHENTICATION FAILED. You are not authorized to update a festival.");
        }
    }

    /**
     * Allows managers to remove festivals from the system after validating user credentials.
     *
     * @param scanner A Scanner object for reading user input.
     * @param byName A binary search tree of festivals sorted by name.
     * @param byStartDateCity A binary search tree of festivals sorted by start date and city.
     * @param user The current user attempting to remove a festival.
     */
    static void removeFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        if (isManager(user)) {
            System.out.println("USER AUTHENTICATED. You are authorized to remove a new festival.");
            boolean found = false;

            while (!found) {
                System.out.println("Press enter to continue: ");
                scanner.nextLine();
                System.out.println("Here is the current festival.");
                System.out.println();
                System.out.println(byName.inOrderString());
                System.out.println();
                System.out.println("Enter the name of the festival you would like to remove: ");
                String name = scanner.nextLine();

                Festival removed = findAndRemove(name, byName, byStartDateCity);

                if (removed != null) {
                    System.out.println("Festival was sucessfully removed. Moving back to the previous menu.");
                    System.out.println();
                    found = true;
                    continue;
                } else {
                    System.out.println("ERROR. Festival not found.");

                    System.out.println("Enter '1' to try again or anything else to exit: ");
                    String choice = scanner.nextLine();

                    if (choice.equals("1")) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
        } else {
            System.out.println("USER AUTHENTICATION FAILED. You are not authorized to remove a festival.");
        }

    }

    /**
     * Searches for a festival by name and removes it from both BSTs if found.
     *
     * @param festivalName The name of the festival to find and remove.
     * @param byName The BST sorted by festival name.
     * @param byStartDateCity The BST sorted by start date and city.
     * @return The removed Festival object if found, otherwise null.
     */
    private static Festival findAndRemove(String festivalName, BST<Festival> byName, BST<Festival> byStartDateCity) {
        Festival found = byName.search(new Festival(festivalName), NAME_COMPARATOR);
        if (found != null) {
            byName.remove(found, NAME_COMPARATOR);
            byStartDateCity.remove(found, START_DATE_CITY_COMPARATOR);
        }
        return found;
    }

    /**
     * Checks if the given User object is an instance of Employee and if they are a manager.
     *
     * @param user The User object to check for manager status.
     * @return true if the user is an Employee and a manager, false otherwise.
     */
    private static boolean isManager(User user) {
        return (user instanceof Employee) && ((Employee) user).getIsManager();
    }

    /**
     * Prompts the user for input and validates it as an integer within a specified range.
     * It is used to handle menu selections and other numerical inputs that require validation.
     *
     * @param validStart The start (inclusive) of the valid input range.
     * @param validEnd The end (inclusive) of the valid input range.
     * @param scanner The Scanner object used for input.
     * @return An integer representing the valid user choice.
     */
    private static int getValidInput(int validStart, int validEnd, Scanner scanner) {
        while (true) {
            System.out.print("Please enter your choice: ");
            String input = scanner.nextLine();
            if (input == null) {
                System.out.println("Invalid input.");
                continue;
            }
            input = input.trim();
            if (input.isEmpty() || input.length() != 1) {
                System.out.println("Invalid input.");
                continue;
            } else if (!Character.isDigit(input.charAt(0))) {
                System.out.println("Invalid input.");
                continue;
            }
            int choice = Integer.parseInt(input);
            if (choice < validStart || choice > validEnd) {
                System.out.println("Invalid input.");
                continue;
            }
            // Valid
            return choice;
        }
    }
}
