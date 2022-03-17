package BusinessLayer;

import java.util.TreeSet;

public class CompositeProduct extends MenuItem {
    TreeSet<MenuItem> products = new TreeSet<>();

    public CompositeProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price, TreeSet<MenuItem> products) {
        super(title, rating, calories, proteins, fats, sodium, price);
        this.products.addAll(products);
    }

    @Override
    public float computePrice() {
        int totalPrice = 0;
        for (MenuItem m : products) {
            totalPrice += m.getPrice();
        }
        return totalPrice;
    }
}
