package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddOrderView extends JFrame {
    private final JTextField yearField;
    private final JTextField monthField;
    private final JTextField dayField;
    private final JTextField hourField;
    private final JTextField textField;
    private final JButton submitButton;

    public AddOrderView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 180, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Create New Order");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(10, 10, 170, 13);
        contentPane.add(titleLabel);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(10, 50, 45, 13);
        contentPane.add(yearLabel);

        yearField = new JTextField();
        yearField.setBounds(43, 48, 40, 19);
        contentPane.add(yearField);
        yearField.setColumns(10);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(10, 90, 45, 13);
        contentPane.add(monthLabel);

        monthField = new JTextField();
        monthField.setColumns(10);
        monthField.setBounds(52, 88, 30, 19);
        contentPane.add(monthField);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setBounds(10, 130, 45, 13);
        contentPane.add(dayLabel);

        dayField = new JTextField();
        dayField.setColumns(10);
        dayField.setBounds(37, 128, 30, 19);
        contentPane.add(dayField);

        JLabel hourLabel = new JLabel("Hour:");
        hourLabel.setBounds(10, 170, 45, 13);
        contentPane.add(hourLabel);

        hourField = new JTextField();
        hourField.setColumns(10);
        hourField.setBounds(45, 168, 30, 19);
        contentPane.add(hourField);

        JLabel minLabel = new JLabel("Minute:");
        minLabel.setBounds(10, 210, 50, 13);
        contentPane.add(minLabel);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(55, 208, 30, 19);
        contentPane.add(textField);

        submitButton = new JButton("Place Order");
        submitButton.setBounds(10, 235, 140, 21);
        contentPane.add(submitButton);
    }

    public void addSubmitButtonListener(ActionListener sal) {
        submitButton.addActionListener(sal);
    }

    public String getYear() {
        return yearField.getText();
    }

    public String getMonth() {
        return monthField.getText();
    }

    public String getDay() {
        return dayField.getText();
    }

    public String getHour() {
        return hourField.getText();
    }

    public String getMinute() {
        return textField.getText();
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Account created successfully", JOptionPane.INFORMATION_MESSAGE);
    }

}
