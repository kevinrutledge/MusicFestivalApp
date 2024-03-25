package main;

public class Customer extends User {
    private String address;
    private String city;
    private String state;
    private String zip;
    private LinkedList<Order> shippedOrders = new LinkedList<>();
    private LinkedList<Order> unshippedOrders = new LinkedList<>();

    // Constructor
    public Customer(String email) {
        super(email);
    }

    public Customer(String email, String password) {
        super(email, password);
    }

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

    public Order getShippedOrderByOrderID(String orderID) {
        Order placeholderOrder = new Order(orderID);
        int index = shippedOrders.findIndex(placeholderOrder);
        shippedOrders.advanceIteratorToIndex(index);
        Order result = shippedOrders.getIterator();
        return result;
    }

    public Order getUnshippedOrderByOrderID(String orderID) {
        Order placeholderOrder = new Order(orderID);
        int index = unshippedOrders.findIndex(placeholderOrder);
        unshippedOrders.advanceIteratorToIndex(index);
        Order result = unshippedOrders.getIterator();
        return result;
    }

    public LinkedList<Order> getShippedOrderByCustomerName() {
        return shippedOrders;
    }

    public LinkedList<Order> getUnshippedOrderByCustomerName() {
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

    /**
     * Move an order from the customer's unshipped list to their shipped list
     * @param order
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
        sb.append("Zip").append(zip).append("\n");
        sb.append("Shipped Orders: ").append("\n").append(shippedOrders.toString()).append("\n");
        sb.append("Unshipped Orders: ").append("\n").append(unshippedOrders.toString()).append("\n");
        return sb.toString();
    }
}