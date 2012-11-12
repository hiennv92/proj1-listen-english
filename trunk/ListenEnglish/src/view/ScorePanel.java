package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import DBManager.ListenDB;

public class ScorePanel extends JPanel{
	private JPanel userScorePanel;
	private JPanel topScorePanel;
	private JList userScoreList;
	private JList topScoreList;
	private JScrollPane listScrollPaneUser;
	private JScrollPane listScrollPaneTop;
	private JLabel lblUserScore;
	private JLabel lblTopScore;
	private String userScore[];
	private String topScore[];
	
	private MainUI mainUI;
	
	public ScorePanel(MainUI mainUI){
		this.mainUI = mainUI;
		
		userScore = ListenDB.getTopScoreByUser(mainUI.getCurrentUser().getID());
		topScore = ListenDB.getTopScore();
		
		setLayout(null);
		
		userScorePanel = new JPanel();
		
		if(userScore != null)
			userScoreList = new JList(userScore);
		else
			userScoreList = new JList();
		
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
		
		if(topScore != null)
			topScoreList = new JList(topScore);
		else
			topScoreList = new JList();
		
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
	
	public void refreshScore(){
		userScore = ListenDB.getTopScoreByUser(mainUI.getCurrentUser().getID());
		topScore = ListenDB.getTopScore();
		
		if(userScore != null)
			userScoreList.setListData(userScore);
		if(topScore != null)
			topScoreList.setListData(topScore);
	}
}
