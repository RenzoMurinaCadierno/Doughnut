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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class is solely dedicated to validate every user input, program 
 * request or anything that a method of this package can require.
 * <p>
 * Every method in this class is commented, so feel free to check their
 * Javadoc to get to know them.
 * Renzo Nahuel Murina Cadierno
 * mail: nmcadierno@hotmail.com
 */
public class Validator {
    
    /* lblValidation is the main instantiated label, which will be used to
       display error messages */
    private JLabel label;
    
    /* if we don not require to show anything in the label, an empty
       constructor is needed */
    public Validator() { }
    
    public Validator(JLabel label) { this.label = label; }
    
    /**
     * Validates that the set of Letter, condition, question and answer
     * that the user wants to add to a category file is not already
     * added, to prevent repeated items.
     * <p>
     * First, this method checks if the file can be read as a safety
     * measure. Then if proceeds to read the file to find lines that
     * share the same Letter, condition, question and answer that the 
     * user is trying to append to that category.
     * @param incompleteFile
     * The string value the selected category in cmbCategory JComboBox.
     * @param lc
     * The button group holding the answer coditions (letterCondition 
     * buttonGroup in DoughnutMain class).
     * @param cmb
     * The JComboBox that stores the categories to play (cmbCategory
     * in DoughnutMain class).
     * @param cmb2
     * The JComboBox that stores all EnumLetter's values (cmbAddLetter
     * in DoughnutMain class).
     * @param txt1
     * The JTextField for the user to input the question (txtAddQuestion
     * in DoughnutMain class).
     * @param txt2
     * The JTextField for the user to input the answer (txtAddAnswer
     * in DoughnutMain class).
     * @return
     * True if the file cannot be read or if the set the user is trying
     * to append to the category contains an item that shares the same
     * Letter, condition, question and answer. False otherwise.
     */
    public boolean areAlreadyInTxtFile(   
            String incompleteFile, ButtonGroup lc, JComboBox cmb,
            JComboBox cmb2, JTextField txt1, JTextField txt2) {
        
        List<String> stringFiles = new ArrayList();
        List<String> falseStringsList = new ArrayList();
        File file;

        //the pattern used to compare repeated items.
        String pattern = 
                  cmb2.getSelectedItem().toString()+" // " 
                + lc.getSelection().getActionCommand()+" // " 
                + txt1.getText().trim()+" // " 
                + txt2.getText().trim();
        
        //if the file cannot be read, we display a message and return true.
        try {

            file = new File(".\\Categories__\\" + incompleteFile+".txt");

        } catch (Exception e) {
            
            Util.displayErrorCouldNotFindFiles("play a quick game.");

            Util.setErrorBorder(cmb);

            return true;
        }

        /* we read the file, filter the lines using the pattern above and
           if we find any instance of repetition, it is added to stringFiles */
        try (BufferedReader reader = 
                            new BufferedReader(
                                    new FileReader(file))) { 

            stringFiles = reader
                    .lines()
                    .filter(a -> a.equalsIgnoreCase(pattern))
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
            
            /* if there were repeated items, we generate and display an 
               error message showing the conflictive lines */

            if (!stringFiles.isEmpty()) {

                Util.setErrorBorder(cmb);

                falseStringsList.add("Repeated items in " + file.getName() +":\n\n");

                do {

                    try {

                        falseStringsList.add(stringFiles.get(0) + "\n");

                    } catch (IndexOutOfBoundsException e) { break; }

                    stringFiles.remove(0);

                } while (falseStringsList.size() < 4 || stringFiles.isEmpty());

                if (!stringFiles.isEmpty()) {
                    falseStringsList.add("...and more.\n\n");
                    
                } else { falseStringsList.add("\n"); }

                falseStringsList.add("Please choose a different category to add the");
                falseStringsList.add("Q and A, or create a new category for them");

                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain, 
                        falseStringsList.toArray(), 
                        "Reading files error", 
                        JOptionPane.ERROR_MESSAGE);

                return true;
            }

        } catch (IOException exc) { 

            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "Error while trying to read the Category file.\n\n"
                  + "This is an internal error in the app, please\n"
                  + "contact the programmer for a solution.\n\n"
                  + Util.getErrorLocation(
                            "Validator", 
                            "areAlreadyInTxtFile()", 
                            "BufferedReader / FileReader / File"),
                    "Reading files error", 
                    JOptionPane.ERROR_MESSAGE);

