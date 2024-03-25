package main;

public class Employee extends User {
    private boolean isManager;

    // Constructor
    public Employee(String email) {
        super(email);
    }

    public Employee(String email, String password) {
        super(email, password);
    }

    public Employee(String firstName, String lastName, String login, String password,
                    boolean isEmployee, boolean isManager) {
        super(firstName, lastName, login, password, isEmployee);
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Employee)) return false;
        Employee that = (Employee) other;
        return this.email.equals(that.email);
    }
}