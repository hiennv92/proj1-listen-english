package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import audio.PlayAudio;

public class ChooseLevelPanel extends JPanel{
	private JTabbedPane chooseLevelTab;
	private JButton testButton;
	private JButton listenButton;
	
	private JPanel panelLevel[];
	private JList listLevel[];
	private JScrollPane scrollLevel[];
	private String listLV[][];
	
	private JScrollPane scrollLevel2;
	private JScrollPane scrollLevel3;
	
	MainUI mainUI;
	
	public ChooseLevelPanel(MainUI mainUI){
		this.mainUI = mainUI;
		
		listLV = new String[3][];
		listLevel = new JList[3];
		panelLevel = new JPanel[3];
		scrollLevel = new JScrollPane[3];
		
		// Dua du lieu vao listLV
		for(int i = 0; i < 3; i++){
			listLV[i] = new String[mainUI.getLessons(i).length + 2];
			listLV[i][0] = String.format("   %-50s%s", "Lession", "Length");
			listLV[i][1] = "----------------------------------------------------------------";
			for(int j = 2; j < listLV[i].length; j++){
				listLV[i][j] = mainUI.getLesson(i, j - 2).toString();
			}
		}
		
		setBounds(10, 11, 609, 412);
		setLayout(null);
		
		chooseLevelTab = new JTabbedPane(JTabbedPane.TOP);
		chooseLevelTab.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		chooseLevelTab.setBounds(10, 11, 589, 349);
		add(chooseLevelTab);
		
		testButton = new JButton("TEST");
		testButton.setBounds(191, 371, 116, 23);
		testButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickTest(e);
			}
			
		});
		add(testButton);
		
		listenButton = new JButton("LISTEN");
		listenButton.setBounds(317, 371, 113, 23);
		listenButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				clickListen(e);
			}
		});
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
	
	public void clickTest(ActionEvent e){
		
	}
	
	public void clickListen(ActionEvent e){
		int tab = chooseLevelTab.getSelectedIndex();
		int lessonOrder = listLevel[tab].getSelectedIndex() - 2;
		
		if(lessonOrder < 0)
			return;
		
		mainUI.getListenPanel().setPlayer(new PlayAudio(mainUI.getLesson(tab, lessonOrder)));
		mainUI.getChooseLevelPanel().setVisible(false);
		mainUI.getListenPanel().setVisible(true);
	}
}
