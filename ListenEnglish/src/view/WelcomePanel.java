package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Users;
import DBManager.UserDB;

public class WelcomePanel extends JPanel{
	private JTextField accountField;
	private JPasswordField passwordField;
	private JLabel welcome1;
	private JLabel welcome2;
	private JLabel lblAccount;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JButton btnSignup;
	
	MainUI mainUI;
	
	public WelcomePanel(MainUI mainUI){
		this.mainUI = mainUI;
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(10, 11, 622, 451);
		setLayout(null);
		
		welcome1 = new JLabel("Ch\u00E0o m\u1EEBng \u0111\u1EBFn v\u1EDBi \u1EE9ng d\u1EE5ng");
		welcome1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		welcome1.setHorizontalAlignment(SwingConstants.CENTER);
		welcome1.setBounds(108, 11, 421, 48);
		add(welcome1);
		
		welcome2 = new JLabel("LUY\u1EC6N NGHE TI\u1EBENG ANH");
		welcome2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		welcome2.setHorizontalAlignment(SwingConstants.CENTER);
		welcome2.setBounds(118, 70, 410, 57);
		add(welcome2);
		
		accountField = new JTextField();
		accountField.setBounds(274, 203, 130, 30);
		accountField.setColumns(10);
		add(accountField);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(274, 235, 130, 30);
		add(passwordField);
		
		lblAccount = new JLabel("Account");
		lblAccount.setBounds(195, 211, 69, 14);
		add(lblAccount);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(195, 243, 69, 14);
		add(lblPassword);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(209, 302, 91, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickLogin(e);
			}
		});
		add(btnLogin);
		mainUI.getRootPane().setDefaultButton(btnLogin);
		
		btnSignup = new JButton("SIGNUP");
		btnSignup.setBounds(310, 302, 91, 23);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickSignup(e);
			}
		});
		add(btnSignup);
	}

	public void clickLogin(ActionEvent e){
		Users user = UserDB.getUserByName(accountField.getText());
		if(user == null)
			JOptionPane.showMessageDialog(this, "Wrong information !!!");
		else if(!user.getPassword().equals(String.copyValueOf(passwordField.getPassword())))
			JOptionPane.showMessageDialog(this, "Wrong information !!!");
		else{
			mainUI.getWelcome().setVisible(false);
			mainUI.getTabbedPane().setVisible(true);
			mainUI.getChooseLevelPanel().setVisible(true);
			
			mainUI.setCurrentUser(user);
/////////////// Bay gio moi khoi tao duoc scorePanel
			mainUI.setScorePanel(new ScorePanel(mainUI));
			mainUI.getTabbedPane().addTab("High Score", null, mainUI.getScorePanel(), null);
		}
	}
	
	public void clickSignup(ActionEvent e){
		Users user = UserDB.getUserByName(accountField.getText());
		if(user != null)
			JOptionPane.showMessageDialog(this, "Account avaiable !!!");
		else if(accountField.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Insert your username !!!");
		else if(passwordField.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Insert your password !!!");
		else{
			UserDB.insertUser(new Users(0, passwordField.getText(), accountField.getText()));
			mainUI.getWelcome().setVisible(false);
			mainUI.getTabbedPane().setVisible(true);
			mainUI.setCurrentUser(UserDB.getUserByName(accountField.getText()));
			// Bay gio moi khoi tao duoc scorePanel
			mainUI.setScorePanel(new ScorePanel(mainUI));
			mainUI.getTabbedPane().addTab("High Score", null, mainUI.getScorePanel(), null);
		}
	}
}
