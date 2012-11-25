package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import model.Lesson;
import model.User;
import model.db.LessonDB;

public class MainUI extends JFrame {
	private User currentUser;
	private Lesson lessons[][];
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
		lessons = new Lesson[3][];
		lessons[0] = LessonDB.getLessonByLevel(1);
		lessons[1] = LessonDB.getLessonByLevel(2);
		lessons[2] = LessonDB.getLessonByLevel(3);
		
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
		setLocationRelativeTo(null);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Lesson[][] getLessions() {
		return lessons;
	}
	
	public Lesson[] getLessons(int i) {
		return lessons[i];
	}
	
	public Lesson getLesson(int i, int j) {
		return lessons[i][j];
	}

	public void setLesson(Lesson[][] lession) {
		this.lessons = lession;
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
