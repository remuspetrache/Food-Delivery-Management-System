package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeView extends JFrame implements Observer {
    private final JTextArea textArea;
    private final JLabel titleLabel;

    public EmployeeView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        titleLabel = new JLabel("No orders have been placed yet");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        titleLabel.setBounds(10, 15, 400, 15);
        contentPane.add(titleLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 418, 213);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }

    @Override
    public void update(Observable o, Object arg) {
        titleLabel.setText("The following orders have been placed:");
        textArea.setText((String) arg);
    }
}
