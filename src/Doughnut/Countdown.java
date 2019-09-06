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

import java.awt.Color;
import javax.swing.JLabel;

/**
 * This class is responsible for the in-game timer.
 * <p>
 * It shows the time remaining in RoscoGUI's lblCountDown JLabel
 * and can trigger the gameEnd() method to stop the game and go to
 * the results screen (GameOver object).
 * Renzo Nahuel Murina Cadierno
 * mail: nmcadierno@hotmail.com
 */
public class Countdown extends Thread {
    
    static int counter;
    private JLabel timeLeft;
    private volatile boolean finish = false;
    
    Countdown(JLabel timeLeft, int counter) { 
        this.timeLeft = timeLeft;
        this.counter = counter;
    }
    
    @Override
    public void run() {

        //while finish == true
        while(!finish) {

            //we set the labels's text to the counter value.
            timeLeft.setText(String.valueOf(counter));

            /* if the counter is 10 or below, even numbers are colored red
               as a visual indicator to hurry up. */
            if(counter < 11 && counter % 2 == 0)
                timeLeft.setForeground(Color.red);
            
            else timeLeft.setForeground(Color.white);

            //decreses the counter (thus, seconds left) by one.
            counter--;

            //wait for one second.
            try {
                
                Thread.sleep(1000);
                
            } catch (Exception e) { }

            //if the counter is below 0, we break out of the loop.
            if (counter < 0) {
                break;
            }
        }

        /* we call for gameEnd() if the timer is below 0 or if it
           was stopped by stopThread() */
        Util.gameEnd();
    }
        
    // if finish switches to false, the run() method is stopped.
    public void stopThread() { finish = true; }
}
