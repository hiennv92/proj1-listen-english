/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Suggetion;

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
//        System.out.println(""+words.length);
        if (words[count_word].charAt(count_char) == c)
        {
            correct_chars = correct_chars+c;
            ++count_char;
            if (correct_chars.equals(words[count_word]))
            {
                return WORD_DONE;
            }
            char chr;
            if ((chr = words[count_word].charAt(count_char)) == ',' ||
            	chr == '.'	||
            	chr == '!')
            {
            	correct_chars += chr;
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
    
    public final int SENTENCE_DONE = 1;
    public final int WORD_DONE = 2;
    public final int WORD_FAIL = 3;
    public final int CHAR_CORRECT = 4;
}
