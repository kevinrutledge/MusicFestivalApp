package main;

import java.util.Comparator;
import java.time.LocalDate;
import java.util.UUID;

public class Order {
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
    private String userEmailAddress;
    private String datePurchased;
    private LinkedList<Festival> orderContents;
    private double totalPrice;
    private ShippingSpeed shippingSpeed;
    private boolean isShipped;

    // Constructor
    public Order() {
        this.orderID = Order.generateOrderID();
        this.userEmailAddress = "email unknown";
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String orderID) {
        this.orderID = orderID;
        this.userEmailAddress = "email unknown";
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String orderID, String emailAddress, String datePurchased, LinkedList<Festival> orderContents,
                 ShippingSpeed shippingSpeed, boolean isShipped) {
        this.orderID = orderID;
        this.userEmailAddress = emailAddress;
        this.datePurchased = datePurchased;
        this.orderContents = orderContents;
        this.totalPrice = calculateTotalPrice();
        this.shippingSpeed = shippingSpeed;
        this.isShipped = isShipped;
    }

    // Getters
    public static String generateOrderID() {
        return UUID.randomUUID().toString();
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserEmailAddress() { return userEmailAddress; }

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

    public void setUserEmailAddress(String emailAddress) {
        this.userEmailAddress = emailAddress;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Email: ").append(userEmailAddress).append("\n");
        sb.append("Date Purchased: ").append(datePurchased).append("\n");
        sb.append("Music Festivals: ").append(orderContents.toString()).append("\n");
        sb.append("Price: $").append(String.format("%.2f", totalPrice)).append("\n");
        sb.append("Shipping Speed: ").append(shippingSpeed).append("\n");
        sb.append("Shipping Status: ").append(isShipped).append("\n");
        return sb.toString();
    }
}

class PriorityComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        LocalDate date1 = LocalDate.parse(order1.getDatePurchased());
        LocalDate date2 = LocalDate.parse(order2.getDatePurchased());
        int dateComparison = date1.compareTo(date2);

        if (dateComparison != 0) {
            return dateComparison;
        } else {
            return Integer.compare(order1.getShippingSpeed().getShippingCode(),
                                   order2.getShippingSpeed().getShippingCode());
        }
    }
}

class OrderEmailComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getUserEmailAddress().compareTo(order2.getUserEmailAddress());
    }
}

class OrderIdComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getOrderID().compareTo(order2.getOrderID());
    }
}