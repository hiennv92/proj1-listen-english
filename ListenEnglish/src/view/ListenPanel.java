package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Lesson;
import model.db.ConnectDB;
import model.db.ListenDB;
import control.AnswerText;
import control.PlayAudio;
import control.SuggestionText;
import control.Utility;

public class ListenPanel extends JPanel{
	private JLabel lessNameLabel;
	private JLabel trackNameLabel;
	private JSlider sliderTrack;
	private JSlider sliderVolume;
	private JLabel lblVolume;
	private JLabel lblCurrentTime;
	private JLabel lblTime;
	private JTextArea suggestArea;
	private JTextField inputArea;
	private JButton btnRestart;
	
	private JButton btnOtherLession;
	private JLabel lblTotalTime;
	private JButton playPauseButton;
	private JButton nextButton;
	private MainUI mainUI;
	private SuggestionText suggestionText;
	
	// lesson hien tai dang nghe
	private Lesson currentLesson;
	// Thu tu cua track hien tai dang chay
	private int currentTrackOrder;
	
	private PlayAudio player = null;
	// trang thai cua trinh phat nhac co chay hay khong
	private boolean isPlay = true;
	// kiem tra lan dau tien click
	private boolean firstPlay = true;
	
	// Thread tong thoi gian
	TotalTimeThread ttThread;
	SliderThread sliderThread;
	
	public PlayAudio getPlayer() {
		return player;
	}

	public void setPlayer(PlayAudio player) {
		this.player = player;
		currentLesson = player.getLesson();
		currentTrackOrder = 0;
		
		lessNameLabel.setText("LESSON : " + player.getLesson().getName());
//		aText.setAnswer(player.getCurrentScript());
		suggestionText.setScriptText(player.getCurrentScript());
		suggestionText.setSuggestionText(player.getCurrentSuggestionText());
		
		sliderVolume.setMaximum(100);
		sliderVolume.setValue(player.getVolumn());
		
		init();
		// chay luong slider cho bai nhac
		sliderThread = new SliderThread();
		sliderTrack.setValue(0);
		// chay luong cap nhat tong thoi gian
		ttThread = new TotalTimeThread();
		// cai dat lai la lan chay dau tien
		firstPlay = true;
		// khung nhap bi disable
		inputArea.setEditable(false);
		// set lai trang thai
		lblTotalTime.setText("Total Time : 0:00:00");
		playPauseButton.setText("Play");
		
		suggestArea.setText("");
		inputArea.setText("");
	}

