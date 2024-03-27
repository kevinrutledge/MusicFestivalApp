package main;

/**
 * Represents an employee in the Music Festival App.
 * Employees have additional privileges compared to regular users, such as managing orders.
 * An employee can be promoted to a manager, which may grant them further privileges.
 *
 * @author: Kevin Rutledge
 * @author: Heejung Lim
 * @author: Congcong Ai
 * @author: Kyle Nguyen
 * @author: Justin Nguyen
 * @author: Nelson Ngo
 * @author: Adnan Abou Kewik
 */
public class Employee extends User {
    private boolean isManager;

    /**
     * Constructs an Employee with only an email address.
     *
     * @param email the employee's email address
     */
    public Employee(String email) {
        super(email);
    }

    /**
     * Constructs an Employee with an email and password.
     *
     * @param email the employee's email
     * @param password the employee's password
     */
    public Employee(String email, String password) {
        super(email, password);
    }

    /**
     * Constructs an Employee with all details.
     *
     * @param firstName the employee's first name
     * @param lastName the employee's last name
     * @param login the employee's login information
     * @param password the employee's password
     * @param isEmployee indicates if the user is an employee (should always be true)
     * @param isManager indicates if the employee is a manager
     */
    public Employee(String firstName, String lastName, String login, String password,
                    boolean isEmployee, boolean isManager) {
        super(firstName, lastName, login, password, isEmployee);
        this.isManager = isManager;
    }

    /**
     * Returns whether the employee is a manager.
     *
     * @return true if the employee is a manager, false otherwise
     */
    public boolean getIsManager() {
        return isManager;
    }

    /**
     * Sets the manager status of the employee.
     *
     * @param isManager the manager status to set
     */
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public void updateOrderStatus() {
    }

    /**
     * Retrieves an order by its ID from a list of customers.
     *
     * @param orderID the ID of the order to find
     * @param customers the list of customers to search through
     * @param searchShipped whether to search through shipped orders (true) or unshipped orders (false)
     * @return the order if found, null otherwise
     */
    public Order getByOrderID(String orderID, LinkedList<Customer> customers, boolean searchShipped) {
        customers.positionIterator();
        while (!customers.offEnd()) {
            Customer currentCustomer = customers.getIterator();
            LinkedList<Order> orders = searchShipped ? currentCustomer.getShippedOrders() : currentCustomer.getUnshippedOrders();

            orders.positionIterator();
            while (!orders.offEnd()) {
                Order currentOrder = orders.getIterator();
                if (currentOrder.getOrderID().equals(orderID)) {
                    return currentOrder;
                }
                orders.advanceIterator();
            }
            customers.advanceIterator();
        }
        return null;
    }

    /**
     * Retrieves all orders associated with a customer's name, either shipped or unshipped.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param customers the list of customers to search through
     * @param searchShipped whether to search through shipped orders (true) or unshipped orders (false)
     * @return a LinkedList of orders matching the criteria
     */
    public LinkedList<Order> getOrdersByName(String firstName, String lastName, LinkedList<Customer> customers, boolean searchShipped) {
        customers.positionIterator();
        while (!customers.offEnd()) {
            Customer currentCustomer = customers.getIterator();
            if (currentCustomer.getFirstName().equals(firstName) && currentCustomer.getLastName().equals(lastName)) {
                System.out.print(currentCustomer.getShippedOrders());
                System.out.print(currentCustomer.getUnshippedOrders());
                return searchShipped ? currentCustomer.getShippedOrders() : currentCustomer.getUnshippedOrders();
            }
            customers.advanceIterator();
        }
        return new LinkedList<Order>();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Employee)) return false;
        Employee that = (Employee) other;
        return this.email.equals(that.email);
    }
}