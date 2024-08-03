package jdbcProject2;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RepresentativeFrame extends JPanel {
    JLabel rNo, rName, rState, rComission, rRate;
    JTextField rNoTF, rNameTF, rStateTF, rComissionTF, rRateTF;
    JButton submit, display;
    JTextArea displayArea;

    public RepresentativeFrame() {
        rNo = new JLabel("Rep No:");
        rName = new JLabel("Rep Name:");
        rState = new JLabel("State:");
        rComission = new JLabel("Comission:");
        rRate = new JLabel("Rate:");

        rNoTF = new JTextField(10);
        rNameTF = new JTextField(10);
        rStateTF = new JTextField(10);
        rComissionTF = new JTextField(10);
        rRateTF = new JTextField(10);

        submit = new JButton("Insert");
        display = new JButton("Display");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(rNo);
        add(rNoTF);
        add(rName);
        add(rNameTF);
        add(rState);
        add(rStateTF);
        add(rComission);
        add(rComissionTF);
        add(rRate);
        add(rRateTF);
        add(submit);
        add(display);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        add(displayArea);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password")) {
                    Statement st = con.createStatement();
                    int rno = Integer.parseInt(rNoTF.getText());
                    String rName = rNameTF.getText();
                    String rState = rStateTF.getText();
                    int rComission = Integer.parseInt(rComissionTF.getText());
                    int rRate = Integer.parseInt(rRateTF.getText());

                    String query = String.format("INSERT INTO Representative VALUES (%d, '%s', '%s', %d, %d)", rno, rName, rState, rComission, rRate);
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(RepresentativeFrame.this, "Record inserted successfully");
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(RepresentativeFrame.this, "Error in connecting to database: " + se.getMessage());
                }
            }
        });

        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password")) {
                    Statement st = con.createStatement();
                    String query = "SELECT * FROM Representative";
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        int repNo = rs.getInt("RepNo");
                        String repName = rs.getString("RepName");
                        String state = rs.getString("State");
                        int comission = rs.getInt("Comission");
                        int rate = rs.getInt("Rate");
                        displayArea.append(repNo + "\t" + repName + "\t" + state + "\t" + comission + "\t" + rate + "\n");
                    }
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(RepresentativeFrame.this, "Error in connecting to database: " + se.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Representative Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RepresentativeFrame());
        frame.pack();
        frame.setVisible(true);
    }
}
