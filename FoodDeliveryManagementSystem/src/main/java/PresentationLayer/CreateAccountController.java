package PresentationLayer;

import BusinessLayer.Client;
import BusinessLayer.DeliveryService;
import BusinessLayer.Role;

public class CreateAccountController {
    private final DeliveryService deliveryService = new DeliveryService();

    public CreateAccountController(CreateAccountView createAccountView) {
        createAccountView.addCreateListener(e -> {
            String username = createAccountView.getUsername();
            String password = createAccountView.getPassword();
            String clientName = createAccountView.getNameOfUser();
            if (deliveryService.isAccountCreated(username)) {
                createAccountView.showError("This account username is already taken!\n" +
                        "Please choose a different username");
            } else {
                int clientID = deliveryService.getMaxClientID() + 1;
                Client client = new Client(username, password, Role.CLIENT, clientID, clientName);
                DeliveryService.users.add(client);
                createAccountView.showSuccess("Your account has been created successfully!");
            }
        });
    }
}
