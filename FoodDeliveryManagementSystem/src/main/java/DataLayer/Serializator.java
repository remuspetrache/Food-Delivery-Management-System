package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Serializator {
    private static final String usersFile = "storedUsers.txt";
    private static final String menuFile = "storedProducts.txt";
    private static final String orderFile = "storedOrders.txt";

    public static void serializeInformation(ArrayList<User> users, TreeSet<MenuItem> menuItems, HashMap<Order, ArrayList<MenuItem>> orders) {
        try {
            FileOutputStream fileUsers = new FileOutputStream(usersFile);
            ObjectOutputStream outUsers = new ObjectOutputStream(fileUsers);
            outUsers.writeObject(users);
            outUsers.close();
            fileUsers.close();

            FileOutputStream fileMenuItems = new FileOutputStream(menuFile);
            ObjectOutputStream outMenuItems = new ObjectOutputStream(fileMenuItems);
            outMenuItems.writeObject(menuItems);
            outMenuItems.close();
            fileMenuItems.close();

            FileOutputStream fileOrders = new FileOutputStream(orderFile);
            ObjectOutputStream outOrders = new ObjectOutputStream(fileOrders);
            outOrders.writeObject(orders);
            outOrders.close();
            fileOrders.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> deserializeUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(usersFile);
            ObjectInputStream in = new ObjectInputStream(file);
            users = (ArrayList<User>) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static TreeSet<MenuItem> deserializeProducts() {
        TreeSet<MenuItem> products = new TreeSet<>();
        try {
            FileInputStream file = new FileInputStream(menuFile);
            ObjectInputStream in = new ObjectInputStream(file);
            products = (TreeSet<MenuItem>) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static HashMap<Order, ArrayList<MenuItem>> deserializeOrders() {
        HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(orderFile);
            ObjectInputStream in = new ObjectInputStream(file);
            orders = (HashMap<Order, ArrayList<MenuItem>>) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
