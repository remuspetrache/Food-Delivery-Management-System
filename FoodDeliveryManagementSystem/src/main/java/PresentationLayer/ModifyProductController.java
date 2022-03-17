package PresentationLayer;

import BusinessLayer.DeliveryService;

import java.util.ArrayList;
import java.util.List;

public class ModifyProductController {

    public ModifyProductController(ManageProductsView manageProductsView, ModifyProductView modifyProductView, List<String> information, DeliveryService deliveryService) {
        modifyProductView.setProductTitle(information.get(1));
        modifyProductView.setRating(information.get(2));
        modifyProductView.setCalories(information.get(3));
        modifyProductView.setProteins(information.get(4));
        modifyProductView.setFats(information.get(5));
        modifyProductView.setSodium(information.get(6));
        modifyProductView.setPrice(information.get(7));
        modifyProductView.addSubmitActionListener(e -> {
            try {
                List<String> newInformation = new ArrayList<>();
                newInformation.add(information.get(0));
                newInformation.add(modifyProductView.getProductTitle());
                newInformation.add(modifyProductView.getRating());
                newInformation.add(modifyProductView.getCalories());
                newInformation.add(modifyProductView.getProteins());
                newInformation.add(modifyProductView.getFats());
                newInformation.add(modifyProductView.getSodium());
                newInformation.add(modifyProductView.getPrice());
                deliveryService.editProduct(information.get(1), newInformation);
                manageProductsView.editGivenRow(newInformation);
                modifyProductView.showSuccess("Product modified successfully!");
            } catch (NumberFormatException ex) {
                modifyProductView.showError("Invalid data!");
            }

        });
    }
}
