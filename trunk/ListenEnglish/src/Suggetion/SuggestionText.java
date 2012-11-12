package Suggetion;

public class SuggestionText {
	private String suggestionText;
	private int currentIndex;
	private String currentString;
	private static final String punctuation = ",.?/!()";
	private String scriptText;
	private int currentSciptChar;
	private String currentScript;
	private String correctedWord;
	private int type;
	private AnswerText answerText;
	
	public SuggestionText()
	{
		answerText = new AnswerText();
		reset();
	}
	
	public void reset()
	{
		currentIndex = -1;
		currentSciptChar = 0;
		currentSciptChar = 0;
		currentScript = "";
		currentString = "";
	}
	
	public void setSuggestionText(String suggestionText)
	{
		this.suggestionText = suggestionText.replaceAll(" ", "");
//		System.out.println("-"+this.suggestionText);
		next();
	}
	
	public void setScriptText(String scriptText)
	{
		reset();
		this.scriptText = scriptText;
	}
	
	public int checkChar(char c)
	{
//		String result = "";
		correctedWord = "";
		int k = answerText.checkChar(c);
		correctedWord = answerText.getCorrectedWord();
		if (k == AnswerText.CHAR_CORRECT)
		{
//			result = answerText.getCorrectedWord();
		}
		else if (k == AnswerText.WORD_DONE)
		{
//			result = answerText.getCorrectedWord();
			
			answerText.nextWord();
		}
		else if (k == AnswerText.WORD_FAIL)
		{
//			result = answerText.getCorrectedWord();
		}
		else if (k == AnswerText.SENTENCE_DONE)
		{
//			currentSciptChar += currentScript.length();
			if (currentSciptChar >= scriptText.length() - 1)
			{
//				result = "$";
				System.out.println("het");
				return AnswerText.END_SCRIPT;
			}
			else
			{
				do {
					next();
					if (!currentScript.equals(""))
					{
						System.out.println("current "+currentScript);
						break;
					}
					//
					correctedWord += currentString;
				}while(type != ERROR);
				System.out.println(currentString);
//				currentSciptChar += correctedWord.length();
			}
		}
		
		return k;
	}
	
	
	public String getCorrectedWord()
	{
		return this.correctedWord;
	}
	
	private void next()
	{
		processSuggestionText();
		System.out.println("so");
		currentScript = "";
		if (type == NUMBER)
		{
			int k = Integer.parseInt(currentString);
			int i = currentSciptChar;
			int j = 0;
			int length = scriptText.length();
			char c;
			// xoa het dau cach o dau neu co
			while(scriptText.charAt(i) == 32)
			{
				++i;
			}
			
			while(i < length)
			{
				if ((c = scriptText.charAt(i)) == 32 || isPunctuation(c) || c == '[')
				{
					--k;
					if (k == 0) break;
				}
				currentScript += c;
				++i;
			}
			currentSciptChar = i;
			currentString = "";
//			if (currentScript.charAt(0)==32) currentScript = currentScript.replaceFirst(" ", "");
			answerText.setAnswer(currentScript);
		}
		else
		{
			System.out.println("loi "+currentScript);
			currentSciptChar += currentString.length();
		}
		
	}
	
	private void processSuggestionText()
	{
		++currentIndex;
		int length = suggestionText.length();
//		System.out.println("so "+length);
		if (currentIndex == length - 1) return;
		char c = suggestionText.charAt(currentIndex);
		currentString = "";
//		System.out.println("hehe "+currentIndex + c);
		if (isNumber(c))
		{
			type = NUMBER;
			do
			{
				currentString += c;
				++currentIndex;
//				c = suggestionText.charAt(currentIndex);
			}while(currentIndex < length && isNumber(c = suggestionText.charAt(currentIndex)));
			if (!isNumber(c)) --currentIndex;
		}
		else if (isPunctuation(c))
		{
			type = PUNCTUATION;
//			do
//			{
//				currentString += c;
//				++currentIndex;
////				c = suggestionText.charAt(currentIndex);
//			}while(currentIndex < length && isPunctuation(c)(c = suggestionText.charAt(currentIndex)));
			currentString += c + " ";
		}
		else if (c == '[')
		{
			type = SUGGESTION;
			do
			{
				++currentIndex;
				c = suggestionText.charAt(currentIndex);
				if (c == ']') break;
				currentString += c;
			}while(currentIndex < length);
			currentString += " ";
			currentString = " " + currentString;
		}
		else
		{
			// Loi
			type = ERROR;
			processSuggestionText();
		}
	}
	
	private boolean isNumber(char c)
	{
		if (c >= 48 && c < 58)
			return true;
		return false;
	}
	
	private boolean isPunctuation(char c)
	{
		if (punctuation.indexOf(c) != -1)
			return true;
		return false;
	}
	
	private static final int ERROR = 0;
	private static final int SUGGESTION = 1;	// tu goi y
	private static final int PUNCTUATION = 2;	// dau cau
	private static final int NUMBER = 3;		// so tu
	
	

}
