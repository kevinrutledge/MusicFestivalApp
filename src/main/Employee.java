package main;

public class Employee extends User {
    private boolean isManager;

    // Constructor
    public Employee(String firstName, String lastName, String login, String password, boolean isManager) {
        super(firstName, lastName, login, password);
        this.isManager = isManager;
    }

    // Getters
    public boolean getIsManager() {
        return isManager;
    }

    // Setters
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    // Update order status
    public void updateOrderStatus() {
    }
}