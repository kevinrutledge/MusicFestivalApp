package main;

import java.util.Comparator;
import java.time.LocalDate;

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
    private String firstName;
    private String lastName;
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
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.datePurchased = "date unknown";
        this.orderContents = null;
        this.totalPrice = 0.0;
        this.shippingSpeed = null;
        this.isShipped = false;
    }

    public Order(String firstName, String lastName, String datePurchased, LinkedList<Festival> orderContents,
                 ShippingSpeed shippingSpeed, boolean isShipped) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("Date Purchased: ").append(datePurchased).append("\n");
        sb.append("Music Festivals: ").append("\n").append(orderContents.toString()).append("\n");
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

class OrderNameComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        String order1CustomerName = order1.getFirstName() + " " + order1.getLastName();
        String order2CustomerName = order2.getFirstName() + " " + order2.getLastName();
        return order1CustomerName.compareTo(order2CustomerName);
    }
}

class OrderIdComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getOrderID().compareTo(order2.getOrderID());
    }
}
