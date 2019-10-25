import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Register extends JFrame {
	JPanel panel;
	JLabel titleLbl;
	JLabel usernameLbl;
	JTextField usernameField;
	JLabel usernameTakenLbl;
	JLabel passLbl;
	JTextField passField;
	JLabel passCheckLbl;
	JTextField passCheckField;
	JLabel passMatchLbl;
	JButton createAccountBtn;
	
	final int WINDOW_WIDTH = 400;
	final int WINDOW_HEIGHT = 400;
	
	Register(){
		initUI();
	}
	
	public void initUI() {
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		setTitle("Account Registration");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// TITLE LABEL
		titleLbl = new JLabel("Account Registration");
		titleLbl.setBounds(100, 15, 200, 30);
		titleLbl.setFont(titleLbl.getFont().deriveFont(18f));
		panel.add(titleLbl);
		
		
		// ************************** USERNAME **************************
		// USERNAME LABEL
		usernameLbl = new JLabel("Enter Username:");
		usernameLbl.setBounds(20, 60, 110 , 35); // x, y, width, height
		panel.add(usernameLbl);
		// USERNAME FIELD
		usernameField = new JTextField();
		usernameField.setEditable(true);
		usernameField.setBounds(140, 60, 220 , 35); // x, y, width, height
		panel.add(usernameField);
		// USERNAME TAKEN LABEL
		usernameTakenLbl = new JLabel("Username is taken.");
		usernameTakenLbl.setBounds(140, 90, 200, 35);
		usernameTakenLbl.setVisible(false);
		panel.add(usernameTakenLbl);
		
		// ************************** PASSWORD **************************
		// PASSWORD LABEL
		passLbl = new JLabel("Enter Password:");
		passLbl.setBounds(20, 135, 110 , 35); // x, y, width, height
		panel.add(passLbl);
		// PASSWORD FIELD
		passField = new JTextField();
		passField.setEditable(true);
		passField.setBounds(140, 135, 220 , 35); // x, y, width, height
		panel.add(passField);
		
		// ************************** PASSWORD CHECK **************************
		// PASSWORD CHECK LABEL
		passCheckLbl = new JLabel("Reenter Password:");
		passCheckLbl.setBounds(20, 210, 110 , 35); // x, y, width, height
		panel.add(passCheckLbl);
		// PASSWORD CHECK FIELD
		passCheckField = new JTextField();
		passCheckField.setEditable(true);
		passCheckField.setBounds(140, 210, 220 , 35); // x, y, width, height
		panel.add(passCheckField);
		// PSSWORD MATCH FIELD
		passMatchLbl = new JLabel("Passwords don't match.");
		passMatchLbl.setBounds(140, 240, 220, 35);
		passMatchLbl.setVisible(false);
		panel.add(passMatchLbl);
		
		// ************************** CREATE ACCOUNT **************************
		// CREATE ACCOUNT BUTTON
		createAccountBtn = new JButton("Create Account");
		createAccountBtn.setBounds(220,300,140,40);
		panel.add(createAccountBtn);
		createAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				boolean validRegistration = false;
				boolean passwordsMatch = passField.getText().equals(passCheckField.getText());
				
				// CHECK PASSWORD FIELDS MATCH
				if (passwordsMatch) {
					passMatchLbl.setVisible(false);
					
					// ************** TEST VARIABLE NEXT LINE, REPLACE WITH DATABASE QUERY ********
					boolean usernameIsTaken = false;
					
					
					
					if (!usernameIsTaken) {
						usernameTakenLbl.setVisible(false);
						
						// ********* VALID LOGIN *************
						validRegistration = true;
					}
					else {
						usernameTakenLbl.setVisible(true);
					}
					
				}
				else {
					passMatchLbl.setVisible(true);
				}
				
				// IF VALID REGISTRATION INFO, INSERT USERNAME AND PASSWORD INTO DATABASE
				if (validRegistration) {
					// INSERT USERNAME AND PASSWORD INTO DATABASE
					// ..
					// ..
					// ..
					
					// DISPLAY SUCCESS MESSAGE
					JOptionPane.showMessageDialog(panel, "Account successfully created!");
					// EXIT
					Register.this.dispose();
				}
				

			}
		});

		
		
		
		
	}
}
