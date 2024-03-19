package main;

public class Order {
    private int orderID = 100000000;
    private String firstName;
    private String lastName;
    private String date;
    private LinkedList<Festival> orderContents;
    private int shippingSpeed;
    private int priority;

    // Constructor
    public Order(int orderID) {
        this.orderID = orderID;
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.date = "date unknown";
        this.orderContents = null;
        this.shippingSpeed = 0;
        this.priority = 0;
    }

    public Order(String firstName, String lastName) {
        this.orderID = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = "date unknown";
        this.orderContents = null;
        this.shippingSpeed = 0;
        this.priority = 0;
    }

    public Order(int orderID, String firstName, String lastName, String date,
                 LinkedList<Festival> orderContents, int shippingSpeed, int priority) {
        this.orderID = orderID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.orderContents = orderContents;
        this.shippingSpeed = shippingSpeed;
        this.priority = priority;
    }

    // Getters
    public int getOrderID() {
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

    public int getPriority() {
        return priority;
    }

    // Setters
    public void setOrderID(int orderID) {
        this.orderID = orderID;
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