package PresentationLayer;

import BusinessLayer.DeliveryService;
import java.util.List;

public class AdminController {

    public AdminController(AdminView adminView, DeliveryService deliveryService) {
        adminView.addImportInitialActionListener(e -> {
            deliveryService.importBaseProduct();
            adminView.showSuccess("Initial products have been successfully imported!");
        });
        adminView.addImportLastSavedDataActionListener(e -> {
            deliveryService.loadInformation();
            adminView.showSuccess("Last save data has been successfully imported!");
        });
        adminView.addSaveCurrentDataActionListener(e -> {
            deliveryService.storeInformation();
            adminView.showSuccess("Data has been successfully saved");
        });
        adminView.addGenerateReport1ActionListener(e -> {
            try {
                int startingHour = Integer.parseInt(adminView.getStartingHour());
                int endingHour = Integer.parseInt(adminView.getEndingHour());
                if (startingHour > endingHour || startingHour < 0 || startingHour > 24 || endingHour > 24) {
                    throw new NumberFormatException();
                }
                deliveryService.generateTimeIntervalReport(startingHour, endingHour);
                adminView.showSuccess("Report has been successfully generated!");
            } catch (NumberFormatException ex) {
                adminView.showError("Please enter valid data!");
            }
        });
        adminView.addGenerateReport2ActionListener(e -> {
            try {
                int specifiedNumber = Integer.parseInt(adminView.getReport2Field());
                deliveryService.generateProductNumberReport(specifiedNumber);
                adminView.showSuccess("Report has been successfully generated!");
            } catch (NumberFormatException ex) {
                adminView.showError("Please enter valid data!");
            }
        });
        adminView.addGenerateReport3ActionListener(e -> {
            try {
                int specifiedNumber = Integer.parseInt(adminView.getReport3TimesField());
                int orderAmount = Integer.parseInt(adminView.getReport3OrderField());
                deliveryService.generateClientValueReport(specifiedNumber, orderAmount);
                adminView.showSuccess("Report has been successfully generated!");
            } catch (NumberFormatException ex) {
                adminView.showError("Please enter valid data!");
            }
        });
        adminView.addGenerateReport4ActionListener(e -> {
            try {
                int day = Integer.parseInt(adminView.getReport4Day());
                int month = Integer.parseInt(adminView.getReport4Month());
                int year = Integer.parseInt(adminView.getReport4Year());
                if (day <= 0 || year < 0 || month <= 0 || day > 31 || month > 12) {
                    throw new NumberFormatException();
                }
                deliveryService.generateDayTimesReport(year, month, day);
                adminView.showSuccess("Report has been successfully generated!");
            } catch (NumberFormatException ex) {
                adminView.showError("Please enter valid data!");
            }
        });
        adminView.addManageProducts(e -> {
            String[] columnNames = getColumnNamesArray(deliveryService.getColumnNames());
            String[][] rowData = getRowDataArray(deliveryService.getTableData(DeliveryService.menuItems));
            ManageProductsView manageProductsView = new ManageProductsView(columnNames, rowData);
            ManageProductsController manageProductsController = new ManageProductsController(manageProductsView, deliveryService);
            manageProductsView.setVisible(true);
        });
    }

    protected static String[] getColumnNamesArray(List<String> columnNames) {
        String[] columnNamesArray = new String[columnNames.size()];
        int i = 0;
        for (String s : columnNames) {
            columnNamesArray[i] = columnNames.get(i++);
        }
        return columnNamesArray;
    }

    protected static String[][] getRowDataArray(List<List<String>> rowData) {
        if (rowData.isEmpty()) {
            return null;
        }
        String[][] rowDataArray = new String[rowData.size()][rowData.get(0).size()];
        int i = 0;
        for (Object s : rowData) {
            Object[] array = rowData.get(i).toArray();
            int j = 0;
            for (Object obj : array) {
                rowDataArray[i][j++] = (String) obj;
            }
            i++;
        }
        return rowDataArray;
    }
}
