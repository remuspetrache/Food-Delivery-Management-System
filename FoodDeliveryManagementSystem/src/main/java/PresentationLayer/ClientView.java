package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientView extends JFrame {
    private final JTextField keywordField;
    private final JTextField priceField;
    private final JTextField ratingField;
    private final JTextField caloriesField;
    private final JTextField proteinsField;
    private final JTextField fatsField;
    private final JTextField sodiumField;
    private final JButton searchButton;
    private final JButton placeOrderButton;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JMenuItem menuItemAdd;

    public ClientView(String[] columnNames, String[][] rowData) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel upperPanel = new JPanel();
        upperPanel.setBounds(10, 10, 426, 150);
        contentPane.add(upperPanel);
        upperPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        welcomeLabel.setBounds(160, 10, 100, 13);
        upperPanel.add(welcomeLabel);

        JLabel searchLabel = new JLabel("Search for products by:");
        searchLabel.setBounds(10, 45, 140, 13);
        upperPanel.add(searchLabel);

        JLabel keywordLabel = new JLabel("Keyword:");
        keywordLabel.setBounds(10, 68, 70, 13);
        upperPanel.add(keywordLabel);

        keywordField = new JTextField();
        keywordField.setBounds(65, 66, 96, 19);
        upperPanel.add(keywordField);
        keywordField.setColumns(10);

        JLabel priceLabel = new JLabel("Price >=");
        priceLabel.setBounds(180, 68, 60, 13);
        upperPanel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(233, 66, 30, 19);
        upperPanel.add(priceField);
        priceField.setColumns(10);

        JLabel ratingLabel = new JLabel("Rating >= ");
        ratingLabel.setBounds(283, 68, 80, 13);
        upperPanel.add(ratingLabel);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(343, 66, 30, 19);
        upperPanel.add(ratingField);

        JLabel caloriesLabel = new JLabel("Calories >=");
        caloriesLabel.setBounds(10, 96, 80, 13);
        upperPanel.add(caloriesLabel);

        caloriesField = new JTextField();
        caloriesField.setColumns(10);
        caloriesField.setBounds(80, 94, 30, 19);
        upperPanel.add(caloriesField);

        JLabel proteinsLabel = new JLabel("Proteins >=");
        proteinsLabel.setBounds(120, 96, 80, 13);
        upperPanel.add(proteinsLabel);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(190, 94, 30, 19);
        upperPanel.add(proteinsField);

        JLabel fatLabel = new JLabel("Fats >=");
        fatLabel.setBounds(235, 96, 45, 13);
        upperPanel.add(fatLabel);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(280, 94, 30, 19);
        upperPanel.add(fatsField);

        JLabel sodiumLabel = new JLabel("Sodium >=");
        sodiumLabel.setBounds(320, 96, 80, 13);
        upperPanel.add(sodiumLabel);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(383, 94, 30, 19);
        upperPanel.add(sodiumField);

        searchButton = new JButton("Search");
        searchButton.setBounds(150, 123, 85, 21);
        upperPanel.add(searchButton);

        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);

        JPopupMenu popupMenu = new JPopupMenu();
        menuItemAdd = new JMenuItem("Add item to current order");
        popupMenu.add(menuItemAdd);

        table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(new ProductsTableMouseListener(table));

        JScrollPane tablePane = new JScrollPane();
        tablePane.setViewportView(table);
        tablePane.setBounds(5, 170, 426, 291);
        contentPane.add(tablePane);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(136, 471, 120, 21);
        contentPane.add(placeOrderButton);
    }

    public void addSearchActionListener(ActionListener sal) {
        searchButton.addActionListener(sal);
    }

    public void addPlaceOrderActionListener(ActionListener poal) {
        placeOrderButton.addActionListener(poal);
    }

    public void addAddProductToOrder(ActionListener ptoal) {
        menuItemAdd.addActionListener(ptoal);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Account already exists", JOptionPane.ERROR_MESSAGE);
    }

    public void resetTableData(String[][] tableData) {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }

        for (String[] tableDatum : tableData) {
            tableModel.addRow(tableDatum);
        }
    }

    public java.util.List<String> getCurrentRowInformation() {
        java.util.List<String> currentRow = new ArrayList<>();
        int selected = table.getSelectedRow();
        currentRow.add("" + selected);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            currentRow.add((String) tableModel.getValueAt(selected, i));
        }
        return currentRow;
    }

    public String getKeyword() {
        return keywordField.getText();
    }

    public String getRating() {
        return ratingField.getText();
    }

    public String getCalories() {
        return caloriesField.getText();
    }

    public String getProteins() {
        return proteinsField.getText();
    }

    public String getFats() {
        return fatsField.getText();
    }

    public String getSodium() {
        return sodiumField.getText();
    }

    public String getPrice() {
        return priceField.getText();
    }
}
