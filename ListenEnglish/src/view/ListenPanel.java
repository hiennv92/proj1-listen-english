package view;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Suggetion.AnswerText;
import Utility.Utility;
import audio.PlayAudio;

import model.ConnectDB;

public class ListenPanel extends JPanel{
	private JLabel lessNameLabel;
	private JLabel trackNameLabel;
	private JSlider sliderTrack;
	private JSlider sliderVolume;
	private JLabel lblVolume;
	private JLabel lblCurrentTime;
	private JLabel lblTime;
	private JTextArea suggestArea;
	private JTextArea inputArea;
	private JButton btnExit;
	private JButton btnLogout;
	private JButton btnOtherLession;
	private JLabel lblTotalTime;
	private JButton playPauseButton;
	private JButton nextButton;
	private MainUI mainUI;
	private AnswerText aText;
	
	private PlayAudio player = null;
	private boolean isPlay = true;
	
	
	public PlayAudio getPlayer() {
		return player;
	}

	public void setPlayer(PlayAudio player) {
		this.player = player;
		lessNameLabel.setText("LESSON : " + player.getLesson().getName());
		aText.setAnswer(player.getCurrentScript());
		sliderVolume.setMaximum(100);
		sliderVolume.setValue(player.getVolumn());
		
		init();
		(new SliderThread()).start();
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
		
		lblCurrentTime = new JLabel("Current time : ");
		lblCurrentTime.setBounds(127, 96, 131, 14);
		add(lblCurrentTime);
		
		lblTime = new JLabel("Time : ");
		lblTime.setBounds(492, 96, 96, 14);
		add(lblTime);
		
		suggestArea = new JTextArea();
		suggestArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		suggestArea.setWrapStyleWord(true);
		suggestArea.setLineWrap(true);
		suggestArea.setBounds(10, 162, 578, 84);
		suggestArea.setEditable(false);
		add(suggestArea);
		
		inputArea = new JTextArea();
		inputArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inputArea.setWrapStyleWord(true);
		inputArea.setLineWrap(true);
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
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(458, 378, 141, 23);
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clickExit(e);
			}
		});
		add(btnExit);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(317, 378, 131, 23);
		btnLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clickLogout(e);
			}
		});
		add(btnLogout);
		
		btnOtherLession = new JButton("Other Lession");
		btnOtherLession.setBounds(176, 378, 131, 23);
		btnOtherLession.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clickOtherLession(e);
			}
			
		});
		add(btnOtherLession);
		
		lblTotalTime = new JLabel("Total Time : ");
		lblTotalTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalTime.setBounds(57, 127, 156, 29);
		add(lblTotalTime);
		
		playPauseButton = new JButton("P");
		playPauseButton.setBounds(00, 61, 50, 30);
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
		add(nextButton);
		aText = new AnswerText();
		
	}
	
	public void clickExit(ActionEvent e){
		ConnectDB.closeConnect();
		System.exit(0);
	}
	
	public void clickLogout(ActionEvent e){
		
	}
	
	public void clickPlayPause(ActionEvent e){
		player.setState(!player.getState());
		inputArea.setEditable(player.getState());
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
		trackNameLabel.setText("TRACK : " + player.getCurrentTrack().getAudioFile());
		lblTime.setText(Utility.convertToTime(player.getCurrentTrack().getLength()/1000));
	}
	
	public class SliderThread extends Thread{
		public void run(){
			while(isPlay){
				if(player != null){
					sliderTrack.setValue(player.getCurrentTime());
					lblCurrentTime.setText(Utility.convertToTime(player.getCurrentTime()/1000));
				}
			}
		}
	}
	
	private void next()
	{
		player.next();
		aText.setAnswer(player.getCurrentScript());
		inputArea.setText("");
		suggestArea.setText("");
		init();
	}
	
	private void checkCharInput(char c)
	{
		int k;
        if ((k = aText.checkChar(c)) == aText.SENTENCE_DONE)
        {
            // do something
            System.out.println("het cau");
            next();
        }
        else if (k == aText.WORD_DONE)
        {
            suggestArea.append(aText.getCorrectedWord()+" ");
            inputArea.setText("");
            if (!aText.nextWord()) next();
        }
        else if (k == aText.CHAR_CORRECT)
        {
            
        }
        else
        {
            inputArea.setText(aText.correct_chars);
        }
	}
}