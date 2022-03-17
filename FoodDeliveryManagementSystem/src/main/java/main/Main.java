package main;

import BusinessLayer.*;
import PresentationLayer.SignInController;
import PresentationLayer.SignInView;

public class Main {

    public static void main(String[] args) {
        Admin a1 = new Admin("remus", "petrache", Role.ADMIN);
        Client c1 = new Client("client", "parola", Role.CLIENT, 1, "Mihai");
        Employee e1 = new Employee("employee", "password", Role.EMPLOYEE);
        DeliveryService.users.add(a1);
        DeliveryService.users.add(c1);
        DeliveryService.users.add(e1);
        SignInView signInView = new SignInView();
        SignInController signInController = new SignInController(signInView);
        signInView.setVisible(true);
    }
}

