package main;

/**
 * Represents a manager with the capabilities to add, update, and remove festivals in the Music Festival App.
 * Inherits from the Employee class, leveraging shared user attributes and adding manager-specific functionalities.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class Manager extends Employee {

    /**
     * Constructs a Manager with only an email address.
     *
     * @param email The manager's email address.
     */
    public Manager(String email) {
        super(email);
    }

    /**
     * Constructs a Manager with an email and password.
     *
     * @param email The manager's email.
     * @param password The manager's password.
     */
    public Manager(String email, String password) {
        super(email, password);
    }

    /**
     * Constructs a Manager with all details.
     *
     * @param firstName The manager's first name.
     * @param lastName The manager's last name.
     * @param login The manager's login information (typically their email).
     * @param password The manager's password.
     * @param isEmployee Indicates if the person is an employee, which should be true for a manager.
     * @param isManager Indicates if the employee is a manager, which should be true for a manager.
     */
    public Manager(String firstName, String lastName, String login, String password,
            boolean isEmployee, boolean isManager) {
        super(firstName, lastName, login, password, isEmployee, isManager);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Manager)) return false;
        Manager that = (Manager) other;
        return this.email.equals(that.email);
    }
}