package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageProductsView extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JMenuItem menuItemEdit;
    private final JMenuItem menuItemRemove;
    private final JMenuItem menuItemAdd;
    private final JButton searchButton;
    private final JButton addBaseProduct;
    private final JButton addCompositeProduct;
    private final JTextField keywordField;

    public ManageProductsView(String[] columnNames, String[][] rowData) {
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);

        JPopupMenu popupMenu = new JPopupMenu();
        menuItemEdit = new JMenuItem("Edit selected product");
        menuItemRemove = new JMenuItem("Remove selected product");
        menuItemAdd = new JMenuItem("Add item to new composite product");
        popupMenu.add(menuItemEdit);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemAdd);

        table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(new ProductsTableMouseListener(table));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel searchPane = new JPanel();
        JLabel searchLabel = new JLabel("Search by keyword: ");
        keywordField = new JTextField();
        keywordField.setPreferredSize(new Dimension(100, 20));
        searchButton = new JButton("Search");
        searchPane.add(searchLabel);
        searchPane.add(keywordField);
        searchPane.add(searchButton);
        contentPane.add(searchPane);

        JScrollPane tablePane = new JScrollPane();
        tablePane.setViewportView(table);
        contentPane.add(tablePane);

        JPanel buttonPane = new JPanel();
        addBaseProduct = new JButton("Add new base product");
        addCompositeProduct = new JButton("Add new composite product");
        buttonPane.add(addBaseProduct);
        buttonPane.add(addCompositeProduct);
        contentPane.add(buttonPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
    }

    public void addRemoveProductActionListener(ActionListener rpal) {
        menuItemRemove.addActionListener(rpal);
    }

    public void addEditProductActionListener(ActionListener epal) {
        menuItemEdit.addActionListener(epal);
    }

    public void addAddProductToCompositeActionListener(ActionListener aptcal) {
        menuItemAdd.addActionListener(aptcal);
    }

    public void addAddBaseProductActionListener(ActionListener abpal) {
        addBaseProduct.addActionListener(abpal);
    }

    public void addAddCompositeProductActionListener(ActionListener acpal) {
        addCompositeProduct.addActionListener(acpal);
    }

    public void addSearchActionListener(ActionListener sal) {
        searchButton.addActionListener(sal);
    }

    public String getKeyword() {
        return keywordField.getText();
    }

    public void setTableData(String[][] tableData) {
        if (tableData == null) {
            while (tableModel.getRowCount() != 0) {
                tableModel.removeRow(0);
            }
        } else {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (i < tableData.length) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        table.setValueAt(tableData[i][j], i, j);
                    }
                }
            }
            while (tableModel.getRowCount() > tableData.length) {
                tableModel.removeRow(tableData.length);
            }
        }
    }

    public void resetTableData(String[][] tableData) {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }

        for (String[] tableDatum : tableData) {
            tableModel.addRow(tableDatum);
        }
    }

    public void removeGivenRow(int rowToRemove) {
        tableModel.removeRow(rowToRemove);
    }

    public void editGivenRow(java.util.List<String> information) {
        int row = Integer.parseInt(information.get(0));
        information.remove(0);
        int j = 0;
        for(String s: information) {
            table.setValueAt(s, row, j++);
        }
    }

    public java.util.List<String> getCurrentRowInformation() {
        int selectedRow = table.getSelectedRow();
        java.util.List<String> currentRow = new ArrayList<>();
        currentRow.add("" + selectedRow);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            currentRow.add((String) tableModel.getValueAt(selectedRow, i));
        }
        return currentRow;
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "No User Found", JOptionPane.ERROR_MESSAGE);
    }
}
