package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;

public class AddBaseProductController {

    public AddBaseProductController(ModifyProductView modifyProductView, ManageProductsView manageProductsView, DeliveryService deliveryService) {
        modifyProductView.addSubmitActionListener(e -> {
            try {
                String title = modifyProductView.getProductTitle();
                double rating = Double.parseDouble(modifyProductView.getRating());
                int calories = Integer.parseInt(modifyProductView.getCalories());
                int proteins = Integer.parseInt(modifyProductView.getProteins());
                int fats = Integer.parseInt(modifyProductView.getFats());
                int sodium = Integer.parseInt(modifyProductView.getSodium());
                int price = Integer.parseInt(modifyProductView.getPrice());
                BaseProduct baseProduct = new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
                if (DeliveryService.menuItems.add(baseProduct)) {
                    manageProductsView.resetTableData(AdminController.getRowDataArray(deliveryService.getTableData(DeliveryService.menuItems)));
                    modifyProductView.showSuccess("Base product added successfully");
                } else {
                    modifyProductView.showError("Product already exists!");
                }
            } catch (NumberFormatException ex) {
                modifyProductView.showError("Invalid data!");
            }
        });
    }
}
