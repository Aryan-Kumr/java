import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentInformationApp extends JPanel {
    private JLabel nameLabel, usnLabel, ageLabel, addressLabel, sgpa1Label, sgpa2Label, sgpa3Label, sgpa4Label, categoryLabel;
    private JTextField nameField, usnField, ageField, addressField, sgpa1Field, sgpa2Field, sgpa3Field, sgpa4Field, categoryField;
    private JTextArea displayArea;
    private ArrayList<Student> students;

    public StudentInformationApp() {
        students = new ArrayList<>();

        nameLabel = new JLabel("Name:");
        usnLabel = new JLabel("USN:");
        ageLabel = new JLabel("Age:");
        addressLabel = new JLabel("Address:");
        sgpa1Label = new JLabel("SGPA 1:");
        sgpa2Label = new JLabel("SGPA 2:");
        sgpa3Label = new JLabel("SGPA 3:");
        sgpa4Label = new JLabel("SGPA 4:");
        categoryLabel = new JLabel("Category:");

        nameField = new JTextField(10);
        usnField = new JTextField(10);
        ageField = new JTextField(10);
        addressField = new JTextField(10);
        sgpa1Field = new JTextField(10);
        sgpa2Field = new JTextField(10);
        sgpa3Field = new JTextField(10);
        sgpa4Field = new JTextField(10);
        categoryField = new JTextField(10);

        JButton computeButton = new JButton("Compute CGPA");
        JButton doneButton = new JButton("Done");
        JButton displayButton = new JButton("Display Students");

        setLayout(new GridLayout(12, 2)); // 12 rows, 2 columns

        add(nameLabel);
        add(nameField);
        add(usnLabel);
        add(usnField);
        add(ageLabel);
        add(ageField);
        add(addressLabel);
        add(addressField);
        add(sgpa1Label);
        add(sgpa1Field);
        add(sgpa2Label);
        add(sgpa2Field);
        add(sgpa3Label);
        add(sgpa3Field);
        add(sgpa4Label);
        add(sgpa4Field);
        add(categoryLabel);
        add(categoryField);
        add(computeButton);
        add(doneButton);
        add(displayButton);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));

        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeCGPA();
            }
        });

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
    }

    private void computeCGPA() {
        try {
            double sgpa1 = Double.parseDouble(sgpa1Field.getText());
            double sgpa2 = Double.parseDouble(sgpa2Field.getText());
            double sgpa3 = Double.parseDouble(sgpa3Field.getText());
            double sgpa4 = Double.parseDouble(sgpa4Field.getText());

            double cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4.0;
            JOptionPane.showMessageDialog(this, "CGPA: " + cgpa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid SGPA values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            String usn = usnField.getText();
            int age = Integer.parseInt(ageField.getText());
            String address = addressField.getText();
            double sgpa1 = Double.parseDouble(sgpa1Field.getText());
            double sgpa2 = Double.parseDouble(sgpa2Field.getText());
            double sgpa3 = Double.parseDouble(sgpa3Field.getText());
            double sgpa4 = Double.parseDouble(sgpa4Field.getText());
            String category = categoryField.getText();

            Student student = new Student(name, usn, age, address, sgpa1, sgpa2, sgpa3, sgpa4, category);
            students.add(student);
            JOptionPane.showMessageDialog(this, "Student details added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudents() {
        StringBuilder studentDetails = new StringBuilder();
        for (Student student : students) {
            studentDetails.append(student).append("\n");
        }
        displayArea.setText(studentDetails.toString());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new StudentInformationApp());
        frame.pack();
        frame.setVisible(true);
    }
}

class Student {
    private String name, usn, address, category;
    private int age;
    private double sgpa1, sgpa2, sgpa3, sgpa4;

    public Student(String name, String usn, int age, String address, double sgpa1, double sgpa2, double sgpa3, double sgpa4, String category) {
        this.name = name;
        this.usn = usn;
        this.age = age;
        this.address = address;
        this.sgpa1 = sgpa1;
        this.sgpa2 = sgpa2;
        this.sgpa3 = sgpa3;
        this.sgpa4 = sgpa4;
        this.category = category;
    }

    public double getCGPA() {
        return (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4.0;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", USN: " + usn + ", Age: " + age + ", Address: " + address + ", SGPA 1: " + sgpa1 + ", SGPA 2: " + sgpa2 + ", SGPA 3: " + sgpa3 + ", SGPA 4: " + sgpa4 + ", Category: " + category + ", CGPA: " + getCGPA();
    }
}
