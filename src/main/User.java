package main;

public abstract class User {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected boolean isEmployee;

    public User(String email) {
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.email = email;
        this.password = "password unknown";
        this.isEmployee = false;
    }

    public User(String email, String password) {
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.email = email;
        this.password = password;
        this.isEmployee = false;
    }

    public User(String firstName, String lastName, String email, String password, boolean isEmployee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isEmployee = isEmployee;
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
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

    // Abstract methods
    public boolean passwordMatch(String anotherPassword) {
        return this.password.equals(anotherPassword);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
