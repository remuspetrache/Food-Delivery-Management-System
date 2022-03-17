package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ClientController {
    protected ArrayList<MenuItem> orderItems = new ArrayList<>();

    public ClientController(ClientView clientView, DeliveryService deliveryService, int clientID) {
        clientView.addSearchActionListener(e -> {
            double rating = 0.0d;
            int calories = 0, proteins = 0, fats = 0, sodium = 0, price = 0;
            boolean ok = true;
            String keyword = clientView.getKeyword();
            try {
                rating = Double.parseDouble(clientView.getRating());
            } catch (NumberFormatException ex) {
                if (!clientView.getRating().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            try {
                calories = Integer.parseInt(clientView.getCalories());
            } catch (NumberFormatException ex) {
                if (!clientView.getCalories().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            try {
                proteins = Integer.parseInt(clientView.getProteins());
            } catch (NumberFormatException ex) {
                if (!clientView.getProteins().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            try {
                fats = Integer.parseInt(clientView.getFats());
            } catch (NumberFormatException ex) {
                if (!clientView.getFats().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            try {
                sodium = Integer.parseInt(clientView.getSodium());
            } catch (NumberFormatException ex) {
                if (!clientView.getSodium().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            try {
                price = Integer.parseInt(clientView.getPrice());
            } catch (NumberFormatException ex) {
                if (!clientView.getPrice().equals("")) {
                    clientView.showError("Please enter valid data!");
                    ok = false;
                }
            }
            if (ok) {
                TreeSet<MenuItem> filteredProducts = deliveryService.filterProducts(keyword, rating, calories, proteins, fats, sodium, price);
                List<List<String>> tableData = deliveryService.getTableData(filteredProducts);
                clientView.resetTableData(AdminController.getRowDataArray(tableData));
            }
        });
        clientView.addAddProductToOrder(e -> {
            List<String> selectedRow = clientView.getCurrentRowInformation();
            for (MenuItem menuItem : DeliveryService.menuItems) {
                if (menuItem.getTitle().equals(selectedRow.get(1))) {
                    orderItems.add(menuItem);
                }
            }
        });
        clientView.addPlaceOrderActionListener(e -> {
            if (orderItems.isEmpty()) {
                clientView.showError("Please add some products first!");
            } else {
                AddOrderView addOrderView = new AddOrderView();
                AddOrderController addOrderController = new AddOrderController(addOrderView, deliveryService, orderItems, clientID);
                addOrderView.setVisible(true);
            }
        });
    }
}
