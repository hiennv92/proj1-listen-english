package Utility;

public class Utility {
	public static String convertToTime(int seconds){
		int h = seconds / 3600;
		int m = (seconds % 3600) / 60;
		int s = ((seconds % 3600) % 60);
		return String.format("%d:%02d:%02d", h, m, s);
	}
}
