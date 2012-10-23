package audio;
import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;

public class Test {
    
    public static void main(String[] args) {
    	final JFXPanel fxPanel = new JFXPanel();
   	 	
    	 Platform.runLater(new Runnable() {
             @Override
             public void run() {
            	 JavaFXSceneBuilder sceneBuider = new JavaFXSceneBuilder();
		    	 Media media = new Media(new File("thucuoi.mp3").toURI().toString());
		 		 MediaPlayer mediaPlayer = new MediaPlayer(media);
		 		 
		 		 mediaPlayer.setStartTime(new Duration(50000));
		 		 mediaPlayer.play();
             }
    	 });
    }
}