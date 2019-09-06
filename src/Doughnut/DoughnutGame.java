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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import static Doughnut.DoughnutMain.rMain;

/**
 * This class is the game itself.
 * <p>
 * What is handled here:<br> 
 * > The creation of the Letter button wheel. <br>
 * > The import of itemList from the DoughnutMain instance and the distribution 
 of its Item objects to each button. <br>
 * > The display of the Question to be asked, the Letter and condition of
 * the answer, and the JTextField for the user input for the answer.<br>
 * > The initialization of a CountDown instance, which starts up the timer.<br>
 * > The turn handling: what happens when the user answers correctly, 
 * incorrectly or passes. Also, how the Letter wheel responds after the
 * last Item of the loop.<br>
 * > How the game ends. That is to say, when no more questions are left to
 * be answered or when the time is up.<br>
 * > The finalItemList ArrayList which contains FinalItem objects that will
 * be retrieved by a GameOver instance when the game ends, in order to 
 * construct the display screen and events there.
 * @author 
 * Renzo Nahuel Murina Cadierno (a.k.a) "Max"
 * mail: nmcadierno@hotmail.com
 */
public class DoughnutGame extends javax.swing.JFrame {
    
    private static List<Item> itemList;
    private static List<FinalItem> finalItemList;
    private List<JButton> buttons;
    private List<JButton> unansweredQuestions;
    private String spanishQuestion;
    private int activeButtonNumber = 0;
    private static Countdown timer;

    /**
     * Creates new form DoughnutGame
     */
    public DoughnutGame() throws ClassNotFoundException {
        
        /*we dispose the previous JFrame and set its instance to
          null */       
        try {
            
            Util.closeMainFrame();
            
        } catch (NullPointerException e) { }
        
        initComponents();
        
        //set the title, background and center position.
        setTitle(": : Answer! Go, go, timer is running! : :");
        getContentPane().setBackground(new Color(0, 0, 51));
        this.setLocationRelativeTo(null);
        
        //disable trasversal policy to set a custom event to the TAB key.
        txtAnswer.setFocusTraversalKeysEnabled(false);
        
        //request the Item objects in DoughnutMain's itemList ArrayList.
        itemList = DoughnutMain.loadQandA();
        
        /* as a failsafe, if anyone forces this class to be executed
           as an entry for the program, then we bring them back to
           the real startup by initializing a new rMain object. */
        if(itemList.isEmpty()) {

            rMain = new DoughnutMain();
            rMain.setVisible(true);
            rMain.setEnabled(true);

            return;
        }
        
        /* we create a list to store all finalItem objects, and fill it up
           with the same values as each Item object inside itemList, plus an
           empty string for the given answer, and 0 for the status, for now */
        finalItemList = new ArrayList();
        
        itemList.forEach(item -> 
                finalItemList.add(new FinalItem(
                        item.getLetter(), item.getCondition(), item.getQuestion(),
                        item.getAnswer(), "", 0)));
        
        // we set itemList to null, since we no longer need it.
        DoughnutMain.clearItemList();
        
        // we create a list to hold all questions that will be passed.
        unansweredQuestions = new ArrayList();
        
        /* we create a list to hold all 10 buttons of the wheel, and set
           their corresponding letter as their text with a for loop */
        buttons = new ArrayList();
        
        buttons.add(btn1); buttons.add(btn2);
        buttons.add(btn3); buttons.add(btn4);
        buttons.add(btn5); buttons.add(btn6);
        buttons.add(btn7); buttons.add(btn8);
        buttons.add(btn9); buttons.add(btn10);
        
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(itemList.get(i).getLetter());
        }
        
        //we initialize a Countdown object, and start it up.
        timer = new Countdown(lblCountdown, DoughnutMain.getTimer());
        timer.start();
        
        //we begin the turn.
        displayNextQuestion();
        
