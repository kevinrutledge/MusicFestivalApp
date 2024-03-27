package main;

import java.util.Comparator;
import java.time.LocalDate;

/**
 * Represents an order within the Music Festival App.
 * Orders contain information about the customer, the festivals included in the order,
 * total price, shipping speed, and shipping status.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class Order {
    /**
     * Enum representing the shipping speeds available for an order.
     */
    public enum ShippingSpeed {
        STANDARD(1),
        RUSH(2),
        OVERNIGHT(3),
        DIGITAL(4);

        private final int shippingCode;

        ShippingSpeed(int shippingCode) {
            this.shippingCode = shippingCode;
        }

        public int getShippingCode() {
            return this.shippingCode;
        }

        public static ShippingSpeed fromCode(int code) {
            for (ShippingSpeed speed : ShippingSpeed.values()) {
                if (speed.getShippingCode() == code) {
                    return speed;
                }
            }
            throw new IllegalArgumentException("Invalid Shipping Speed code: " + code);
        }
    }

    private String orderID;
    private String firstName;
    private String lastName;
    private String email;
    private String datePurchased;
    private LinkedList<Festival> orderContents;
    private double totalPrice;
    private ShippingSpeed shippingSpeed;
    private boolean isShipped;
    private static int orderIDSeed = 100000000;

    // Constructor
    public Order(String orderID) {
        this.orderID = orderID;
        this.firstName = "first name unknown";
        this.lastName = "last name unknown";
        this.email = "email unknown";
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = "email unknown";
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String firstName, String lastName, String email, String datePurchased, LinkedList<Festival> orderContents,
                 ShippingSpeed shippingSpeed, boolean isShipped) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.datePurchased = datePurchased;
        this.orderContents = orderContents;
        this.totalPrice = calculateTotalPrice();
        this.shippingSpeed = shippingSpeed;
        this.isShipped = isShipped;
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

    public String getEmail() {
        return email;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public LinkedList<Festival> getOrderContents() {
        return orderContents;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ShippingSpeed getShippingSpeed() {
        return shippingSpeed;
    }

    public boolean getIsShipped() {
        return isShipped;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public void setShippingSpeed(ShippingSpeed shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public void setIsShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }

    public void addFestival(Festival festival) {
        orderContents.addLast(festival);
    }

    /**
     * Calculates the total price of the festivals in the order.
     * Iterates through the festivals, summing their prices.
     *
     * @return The total price of the order.
     */
    public double calculateTotalPrice() {
        if (this.orderContents == null || this.orderContents.isEmpty()) {
            return 0.0;
        }
        double totalPrice = 0.0;
        this.orderContents.positionIterator();
        while (!this.orderContents.offEnd()) {
            Festival currentFestival = this.orderContents.getIterator();
            totalPrice += currentFestival.getPrice();
            orderContents.advanceIterator();
        }
        this.totalPrice = totalPrice;
        return totalPrice;
    }

    public void removeFestival(Festival festival) {
        int index = orderContents.findIndex(festival);
        orderContents.advanceIteratorToIndex(index);
        orderContents.removeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Order)) return false;
        Order that = (Order) other;
        return this.orderID.equals(that.orderID);
    }
    @Override
    public int hashCode() {
        return (orderID == null) ? 0 : orderID.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("Date Purchased: ").append(datePurchased).append("\n");
        sb.append("Music Festivals: ").append("\n").append(orderContents.toString()).append("\n");
        sb.append("Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        sb.append("Shipping Speed: ").append(shippingSpeed).append("\n");
        sb.append("Is Shipped Status: ").append(isShipped).append("\n");
        return sb.toString();
    }
}

/**
 * Comparator for sorting orders based on their purchase date and shipping speed.
 * Orders with earlier purchase dates are given priority, and among those, orders
 * with faster shipping speeds are prioritized.
 */
class PriorityComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        LocalDate date1 = order1.getDatePurchased() != null ? LocalDate.parse(order1.getDatePurchased()) : LocalDate.MAX;
        LocalDate date2 = order2.getDatePurchased() != null ? LocalDate.parse(order2.getDatePurchased()) : LocalDate.MAX;
        int dateComparison = date1.compareTo(date2);
        if (dateComparison != 0) {
            return dateComparison;
        } else {
            return Integer.compare(order2.getShippingSpeed().getShippingCode(), 
                                   order1.getShippingSpeed().getShippingCode());
        }
    }
 }

/**
 * Comparator for sorting orders by the customer's name.
 * Orders are compared based on the concatenation of the customer's first and last name.
 */
class OrderNameComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        String order1CustomerName = order1.getFirstName() + " " + order1.getLastName();
        String order2CustomerName = order2.getFirstName() + " " + order2.getLastName();
        return order1CustomerName.compareTo(order2CustomerName);
    }
}

/**
 * Comparator for sorting orders by their order ID.
 * Facilitates easy identification and retrieval of specific orders.
 */
class OrderIdComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getOrderID().compareTo(order2.getOrderID());
    }
}
