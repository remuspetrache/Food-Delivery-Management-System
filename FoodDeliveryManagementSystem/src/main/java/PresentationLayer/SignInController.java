package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.Role;

public class SignInController {
    private final DeliveryService deliveryService = new DeliveryService();
    private final EmployeeView employeeView = new EmployeeView();

    public SignInController(SignInView signInView) {
        deliveryService.addObserver(employeeView);
        signInView.addLogInListener(e -> {
            String username = signInView.getUsername();
            String password = signInView.getPassword();
            Role role = deliveryService.verifyLogIn(username, password);
            if (role == null) {
                signInView.showError("No user exists with these credentials!\n" +
                        "Verify the log in credentials or create a new account if you don't have one");
            } else {
                switch (role) {
                    case EMPLOYEE -> employeeView.setVisible(true);
                    case ADMIN -> {
                        AdminView adminView = new AdminView();
                        AdminController adminController = new AdminController(adminView, deliveryService);
                        adminView.setVisible(true);
                    }
                    case CLIENT -> {
                        int clientID = deliveryService.getCurrentClientID(username, password);
                        String[] columnNames = AdminController.getColumnNamesArray(deliveryService.getColumnNames());
                        String[][] rowData = AdminController.getRowDataArray(deliveryService.getTableData(DeliveryService.menuItems));
                        ClientView clientView = new ClientView(columnNames, rowData);
                        ClientController clientController = new ClientController(clientView, deliveryService, clientID);
                        clientView.setVisible(true);
                    }
                }
            }
        });
        signInView.addCreateListener(e -> {
            CreateAccountView createAccountView = new CreateAccountView();
            CreateAccountController createAccountController = new CreateAccountController(createAccountView);
            createAccountView.setVisible(true);
        });
    }
}
