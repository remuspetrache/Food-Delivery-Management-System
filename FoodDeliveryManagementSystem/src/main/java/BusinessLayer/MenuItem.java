package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Comparable<MenuItem>, Serializable {
    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private int price;

    public MenuItem(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem(String title) {
        this.title = title;
    }

    public MenuItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract float computePrice();

    @Override
    public int compareTo(MenuItem otherMenuItem) {
        return title.compareTo(otherMenuItem.getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        return title.equals(((MenuItem) obj).getTitle());
    }

    @Override
    public String toString() {
        return "Product name: " + title + "\nRating " + rating + " out of 5\n" +
                "It has " + calories + " calories, " + proteins + " proteins, " + fats + " fats, " + sodium + " sodium.\n" +
                "Price: " + price;
    }
}