        //and set the action listener to the answer JTextBox.
        txtAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblAnswerActionPerformed(e);
   
            } //end ActionPerformed
        
        }); //end Action listener
        
    } // end Constructor DoughnutGame
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQuestion = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnPass = new javax.swing.JButton();
        txtAnswer = new javax.swing.JTextField();
        lblCondition = new javax.swing.JLabel();
        lblCountdown = new javax.swing.JLabel();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQuestion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lblQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 460, 60));

        btn1.setBackground(new java.awt.Color(51, 51, 255));
        btn1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("A");
        btn1.setMaximumSize(new java.awt.Dimension(50, 50));
        btn1.setMinimumSize(new java.awt.Dimension(50, 50));
        btn1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        btn2.setBackground(new java.awt.Color(51, 51, 255));
        btn2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("B");
        btn2.setMaximumSize(new java.awt.Dimension(50, 50));
        btn2.setMinimumSize(new java.awt.Dimension(50, 50));
        btn2.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        btn5.setBackground(new java.awt.Color(51, 51, 255));
        btn5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn5.setForeground(new java.awt.Color(255, 255, 255));
        btn5.setText("E");
        btn5.setMaximumSize(new java.awt.Dimension(50, 50));
        btn5.setMinimumSize(new java.awt.Dimension(50, 50));
        btn5.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        btn4.setBackground(new java.awt.Color(51, 51, 255));
        btn4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn4.setForeground(new java.awt.Color(255, 255, 255));
        btn4.setText("D");
        btn4.setMaximumSize(new java.awt.Dimension(50, 50));
        btn4.setMinimumSize(new java.awt.Dimension(50, 50));
        btn4.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        btn3.setBackground(new java.awt.Color(51, 51, 255));
        btn3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setText("C");
        btn3.setMaximumSize(new java.awt.Dimension(50, 50));
        btn3.setMinimumSize(new java.awt.Dimension(50, 50));
        btn3.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        btnPass.setBackground(new java.awt.Color(255, 255, 51));
        btnPass.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btnPass.setText("PASS");
        btnPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassActionPerformed(evt);
            }
        });
        getContentPane().add(btnPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 150, 40));

        txtAnswer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAnswer.setFocusCycleRoot(true);
        txtAnswer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnswerFocusLost(evt);
            }
        });
        txtAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnswerKeyReleased(evt);
            }
        });
        getContentPane().add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 300, 40));

        lblCondition.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCondition.setForeground(new java.awt.Color(255, 255, 255));
        lblCondition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblCondition, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 140, 40));

        lblCountdown.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        lblCountdown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblCountdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 120, 70));

        btn6.setBackground(new java.awt.Color(51, 51, 255));
        btn6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn6.setForeground(new java.awt.Color(255, 255, 255));
        btn6.setText("F");
        btn6.setMaximumSize(new java.awt.Dimension(50, 50));
        btn6.setMinimumSize(new java.awt.Dimension(50, 50));
        btn6.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        btn7.setBackground(new java.awt.Color(51, 51, 255));
        btn7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn7.setForeground(new java.awt.Color(255, 255, 255));
        btn7.setText("G");
        btn7.setMaximumSize(new java.awt.Dimension(50, 50));
        btn7.setMinimumSize(new java.awt.Dimension(50, 50));
        btn7.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        btn8.setBackground(new java.awt.Color(51, 51, 255));
        btn8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn8.setForeground(new java.awt.Color(255, 255, 255));
        btn8.setText("H");
        btn8.setMaximumSize(new java.awt.Dimension(50, 50));
        btn8.setMinimumSize(new java.awt.Dimension(50, 50));
        btn8.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 50, -1));

        btn9.setBackground(new java.awt.Color(51, 51, 255));
        btn9.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn9.setForeground(new java.awt.Color(255, 255, 255));
        btn9.setText("I");
        btn9.setMaximumSize(new java.awt.Dimension(50, 50));
        btn9.setMinimumSize(new java.awt.Dimension(50, 50));
        btn9.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        btn10.setBackground(new java.awt.Color(51, 51, 255));
        btn10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btn10.setForeground(new java.awt.Color(255, 255, 255));
        btn10.setText("J");
        btn10.setMaximumSize(new java.awt.Dimension(50, 50));
        btn10.setMinimumSize(new java.awt.Dimension(50, 50));
        btn10.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 254, 0, 0));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 422, 80, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void lblAnswerActionPerformed(ActionEvent evt) {
        
        String givenAnswer = Util
                                .unstressLetters(txtAnswer.getText())
                                .toLowerCase().trim();
        String realAnswer = Util
                            .unstressLetters(itemList.get(activeButtonNumber).getAnswer())
                            .toLowerCase();

        /*we check if the answer given in txtAnswer equals the answer in the
          corresponding itemList object. If they match, then the button assigned
          for the letter is painted blue, and the respective finalItemList object
          has its answer given and the "Correct" (1) status set to it. 
          If they do not match, the answer given and the "Wrong" (2) status are
          set to it instead. */
            if (givenAnswer.equalsIgnoreCase(realAnswer)) { 
                
                buttons.get(activeButtonNumber)
                            .setBackground(new java.awt.Color(51, 255, 0));
                buttons.get(activeButtonNumber)
                            .setForeground(new java.awt.Color(0, 0, 0));
                
                modifyFinalItemList(txtAnswer.getText(), 1);

            } else if (!givenAnswer.equalsIgnoreCase(realAnswer)) {
                
                buttons.get(activeButtonNumber)
                            .setBackground(new java.awt.Color(255, 0, 51));
                buttons.get(activeButtonNumber)
                            .setForeground(new java.awt.Color(0, 0, 0));
                
                modifyFinalItemList(txtAnswer.getText(), 2);
            }
            
            /*If we are not at the end of the letter loop, or we are but not
              all questions are answered, or the timer isnt over, the next 
              question is displayed. 
              If we are at the end of the loop and all questions are answered, 
              then we return so as not to trigger displayNextQuestion() method 
              which will cause an ArrayOutOfBoundsException, as it will try to 
              fetch the non-existent button index 10. */
            if (!actionAfterAnswering()) return;
         
            displayNextQuestion();
    }
    
    private void btnPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPassActionPerformed
        
        passTurn();
    }//GEN-LAST:event_btnPassActionPerformed

    private void txtAnswerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnswerKeyReleased
      
        //TAB can be used instead of clicking on the "PASS" button.
        if(evt.getKeyCode() == KeyEvent.VK_TAB) {
            
            evt.consume();
            passTurn(); 
        }
    }//GEN-LAST:event_txtAnswerKeyReleased

    private void txtAnswerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnswerFocusLost
        
        /*while in game, no element other than the answer JTextField can
        gain focus */
        txtAnswer.requestFocus();
        
    }//GEN-LAST:event_txtAnswerFocusLost

    /**
     * Increases activeButtonNumber counter by 1, allowing all methods that
     * work with it to use the next intended Letter button and its associated 
     * objects.
     * <p>
     * Most important, this method checks if the next Letter button in the
     * buttonGroup is the first one in the loop. 
     * <p>
     * If so and if all questions are answered either correctly or incorrectly, 
     * it will stop the timer in the Countdown instance which disposes the 
     * current DoughnutGame instance and initializes a GameOver one.
     * <p>
     * If the next Letter button is the first one in the loop but not all
     * questions are answered (one or more have been passed), then it will
     * set the background for those unanswered questions to blue, and will
     * remove the answered ones from the loop.
     * <p>
     * This method returns false as a failsafe to avoid an Exception caused
     * by trying to access an index that is out of bounds. For more information,
     * see the comment in the lblAnswerActionPerformed function.
     * @return 
     * True if the next Letter button is not the first one of the loop, or if
     * it is but the loop contains questions that were passed. False otherwise.
     */
    private boolean actionAfterAnswering() { 
    
        activeButtonNumber++;
  
        txtAnswer.setText("");
        
        /*if we are at the end of the loop (the next Letter button is the
          first one of the loop) */
        if (activeButtonNumber >= buttons.size()) {

            //if there are no more questions to be answered (no passed ones)
            if (unansweredQuestions.isEmpty()) {

                //we stop the timer and it is Game Over.
                timer.stopThread();
                return false;
            }
            
            // if there were passed questions, we create two lists.
            List<JButton> removeButtons = new ArrayList();
            List<Item> removeItems = new ArrayList();

            /* we filter all of the Letter buttons in "buttons" list and add
               the ones that were answered either correctly or incorrectly
               to the "removeButtons" list. We also add their corresponding
               "itemList" objects to the "removeItems" list. */
            buttons
                .stream()
                .filter((item) -> (!unansweredQuestions.contains(item)))
                .map((item) -> {
                        
                    removeButtons.add(buttons.get(buttons.indexOf(item)));
                    return item;
                })
                .forEachOrdered((item) -> {
                    
                    removeItems.add(itemList.get(buttons.indexOf(item)));
                });
            
            /* then, we remove the content of each buttons and itemList lists
               that match with what we have added to removeButtons and
               removeItems lists */
            buttons.removeAll(removeButtons);
            itemList.removeAll(removeItems);  
            
            /* we reset the counter, so that the next Letter button is the
               first one of the loop */
            activeButtonNumber = 0;
            
            /* we re-set all passed Letter buttons to its normal foreground and
              background color to visually indicate that they can be answered
              again */
            unansweredQuestions.forEach(item -> {
                
                item.setForeground(new java.awt.Color(255, 255, 255));
                item.setBackground(new java.awt.Color(51, 51, 255));
                
                /* we use this nested loop to assign the "Unanswered" and 0 
                   values to the answerGiven and status attributes of each 
                   corresponding finalItemList object that are now able to 
                   be answered again (that were passed in the previous loop */
                finalItemList.forEach(fit -> {
                   
                    if(fit.getLetter().equals(item.getText())) {
                        
                        fit.setAnswerGiven("Unanswered");
                        fit.setStatus(0);
                    }
                });
            });                       
            
            /* finally, we clear the list that holds all passed questions of
               the previous loop, so that we can re-use it. */
            unansweredQuestions.clear();

            return true;
        }    
        return true;
    }
    
    /**
     * Sets itemList to null so that Garbage Collector can dispose it.
     */
    public static void clearItemListAndFinalItemList() {
        
        itemList = null;
        finalItemList = null;
    }
    
    /**
     * Renders the condition and the question of the next turn's Letter 
     * inside their respective JLabels, and paints the active button in 
     * light blue to indicate that that one is the current turn to play.
     */
    private void displayNextQuestion() {

        txtAnswer.requestFocus();
        
        lblQuestion.setText(
              "<html><div>"
            + Util.convertToSpanishCharacters(
                    itemList.get(activeButtonNumber).getQuestion())
            + "</div></html>");
        
        lblCondition.setText(
              "<html>"
            + itemList.get(activeButtonNumber).getCondition()
            + ": "
            + itemList.get(activeButtonNumber).getLetter()
            + "</html>");
        
        buttons.get(activeButtonNumber)
                    .setBackground(new java.awt.Color(102, 255, 255));
        buttons.get(activeButtonNumber)
                    .setForeground(new java.awt.Color(0, 0, 0));   
    }
    
    /**
     * Auto-completes the answerGiven values in all unanswered 
     * finalItemList objects and returns a list with all of 
     * those objects ordered by their Letter value.
     * @return
     * A list with all finalItemList objects ordered by their 
     * Letter value.
     */
    public static List<FinalItem> getFinalItems() {
        
        finalItemList = finalItemList
                .stream()
                .sorted(Comparator.comparing(FinalItem :: getLetter))
                .collect(Collectors.toList());
        
        return finalItemList;
    }
    
    /**
     * Sets the answer given by the user and the status value for it 
     * (1, 2, 3) to the current finalItemList object.
     * @param answerGiven
     * The answer given by the user in txtAnswer for the current 
     * turn's question.
     * @param status
     * The status value according to the given answer. 1 = Correct, 
     * 2 = Wrong, 3 = "Passed".
     */
    private void modifyFinalItemList(String answerGiven, int status) {
        
        /*if (finalItemList.isEmpty()) {
            
            finalItemList.get(activeButtonNumber).setAnswerGiven(answerGiven);
            finalItemList.get(activeButtonNumber).setStatus(status);
            return;
        }*/
        
        finalItemList.forEach(item -> {
            
            if (itemList.get(activeButtonNumber)
                        .getLetter()
                        .equals(item.getLetter())) {
                
                item.setAnswerGiven(answerGiven);
                item.setStatus(status);
            }
        });
    }
    
    /**
     * Paints the current Letter button's background color in yellow to 
     * indicate that the turn was passed, inserts the current button value 
     * to the unansweredQuestions list, adds the "None" and "Passed" (3) 
     * values to the respective finalItemList object and if it is not the 
     * end of the button loop, it calls for displayNextQuestion().
     */
    private void passTurn() {
        
        unansweredQuestions.add(buttons.get(activeButtonNumber));
        
        modifyFinalItemList("", 3);
        
        buttons.get(activeButtonNumber)
                    .setBackground(new java.awt.Color(255, 255, 0));
        buttons.get(activeButtonNumber)
                    .setForeground(new java.awt.Color(0, 0, 0));
        
        /* See the comment for this same line in lblAnswerActionPerformed()
           method. The same logic applies here */
        if (!actionAfterAnswering()) return;
      
        displayNextQuestion();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnPass;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCondition;
    private javax.swing.JLabel lblCountdown;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JTextField txtAnswer;
    // End of variables declaration//GEN-END:variables
} //end DoughnutGame
