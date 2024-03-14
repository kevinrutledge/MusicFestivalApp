package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Festival {
    private String name;
    private String date;
    private double price;
    private String location;
    private String venue;
    private int ticketsRemaining;
    private ArrayList<String> artistLineup;

    // Constructor
    public Festival(String name, String date, double price, String location, String venue, int ticketsRemaining) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.location = location;
        this.venue = venue;
        this.ticketsRemaining = ticketsRemaining;
        this.artistLineup = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getVenue() {
        return venue;
    }

    public int getTicketsRemaining() {
        return ticketsRemaining;
    }

    public ArrayList<String> getArtistLineup() {
        return artistLineup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setTicketsRemaining(int ticketsRemaining) {
        this.ticketsRemaining = ticketsRemaining;
    }

    // Methods for managing artists
    public void insertArtist(String artist) {
        artistLineup.add(artist);
    }

    public void removeArtist(String artist) {
        artistLineup.remove(artist);
    }
}

class NameComparator implements Comparator<Festival> {
    @Override
    public int compare(Festival f1, Festival f2) {
        return f1.getName().compareTo(f2.getName());
    }
}

class DateComparator implements Comparator<Festival> {
    @Override
    public int compare(Festival f1, Festival f2) {
        // date is stored in 'yyyy-MM-dd' format or else error
        return f1.getDate().compareTo(f2.getDate());
    }
}