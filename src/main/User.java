package main;

import java.util.Comparator;

/**
 * Abstract base class for representing users in the Music Festival App.
 * Users can be either customers or employees, with basic attributes like name, email, and password.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected boolean isEmployee;

    /**
     * Constructor initializing a user with only an email. Other fields are set to default unknown values.
     *
     * @param email The user's email address.
     */
    public User(String email) {
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.email = email;
        this.password = "password unknown";
        this.isEmployee = false;
    }

    /**
     * Constructor initializing a user with email and password. Other fields are set to default unknown values.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    public User(String email, String password) {
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.email = email;
        this.password = password;
        this.isEmployee = false;
    }


    /**
     * Full constructor initializing a user with all available information.
     *
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email address.
     * @param password The user's password.
     * @param isEmployee Whether the user is an employee.
     */
    public User(String firstName, String lastName, String email, String password, boolean isEmployee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isEmployee = isEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * Abstract method for matching the user's password with another password.
     *
     * @param anotherPassword The password to compare against the user's password.
     * @return true if the passwords match, false otherwise.
     */
    public boolean passwordMatch(String anotherPassword) {
        return this.password.equals(anotherPassword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            return hashCode() == obj.hashCode();
        }
    }

    @Override
    public int hashCode() {
        return Math.abs(email.hashCode());
    }
}
