package audio;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Lessions;
import model.Tracks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;

import com.sun.media.jfxmedia.track.Track;

/**
 * Dung de chay mot tap cac danh sach path truyen vao
 * @author _Daotac_
 *
 */
public class PlayAudio {
	/**
	 * Danh sach cac duong dan can play
	 */
	Lessions lesson;
	Tracks[] listTracks;// Danh sach cac track
	MediaPlayer mediaPlayer;
	Media media;		
	int currentTrack;	// So thu tu cua Track hien dang phat
	boolean state;	// trang thai cua trinh phat nhac
					// true neu dang phat
					// false neu dang pause
	
	public PlayAudio(Lessions les){
		this.lesson = les;
		this.listTracks = les.getTrack();
		// Khoi tao currentTrack
		currentTrack = 0;
		state = false;
		
		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();
		
		media = new Media((new File(listTracks[currentTrack].getAudioFile())).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(1000000);
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	synchronized (mediaPlayer) {
            		mediaPlayer.pause();
				}
            }
   	 	});
	}
	
	public Lessions getLesson() {
		return lesson;
	}

	public void setLesson(Lessions lesson) {
		this.lesson = lesson;
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
		
		mediaPlayer.onEndOfMediaProperty();
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
	
	public Tracks getCurrentTrack(){
		return listTracks[currentTrack];
	}
	
	public String getCurrentScript()
	{
//		Tracks track = getCurrentTrack();
		return getCurrentTrack().getScriptFile();
	}
	
	public String getCurrentSuggestionText()
	{
		return getCurrentTrack().getSuggest();
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
			mediaPlayer.seek(current);
		}
	}
	
	public int getVolumn(){
		int volume = 0;
		synchronized(mediaPlayer){
			volume = (int)mediaPlayer.getVolume() * 100;
		}
		return volume;
	}
	
	public void setVolume(int volume){
		synchronized(mediaPlayer){
			mediaPlayer.setVolume(volume * 1.0 / 100);
		}
	}
	
	public void stop(){
		synchronized(mediaPlayer){
			mediaPlayer.stop();
		}
	}
}
