package main;

/**
 * Represents a customer in the Music Festival App.
 * A customer is a user with additional information such as address and order history.
 * Customers can have both shipped and unshipped orders.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class Customer extends User {
    private String address;
    private String city;
    private String state;
    private String zip;
    private LinkedList<Order> shippedOrders = new LinkedList<>();
    private LinkedList<Order> unshippedOrders = new LinkedList<>();

    /**
     * Constructs a Customer with only an email address.
     *
     * @param email the customer's email address
     */
    public Customer(String email) {
        super(email);
    }

    /**
     * Constructs a Customer with an email and password.
     *
     * @param email the customer's email
     * @param password the customer's password
     */
    public Customer(String email, String password) {
        super(email, password);
    }

    /**
     * Constructs a Customer with full details.
     *
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @param email the customer's email
     * @param password the customer's password
     * @param isEmployee indicates if the user is an employee
     * @param address the customer's address
     * @param city the customer's city
     * @param state the customer's state
     * @param zip the customer's zip code
     */
    public Customer(String firstName, String lastName, String email, String password,
                    boolean isEmployee, String address, String city, String state, String zip) {
        super(firstName, lastName, email, password, isEmployee);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    // Getters
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public LinkedList<Order> getShippedOrders() {
        return this.shippedOrders;
    }

    public LinkedList<Order> getUnshippedOrders() {
        return this.unshippedOrders;
    }

    // Setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Adds a shipped order to the customer's list of shipped orders.
     *
     * @param order the order to add
     */
    public void addShippedOrder(Order order) {
        shippedOrders.addLast(order);
    }

    /**
     * Adds an unshipped order to the customer's list of unshipped orders.
     *
     * @param order the order to add
     */
    public void addUnshippedOrder(Order order) {
        unshippedOrders.addLast(order);
    }

    /**
     * Moves an order from the unshipped list to the shipped list.
     * Throws IllegalArgumentException if the order is not found in the unshipped list.
     *
     * @param order the order to move
     * @throws IllegalArgumentException if the order is not in the unshipped orders list
     */
    public void shipOrder(Order order) {
        int index = unshippedOrders.findIndex(order);
        if (index == -1) {
            throw new IllegalArgumentException("shipOrder(): Order not found in unshipped orders for customer " + getFullName() + ": " + order);
        }
        unshippedOrders.advanceIteratorToIndex(index);
        unshippedOrders.removeIterator();
        addShippedOrder(order);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Customer)) return false;
        Customer that = (Customer) other;
        return this.email.equals(that.email);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("City: ").append(city).append("\n");
        sb.append("Zip: ").append(zip).append("\n");
        sb.append("Shipped Orders: ").append("\n").append(shippedOrders.toString()).append("\n");
        sb.append("Unshipped Orders: ").append("\n").append(unshippedOrders.toString()).append("\n");
        return sb.toString();
    }
}