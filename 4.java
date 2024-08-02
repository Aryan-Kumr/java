package practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class lab3 extends JFrame {
	
	public lab3() {
		
		
		JLabel label1=new JLabel("sgpa1");
		JTextField tf1=new JTextField(10);
		
		JLabel label2=new JLabel("sgpa2");
		JTextField tf2=new JTextField(10);
		
		JLabel label3=new JLabel("sgpa3");
		JTextField tf3=new JTextField(10);
		
		JPanel panel=new JPanel();
		
		JButton btn1=new JButton("calculate");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createPanel(tf1,label1));
		panel.add(createPanel(tf2,label2));
		panel.add(createPanel(tf3,label3));
		panel.add(btn1);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double one=Double.parseDouble(tf1.getText());
				Double two=Double.parseDouble(tf2.getText());
				Double three=Double.parseDouble(tf3.getText());
				Double gpa=(one+two+three)/3.00;
				JOptionPane.showMessageDialog(panel, gpa);
			}
		});
		add(panel);
		setVisible(true);
		pack();
		
		
	}
	private JPanel createPanel(JTextField tf,JLabel label) {
		JPanel panel=new JPanel();
		panel.add(label);
		panel.add(tf);
		return panel;
		
	}
	public static void main(String[] args) {
		
new lab3();
	}

}
