package jdbcProject2;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CustomerFrame extends JPanel {
	JLabel cNo, cName, cState, cCreditLimit, cRepNo;
	JTextField cNoTF, cNameTF, cStateTF, cCreditLimitTF, cRepNoTF;
	JButton submit, creditLimitButton;
	HigherCreditLimitFrame myFrame;
	public CustomerFrame() {
		cNo = new JLabel("customer frame");
		add(cNo);
		
		cNo = new JLabel("cust no:");
		cName = new JLabel("Cust name:");
		cState = new JLabel("state:");
		cCreditLimit = new JLabel("cred limit:");
		cRepNo= new JLabel("rep no");
		
		
		cNoTF = new JTextField(10);
		cNameTF = new JTextField(10);
		cStateTF = new JTextField(10);
		cCreditLimitTF = new JTextField(10);
		cRepNoTF = new JTextField(10);
		
		submit = new JButton("insert");
		creditLimitButton = new JButton("creditLimit");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(cNo);
		add(cNoTF);
		add(cName);
		add(cNameTF);
		add(cState);
		add(cStateTF);
		add(cCreditLimit);
		add(cCreditLimitTF);
		add(cRepNo);
		add(cRepNoTF);
		add(submit);
		add(creditLimitButton);
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password");
					Statement st = con.createStatement();
					int cno = Integer.parseInt(cNoTF.getText());
					String cuName = cNameTF.getText();
					String cuState = cStateTF.getText();
					int crLimit = Integer.parseInt(cCreditLimitTF.getText());
					int repre = Integer.parseInt(cRepNoTF.getText());
					
					String query = String.format("insert into customer values (%d, '%s', '%s', %d, %d)", cno, cuName, cuState, crLimit, repre);
					st.executeUpdate(query);
					
				} catch(SQLException se) {
					
					System.out.println("error in connecting to database");
				}
				
				JOptionPane.showMessageDialog(CustomerFrame.this, "Recor inserted succesfully");
				
			}
			
		});	
		creditLimitButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFrame = new HigherCreditLimitFrame();
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password");
					Statement st = con.createStatement();
					String query = "select repno, repname from representative where repno in(select repno from customer where creditLimit > 1000)";
					ResultSet rs = st.executeQuery(query);
					while(rs.next()) {
						myFrame.addText(rs.getInt(1) + "\t" + rs.getString(2) + "\n");
					}
				} catch (SQLException seq) {
					System.out.println(seq);
				}
			}
		});
	}
}







/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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

public class CustomerFrame extends JPanel {
    private JLabel cNoLabel, cNameLabel, cStateLabel, cCreditLimitLabel;
    private JTextField cNoTF, cNameTF, cStateTF, cCreditLimitTF;
    private JButton submitButton, displayButton;
    private JTextArea displayArea;

    public CustomerFrame() {
        // Labels
        cNoLabel = new JLabel("Customer No:");
        cNameLabel = new JLabel("Customer Name:");
        cStateLabel = new JLabel("State:");
        cCreditLimitLabel = new JLabel("Credit Limit:");

        // Text fields
        cNoTF = new JTextField(10);
        cNameTF = new JTextField(10);
        cStateTF = new JTextField(10);
        cCreditLimitTF = new JTextField(10);

        // Buttons
        submitButton = new JButton("Insert");
        displayButton = new JButton("Display");

        // Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adding components
        add(cNoLabel);
        add(cNoTF);
        add(cNameLabel);
        add(cNameTF);
        add(cStateLabel);
        add(cStateTF);
        add(cCreditLimitLabel);
        add(cCreditLimitTF);
        add(submitButton);
        add(displayButton);

        // Text area for displaying data
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        add(displayArea);

        // Insert button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password")) {
                    Statement st = con.createStatement();
                    int cno = Integer.parseInt(cNoTF.getText());
                    String cuName = cNameTF.getText();
                    String cuState = cStateTF.getText();
                    int crLimit = Integer.parseInt(cCreditLimitTF.getText());

                    String query = String.format("INSERT INTO Customer (CustNo, CustName, State, Credit_Limit) VALUES (%d, '%s', '%s', %d)", cno, cuName, cuState, crLimit);
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(CustomerFrame.this, "Record inserted successfully");
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(CustomerFrame.this, "Error in connecting to database: " + se.getMessage());
                }
            }
        });

        // Display button action listener
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo6a", "root", "password")) {
                    Statement st = con.createStatement();
                    String query = "SELECT * FROM Customer";
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        int custNo = rs.getInt("CustNo");
                        String custName = rs.getString("CustName");
                        String state = rs.getString("State");
                        int creditLimit = rs.getInt("Credit_Limit");
                        displayArea.append(custNo + "\t" + custName + "\t" + state + "\t" + creditLimit + "\n");
                    }
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(CustomerFrame.this, "Error in connecting to database: " + se.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Customer Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CustomerFrame());
        frame.pack();
        frame.setVisible(true);
    }
}
