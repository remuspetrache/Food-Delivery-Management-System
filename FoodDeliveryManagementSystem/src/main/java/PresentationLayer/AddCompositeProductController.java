package PresentationLayer;

import BusinessLayer.CompositeProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import java.util.TreeSet;

public class AddCompositeProductController {

    public AddCompositeProductController(AddCompositeProductView addCompositeProductView, ManageProductsView manageProductsView, DeliveryService deliveryService, TreeSet<MenuItem> products) {
        addCompositeProductView.addSubmitActionListener(e -> {
            try {
                String title = addCompositeProductView.getProductTitle();
                double rating = Double.parseDouble(addCompositeProductView.getRating());
                int price = Integer.parseInt(addCompositeProductView.getPrice());
                int calories = 0, proteins = 0, fats = 0, sodium = 0;
                for (MenuItem menuItem : products) {
                    calories += menuItem.getCalories();
                    proteins += menuItem.getProteins();
                    fats += menuItem.getFats();
                    sodium += menuItem.getSodium();
                }
                CompositeProduct compositeProduct = new CompositeProduct(title, rating, calories, proteins, fats, sodium, price, products);
                products.clear();
                if (DeliveryService.menuItems.add(compositeProduct)) {
                    deliveryService.addProduct(compositeProduct);
                    manageProductsView.resetTableData(AdminController.getRowDataArray(deliveryService.getTableData(DeliveryService.menuItems)));
                    addCompositeProductView.showSuccess("Composite product added successfully");
                } else {
                    addCompositeProductView.showError("Product already exists!");
                }
            } catch (NumberFormatException ex) {
                addCompositeProductView.showError("Invalid data!");
            }
        });
    }
}
