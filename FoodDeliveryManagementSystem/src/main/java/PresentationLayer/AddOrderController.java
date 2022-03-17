package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import java.util.ArrayList;

public class AddOrderController {

    public AddOrderController(AddOrderView addOrderView, DeliveryService deliveryService, ArrayList<MenuItem> menuItems, int clientID) {
        addOrderView.addSubmitButtonListener(e -> {
            try {
                int year = Integer.parseInt(addOrderView.getYear());
                int month = Integer.parseInt(addOrderView.getMonth());
                int day = Integer.parseInt(addOrderView.getDay());
                int hour = Integer.parseInt(addOrderView.getHour());
                int minute = Integer.parseInt(addOrderView.getMinute());
                ArrayList<MenuItem> products = new ArrayList<>();
                products.addAll(menuItems);
                deliveryService.createOrder(year, month, day, hour, minute, clientID, deliveryService.getMaxOrderID() + 1, products);
                menuItems.clear();
                addOrderView.showSuccess("Order has been placed!");
            } catch (NumberFormatException ex) {
                addOrderView.showError("Invalid data!");
            }
        });
    }
}
