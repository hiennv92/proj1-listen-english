/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Hien
 */
public class AnswerText {
    private String answer;
    private String[] words;
    public String correct_chars;
    private int count_char;
    private int count_word;
    
    public AnswerText()
    {
        reset();
    }
    
    public void setAnswer(String answer)
    {
        this.answer = answer;
        System.out.println("dap an"+answer);
        this.words = this.answer.split(" ");
        reset();
    }
    private void reset()
    {
        count_char = count_word = 0;
        correct_chars = "";
    }
    
    public String getAnswer()
    {
        return this.answer;
    }
    
    public int checkChar(char c)
    {
        if (words.length <= count_word)
        {
//            System.out.println("het cau"+words)
            return SENTENCE_DONE;   // het cau
        }
        
        if (Character.toLowerCase(words[count_word].charAt(count_char)) == Character.toLowerCase(c) )
        {
            correct_chars = correct_chars+words[count_word].charAt(count_char);
            ++count_char;
            if (correct_chars.equals(words[count_word]))
            {
            	if (count_word == words.length - 1)
            	{ 
            		return SENTENCE_DONE;
            	}
            	System.out.println("het tu");
            	correct_chars += " ";
                return WORD_DONE;
            }
            
               // 
            return CHAR_CORRECT;
        }
        return WORD_FAIL;
    }
    
    public String getCorrectedWord()
    {
    	return correct_chars;
    }
    
    public boolean nextWord()
    {
    	if (correct_chars.equals(words[words.length - 1]))
    	
    		return false;
    	System.out.println("-"+correct_chars+ " -"+words[words.length -1]+"-");
        count_char = 0;
        ++count_word;
        correct_chars = "";
        return true;
    }
    
    public static final int SENTENCE_DONE = 1;
    public static final int WORD_DONE = 2;
    public static final int WORD_FAIL = 3;
    public static final int CHAR_CORRECT = 4;
    public static final int END_SCRIPT = 5;
}
