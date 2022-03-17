package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView extends JFrame {
    private final JTextField nameField;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton createButton;

    public CreateAccountView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Create a new account");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(95, 20, 200, 17);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(90, 65, 45, 13);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 63, 165, 20);
        contentPane.add(nameField);
        nameField.setColumns(10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(90, 100, 80, 13);
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(155, 97, 140, 20);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(90, 135, 80, 13);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(155, 132, 140, 20);
        contentPane.add(passwordField);
        passwordField.setColumns(10);

        createButton = new JButton("Create");
        createButton.setBounds(145, 178, 85, 21);
        contentPane.add(createButton);
    }

    public void addCreateListener(ActionListener cal) {
        createButton.addActionListener(cal);
    }

    public String getNameOfUser() {
        return nameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Account created successfully", JOptionPane.INFORMATION_MESSAGE);
    }
}
