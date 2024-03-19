package main;

public class Manager extends Employee {

    public Manager(String firstName, String lastName, String login, String password,
                   boolean isEmployee, boolean isManager) {
        super(firstName, lastName, login, password, isEmployee, isManager);
    }

    public void addFestival(Festival festival) {
    }

    public void updateFestival(Festival festival) {
    }

    public void removeFestival(Festival festival) {
    }
}