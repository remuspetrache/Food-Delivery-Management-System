package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private final JTextField startingHourField;
    private final JTextField endingHourField;
    private final JTextField report2Field;
    private final JTextField report3TimesField;
    private final JTextField report3OrderField;
    private final JTextField report4FieldDay;
    private final JTextField report4FieldMonth;
    private final JTextField report4FieldYear;
    private final JButton importInitialButton;
    private final JButton importLastSavedButton;
    private final JButton saveButton;
    private final JButton manageButton;
    private final JButton generateReport1Button;
    private final JButton generateReport2Button;
    private final JButton generateReport3Button;
    private final JButton generateReport4Button;

    public AdminView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 370);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Admin Page");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(120, 10, 200, 20);
        contentPane.add(titleLabel);

        JLabel initialImportLabel = new JLabel("Import initial products");
        initialImportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        initialImportLabel.setBounds(20, 60, 150, 13);
        contentPane.add(initialImportLabel);

        importInitialButton = new JButton("Import");
        importInitialButton.setFont(new Font("Tahoma", Font.BOLD, 8));
        importInitialButton.setBounds(60, 83, 65, 15);
        contentPane.add(importInitialButton);

        JLabel importLastSavedLabel = new JLabel("Import last saved data");
        importLastSavedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        importLastSavedLabel.setBounds(20, 108, 150, 13);
        contentPane.add(importLastSavedLabel);

        importLastSavedButton = new JButton("Import");
        importLastSavedButton.setFont(new Font("Tahoma", Font.BOLD, 8));
        importLastSavedButton.setBounds(60, 127, 65, 15);
        contentPane.add(importLastSavedButton);

        JLabel saveDataLabel = new JLabel("Save current data");
        saveDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        saveDataLabel.setBounds(20, 152, 150, 13);
        contentPane.add(saveDataLabel);

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 8));
        saveButton.setBounds(60, 170, 65, 15);
        contentPane.add(saveButton);

        JLabel manageProductsLabel = new JLabel("Manage saved products");
        manageProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        manageProductsLabel.setBounds(20, 195, 150, 13);
        contentPane.add(manageProductsLabel);

        manageButton = new JButton("Manage");
        manageButton.setFont(new Font("Tahoma", Font.BOLD, 8));
        manageButton.setBounds(60, 218, 65, 15);
        contentPane.add(manageButton);

        JLabel generateReportsLabel = new JLabel("Generate reports:");
        generateReportsLabel.setBounds(273, 60, 150, 13);
        contentPane.add(generateReportsLabel);

        JLabel report1Label = new JLabel("Orders performed between");
        report1Label.setBounds(210, 83, 160, 13);
        contentPane.add(report1Label);

        startingHourField = new JTextField();
        startingHourField.setBounds(367, 80, 22, 19);
        contentPane.add(startingHourField);
        startingHourField.setColumns(10);

        JLabel andLabel = new JLabel("and");
        andLabel.setBounds(391, 83, 45, 13);
        contentPane.add(andLabel);

        endingHourField = new JTextField();
        endingHourField.setColumns(10);
        endingHourField.setBounds(414, 80, 22, 19);
        contentPane.add(endingHourField);

        generateReport1Button = new JButton("Generate");
        generateReport1Button.setFont(new Font("Tahoma", Font.BOLD, 8));
        generateReport1Button.setBounds(285, 106, 75, 15);
        contentPane.add(generateReport1Button);

        JLabel report2Label = new JLabel("Products ordered more than");
        report2Label.setBounds(210, 139, 170, 13);
        contentPane.add(report2Label);

        report2Field = new JTextField();
        report2Field.setColumns(10);
        report2Field.setBounds(374, 136, 22, 19);
        contentPane.add(report2Field);

        JLabel timesField = new JLabel("times");
        timesField.setBounds(400, 139, 45, 13);
        contentPane.add(timesField);

        generateReport2Button = new JButton("Generate");
        generateReport2Button.setFont(new Font("Tahoma", Font.BOLD, 8));
        generateReport2Button.setBounds(285, 166, 75, 15);
        contentPane.add(generateReport2Button);

        JLabel report3Label = new JLabel("Clients that have ordered more than ");
        report3Label.setBounds(210, 195, 240, 13);
        contentPane.add(report3Label);

        report3TimesField = new JTextField();
        report3TimesField.setColumns(10);
        report3TimesField.setBounds(417, 192, 22, 19);
        contentPane.add(report3TimesField);

        JLabel timesField2 = new JLabel("times");
        timesField2.setBounds(441, 195, 45, 13);
        contentPane.add(timesField2);

        JLabel lblNewLabel = new JLabel("and the value of the order was higher than");
        lblNewLabel.setBounds(210, 214, 260, 13);
        contentPane.add(lblNewLabel);

        report3OrderField = new JTextField();
        report3OrderField.setColumns(10);
        report3OrderField.setBounds(453, 212, 22, 19);
        contentPane.add(report3OrderField);

        generateReport3Button = new JButton("Generate");
        generateReport3Button.setFont(new Font("Tahoma", Font.BOLD, 8));
        generateReport3Button.setBounds(285, 238, 75, 15);
        contentPane.add(generateReport3Button);

        JLabel report4Label = new JLabel("Products ordered in");
        report4Label.setBounds(210, 268, 140, 13);
        contentPane.add(report4Label);

        report4FieldDay = new JTextField();
        report4FieldDay.setColumns(10);
        report4FieldDay.setBounds(327, 265, 22, 19);
        contentPane.add(report4FieldDay);

        JLabel auxLabel1 = new JLabel(".");
        auxLabel1.setBounds(349, 268, 45, 13);
        contentPane.add(auxLabel1);

        report4FieldMonth = new JTextField();
        report4FieldMonth.setColumns(10);
        report4FieldMonth.setBounds(355, 265, 22, 19);
        contentPane.add(report4FieldMonth);

        JLabel auxLabel2 = new JLabel(".");
        auxLabel2.setBounds(377, 268, 45, 13);
        contentPane.add(auxLabel2);

        report4FieldYear = new JTextField();
        report4FieldYear.setColumns(10);
        report4FieldYear.setBounds(383, 265, 35, 19);
        contentPane.add(report4FieldYear);

        generateReport4Button = new JButton("Generate");
        generateReport4Button.setFont(new Font("Tahoma", Font.BOLD, 8));
        generateReport4Button.setBounds(285, 291, 75, 15);
        contentPane.add(generateReport4Button);
    }

    public void addImportInitialActionListener(ActionListener iial) {
        importInitialButton.addActionListener(iial);
    }

    public void addImportLastSavedDataActionListener(ActionListener ilsdal) {
        importLastSavedButton.addActionListener(ilsdal);
    }

    public void addSaveCurrentDataActionListener(ActionListener scdal) {
        saveButton.addActionListener(scdal);
    }

    public void addManageProducts(ActionListener mpal) {
        manageButton.addActionListener(mpal);
    }

    public void addGenerateReport1ActionListener(ActionListener gr1al) {
        generateReport1Button.addActionListener(gr1al);
    }

    public void addGenerateReport2ActionListener(ActionListener gr2al) {
        generateReport2Button.addActionListener(gr2al);
    }

    public void addGenerateReport3ActionListener(ActionListener gr3al) {
        generateReport3Button.addActionListener(gr3al);
    }

    public void addGenerateReport4ActionListener(ActionListener gr4al) {
        generateReport4Button.addActionListener(gr4al);
    }

    public String getStartingHour() {
        return startingHourField.getText();
    }

    public String getEndingHour() {
        return endingHourField.getText();
    }

    public String getReport2Field() {
        return report2Field.getText();
    }

    public String getReport3TimesField() {
        return report3TimesField.getText();
    }

    public String getReport3OrderField() {
        return report3OrderField.getText();
    }

    public String getReport4Day() {
        return report4FieldDay.getText();
    }

    public String getReport4Month() {
        return report4FieldMonth.getText();
    }

    public String getReport4Year() {
        return report4FieldYear.getText();
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage, "Account created successfully", JOptionPane.INFORMATION_MESSAGE);
    }
}
