package audio;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Lessions;
import model.Tracks;

import java.io.File;

/**
 * Dung de chay mot tap cac danh sach path truyen vao
 * @author _Daotac_
 *
 */
public class PlayAudio {
	/**
	 * Danh sach cac duong dan can play
	 */
	Tracks[] listTracks;// Danh sach cac track
	MediaPlayer mediaPlayer;
	Media media;		
	int currentTrack;	// So thu tu cua Track hien dang phat
	boolean state;	// trang thai cua trinh phat nhac
					// true neu dang phat
					// false neu dang pause
	
	public PlayAudio(Lessions les){
		this.listTracks = les.getTrack();
		// Khoi tao currentTrack
		currentTrack = 0;
		state = false;
		
		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();
		
		media = new Media((new File(listTracks[currentTrack].getAudioFile())).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	mediaPlayer.pause();
            }
   	 	});
	}
	
	public PlayAudio(Tracks[] listPath){
		this.listTracks = listPath;
		// Khoi tao currentTrack
		currentTrack = 0;
		state = false;
		
		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();
		
		media = new Media((new File(listTracks[currentTrack].getAudioFile())).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	synchronized (mediaPlayer) {
            		mediaPlayer.pause();
				}
            }
   	 	});
	}
	
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
		if(state == true){
			synchronized (mediaPlayer) {
        		mediaPlayer.play();
			}
		}
		else
			synchronized (mediaPlayer) {
        		mediaPlayer.pause();
			}
	}
	
	public Tracks[] getListPath() {
		return listTracks;
	}

	public void setListPath(Tracks[] listPath) {
		this.listTracks = listPath;
	}
	
	public boolean next(){
		// neu ma currentTrack hien dang la phan tu cuoi
		// thi return false va khong lam gi ca
		if(currentTrack >= listTracks.length - 1)
			return false;
		
		// neu khong phai thi dua track sang track ke tiep
		currentTrack++;
		// dat lai state
		state = true;
		// dung trinh phat nhac cu lai
		synchronized (mediaPlayer) {
    		mediaPlayer.stop();
		}
		// tao mot trinh phat nhac moi
		media = new Media((new File(listTracks[currentTrack].getAudioFile())).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	synchronized (mediaPlayer) {
            		mediaPlayer.play();
    			}
            }
   	 	});
		
		return true;
	}
	
	// tra ve miliseconds cho thoi gian hien tai
	public int getCurrentTime(){
		Duration current = null;
		synchronized(mediaPlayer){
			current = mediaPlayer.getCurrentTime();
		}
		
		return (int)current.toMillis();
	}
	
	public void setCurrentTime(int miliseconds){
		Duration current = new Duration(miliseconds);
		synchronized(mediaPlayer){
			mediaPlayer.setStartTime(current);
		}
	}
	
	public double getVolumn(){
		double volume = 0;
		synchronized(mediaPlayer){
			volume = mediaPlayer.getVolume();
		}
		return volume;
	}
	
	public void setVolume(double volume){
		synchronized(mediaPlayer){
			mediaPlayer.setVolume(volume);
		}
	}
}
