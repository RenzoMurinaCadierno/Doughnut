/**
 * Designed, programmed and tested by 
 * Renzo Nahuel Murina Cadierno (a.k.a) "Max".
 * Contact: nmcadierno@hotmail.com
 * GIT: github.com/RenzoMurinaCadierno
 * 
 * Disclaimer:
 * 
 * Before writing the very first line of this whole app, my intention was 
 * to make it open source, so you can use it in any way you wish. 
 * 
 * But please, it would be much appreciated if you could give the
 * credits to the author and the links of contact and of download to the
 * original work, as I'm trying my best to be acknowledged to safely drop
 * the not-life-fulfilling job I'm currently at, and finally dedicate my
 * life to my true passion: coding apps and applets.
 * 
 * Additional commentary:
 * 
 * The code in this class was written by hand, and that is why the GUI's 
 * aesthetic is not at all appealing.
 * I did it this as a practical exercise to understand how a JForm is 
 * created from scratch and give it my best shot, since this whole 
 * project is my first attempt at coding in Java and at OOP.
 * I'm new to programming. I have been studying Java, Javascript and Python
 * (and some of its frameworks) for half a year since this date.
 */
package Doughnut;

public class Item {
    
    private String letter;
    private String condition;
    private String question;
    private String answer;

    Item(String letter, String condition, String question, String answer) {
            this.letter = letter;
            this.condition = condition;
            this.question = question;
            this.answer = answer;
    }

    @Override
    public String toString() {
        return "[" + letter + " - " + condition + " - " + question 
                + " - " + answer + ']';
    }
    
    public String getLetter() { return letter; }
    public String getCondition() { return condition; }
    public String getQuestion() { return question; } 
    public String getAnswer() { return answer; }

    public String compare() { return letter+" "+condition+" "+question+" "+answer; }
    
    @Override
    public int hashCode() { return this.compare().hashCode(); }
    
    public int compareLetters(Item it) {
        return this.getLetter().compareTo(it.getLetter());
    }
}
