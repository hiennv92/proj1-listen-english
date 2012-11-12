package Utility;

public class Utility {
	public static String convertToTime(int seconds){
		int h = seconds / 3600;
		int m = (seconds % 3600) / 60;
		int s = ((seconds % 3600) % 60);
		return String.format("%d:%02d:%02d", h, m, s);
	}
	
	public static int calScore(int totalTime, int defaultTime){
		System.out.println("Total : " + totalTime);
		System.out.println("default : " + defaultTime);
		int score = (int) (10 * (11 - totalTime * 1.0 / defaultTime));
		//return score > 0 ? score : 0;
		return score;
	}
}
