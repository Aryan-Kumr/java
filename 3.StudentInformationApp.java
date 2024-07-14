import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentInformationApp {
    private JFrame frame;
    private JTextField nameField, usnField, ageField, addressField, sgpa1Field, sgpa2Field, sgpa3Field, sgpa4Field, categoryField;
    private JTextArea displayArea;
    private ArrayList<Student> students;

    public StudentInformationApp() {
        students = new ArrayList<>();
        frame = new JFrame("Student Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(12, 2));    //12 rows 2 colms

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("USN:"));
        usnField = new JTextField();
        frame.add(usnField);

        frame.add(new JLabel("Age:"));
        ageField = new JTextField();
        frame.add(ageField);

        frame.add(new JLabel("Address:"));
        addressField = new JTextField();
        frame.add(addressField);

        frame.add(new JLabel("SGPA 1:"));
        sgpa1Field = new JTextField();
        frame.add(sgpa1Field);

        frame.add(new JLabel("SGPA 2:"));
        sgpa2Field = new JTextField();
        frame.add(sgpa2Field);

        frame.add(new JLabel("SGPA 3:"));
        sgpa3Field = new JTextField();
        frame.add(sgpa3Field);

        frame.add(new JLabel("SGPA 4:"));
        sgpa4Field = new JTextField();
        frame.add(sgpa4Field);

        frame.add(new JLabel("Category:"));
        categoryField = new JTextField();
        frame.add(categoryField);

        JButton computeButton = new JButton("Compute CGPA");
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeCGPA();
            }
        });
        frame.add(computeButton);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        frame.add(doneButton);

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
        frame.add(displayButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea));

        frame.setVisible(true);
    }

    private void computeCGPA() {
        try {
            double sgpa1 = Double.parseDouble(sgpa1Field.getText());
            double sgpa2 = Double.parseDouble(sgpa2Field.getText());
            double sgpa3 = Double.parseDouble(sgpa3Field.getText());
            double sgpa4 = Double.parseDouble(sgpa4Field.getText());

            double cgpa = (sgpa1 + sgpa2 + sgpa3 + sgpa4) / 4.0;
            JOptionPane.showMessageDialog(frame, "CGPA: " + cgpa);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid SGPA values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
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
        JOptionPane.showMessageDialog(frame, "Student details added successfully.");
    }

    private void displayStudents() {
        StringBuilder studentDetails = new StringBuilder();
        for (Student student : students) {
            studentDetails.append(student).append("\n");
        }
        displayArea.setText(studentDetails.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentInformationApp();
            }
        });
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