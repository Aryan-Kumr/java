import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PurchaseApp extends JPanel {
    private JLabel itemIdLabel, quantityLabel, itemNameLabel, totalCostLabel;
    private JTextField itemIdField, quantityField, itemNameField, totalCostField;
    private JButton calculateButton, printButton;
    private HashMap<String, Item> items;

    public PurchaseApp() {
        items = new HashMap<>();
        items.put("101", new Item("Laptop", 1000));
        items.put("102", new Item("Phone", 500));
        items.put("103", new Item("Tablet", 300));

        itemIdLabel = new JLabel("Item ID:");
        quantityLabel = new JLabel("Quantity:");
        itemNameLabel = new JLabel("Item Name:");
        totalCostLabel = new JLabel("Total Cost:");

        itemIdField = new JTextField(10);
        quantityField = new JTextField(10);
        itemNameField = new JTextField(10);
        itemNameField.setEditable(false);
        totalCostField = new JTextField(10);
        totalCostField.setEditable(false);

        calculateButton = new JButton("Calculate");
        printButton = new JButton("Print");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(itemIdLabel);
        add(itemIdField);
        add(quantityLabel);
        add(quantityField);
        add(itemNameLabel);
        add(itemNameField);
        add(totalCostLabel);
        add(totalCostField);
        add(calculateButton);
        add(printButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalCost();
            }
        });

        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printFinalCost();
            }
        });
    }

    private void calculateTotalCost() {
        String itemId = itemIdField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Item item = items.get(itemId);
        if (item == null) {
            JOptionPane.showMessageDialog(this, "Invalid Item ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        itemNameField.setText(item.getName());
        double totalCost = item.getPrice() * quantity;
        totalCostField.setText(String.valueOf(totalCost));
    }

    private void printFinalCost() {
        String[] discountOptions = { "No Discount", "10% Discount", "20% Discount" };
        int discountChoice = JOptionPane.showOptionDialog(
                this, "Choose a discount:", "Discount Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                discountOptions, discountOptions[0]);

        if (discountChoice < 0) return;

        double discount = 0;
        if (discountChoice == 1) {
            discount = 0.10;
        } else if (discountChoice == 2) {
            discount = 0.20;
        }

        double totalCost = Double.parseDouble(totalCostField.getText());
        double finalCost = totalCost - (totalCost * discount);

        JOptionPane.showMessageDialog(this, "Final Cost after discount: $" + finalCost, "Final Cost", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Purchase App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PurchaseApp());
        frame.pack();
        frame.setVisible(true);
    }
}

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
