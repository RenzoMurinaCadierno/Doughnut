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
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * A class that allocates most static methods with multiple and diverse 
 * purposes used in the whole package.
 * @author
 * Renzo Nahuel Murina Cadierno (a.k.a) "Max"
 * mail: nmcadierno@hotmail.com
 */
public class Util {
    
    static public GameOver goGUI;
    
    public Util() { }
    
    /**
     * Appends the Letter to use, Condition, Question and Answer
     * to the category file, in the correct format (split by the 
     * " // " separator).
     * @param file
     * The absolute path to the the category file.
     * @param letter
     * The selected enumLetter value of cmbAddLetter in the DoughnutMain instance.
     * @param condition
     * The selected radio button of letterCondition in the DoughnutMain instance.
     * @param question
     * The user input in txtAddQuestion in the DoughnutMain instance.
     * @param answer 
     * The user input in txtAddAnswer in the DoughnutMain instance.
     */
    public static void appendQandAtoCategory(
            File file, String letter, String condition, 
            String question, String answer) {
        
        try (FileWriter writer = new FileWriter(file, true)) {
            
            writer.write("\r\n" + letter + " // " + condition + " // "
                           + question + " // " + answer);
            
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    "Successfully added!",
                    "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(DoughnutMain.rMain, 
                    getErrorModifyCategoryFiles(
                        "Util", 
                        "appendQandAtoCategory()", 
                        "FileWriter"),
                    "Category files error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Disposes rGUI (DoughnutGame object's instance) and sets it to
     * null so that it can be Garbage Collected.
     */
    public static void closeGUIFrame() { 
        
        DoughnutMain.rGUI.dispose();
        DoughnutMain.rGUI = null;
    }
    
    /**
     * Disposes rMain (DoughnutMain object's instance) and sets it to
     * null so that it can be Garbage Collected.
     */
    public static void closeMainFrame() { 
        
        DoughnutMain.rMain.dispose(); 
        DoughnutMain.rMain = null;
    }
    
    /**
     * Disposes goGUI (GameOver's object instance) and sets it to
     * null so that it can be Garbage Collected. 
     */
    public static void closeGameOverFrame() { 
        
        goGUI.dispose(); 
        goGUI = null;
    }
    
    /**
     * Converts the spanish characters in the string passed as a param
     * to their unicode representation, so that they do not look buggy
     * when displayed inside JLabels or JOptionPanes.
     * <p>
     * Author's comment: I would have used another encoding, but none of
     * them (as far as I could explore) accepted stressed capital letters.
     * This is the very last thing I have done before uploading the final
     * version of the program (it is a patch fix), that is why I did not
     * feel the need to create a complex method to solve the issue.
     * @param str
     * The string to be converted.
     * @return 
     * The converted string.
     */
    public static String convertToSpanishCharacters(String str) {
            
        str = str
            .replace("Ã¡", "\u00e1")  //á
            .replace("Ã­", "\u00ed")  //í
            .replace("Ãº", "\u00fa")  //ú
            .replace("Ã©", "\u00e9")  //é
            .replace("Ã³", "\u00f3")  //ó
            .replace("Â¿", "\u00bf")  //¿
            .replace("Â¡", "\u00A1")  //¡
            .replace("Ã±", "\u00f1")  //ñ
            .replace("Ã‰", "\u00C9")  //É
            .replace("Ã“", "\u00D3")  //Ó
            .replace("Ãš", "\u00DA")  //Ú
            .replace("Ã‘", "\u00D1")  //Ñ
            .replace("Ã�", "\u00C1")  //Á
            .replace("Ã�", "\u00f1"); //Í    
            
        return str;
    }
      
    /**
     * Creates a "Categories__" folder in the same path as the
     * app after checking that it is not already there.
     */
    public static void createCategoryFolder() {
        
        if (new Validator().categoryFolderIsAlreadyCreated())
            return;
        
        try {
            
            File folder = new File(".\\Categories__");
            folder.mkdir();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    getErrorModifyCategoryFiles(
                        "Util", 
                        "createCategoryFolder()", 
                        "File"),
                    "Category files error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    /**
     * Creates a category file named with the string passed as
     * a value.
     * <p>
     * If it was called from DoughnutMain's createCategory() method, it 
     * creates a category file with the name of the value passed
     * as a string and types a default text into it.
     * <p>
     * If it was called from DoughnutMain's refreshDefaultCategories()
     * method, it will create the default category files and
     * type a disclaimer text and the sets of Letter, condition,
     * question and answer into them.
     * <p>
     * This method also re-creates the "Categories__" folder, as
     * a failsafe measure if it was erased during runtime.
     * @param string
     * The name of the category file to be created.
     */
    public static void createCategoryTextFile(String string) {
        
        createCategoryFolder();
        
        try(BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter( 
                                    new FileOutputStream(
                                            ".\\Categories__\\"
                                           + string
                                           + ".txt"), "utf-8"))) {
            switch (string) {
                
                //we create and fill up the default "Animals" category.
                case "Animals (default)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_eng")
                      + ANIMALS_TEXT );

                    break;
                   
                //we create and fill up the default "Animals" category (Spanish)
                case "Animales (default_esp)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_esp")
                      + ANIMALES_TEXT );

                    break;
                    
                //we create and fill up the default "Geography" category.
                case "Geography (default)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_eng")
                      + GEOGRAPHY_TEXT );

                    break;    
                    