            Util.setErrorBorder(cmb);

            return true;
        }
        return false;
    }
    
    /**
     * Checks if the texts inside the txtAddQuestion and
     * txtAddAnswer JTextFields are empty, are empty spaces 
     * or are the default values.
     * @param txt1
     * txtAddQuestion JTextField.
     * @param txt2
     * txtAddAnswer JTextField.
     * @return 
     * True if the texts inside any JTextFields are empty,
     * are only empty spaces or are the default "Question"
     * or "Answer" values. False otherwise.
     */
    public boolean areDefaultOrEmptyValues(JTextField txt1, JTextField txt2) {
        
        if (txt1.getText().trim().equalsIgnoreCase("Question")) {
            
            setErrorMsgAndBorders(
                    "You cannot add the default "
                  + "\"Question\" value as a question.",
                    txt1,
                    txt2);

            return true;
            
        } else if (txt1.getText().trim().equalsIgnoreCase("Pregunta")) {
            
            setErrorMsgAndBorders(
                    "You cannot add the spanish default "
                  + "\"Pregunta\" value as a question.",
                    txt1,
                    txt2);

            return true;
         
        } else if (txt1.getText().isEmpty() 
                || txt1.getText().trim().length() == 0) {
            
            setErrorMsgAndBorders(
                    "You cannot add an empty text as a question.",
                    txt1,
                    txt2);

            return true;
        
        } else if (txt2.getText().trim().equalsIgnoreCase("Answer")) {
        
            setErrorMsgAndBorders(
                    "You cannot add the default "
                  + "\"Answer\" value as an answer.",
                    txt2,
                    txt1);

            return true;
            
        } else if (txt2.getText().trim().equalsIgnoreCase("Respuesta")) {
        
            setErrorMsgAndBorders(
                    "You cannot add the spanish default "
                  + "\"Respuesta\" value as an answer.",
                    txt2,
                    txt1);

            return true;
            
        } else if (txt2.getText().isEmpty()
                || txt2.getText().trim().length() == 0) {
            
            setErrorMsgAndBorders(
                    "You cannot add an empty text as an answer.",
                    txt2,
                    txt1);

            return true;
        
        } else {
            
            return false;
        }
    }
    
    /**
     * Checks if the "Categories__" folder is already created.
     * @return
     * True if it is already in the proper path, false otherwise.
     */
    public boolean categoryFolderIsAlreadyCreated() {
        
        List<String> files = new ArrayList();
        
        //we try to read the path and search for the folder.
        try(Stream<Path> path = Files.walk(Paths.get(".\\Categories__"))) {

        //if we cannot find it, and exception is triggered. We return true.
        } catch (IOException e) { 
          
            return false;
        }
        
        //if we found it, then we return true.
        return true;
    }
    
    /**
     * Checks that the JComboBox has no values in it.
     * <p> If it does not, then an error border is set to it and a 
     * message is displayed indicating further actions to take.
     * @param jcb
     * cmbCategory JComboBox in DoughnutMain class.
     * @return 
     * True if the JComboBox has no values in it, false otherwise.
     */
    public boolean cmbBoxIsEmpty(JComboBox jcb) {
        
        if (jcb.getItemCount() <= 0) {
            
            Util.displayErrorCouldNotFindFiles("play a quick game.");    
            
            Util.setErrorBorder(jcb);
            
            return true;
        }
        return false;
    }

    /**
     * Tries to read the file to make sure is a valid ".txt" one.
     * @param incompleteFile
     * The file name, without its path and its extension.
     * @param cmb
     * cmbCategory JComboBox.
     * @return 
     * True if no errors are encountered while trying to read the file,
     * false otherwise.
     */
    public boolean isFile(String incompleteFile, JComboBox cmb) {
        
        try {
            
            File file = new File(".\\Categories__\\" + incompleteFile+".txt");
            
        } catch (Exception exc) {
            
            Util.displayErrorCouldNotFindFiles(
                  "add the Q and A. Or create a\n"
                + "new Category to add the item");  
            
            Util.setErrorBorder(cmb);
            
            return false;
        }
        return true;
    }
    
    /**
     * If cmbGameTime (Game Time JComboBox) has an error border set,
     * this method checks that the next selected item, when clicked 
     * on it, can be parsed in a correct Integer value.
     * <p>
     * If it is, it will set the default border for the JComboBox,
     * hinting the user that they preselected a correct option.
     * @param item
     * cmbGameTime's selected item, in a correct format to apply
     * parseInt to it.
     * @param cmb 
     * cmbGameTime JComboBox.
     */
    public void isGameTimeSecondaryCheck(String item, JComboBox cmb) {
        
        try { Integer.parseInt(item.substring(0, 3));

        } catch (NumberFormatException e) {

            try { Integer.parseInt(item.substring(0, 2));

            } catch (NumberFormatException err) { 

                return;
            }
        }  
        
        Util.setDefaultBorder(cmb);
    }
    
    /**
     * Validates that the selected item in cmbGameTime (Game 
     * Time JComboBox) can be parsed in an Integer value.
     * <p>
     * This method checks for the first two and first three
     * characters of the item passed to it as a String.
     * @param item
     * cmbGameTime's selected item.
     * @param cmb
     * cmbGameTime JComboBox.
     * @return
     * True if the selected item's first two characters or
     * first three characters can be parsed in an Integer
     * value, false otherwise.
     */
    public boolean isGameTimePrimaryCheck(String item, JComboBox cmb) {
        
        try { Integer.parseInt(item.substring(0, 3));

        } catch (NumberFormatException e) {

            try { Integer.parseInt(item.substring(0, 2));

            } catch (NumberFormatException err) { 
                
                setErrorMsgAndBorders("Invalid game time", cmb);

                return false;
            }
        } 
        return true;
    }
    
    /**
     * <b>
     * Validates that the user does not submit the same question,
     * answer and condition more than once.
     * </b>
     * <p>
     * It checks all previously added questions, answers and
     * conditions. If any combination of the same question and 
     * answer, the same question and condition, or the same 
     * answer and condition matches one of those added previously,
     * it will return true.
     * <p>
     * It also displays a warning message inside the JLabel passed
     * as a parameter while calling the constructor.
     * @param list
     * The item list of all user input components for the configs.
     * @return 
     * False if the combined input values are altogether different 
     * from a past question. True otherwise.
     */
    public boolean isRepeatedQuestion(
            List<Item> list, ButtonGroup lc, JComboBox cmb,
            JTextField txt1, JTextField txt2) {
        
        for (Item item : list) {
            
            //if the letter is repeated, the set is not added.
            if(item
                    .getLetter()
                    .equals(cmb.getSelectedItem().toString())) {
                
                setErrorMsgAndBorders(
                        "Cannot add the same letter twice",
                        cmb,
                        txt1, txt2);
                
                return true;
                
            /* if the question and condition combo is repeated, 
               the set is not added */    
            } else if ( item
                            .getCondition()
                            .equals(lc.getSelection().getActionCommand())
                     && item
                            .getQuestion()
                            .equals(txt1.getText().trim()) ) {
                
                setErrorMsgAndBorders(
                    "Cannot add the same question "
                  + "and condition twice",
                    txt1,
                    txt2, cmb);

                return true; 
            
            /* if the answer and condition combo is repeated,
               the set is not added */
            } else if ( item
                            .getCondition()
                            .equals(lc.getSelection().getActionCommand())
                     && item
                            .getAnswer()
                            .equals(txt2.getText().trim()) ) {
                
                setErrorMsgAndBorders(
                    "Cannot add the same answer "
                  + "and condition twice",
                    txt2,
                    txt1, cmb);
                
                return true; 
            
            /* if the question and answer combo is repeated,
               the set is not added */    
            } else if ( item
                            .getQuestion()
                            .equals(txt1.getText().trim())
                     && item
                            .getAnswer()
                            .equals(txt2.getText().trim()) ) {
                
                setErrorMsgAndBorders(
                    "Cannot add the same question "
                  + "and answer twice",
                    txt1,
                    cmb);

                Util.setErrorBorder(txt2);

                return true; 
            }   
        }
        
        //nothing is repeated, the set is an unique one.
        return false;
    }
    
    /**
     * Validates that the Question field (txtAddQuestion in Doughnut
     * class) does not contain upper-case stressed spanish letters,
     * and that the Answer field (txtAddAnswer in Doughnut class)
     * does only contain english letters, lower-case stressed spanish
     * letters, numbers and/or spaces.
     * @param jtxt
     * txtAddQuestion and txtAddAnswer JTextfields.
     * @return
     * False if txtAddQuestion does not contain upper-case stressed 
     * spanish letters and txtAddAnswer does only contain english 
     * letters, lower-case stressed spanish letters, numbers and/or 
     * spaces. True otherwise.
     */
    public boolean isUnsupportedCharacter(JTextField ...jtxt) {
        
        for (JTextField txt : jtxt) {
            
            if (txt.getName().equals("txtAddAnswer")) {
                
                if (!txt.getText().matches(
                    "[A-Za-z0-9\\sáéíóúñÑ]+")) {

                    setErrorMsgAndBorders(
                        "Use numbers, spaces and/or A to Z "
                      + "letters for the answer (no stressed caps.)",
                        txt);

                    return true;
                }
            
            } else if (!txt.getText().matches(
                    "[\\w\\W&&[^ÁÉÍÓÚ]]+")) {

                setErrorMsgAndBorders(
                    "You cannot use upper-case stressed spanish "
                  + "letters for the question.",
                    txt);
            
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validates that the name of the category file to be created is not
     * named "README" or "LEEME", does not share the same name with one
     * already in existance, and that the name consists of 3 to 25 in any
     * combination of english letters, numbers and spaces.
     * @param categoryName
     * The user inputted name for the category file to be created.
     * @return
     * False if anything described above fail to validate. True otherwise.
     */
    public boolean isValidCategoryName(String categoryName) {
        
        /* first, a quick check to make sure that the user does not 
           create a category named "README" or "LEEME", even if those
           files do not exist yet. */
        String readmeTryHard = categoryName.replace(" ", "");
        
        if( readmeTryHard.toLowerCase().equals("readme")
         || readmeTryHard.toLowerCase().equals("leeme")
          ) {

            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "You cannot name your category file\n"
                  + "\"README\" or \"LEEME\" as it will\n"
                  + "conflict with the help files this\n"
                  + "app generates.\n\n" +
                    "Please try with different name.\n",
                    "Invalid category name", 
                    JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
        
        List<String> files = new ArrayList();
        String pattern = "[A-Za-z0-9\\s]{3,25}";
       
        /* then, we read the "Categories__" file to try find any category that
           matches the name of the category the user is trying to create */
        try(Stream<Path> path = Files.walk(Paths.get(".\\Categories__\\"))) {
           
            files = path
                .map(a -> a.toString())
                .filter(b -> b.equalsIgnoreCase(".\\Categories__\\"+categoryName+".txt"))
                .collect(Collectors.toList());

            // if there are, a message is displayed and the file is not created.
            if(!files.isEmpty()) {
                
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain, 
                        "The name for that category already exists.\n\n"
                      + "Please try a with different one.\n\n"
                      + "By the way, it is case insensitive.",
                        "Invalid category name", 
                        JOptionPane.ERROR_MESSAGE);
                
                return false;
            }
        
        } catch (IOException e) { 
        
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "Could not reach the category files.\n\n"
                  + "There could be no categories added to\n"
                  + "the list, or the path where this app is\n"
                  + "saved has no administrator rights, or\n"
                  + "there is an internal error in the app.\n\n"
                  + "If it is the last option, please contact\n"
                  + "the programmer for a solution.\n\n"
                  + Util.getErrorLocation(
                            "Validator", 
                            "isValidCategoryName()", 
                            "Stream / Files / Paths"),
                    "Accessing files error", 
                    JOptionPane.ERROR_MESSAGE);
        
            return false;
        }
        
        /* if the regex pattern for the name does not validate, we display 
           a message to the user telling them so, and the file is not created */
        if(!categoryName.matches(pattern)) {
            
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "The name does not match the conditions.\n\n"
                  + "Please use english letters, numbers or\n"
                  + "spaces (min 3, max 25 characters)",
                    "Invalid category name", 
                    JOptionPane.ERROR_MESSAGE);
        
            return false;
        }
        
        /* after the checks, the category name for the file that will be
           created is considered valid, so we return true. */
        return true;
    }
    
    /**
     * Validates that the typed category's name is in the correct
     * format.
     * <p>
     * This method checks for any character asides a letter, a
     * number or spaces and will return the corrected category 
     * name (with no extra characters asides from the ones 
     * mentioned before), if it finds one or more invalid inputs.
     * @param createdCategoryName
     * The user inputted category name while creating a new one.
     * @return 
     * A new category name, correctly formatted (trimmed and with
     * no invalid characters)
     */
    public static String isValidCategoryPatternName(String createdCategoryName) {
        
        List<String> files = new ArrayList();
        
        if ((createdCategoryName
                    .toLowerCase())
                    .equals("i wanna be the very best")) {
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain,"Like no one ever was! :D");
            createdCategoryName = "Pokemon"; 
        } else if ((createdCategoryName         //psst, don't spoil the fun.
                    .toLowerCase())
                    .equals("90s games were better")) {
            createdCategoryName = "Retro games";
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain,"And that's why we praise nostalgia!");
        } else
            return createdCategoryName;

        final String patternName = createdCategoryName;
        
        try(Stream<Path> path = Files.walk(Paths.get(".\\Categories__\\"))) {

            files = path
                .map(a -> a.toString())
                .filter(b -> b.contains("\\"+patternName+".txt")
                          || b.contains("\\"+patternName.toLowerCase()+".txt"))
                .collect(Collectors.toList());

            if(!files.isEmpty()) {

                return "wrongformat!";
            }
            JOptionPane.showMessageDialog(DoughnutMain.rMain,"Cheat activated!");
            return patternName;
            
        } catch(IOException e) {

            Util.displayErrorWrongPermissions();
            return null;
        }
    }
    
    /**
     * <b> 
     * Validates if the .txt file associated to the item selected 
     * in cmbCategory is next to the .jar file and if it is written 
     * in the correct format. It also prevents the user to start a 
     * quick game without any valid .txt files.
     * </b>
     * <p>
     * This method generates a File object with the selected cmbBox
     * item. Then it filters it via a BufferedReader object to
     * check that every line in the .txt file is written correctly.
     * If any steps fail, it displays an error JOptionPane with the
     * necessary actions to take.
     * @param incompleteFile
     * The selected item in cmbCategory, missing the ".txt" extension.
     * @param cmb
     * Used to set cmbCategory's border color to red if validation fails.
     * @return
     * False if any steps fail to validate, true otherwise.
     */
    public boolean isValidTxtFile(String incompleteFile, JComboBox cmb){
        
        List<String> stringFiles = new ArrayList();
        File file;
        
        /* This RegEx translates to a string that starts with any english letter
        (Upper or lower case) and follows up with the " // " separator (1). 
        Then, if "contains"|"begins with"|"ends with" strings are present, and 
        another separator (2). After that, 1 to 100 characters for the 
        question and another separator (3). Finally, 1 to 20 characters for 
        the answer, accepting the code form of the lower-case stressed spanish 
        letters, their regular form and the letters ñ and Ñ. (4) */
        String pattern = 
                  "[A-Za-z]\\s\\/{2}\\s" //(1)
                + "(contains|Contains|Begins\\swith|begins\\swith"
                + "|Ends\\swith|ends\\swith)\\s\\/{2}\\s" //(2)
                + "[\\w\\W]{1,100}\\s\\/{2}\\s" //(3)
                + "([A-Za-z0-9\\sáéíóúñÑ]|"
                + "Ã¡|Ã­|Ãº|Ã©|Ã³|Ã‘|Ã±){1,20}"; //(4)
        
        /* we try to read the file. If we cannot, we show an error message
           and return false */
        
        try {

            file = new File(".\\Categories__\\" + incompleteFile+".txt");

        } catch (Exception e) {
            
            Util.displayErrorCouldNotFindFiles("play a quick game.");
    
            Util.setErrorBorder(cmb);

            return false;
        }
        
        // we read the file.
        try (BufferedReader reader = 
                            new BufferedReader(
                                    new FileReader(file))) { 
         
            /* we filter the lines that match the regex pattern, sort
               them by their naturalOrder and collect them in stringFiles */
            
            stringFiles = reader        
                .lines()
                .filter(a -> a.matches(pattern))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
         
            // if there are less than 10 lines, the game cannot initialize.
            if(stringFiles.size() < 10) { //esc to quit

                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain, 
                        "Not enough items inside the Category file.\n\n"
                      + "There are less than 10 valid sets of Q and \n"
                      + "As in the selected Category file.\n\n "
                      + "Please add \n"
                      + "sets of Q and As until you meet the quantity \n"
                      + "and/or format requirements and try again.",
                        "Category files error", 
                        JOptionPane.ERROR_MESSAGE);
                
                Util.setErrorBorder(cmb);

                return false;
            }
            
            List<String> availableLetters = new ArrayList();
            
            /* we add each Letter item (first character) of each filtered 
               line to availableLetters list, assuring that no letters are
               repeated. This measure is to add variety to the game. */
            stringFiles.forEach(item -> {
                if(!availableLetters.contains(item.substring(0, 1)))
                    availableLetters.add(item.substring(0, 1));
            });
            
            /* without 10 or more lines with different Letter items, the
               game cannot initialize. */
            if(availableLetters.size() < 10) {
                
                Util.setErrorBorder(cmb);

                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain, 
                        "Not enough sets of Q and A's with\n"
                      + "different letters.\n\n"
                      + "The category file does not contain\n"
                      + "a minimum of ten sets of Q and A's\n"
                      + "with different letters as a condition.\n\n"
                      + "Plase, correct it and try again.",
                        "Reading files error", 
                        JOptionPane.ERROR_MESSAGE);

                Util.setErrorBorder(cmb);
                return false;
            }
   
        } catch (Exception e) { 
        
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "Error while trying to load presetted Q and A's\n\n"
                  + "If the \"Categories__\" folder or any file inside\n"
                  + "it was modified on runtime, make sure to add them\n"
                  + "again, or generate them by creating the default\n"
                  + "category files with the respective button.\n\n"
                  + "If this was not the case, then this is an internal\n"
                  + "error in the app. Please contact the programmer\n"
                  + "for a solution.\n\n"
                  + Util.getErrorLocation(
                            "Validator", 
                            "isValidTextFile()", 
                            "BufferedReader / FileReader / File"),
                    "Reading files error", 
                    JOptionPane.ERROR_MESSAGE);
            
            return false;
        }

        /* At this point, it is safe to assume that the category text file
           contains lines that do not follow the correct format for the app
           to work. So, we use this trycatch clause to read the file once
           more, get those lines and display them in an error message, so
           that the user knows where and what to correct. */
        try (BufferedReader reader = 
                            new BufferedReader(
                                    new FileReader(file))) { 

            stringFiles = reader
                    .lines()
                    .filter(a -> !a.startsWith("#")
                              && !a.isEmpty()
                              && !a.matches(pattern))
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            if (!stringFiles.isEmpty()) {

                List<String> falseStringsList = new ArrayList();
                
                Util.setErrorBorder(cmb);

                falseStringsList.add("Invalid items in " + file.getName() +":\n\n");

                do {

                    try {

                        falseStringsList.add(
                                Util.convertToSpanishCharacters(
                                        stringFiles.get(0) + "\n"));

                    } catch (IndexOutOfBoundsException e) { break; }

                    stringFiles.remove(0);

                } while (falseStringsList.size() < 4 || stringFiles.isEmpty());

                if (!stringFiles.isEmpty()) {
                    falseStringsList.add("...and more.\n\n");
                    
                } else { falseStringsList.add("\n"); }

                falseStringsList.add(
                        "Please correct each displayed line in the "
                      + "text file to be able to play on that category.\n"
                      + "If you need help, create and read the Help file by clicking"
                      + " on :: Help :: on the down-left corner of the window.");

                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain, 
                        falseStringsList.toArray(), 
                        "Reading files error", 
                        JOptionPane.ERROR_MESSAGE);

                Util.setErrorBorder(cmb);
                return false;
            }

        } catch (IOException exc) { 

            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "Error while trying to read the Category file.\n\n"
                  + "This is an internal error in the app, please\n"
                  + "contact the programmer for a solution.\n\n"
                  + Util.getErrorLocation(
                            "Validator", 
                            "isValidTextFile()", 
                            "BufferedReader / FileReader / File"),
                    "Reading files error", 
                    JOptionPane.ERROR_MESSAGE);
    
            Util.setErrorBorder(cmb);

            return false;
        }
        
        // everything went well, the text file is a valid one.
        Util.setDefaultBorder(cmb);
        
        return true;
    }
    
    /**
     * <b>
     * Validates that the selected answer condition (begins with, contains 
     * or ends with) is met for the respective letter and answer to add as 
     * a Q and A item.
     * </b>
     * <p>
     * For "contains" condition, the answer to add must not begin or end with
     * the chosen letter.
     * @param lc
     * The ButtonGroup that contains the answer conditions.
     * @param cmb
     * The JComboBox that contains the letters to add.
     * @param txt1
     * The JTextField used to type the answer.
     * @return
     * True if the answer condition is met for the given answer and letter
     * chosen, false otherwise.
     */
    public boolean letterConditionIsMet(
            ButtonGroup lc, JComboBox cmb, JTextField txt1) {
        
        String answerText = txt1.getText().trim();
        String selectedLetter = cmb.getSelectedItem().toString();
        String selectedCondition = lc
                                    .getSelection()
                                    .getActionCommand()
                                    .toLowerCase();

        switch(selectedCondition) {
            
            case "begins with":
                
                //if the typed answer begins with the selected letter.
                if ( answerText.startsWith(selectedLetter.toLowerCase())
                  || answerText.startsWith(selectedLetter)
                   ) {
                    
                    Util.setDefaultBorder(txt1); 
                    return true;
                
                } else {
                    
                    setErrorMsgAndBorders(
                        "Answer does not begin with "
                      + "the specified letter.",
                        txt1,
                        cmb);
                    
                    return false; 
                }
                    
            case "ends with":
                
                // if the typed answer ends with the selected letter
                if ( answerText.endsWith(selectedLetter.toLowerCase())
                  || answerText.endsWith(selectedLetter)
                   ) {
                    
                    Util.setDefaultBorder(txt1); 
                    
                    return true;
                
                } else {
                    
                    setErrorMsgAndBorders(
                        "Answer does not end with "
                      + "the specified letter.",
                        txt1,
                        cmb);
                    
                    return false; 
                }
                 
            case "contains":
                
                //if the typed answer contains the selected letter...
                if ((answerText.contains(selectedLetter.toLowerCase())
                 ||  answerText.contains(selectedLetter)
                   )) {
                    
                    /* then we check if the answer does not begin with and
                       does not end with that selected letter */
                    if( (!(answerText.startsWith(selectedLetter.toLowerCase())
                        || answerText.startsWith(selectedLetter)
                          )
                     &&   !(answerText.endsWith(selectedLetter.toLowerCase())
                        || answerText.endsWith(selectedLetter)
                          )
                       ) ) {
                        
                        /* if it does not begin or end with the selected letter,
                           then it is a valid one */
                        Util.setDefaultBorder(txt1); 
                        return true;
                    
                    } else {
                        
                        setErrorMsgAndBorders(
                            "Answer doesn't contain "
                          + "the specified letter or it "
                          + "begins/ends with it.",
                            txt1,
                            cmb);

                        return false;
                    }
                
                } else {
                    
                    setErrorMsgAndBorders(
                        "Answer does not contain "
                      + "the specified letter.",
                        txt1,
                        cmb);
                    
                    return false;
                }
                
            default:
                
                Util.setDefaultBorder(txt1); 
                return false;
        }
    }
    
    /**
     * Checks if the maximum amount of characters for both
     * Question and Answer introduced in the JTextField is 
     * exceeded.
     * <p>
     * The maximum character length for the Question is 100, 
     * and for the Answer, 20.
     * <p>
     * Keep in mind that displayCharLimit() method in DoughnutMain
     * class prevents the user from inputting more than the
     * available quantity of characters, as it erases any
     * character beyond that. However, a dedicated software 
     * or a good ol' palm-smash on the keyboard alongside a 
     * quick mouse click can bypass this limitation, so we 
     * added this extra method to prevent that.
     * @param txt
     * txtAddQuestion (JTextField for the Question to add) or
     * txtAddAnswer (JTextField for the Answer to add).
     * @return 
     * True if the JTextField exceeds the character limit, 
     * true otherwise.
     */
    public boolean maxCharsExceeded(JTextField txt) {

        switch(txt.getName()) {
            
            case "txtAddQuestion":

                if(txt.getText().length() > 100) {
                    
                    setErrorMsgAndBorders(
                        "Tried hard to get over 100 chars, huh? "
                      + "But nope, won't let you :P",
                        txt);
                    
                    return true;
                }
                return false;
              
            case "txtAddAnswer":
                
                if(txt.getText().length() > 20) {
                    
                    setErrorMsgAndBorders(
                       "Tried hard to get over 20 chars, huh? "
                      + "But nope, won't let you :P",
                        txt);
                    
                    return true;
                }
                return false;
                
            default:
                
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        "Congrats! This is a message that cannot\n"
                      + "be displayed by normal means.\n\n"
                      + "Way to go, code-miner! *pats your back*\n\n"
                      + "Shout out to you!",
                        "Hidden message",
                        JOptionPane.INFORMATION_MESSAGE);
                
                return true;
        }
    }
    
    
    /**
     * Displays the String parameter as an error message inside
     * the JLabel passed as a parameter in the constructor of
     * this class, sets an error border to the first JComponent
     * in the parameter array and a default border to the rest. 
     * @param msg
     * The string with the error message.
     * @param jcmp
     * The JComponents to set the error and default borders to.
     */
    private void setErrorMsgAndBorders(
            String msg, JComponent ...jcmp) {
        
        Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 1, 16, Color.red, Color.BLACK,
                        SwingConstants.CENTER, label);
        
        label.setText(msg);
        
        /* we set an error border to the first JComponent, and a default one
           to the rest */
        for (int i = 0; i < jcmp.length; i++) {
            
            if(i == 0) {
                Util.setErrorBorder(jcmp[i]);
                continue;
            }
            Util.setDefaultBorder(jcmp[i]);
        }
    }
}