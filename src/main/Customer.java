package main;

public class Customer extends User {
    private String address;
    private String city;
    private String state;
    private String zip;
    private LinkedList<Order> shippedOrders = new LinkedList<>();
    private LinkedList<Order> unshippedOrders = new LinkedList<>();

    // Constructor
    public Customer(String firstName, String lastName, String login, String password,
                    boolean isEmployee, String address, String city, String state, String zip) {
        super(firstName, lastName, login, password, isEmployee);
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

    public Order getShippedOrderByOrderID(int orderID) {
        Order placeholderOrder = new Order(orderID);
        int index = shippedOrders.findIndex(placeholderOrder);
        shippedOrders.advanceIteratorToIndex(index);
        Order result = shippedOrders.getIterator();
        return result;
    }

    public Order getUnshippedOrderByOrderID(int orderID) {
        Order placeholderOrder = new Order(orderID);
        int index = unshippedOrders.findIndex(placeholderOrder);
        unshippedOrders.advanceIteratorToIndex(index);
        Order result = unshippedOrders.getIterator();
        return result;
    }

    public Order getShippedOrderByCustomerName() {
        Order placeholderOrder = new Order(firstName, lastName);
        int index = shippedOrders.findIndex(placeholderOrder);
        unshippedOrders.advanceIteratorToIndex(index);
        Order result = unshippedOrders.getIterator();
        return result;
    }

    public Order getunshippedOrderByCustomerName() {
        Order placeholderOrder = new Order(firstName, lastName);
        int index = unshippedOrders.findIndex(placeholderOrder);
        unshippedOrders.advanceIteratorToIndex(index);
        Order result = unshippedOrders.getIterator();
        return result;
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