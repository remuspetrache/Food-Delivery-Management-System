package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModifyProductView extends JFrame {
    private final JTextField titleField;
    private final JTextField ratingField;
    private final JTextField caloriesField;
    private final JTextField proteinsField;
    private final JTextField fatsField;
    private final JTextField sodiumField;
    private final JTextField priceField;
    private final JButton submitButton;

    public ModifyProductView(String name) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 180, 380);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel(name + " Product");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        titleLabel.setBounds(10, 10, 140, 13);
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

        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setBounds(10, 130, 60, 13);
        contentPane.add(caloriesLabel);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(65, 128, 40, 19);
        contentPane.add(caloriesField);

        JLabel proteinsLabel = new JLabel("Proteins:");
        proteinsLabel.setBounds(10, 170, 60, 13);
        contentPane.add(proteinsLabel);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(65, 168, 40, 19);
        contentPane.add(proteinsField);

        JLabel fatsLabel = new JLabel("Fats:");
        fatsLabel.setBounds(10, 210, 45, 13);
        contentPane.add(fatsLabel);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(40, 208, 40, 19);
        contentPane.add(fatsField);

        JLabel sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setBounds(10, 250, 65, 13);
        contentPane.add(sodiumLabel);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(60, 248, 40, 19);
        contentPane.add(sodiumField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 290, 45, 13);
        contentPane.add(priceLabel);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(45, 288, 40, 19);
        contentPane.add(priceField);

        submitButton = new JButton(name);
        submitButton.setBounds(40, 315, 85, 21);
        contentPane.add(submitButton);
    }

    public void addSubmitActionListener(ActionListener sal) {
        submitButton.addActionListener(sal);
    }

    public String getProductTitle() {
        return titleField.getText();
    }

    public void setProductTitle(String title) {
        titleField.setText(title);
    }

    public String getRating() {
        return ratingField.getText();
    }

    public void setRating(String rating) {
        ratingField.setText(rating);
    }

    public String getCalories() {
        return caloriesField.getText();
    }

    public void setCalories(String calories) {
        caloriesField.setText(calories);
    }

    public String getProteins() {
        return proteinsField.getText();
    }

    public void setProteins(String proteins) {
        proteinsField.setText(proteins);
    }

    public String getFats() {
        return fatsField.getText();
    }

    public void setFats(String fats) {
        fatsField.setText(fats);
    }

    public String getSodium() {
        return sodiumField.getText();
    }

    public void setSodium(String sodium) {
        sodiumField.setText(sodium);
    }

    public String getPrice() {
        return priceField.getText();
    }

    public void setPrice(String price) {
        priceField.setText(price);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Account created successfully", JOptionPane.INFORMATION_MESSAGE);
    }
}
