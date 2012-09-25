package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import model.DBManager;
import model.Lession;
import model.User;




public class MainUI extends JFrame {
	User currentUser;
	Lession lession[][];
	DBManager dbConnector;
	
	WelcomePanel welcome;
	ChooseLevelPanel chooseLevelPanel;
	ScorePanel scorePanel;
	ListenPanel listenPanel;
	
	JTabbedPane tabbedPane;
	JPanel listen;
	
	static{
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainUI() {
		dbConnector = new DBManager();
		lession = new Lession[3][];
		lession[0] = dbConnector.getLessionByLevel(1);
		lession[1] = dbConnector.getLessionByLevel(2);
		lession[2] = dbConnector.getLessionByLevel(3);
		
		getContentPane().setLayout(new CardLayout(0, 0));
		
		welcome = new WelcomePanel();
		chooseLevelPanel = new ChooseLevelPanel();
		
		listenPanel = new ListenPanel();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		listen = new JPanel();
		
		
		listen.setLayout(null);
		listen.add(chooseLevelPanel);
		listen.add(listenPanel);
		listenPanel.setVisible(false);
		
		getContentPane().add(welcome);
		getContentPane().add(tabbedPane);
		tabbedPane.setVisible(false);
		tabbedPane.addTab("Listening", null, listen, null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(650, 500);
		setResizable(false);
	}
	
	class WelcomePanel extends JPanel{
		private JTextField accountField;
		private JPasswordField passwordField;
		private JLabel welcome1;
		private JLabel welcome2;
		private JLabel lblAccount;
		private JLabel lblPassword;
		private JButton btnLogin;
		private JButton btnSignup;
		
		public WelcomePanel(){
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
					eventLogin(e);
				}
			});
			add(btnLogin);
			
			btnSignup = new JButton("SIGNUP");
			btnSignup.setBounds(310, 302, 91, 23);
			btnSignup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eventSignup(e);
				}
			});
			add(btnSignup);
		}
	
		public void eventLogin(ActionEvent e){
			User user = dbConnector.userAvaiable(accountField.getText());
			if(user == null)
				JOptionPane.showMessageDialog(this, "Create an account, please !!!");
			else if(!user.userPass.equals(passwordField.getText()))
				JOptionPane.showMessageDialog(this, "Wrong information !!!");
			else{
				welcome.setVisible(false);
				tabbedPane.setVisible(true);
				currentUser = user;
				// Bay gio moi khoi tao duoc scorePanel
				scorePanel = new ScorePanel();
				tabbedPane.addTab("High Score", null, scorePanel, null);
			}
		}
		
		public void eventSignup(ActionEvent e){
			User user = dbConnector.userAvaiable(accountField.getText());
			if(user != null)
				JOptionPane.showMessageDialog(this, "Account avaiable !!!");
			else if(accountField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Insert your username !!!");
			else if(passwordField.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Insert your password !!!");
			else{
				dbConnector.insertUser(accountField.getText(), passwordField.getText());
				welcome.setVisible(false);
				tabbedPane.setVisible(true);
				currentUser = dbConnector.userAvaiable(accountField.getText());
				// Bay gio moi khoi tao duoc scorePanel
				scorePanel = new ScorePanel();
				tabbedPane.addTab("High Score", null, scorePanel, null);
			}
		}
	}

	class ChooseLevelPanel extends JPanel{
		JTabbedPane chooseLevelTab;
		JButton testButton;
		JButton listenButton;
		
		JPanel panelLevel[];
		JList listLevel[];
		JScrollPane scrollLevel[];
		String listLV[][];

		
		JScrollPane scrollLevel2;
		JScrollPane scrollLevel3;
		
		
		
		public ChooseLevelPanel(){
			listLV = new String[3][];
			listLevel = new JList[3];
			panelLevel = new JPanel[3];
			scrollLevel = new JScrollPane[3];
			
			// Dua du lieu vao listLV
			for(int i = 0; i < 3; i++){
				listLV[i] = new String[lession[i].length + 2];
				listLV[i][0] = String.format("   %-50s%s", "Lession", "Length");
				listLV[i][1] = "----------------------------------------------------------------";
				for(int j = 2; j < listLV[i].length; j++)
					listLV[i][j] = lession[i][j - 2].toString();
			}
			
			setBounds(10, 11, 609, 412);
			setLayout(null);
			
			chooseLevelTab = new JTabbedPane(JTabbedPane.TOP);
			chooseLevelTab.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			chooseLevelTab.setBounds(10, 11, 589, 349);
			add(chooseLevelTab);
			
			testButton = new JButton("TEST");
			testButton.setBounds(191, 371, 116, 23);
			add(testButton);
			
			listenButton = new JButton("LISTEN");
			listenButton.setBounds(317, 371, 113, 23);
			add(listenButton);
			
			// Them vao cac tab level
			for(int i = 0; i < 3; i++){
				panelLevel[i] = new JPanel();
				panelLevel[i].setLayout(null);
				chooseLevelTab.addTab("Level " + (i + 1), null, panelLevel[i], null);
				
				
				listLevel[i] = new JList(listLV[i]);
				listLevel[i].setFont(new Font("consolas", Font.PLAIN, 14));
				panelLevel[i].add(listLevel[i]);
				
				scrollLevel[i] = new JScrollPane(listLevel[i]);
				scrollLevel[i].setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				scrollLevel[i].setBounds(10, 11, 564, 299);
				panelLevel[i].add(scrollLevel[i]);
			}
		}
	}

	class ScorePanel extends JPanel{
		JPanel userScorePanel;
		JPanel topScorePanel;
		JList userScoreList;
		JList topScoreList;
		JScrollPane listScrollPaneUser;
		JScrollPane listScrollPaneTop;
		JLabel lblUserScore;
		JLabel lblTopScore;
		String userScore[];
		String topScore[];
		
		public ScorePanel(){
			
			userScore = dbConnector.getTopScoreByUser(currentUser.userID);
			topScore = dbConnector.getTopScore();
			
			setLayout(null);
			
			userScorePanel = new JPanel();
			
			userScoreList = new JList(userScore);
			userScoreList.setFont(new Font("consolas", Font.PLAIN, 14));
			userScoreList.setVisibleRowCount(3);
			add(userScoreList);
			
			listScrollPaneUser = new JScrollPane(userScoreList);
			listScrollPaneUser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listScrollPaneUser.setBounds(10, 49, 300, 374);
			add(listScrollPaneUser);
			
			lblUserScore = new JLabel("User score");
			lblUserScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblUserScore.setBounds(103, 24, 82, 14);
			add(lblUserScore);
			
			topScoreList = new JList(topScore);
			topScoreList.setFont(new Font("consolas", Font.PLAIN, 14));
			add(topScoreList);
			
			listScrollPaneTop = new JScrollPane(topScoreList);
			listScrollPaneTop.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listScrollPaneTop.setBounds(320, 50, 307, 373);
			add(listScrollPaneTop);
			
			lblTopScore = new JLabel("Top score");
			lblTopScore.setBounds(444, 24, 75, 14);
			add(lblTopScore);
		}
	}
	
	class ListenPanel extends JPanel{
		JLabel lessNamePanel;
		JLabel trackNameLabel;
		JSlider sliderTrack;
		JSlider sliderVolume;
		JLabel lblVolume;
		JLabel lblCurrentTime;
		JLabel lblTime;
		JTextArea suggestArea;
		JTextArea inputArea;
		JButton btnExit;
		JButton btnLogout;
		JButton btnOtherLession;
		JLabel lblTotalTime;
		JButton playPauseButton;
		
		public ListenPanel(){
			setBounds(10, 11, 609, 412);
			setLayout(null);
			
			lessNamePanel = new JLabel("LESSION : ");
			lessNamePanel.setBounds(10, 11, 400, 14);
			add(lessNamePanel);
			
			trackNameLabel = new JLabel("TRACK :");
			trackNameLabel.setBounds(10, 36, 100, 14);
			add(trackNameLabel);
			
			sliderTrack = new JSlider();
			sliderTrack.setBounds(57, 61, 542, 24);
			add(sliderTrack);
			
			sliderVolume = new JSlider();
			sliderVolume.setBounds(492, 127, 96, 24);
			add(sliderVolume);
			
			lblVolume = new JLabel("Volume");
			lblVolume.setBounds(436, 127, 46, 14);
			add(lblVolume);
			
			lblCurrentTime = new JLabel("Current time : ");
			lblCurrentTime.setBounds(57, 96, 131, 14);
			add(lblCurrentTime);
			
			lblTime = new JLabel("Time : ");
			lblTime.setBounds(492, 96, 96, 14);
			add(lblTime);
			
			suggestArea = new JTextArea();
			suggestArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			suggestArea.setWrapStyleWord(true);
			suggestArea.setLineWrap(true);
			suggestArea.setBounds(10, 162, 578, 84);
			add(suggestArea);
			
			inputArea = new JTextArea();
			inputArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			inputArea.setWrapStyleWord(true);
			inputArea.setLineWrap(true);
			inputArea.setBounds(10, 257, 578, 84);
			add(inputArea);
			
			btnExit = new JButton("Exit");
			btnExit.setBounds(458, 378, 141, 23);
			add(btnExit);
			
			btnLogout = new JButton("Logout");
			btnLogout.setBounds(317, 378, 131, 23);
			add(btnLogout);
			
			btnOtherLession = new JButton("Other Lession");
			btnOtherLession.setBounds(176, 378, 131, 23);
			add(btnOtherLession);
			
			lblTotalTime = new JLabel("Total Time : ");
			lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblTotalTime.setBounds(57, 127, 156, 29);
			add(lblTotalTime);
			
			playPauseButton = new JButton("");
			playPauseButton.setBounds(20, 61, 27, 23);
			add(playPauseButton);
		}
	}
}
