package audio;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSlider;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.FlowLayout;

import model.Tracks;
//import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;

public class Test {
	//static Media media;
	//static MediaPlayer mediaPlayer;
	PlayAudio player;
	JButton playPause;
	JButton next;
	JSlider time;
	Tracks[] tracks;
	
	
	Test(){
		JFrame frame = new JFrame("Test audio");
		
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		
		frame.setLayout(layout);
		frame.setBounds(new Rectangle(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		playPause = new JButton("Play");
		frame.getContentPane().add(playPause);
		playPause.addActionListener(clickPlayPause);
		
		next = new JButton("Next");
		frame.getContentPane().add(next);
		next.addActionListener(clickNext);
		
		time = new JSlider();
		time.setMaximum(180000);
		time.setMinimum(0);
		time.addMouseMotionListener(setTime);
		frame.getContentPane().add(time);
		
		initTracks();
		player = new PlayAudio(tracks);
		
		timeThread.start();
	}
	
	public void initTracks(){
		tracks = new Tracks[10];
		for(int i = 0; i < 10; i++){
			tracks[i] = new Tracks(0, 180000, "mp3/audio_" + i + ".mp3", null, null);
		}
	}
	
	ActionListener clickPlayPause = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event) {
			player.setState(!player.getState());
		}
	};
	
	ActionListener clickNext = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent event) {
			player.next();
		}
	};
	
	MouseMotionListener setTime = new MouseMotionListener(){

		@Override
		public void mouseDragged(MouseEvent e) {
			player.setCurrentTime(time.getValue());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			//player.setCurrentTime(time.getValue());
		}
	};
	
	Thread timeThread = new Thread(){
		public void run(){
			while(true){
				time.setValue(player.getCurrentTime());
			}
		}
	};
	
    public static void main(String[] args) {
    	new Test();
    }
}