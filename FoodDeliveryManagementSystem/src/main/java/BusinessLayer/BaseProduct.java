package BusinessLayer;

public class BaseProduct extends MenuItem {

    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
    }

    public BaseProduct(String title) {
        super(title);
    }

    @Override
    public float computePrice() {
        return getPrice();
    }
}
