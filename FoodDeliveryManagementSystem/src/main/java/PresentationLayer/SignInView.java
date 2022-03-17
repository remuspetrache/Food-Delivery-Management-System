package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignInView extends JFrame {
    private final JTextField userField;
    private final JPasswordField passField;
    private final JButton signInButton;
    private final JButton createButton;

    public SignInView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 410, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        userLabel.setBounds(90, 55, 70, 17);
        contentPane.add(userLabel);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        passLabel.setBounds(90, 100, 70, 17);
        contentPane.add(passLabel);

        userField = new JTextField();
        userField.setBounds(160, 56, 150, 19);
        contentPane.add(userField);
        userField.setColumns(10);

        passField = new JPasswordField();
        passField.setColumns(10);
        passField.setBounds(160, 101, 150, 20);
        contentPane.add(passField);

        JLabel signInLabel = new JLabel("Sign In Restaurant Application");
        signInLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signInLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        signInLabel.setBounds(77, 10, 250, 17);
        contentPane.add(signInLabel);

        signInButton = new JButton("Sign In");
        signInButton.setBounds(170, 140, 80, 20);
        contentPane.add(signInButton);

        JLabel createAccLabel = new JLabel("Or create a new account here");
        createAccLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
        createAccLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAccLabel.setBounds(125, 200, 170, 15);
        contentPane.add(createAccLabel);

        createButton = new JButton("Create");
        createButton.setBounds(170, 225, 80, 20);
        contentPane.add(createButton);
    }

    public void addLogInListener(ActionListener lal) {
        signInButton.addActionListener(lal);
    }

    public void addCreateListener(ActionListener cal) {
        createButton.addActionListener(cal);
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "No User Found", JOptionPane.ERROR_MESSAGE);
    }

}
