package Suggetion;

public class SuggestionText {
	private String suggestionText;
	private String[] splitString;
	private int countSuggestion;
	
	public void setSuggestionText(String suggetionText)
	{
		this.suggestionText = suggetionText;
		splitString = this.suggestionText.split("|");
		countSuggestion = 0;
	}
	
	public String getSuggestionText()
	{
		return this.suggestionText;
	}
	
	
	
	
	
	public boolean next()
	{
		return false;
	}
	
	private final int NAME = 1;
	private final int COMMAND = 2;
	

}
