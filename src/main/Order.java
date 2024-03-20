package main;

import java.util.Comparator;

public class Order {
    private String orderID;
    private String firstName;
    private String lastName;
    private String date;
    private LinkedList<Festival> orderContents;
    private boolean isShipped;
    private int shippingSpeed;
    private int priority;
    private static int orderIDSeed = 100000000;

    // Constructor
    public Order(String orderID) {
        this.orderID = orderID;
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.date = "date unknown";
        this.orderContents = null;
        this.isShipped = false;
        this.shippingSpeed = 0;
        this.priority = 0;
    }

    public Order(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = "date unknown";
        this.orderContents = null;
        this.isShipped = false;
        this.shippingSpeed = 0;
        this.priority = 0;
    }

    public Order(String firstName, String lastName, String date, boolean isShipped,
                 LinkedList<Festival> orderContents, int shippingSpeed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.orderContents = orderContents;
        this.isShipped = isShipped;
        this.shippingSpeed = shippingSpeed;
    }

    // Getters
    public static int generateOrderID() {
        orderIDSeed++;
        return orderIDSeed;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public LinkedList<Festival> getOrderContents() {
        return orderContents;
    }
    public int getShippingSpeed() {
        return shippingSpeed;
    }

    public boolean getIsShipped() {
        return isShipped;
    }

    public int getPriority() {
        return priority;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }

    public void setShippingSpeed(int shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void addFestival(Festival festival) {
        orderContents.addLast(festival);
    }

    public void removeFestival(Festival festival) {
        int index = orderContents.findIndex(festival);
        orderContents.advanceIteratorToIndex(index);
        orderContents.removeIterator();
    }

    @Override
    public String toString() {
        // You can modify this to print the details you want to show about the order
        return "";
    }
}

class PriorityComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return Integer.compare(order1.getPriority(), order2.getPriority());
    }
}