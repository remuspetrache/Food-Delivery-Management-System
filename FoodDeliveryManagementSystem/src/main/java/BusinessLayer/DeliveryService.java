package BusinessLayer;

import DataLayer.FileWriter;
import DataLayer.Serializator;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    public static final TreeSet<MenuItem> menuItems = new TreeSet<>();
    public static final HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    public static final ArrayList<User> users = new ArrayList<>();
    public static boolean allGood = true;

    public DeliveryService() {
    }

    public boolean isWellFormed() {
        return allGood;
    }

    /**
     * Imports the initial base products of the menu
     *
     * @pre InputFile exists
     * @post menuItems is not empty
     */
    @Override
    public void importBaseProduct() {
        try {
            InputStream is = new FileInputStream("products.csv");
            assert (is != null);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            TreeSet<BaseProduct> importedProducts = br.lines()
                    .skip(1)
                    .map(line -> {
                        String[] p = line.split(",");
                        return new BaseProduct(p[0].replaceAll("\"", ""), Double.parseDouble(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                                Integer.parseInt(p[4]), Integer.parseInt(p[5]), Integer.parseInt(p[6]));
                    })
                    .collect(Collectors.toCollection(TreeSet::new));

            menuItems.addAll(importedProducts);
            assert (!menuItems.isEmpty());
            assert (isWellFormed());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new product to the restaurant menu
     *
     * @param product the product to be added
     * @pre product != null
     * @post menuItems contains product
     */
    @Override
    public void addProduct(MenuItem product) {
        assert (product != null);
        menuItems.add(product);
        assert (menuItems.contains(product));
        assert (isWellFormed());
    }

    /**
     * Edits a product from the restaurant menu
     *
     * @param oldProductTitle title of the product to be edited
     * @param newInformation  new product information
     * @pre oldProductTitle != null
     * @post newProductTitle = the title from newProductInformation
     */
    @Override
    public void editProduct(String oldProductTitle, List<String> newInformation) {
        assert (oldProductTitle != null);
        for (MenuItem product : menuItems) {
            if (product.getTitle().equals(oldProductTitle)) {
                product.setTitle(newInformation.get(1));
                product.setRating(Double.parseDouble(newInformation.get(2)));
                product.setCalories(Integer.parseInt(newInformation.get(3)));
                product.setProteins((Integer.parseInt(newInformation.get(4))));
                product.setFats(Integer.parseInt(newInformation.get(5)));
                product.setSodium(Integer.parseInt(newInformation.get(6)));
                product.setPrice(Integer.parseInt(newInformation.get(7)));
                assert (product.getTitle().equals(newInformation.get(1)));
                assert (isWellFormed());
            }
        }
    }

    /**
     * Deletes a product from the restaurant menu
     *
     * @param product product to be deleted
     * @pre menuItems contains product
     * @post menuItems no longer contains product
     */
    @Override
    public void deleteProduct(MenuItem product) {
        assert (menuItems.contains(product));
        menuItems.remove(product);
        assert (!menuItems.contains(product));
        assert (isWellFormed());
    }

    /**
     * Generates the first admin report
     *
     * @param startingHour starting hour of the orders
     * @param endingHour   ending hour of the orders
     * @pre startingHour <= endingHour
     * @post fileWriter closed
     */
    @Override
    public void generateTimeIntervalReport(int startingHour, int endingHour) {
        assert (startingHour <= endingHour);
        AtomicInteger currentOrderNb = new AtomicInteger();
        FileWriter fileWriter = new FileWriter("timeIntervalReport");
        orders.keySet().stream()
                .filter(o -> o.getOrderDate().getHour() >= startingHour)
                .filter(o -> o.getOrderDate().getHour() < endingHour)
                .forEach(o -> fileWriter.write(currentOrderNb.incrementAndGet() + ". " + o.toString() + "\n"));
        fileWriter.closeFileWriter();
        assert (true);
        assert (isWellFormed());
    }

    /**
     * Generates the second admin report
     *
     * @param specifiedNumber minimum number of times a product has to be ordered
     * @pre specifiedNumber >= 0
     * @post fileWriter closed
     */
    @Override
    public void generateProductNumberReport(int specifiedNumber) {
        assert (specifiedNumber >= 0);
        AtomicInteger currentProductNb = new AtomicInteger();
        FileWriter fileWriter = new FileWriter(("productNumberReport"));

        ArrayList<MenuItem> products = new ArrayList<>();
        for (ArrayList<MenuItem> list : orders.values()) {
            products.addAll(list);
        }

        Map<MenuItem, Long> counterMap = products.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        counterMap.keySet().stream()
                .filter(m -> counterMap.get(m) > specifiedNumber)
                .forEach(m -> fileWriter.write(currentProductNb.incrementAndGet() + ". " + m.toString() + "\n"));
        fileWriter.closeFileWriter();
        assert (true);
        assert (isWellFormed());
    }

    /**
     * Generates the third admin report
     *
     * @param specifiedNumber minimum number of times a client has placed an order
     * @param orderAmount     minimum value of orders
     * @pre orderAmount >= 0
     * @post fileWriter closed
     */
    @Override
    public void generateClientValueReport(int specifiedNumber, int orderAmount) {
        assert (orderAmount >= 0);
        AtomicInteger currentClientNb = new AtomicInteger();
        FileWriter fileWriter = new FileWriter("clientValueReport");

        List<Integer> clients = orders.keySet().stream()
                .filter(o -> o.getValue() > orderAmount)
                .map(Order::getClientID)
                .collect(Collectors.toList());

        Map<Integer, Long> counterMap = clients.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        counterMap.keySet().stream()
                .filter(o -> counterMap.get(o) > specifiedNumber)
                .forEach(o -> users.stream()
                        .filter(u -> u instanceof Client)
                        .filter(u -> ((Client) u).getClientID() == o)
                        .map(Client.class::cast)
                        .forEach(c -> fileWriter.write(currentClientNb.incrementAndGet() + ". " + c.toString() + "\n")));
        fileWriter.closeFileWriter();
        assert (true);
        assert (isWellFormed());
    }

    /**
     * Generates the fourth admin report
     *
     * @param year  year of order placement
     * @param month month of order placement
     * @param day   day of order placement
     * @pre year >= 0, month > 0, day > 0
     * @post fileWriter closed
     */
    @Override
    public void generateDayTimesReport(int year, int month, int day) {
        assert (year >= 0);
        assert (month > 0);
        assert (day > 0);
        int currentProductNb = 0;
        FileWriter fileWriter = new FileWriter("dayTimesReport");

        ArrayList<MenuItem> products = new ArrayList<>();
        orders.keySet().stream()
                .filter(o -> o.getOrderDate().getDayOfMonth() == day)
                .filter(o -> o.getOrderDate().getMonthValue() == month)
                .filter(o -> o.getOrderDate().getYear() == year)
                .forEach(o -> products.addAll(orders.get(o)));

        Map<MenuItem, Long> counterMap = products.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (MenuItem m : counterMap.keySet()) {
            fileWriter.write(++currentProductNb + ". " + m.toString() + "\nIt has been ordered " +
                    counterMap.get(m) + " times.\n");
        }

        fileWriter.closeFileWriter();
        assert (true);
        assert (isWellFormed());
    }

    public int writeBill(int orderID, ArrayList<MenuItem> menuItems) {
        FileWriter fileWriter = new FileWriter("billNr" + orderID);
        int totalPrice = 0;
        fileWriter.write("Ordered products:\n");
        for (MenuItem m : menuItems) {
            fileWriter.write(m.toString() + "\n");
            totalPrice += m.getPrice();
        }
        fileWriter.write("Total price of the order: " + totalPrice);
        fileWriter.closeFileWriter();
        return totalPrice;
    }

    /**
     * Creates a new order
     *
     * @param year      year of order placement
     * @param month     month of order placement
     * @param day       day of order placement
     * @param hour      hour of order placement
     * @param min       minute of order placement
     * @param clientID  clientID of client that placed the order
     * @param orderID   ID of current order
     * @param menuItems items in the order list
     * @pre clientID >= 0, orderID >= 0
     * @post new order added
     */
    @Override
    public void createOrder(int year, int month, int day, int hour, int min, int clientID, int orderID, ArrayList<MenuItem> menuItems) {
        assert (clientID >= 0);
        assert (orderID >= 0);
        LocalDateTime date = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, min));
        Order o = new Order(orderID, clientID, date);
        o.setValue(writeBill(orderID, menuItems));
        orders.put(o, menuItems);

        String orderStatus = "";
        int currentOrder = 0;
        for (Order order : orders.keySet()) {
            orderStatus += ++currentOrder + ". " + order.toString();
            orderStatus += "\nThe following products are part of this order:\n";
            for (MenuItem menuItem : orders.get(order)) {
                orderStatus += menuItem.toString() + "\n";
            }
            orderStatus += "\n";
        }
        setChanged();
        notifyObservers(orderStatus);
        assert (true);
        assert (isWellFormed());
    }

    /**
     * Helps the client to filter the products in the restaurant menu
     *
     * @param keyword  keyword to search
     * @param rating   minimum rating of product
     * @param calories minimum calories of product
     * @param proteins minimum proteins of product
     * @param fats     minimum fats of product
     * @param sodium   minimum sodium of product
     * @param price    minimum price of product
     * @return list of products that fit the criteria
     * @pre calories >= 0
     * @pre price >= 0
     * @post corresponding list will be returned
     */
    @Override
    public TreeSet<MenuItem> filterProducts(String keyword, double rating, int calories, int proteins, int fats, int sodium, int price) {
        assert (calories >= 0);
        assert (price >= 0);
        assert (isWellFormed());
        return menuItems.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .filter(m -> m.getRating() >= rating)
                .filter(m -> m.getCalories() >= calories)
                .filter(m -> m.getProteins() >= proteins)
                .filter(m -> m.getFats() >= fats)
                .filter(m -> m.getSodium() >= sodium)
                .filter(m -> m.getPrice() >= price)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Helps the admin to filter the products in the restaurant menu
     *
     * @param keyword keyword to search
     * @return list that fits the criteria
     * @pre keyword can be either empty string or not
     * @post corresponding list will be returned
     */
    @Override
    public TreeSet<MenuItem> searchProductByKeyword(String keyword) {
        assert(keyword.isEmpty() || !keyword.isEmpty());
        assert(isWellFormed());
        return menuItems.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Role verifyLogIn(String username, String password) {
        for (User u : users) {
            if (u.getPassword().equals(password) && u.getUsername().equals(username)) {
                return u.getRole();
            }
        }
        return null;
    }

    public int getCurrentClientID(String username, String password) {
        for (User u : users) {
            if (u instanceof Client) {
                if (u.getPassword().equals(password) && u.getUsername().equals(username)) {
                    return ((Client) u).getClientID();
                }
            }
        }
        return -1;
    }

    public boolean isAccountCreated(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int getMaxClientID() {
        int maxClientID = 0;
        for (User u : users) {
            if (u instanceof Client) {
                if (((Client) u).getClientID() > maxClientID) {
                    maxClientID = ((Client) u).getClientID();
                }
            }
        }
        return maxClientID;
    }

    public int getMaxOrderID() {
        int maxOrderID = 0;
        for (Order o : orders.keySet()) {
            if (o.getOrderID() > maxOrderID) {
                maxOrderID = o.getOrderID();
            }
        }
        return maxOrderID;
    }

    public void storeInformation() {
        Serializator.serializeInformation(users, menuItems, orders);
    }

    public void loadInformation() {
        ArrayList<User> loadedUsers = Serializator.deserializeUsers();
        users.clear();
        if (loadedUsers != null) {
            users.addAll(loadedUsers);
        }

        TreeSet<MenuItem> loadedMenuItems = Serializator.deserializeProducts();
        menuItems.clear();
        if (loadedMenuItems != null) {
            menuItems.addAll(loadedMenuItems);
        }

        HashMap<Order, ArrayList<MenuItem>> loadedOrders = Serializator.deserializeOrders();
        orders.clear();
        if (loadedOrders != null) {
            orders.putAll(loadedOrders);
        }
    }

    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("Title");
        columnNames.add("Rating");
        columnNames.add("Calories");
        columnNames.add("Proteins");
        columnNames.add("Fats");
        columnNames.add("Sodium");
        columnNames.add("Price");
        return columnNames;
    }

    public List<List<String>> getTableData(TreeSet<MenuItem> menuItems) {
        List<List<String>> tableData = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            List<String> tableRow = new ArrayList<>();
            tableRow.add(menuItem.getTitle());
            tableRow.add("" + menuItem.getRating());
            tableRow.add("" + menuItem.getCalories());
            tableRow.add("" + menuItem.getProteins());
            tableRow.add("" + menuItem.getFats());
            tableRow.add("" + menuItem.getSodium());
            tableRow.add("" + menuItem.getPrice());

            tableData.add(tableRow);
        }
        return tableData;
    }
}
