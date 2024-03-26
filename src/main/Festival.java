package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Festival {
    private String name;
    private String startDate;
    private double price;
    private String city;
    private String state;
    private int ticketsRemaining;
    private String startDateCity;
    private ArrayList<String> genre;
    private ArrayList<String> featuredArtistLineup;

    public Festival(String name) {
        this.name = name;
        this.startDate = "date unknown";
        this.price = 0.0;
        this.city = "city unknown";
        this.state = "state unknown";
        this.ticketsRemaining = 0;
        this.startDateCity = "city and date unknown";
    }

    public Festival(String startDate, String city) {
        this.name = "name unknown";
        this.startDate = startDate;
        this.price = 0.0;
        this.city = city;
        this.state = "state unknown";
        this.ticketsRemaining = 0;
        this.startDateCity = startDate + ", " + city;
    }

    /*
     * Copy constructor to update remaining tickets.
     */
    public Festival(Festival festival, int ticketsRemaining) {
        this.ticketsRemaining = ticketsRemaining;

        this.name = festival.name;
        this.startDate = festival.startDate;
        this.price = festival.price;
        this.city = festival.city;
        this.state = festival.state;
        this.startDateCity = festival.startDateCity;
        this.genre = festival.genre;
        this.featuredArtistLineup = festival.featuredArtistLineup;
    }

    /*
     * Copy constructor to update prices.
     */
    public Festival(Festival festival, double price) {
        this.price = price;

        this.name = festival.name;
        this.startDate = festival.startDate;
        this.city = festival.city;
        this.state = festival.state;
        this.ticketsRemaining = festival.ticketsRemaining;
        this.startDateCity = festival.startDateCity;
        this.genre = festival.genre;
        this.featuredArtistLineup = festival.featuredArtistLineup;        
    }    

    public Festival(String name, String startDate, double price, String city, String state,
                    int ticketsRemaining, ArrayList<String> genre, ArrayList<String> featuredArtistLineup) {
        this.name = name;
        this.startDate = startDate;
        this.price = price;
        this.city = city;
        this.state = state;
        this.ticketsRemaining = ticketsRemaining;
        this.startDateCity = startDate + ", " + city;
        this.genre = genre;
        this.featuredArtistLineup = featuredArtistLineup;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDate() {
        return startDate;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getTicketsRemaining() {
        return ticketsRemaining;
    }

    public String getStartDateCity() {
        return startDateCity;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public ArrayList<String> getArtistLineup() {
        return featuredArtistLineup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.startDate = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLocation(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTicketsRemaining(int ticketsRemaining) {
        this.ticketsRemaining = ticketsRemaining;
    }

    public void addGenre(String genre) {
        this.genre.add(genre);
    }

    public void removeGenre(String genre) {
        this.genre.remove(genre);
    }

    public void addArtist(String artist) {
        featuredArtistLineup.add(artist);
    }

    public void removeArtist(String artist) {
        featuredArtistLineup.remove(artist);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Start Date: ").append(startDate).append("\n");
        sb.append("Price: $").append(String.format("%.2f", price)).append("\n");
        sb.append("Location: ").append(city).append(", ").append(state).append("\n");
        sb.append("Tickets Remaining: ").append(ticketsRemaining).append("\n");
        sb.append("Genres: ").append("\n").append(genre.toString()).append("\n"); // More readable for ArrayList of Strings
        sb.append("Featured Artists: ").append("\n").append(featuredArtistLineup.toString()).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Festival)) return false;
        Festival that = (Festival) other;
        return this.name.equals(that.name);
    }
}

class NameComparator implements Comparator<Festival> {
    @Override
    public int compare(Festival festival1, Festival festival2) {
        return festival1.getName().compareTo(festival2.getName());
    }
}

class StartDateCityComparator implements Comparator<Festival> {
    @Override
    public int compare(Festival festival1, Festival festival2) {
        return festival1.getStartDateCity().compareTo(festival2.getStartDateCity());
    }
}