                //we create and fill up the default "Geography" category (Spanish).
                case "Geografía (default_esp)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_esp")
                      + GEOGRAFIA_TEXT );

                    break;    
                    
                //we create and fill up the default "History" category.
                case "History (default)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_eng")
                      + HISTORY_TEXT );

                    break;       
                    
                //we create and fill up the default "History" category (Spanish).
                case "Historia (default_esp)":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "internet_esp")
                      + HISTORIA_TEXT );

                    break;     
  
                /* dummy case left here because of nostalgia. It is useless,
                   don't pay attention to it. */
                case "Pokemon":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "renzo")
                      + DEFAULT_TEXT1I); break;
                          
                /* same as before, but this time to test the disclaimer and
                   default text interactions. It has no use. */    
                case "Retro games":
                    writer.write(
                        getCategoryInfoAndDisclaimer(string, "agustin")
                      + DEFAULT_TEXTII); break;
                
                /* if the string for the category file name is not equal
                   to any of the default assigned ones, then the user is
                   creating a new category file. It will fall to the
                   default clause */
                default:
                    
                    writer.write(
                            getCategoryInfoAndDisclaimer(string, "you")
                          + DEFAULT_TEXT11);
                    
                    break;
            }
       
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(
                    DoughnutMain.rMain, 
                    getErrorModifyCategoryFiles(
                        "Util", 
                        "createDefaultCategoryTextFile()", 
                        "BufferedWriter / OutputStreamWriter / FileOutputStream"),
                    "Category files error", 
                    JOptionPane.ERROR_MESSAGE);
        } 
    }

    
    /**
     * Displays an error JOptionPane message explaining that the
     * category files could not be found and the action to take.
     * @param attemptedAction
     * What the user tried to do that triggered this error. It is
     * passed as a string to complete the message to be shown.
     */
    public static void displayErrorCouldNotFindFiles(String attemptedAction) {
        
        JOptionPane.showMessageDialog(
                DoughnutMain.rMain, 
                "No preconfigured .txt files found.\n\n"
              + "Please make sure to add the respective\n"
              + "Category text files in the \"Categories__\"\n"
              + "folder to be able to "
              + attemptedAction,
                "Could not find files", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Displays a JOptionPane box with an error message triggered
     * because of the pattern name for the category file does not
     * follow up the required format.
     */
    public static void displayErrorWrongNamePattern() {
        
        JOptionPane.showMessageDialog(
                DoughnutMain.rMain,
                "Cheat activated!\n\n"
              + "...but failed because the file already\n"
              + "exists inside the \"Categories__\"\n"
              + "folder (it is case insensitive, btw)",
                "Fatality!",
                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Displays a JOptionPane box with an error message triggered
     * because of not being able to create, modify or delete a
     * category file.
     */
    public static void displayErrorWrongPermissions() {

        JOptionPane.showMessageDialog(
                DoughnutMain.rMain,
                "Error while trying to create, modify, delete\n"
              + "files or while loading items from them.\n"
              + "Please, make sure that the folder in where you\n"
              + "are executing this application has writing and\n"
              + "reading permissions and/or administrator privileges.\n"
              + "If the error persists, contact the programmer.\n\n",
                "File Reading error",
              + JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Shows a modal Game Over message, disposes the DoughnutGame's 
     * instance and initializes a GameOver one.
     */
    public static void gameEnd() {
        
        JOptionPane.showMessageDialog(
                DoughnutMain.rGUI,
                "Let's see the results...",
                "GAME OVER!",
                JOptionPane.DEFAULT_OPTION);

        closeGUIFrame();

        goGUI = new GameOver();
        goGUI.setVisible(true);          
    }
    
    /**
     * Returns a String with the disclaimer text.
     * <p>
     * It contains information about who wrote, edited,
     * translated it (if it was), and where the questions
     * were taken from (if they were).
     * @param categoryName
     * A String with name of the category to be created.
     * @param author
     * A String that symbolizes the name of the author of that
     * category.
     * @return
     * A String with the disclaimer text to be written as a
     * header in the category text files
     */
    public static String getCategoryInfoAndDisclaimer(
                            String categoryName, String author) {
    
        String disclaimerText;
        
        switch(author) {
            
            case "agustin": {
                
                disclaimerText =
                       
                    "# : : " + categoryName + " : :\r\n\n"
                  + "#\r\n"
                  + "# This category was written by Agustín D'Agostino (a.k.a)\r\n"
                  + "# \"Agus\" (contact: aguxon@gmail.com) and by Renzo Nahuel\r\n"
                  + "# Murina Cadierno (a.k.a) \"Max\" (contact:\r\n"
                  + "# nmcadierno@hotmail.com). It was edited and added into the\r\n"
                  + "# code by the latter.\r\n"
                  + "#\r\n";
                
                break;
            }
            
            case "renzo":
                
                disclaimerText =
                       
                    "# : : " + categoryName + " : :\r\n\n"
                  + "#\r\n"
                  + "# This category was written, edited and added into the code\r\n"
                  + "# by Renzo Nahuel Murina Cadierno (a.k.a) \"Max\" (contact:\r\n"
                  + "# nmcadierno@hotmail.com).\r\n"
                  + "#\r\n";
              
                break;
                
            case "internet_eng":
                
                disclaimerText =
                        
                   "# : : " + categoryName + " : :\r\n\n"
                  + "#\r\n"
                  + "# This category written using the information provided in\r\n"
                  + "# LaffGaff website. Shout out to it. Full credits. Link:\r\n"
                  + "# http://laffgaff.com/free-trivia-questions-and-answers/\r\n"
                  + "# The category was completed with a few questions, edited\r\n"
                  + "# and added into the code by Renzo Nahuel Murina Cadierno\r\n"
                  + "# (a.k.a) \"Max\" (contact: nmcadierno@hotmail.com).\r\n"
                  + "#\r\n";
                
                break;
                
            case "internet_esp":
                
                disclaimerText =
                        
                   "# : : " + categoryName + " : :\r\n\n"
                  + "#\r\n"
                  + "# Esta categoría fue escrita con la información provista\r\n"
                  + "# por el sitio web LaffGaff. Todos los créditos a ellos.\r\n"
                  + "# Link: http://laffgaff.com/free-trivia-questions-and-answers/\r\n"
                  + "# La categoría fue completada con algunas preguntas, editada\r\n"
                  + "# y agregada al código por Renzo Nahuel Murina Cadierno\r\n"
                  + "# (a.k.a) \"Max\" (contacto: nmcadierno@hotmail.com).\r\n"
                  + "#\r\n";
                
                break;
                
            case "you":
                
                disclaimerText =
                
                    "# : : " + categoryName + " : :\r\n"
                  + "#\r\n";
                
                break;
                
            default:
                
                disclaimerText =
                
                    "# : : " + categoryName + " : :\r\n"
                  + "#\r\n";
                
                break;
        }
        
        return disclaimerText;
    }
    
    /**
     * Returns an error message string specifying the class,
     * method and object where it was triggered.
     * @param classLocation
     * a String with the class where the error or exception 
     * triggered.
     * @param method
     * a String with the method where the error or exception 
     * triggered.
     * @param object
     * a String with the object where the error or exception 
     * triggered.
     * @return 
     * a String with the error location message.
     */
    public static String getErrorLocation(
                    String classLocation, String method, String object) {
        
        return "Error in class: " + classLocation + "\n"
             + "Method: " + method + "\n"
             + "Object: " + object;
    }
    
    /**
     * Returns a customized error message that states that the app
 failed due to accessing it via a different way other than
 the DoughnutMain instance.
     * @param classLocation
     * a String with the class where the error or exception 
     * triggered.
     * @param method
     * a String with the method where the error or exception 
     * triggered.
     * @param object
     * a String with the object where the error or exception 
     * triggered.
     * @return 
     * a String with the custom wrong access error message.
     */
    private static String getErrorWrongAccess(
                String classLocation, String method, String object) {
        
        return    "This is an internal error in the app, most\n"
                + "probably because of trying to access it via.\n"
                + "a different way other than the Configs screen.\n"
                + "If that was the case, please use the app as \n"
                + "intended. If it was not, please, contact the \n"
                + "programmer for a solution.\n\n"
                + getErrorLocation(classLocation, method, object);
    }
    
    /**
     * Returns a customized error message that states that the
     * category files could not be created, modified or deleted,
     * most likely because of lacking writing/reading permissions.
     * @param classLocation
     * a String with the class where the error or exception 
     * triggered.
     * @param method
     * a String with the method where the error or exception 
     * triggered.
     * @param object
     * a String with the object where the error or exception 
     * triggered.
     * @return 
     * a String with the custom writing/reading error message.
     */
    public static String getErrorModifyCategoryFiles(
                String classLocation, String method, String object) {
        
        return    "Error while trying to create, modify, delete\n"
                + "files or while loading items from them.\n"
                + "Please, make sure that the folder in where you\n"
                + "are executing this application has writing and\n"
                + "reading permissions and/or administrator privileges.\n"
                + "If the error persists, contact the programmer.\n\n"
                + getErrorLocation(classLocation, method, object);
    }
    
    /**
     * Returns the Game Time as an Integer of two or three numbers,
 parsed from the selected item's first three or two characters
 in cmbGameTime JComboBox in DoughnutMain class.
     * @param time
     * cmbGameTime's selected item.
     * @return 
     * The first three or first two characters of cmbGameTime's
     * selected item parsed as an Integer.
     */
    public static int getGameTime(String time) {
        
        try {
            
            return Integer.parseInt(time.substring(0, 3));

        } catch (NumberFormatException e) {

            return Integer.parseInt(time.substring(0, 2));
        }
    }
    
    /**
     * Gets one set of Q and A's inside the selected category file.
     * <p>
     * This method reads the category file, filters the sets of
     * Q and A's evaluating the available Letters to use in each one,
     * and randomly selects one of them to return.
     * <p>
     * Since this method is called in a DoughnutGame object instance (once
     * all other validations returned correctly and the DoughnutMain instance
     * was disposed), the app will shut down if any exception is handled.
     * @param lettersToUse
     * All available EnumLetter values, which will be used to filter
     * the Letters to use of each line inside the selected category 
     * file.
     * @param selectedFile
     * The item selected in DoughnutMain's cmbCategory JComboBox.
     * @return 
     * a new Item object made out of a filtered and randomly selected
     * line inside the category file, or null if the app could not shut
     * down.
     */
    public static Item getItemsFromText(
                    List<String> lettersToUse, String selectedFile) {
        
        List<String> partialDefaultItems = new ArrayList();
        List<String> partialItemList = new ArrayList();
        List<String> availableLetters = new ArrayList();
        
        int letterRandomizer;
        String itemToAdd;
            
        try (BufferedReader reader = 
                            new BufferedReader(
                                    new FileReader(
                                                ".\\Categories__\\"
                                              + selectedFile
                                              + ".txt"))) {            

            partialItemList = reader        
                        .lines()
                        .sorted()
                        .collect(Collectors.toList());

            /* 
            *  This nested for loop fills availableLetters ArrayList with
            *  the specified Letters to use in the sets of Q and A's inside 
            *  the selected category file, without repeating them and ignoring
            *  case.
            */
            for (String letter : lettersToUse) {

                for(String item : partialItemList) {

                    if(    !item.isEmpty()
                        && item.substring(0, 1).equalsIgnoreCase(letter) 
                        && !availableLetters.contains(letter.toLowerCase())
                        && !availableLetters.contains(letter.toUpperCase())
                    )
                        availableLetters.add(letter); 
                }    
            }
         
        } catch (Exception e) { 
            
            JOptionPane.showMessageDialog(
                            null, 
                            "Error while trying to load presetted Q and A's\n"
                          + getErrorModifyCategoryFiles(
                                "Util", 
                                "getItemsFromText()", 
                                "BufferedReader / FileReader / File"),
                            "File reading error", 
                            JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }

        // we get a random letter out of the available ones.
        letterRandomizer = new Random().nextInt(availableLetters.size());

        try (BufferedReader reader = 
                            new BufferedReader(
                                    new FileReader(
                                            ".\\Categories__\\"
                                              + selectedFile
                                              + ".txt"))) {      

            // we get all lines in the category file that have that Letter to use.
            partialDefaultItems = reader        
                .lines()
                .filter(a -> ( a.startsWith(availableLetters.get(letterRandomizer))
                            || a.startsWith(availableLetters.get(letterRandomizer).toLowerCase())
                             )
                          && !a.isEmpty())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList()); 
   
        } catch (Exception e) { 
        
            JOptionPane.showMessageDialog(
                            null,
                            "Error while trying to load presetted Q and A's\n"
                          + getErrorModifyCategoryFiles(
                                "Util", 
                                "getItemsFromText()", 
                                "BufferedReader / FileReader / File"),
                            "File reading error", 
                            JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }
      
        // we randomly select one of those filtered lines.
        itemToAdd = partialDefaultItems
                                .get(new Random()
                                .nextInt(partialDefaultItems.size()));
        
        //try {
            /* we convert the special spanish characters (if any), to unicode
            so that they do not look like a garbled mess */
            
            /*itemToAdd= new String(itemToAdd.getBytes("ISO-8859-1"),"UTF-8");
            answertolowercase*/
            
           
        /*} catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        try {
            
            //creates a new Item object out of the selected line and returns it.
            String[] str = itemToAdd.split(" // ");
            
            str[3] = convertToSpanishCharacters(str[3]);
            
            return new Item(str[0].toUpperCase(), str[1], str[2], str[3]);
            
        } catch (Exception e) { 
            
            JOptionPane.showMessageDialog(
                            null,
                            "Error while trying to validate String items\n"
                          + getErrorWrongAccess(
                                "Util", 
                                "getItemsFromText()", 
                                "String"),
                            "File reading error", 
                            JOptionPane.ERROR_MESSAGE); 
            
            System.exit(1);
        }
        
        System.exit(1);
        
        return null;
    }

    /**
     * Sets a desired font, style, size and foreground color.
     * @param font
     * A string with the desired font.
     * @param style
     * The constant value of the style (0 = normal, 1 = bold, 2 = italic).
     * @param size
     * An int with the desired size.
     * @param fgColor
     * The constant value of the foreground color (like Color.black), 
     * or a valid value for one (e.g.: RGB).
     * @param jcmp
     * The JComponents to apply the format to.
     */
    public static void setFontStyleSizeColor(
            String font, int style, int size, 
            Color fgColor, JComponent ...jcmp) {
        
        for(JComponent jc : jcmp) {
        
            jc.setOpaque(true);
            jc.setFont(new Font(font, style, size));
            jc.setForeground(fgColor); 
        }
    }
    
    /**
     * Sets a desired font, style, size and foreground and background 
     * color.
     * @param font
     * A string with the desired font.
     * @param style
     * The constant value of the style (0 = normal, 1 = bold, 2 = italic).
     * @param size
     * An int with the desired size.
     * @param fgColor
     * The constant value of the color (like Color.black), or a valid 
     * value for one (e.g.: RGB).
     * @param bgColor
     * Same as fgColor.
     * @param jcmp 
     * The JComponents to apply the format to.
     */
    public static void setFontStyleSizeColorBgColor(
            String font, int style, int size, 
            Color fgColor, Color bgColor, 
            JComponent ...jcmp) {
        
        for(JComponent jc : jcmp) {
        
            jc.setOpaque(true);
            jc.setFont(new Font(font, style, size));
            jc.setForeground(fgColor);       
            jc.setBackground(bgColor);
        }
    }
    
    /**
     * Sets a desired font, style, size and foreground and background 
     * color, and horizontal alignment of the JLabels passed as parameter.
     * @param font
     * A string with the desired font.
     * @param style
     * The constant value of the style (0 = normal, 1 = bold, 2 = italic).
     * @param size
     * An int with the desired size.
     * @param fgColor
     * The constant value of the color (like Color.black), or a valid 
     * value for one (e.g.: RGB).
     * @param bgColor
     * Same as fgColor.
     * @param alignment
     * The int corresponding to the SwingConstants class, or the enum
     * value for it.
     * @param jcmp 
     * The JLabels to apply the format to.
     */
    public static void setLabelFontStyleSizeColorBgColorHAlignment(
            String font, int style, int size, 
            Color fgColor, Color bgColor, int alignment,
            JLabel ...jcmp) {
        
        for(JLabel jc : jcmp) {
        
            jc.setOpaque(true);
            jc.setFont(new Font(font, style, size));
            jc.setForeground(fgColor);       
            jc.setBackground(bgColor);
            jc.setHorizontalAlignment(alignment);
        }
    }
    
    /**
     * Sets an error border to the assigned JComponents.
     * <p>
     * Its format is the standard 1px solid red line border.
     * @param jcmp
     * The JComponents to set the error border.
     */
    public static void setErrorBorder(JComponent ...jcmp) {

        for (JComponent cmp : jcmp) {
        
        cmp.setBorder(BorderFactory
                .createLineBorder(new java.awt.Color(255, 51, 51)));
        }
    }
    
    /**
     * Sets an the default border to the assigned JComponents.
     * @param jcmp 
     * The JComponents to set the error border.
     */
    public static void setDefaultBorder(JComponent ...jcmp) {
        
        for (JComponent cmp : jcmp) {
            
            cmp.setBorder(UIManager
                .getLookAndFeel()
                .getDefaults()
                .getBorder("TextField.border"));
        }
    }
    
    /**
     * Sets the text param to the assigned JComponents.
     * <p>
     * Works for JLabels and JTextFields.
     * <p>
     * If "DEFAULT", txtAddQuestion and txtAddAnswer are 
     * passed as params, then it sets the default values 
     * "Question" and "Answer" to their respective texts.
     * <p>
     * If "DEFAULT", lblCondition, lblLetter, lblQuestion,
     * lblAnswerGiven, lblAnswer and lblStatus are passed
     * as params, then it sets the default values "Condition",
     * "Letter", "Question", "Answer Given", "Answer" and
     * "Status" to their respective texts.
     * @param text
     * The text to be set, or "DEFAULT" 
     * @param jcomp
     * JTextFields and JLabels used in the Configs and Game 
     * Over forms. 
     */
    public static void setText(String text, JComponent ...jcomp) {

        List<String> defaultValues = new ArrayList();
        List<JComponent> componentsList = new ArrayList();
        
        if (text.equals("DEFAULT")) {
            
            //adds to list the default values to set in the components' text.
            defaultValues.add("Condition");
            defaultValues.add("Letter");
            defaultValues.add("Question");
            defaultValues.add("Answer Given");
            defaultValues.add("Answer");
            defaultValues.add("Status");
        }
        
        //adds the components to their own list
        componentsList.addAll(Arrays.asList(jcomp));
        
        componentsList.forEach(item -> {
            
            /* if this method is called from a GameOver instance, then it
               is used to fill the JLabels inside the circle made by the
               buttons in the form. The order of the labels is the one
               given in defaultValues list, thus for each of them we set
               their text, remove the string from the list and continue 
               with the next one until the list is empty. */
            if (item instanceof JLabel && text.equals("DEFAULT")) {
                
                ((JLabel)item).setText(defaultValues.get(0));
                
                Util.setFontStyleSizeColor("Calibri", 2, 16, Color.gray, item);
                
                defaultValues.remove(0);

            } else if (item instanceof JLabel) {
                
                /* the JLabel does not require a default value, so we
                   set any desired text to it. */
                ((JLabel) item).setText(text);
                
            } else if (item instanceof JTextField && text.equals("DEFAULT")) {
                
                /* if the moethod is called from a DoughnutMain instance, then
                   it is used to set the default "Question" and "Answer"
                   text to the corresponding JTextFields once the Q and
                   A's are resetted, or lose focus with nothing written
                   in them, or if the game has just started. In any case,
                   we need to leave the "Question" and "Answer" strings
                   as the only values inside the list, so we remove all
                   the rest. */
                if (defaultValues.contains("Condition")) {

                    defaultValues.remove("Condition");
                    defaultValues.remove("Letter");
                    defaultValues.remove("Answer Given");
                    defaultValues.remove("Status");                       
                }

                /* we do the same as with the JLabels above, this time
                only with two values inside the list, corresponding to
                the "Question" and "Answer" strings. */
                ((JTextField)item).setText(defaultValues.get(0));

                Util.setFontStyleSizeColor("Calibri", 2, 16, Color.gray, item);
                
                defaultValues.remove(0);
                
            } else {
                
                /* the JTextField does not require a default value, so 
                   we set any desired text to it. */
                ((JTextField)item).setText(text);
            }
        });     
    }
    
    /**
     * Assigns a JOption message to each mouse clicked event 
     * added to the JLabels in DoughnutMain class's setEventListener() 
     * method.
     * @param lblName
     * The JLabel's name.
     */
    public static void triggerMouseEvent(String lblName) {
        
        switch(lblName) {
            
            case "lblAuthor":
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_AUTHOR_TEXT,
                        "About the author",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "lblAyuda":
                
                //if we want the help text file to be written and shown
                if(JOptionPane.showConfirmDialog(
                        DoughnutMain.rMain, 
                        Util.convertToSpanishCharacters(LBL_AYUDA_TEXT),
                        "Archivo de ayuda",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    
                    //we create it and write the help text into it.
                    try(BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter( 
                                    new FileOutputStream(
                                            ".\\Categories__\\LEEME.txt"), 
                                            "utf-8"))) {
                    
                        writer.write(LEEME_TEXT);
                        
                    } catch(Exception e) {
                        
                        JOptionPane.showMessageDialog(
                            DoughnutMain.rMain, 
                            getErrorModifyCategoryFiles(
                                "Util",
                                "displayMouseEventMsg()",
                                "BufferedWriter/OutputStreamWriter/"
                              + "FileOutPutStream/Desktop"),
                            "Could not create files", 
                            JOptionPane.ERROR_MESSAGE); 
                        
                        break;
                    }
                    
                    try { 
                        
                        /* if we have a text editor installed, we open the
                           file with it */
                        if(Desktop.isDesktopSupported()) 
                        Desktop
                                .getDesktop()
                                .edit(new File(".\\Categories__\\LEEME.txt"));
                    
                    } catch(Exception e) {
                        
                        JOptionPane.showMessageDialog(
                            DoughnutMain.rMain, 
                            "El archivo fue creado pero no pudo ser abierto.\n"
                          + "Por favor, dirígete a la carpeta\"Categories__\"\n"
                          + "que se halla al lado de la aplicación .jar, y\n"
                          + "ábrela desde ahí.",
                            "No se pudo mostrar el archivo", 
                            JOptionPane.ERROR_MESSAGE);      
                        
                        break;
                    }
                }
                break;
                
            case "lblCustomizeCategories":
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_CUSTOMIZE_CATEGORIES_TEXT,
                        "Customize categories help",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "lblCustomGame":
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_CUSTOM_GAME_TEXT,
                        "Custom category-less game help",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "lblGameTime":
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_GAME_TIME_TEXT,
                        "Total game time help",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
 
            case "lblHelp":
                
                //if we want the help text file to be written and shown
                if(JOptionPane.showConfirmDialog(
                        DoughnutMain.rMain, 
                        LBL_HELP_TEXT,
                        "Help file",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    
                    //we create it and write the help text into it.
                    try(BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter( 
                                    new FileOutputStream(
                                            ".\\Categories__\\README.txt"), 
                                            "utf-8"))) {
                    
                        writer.write(README_TEXT);
                    
                    } catch(Exception e) {
                        
                        JOptionPane.showMessageDialog(
                            DoughnutMain.rMain, 
                            getErrorModifyCategoryFiles(
                                "Util",
                                "displayMouseEventMsg()",
                                "BufferedWriter/OutputStreamWriter/"
                              + "FileOutPutStream/Desktop"),
                            "Could not create files", 
                            JOptionPane.ERROR_MESSAGE);      
                        
                        break;
                    }
                    try {
                        
                        /* if we have a text editor installed, we open the
                           file with it */
                        if(Desktop.isDesktopSupported()) 
                        Desktop
                            .getDesktop()
                            .edit(new File(".\\Categories__\\README.txt"));
                            
                    } catch (Exception e) {
                        
                        JOptionPane.showMessageDialog(
                            DoughnutMain.rMain, 
                            "The file was created but could not be opened.\n"
                          + "Please, go to the \"Categories__\" folder\n"
                          + "alongside the .jar app and open it from there.\n",
                            "Could not display files", 
                            JOptionPane.ERROR_MESSAGE);      
                        
                        break;
                    }                 
                }
                break;
                
            case "lblQuickGame":
     
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_QUICK_GAME_TEXT,
                        "Quick game help",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "lblPresetGameOrCustomizeCategories":
     
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        LBL_PRESET_GAME_OR_CUSTOMIZE_CATEGORIES_TEXT,
                        "Quick game or customize categories help",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
                
            default:
                
                JOptionPane.showMessageDialog(
                        DoughnutMain.rMain,
                        "If you changed the label's name, then there\n"
                      + "there will be no help info for you :P\n\n"
                      + "If you didn't touch anything and this error\n"
                      + "showed up, then contact the programmer\n"
                      + "for a solution.\n\n"
                      + getErrorLocation(
                            "Util", "triggerMouseEvent()", "Switch (default)"),
                        "Could not display help info", 
                        JOptionPane.ERROR_MESSAGE);    
                break;
        }  
    }
    
    /**
     * Takes any stressed spanish letter and unstresses it, normalizing
     * them to the english alphabet.
     * @param str
     * The string to be normalized.
     * @return 
     * The normalized string.
     */
    public static String unstressLetters(String str) {
            
        String unstressedStr = convertToSpanishCharacters(str);
        
        unstressedStr = str
            .replace("\u00e1", "a")  //A
            .replace("\u00C1", "a")  //a
            .replace("\u00DA", "u")  //U
            .replace("\u00fa", "u")  //u
            .replace("\u00f1", "i")  //Í
            .replace("\u00ed", "i")  //í
            .replace("\u00e9", "e")  //É
            .replace("\u00C9", "e")  //é
            .replace("\u00f3", "o")  //Ó
            .replace("\u00D3", "o")  //ó
            .replace("\u00D1", "ñ")  //Ñ
            .replace("\u00D1", "ñ");  //ñ
                
        return unstressedStr;
    }
    
    //The default text to be written when creating a category file.
    private static final String DEFAULT_TEXT11 = 
              "# Input the items to be displayed in the game in this text\r\n"
            + "# file below, on the first line that does not begin with a\r\n"
            + "# hash\r\n"
            + "# \r\n"
            + "# The letter to be shown in the wheel goes first. Then,\r\n"
            + "# input the condition of the answer (Begins with, Contains,\r\n"
            + "# or Ends with). After that, write the question to be asked\r\n"
            + "# (100 characters maximum). Finally, the answer to it (20\r\n"
            + "# characters maximum). As a rule of thumb for simplicity,\r\n"
            + "# only letters, numbers and spaces are allowed as inputs\r\n"
            + "# for the answers.\r\n"
            + "# \r\n"
            + "# Make sure everything is separated with a space, then two\r\n"
            + "# forward slashes, and then another space, like this:\r\n"
            + "# \" // \" (without the quotation marks). Immediately after\r\n"
            + "# the answer, begin a new line without leaving spaces at the\r\n"
            + "# end of the previous one.\r\n"
            + "# \r\n"
            + "# For example:\r\n"
            + "# I // Contains // How are you? // Fine\r\n"
            + "# \r\n"
            + "# Every line starting with a hash will be ignored by the\r\n"
            + "# program, as well as empty lines. Any line that begins with\r\n"
            + "# anything asides from a letter of the english alphabet or a\r\n"
            + "# hash will cause the file to be recognized as invalid by the\r\n"
            + "# program.\r\n"
            + "# \r\n"
            + "# You can start typing the items in a new line after this.\r\n";

    // the text for the lblAuthor mouse clicked event.
    private static final String LBL_AUTHOR_TEXT =
            "Designed and programmed by\n"
          + "Renzo Nahuel Murina Cadierno\n"
          + "(a.k.a) Max \n\n"
          + "Contact info: \n"
          + "Mail: nmcadierno@hotmail.com\n"
          + "GIT: github.com/RenzoMurinaCadierno";
    
    // the text for the lblAyuda mouse clicked event.
    private static final String LBL_AYUDA_TEXT =
            ":: Ayuda ::\n\n"
          + "La app generará un archivo \"LEEME.txt\" que contiene las\n"
          + "instrucciones necesarias en español para jugar, junto con\n"
          + "otra información adicional de interés.\n\n"
          + "El archivo se creará dentro de la carpeta \"Categories__\".\n\n"
          + "Por favor, asegúrate de contar con un editor de texto\n"
          + "predeterminado para abrir archivos de texto plano (por\n"
          + "ejemplo, el \"Bloc de notas\" para Windows).\n\n"
          + "Además, debes contar con permisos para leer y escribir\n"
          + "archivos y/o permisos de administrador en la sesión actual\n"
          + "antes de proceder.\n\n"
          + "¿Crear y mostrar el archivo \"LEEME.txt\"?\n\n";
    
    // the text for the lblCustomGame mouse clicked event.
    private static final String LBL_CUSTOM_GAME_TEXT =
            ":: Custom category-less game ::\n\n"
          + "To play a one-time-only game, you need to add sets which consist of one\n"
          + "question and one answer, one of the three answer conditions and a Letter\n"
          + "for them each. Once you finish completing the fields, click on the \"Add set\n"
          + "of Q and A\" button to save it in memory. 10 sets are required to begin a\n"
          + "custom game.\n\n" 
          + "The question to type has a maximum amount of 100 characters, and the\n"
          + "answer, 20. No repeated letters are allowed in the full set of 10 items,\n"
          + "and you cannot repeat any combination of the same question and condition,\n"
          + "question and answer, answer and condition, or question and answer. As a\n"
          + "rule of thumb to simplify this game, the answer can only contain letters,\n"
          + "numbers, and spaces (it accepts lower-case stressed spanish letters).\n\n"
          + "Before entering the 10 sets, select the full game time for it on the list\n"
          + "under the \"Total game time for any game you wish to play\" section and,\n"
          + "when you finish entering the sets, the app will ask you if you want to\n"
          + "start the game. The counter will start immediately, so be ready!\n\n"
          + "You can add a set of Question and Answer you type here to a category file\n"
          + "to save it permanently. If you wish to do that, check the information\n"
          + "provided in the \"Customize categories\" section.\n\n";
            
    // the text for the lblCustomizeCategories mouse clicked event.
    private static final String LBL_CUSTOMIZE_CATEGORIES_TEXT =
            
        ":: Customize categories ::\n\n"
        + "In this section, you can:\n\n"
        + "> Add a set of Question and Answer to a category file:\n\n"
        + "You can complete the fields of Question, condition, Letter and answer under the\n"
        + "\"Custom category-less section\" but instead of clicking on the \"Add set of Q\n"
        + "and A\" there, you select a category in the \"Categories to play a quick game or\n"
        + "to customize\" section and hit the \"Add to category\" button in this section\n"
        + "Doing so will permanently add that set to the selected file so that it can\n"
        + "randomly appear any time you wish to play a quick game on that category. You\n"
        + "can add as many sets as you like.\n\n"
        + "> Create a custom category file:\n\n"
        + "Clicking on the \"Create category\" button, you will give you the option to name\n"
        + "a new category file which will be added to the the \"Categories__\" folder. Be\n"
        + "aware that two files cannot share the same name, and you can't use \"LEEME\"\n"
        + "or \"README\" as names since they are reserved for the help files.\n\n"
        + "> Delete a category:\n\n"
        + "By selecting a category file inside the list in the \"Categories to play a quick\n"
        + "game or to customize\" and clicking on this option, you can delete that file. Be\n"
        + "warned that this action is permanent. The file will not be moved to the Recycle\n"
        + "bin.\n\n"
        + "> Refresh categories:\n\n"
        + "If the category files inside \"Categories__\" folder were manually changed during\n"
        + "game time, you can refresh the GUI's category list by clicking on this button.\n";
    
    // the text for the lblGameTime mouse clicked event.
    private static final String LBL_GAME_TIME_TEXT =
            
          ":: Total game time ::\n\n"
        + "This is the full game time assigned to a complete\n"
        + "custom or quick game (it is not the time for each\n"
        + "individual question, but the one for the full set\n"
        + "of 10 of them).\n\n"
        + "You will need to select the desired full game time\n"
        + "on the list here to play a manually constructed game\n"
        + "using the \"Custom category-less game\" section, or\n"
        + "a quick one under the \"Quick game\" section.";
            
    
    // the text for the lblAuthor mouse clicked event.
    private static final String LBL_HELP_TEXT =
            
          ":: Help :: \n\n"
        + "The app will generate a README.txt file which contains all\n"
        + "necessary instructions to play the game, as well as some\n"
        + "additional information.\n\n"
        + "The file will be created inside the \"Categories__\" folder.\n\n"
        + "Please, make sure to have a plain text editor installed\n"
        + "(like \"Notepad\" for Windows). You might also need to\n"
        + "have writing and reading privileges and/or administrator\n"
        + "rights in your actual session before proceeding.\n\n"
        + "Create and show \"README.txt\" file?\n\n";
    
    // the text for the lblPresetGameOrCustomizeCategories mouse clicked event.
    private static final String LBL_PRESET_GAME_OR_CUSTOMIZE_CATEGORIES_TEXT =
        ":: Categories to play a quick game or to customize ::\n\n"
        + "If you want to play a Quick Game, you will need to\n"
        + "select a category on this list to load the sets of\n"
        + "Questions and Answers from it.\n\n"
        + "Also, if you create a custom file using the option\n"
        + "inside the \"Customize categories\" section, it will\n"
        + "appear on this list. Moreover, if you attempt to delete\n"
        + "a category, you need to select it from this list first.\n\n"
        + "If you choose the \"Create/Reload default categories\"\n"
        + "option under the \"Quick game\" section, those default\n"
        + "categories will also appear on this list.";
    
    // the text for the lblQuickGame mouse clicked event.
    private static final String LBL_QUICK_GAME_TEXT =  
          ":: Quick game ::\n\n"
        + "Select a category on the list under the \"Categories to play a\n"
        + "quick game or to customize\" section, choose the total game time\n"
        + "you desire for the full game and click on the \"Play on the selected\n"
        + "category\" button to start a the game on that category.\n\n"
        + "The timer will start immediately after you hit the button, so be\n"
        + "ready!\n\n"
        + "You do not need to type anything inside the \"Custom category-less\n"
        + "game\" section to play a quick game.\n\n" 
        + "If there are no categories on the list, you can create one yourself\n"
        + "using the \"Create category\" button in the \"Cutomize categories\",\n"
        + "folder, or you can generate the default categories by clicking on the \n"
        + "\"Create/Refresh default categories\" in this section. You can also\n"
        + "add a valid category file inside the \"Categories__\" folder manually\n"
        + "and reload the game so that they appear on the list.\n\n";
    
    //the default "Animal" category text.
    private static final String ANIMALS_TEXT =
            
          "A // Contains // Which large mammal's tail is so strong it can lift its hind legs off the ground? // Kangaroo\r\n"
        + "B // Begins with // The largest mammal in the world is the ______ whale. // Blue\r\n"
        + "C // Begins with // The bactrian ________ has two humps. // Camel\r\n"
        + "D // Begins with // What is the singular name of the members of the honey bees able to mate with the queen? // Drone\r\n"
        + "E // Contains // How many legs does a lobster have? // Ten\r\n"
        + "F // Begins with // The ______ fox has unuasually large ears, which also serve to dissipate heat. // Fennec\r\n"
        + "G // Ends with // What type of animal is a Mexican hairless? // Dog\r\n"
        + "H // Begins with // What specific type of animal is a basenji? A _________ dog. // Hunting\r\n"
        + "I // Contains // How many arms do most starfish have? // Five\r\n"
        + "J // Begins with // What kind of creature is a Portuguese man o' war? // Jellyfish\r\n"
        + "K // Ends with // Whats the general collective name for a group of wolves? // Pack\r\n"
        + "L // Begins with // What kind of animal is a Komodo dragon? // Lizard\r\n"
        + "M // Contains // A bird with weeb feet is consider a _______. // Palmiped\r\n"
        + "N // Ends with // Which ape gets its name from the Malay word meaning \"man of the forest\"? // Orangutan\r\n"
        + "O // Contains // The whereabouts of demersal fish is the ______ of the sea. // Bottom\r\n"
        + "P // Ends with // What kind of animal is a karakul? // Sheep\r\n"
        + "Q // Begins with // The coat of sharp spines that protects porcupines from predators is known as ______. // Quill\r\n"
        + "R // Ends with // Which arthropod produce gossamer? // Spider\r\n"
        + "S // Contains // Which is the largest living bird? // Ostrich\r\n"
        + "T // Begins with // The scientific name for the ______ of the elephant is known as proboscis. // Trunk\r\n"
        + "U // Contains // The spiny anteater and the duck billed _____ are the two only animals to lay eggs. // Platypus\r\n"
        + "V // Contains // What is the name of the food made out of salt-cured roe of the Acipenseridae family? // Caviar\r\n"
        + "W // Begins with // \"Murder\" is the collective noun for a group of which bird? // Crow\r\n"
        + "X // Ends with // Small-to-medium-sized mammal of the Canidae family, with an upturned snout and a long bushy tail. // Fox\r\n"
        + "Y // Ends with // A cabbage white is a _______. // Butterfly\r\n"
        + "Z // Contains // Species of great ape native to forests and savannahs of tropical Africa. // Chimpanzee\r\n";
   
    //the default "Animals" spanish category text.
    private static final String ANIMALES_TEXT =

          "A // Contains // ¿Qué mamífero tiene la cola tan fuerte que puede levantar todo su cuerpo usándola como soporte? // Canguro\r\n"
        + "B // Begins with // El mamífero más grande del mundo es la _________ Azul. // Ballena\r\n"
        + "C // Begins with // El ________ vive en el desierto y tiene dos jorobas. // Camello\r\n"
        + "D // Begins with // El ________ es un cetáceo amistoso, que utiliza danzas y ecolocalización para comunicarse. // Delfín\r\n"
        + "E // Contains // ¿Cuántas patas tiene una langosta? // Diez\r\n"
        + "F // Begins with // El zorro _________ tiene orejas inusualmente largas, que sirven para disipar el calor. // Fennec\r\n"
        + "G // Contains // El tercer felino más largo, cuyo pelaje esta cubierto de manchas negras y es similar al leopardo. // Jaguar\r\n"
        + "H // Begins with // ¿Qué animal tiene el nombre técnico \"Hyaenidae\" (hienes)? // Hiena\r\n"
        + "I // Begins with // ¿Cuántos brazos tiene una estrella de mar? // Cinco\r\n"
        + "J // Contains // La _____ vive en arrecifes de coral del océano Pacífico y pasa casi toda su vida debajo del arena. // Almeja\r\n"
        + "K // Contains // Ave muy pequeña, indefensa, nativa y característica de Nueva Zelanda. // Kiwi\r\n"
        + "L // Begins with // ¿Cuál es el nombre reptiliano del Dragón de Comodo? // Lagartija\r\n"
        + "M // Contains // Un ave con pies palmeados es considerado un _________ // Palmípedo\r\n"
        + "N // Ends with // ¿Qué primate obtiene su nombre de la palabra malaya que significa \"Hombre del bosque\"? // Orangután\r\n"
        + "O // Contains // ¿Qué animal es un Pelón Mejicano? // Perro\r\n"
        + "P // Contains // Miembro de las Hymenóptera que no es una abeja o una hormiga, cuya raza solitaria es parasitoide. // Avispa\r\n"
        + "Q // Begins with // El ________ Andino (o armadillo andino) es un armadillo peludo normalmente hallado en Bolivia. // Quirquincho\r\n"
        + "R // Contains // Artrópodo que captura a sus presas con un tejido que produce con su propia seda. // Araña\r\n"
        + "S // Begins with // ¿Cuál es el ave más grande del mundo? // Avestrúz\r\n"
        + "T // Begins with // ¿Qué parte de los Elephantidae es la que utilizan como herramienta principal para bañarse? // Trompa\r\n"
        + "U // Contains // Nombre genérico de la especie de mamíferos roedores característicos por las púas en su piel. // Puercoespín\r\n"
        + "V // Contains // Hueva del pez esturión, consumida por los humanos, rara y de alto precio en el mercado. // Caviar\r\n"
        + "W // Begins with // Habita en madrigueras y cava con sus dientes delanteros. Su nombre técnico es Vombatidae. // Wombat\r\n"
        + "X // Begins with // Especie de pez perciforme de la familia Scombridae. De agua salada y más conocido como caballa. // Xarda\r\n"
        + "Y // Begins with // Hembra del Equus Ferus Caballus, mamífero domesticado de la familia de los équidos (o equinos). // Yegua\r\n"
        + "Z // Begins with // Equino característico por sus rayas blancas y negras en todo el cuerpo. // Zebra\r\n";

    //the default "Geography" category text.
    private static final String GEOGRAPHY_TEXT =
       
          "A // Begins with // Which is the highest mountain range that lies exclusively in Europe? The ______. // Alps\r\n"
        + "B // Contains // What is the capital city of Australia? // Canberra\r\n"
        + "C // Begins with // A ________ is what Spaniards call a bullfight. // Corrida\r\n"
        + "D // Ends with //  Not including Australia (it's classed as a continent), which is the largest island in the world? // Greenland\r\n"
        + "E // Begins with // Which African country used to be called Abyssinia? // Ethiopia\r\n"
        + "F // Ends with // The national emblem of Canada is the maple _____. // Leaf\r\n"
        + "G // Contains // The Taj Mahal is located in which Indian city? // Agra\r\n"
        + "H // Contains // Apart from the five oceans, the largest sea in the world is the _________ Sea. // Philippines\r\n"
        + "I // Contains // What is the capital city of Cyprus? // Nicosia\r\n"
        + "J // Begins with // Montego Bay is on which Caribbean island? // Jamaica\r\n"
        + "K // Begins with // Mount _______ is located in Tibet and is the source of Indus, Sutlej, Brahmaputra rivers. // Kailash\r\n"
        + "L // Contains // Beverly Hills is a suburb of Los _________, U.S. // Angeles\r\n"
        + "M // Ends with // Which is the capital city of Netherlands? // Amsterdam\r\n"
        + "N // Begins with // The ______ is the longest river in the world? // Nile\r\n"
        + "O // Begins with //  Mount _______ was Greece's highest mountain, home to the gods in Greek mythology. // Olympus\r\n"
        + "P // Begins with // The artificial waterway that connects the Pacific Ocean to the Atlantic Ocean is the _______ Canal. // Panama\r\n"
        + "Q // Begins with // Name given to native inhabitants of the central Andes, which is also the name of their language. // Quechuan\r\n"
        + "R // Contains // Lake _________ is the largest lake in Africa. // Victoria\r\n"
        + "S // Ends with // The ruins of the ancient city of Pompeii are near a volcano named Mount _______. // Vesuvius\r\n"
        + "T // Contains // What is the official language of Brazil? // Portuguese\r\n"
        + "U // Contains // The river Liffey flows through the centre of which Ireland city? // Dublin\r\n"
        + "V // Begins with // In the Royal Palace of _______ you find the Hall of Mirrors // Versailles\r\n"
        + "W // Begins with // Which is the least populated U.S. state? // Wyoming\r\n"
        + "X // Begins with // The highest peak in Min Mountains of Sichuan is Mount _________. // Xuebaoding\r\n"
        + "Y // Ends with // In the state of California, you can find the famous Death _______. // Valley\r\n"
        + "Z // Begins with // What is the capital and largest city of Croatia? // Zagreb\r\n";
      
    //the default "Geography" spanish category text.
    private static final String GEOGRAFIA_TEXT =
            
          "A // Begins with // ¿Cuál es la cadena de montañas más alta situada exclusivamente en Europa? Los _____. // Alpes\r\n"
        + "B // Contains // ¿Cuál es la ciudad capital de Australia? // Canberra\r\n"
        + "C // Begins with // En España hay espectáculos tradicionales. Uno de ellos es la ________ de toros. // Corrida\r\n"
        + "D // Contains // Sin incluir a Australia (es un continente), ¿cuál es la isla más grande del mundo? // Groenlandia\r\n"
        + "E // Begins with // ¿Cuál pais africano solía conocerse como Abisinia? // Etiopía\r\n"
        + "F // Begins with // ¿Cuál nación limita con Suiza, Noruega y Rusia; y cuya capital es Helsinki? // Finlandia\r\n"
        + "G // Contains // ¿El Taj Mahal se halla en qué ciudad? // Agra\r\n"
        + "H // Begins with // País que limita con el océano Pacifico, el Atlántico, Nicaragua, El Salvador y Guatemala. // Honduras\r\n"
        + "I // Contains // ¿Cuál es la ciudad capital de Chipre? // Nicosia\r\n"
        + "J // Begins with // ¿En cuál isla del Caribe se halla la Bahía Montego? // Jamaica\r\n"
        + "K // Begins with // El Monte _______ se halla en el Tíbet, y es fuente de los ríos Indus, Sutlej y Brahmaputra. // Kailash\r\n"
        + "L // Contains // Beverly Hills es un suburbio de Los ________, Estados Unidos. // Angeles\r\n"
        + "M // Ends with // ¿Cuál es la ciudad capital de los Paises Bajos? // Amsterdam\r\n"
        + "N // Begins with // ¿Cuál es el río más grande del mundo? El _____. // Nilo\r\n"
        + "O // Begins with // El Monte _____, la montaña más grande de Grecia, fue hogar de los Dioses en su mitologia. // Olimpo\r\n"
        + "P // Begins with // El canal artificial que conecta el océano Pacifico con el Atlántico es el Canal de _________. // Panamá\r\n"
        + "Q // Begins with // Nombre de los habitantes de los Andes Centrales, tambien nombre de su lengua. // Quechua\r\n"
        + "R // Contains // El Lago _________ es el más largo de Africa. // Victoria\r\n"
        + "S // Contains // Las ruinas de la ciudad de Pompeya están cerca de un volcán conocido como Monte _______. // Vesuvio\r\n"
        + "T // Contains // ¿Cuál es el lenguaje oficial de Brasil? // Portugués\r\n"
        + "U // Contains // ¿El rio Liffey fluye a través del centro de qué ciudad Irlandesa? // Dublín\r\n"
        + "V // Begins with // En el Palacio Real de ________ podemos hallar la Galería de los Espejos. // Versalles\r\n"
        + "W // Begins with // ¿Cuál es la ciudad menos populada de los Estados Unidos? // Wyoming\r\n"
        + "X // Begins with // El punto más alto en de las Montañas de Sichuan es el Monte ______. // Xuebaoding\r\n"
        + "Y // Begins with // El segundo y más grande estado soberano árabe en la peninsula Arábica. // Yemen\r\n"
        + "Z // Begins with // ¿Cuál es la capital de (y tambien la ciudad más grande de) Croacia? // Zagreb\r\n";
    
    //the default "History" category text.
    private static final String HISTORY_TEXT =
            
          "A // Contains // The _________ in Paris was an infamous prison stormed on July 14, 1789. // Bastille\r\n"
        + "B // Ends with // The ________ is a dung beetle which was worshipped by the ancient Egyptians // Scarab\r\n"
        + "C // Begins with // The space shuttle named _________ was the first one to go into space. // Columbia\r\n"
        + "D // Ends with // Alexander the Great cut the Gordian knot with his ________ to solve the puzzle. // Sword\r\n"
        + "E // Contains // What three colors appear on the Italian flag? Red, white and ________. // Green\r\n"
        + "F // Begins with // How many U.S. presidents have been assassinated? // Four\r\n"
        + "G // Contains // In which Libyan city was the American diplomatic compound attacked in September 2012? // Benghazi\r\n"
        + "H // Ends with // Charles _________ was the first man to fly solo and non-stop across the Atlantic // Lindbergh\r\n"
        + "I // Begins with // In which country did the Easter Rising take place in 1916? // Ireland\r\n"
        + "J // Begins with // Lady _____ Grey was an English queen who reigned for just nine days. // Jane\r\n"
        + "K // Contains // Which U.S. president had a home called The Hermitage? Andrew _________. // Jackson\r\n"
        + "L // Begins with // Which was the surname of the president of the U.S. during the U.S. Civil War period? // Lincoln\r\n"
        + "M // Ends with // Octavian defeated Mark Antony in the famous naval battle of 31 B.C., the Battle of _______. // Actium\r\n"
        + "N // Contains // ______ bridge was the first one to be built across the River Thames. // London\r\n"
        + "O // Contains // The _________ war took place between 1950 and 1953. // Korean\r\n"
        + "P // Contains // The Peloponnesian War was fought between Athens and which other ancient Greek state? // Sparta\r\n"
        + "Q // Begins with // In Aztec mythology, ____________ was the god of wind and knowledge. // Quetzalcoatl\r\n"
        + "R // Begins with // Ex-president Ronald __________ was shot outside the Hilton Hotel in Washington on March 30, 1981. // Reagan\r\n"
        + "S // Ends with // Which King of England was executed in 1649 during the English Civil War? _________ I. // Charles\r\n"
        + "T // Contains // Which Greek historian is known as the \"Father of History\"? // Herodotus\r\n"
        + "U // Contains // Estonia, Latvia and _______ declared their independence before the demise of USSR in September 1991. // Lithuania\r\n"
        + "V // Ends with // What was the family name of the Russian rulers from the 17th century until the 1917 revolution? // Romanov\r\n"
        + "W // Begins with // The Battle of __________  took place on Sunday, 18th June, 1815? // Waterloo\r\n"
        + "X // Contains // Russian astronaut ________ Leonov was the first man to conduct a space walk. // Alexey\r\n"
        + "Y // Contains // The Ptolemy dynasty ruled which ancient kingdom? // Egypt\r\n"
        + "Z // Begins with // Mao Tse-Tsung (or Mao _______) came to power in 1949 and was known for his \"Little Red Book\" // Zedong\r\n";

    //the default "History" spanish category text.
    private static final String HISTORIA_TEXT =
            
          "A // Contains // La prisión de _________ fue tomada el 14 de Julio de 1789, en París. // Bastilla\r\n"
        + "B // Contains // Insecto adorado por los Antiguos Egipcios, conocido como el ________ pelotero. // Escarabajo\r\n"
        + "C // Begins with // El transbordador espacial nombrado ________ fue el primero en arribar al Espacio. // Columbia\r\n"
        + "D // Contains // Alejandro Magno cortó el Nudo Gordiano con una _______ para resolver el acertijo. // Espada\r\n"
        + "E // Contains // ¿Cuáles son los tres colores de la bandera de Italia? Rojo, blanco y _______. // Verde\r\n"
        + "F // Contains // ¿En qué continente se originó la humanidad? // Africa\r\n"
        + "G // Contains // ¿En qué ciudad Libia fue atacado el edificio diplomático de Estados Unidos en Septiembre de 2012? // Benghazi\r\n"
        + "H // Ends with // Charles _________ fue el primer hombre en cruzar el océano Atlantico volando solo y sin detenerse. // Lindbergh\r\n"
        + "I // Begins with // ¿En qué país tuvo lugar el Levantamiento de Pascua en 1916? // Irlanda\r\n"
        + "J // Begins with // Lady _____ Grey fue una Reina de Inglaterra cuyo reinado duro solo 9 días. // Jane\r\n"
        + "K // Contains // ¿Qué presidente de los Estados Unidos tenía un hogar conocido como \"La Ermita\"? Andrew ________. // Jackson\r\n"
        + "L // Begins with // ¿Cuál era el apellido del presidente al mando de los Estados Unidos durante la Guerra Civil? // Lincoln\r\n"
        + "M // Ends with // Octavio derrotó a Marco Antonio en la famosa batalla naval del 31 A.C., la Batalla de _________. // Actium\r\n"
        + "N // Contains // El puente de ________ fue el primero en construirse punta a punta sobre el río Thames. // Londres\r\n"
        + "O // Contains // La guerra de ________ se llevó a cabo entre los años 1950 y 1953. // Corea\r\n" 
        + "P // Contains // La guerra de Peloponeso tuvo como participantes a dos estados Griegos: Atenas y ________. // Esparta\r\n"
        + "Q // Begins with // En la mitologia Azteca, ___________ era el dios del viento y la sabiduría. // Quetzalcoatl\r\n"
        + "R // Begins with // Al ex-presidente de los Estados Unidos, Ronald _______, le dispararon el 30 de Marzo de 1981. // Reagan\r\n"
        + "S // Ends with // El Rey de Inglaterra, _________ I,  fue ejecutado en 1649 durante la Guerra Civil Inglesa. // Carlos\r\n"
        + "T // Contains // ¿Qué historiador griego es conocido como el \"Padre de la Historia\"? // Heródoto\r\n"
        + "U // Contains // Estonia, Letonia y _______ declararon su independencia antes del retiro de la URSS en Sept. de 1991. // Lituania\r\n"
        + "V // Ends with // ¿Cuál era el nombre de la familia Rusa del siglo XVII, al poder hasta la revolucion de 1917? // Romanov\r\n"
        + "W // Begins with // La Batalla de ________ tuvo lugar un domingo 18 de Junio, en el año 1815. // Waterloo\r\n"
        + "X // Contains // El astronauta ruso _______ Leonov fue el primer hombre en realizar una caminata espacial. // Alexey\r\n"
        + "Y // Contains // La dinastía de Ptolomeo gobernó sobre el reino antiguo de ______ // Egipto\r\n"
        + "Z // Begins with // Mao Tse-Tung (o Mao ______) subio al poder en 1949 y fue conocido por su \"Pequeño Libro Rojo\" // Zedong\r\n";
    
    //dummy to test manual category file writing, nothing of interest.
    private static final String DEFAULT_TEXT1I =
            
          "A // Contains // I can only learn the same move several times, but with it, I can learn everything. // Smeargle\r\n"
        + "B // Begins with // I'm a move allowed a team member to get the stats of another one. I'm _______ pass. // Baton\r\n"
        + "C // Begins with // We are in almost every city in the whole series, alongside a pink-haired girl partner. // Chansey\r\n"
        + "D // Contains // I know a lot about Pokemon, and I'm only a simple and portable Gen 1 gadget. // Pokedex\r\n"
        + "E // Contains // Use me as a move and no one will hit you (unless it's a Z-move). // Protect\r\n"
        + "F // Begins with // The only member an evolved family who was not good even after it was given fire physical moves. // Flareon\r\n"
        + "G // Ends with // I know squirtle is the best turtle-like pokemon, but I can be good too! (but in grass-type version) // Turtwig\r\n"
        + "H // Contains // You want to be faster? Then hold me, but lock yourself up in one move. I'm the _______ skarf. // Choice\r\n"
        + "I // Contains // On the series you remember me funny, on the manga I'm a Team Rocket's agent. // Blaine\r\n"
        + "J // Begins with // We, steel pokemon, became a thing in this region. // Johto\r\n"
        + "K // Begins with // Now you see me, now you don't. Oh, yeah... You can. My stipe... I forgot. Sigh... // Kecleon\r\n"
        + "L // Begins with // I can make a cute little 2nd Generation seed grow up to become a yellow flower. I am a ____ stone. // Leaf\r\n"
        + "M // Ends with // My IQ is 5000! (and when I evolve I get to eat more ice-cream, yay!) // Alakazam\r\n"
        + "N // Contains // I have a famous creepypasta lullaby song which starts with \"Come little children\" // Hypno\r\n"
        + "O // Ends with // I can be you, I can he her, I can be it, I can be me. Who am I? // Ditto\r\n"
        + "P // Begins with // If you got struck with Scald's secondary effect, you need a _______ heal. // Burn\r\n"
        + "Q // Begins with // This pokemon is so unaware of everything, that it gained it as a skill // Quagsire\r\n"
        + "R // Begins with // You thought us dead, but we returned (again). In Ultra Sun/Moon, we are team ______ Rocket! // Rainbow\r\n"
        + "S // Ends with // I offer you a deal: resurrect and evolve me, and I'll bash anyone with my rock-hard head // Cranidos\r\n"
        + "T // Begins with // 70% accuracy? Make it rain and it'll strike you every single time. // Thunder\r\n"
        + "U // Contains // Just wait until I take this yellow shell off, and I'll sting you to death. // Kakuna\r\n"
        + "V // Begins with // I like, but really, REALLY like, the victory pose (\"peace sign\"). It's even my best move! // Victini\r\n"
        + "W // Begins with // The guards on Gen 1 won't let you pass unless you give them some fresh ______, among other options. // Water\r\n"
        + "X // Ends with // I'm an evolved water/poison Pokemon and I heal myself with my hidden ability when I'm retrieved. // Toxapex\r\n"
        + "Y // Ends with // I was to be the main series' mascot, until that little sparky mouse came by... // Clefairy\r\n"
        + "Z // Begins with // Generation 3's not-so-useful Pokemon that appears almost everywhere. // Zigzagoon\r\n";
    
    //dummy to test the automatic category file creation. I left it here for fun.
    private static final String DEFAULT_TEXTII =
            
          "A // Ends with // Sephiroth's Champion summon ability // Supernova\r\n"
        + "A // Contains // Arm-and-legless protagonist of a platform name, with extensible fists // Rayman\r\n"
        + "B // Begins with // Surname of a looney and spinny marsupial wearing stylish jeans // Bandicoot\r\n"
        + "B // Begins with // The name of Diablo 1 boss whose famous quote is \"Ah... Fresh meat!\" is The ______ // Butcher\r\n"
        + "C // Begins with // This banned game consisted of driving and crashing into obstacles, including people // Carmageddon\r\n"
        + "C // Begins with // Name of a famous RPG protagonist, spikey hair, big sword, Limit break skill // Cloud\r\n"
        + "D // Contains // Sonic can transform into Super Sonic when he collects 50 rings and 7 Chaos _______ // Emeralds\r\n"
        + "D // Begins with // Solid Snake's real name // David\r\n"
        + "E // Contains // The name of the city surrounded by nightmares, fog and an intended creepy aspect is ________ Hill // Silent\r\n"
        + "E // Ends with // Axel and Blaze are the main protagonists of Street of _____ // Rage\r\n"
        + "F // Begins with // Noble warrior who confronted Magus, wields a legendary sword and has no human form // Frog\r\n"
        + "F // Contains // Surname of an ancient ruins explorer who wields two guns as main weapons // Croft\r\n"
        + "G // Contains // In Donkey Kong Country, you can mount Rambi, Expresso, Winky, Squawks and _________ // Enguarde\r\n"
        + "G // Contains // Famous protagonist's bother, debuting in the same game as him, now owner of a haunted mansion // Luigi\r\n"
        + "H // Begins with // In Warcraft, orcs fight against _______ // Humans\r\n"
        + "H // Ends with // Name of Neo Periwinkle Cortex's main enemy // Crash\r\n"
        + "I // Contains // The strategy game where you construct civilizations though the ages is Age of ______ // Empires\r\n"
        + "I // Contains // Part of the body the first boss of Final Fantasy VII raises up that indicates you must not attack // Tail\r\n"
        + "J // Begins with // The experiments performed by the Shinra Electric Power Company are known as the _______ Project // Jenova\r\n"
        + "J // Begins with // In Mortal Combat, the nickname of the character who possesses bionic arms // Jax\r\n"
        + "K // Contains // Street fighter character with an odd skin color, capable of generating electricity // Blanka\r\n"
        + "K // Ends with // Undead pirate persuing the affections of Elaine Marley // LeChuck\r\n"
        + "L // Begins with // In Chrono Trigger, who needs to be stopped so that the world does not end? // Lavos\r\n"
        + "L // Begins with // Mortal Kombat fighter's first name. His signature skill is a horizontal flying kick // Liu\r\n"
        + "M // Begins with // Famous corporation's mascot with more jobs than you can count // Mario\r\n"
        + "M // Begins with // Sonic's side-kick's first name // Miles\r\n"
        + "N // Contains // Name of the protagonist who only screams, main weapon is a sword and heals himself with fairies // Link\r\n"
        + "N // Ends with // What is the surname of the main antagonist of Street Fighter II? // Bison\r\n"
        + "O // Contains // Link's extensible weapon which allows him to climb up high distances in one shot // Hookshot\r\n"
        + "O // Contains // In Metroid Prime, the name of Samus Aran's last Prime Pinball upgrade is the ______ ball // Force\r\n"
        + "P // Contains // Final Fantasy VII's first boss is the Guard ______ // Scorpion\r\n"
        + "P // Contains // In LoZ: Ocarina of Time, Link's horse name is ________ // Epona\r\n"
        + "Q // Begins with // Name of the game where your main weapon can shoot plungers, popcorn or bubblegum // Quackshot \r\n"
        + "Q // Contains // Name of one primary protagonist of Final Fantasy VIII, surnamed Leonhart // Squall\r\n"
        + "R // Begins with // The place where an apocalyptic pandemia was triggered by a virus outbreak is ________ city // Raccoon\r\n"
        + "R // Begins with // Sonic's pinkish fangirl's surname // Rose\r\n"
        + "S // Ends with // Doom's action takes place on Phobos and _____ // Deimos\r\n"
        + "S // Begins with // The strongest plant-type move in Generation 1 Pokemon is _______ beam // Solar\r\n"
        + "T // Ends with // Metal Gear's character who loses his right hand because of the Cyborg Ninja is Revolver ______ // Ocelot\r\n"
        + "T // Begins with // In Mario RPG, Princess Peach is known as Princess _______ // Toadstool\r\n"
        + "U // Contains // Powerful dark wizard who wields a scythe, has long hair and wears a blue cape // Magus\r\n"
        + "U // Contains // The game starring Mickey and Donald is World of _________ // Illusion\r\n"
        + "V // Contains // Name of Dr. Robotnik before he turned evil // Ovi\r\n"
        + "V // Contains // In Tekken 3, Dr. Abel sent Bryan Fury to kidnap his rival, Dr. _________ // Bosconovitch\r\n"
        + "W // Contains // Blond and funny pirate whose main enemy is a living dead is Guybrush __________ // Threepwood\r\n"
        + "W // Contains // Biological father of one, adoptive parent of seven and a mother if he wears a crown // Bowser\r\n"
        + "X // Contains // In Metal Gear, Psycho Mantis was a psychic member of _________ // Foxhound\r\n"
        + "X // Ends with // Solid Snake's favourite camouflage tool. // Box\r\n"
        + "Y // Ends with // One of the few Generation 1 Pokemon that can evolve with a moon Stone // Clefairy\r\n"
        + "Y // Ends with // Famous alien boss in Metroid, now as a playable character in Super Smash Bros. Ultimate // Ripley\r\n"
        + "Z // Begins with // The game whose main character can carry and wield several weapons and wears green is Legend of ____ // Zelda\r\n"
        + "Z // Begins with // Surname of the leader of the Battletoads // Ziegler\r\n";
    
    //text to be generated inside README.txt help file.
    private static final String README_TEXT =
            
            "\r\n"
            + "                              ::::::::::\r\n"
            + "                            ::          ::\r\n"
            + "                          ::              ::\r\n"
            + "                          ::   DOUGHNUT   ::\r\n"
            + "                          ::              ::\r\n"
            + "                            ::          ::\r\n"
            + "                              ::::::::::\r\n"
            + "\r\n"
            + "              by Renzo Nahuel Murina Cadierno (a.k.a) Max\r\n"
            + "\r\n"
            + "\r\n"
            + "                          ::  Help  file  ::\r\n"
            + "\r\n"
            + "\r\n"
            + "1. Instructions\r\n"
            + "    1.1. Main graphical user interface (first screen)\r\n"
            + "    1.2. Main functionalities\r\n"
            + "        1.2.1 Custom category-less game\r\n"
            + "        1.2.2 Quick game\r\n"
            + "        1.2.3 Customize categories\r\n"
            + "2. About the game\r\n"
            + "3. About the author\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1. Insctructions\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "This is a simple question-and-answer game, made in NetBeans IDE,\r\n"
            + "using J.D.K. 8.0.\r\n"
            + "\r\n"
            + "So, first things first, if you want to play the game, you need to\r\n"
            + "make sure to have Java Runtime Environment installed in you device\r\n"
            + "(download link: https://www.java.com/en/download/). Then, just\r\n"
            + "double click on the .jar file inside the \"dist\" folder and done!\r\n"
            + "You have just launched the main GUI!\r\n"
            + "\r\n"
            + "The questions appear on screen alongside the designed letter and\r\n"
            + "condition for the answer. The conditions can be \"begins with\",\r\n"
            + "respuesta. Las condiciones son \"comienza con\" (begins with),\r\n"
            + "\"contains\" or \"ends with\". For example:\r\n"
            + "\r\n"
            + "Condition: Begins with\r\n"
            + "Letter: D\r\n"
            + "Question: It is said to be man's best friend.\r\n"
            + "Answer: Dog\r\n"
            + "\r\n"
            + "That is to say, the answer to type begins with D, and in this example\r\n"
            + "it is \"Dog\".\r\n"
            + "\r\n"
            + "Once you have chosen a gameplay option -either a custom or a quick\r\n"
            + "one, the questions will appear on screen and a timer will start\r\n"
            + "running. You need to answer in the designed box, and you can pass\r\n"
            + "the question without answering it using the \"pass\" button or by\r\n"
            + "pressing the Tab key. If you reach the end of the cicle of questions\r\n"
            + "with some or all of them unanswered (passed) and if the timer is\r\n"
            + "still running, the game will repeat those unanswered questions,\r\n"
            + "ignoring the ones that were answered correctly or incorrectly.\r\n"
            + "\r\n"
            + "When the time is up or when all questions were answered, the game\r\n"
            + "ends and a result screen shows up, which replicates the question\r\n"
            + "list. You can click on each Letter and the associated Question,\r\n"
            + "condition, true answer, answer you have given and status for it\r\n"
            + "(correct, incorrect, passed or unanswered) will appear on screen.\r\n"
            + "Additionally, the final score and options to play again or exit the\r\n"
            + "game will be displayed too.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.1. Main graphical user interface (first screen)\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "The starting GUI is divided into three main headers, which we will\r\n"
            + "explain later in their own sections:\r\n"
            + "\r\n"
            + "> Custom category-less game\r\n"
            + "> Quick game\r\n"
            + "> Customize categories\r\n"
            + "\r\n"
            + "Furthermore, there are four other sub-headers:\r\n"
            + "\r\n"
            + "> The top header, above \"Custom category-less game\", which tells you\r\n"
            + "you where to click to get in-game instructions on what to do and\r\n"
            + "where to do it. Error messages are also displayed here.\r\n"
            + "\r\n"
            + "> The \"Total game time for any game you wish to play\" header, that\r\n"
            + "contains a list with the different options for the complete game\r\n"
            + "time (for the 10 sets of questions in total, not for each individual\r\n"
            + "one). Either you decide to play a custom game or a quick one, you\r\n"
            + "need to select the total game time which will tell the in-game counter\r\n"
            + "where to start ticking.\r\n"
            + "\r\n"
            + "> \"Categories to play a quick game or to customize\" is the category\r\n"
            + "list from where you select a file either to play a quick game or to\r\n"
            + "add a set of question, condition, letter and answer to add to a\r\n"
            + "category text file (we will explain this later in the \"Custom\r\n"
            + "category-less game\" section)..\r\n"
            + "\r\n"
            + "> The footer, that contains the ::Help:: and ::Ayuda:: labels,\r\n"
            + "which on click will create this file you are reading now (on its\r\n"
            + "english and spanish versions, depending on which label you click\r\n"
            + "on. The footer contains also my contact information if you hit the\r\n"
            + "\"Designed by Max\" text.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2. Main functionalities\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "As we mentioned in the previous section, three main headers exist\r\n"
            + "which allocate the prime functionalities of the program:\r\n"
            + "\r\n"
            + "> Custom category-less game\r\n"
            + "> Quick game\r\n"
            + "> Customize categories\r\n"
            + "\r\n"
            + "The first and second one allow you to play the game, while the last\r\n"
            + "one will let you configure the category files.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.1. Custom category-less game\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "On this section of the GUI you can generate 10 custom questions\r\n"
            + "manually, without the need of being stuck to a certain category.\r\n"
            + "These questions are one-time-only, meaning that once the game is\r\n"
            + "over, whatever you have typed is lost.\r\n"
            + "\r\n"
            + "To load a set of question, condition, letter and answer, you need\r\n"
            + "to type the question in the textbox that displays \"Question\" in\r\n"
            + "gray color. Then, you have to select the condition for the letter\r\n"
            + "and answer (Begins with, Contains or Ends with) by clicking on the\r\n"
            + "corresponding item option. After that, you choose which letter will\r\n"
            + "be used to link the condition and the answer, on the list next to\r\n"
            + "the condition options. Finally, you type the answer in the textbox\r\n"
            + "that displays \"Answer\" in gray color.\r\n"
            + "\r\n"
            + "Clicking on the \"Add set of Q and A\" button will retrieve everything\r\n"
            + "you have done above and store it into memory. Below the \"Back to\r\n"
            + "previous set\" button, a list with the currently inserted Letters\r\n"
            + "will show up, so that you can keep track of which ones you have\r\n"
            + "already used up.\r\n"
            + "\r\n"
            + "If we wish to delete the last set we have added, then we can click\r\n"
            + "on the \"Back to previous set\". This action will also bring back on\r\n"
            + "screen everything you had on that set, as a safety measure if you\r\n"
            + "want to add it back or change something and re-add it.\r\n"
            + "\r\n"
            + "It is important that you select the desired full game time before\r\n"
            + "adding the whole 10 sets to memory. You can do so by choosing the\r\n"
            + "game time option from the \"Total game time for any game you wish\r\n"
            + "to play\" section. Go to the previous section if you need more\r\n"
            + "information on it.\r\n"
            + "\r\n"
            + "Once you have added 10 sets with different Letters each and you\r\n"
            + "have chosen the total game time, the program will ask you if you\r\n"
            + "wish to begin the game. If you do not, then the last added set will\r\n"
            + "be removed from memory and everything typed and selected in it will\r\n"
            + "be displayed on screen for you to re-add it, so that the program\r\n"
            + "can ask you again.\r\n"
            + "\r\n"
            + "If you select \"OK\", then the game will begin. Keep in mind that the\r\n"
            + "timer will start up immediately, so be ready!\r\n"
            + "\r\n"
            + "Rules to add sets of Letter, condition, question and answer:\r\n"
            + "\r\n"
            + "> You cannot add empty fields or only spaces to the \"Question\" and\r\n"
            + "\"Answer\" text fields.\r\n"
            + "> You cannot type \"Question\" or \"Pregunta\" on the \"Question\" text\r\n"
            + "field, or \"Answer or \"Respuesta\" on the \"Answer text field. Those\r\n"
            + "are the default values, so they are not allowed.\r\n"
            + "> You cannot repeat previously added letters.\r\n"
            + "> You cannot repeat any combination of: question and condition,\r\n"
            + "answer and condition, or question and answer.\r\n"
            + "> The questions have a limit of 100 characters each, and the\r\n"
            + "answers, 20.\r\n"
            + "> If you choose the condition \"Contains\", then the typed answer\r\n"
            + "cannot begin with or end with the specified letter.\r\n"
            + "> The answers must respect the selected letter and condition.\r\n"
            + "> The answer only accepts A to Z letters, numbers and spaces as\r\n"
            + "characters. It supports stressed lower-case spanish letters, but not\r\n"
            + "stressed upper-case ones.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.2 Quick game\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "When you start the program up, it automatically creates a folder\r\n"
            + "named \"Categories__\" in the same path as the one you launched\r\n"
            + "it. Each category text file that hold the sets of letter, question,\r\n"
            + "answer and condition is stored there. If a category file contains\r\n"
            + "at least 10 sets with different Letters assigned to them, then that\r\n"
            + "file is elegible to play a quick game.\r\n"
            + "\r\n"
            + "To start a quick game up, you simply have to select one of those\r\n"
            + "category files which are displayed in the drop menu under the\r\n"
            + "\"Categories to play a quick game or to customize\" section, select\r\n"
            + "the full game time under the \"Total game time for any game you wish\r\n"
            + "to play \" section and click on the \"Play on the selected category\"\r\n"
            + "button.\r\n"
            + "\r\n"
            + "If the category file is valid, the game will begin immediately. If\r\n"
            + "it is not, an error message will show up on screen indicating what\r\n"
            + "failed and how to deal with it.\r\n"
            + "\r\n"
            + "The category drop menu if empty by default. To fill it up, you can\r\n"
            + "generate the default categories (explained in the next paragraph),\r\n"
            + "create a custom text file in the \"Customize categories\" header, or\r\n"
            + "add a valid category text file (.txt) manually to the self-\r\n"
            + "generated \"Categories__\" folder. Remember that the file must\r\n"
            + "contain at least 10 sets with different Letters assigned to them.\r\n"
            + "There is no limit on the amount of sets a category file can store.\r\n"
            + "\r\n"
            + "The program is able to generate default categories, which are\r\n"
            + "included into the code. To do so, you need to click on the\r\n"
            + "Generate/reload default categories\" button. Doing so will trigger\r\n"
            + "their creation, and they will automatically appear in the drop menu,\r\n"
            + "which is where they have to be selected to play a quick game\r\n"
            + "\r\n"
            + "Most sets in the default categories were extracted from LaffGaff's\r\n"
            + "website (http://laffgaff.com/free-trivia-questions-and-answers/),\r\n"
            + "I've only edited and translated them to Spanish and added some,\r\n"
            + "additional sets to complete the category. So, full credits to them.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.2 Customize categories\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "> Add to category\r\n"
            + "Following up with what was described in the first paragraph of the\r\n"
            + "previous section, the program can permanently add a set of letter,\r\n"
            + "condition, question and answer from the game directly to a text file\r\n"
            + "inside the \"Categories__\" folder.\r\n"
            + "\r\n"
            + "To do so, you need to type a question and answer on their respective\r\n"
            + "fields under the \"Custom category-less\" section, as well as\r\n"
            + "selecting the letter and the condition (just as if we were to add a\r\n"
            + "set to a one-time-only game. However, instead of clicking on the \"Add\r\n"
            + "set of Q and A\" button, you select a category you wish to store the \r\n"
            + "set from the list under the \"Categories to play a quick game or to\r\n"
            + "customize\" header, and click on the \"Add to category\" button.\r\n"
            + "If there were no errors, then everything typed and selected above will\r\n"
            + "be added to that category file. We can open that file up (inside the\r\n"
            + "\"Categories\" folder and we will find the set we have just added on\r\n"
            + "the last line.\r\n"
            + "\r\n"
            + "It is worth clarifying that the program only accepts A to Z letters,\r\n"
            + "numbers and spaces as characters for the answer. It does also support\r\n"
            + "stressed lower-case spanish letters, but not stressed upper-case ones.\r\n"
            + "\r\n"
            + "\r\n"
            + "> Create category\r\n"
            + "\r\n"
            + "Using this option we can create a text file (.txt) for a new category,\r\n"
            + "to be stored inside the \"Categories__\" folder.\r\n"
            + "\r\n"
            + "The name must contain from 3 to 25 characters (english letters,\r\n"
            + "numbers and spaces, and cannot be named \"README\" or \"LEEME\" as\r\n"
            + "those are reserved names for the help files, stored inside the same\r\n"
            + "folder.\r\n"
            + "\r\n"
            + "If the creation was successful, the file will now be displayed inside\r\n"
            + "the categories drop menu.\r\n"
            + "\r\n"
            + "> Delete a category\r\n"
            + "\r\n"
            + "If we select a category on the drop menu under the \"Categories to play\r\n"
            + "a quick game or to customize\" and click on the \"Delete category\"\r\n"
            + "button, you can delete that category file permanently.\r\n"
            + "\r\n"
            + "*WARNING*\r\n"
            + "This action is permanent. Once you delete the file, it is not sent, for\r\n"
            + "example, to the Recycle Bin if you are using Windows.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "2. About the game\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "This game was created as a first try at programming in Java and with\r\n"
            + "Object oriented programming concept.\r\n"
            + "\r\n"
            + "The idea is not that different from any question-and-answer game, but\r\n"
            + "was considered ideal to integrate each basic and essential concepts\r\n"
            + "of OOP, as well as managing file system and user interaction, flow\r\n"
            + "control with exception handling, static variables and method usage\r\n"
            + "wherever necessary, and so on.\r\n"
            + "\r\n"
            + "The whole application was constructed with 8 classes and 1 Enum.\r\n"
            + "\r\n"
            + "1. The main GUI (DoughnutMain class, the entry point of the program),\r\n"
            + "which allows the user to load the sets of letter, condition, question\r\n"
            + "and answer, to select the game time, to choose categories to play a\r\n"
            + "quick game and to configure them.\r\n"
            + "The code for this interface was written by hand, and that is why it\r\n"
            + "looks different than the other JForms in the program. Only NetBeans\r\n"
            + "text editor was used to code it, without the assistance of JForm's\r\n"
            + "standard design. The decision to do it this was mostly supported as\r\n"
            + "a way to practise to understand how a Form is designed from scratch,\r\n"
            + "how the components and listeners are added and how they interact with\r\n"
            + "each other, how variables and methods are ordered, as well as when and\r\n"
            + "where to create and instantiate objects and other characteristics.\r\n"
            + "At all times, the simplest and/or most efficient expression was tried\r\n"
            + "to be used, coded by hand without the help of any framework or tools\r\n"
            + "provided by NetBeans IDE to ease the procedure.\r\n"
            + "\r\n"
            + "2. The game GUI (DoughtnutGame class), which extracts the collected\r\n"
            + "information in the previous interface, creates finalItem and CountDown\r\n"
            + "objects (explained later) and controls the flow of the game itself. This\r\n"
            + "class is the one responsible for displaying the questions, letters and\r\n"
            + "condition on screen, read the user inputs for the answers and act as a\r\n"
            + "consequence, handling each correct, incorrect and passed questions\r\n"
            + "accordingly.\r\n"
            + "\r\n"
            + "3. The CountDown class, designed to create a parallel thread next to\r\n"
            + "the main one. This class keeps track of the remaining total game time\r\n"
            + "in seconds and counts down by one as they go by. Objects of this class\r\n"
            + "themselves by a command emitted from the game GUI class if there are\r\n"
            + "no more questions left to be answered. The CountDown class is the one\r\n"
            + "that instantiates and initializes GameOver objects.\r\n"
            + "\r\n"
            + "4. GameOver class displays a JForm that serves as a GUI for the results.\r\n"
            + "The Letters, questions and conditions shown in the game GUI are\r\n"
            + "replicated here, and the answers given by the users, the real answers\r\n"
            + "and the status condition for them (correct, incorrect, passed and \r\n"
            + "unanswered) follow along. This class commands its objects to pull the\r\n"
            + "list that contains all finalItem objects from the game instance before\r\n"
            + "disposing it, and will use it to generate all the data mentioned above.\r\n"
            + "The final result score is also displayed on screen, with the individual\r\n"
            + "scores for each status. Finally, the JForm of this class is set to offer\r\n"
            + "the options to exit the game or to play again. The latter will cause an\r\n"
            + "instantiation and initialization of a new main class object, effectively\r\n"
            + "restarting the game.\r\n"
            + "\r\n"
            + "5. Item class allows the construction of objects capable to store\r\n"
            + "the set of Letter to use in a question, the answer Condition, the\r\n"
            + "Question and Answer to it (LCQA set). The main class can instance and\r\n"
            + "initialize objects of this class each time the user inputs a LCQA set\r\n"
            + "manually to play a Custom category-less game, or when the user chooses\r\n"
            + "the Quick game option (in that case, the Item object is called ten\r\n"
            + "times to extract ten LCQA sets from the selected category file and\r\n"
            + "store them in a list which will later be retrieved by the game class\r\n"
            + "\r\n"
            + "6. FinalItem class inherits from Item and adds two new attributes\r\n"
            + "which are capable of storing the answer given by the user while in\r\n"
            + "game and the status for that answer (correct, incorrect, passed or\r\n"
            + "unanswered). Objects of this class replicate the LCQA sets in the form\r\n"
            + "of Item objets inside the list that stores them, and modify their own\r\n"
            + "attributes as the user responds in execution time. This objects are\r\n"
            + "retrieved by a GameOver instance to complete its own GUI's data.\r\n"
            + "\r\n"
            + "7. Validator class, as the name indicates, is the one that contains\r\n"
            + "all methods used to validate any user input, creation of files, file\r\n"
            + "reading, exception handling, sanity checks and any other instructions\r\n"
            + "that might need to behave properly before proceeding with the flow.\r\n"
            + "Objects created by this class interact with methods in other classes\r\n"
            + "-especially the ones allocated in Util- to manipulate the format of\r\n"
            + "some JComponents in other GUIs to, as examples, set default or error\r\n"
            + "error borders to them, or to change the format of the font to notify\r\n"
            + "the user visually that there is an error or that everything is OK.\r\n"
            + "The constructor of this class can either be an empty one or accept a\r\n"
            + "label as a parameter. That JLabel is the one used in the main class\r\n"
            + "to display error messages on screen.\r\n"
            + "\r\n"
            + "8. Util class is meant to hold all static methods and variables to be\r\n"
            + "reused throughout the whole program. Given the fact that this class\r\n"
            + "is not designed to be instanciated (there are no Util objects), it is\r\n"
            + "the natural host for all final and static variables, so as they do\r\n"
            + "not consume more memory than required. The quantity and functionality\r\n"
            + "of the methods this class holds is extense, which is why if you are\r\n"
            + "interested in knowing what they do, it is better for you to check the\r\n"
            + "code yourself. Do not worry, it is fully commented.\r\n"
            + "\r\n"
            + "> Enum:\r\n"
            + "\r\n"
            + "9. The only Enum this application uses was designed to hold the values\r\n"
            + "of the english letters of the alphabet (A-Z), and make them immutable.\r\n"
            + "Those values are used to complete the drop list of letters to choose\r\n"
            + "when creating a manual game, and to compare the letters inside the\r\n"
            + "category text files to make sure the characters are the correct ones.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "3. About the author\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "My name is Renzo Nahuel Murina Cadierno, pleasure to meet you.\r\n"
            + "I have a university degree in Business Administration and an M.B.A in\r\n"
            + "Business Management. I have been an academic researcher for two years\r\n"
            + "and I am actually working for the my State's Judiciary.\r\n"
            + "\r\n"
            + "Counting from the current month (July/2019), six months ago I fell in\r\n"
            + "love with my true passion: programming. I have assisted and approved\r\n"
            + "several courses on Java, Javascript and Python branches, and I plan on\r\n"
            + "doing many more. To this date, I have designed some mini applications in\r\n"
            + "Python that help me automatize my everyday routine at work and at home.\r\n"
            + "I am also designing and programming a NodeJS server alongside a MongoDB\r\n"
            + "database which will serve to bring my own website up that will serve both\r\n"
            + "as a way of contacting me, checking up on my progress, and as a main, free\r\n"
            + "and open source repository for anyone who is interested in downloading\r\n"
            + "my applications to use them and/or to check the code up. As far as Java\r\n"
            + "goes, this program is my first try at the language.\r\n"
            + "\r\n"
            + "Mi dream is freelance programming, but to archieve it I am yet to learn\r\n"
            + "and practise much more. As they say, better late than never, and that is\r\n"
            + "why I try hard each and every day to read a little more and write some\r\n"
            + "lines of code to succeed. My foresight is to take the international\r\n"
            + "certification exams for the three mentioned languages, and it is of\r\n"
            + "my enormous pleasure getting to know about task automation and Machine\r\n"
            + "Learning (Python), about big-scaled and highly-replicable applications\r\n"
            + "(Java) and back-end server and NoSQL management (JS, NodeJS and MongoDB).\r\n"
            + "Of course, not only getting to know them, but also doing them.\r\n"
            + "\r\n"
            + "I am an independent learner, but at the same time I take full courses\r\n"
            + "linked to everything mentioned and with a great smile drawn on my face\r\n"
            + "I try to occupy a great chunk of my free time programming given that,\r\n"
            + "of course, it is my passion.\r\n"
            + "\r\n"
            + "If you wish to consider it, with pleasure I am open to job offers.\r\n"
            + "My contact mail is the following one:\r\n"
            + "\r\n"
            + "           -----------------------------------------------\r\n"
            + "                     nmcadierno@hotmail.com\r\n"
            + "           -----------------------------------------------\r\n"
            + "\r\n"
            + "Thank you very much for reading, and for giving me the opportunity to\r\n"
            + "show you this modest first try at a program designed in Java.\r\n"
            + "You are the best!\r\n";
    
    //text to be generated inside LEEME.txt help file.
    private static final String LEEME_TEXT =
            "\r\n"
            + "                              ::::::::::\r\n"
            + "                            ::          ::\r\n"
            + "                          ::              ::\r\n"
            + "                          ::   DOUGHNUT   ::\r\n"
            + "                          ::              ::\r\n"
            + "                            ::          ::\r\n"
            + "                              ::::::::::\r\n"
            + "\r\n"
            + "             by Renzo Nahuel Murina Cadierno (a.k.a) Max\r\n"
            + "\r\n"
            + "                        :: Archivo de ayuda ::\r\n"
            + "\r\n"
            + "\r\n"
            + "1. Instrucciones\r\n"
            + "    1.1. La interfaz gráfica de entrada (primera pantalla)\r\n"
            + "    1.2. Funciones principales\r\n"
            + "        1.2.1 Juego personalizado sin categoría\r\n"
            + "        1.2.2 Juego rápido\r\n"
            + "        1.2.3 Configurar categorías\r\n"
            + "2. Acerca del programa\r\n"
            + "3. Acerca del autor\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1. Instrucciones\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "Este es un simple juego de preguntas y respuestas, hecho en el IDE\r\n"
            + "de NetBeans, utilizando J.D.K. 8.0.\r\n"
            + "\r\n"
            + "Entonces, primero lo primero: si desemos jugar, debemos asegurarnos\r\n"
            + "de tener el Java Runtime Environment instalado en nuestro dispositivo\r\n"
            + "(link de descarga: https://www.java.com/en/download/). Luego, sólo\r\n"
            + "debemos hacer doble click en el archivo .jar dentro en la carpeta\r\n"
            + "\"dist\" y listo! Acabamos de lanzar la interfaz gráfica de inicio!\r\n"
            + "\r\n"
            + "Las preguntas aparecen en pantalla junto con la letra designada\r\n"
            + "para la respuesta, y la condición que vincula a la letra con la\r\n"
            + "respuesta. Las condiciones son \"Begins with\" (comienza con),\r\n"
            + "\"Contains\" (contiene) y \"Ends with\" (termina con). Por ejemplo:\r\n"
            + "\r\n"
            + "Condición: Begins with\r\n"
            + "Letra: P\r\n"
            + "Pregunta: Dícese del animal que es el mejor amigo del hombre.\r\n"
            + "Respuesta: Perro\r\n"
            + "\r\n"
            + "Es decir, la respuesta a la pregunta comienza con P, que en este\r\n"
            + "caso es \"perro\".\r\n"
            + "\r\n"
            + "Una vez que se escoja una opción para jugar en una instancia creada\r\n"
            + "manualmente o en un juego rápido, las preguntas aparecerán en\r\n"
            + "pantalla y comenzará a correr un contador de tiempo para responder.\r\n"
            + "Se debe responder en la casilla designada y se puede pasar a la\r\n"
            + "próxima pregunta sin responder utilizando el botón de \"Pasar\" (pass)\r\n"
            + "o presionando la tecla de tabulador (Tab). Si se llega al final del\r\n"
            + "ciclo de preguntas con preguntas sin responder y con el contador de\r\n"
            + "tiempo aún corriendo, el juego repetirá las preguntas sin respuesta,\r\n"
            + "ignorando las que ya fueron contestadas correcta o incorrectamente.\r\n"
            + "\r\n"
            + "* ADVERTENCIA *\r\n"
            + "\r\n"
            + "NO respondas usando mayúsculas tildadas, ya que el programa no está\r\n"
            + "diseñado para soportar esos caracteres (sí las minúsculas tildadas).\r\n"
            + "\r\n"
            + "Al finalizar el tiempo o al haber respondido todas las preguntas, el\r\n"
            + "juego concluye y se pasa a la ventana de resultados, la cual muestra\r\n"
            + "todas las letras y, al hacerles click, se presenta en pantalla la\r\n"
            + "condición, pregunta, respuesta dada por el usuario, la respuesta\r\n"
            + "correcta y el estado (correcta, incorrecta, pasada o no respondida).\r\n"
            + "Además, se hace visible el puntaje final y las opciones para salir o\r\n"
            + "jugar nuevamente.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.1. La interfaz gráfica de entrada (primera pantalla)\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "La interfaz está dividida en tres secciones principales, que\r\n"
            + "explicamos más adelante en sus propios apartados:\r\n"
            + "\r\n"
            + "> Juego personalizado sin categoría (Custom category-less game)\r\n"
            + "> Juego rápido (Quick game)\r\n"
            + "> Configurar categorías (Customize categories)\r\n"
            + "\r\n"
            + "Asimismo, existen cuatro sub-secciones:\r\n"
            + "\r\n"
            + "> La cabecera (primer recuadro, arriba de la sección Custom\r\n"
            + "category-less game, que instruye dónde hacer click para obtener\r\n"
            + "instrucciones dentro del juego acerca de qué hacer y cómo hacerlo.\r\n"
            + "Además, es aquí donde se muestran los mensajes de error).\r\n"
            + "\r\n"
            + "> El tiempo total de juego (Total game time for any game you wish\r\n"
            + "to play), el cual es literalmente el tiempo completo de juego\r\n"
            + "(para las 10 preguntas en total, no individualmente). Ya sea si\r\n"
            + "se desea escoger un juego rápido o uno personalizado, se debe\r\n"
            + "seleccionar el tiempo que durará el contador para llegar a cero\r\n"
            + "y que el juego finalice.\r\n"
            + "\r\n"
            + "> Las categorías para juego rápido o para configurar (Categories to\r\n"
            + "play a quick game or to customize). Es la lista de categorías para\r\n"
            + "escoger tanto para comenzar un juego rápido como para agregar un set\r\n"
            + "de Letra, condición, pregunta y respuesta a un archivo de texto\r\n"
            + "de una categoría (que explicamos en el apartado de \"Juego\r\n"
            + "personalizado sin categoría\", más adelante).\r\n"
            + "\r\n"
            + "> El pie de la interfaz gráfica, que cuenta con las etiquetas de\r\n"
            + "::Help:: y ::Ayuda:: que al hacerles click crean este archivo que\r\n"
            + "está leyendo en este momento (con versión en inglés y en español),\r\n"
            + "como así también con los datos de contacto de su servidor, el autor\r\n"
            + "del programa.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2. Funciones principales\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "Como mencionado en el apartado anterior, existen tres secciones\r\n"
            + "que alojan las funcionalidades principales del programa.\r\n"
            + "\r\n"
            + "> Juego personalizado sin categoría (Custom category-less game)\r\n"
            + "> Juego rápido (Quick game)\r\n"
            + "> Configurar categorías (Customize categories)\r\n"
            + "\r\n"
            + "Las primeras dos son para jugar, y la última para configurar\r\n"
            + "aspectos del juego.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.1 Juego personalizado sin categoría\r\n"
            + "      (Custom category-less game)\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "En esta sección se puede crear un juego de 10 preguntas y\r\n"
            + "respuestas sin estar atados a alguna categoría en particular, y de\r\n"
            + "un solo uso (es decir, que una vez que finalice el juego, dichas\r\n"
            + "preguntas cargadas se pierden).\r\n"
            + "\r\n"
            + "Para agregar un set de Letra, pregunta, respuesta y condición, hay\r\n"
            + "que tipear la pregunta en el casillero que muestra \"Question\" en\r\n"
            + "gris, luego escoger la condición de la respuesta (si comienza con,\r\n"
            + "contiene o termina con -begins with, contains, ends with-), y\r\n"
            + "después, la letra que cumple con esa condición en la respuesta, en\r\n"
            + "el casillero que muestra la letra \"A\". Por último, se tipea la\r\n"
            + "respuesta en el casillero que muestra \"Answer\" en gris.\r\n"
            + "\r\n"
            + "Al clickear en el botón de \"Agregar set de pregunta y respuesta\"\r\n"
            + "(Add set of Q and A), todo lo que tipeamos y esocogimos queda\r\n"
            + "guardado en memoria, y en la fila azul marino debajo de los dos\r\n"
            + "botones se mostrará la letra que acabamos de agregar.\r\n"
            + "\r\n"
            + "Si deseamos eliminar la última letra agregada, clickeamos en \"Back\r\n"
            + "to previous set\", que al mismo tiempo devolverá en pantalla todo lo\r\n"
            + "que tipeamos y escogimos en dicho set asociado a la última letra\r\n"
            + "que agregamos en lista y que estamos tratando de eliminar.\r\n"
            + "\r\n"
            + "Antes de agregar un total de 10 sets completos, debemos escoger el\r\n"
            + "tiempo máximo que durará el juego en la sección de \"Total game time\r\n"
            + "for any category you wish to play\". Ver dicha sección en el\r\n"
            + "apartado anterior para más información.\r\n"
            + "\r\n"
            + "Una vez que agreguemos 10 sets con letras distintas, y hayamos\r\n"
            + "determinado el tiempo total de juego, el programa preguntará si\r\n"
            + "deseamos comenzar el juego. Si escogemos que no, el programa\r\n"
            + "eliminará el último set que agregamos y devolverá en pantalla lo\r\n"
            + "que hayamos tipeado y seleccionado en el último set. De esta forma,\r\n"
            + "al querer agregarlo nuevamente, nos volverá a preguntar si deseamos\r\n"
            + "iniciar.\r\n"
            + "\r\n"
            + "Al responder con OK, comienza el juego. El contador de tiempo\r\n"
            + "límite comienza a correr inmediatamente, así que a prepararse!\r\n"
            + "\r\n"
            + "Reglas para agregar sets de Letras, condiciones, preguntas y\r\n"
            + "respuestas:\r\n"
            + "\r\n"
            + "> No se puede agregar campos vacíos, ni tipear solo espacios.\r\n"
            + "> No se puede tipear \"Question\" o \"Pregunta\" en el campo para las\r\n"
            + "preguntas; o \"Respuesta\" o \"Answer\" en los campos para las\r\n"
            + "respuestas.\r\n"
            + "> No se pueden repetir letras utilizadas anteriormente.\r\n"
            + "> No se pueden repetir las combinaciones de pregunta y condición,\r\n"
            + "respuesta y condición, o pregunta y respuesta.\r\n"
            + "> Las preguntas tienen un límite de 100 caracteres, y las respuestas,\r\n"
            + "20.\r\n"
            + "> Si se escoge la condición de \"Contiene\" (contains), la respuesta\r\n"
            + "no puede iniciar ni finalizar con la letra en cuestión.\r\n"
            + "> Las respuestas deben respetar las letras y condiciones escogidas.\r\n"
            + "> Las respuestas solo aceptan caracteres de la A a la Z en mayúsculas\r\n"
            + "o minúsculas, números y espacios (pero no admiten mayúsculas con\r\n"
            + "tilde).\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.2 Juego rápido (Quick game)\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "Al iniciar el juego o al crear/recargar categorías, la aplicación\r\n"
            + "crea una carpeta llamada \"Categories__\" en la misma dirección\r\n"
            + "donde se haya instalado el programa. Dentro de la misma es que se\r\n"
            + "guardan todos los archivos de texto que almacenan los sets de\r\n"
            + "letra, condición, pregunta y respuesta. Si un archivo de categoría\r\n"
            + "tiene diez o más sets (con al menos diez letras sin repetir),\r\n"
            + "entonces esa categoría es elegible para un juego rápido.\r\n"
            + "\r\n"
            + "Para poder comenzar un juego rápido, simplemente hay que escoger\r\n"
            + "uno de esos archivos de categoría en el menú desplegable en la\r\n"
            + "sección de \"Categories to play a quick game or to customize\",\r\n"
            + "seleccionar el tiempo total para el juego en la sección de \"Total\r\n"
            + "game time for any game you wish to play\", y clickear en el botón\r\n"
            + "que marca \"Play on the selected category\".\r\n"
            + "\r\n"
            + "Si el archivo de categoría es uno válido, el juego comenzará\r\n"
            + "inmediatamente. Si no lo es, un error se mostrará en pantalla\r\n"
            + "indicando qué es lo que falló y cómo solucionarlo.\r\n"
            + "\r\n"
            + "El menú de categorías comienza vacío por defecto, para llenarlo\r\n"
            + "deberemos generar categorías por defecto (ver párrafo siguiente),\r\n"
            + "crear una archivo propio en la sección de \"Configurar categorías\"\r\n"
            + "(explicado en el siguiente apartado), o agregar manualmente\r\n"
            + "dentro de la carpeta autogenerada \"Categories__\" un archivo de\r\n"
            + "texto plano (.txt) válido con al menos 10 sets con letras\r\n"
            + "distintas.\r\n"
            + "\r\n"
            + "El programa cuenta con la posibilidad de generar categorías por\r\n"
            + "defecto, incluídas dentro del código. Para hacerlo, solo debemos\r\n"
            + "hacer click en el botón que muestra \"Generate/reload default\r\n"
            + "categories\". Al hacerlo, las categorías por defecto aparecerán\r\n"
            + "automáticamente en el menú desplegable, donde deben seleccionarse.\r\n"
            + "\r\n"
            + "Las preguntas de las categorías por defecto fueron extraídas de\r\n"
            + "LaffGaff (http://laffgaff.com/free-trivia-questions-and-answers/),\r\n"
            + "yo solo las edité y traduje, y agregue algunas preguntas adicionales\r\n"
            + "para completar las categorías. Así que todo el crédito para ellos.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "1.2.2 Configurar categorías (Customize categories)\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "> Agregar un set a una categoría (Add to category)\r\n"
            + "\r\n"
            + "En línea con lo escrito en el primer párrafo del apartado anterior,\r\n"
            + "el programa cuenta con la posibilidad de agregar un set de letra,\r\n"
            + "condición, pregunta y respuesta desde el juego directamente a un\r\n"
            + "archivo de texto dentro de la carpeta \"Categories__\".\r\n"
            + "\r\n"
            + "Para ello, debemos tipear una pregunta y una respuesta, y escoger\r\n"
            + "una condición y una letra en la sección de \"Juego personalizado sin\r\n"
            + "categoría\" (Custom category-less game), exactamente igual que si\r\n"
            + "fuesemos a crear un set para un juego de un solo uso. Pero, en vez\r\n"
            + "de clickear en el botón de \"Add set of Q and A\", seleccionamos de\r\n"
            + "la lista \"Categories to play a quick game or to customize\" una\r\n"
            + "categoría en la cual querramos agregar la pregunta, y clickeamos\r\n"
            + "el botón de \"Add to category\".\r\n"
            + "\r\n"
            + "Si no existieron inconvenientes, el set que tipeamos será agregado\r\n"
            + "permanentemente al archivo en cuestión (si vamos a la carpeta\r\n"
            + "\"Categories__\" y abrimos el archivo donde agregamos el set, lo\r\n"
            + "hallaremos en la última línea).\r\n"
            + "\r\n"
            + "Vale recordar que las respuestas solo aceptan caracteres de la A a\r\n"
            + "la Z en mayúsculaso minúsculas, números y espacios (pero no admiten\r\n"
            + "mayúsculas con tilde).\r\n"
            + "\r\n"
            + "> Create category\r\n"
            + "\r\n"
            + "Con este botón podremos crear un archivo de texto (.txt) para una\r\n"
            + "categoría, que se guardará dentro de la carpeta \"Categories__\".\r\n"
            + "\r\n"
            + "El nombre debe contener de 3 a 25 caracteres (letras, números y\r\n"
            + "espacios), y no puede llamarse \"README\" o \"LEEME\", ya que entraría\r\n"
            + "en conflicto con el nombre de los archivos de ayuda.\r\n"
            + "\r\n"
            + "Si la creación fue exitosa, dicho archivo se mostrará en el menú\r\n"
            + "de selección de categorías.\r\n"
            + "\r\n"
            + "> Delete a category\r\n"
            + "\r\n"
            + "Si seleccionamos una categoría del menú ubicado en la sección de\r\n"
            + "\"Categories to play a quick game or to customize\" y clickeamos en\r\n"
            + "el botón de \"Delete category\", podremos borrar dicho archivo de\r\n"
            + "texto de la categoría apuntada.\r\n"
            + "\r\n"
            + "*ADVERTENCIA*\r\n"
            + "Esta acción es permanente. Si borramos un archivo, no se envía, por\r\n"
            + "ejemplo, a la papelera de reciclaje si estamos utilizando Windows.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "2. Acerca del programa\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "Este juego fue creado en un primer intento de programación en Java\r\n"
            + "y utilizando conceptos de Programación Orientada a Objetos.\r\n"
            + "\r\n"
            + "La idea del mismo no va mucho más allá que cualquier otro juego de\r\n"
            + "preguntas y respuestas, pero se consideró ideal para integrar todos\r\n"
            + "los conceptos básicos y esenciales para la POO, como así también\r\n"
            + "para interactuar con el sistema operativo y con el usuario,\r\n"
            + "controlar el flujo natural del programa manipulando excepciones,\r\n"
            + "utilizar métodos estáticos donde sea necesario, y demás.\r\n"
            + "\r\n"
            + "Se utilizaron ocho clases y un enumerado en toda la aplicación.\r\n"
            + "\r\n"
            + "> Clases\r\n"
            + "\r\n"
            + "1. La interfaz gráfica de entrada (clase DougnutMain), responsable de\r\n"
            + "la carga manual de las preguntas y respuestas, la selección del tiempo\r\n"
            + "de juego, la selección y cofiguración de categorías y el juego rápido.\r\n"
            + "El código de esta interfaz fue escrito manualmente, y es por eso que\r\n"
            + "se ve distinta a los demás formularios que constituyen la aplicación.\r\n"
            + "Es decir, solo se utilizó el editor de texto del IDE de NetBeans, sin\r\n"
            + "recurrir a JForms. La decisión de esto se fundó en comprender en\r\n"
            + "primera persona cómo se diseña un formulario, se agregan elementos,\r\n"
            + "se configuran eventos, se ordenan variables y métodos, se instancian\r\n"
            + "objetos y demás características, todo con la expresión más simple que\r\n"
            + "ofrece en lenguaje, sin la ayuda de frameworks o la asistencia de\r\n"
            + "herramientas del IDE para facilitar el procedimiento.\r\n"
            + "\r\n"
            + "2. La interfaz del juego en sí (clase DoughnutGame), que extrae la\r\n"
            + "información recopilada de la interfaz anterior, se comunica con la\r\n"
            + "clase del contador y con la clase finalItem, explicadas adelante. Esta\r\n"
            + "clase es la responsable de mostrar las preguntas en pantalla,\r\n"
            + "condiciones y letras, leer lo que el usuario responde y actuar en\r\n"
            + "consecuencia con las respuestas correctas, incorrectas y pasadas.\r\n"
            + "\r\n"
            + "3. La clase CountDown, que se establece como hilo paralelo al flujo\r\n"
            + "principal del programa, que mantiene un conteo regresivo de tiempo en\r\n"
            + "segundos mientras se desarrolla el juego. Objetos de esta clase son\r\n"
            + "capaces de finalizar el juego si el contador llega a cero, o detenerse\r\n"
            + "si la interfaz del juego se lo ordena (por no haber más preguntas que\r\n"
            + "responder). La clase CountDown es la que ordena la inicialización de\r\n"
            + "un objeto de la clase GameOver.\r\n"
            + "\r\n"
            + "4. La clase GameOver, que despliega la interfaz gráfica de resultados,\r\n"
            + "donde el usuario puede corroborar las preguntas, respuestas correctas\r\n"
            + "y sus respuestas dadas, como así también el puntaje final y la\r\n"
            + "posibilidad de salir del sistema o jugar otra vez. Si decide jugar\r\n"
            + "nuevamente, se instancia e inicializa un objeto de la primer interfaz\r\n"
            + "gráfica comentada, comenzando el ciclo nuevamente.\r\n"
            + "\r\n"
            + "5. La clase Item que permite contruir objetos capaces de almacentar\r\n"
            + "la letra a utilizar en cada pregunta, la condición de la respuesta,\r\n"
            + "la pregunta y la respuesta (que abreviaremos con las siglas LCPR de\r\n"
            + "ahora en más). La clase principal instancia e inicializa objetos\r\n"
            + "de clase Item cada vez que el usuario introduce manualmente un set\r\n"
            + "de LCPR para crear un juego manual sin categoría, o cuando se escoje\r\n"
            + "la opción de juego rápido (en este caso, para almacenar diez sets de\r\n"
            + "LCPR extrayéndolos del archivo de categoría escogido.\r\n"
            + "\r\n"
            + "6. La clase finalItem, que hereda de Item y agrega dos atributos\r\n"
            + "capaces de almacenar la respuesta dada por el usuario en tiempo de\r\n"
            + "juego y el estado de esa respuesta (correcta, incorrecta, pasada o\r\n"
            + "sin responder). Objetos de esta clase replican los sets de LCPR en la\r\n"
            + "lista que almacena los diez objetos Item, y modifican sus propios\r\n"
            + "atributos a medida que el usuario responde en tiempo real. Estos\r\n"
            + "objetos son utilizados por la clase GameOver para completar los\r\n"
            + "datos en su interfaz gráfica.\r\n"
            + "\r\n"
            + "7. La clase Validator, que contiene todos los validadores utilizados\r\n"
            + "en todas las clases que requieran certificar que la información\r\n"
            + "introducida por el usuario es correcta; que los archivos pueden\r\n"
            + "generarse, modificarse o eliminarse; que no existan otros conflictos\r\n"
            + "ni excepciones y demás instrucciones que deban corroborarse para\r\n"
            + "poder proceder. Objetos de esta clase interactúan con métodos de las\r\n"
            + "demás clases para validar información y para mostrar al usuario\r\n"
            + "mensajes que ofrezcan instrucciones sobre cómo proceder. En especial,\r\n"
            + "estos objetos interactúan con los métodos de la clase Util para\r\n"
            + "manipular el formato de los componentes de las interfaces gráficas\r\n"
            + "para, por ejemplo, establecer bordes de error o cambiar el formato de\r\n"
            + "la letra; y con la clase de interfaz gráfica de entrada del programa,\r\n"
            + "para mostrar en la etiqueta de validación la información errónea que\r\n"
            + "pudo haber introducido el usuario.\r\n"
            + "\r\n"
            + "8. La clase Util, diseñada para contener todos los métodos estáticos\r\n"
            + "reutilizables a lo largo del programa. Dado que no se instancian\r\n"
            + "objetos en esta clase, resultó ideal para guardar variables estáticas\r\n"
            + "y finales que no consuman memoria cada vez que se inicializa un\r\n"
            + "objeto. La cantidad y funcionalidad de los métodos que contiene esta\r\n"
            + "clase es muy extensa, por lo que es mejor revisar el código (que se\r\n"
            + "encuentra comentado en su totalidad) si se desea ahondar en la misma.\r\n"
            + "\r\n"
            + "> Enumerado\r\n"
            + "\r\n"
            + "9. El único enumerador de la aplicación fue diseñado para contener\r\n"
            + "los valores inmutables de las letras del abecedario inglés (A-Z),\r\n"
            + "que son utilizadas para completar la lista desplegable de letras a\r\n"
            + "utilizar al crear un juego manual, o para comparar con las letras\r\n"
            + "dentro de los archivos de texto de las clases y así poder\r\n"
            + "corroborar que estas sean caracteres correctos a modo de validación.\r\n"
            + "\r\n"
            + "\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "3. Acerca del autor\r\n"
            + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\r\n"
            + "\r\n"
            + "Mi nombre es Renzo Nahuel Murina Cadierno, alias \"Max\".\r\n"
            + "Soy Licenciado en Administración y Magister en Dirección de\r\n"
            + "Empresas (M.B.A). Fui investigador académico durante dos años y\r\n"
            + "trabajo en Tribunales Provinciales de Rosario en la actualidad.\r\n"
            + "\r\n"
            + "Contando desde este mes (Julio/2019), hace seis meses que descubrí\r\n"
            + "mi verdadera pasión, que es programar. Realicé y realizo bastantes\r\n"
            + "cursos en la rama de Java, Javascript y Python. Al día de la fecha,\r\n"
            + "diseñé algunas mini aplicaciones en Python que me ayudaron mucho a\r\n"
            + "automatizar tareas y a practicar el lenguaje, y estoy gestando un\r\n"
            + "servidor con NodeJS y MongoDB donde levantaré mi propia página\r\n"
            + "personal para información sobre mi progreso y como repositorio\r\n"
            + "libre y código abierto para quien desee descargar mis trabajos y\r\n"
            + "utilizarlos. En lo que respecta a Java, este programa es mi\r\n"
            + "primer intento en el lenguaje.\r\n"
            + "\r\n"
            + "Mi sueño es poder dedicarme a la programación freelancer, pero\r\n"
            + "para ello recién estoy comenzando a dar los primeros pasos. Más\r\n"
            + "vale tarde que nunca, y es por eso que los pasos son agigantados.\r\n"
            + "Tengo en vista rendir certificaciones internacionales para esos\r\n"
            + "tres lenguajes mencionados, y es para mí todo un placer informarme\r\n"
            + "sobre la automatización de tareas y el machine learning (Python),\r\n"
            + "la creación de aplicaciones a gran escala y replicables (Java), y\r\n"
            + "la gestión back-end de servidores y bases de datos no relacionales\r\n"
            + "(JS, NodeJS y MongoDB).\r\n"
            + "\r\n"
            + "Soy autodidacta, pero al mismo tiempo realizo cursos vinculados a\r\n"
            + "lo mencionado y utilizo con enorme placer cada segundo de ocio en\r\n"
            + "la programación, dado que como fue mencionado, es una pasión.\r\n"
            + "\r\n"
            + "De ustedes considerarlo, con mucho gusto estoy abierto a\r\n"
            + "propuestas laborales. Mi mail de contacto personal es:\r\n"
            + "\r\n"
            + "           -----------------------------------------------\r\n"
            + "                     nmcadierno@hotmail.com\r\n"
            + "           -----------------------------------------------\r\n"
            + "\r\n"
            + "Muchas gracias por leer, y más que nada por darme una oportunidad\r\n"
            + "de mostrarles este pequeño gran programa, por más que sea un\r\n"
            + "modesto y simple primer intento.\r\n";
    
}
