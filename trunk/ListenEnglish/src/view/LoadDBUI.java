package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.DBManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadDBUI extends JFrame {
	private JTextField hostnameField;
	private JTextField portField;
	private JTextField userField;
	private JTextField passField;
	private JTextField databaseField;
	
	MainUI mainUI;
	DBManager dbConnection;
	
	public LoadDBUI(MainUI mainUI, DBManager dbConnection) {
		this.mainUI = mainUI;
		setSize(400, 220);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblHostname = new JLabel("Hostname");
		lblHostname.setBounds(10, 11, 79, 14);
		getContentPane().add(lblHostname);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(236, 11, 40, 14);
		getContentPane().add(lblPort);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 57, 46, 14);
		getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 104, 46, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setBounds(10, 146, 46, 14);
		getContentPane().add(lblDatabase);
		
		hostnameField = new JTextField();
		hostnameField.setBounds(72, 8, 154, 20);
		getContentPane().add(hostnameField);
		hostnameField.setColumns(10);
		
		portField = new JTextField();
		portField.setBounds(286, 8, 91, 20);
		getContentPane().add(portField);
		portField.setColumns(10);
		
		userField = new JTextField();
		userField.setBounds(72, 54, 214, 20);
		getContentPane().add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setBounds(72, 101, 214, 20);
		getContentPane().add(passField);
		passField.setColumns(10);
		
		databaseField = new JTextField();
		databaseField.setBounds(72, 143, 214, 20);
		getContentPane().add(databaseField);
		databaseField.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnConnect.setBounds(296, 53, 81, 49);
		getContentPane().add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnExit.setBounds(296, 113, 81, 47);
		getContentPane().add(btnExit);
	}
	
	
}
