import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingApp {
    private static JTextField itemIdField, quantityField, itemNameField, totalCostField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shopping App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel itemIdLabel = new JLabel("Item ID:");
        itemIdLabel.setBounds(20, 20, 100, 30);
        frame.add(itemIdLabel);

        itemIdField = new JTextField();
        itemIdField.setBounds(120, 20, 200, 30);
        frame.add(itemIdField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 60, 100, 30);
        frame.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(120, 60, 200, 30);
        frame.add(quantityField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(20, 100, 120, 30);
        frame.add(calculateButton);

        JLabel itemNameLabel = new JLabel("Item Name:");
        itemNameLabel.setBounds(20, 140, 100, 30);
        frame.add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(120, 140, 200, 30);
        itemNameField.setEditable(false);
        frame.add(itemNameField);

        JLabel totalCostLabel = new JLabel("Total Cost:");
        totalCostLabel.setBounds(20, 180, 100, 30);
        frame.add(totalCostLabel);

        totalCostField = new JTextField();
        totalCostField.setBounds(120, 180, 200, 30);
        totalCostField.setEditable(false);
        frame.add(totalCostField);

        JButton printButton = new JButton("Print");
        printButton.setBounds(250, 100, 120, 30);
        frame.add(printButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemId = itemIdField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                String itemName = "Item" + itemId; // Simple example
                double itemPrice = 10.0; // Example price
                double totalCost = itemPrice * quantity;

                itemNameField.setText(itemName);
                totalCostField.setText(String.valueOf(totalCost));

                String[] options = {"10% Discount", "15% Discount", "20% Discount"};
                int discountOption = JOptionPane.showOptionDialog(frame, 
                        "Select Discount Type:", 
                        "Discount Options", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, 
                        null, options, options[0]);
                
                double discount = 0.0;
                switch (discountOption) {
                    case 0 -> discount = 0.1;
                    case 1 -> discount = 0.15;
                    case 2 -> discount = 0.2;
                }

                totalCost = totalCost * (1 - discount);
                totalCostField.setText(String.valueOf(totalCost));
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = itemNameField.getText();
                String totalCost = totalCostField.getText();
                JOptionPane.showMessageDialog(frame, "Item: " + itemName + "\nFinal Cost: " + totalCost, 
                        "Final Bill", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
