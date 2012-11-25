package control;

import java.io.File;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.Lesson;
import model.Track;

/**
 * Dung de chay mot tap cac danh sach path truyen vao
 * 
 * @author _Daotac_
 * 
 */
public class PlayAudio {
	/**
	 * Danh sach cac duong dan can play
	 */
	Lesson lesson;
	Track[] listTrack;// Danh sach cac track
	MediaPlayer mediaPlayer;
	Media media;
	MediaPlayer mediaPlayerFull;
	Media mediaFull;
	int currentTrack; // So thu tu cua Track hien dang phat
	boolean state; // trang thai cua trinh phat nhac
					// true neu dang phat
					// false neu dang pause
	boolean stateFull;
	int countTrack; // So thu tu cua track dang phat trong che do full lesson

	public PlayAudio(Lesson les) {
		this.lesson = les;
		this.listTrack = les.getTrack();
		// Khoi tao currentTrack
		currentTrack = 0;
		state = false;

		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();

		media = new Media((new File(listTrack[currentTrack].getAudioFile()))
				.toURI().toString());
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

	public PlayAudio(Track[] listPath) {
		this.listTrack = listPath;
		// Khoi tao currentTrack
		currentTrack = 0;
		state = false;

		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();

		media = new Media((new File(listTrack[currentTrack].getAudioFile()))
				.toURI().toString());
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

	public PlayAudio() {
		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();
	}

	/**
	 * Chay mot file xac dinh
	 * 
	 * @param path
	 *            duong dan cua file nhac
	 */
	public void playSpecifiedFile(String path) {
		media = new Media((new File(path)).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				synchronized (mediaPlayer) {
					mediaPlayer.play();
				}
			}
		});
	}

	public void playFullLesson() {
		if (getState())
		{
			state = false;
			stop();
		}
		// Khoi tao moi truong
		final JFXPanel fxPanel = new JFXPanel();
		stateFull = true;
		countTrack = 0;
		playTrackInFullLesson();
	}

	private void playTrackInFullLesson() {
		if (countTrack >= listTrack.length)
			return;
		mediaFull = new Media((new File(listTrack[countTrack].getAudioFile()))
				.toURI().toString());
		mediaPlayerFull = new MediaPlayer(mediaFull);
		mediaPlayerFull.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (mediaPlayerFull) {
					countTrack++;
					playTrackInFullLesson();
				}
			}
		});

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				synchronized (mediaPlayerFull) {
					mediaPlayerFull.play();
				}
			}
		});
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public boolean getState() {
		return state;
	}

	public boolean getStateFull() {
		return stateFull;
	}

	public void setStateFull(boolean stateFull) {
		this.stateFull = stateFull;
		if (stateFull == true) {
			playFullLesson();
		} else if (mediaPlayerFull != null)
			synchronized (mediaPlayerFull) {
				mediaPlayerFull.stop();
			}
	}

	public void setState(boolean state) {
		this.state = state;
		if (state == true) {
			synchronized (mediaPlayer) {
				mediaPlayer.play();
			}
		} else
			synchronized (mediaPlayer) {
				mediaPlayer.pause();
			}
	}

	public Track[] getListPath() {
		return listTrack;
	}

	public void setListPath(Track[] listPath) {
		this.listTrack = listPath;
	}

	public Track getCurrentTrack() {
		return listTrack[currentTrack];
	}

	public boolean setCurrentTrack(int count) {
		// neu ma currentTrack hien dang la phan tu cuoi
		// thi return false va khong lam gi ca
		if (count > listTrack.length - 1)
			return false;

		currentTrack = count;

		// dat lai state
		state = true;
		// dung trinh phat nhac cu lai
		synchronized (mediaPlayer) {
			mediaPlayer.stop();
		}
		// tao mot trinh phat nhac moi
		media = new Media((new File(listTrack[currentTrack].getAudioFile()))
				.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(1000000);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				synchronized (mediaPlayer) {
					mediaPlayer.seek(new Duration(0));
					mediaPlayer.pause();
				}
			}
		});

		return true;
	}

	public int getCurrentPosition() {
		return currentTrack + 1;
	}

	public int getNumberOfTracks() {
		return listTrack.length;
	}

	public String getCurrentScript() {
		// Track track = getCurrentTrack();
		return getCurrentTrack().getScriptFile();
	}

	public String getCurrentSuggestionText() {
		return getCurrentTrack().getSuggest();
	}

	public int getLessonLength() {
		int length = 0;
		if (listTrack != null) {
			int n = listTrack.length;
			for (int i = 0; i < n; ++i) {
				length += listTrack[i].getLength();
			}
		}
		return length;
	}

	public boolean next() {
		// neu ma currentTrack hien dang la phan tu cuoi
		// thi return false va khong lam gi ca
		if (currentTrack >= listTrack.length - 1)
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
		media = new Media((new File(listTrack[currentTrack].getAudioFile()))
				.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(1000000);

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
	public int getCurrentTime(boolean isFull) {
		Duration current = null;
		int bonus = 0;
		MediaPlayer mp = mediaPlayer;
		if (isFull){
			mp = mediaPlayerFull;
			bonus += getCurrentTotalTime();
		}
		synchronized (mp) {
			current = mp.getCurrentTime();
		}

		return (int) current.toMillis() + bonus;
	}
	
	public int getCurrentTotalTimePlaying()
	{
		int time = 0;
		for (int i = 0; i < currentTrack; ++i)
		{
			time += listTrack[i].getLength();
		}
		if (state)
		{
			time += mediaPlayer.getCurrentTime().toMillis();
		}
		return time;
	}
	
	private int getCurrentTotalTime()
	{
		int time = 0;
		for (int i = 0; i < countTrack; ++i)
		{
			time += listTrack[i].getLength();
		}
		return time;
	}

	public void setCurrentTime(int miliseconds) {
		Duration current = new Duration(miliseconds);
		synchronized (mediaPlayer) {
			mediaPlayer.seek(current);
		}
	}

	public int getVolumn() {
		int volume = 0;
		synchronized (mediaPlayer) {
			volume = (int) mediaPlayer.getVolume() * 100;
		}
		return volume;
	}

	public void setVolume(int volume) {
		synchronized (mediaPlayer) {
			mediaPlayer.setVolume(volume * 1.0 / 100);
		}
	}

	public void stop() {
		// mediaPlayer chua khoi tao
		if (mediaPlayer == null)
			return;

		synchronized (mediaPlayer) {
			mediaPlayer.stop();
		}
	}
}
