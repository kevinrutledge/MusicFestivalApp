package main;

public class Customer extends User {
    private String address;
    private String city;
    private String state;
    private String zip;
    private LinkedList<Order> shippedOrders;
    private LinkedList<Order> unshippedOrders;

    // Constructor
    public Customer(String firstName, String lastName, String login, String password,
                    String address, String city, String state, String zip) {
        super(firstName, lastName, login, password);
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shippedOrders = new LinkedList<>();
        this.unshippedOrders = new LinkedList<>();
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
        return shippedOrders;
    }

    public LinkedList<Order> getUnshippedOrders() {
        return unshippedOrders;
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

    // Methods for managing orders
    public void addShippedOrder(Order order) {
        shippedOrders.addLast(order);
    }

    public void addUnshippedOrder(Order order) {
        unshippedOrders.addLast(order);
    }

    @Override
    public String toString() {
        return "";
    }
}