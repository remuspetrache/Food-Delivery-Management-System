package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public interface IDeliveryServiceProcessing {

    /**
     * Imports the initial base products of the menu
     * @pre InputFile exists
     * @post menuItems is not empty
     */
    void importBaseProduct();

    /**
     * Adds a new product to the restaurant menu
     * @param product the product to be added
     * @pre product != null
     * @post menuItems contains product
     */
    void addProduct(MenuItem product);

    /**
     * Edits a product from the restaurant menu
     * @param oldProductTitle title of the product to be edited
     * @param newProductInformation new product information
     * @pre oldProductTitle != ""
     * @post newProductTitle = the title from newProductInformation
     */
    void editProduct(String oldProductTitle, List<String> newProductInformation);

    /**
     * Deletes a product from the restaurant menu
     * @param product product to be deleted
     * @pre menuItems contains product
     * @post menuItems no longer contains product
     */
    void deleteProduct(MenuItem product);

    /**
     * Generates the first admin report
     * @param startingHour starting hour of the orders
     * @param endingHour ending hour of the orders
     * @pre startingHour <= endingHour
     * @post fileWriter closed
     */
    void generateTimeIntervalReport(int startingHour, int endingHour);

    /**
     * Generates the second admin report
     * @param specifiedNumber minimum number of times a product has to be ordered
     * @pre specifiedNumber >= 0
     * @post fileWriter closed
     */
    void generateProductNumberReport(int specifiedNumber);

    /**
     * Generates the third admin report
     * @param specifiedNumber minimum number of times a client has placed an order
     * @param orderAmount minimum value of orders
     * @pre orderAmount >= 0
     * @post fileWriter closed
     */
    void generateClientValueReport(int specifiedNumber, int orderAmount);

    /**
     * Generates the fourth admin report
     * @param year year of order placement
     * @param month month of order placement
     * @param day day of order placement
     * @pre year >= 0, month > 0, day > 0
     * @post fileWriter closed
     */
    void generateDayTimesReport(int year, int month, int day);

    /**
     * Creates a new order
     * @param year year of order placement
     * @param month month of order placement
     * @param day day of order placement
     * @param hour hour of order placement
     * @param min minute of order placement
     * @param clientID clientID of client that placed the order
     * @param orderID ID of current order
     * @param menuItems items in the order list
     * @pre clientID >= 0, orderID >= 0
     * @post new order added
     */
    void createOrder(int year, int month, int day, int hour, int min, int clientID, int orderID, ArrayList<MenuItem> menuItems);

    /**
     * Helps the client to filter the products in the restaurant menu
     * @param keyword keyword to search
     * @param rating minimum rating of product
     * @param calories minimum calories of product
     * @param proteins minimum proteins of product
     * @param fats minimum fats of product
     * @param sodium minimum sodium of product
     * @param price minimum price of product
     * @return list of products that fit the criteria
     * @pre calories >= 0
     * @pre price >= 0
     * @post corresponding list will be returned
     */
    TreeSet<MenuItem> filterProducts(String keyword, double rating, int calories, int proteins, int fats, int sodium, int price);

    /**
     * Helps the admin to filter the products in the restaurant menu
     *
     * @param keyword keyword to search
     * @return list that fits the criteria
     * @pre keyword can be either empty string or not
     * @post corresponding list will be returned
     */
    TreeSet<MenuItem> searchProductByKeyword(String keyword);
}