	public ListenPanel(MainUI mainUI){
		this.mainUI = mainUI;
		
		setBounds(10, 11, 609, 412);
		setLayout(null);
		
		lessNameLabel = new JLabel("LESSION : ");
		lessNameLabel.setBounds(10, 11, 400, 14);
		add(lessNameLabel);
		
		trackNameLabel = new JLabel("TRACK :");
		trackNameLabel.setBounds(10, 36, 400, 14);
		add(trackNameLabel);
		
		sliderTrack = new JSlider();
		sliderTrack.setBounds(120, 61, 430, 24);
		sliderTrack.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				draggOnTrackSlider(e);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
		});
		add(sliderTrack);
		
		sliderVolume = new JSlider();
		sliderVolume.setBounds(492, 127, 96, 24);
		sliderVolume.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				draggOnTrackVolume(e);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
		});
		add(sliderVolume);
		
		lblVolume = new JLabel("Volume");
		lblVolume.setBounds(436, 127, 46, 14);
		add(lblVolume);
		
		lblCurrentTime = new JLabel("Current time : 0:00:00");
		lblCurrentTime.setBounds(127, 96, 131, 14);
		add(lblCurrentTime);
		
		lblTime = new JLabel("Time : ");
		lblTime.setBounds(492, 96, 96, 14);
		add(lblTime);
		
		suggestArea = new JTextArea();
		suggestArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		suggestArea.setFont(new Font("consolas", Font.BOLD, 14));
		suggestArea.setWrapStyleWord(true);
		suggestArea.setLineWrap(true);
		suggestArea.setBounds(10, 162, 578, 84);
		suggestArea.setEditable(false);
		add(suggestArea);
		
		inputArea = new JTextField();
		inputArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//inputArea.setWrapStyleWord(true);
		//inputArea.setLineWrap(true);
		inputArea.setFont(new Font("consolas", Font.BOLD, 30));
		inputArea.setHorizontalAlignment(JTextField.CENTER);
		inputArea.setBounds(10, 257, 578, 84);
		inputArea.setEditable(false);
		inputArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				checkCharInput(arg0.getKeyChar());
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		add(inputArea);
		
		btnRestart = new JButton("Restart");
		btnRestart.setBounds(399, 378, 141, 23);
		btnRestart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clickRestart(e);
			}
		});
		add(btnRestart);
		
		btnOtherLession = new JButton("Back");
		btnOtherLession.setBounds(250, 378, 131, 23);
		btnOtherLession.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickOtherLession(e);
			}
			
		});
		add(btnOtherLession);
		
		lblTotalTime = new JLabel("Total Time : 0:00:00");
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalTime.setBounds(57, 127, 156, 29);
		add(lblTotalTime);
		
		playPauseButton = new JButton("Play");
		playPauseButton.setBounds(0, 61, 100, 30);
		playPauseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickPlayPause(e);
			}
			
		});
		add(playPauseButton);
		
		nextButton = new JButton("N");
		nextButton.setBounds(50, 61, 50, 30);
		nextButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickNext(e);
			}
			
		});
		//add(nextButton);
		suggestionText = new SuggestionText();
		
	}
	
	public void clickRestart(ActionEvent e){
		restart();
	}
	
	public void clickPlayPause(ActionEvent e){
		player.setState(!player.getState());
		// dat lai text cho button 
		if(player.getState())
		{
			playPauseButton.setText("Pause");
			inputArea.requestFocus();
		}
		else
			playPauseButton.setText("Play");
		// neu la lan dau tien chay thi chay timer
		if(firstPlay){
			ttThread.start();
			sliderThread.start();
			inputArea.setEditable(true);
			firstPlay = false;
		}
		//inputArea.setEditable(player.getState());
		sliderTrack.setMaximum(player.getCurrentTrack().getLength());
	}
	
	public void clickOtherLession(ActionEvent e){
		isPlay = false;
		synchronized(player){
			player.stop();
			player = null;
		}
		mainUI.getChooseLevelPanel().setVisible(true);
		mainUI.getListenPanel().setVisible(false);
	}
	
	public void clickNext(ActionEvent e){
		next();
	}
	
	private void draggOnTrackSlider(MouseEvent e) {
		player.setCurrentTime(sliderTrack.getValue());
	}
	
	private void draggOnTrackVolume(MouseEvent e) {
		player.setVolume(sliderVolume.getValue());
	}
	
	public void init(){
		isPlay = true;
		sliderTrack.setMaximum(player.getCurrentTrack().getLength());
		trackNameLabel.setText("TRACK : " + player.getCurrentPosition() + " / " + player.getNumberOfTracks());
		lblTime.setText(Utility.convertToTime(player.getCurrentTrack().getLength()/1000));
	}
	
	private void next()
	{
		currentTrackOrder++;
		if(currentTrackOrder >= currentLesson.getTrack().length){
			// dung chuong trinh nghe nhac lai
			isPlay = false;
			player.stop();
			// tinh diem
			int score = Utility.calScore(ttThread.getTime() / 1000, currentLesson.getLength() / 1000);
			// dua ra thong bao
//			JOptionPane.showMessageDialog(this, "Ban dat duoc " + score + " diem.");
			Object[] options = {"Play again",
            "Other Lessons"};
			int n = JOptionPane.showOptionDialog(this, "You got " + score + " points.",
												"Congratulation!!!",
												JOptionPane.YES_NO_OPTION,
												JOptionPane.INFORMATION_MESSAGE,
												null,     //do not use a custom Icon
												options,  //the titles of buttons
												options[0]); //default button title
			// them vao co so du lieu
			ListenDB.InsertListenDB(mainUI.getCurrentUser().getID(), currentLesson.getID(), score);
			mainUI.getScorePanel().refreshScore();
			if (n == 0)
			{
				restart();
			}
			else
			{
				// ra man hinh chon bai
				mainUI.getChooseLevelPanel().setVisible(true);
				mainUI.getListenPanel().setVisible(false);
			}
			
		}else
		{
		}
		
		player.next();
		suggestionText.setScriptText(player.getCurrentScript());
		suggestionText.setSuggestionText(player.getCurrentSuggestionText());
		inputArea.setText("");
		suggestArea.setText("");
		
		init();
	}
	
	public void restart(){
		// dung thread cu va khoi tao thread moi
		isPlay = false;
		ttThread = new TotalTimeThread();
		
		// tao mot slider thread khac
		sliderThread = new SliderThread();
		sliderTrack.setValue(0);
		
		// dua ve trang thai lan dau chay
		firstPlay = true;
		
		// dua player ve track dau tien va pause
		player.setCurrentTrack(0);
		player.setState(false);
		
		// dat lai text cho button play
		playPauseButton.setText("Play");
		
		
		inputArea.setEditable(false);
		lblCurrentTime.setText("Current time : 0:00:00"); 
		lblTotalTime.setText("Total Time : 0:00:00");
		suggestionText.setScriptText(player.getCurrentScript());
		suggestionText.setSuggestionText(player.getCurrentSuggestionText());
		inputArea.setText("");
		suggestArea.setText("");
		init();
	}
	
	private void checkCharInput(char c)
	{
		int result = suggestionText.checkChar(c);
		
		if (result == AnswerText.WORD_DONE)
		{
			suggestArea.append(suggestionText.getCorrectedWord());
			inputArea.setText("");
		}
		else if (result == AnswerText.SENTENCE_DONE)
		{
			suggestArea.append(suggestionText.getCorrectedWord());
			inputArea.setText("");
		}
		else if (result == AnswerText.END_SCRIPT)
		{
//			System.out.println(suggestionText.getCorrectedWord());
			suggestArea.append(suggestionText.getCorrectedWord());
			inputArea.setText("");
			next();
		}
		else
		{
			inputArea.setText(suggestionText.getCorrectedWord());
		}
	}
	

	public class SliderThread extends Thread{
		public void run(){
			while(isPlay){
				if(player != null){
					sliderTrack.setValue(player.getCurrentTime());
					lblCurrentTime.setText("Current time : " + Utility.convertToTime(player.getCurrentTime()/1000));
				}
			}
		}
	}
	
	public class TotalTimeThread extends Thread{
		// Thoi gian bat dau
		Long startTime;
		// Tong thoi gian hien tai, tinh bang miliseconds
		Integer countTime;
		public TotalTimeThread(){
			countTime = 0;
		}
		
		public void run(){
			startTime = System.currentTimeMillis();
			
			while(isPlay){
				synchronized(countTime){
					countTime = (int)(System.currentTimeMillis() - startTime);
				}
				String timeS = Utility.convertToTime(countTime/1000);
				lblTotalTime.setText("Total Time : " + timeS);
			}
		}
		
		public int getTime(){
			synchronized(countTime){
				return countTime;
			}
		}
		
		public void restart(){
			synchronized(startTime){
				startTime = System.currentTimeMillis();
			}
		}
			
	}
}