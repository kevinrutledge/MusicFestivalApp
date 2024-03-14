package main;

public class Manager extends Employee {

    // Constructor
    public Manager(String firstName, String lastName, String login, String password) {
        super(firstName, lastName, login, password, true);
    }

    // Manager-specific Methods
    public void addProduct(Festival festival) {
        // Implementation
    }

    public void updateProduct(Festival festival) {
        // Implementation
    }

    public void removeProduct(Festival festival) {
        // Implementation
    }
}