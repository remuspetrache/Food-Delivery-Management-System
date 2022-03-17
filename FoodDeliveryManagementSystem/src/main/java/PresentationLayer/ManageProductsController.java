package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import java.util.List;
import java.util.TreeSet;

public class ManageProductsController {
    protected TreeSet<MenuItem> itemsForComposite = new TreeSet<>();

    public ManageProductsController(ManageProductsView manageProductsView, DeliveryService deliveryService) {
        manageProductsView.addSearchActionListener(e -> {
            String keyword = manageProductsView.getKeyword();
            manageProductsView.resetTableData(AdminController.getRowDataArray(deliveryService.getTableData(DeliveryService.menuItems)));
            if (!keyword.equals("")) {
                TreeSet<MenuItem> filteredMenuItems = deliveryService.searchProductByKeyword(keyword);
                List<List<String>> tableData = deliveryService.getTableData(filteredMenuItems);
                manageProductsView.setTableData(AdminController.getRowDataArray(tableData));
            }
        });
        manageProductsView.addRemoveProductActionListener(e -> {
            List<String> selectedRow = manageProductsView.getCurrentRowInformation();
            BaseProduct baseProduct = new BaseProduct(selectedRow.get(1));
            deliveryService.deleteProduct(baseProduct);
            manageProductsView.removeGivenRow(Integer.parseInt(selectedRow.get(0)));
        });
        manageProductsView.addEditProductActionListener(e -> {
            List<String> selectedRow = manageProductsView.getCurrentRowInformation();
            ModifyProductView modifyProductView = new ModifyProductView("Modify");
            ModifyProductController modifyProductController = new ModifyProductController(manageProductsView, modifyProductView, selectedRow, deliveryService);
            modifyProductView.setVisible(true);
        });
        manageProductsView.addAddBaseProductActionListener(e -> {
            ModifyProductView modifyProductView = new ModifyProductView("Add");
            AddBaseProductController addBaseProductController = new AddBaseProductController(modifyProductView, manageProductsView, deliveryService);
            modifyProductView.setVisible(true);
        });
        manageProductsView.addAddProductToCompositeActionListener(e -> {
            List<String> selectedRow = manageProductsView.getCurrentRowInformation();
            for (MenuItem menuItem : DeliveryService.menuItems) {
                if (menuItem.getTitle().equals(selectedRow.get(1))) {
                    itemsForComposite.add(menuItem);
                }
            }
        });
        manageProductsView.addAddCompositeProductActionListener(e -> {
            if (itemsForComposite.isEmpty()) {
                manageProductsView.showError("Please add some products first!");
            } else {
                AddCompositeProductView addCompositeProductView = new AddCompositeProductView();
                AddCompositeProductController addCompositeProductController = new AddCompositeProductController(addCompositeProductView, manageProductsView, deliveryService, itemsForComposite);
                addCompositeProductView.setVisible(true);
            }
        });
    }
}
