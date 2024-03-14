package main;

public class Order {
    private int orderID;
    private Customer customer;
    private String date;
    private BST<Festival> orderContents;
    private int shippingSpeed;
    private int priority;

    // Constructor
    public Order(int orderID, Customer customer, String date, BST<Festival> orderContents,
                 int shippingSpeed, int priority) {
        this.orderID = orderID;
        this.customer = customer;
        this.date = date;
        this.orderContents = orderContents;
        this.shippingSpeed = shippingSpeed;
        this.priority = priority;
    }

    // Getters
    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public BST<Festival> getOrderContents() {
        return orderContents;
    }

    public int getShippingSpeed() {
        return shippingSpeed;
    }

    public int getPriority() {
        return priority;
    }

    // Setters
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderContents(BST<Festival> orderContents) {
        this.orderContents = orderContents;
    }

    public void setShippingSpeed(int shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Additional methods for functionality such as calculating total price, updating order status, etc.

    @Override
    public String toString() {
        // You can modify this to print the details you want to show about the order
        return "";
    }
}