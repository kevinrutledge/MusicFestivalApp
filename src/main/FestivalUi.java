package main;

import java.util.ArrayList;
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
    	if(isManager(user)) {
    		System.out.println("USER AUTHENTICATED. You are authorized to add a new festival.");
    		
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
            //Splits the names by comma
            String[] artists = artistInput.split(",");
            ArrayList<String> featuredArtists = new ArrayList<>();
            for(String artist: artists) { 
            	featuredArtists.add(artist.trim());
            }
            
            
            System.out.println("Enter the genres seperated by a comma (Rock,Hip Hop,Jazz): ");
            String genreInput = scanner.nextLine();
            //Splits the genres by comma
            String[] genreArray = genreInput.split(",");
            ArrayList<String> genres = new ArrayList<>();
            
            for(String genre: genreArray) { 
            	genres.add(genre.trim());
            }
            
            Festival newFestival = new Festival(name, startDate, price, city, state, tickets, genres, featuredArtists);
            
            byName.insert(newFestival, NAME_COMPARATOR);
            byStartDateCity.insert(newFestival, START_DATE_CITY_COMPARATOR);   
    	} else {
    		System.out.println("USER AUTHENTICATION FAILED. You are not authorized to add a festival.");
    	}
    }

    static void updateFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
        // 1. Check if user is manager
        // 2. Read in user input for the name of festival to update.
        // 3. Call findAndRemove. The returned Festival object is the search result. Make sure to check null for not_found cases.
        // 4. Mutate the found festival using Festival copy constructor (see Festival.java).
        // 5. Insert festival to both BSTs to finish update.
    }

	static void removeFestival(Scanner scanner, BST<Festival> byName, BST<Festival> byStartDateCity, User user) {
		if (isManager(user)) {
			System.out.println("USER AUTHENTICATED. You are authorized to remove a new festival.");
			boolean found = false;

			while (!found) {
				System.out.println("Enter the name of the festival you would like to remove: ");
				String name = scanner.nextLine();

				Festival removed = findAndRemove(name, byName, byStartDateCity);

				if (removed != null) {
					System.out.println("Festival was sucessfully removed.");
					found = true;
					continue;
				} else {
					System.out.println("ERROR. Festival not found.");
					
					System.out.println("Enter '1' to try again or anything else to exit: ");
					String choice = scanner.nextLine();
					
					if(choice.equals("1")) {
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

    private static Festival findAndRemove(String festivalName, BST<Festival> byName, BST<Festival> byStartDateCity) {
        Festival found = byName.search(new Festival(festivalName), NAME_COMPARATOR);
        if (found != null) {
            byName.remove(found, NAME_COMPARATOR);
            byStartDateCity.remove(found, START_DATE_CITY_COMPARATOR);
        }
		return found;
	}

    private static boolean isManager(User user) {
        return (user instanceof Employee) && ((Employee) user).getIsManager();
    }
}
