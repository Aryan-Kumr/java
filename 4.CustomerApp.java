import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CustomerApp {
    private static Map<String, Customer> customers = new HashMap<>();
    private static Map<String, Item> items = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showCustomerDialog());
    }

    private static void showCustomerDialog() {
        String[] options = {"New Customer", "Existing Customer"};
        int choice = JOptionPane.showOptionDialog(null, "Are you a new customer or existing customer?", "Customer Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String customerId;
        if (choice == 0) {
            customerId = JOptionPane.showInputDialog("Enter new Customer ID:");
            String mobileNumber = JOptionPane.showInputDialog("Enter Mobile Number:");
            customers.put(mobileNumber, new Customer(customerId, mobileNumber));
        } else {
            String mobileNumber = JOptionPane.showInputDialog("Enter Mobile Number:");
            Customer customer = customers.get(mobileNumber);
            if (customer != null) {
                customerId = customer.getId();
                JOptionPane.showMessageDialog(null, "Customer ID: " + customerId);
            } else {
                JOptionPane.showMessageDialog(null, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        showPurchaseDialog(customerId);
    }

    private static void showPurchaseDialog(String customerId) {
        JFrame frame = new JFrame("Purchase Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2));

        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField();
        JLabel itemNameLabel = new JLabel("Item Name:");
        JTextField itemNameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel totalCostLabel = new JLabel("Total Cost:");
        JTextField totalCostField = new JTextField();
        totalCostField.setEditable(false);
        JButton addButton = new JButton("Add Item");
        JButton discountButton = new JButton("Discount Options");
        JButton printButton = new JButton("Print Details");

        addButton.addActionListener(e -> {
            String itemId = itemIdField.getText();
            String itemName = itemNameField.getText();
            String quantityStr = quantityField.getText();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price for " + itemName + ":"));
            items.put(itemId, new Item(itemId, itemName, price));
            double totalCost = price * quantity;
            totalCostField.setText(String.valueOf(totalCost));
        });

        discountButton.addActionListener(e -> {
            String[] discounts = {"No Discount", "10% Discount", "20% Discount"};
            int discountChoice = JOptionPane.showOptionDialog(frame, "Select a discount", "Discount Options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, discounts, discounts[0]);
            JOptionPane.showMessageDialog(frame, "Selected Discount: " + discounts[discountChoice]);
        });

        printButton.addActionListener(e -> {
            String details = "Customer ID: " + customerId +
                    "\nItem: " + itemNameField.getText() +
                    "\nTotal Cost: " + totalCostField.getText();
            JOptionPane.showMessageDialog(frame, details, "Purchase Details", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.add(itemIdLabel);
        frame.add(itemIdField);
        frame.add(itemNameLabel);
        frame.add(itemNameField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(addButton);
        frame.add(new JLabel());
        frame.add(totalCostLabel);
        frame.add(totalCostField);
        frame.add(discountButton);
        frame.add(printButton);

        frame.setVisible(true);
    }
}

class Customer {
    private String id;
    private String mobileNumber;

    public Customer(String id, String mobileNumber) {
        this.id = id;
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}

class Item {
    private String id;
    private String name;
    private double price;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}