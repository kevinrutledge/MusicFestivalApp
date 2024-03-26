package main;

public class Employee extends User {
    private boolean isManager;

    // Constructor
    public Employee(String email) {
        super(email);
    }

    public Employee(String email, String password) {
        super(email, password);
    }

    public Employee(String firstName, String lastName, String login, String password,
                    boolean isEmployee, boolean isManager) {
        super(firstName, lastName, login, password, isEmployee);
        this.isManager = isManager;
    }

    public boolean getIsManager() {
        return isManager;
    }

    // Setters
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public void updateOrderStatus() {
    }

    public Order getShippedOrderByOrderID(String orderID, BST<Customer> customerByName) {
        Order[] foundOrder = {null};
        customerByName.traverseAndPerformAction(new BST.TraversalAction<Customer>() {
            @Override
            public void performAction(Customer customer) {
                searchOrders(customer.getShippedOrders(), orderID, foundOrder);
            }
        });
        return foundOrder[0];
    }

    public Order getUnshippedOrderByOrderID(String orderID, BST<Customer> customerByName) {
        Order[] foundOrder = {null};
        customerByName.traverseAndPerformAction(new BST.TraversalAction<Customer>() {
            @Override
            public void performAction(Customer customer) {
                searchOrders(customer.getUnshippedOrders(), orderID, foundOrder);
            }
        });
        return foundOrder[0];
    }

    private void searchOrders(LinkedList<Order> orders, String orderID, Order[] foundOrder) {
        if (orders == null || orders.isEmpty()) return;

        Order placeholderOrder = new Order(orderID);
        int index = orders.findIndex(placeholderOrder);

        if (index != -1) {
            orders.advanceIteratorToIndex(index);
            foundOrder[0] = orders.getIterator();
        }
    }

    public LinkedList<Order> getShippedOrderByName(String firstName, String lastName, BST<Customer> customerByName) {
        UserNameComparator<Customer> userNameComparator = new UserNameComparator<>();
        Customer customerPlaceholder = new Customer(firstName, lastName, "", "", false,
                "", "", "", "");
        Customer result = customerByName.search(customerPlaceholder, userNameComparator);
        return result.getShippedOrders();
    }

    public LinkedList<Order> getUnshippedOrderByName(String firstName, String lastName, BST<Customer> customerByName) {
        UserNameComparator<Customer> userNameComparator = new UserNameComparator<>();
        Customer customerPlaceholder = new Customer(firstName, lastName, "", "", false,
                "", "", "", "");
        Customer result = customerByName.search(customerPlaceholder, userNameComparator);
        return result.getUnshippedOrders();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Employee)) return false;
        Employee that = (Employee) other;
        return this.email.equals(that.email);
    }
}