package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddCompositeProductView extends JFrame {

    private final JTextField titleField;
    private final JTextField ratingField;
    private final JTextField priceField;
    private final JButton submitButton;

    public AddCompositeProductView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 180, 250);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Add Composite Product");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        titleLabel.setBounds(10, 10, 180, 13);
        contentPane.add(titleLabel);

        JLabel productTitleLabel = new JLabel("Title:");
        productTitleLabel.setBounds(10, 50, 45, 13);
        contentPane.add(productTitleLabel);

        titleField = new JTextField();
        titleField.setBounds(46, 48, 96, 19);
        contentPane.add(titleField);
        titleField.setColumns(10);

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(10, 90, 45, 13);
        contentPane.add(ratingLabel);

        ratingField = new JTextField();
        ratingField.setBounds(55, 88, 40, 19);
        contentPane.add(ratingField);
        ratingField.setColumns(10);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 130, 60, 13);
        contentPane.add(priceLabel);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(65, 128, 40, 19);
        contentPane.add(priceField);

        submitButton = new JButton("Add");
        submitButton.setBounds(46, 164, 85, 21);
        contentPane.add(submitButton);
    }

    public void addSubmitActionListener(ActionListener sal) {
        submitButton.addActionListener(sal);
    }

    public String getProductTitle() {
        return titleField.getText();
    }

    public String getRating() {
        return ratingField.getText();
    }

    public String getPrice() {
        return priceField.getText();
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Account created successfully", JOptionPane.INFORMATION_MESSAGE);
    }
}
