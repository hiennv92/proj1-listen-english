package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import model.ConnectDB;
import model.Lessions;
import model.Users;
import DBManager.LessionDB;

public class MainUI extends JFrame {
	private Users currentUser;
	private Lessions lession[][];
	private Lessions currentLession;
	
	private WelcomePanel welcome;
	private ChooseLevelPanel chooseLevelPanel;
	private ScorePanel scorePanel;
	private ListenPanel listenPanel;
	
	private JTabbedPane tabbedPane;
	private JPanel listen;
	
	static{
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainUI() {
		// khoi tao cac tham so ban dau cho co so du lieu
		ConnectDB.initInfor("root", "12345", "127.0.0.1", "3306", "listeningenglish");
		// ket noi vao co so du lieu
		ConnectDB.connect();
		
		lession = new Lessions[3][];
		lession[0] = LessionDB.getLessionByLevel(1);
		lession[1] = LessionDB.getLessionByLevel(2);
		lession[2] = LessionDB.getLessionByLevel(3);
		
		getContentPane().setLayout(new CardLayout(0, 0));
		
		welcome = new WelcomePanel(this);
		chooseLevelPanel = new ChooseLevelPanel(this);
		
		listenPanel = new ListenPanel(this);
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

	public Users getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
	}

	public Lessions[][] getLession() {
		return lession;
	}
	
	public Lessions[] getLession(int i) {
		return lession[i];
	}
	
	public Lessions getLession(int i, int j) {
		return lession[i][j];
	}

	public void setLession(Lessions[][] lession) {
		this.lession = lession;
	}

	public WelcomePanel getWelcome() {
		return welcome;
	}

	public void setWelcome(WelcomePanel welcome) {
		this.welcome = welcome;
	}

	public ChooseLevelPanel getChooseLevelPanel() {
		return chooseLevelPanel;
	}

	public void setChooseLevelPanel(ChooseLevelPanel chooseLevelPanel) {
		this.chooseLevelPanel = chooseLevelPanel;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public ListenPanel getListenPanel() {
		return listenPanel;
	}

	public void setListenPanel(ListenPanel listenPanel) {
		this.listenPanel = listenPanel;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JPanel getListen() {
		return listen;
	}

	public void setListen(JPanel listen) {
		this.listen = listen;
	}

}
