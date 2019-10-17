import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class Login extends JFrame {
	
	HashMap<String, Integer> usernameAttemptMap;
	JFrame frame;
	JPanel panel;
	JTextField userField;
	JLabel userLabel;
	JTextField passField;
	JLabel passLabel;
	JButton registerBtn;
	JButton loginBtn;
	JLabel invalidUserLbl;
	JLabel invalidPassLbl;
	
	int attempts = 0;
	
	boolean validLogin;
	
	final int WINDOW_WIDTH = 450;
	final int WINDOW_HEIGHT = 300;
	
	Login(){
		initUI();
	}
	
	private void initUI(){
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		// Set the JFrame attributes
		setTitle("User Login");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// USERNAME FIELD
		userField = new JTextField("Enter username");
		userField.setEditable(true);
		userField.setBounds(120, 60, 250 , 35); // x, y, width, height
		panel.add(userField);
		// USERNAME LABEL
		userLabel = new JLabel("Username:");
		userLabel.setBounds(25, 60, 70 , 35); // x, y, width, height
		panel.add(userLabel);
		
		
		// PASSWORD FIELD
		passField = new JTextField("Enter password");
		passField.setEditable(true);
		passField.setBounds(120, 120, 250 , 35); // x, y, width, height
		panel.add(passField);
		// PASSWORD LABEL
		passLabel = new JLabel("Password:");
		passLabel.setBounds(25, 120, 70 , 35); // x, y, width, height
		panel.add(passLabel);
		
		// REGISTER BUTTON
		registerBtn = new JButton("Register");	
		registerBtn.setBounds(225, 215, 90, 40);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// REGISTER WINDOW OPEN
			}
		});
		panel.add(registerBtn);
		
		// LOGIN BUTTON
		loginBtn = new JButton("Login");
		loginBtn.setBounds(330, 215, 80, 40);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validLogin = false;
				
				// attempt to log in
				String username = userField.getText();
				String password = passField.getText();
				
				// connect to database and compare both fields
				
				// For testing:
				//validLogin = true;
				if (validLogin) {
					System.exit(0);
				}
			}
		});
		panel.add(loginBtn);
		
		// INVALID USERNAME LABEL
		invalidUserLbl = new JLabel("Invalid username. Please register an account.");
		invalidUserLbl.setBounds(120, 150, 300 , 35); // x, y, width, height
		invalidUserLbl.setVisible(false);
		panel.add(invalidUserLbl);
		
		// INVALID PASSWORD LABEL
		invalidPassLbl = new JLabel("Invalid password. " + (5-attempts) + " attempts left.");
		invalidPassLbl.setBounds(120, 150, 300 , 35); // x, y, width, height
		invalidPassLbl.setVisible(false);
		panel.add(invalidPassLbl);
		
		
		
	}
	
	public boolean validateLogin() {
		
		return true; //  
	}
	

	
	
}
