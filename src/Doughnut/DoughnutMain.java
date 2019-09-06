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
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 * This class is the one that creates the main GUI and the entry point of
 * the program.
 * <p>
 * > The JPanels are constructed and all JComponents declared, initialized
 * formatted and added into them. <br>
 * > All main configurations and event listeners are established. <br>
 * > Every functionality designed for the GUI is handled here, with the
 * proper support of the Util and Validator class. <br>
 * > The main game's itemList ArrayList and transferCategory variable that
 * are loaded from DoughnutGame's instance are handled here.
 * @author 
 * Renzo Nahuel Murina Cadierno (a.k.a) "Max"
 * mail: nmcadierno@hotmail.com
 */
public class DoughnutMain extends JFrame implements ActionListener, FocusListener {
 
    public static DoughnutGame rGUI;
    public static DoughnutMain rMain;
    private static List<Item> itemList;
    private static int gameTime;
    private float charsLeft;
    private static String transferCategory;
    private String letterText = "";
    private JPanel mainPanel;
    private JPanel rowOne;
    private JPanel rowTwo;
    private JPanel rowThree;
    private JPanel rowFour;
    private JPanel rowFive;
    private JPanel rowSix;
    private JPanel rowSeven;
    private JPanel rowEight;
    private JPanel rowNine;
    private JPanel rowTen;
    private JPanel rowEleven;
    private JPanel rowTwelve;
    private JPanel rowThirteen;
    private JPanel rowFourteen;
    private JPanel rowFifteen;
    private JPanel rowSixteen;
    private JPanel rowSeventeen;
    private JPanel rowEighteen;
    private JPanel rowNineteen;
    private JLabel lblValidation;
    private JLabel lblAuthor;
    private JLabel lblAnswerCondition;
    private JLabel lblCharLimit;
    private JLabel lblCustomGame;
    private JLabel lblCustomGameDescription;
    private JLabel lblFiller1;
    private JLabel lblFiller2;
    private JLabel lblAddedLetters;
    private JLabel lblLettersInList;
    private JLabel lblGameTime;
    private JLabel lblSelectGameTime;
    private JLabel lblPresetGameOrCustomizeCategories;
    private JLabel lblCategory;
    private JLabel lblQuickGame;
    private JLabel lblQuickGameDescription;
    private JLabel lblCustomizeCategoriesDescription;
    private JLabel lblCustomizeCategoriesDescription2;
    private JLabel lblCustomizeCategories;
    private JLabel lblHelp;
    private JLabel lblAyuda;
    private JLabel lblFillerBottom2;
    private JLabel lblFillerBottom3;
    private JLabel lblFillerBottom4;
    private JTextField txtAddQuestion;
    private JTextField txtAddAnswer;
    private JButton btnAddItemToCategory;
    private JButton btnCreateCategory;
    private JButton btnDeleteCategory;
    private JButton btnRefreshCategories;
    private JButton btnGenerateDefaultCategories;
    private static JButton btnAddItem;
    private static JButton btnPreviousItem;
    private static JButton btnPlayQuickGame;
    private JRadioButton radBeginsWith;
    private JRadioButton radContains;
    private JRadioButton radEndsWith;
    private ButtonGroup letterCondition;
    private static JComboBox<EnumLetters> cmbAddLetter;
    private static JComboBox<String> cmbGameTime;
    private static JComboBox cmbCategory;
  

    /**
     * The constructor is the one responsible for the frame's creation and
     * configuration, as well as the one that sets its event listeners and
     * initializes the itemList arrayList, which will carry all Item objects
     * created by user inputs or brought from the category text files.
     */
    public DoughnutMain() {
        
        /* if we initialize a DoughnutMain object from a GameOver instance
           due to clicking on the "Play Again" button, then we dispose
           the previous GameOver instance and set it to null. If we
           are just starting the program up, then a NullPointerException
           will be raised (as there are no GameOver instances yet, so
           we catch it to do nothing. */
        try {
         
           Util.closeGameOverFrame();
           
        } catch (NullPointerException e) { }
        
        // we draw the GUI.
        createFrame();
        
        // set the default configurations.
        setMainConfigs();
        
        /* we create a list to store every set of Letter, condition, question
           and answers that are to be manually inputted or retrieved from the
           text files */
        itemList = new ArrayList();
        
        // and set each event listener to their corresponding components.
        setEventListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
    
    /**
     * The main JPanel, all of its subpanels and every JComponent inside
     * them are initialized, ordered and formatted here.
     * <p>
     * This method creates a main panel and 17 subpanels which are inserted
     * inside of it. Those subpanels will be treated as "rows" of the main
     * JPanel, and each of those rows will contain their corresponding
     * JComponents, which are initialized and formatted here.
     */
    private void createFrame() {
        
        //////////////////////////////////////////
        // Constructing the window's main frame //
        //////////////////////////////////////////
     
        /* we set the frame's title, fix its size, nullify its 
           default close operation to add a custom one later, 
           and we center it on the screen. */
        setTitle(": : Doughnut : :");
        setSize(700,695);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        /* we add the main panel for the JFrame. We set it to 17 rows and
           1 column. Each row will be a new JPanel divided in columns as 
           required, and will later be inserted inside this main panel. */ 
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(19,1));
        
        //row 1
        lblValidation = new JLabel(
                                   ": : Click on the headers on each "
                                 + "section for instructions : :  ");
        lblValidation.setName("lblValidation");
        rowOne = new JPanel();
        rowOne = new JPanel(new GridLayout(1, 1));
        rowOne.add(lblValidation);
        
        //row 2
        lblCustomGame = new JLabel("Custom category-less game");
        lblCustomGame.setName("lblCustomGame");
        rowTwo = new JPanel(new GridLayout(1, 1));
        rowTwo.add(lblCustomGame);
        
        //row 3
        lblCustomGameDescription = new JLabel(
                "Add 10 sets of questions and answers "
              + "to begin a one-time-only game");
        rowThree = new JPanel(new GridLayout(1, 1));
        rowThree.add(lblCustomGameDescription);
   
        //row 4
        txtAddQuestion = new JTextField("Question");
        txtAddQuestion.setName("txtAddQuestion");
        rowFour = new JPanel(new GridLayout(1, 1));
        rowFour.add(txtAddQuestion);
        
        //row 5
        lblAnswerCondition = new JLabel("The answer");
        radBeginsWith = new JRadioButton("Begins with");
        radContains = new JRadioButton("Contains");
        radEndsWith = new JRadioButton("Ends with");
        letterCondition = new ButtonGroup();
        letterCondition.add(radBeginsWith);
        letterCondition.add(radContains);
        letterCondition.add(radEndsWith);
        cmbAddLetter = new JComboBox();
        rowFive = new JPanel(new GridLayout(1, 5));
        rowFive.add(lblAnswerCondition);
        rowFive.add(radBeginsWith);
        rowFive.add(radContains);
        rowFive.add(radEndsWith);
        rowFive.add(cmbAddLetter);
        
        //row 6
        txtAddAnswer = new JTextField("Answer");
        txtAddAnswer.setName("txtAddAnswer");
        lblCharLimit = new JLabel();
        lblCharLimit.setName("lblCharLimit");
        rowSix = new JPanel(new GridLayout(1, 2));
        rowSix.add(txtAddAnswer);
        rowSix.add(lblCharLimit);

        btnAddItem = new JButton("Add set of Q and A");
        btnPreviousItem = new JButton("Back to previous set");
        rowSeven = new JPanel(new GridLayout(1, 2));
        rowSeven.add(btnAddItem);
        rowSeven.add(btnPreviousItem);
        
        //row 8
        lblFiller1 = new JLabel();
        lblFiller2 = new JLabel();
        lblAddedLetters = new JLabel();
        lblLettersInList = new JLabel();
        rowEight = new JPanel(new GridLayout(1, 4));
        rowEight.add(lblFiller1);
        rowEight.add(lblFiller2);
        rowEight.add(lblAddedLetters);
        rowEight.add(lblLettersInList);
        
        //row 9
        lblGameTime = new JLabel(
                    "Total game time for any game you wish to play");
        lblGameTime.setName("lblGameTime");
        rowNine = new JPanel(new GridLayout(1, 1));
        rowNine.add(lblGameTime);
        
        //row 10
        lblSelectGameTime = new JLabel("Select full game time:");
        cmbGameTime = new JComboBox();
        rowTen = new JPanel(new GridLayout(1, 2));
        rowTen.add(lblSelectGameTime);
        rowTen.add(cmbGameTime);
        
        //row 11
        lblPresetGameOrCustomizeCategories = new JLabel(
                    "Categories to play a quick game or to customize");
        lblPresetGameOrCustomizeCategories.setName(
                    "lblPresetGameOrCustomizeCategories");
        rowEleven = new JPanel(new GridLayout(1, 1));
        rowEleven.add(lblPresetGameOrCustomizeCategories);
        
        //row 12
        lblCategory = new JLabel("Select a category:");
        cmbCategory = new JComboBox();
        rowTwelve = new JPanel(new GridLayout(1, 2));
        rowTwelve.add(lblCategory);
        rowTwelve.add(cmbCategory);
        
        //row 13
        lblQuickGame = new JLabel("Quick game");
        lblQuickGame.setName("lblQuickGame");
        rowThirteen = new JPanel(new GridLayout(1, 1));
        rowThirteen.add(lblQuickGame);
        
        //row 14
        lblQuickGameDescription = new JLabel(
                "Select a category above to play a game on it");
        rowFourteen = new JPanel(new GridLayout(1, 1));
        rowFourteen.add(lblQuickGameDescription);
        
        //row 15
        btnPlayQuickGame = new JButton("Play on the selected category");
        //btnPlayPresetGame.addActionListener(this);
        btnGenerateDefaultCategories = new JButton(
                "Generate/reload default categories");
        rowFifteen = new JPanel(new GridLayout(1, 2));
        rowFifteen.add(btnPlayQuickGame);
        rowFifteen.add(btnGenerateDefaultCategories);
        
        //row 16
        lblCustomizeCategories = new JLabel("Customize categories");
        lblCustomizeCategories.setName("lblCustomizeCategories");
        rowSixteen = new JPanel(new GridLayout(1, 1));
        rowSixteen.add(lblCustomizeCategories);
        
        //row 17
        lblCustomizeCategoriesDescription = new JLabel(
                    "Type an item in the Custom Game section and add "
                  + "it to a category in the list above,");
        lblCustomizeCategoriesDescription2 = new JLabel(
                    "create a custom category to add to the "
                  + "list or delete one from it");
        rowSeventeen = new JPanel(new GridLayout(2, 1));
        rowSeventeen.add(lblCustomizeCategoriesDescription);
        rowSeventeen.add(lblCustomizeCategoriesDescription2);
        
        //row 18
        btnAddItemToCategory = new JButton("Add to category");
        btnCreateCategory = new JButton("Create category");
        btnDeleteCategory = new JButton("Delete category");
        btnRefreshCategories = new JButton("Refresh categories");
        rowEighteen = new JPanel(new GridLayout(1, 3));
        rowEighteen.add(btnAddItemToCategory);
        rowEighteen.add(btnCreateCategory);
        rowEighteen.add(btnDeleteCategory);
        rowEighteen.add(btnRefreshCategories);
        
        //row 19
        lblHelp = new JLabel(":: Help ::");
        lblHelp.setName("lblHelp");
        lblAyuda = new JLabel(":: Ayuda ::");
        lblAyuda.setName("lblAyuda");
        lblFillerBottom2 = new JLabel();
        lblFillerBottom3 = new JLabel();
        lblFillerBottom4 = new JLabel();
        lblAuthor = new JLabel("Designed by Max  ");
        lblAuthor.setName("lblAuthor");
        rowNineteen = new JPanel(new GridLayout(1, 6));
        rowNineteen.add(lblHelp);
        rowNineteen.add(lblAyuda);
        rowNineteen.add(lblFillerBottom2);
        rowNineteen.add(lblFillerBottom3);
        rowNineteen.add(lblFillerBottom4);
        rowNineteen.add(lblAuthor);
        
        //we add all sub-panels to the main panel.
        mainPanel.add(rowOne);          mainPanel.add(rowTwo); 
        mainPanel.add(rowThree);        mainPanel.add(rowFour); 
        mainPanel.add(rowFive);         mainPanel.add(rowSix);
        mainPanel.add(rowSeven);        mainPanel.add(rowEight); 
        mainPanel.add(rowNine);         mainPanel.add(rowTen); 
        mainPanel.add(rowEleven);       mainPanel.add(rowTwelve);
        mainPanel.add(rowThirteen);     mainPanel.add(rowFourteen); 
        mainPanel.add(rowFifteen);      mainPanel.add(rowSixteen);    
        mainPanel.add(rowSeventeen);    mainPanel.add(rowEighteen);
        mainPanel.add(rowNineteen);
        
        //lastly, we add the main panel to the window frame.
        add(mainPanel);
        
        
        ///////////////////////////////////////////////////////
        // Setting the default format of font, size, fgColor //
        //    and bgColor to all of its main JComponents     //
        ///////////////////////////////////////////////////////
        
        /* we create a vector which contains all JLabels we want to apply
           format to. */
        JLabel[] labelsToFormat = {
            lblValidation, // 0
            lblGameTime, 
            lblPresetGameOrCustomizeCategories, //bold - white - black  //2
            lblCustomGame, 
            lblQuickGame, 
            lblCustomizeCategories, //italics - white - lightblue  //5
            lblCustomGameDescription,
            lblAnswerCondition,
            lblCharLimit,
            lblFiller1,
            lblFiller2, //10
            lblAddedLetters, 
            lblLettersInList,
            lblQuickGameDescription, 
            lblCustomizeCategoriesDescription,
            lblCustomizeCategoriesDescription2, //italics - white - azure //15
            lblSelectGameTime, 
            lblCategory, //17
            lblFillerBottom2,
            lblFillerBottom3,
            lblFillerBottom4, //20
            lblHelp, 
            lblAyuda,
            lblAuthor,  //23
        };
        
        // we apply the format to each JLabel in the vector above.
        for (int i = 0; i < labelsToFormat.length; i++) {
                
            //lblAuthor
            if (i > 22) {
                
                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, Color.gray, Color.BLACK,
                        SwingConstants.RIGHT, labelsToFormat[i]);
                labelsToFormat[i].setBorder(
                                    new MatteBorder(3, 0, 0, 0, Color.white));

            /*lblHelp, lblAyuda, lblFillerBottom2, lblFillerBottom3
              lblFillerBottom4 */
            } else if (i > 17) {

                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 1, 16, Color.white, Color.BLACK,
                        SwingConstants.CENTER, labelsToFormat[i]);
                labelsToFormat[i].setBorder(
                                    new MatteBorder(3, 0, 0, 0, Color.white));
         
            /* lblCustomGameDescription, lblCharLimit, lblAnswerCondition, 
               lblFiller1, lblFiller2, lblAddedLetters, lblLettersInList, 
               lblQuickGameDescription, lblCustomizeCategoriesDescription, 
               lblCustomizeCategoriesDescription2, lblSelectGameTime, 
               lblCategory, */
            } else if (i > 5) {
                
                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, Color.white, new Color(0, 50, 100),
                        SwingConstants.CENTER, labelsToFormat[i]);
            
            // lblCustomGame, lblQuickGame, lblCustomizeCategories
            } else if (i > 2) {
                
                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 1, 16, Color.white, new Color(0, 128, 255),
                        SwingConstants.CENTER, labelsToFormat[i]);
                labelsToFormat[i].setBorder(
                                    new MatteBorder(3, 0, 0, 0, Color.white));
                
            //lblGameTime, lblPresetGameOrCustomizeCategories
            } else if (i > 0 ) { 
                
                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 1, 16, Color.white, new Color(0, 0, 51),
                        SwingConstants.CENTER, labelsToFormat[i]);
                labelsToFormat[i].setBorder(
                                    new MatteBorder(3, 0, 0, 0, Color.white));
            
            //lblValidation
            } else { 
                
                Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, new Color(192, 192, 192), Color.BLACK,
                        SwingConstants.RIGHT, labelsToFormat[i]);
            }
        }
        
        /* since txtAddQuestion, txtAddAnswer and the radio buttons inside
           letterCondition are not labels, we format them out of the previous 
           loop */
        Util.setFontStyleSizeColor( "Calibri", 2, 16, Color.gray, 
                                    txtAddQuestion, txtAddAnswer);
        Util.setFontStyleSizeColorBgColor(
                            "Calibri", 2, 16, Color.white, new Color(0, 50, 100), 
                            radBeginsWith, radContains, radEndsWith);                

        /* we set the default borders to each JComboBox to unify the look
           and feel for them */
        Util.setDefaultBorder(cmbAddLetter, cmbGameTime, cmbCategory);
        
        //lblCharLimit.setBorder(new MatteBorder(1, 0, 0, 0, Color.white));
        //txtAddAnswer.setBorder(new MatteBorder(1, 0, 0, 0, Color.white));
    }
    
    /**
     * The main configuration for the JComponents that require so are set
     * in this method.
     * <p>
     * > Each radio button is set with an Action command and added into a 
     * buttonGroup, <br>
     * > cmbAddLetter is filled with EnumLetters values, <br>
     * > cmbGameTime has its preset time values added into it, <br>
     * > the JLabels that contain a JOptionPane panel for the instructions
     * are set to display a hand cursor. <br>
     * > focus listeners are added, <br>
     * > the "Categories__" folder is created next to the .jar if it did
     * not exist already, and <br>
     * > all categories are loaded, if they already exist inside the
     * "Categories__" folder.
     */
    private void setMainConfigs() {
        
        /////////////////////////////////////////
        // Setting the default main configs to //
        //  the JComponents that require them. //
        /////////////////////////////////////////
        
        /*we add the Radio Buttons to the letterCondition buttongroup,
          set their action commands and select the first one as default. */ 
        letterCondition.add(radBeginsWith);
        letterCondition.add(radContains);
        letterCondition.add(radEndsWith);
        radBeginsWith.setActionCommand("Begins with");
        radContains.setActionCommand("Contains");
        radEndsWith.setActionCommand("Ends with");
        radBeginsWith.setSelected(true);
        
        /* we set all possible Letters from the EnumLetters enumerator Class
           inside cmbAddLetter JComboBox. */
        Arrays.asList(EnumLetters.values()).forEach(cmbAddLetter :: addItem);
        
        /* we add the game time options to cmbGameTime JComboBox. 
           20 values that increment by 15 secs each. That is, from 
           0 to 300 secs (5 mins) total. */
        cmbGameTime.addItem("Total game time");
        for (int i = 1; i < 21; i++) {
            cmbGameTime.addItem(String.valueOf(i*15) + " secs");
        }

        //we set a hand cursor and a tooltip to each required label.
        JLabel[] labelsToSetHandCursor = { lblHelp, lblAyuda, lblAuthor,
            lblCustomGame, lblGameTime, lblCustomizeCategories,
            lblQuickGame, lblPresetGameOrCustomizeCategories };
        
        for(JLabel label : labelsToSetHandCursor) {
            
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            switch (label.getName()) {
                
                case "lblHelp":
                    label.setToolTipText("Click for game instructions");
                    break;
                    
                case "lblAyuda":
                    label.setToolTipText("Click para las instrucciones en español");
                    break;
                 
                case "lblAuthor":
                    label.setToolTipText("Click for contact info");
                    break;
                    
                default:
                    label.setToolTipText("Click for further instructions");
                    break;
            }           
        }
        
        //we set focus listeners to each required JComponent
        txtAddAnswer.addFocusListener(this);
        txtAddQuestion.addFocusListener(this);
        cmbAddLetter.addFocusListener(this);
        cmbGameTime.addFocusListener(this);
        cmbCategory.addFocusListener(this);   
        
        Enumeration<AbstractButton> lc = letterCondition.getElements(); 
        while(lc.hasMoreElements()) lc.nextElement().addFocusListener(this);
        
        /*finally, we create the "Categories__" folder in where the
          category files will be saved, and we load all category files
          inside of it, if any. */
        Util.createCategoryFolder();
        
        try {
            //and refresh the categories inside cmbCategory
            loadCategories();
            
        } catch (IOException e) { }
    }
    
    /**
     * Reads all category .txt files inside the "Categories__" folder,
     * (ignoring "README" and "LEEME" ones) and adds them inside 
     * cmbCategory JCombobox, effectively displaying them in the GUI.
     */
    private void loadCategories() throws IOException {
        
        List<String> files = new ArrayList();
        List<String> filesToAdd = new ArrayList();
        
        //if there are values inside cmbCategory, we clear them first.
        if(cmbCategory.getItemAt(0) != null) {
            
            cmbCategory.removeAllItems();
        }
       
        /* we read all .txt files inside the "Categories__" folder, ignoring
           the ones named "README" or "LEEME" */
        try(Stream<Path> path = Files.walk(Paths.get(".\\Categories__\\"))) {
            
            files = path
                .map(a -> a.toString())
                .filter(b -> b.endsWith(".txt")
                         && !b.equalsIgnoreCase(".\\Categories__\\readme.txt")
                         && !b.equalsIgnoreCase(".\\Categories__\\leeme.txt"))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
            
            /* we remove the path string and the extension of each file 
               (which gets us their names only) and add them to cmbCategory */
            files.forEach(item -> {
           
                String relFile = item.replace(".txt", "");
                relFile = relFile.replace(".\\Categories__\\", "");
                
                if(!filesToAdd.contains(relFile))
                    filesToAdd.add(relFile);
            });
            
            filesToAdd.forEach(item -> {
                
                cmbCategory.addItem(item);
            });
        
        } catch (IOException e) { 
        
            JOptionPane.showMessageDialog(
                    this,
                    "Error while trying to load Categories files\n\n"
                  + "Please, make sure that the folder in where you\n"
                  + "are executing this application has Writing\n"
                  + "permissions and/or administrator privileges.\n\n"
                  + "Also, check that the \"Categories__\" folder \n"
                  + "is in the same folder as the executable app.\n"
                  + "After correcting both things, try closing the\n"
                  + "app and opening it again.\n\n"
                  + "If the error persists, contact the programmer.\n\n"
                  + Util.getErrorLocation(
                        "Doughnut", 
                        "loadCategories()", 
                        "Stream / Files / Paths"),
                  "Writing files error", 
                  JOptionPane.ERROR_MESSAGE);
            
            throw new IOException();
        }
    }
    
    /**
     * Creates (or overwrites) the default categories.
     * <p>
     * <b> Warning: </b> This is permanent! Anything written inside 
     * those category files will be overwritten with the default 
     * texts.
     * <p>
     * The user will be warned with a JOptionPane message, and if 
     * they accept, then the action will be performed and the category
     * list will be refreshed to show the default ones.
     */
    private void generateDefaultCategories() {
        
        //we ask if the user wants to create/reload the default categories.
        if(JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to create/reload default categories?\n\n"
                  + "Keep in mind that any additional data you might\n"
                  + "have inserted in their text files will be deleted.\n\n"
                  + "This action only affects the default category files\n"
                  + "and not the ones you customly add.\n",
                    "Create/reload default categories", 
                    JOptionPane.YES_NO_OPTION) != 0
          )
            return;
        
        /* we make sure the category folder is created if it was deleted
           at runtime. */
        Util.createCategoryFolder();
        
        //we create all default category files.
        Util.createCategoryTextFile("Animals (default)");
        Util.createCategoryTextFile("Animales (default_esp)");
        Util.createCategoryTextFile("Geography (default)");
        Util.createCategoryTextFile("Geografía (default_esp)");
        Util.createCategoryTextFile("History (default)");
        Util.createCategoryTextFile("Historia (default_esp)");
        
        JOptionPane.showMessageDialog(
                    this,
                    "Default categories created successfully!",
                    "Success!", 
                    JOptionPane.INFORMATION_MESSAGE);
        
        try {
            //and reload the category list to show them.
            loadCategories();
            
        } catch (IOException e) { }
    }
    
    /**
     * Creates a new Item object with the user selected Letter and condition, 
     * and user inputted Question and Answer, and adds it to itemList.
     * <p>
     * The method checks if itemList contains 10 Item objects. If so, it asks
     * the user if they want to begin the game. On "yes", it calls for
     * startNewGame() method. On "no", it returns.
     * <p>
     * If there are less than 10 Item objects in itemList and after the 
     * necessary validations, it adds the user inputted and selected values
     * in txtAddQuestion, txtAddAnswer, letterCondition and cmbAddLetter as
     * a new Item Object inside itemList. After doing to, the values and
     * format of all mentioned fields are reseted to their default ones.
     * <p>
     * If the added Item object is the 10th one, this method immediately
     * asks the user if they want to begin a game. If so, it calls for
     * startNewGame(). If they do not, then it triggers removeCurrentItem().
     */
    private void addItem() {
        
        // we reset all error borders.
        Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, 
                               cmbAddLetter, cmbGameTime);
        
        /* if 10 sets of Items are added already, we ask if the user wants
           to begin the game. If they agree, we call for startNewGame(),
           else, we return */
        if (itemList.size() >= 10) {
                    
            if (JOptionPane.showConfirmDialog(
                    this, 
                    "Begin game?",
                    "Start new game", 
                    JOptionPane.YES_NO_OPTION) == 0)
                startNewGame(); 

            return;  
        }
        
        /* we go through all necessary validatiors (you can check the javadoc
           to know what they do. Any invalid instance will cause this method
           to return */
        if (new Validator(lblValidation)
                .areDefaultOrEmptyValues(txtAddQuestion, txtAddAnswer)
            )
            return;
        
        if (new Validator(lblValidation)
                .isUnsupportedCharacter(txtAddQuestion, txtAddAnswer)
            )
            return;

        if ( !itemList.isEmpty()

          && new Validator(lblValidation)
                .isRepeatedQuestion(itemList, letterCondition, cmbAddLetter,
                                    txtAddQuestion, txtAddAnswer)
           ) 
            return;
        
        if(new Validator(lblValidation).maxCharsExceeded(txtAddQuestion)
        || new Validator(lblValidation).maxCharsExceeded(txtAddAnswer)
          )
            return;
        
        if ( !new Validator(lblValidation)
                .letterConditionIsMet(letterCondition, cmbAddLetter, txtAddAnswer)  
           )
            return;

        /* everything is correct, so we create a new Item object using the
           user selected letter and condition, and inputted question and 
           answer, and add that Item to itemList */
        itemList.add(new Item(
                cmbAddLetter.getSelectedItem().toString(), 
                letterCondition.getSelection().getActionCommand(),
                txtAddQuestion.getText().trim(), 
                txtAddAnswer.getText().trim()
        ));

        // we reset all fields to their default values and borders.
        cmbAddLetter.setSelectedIndex(0);
        radBeginsWith.setSelected(true);
        Util.setText("DEFAULT", txtAddQuestion, txtAddAnswer);
        Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbAddLetter);

        /* if itemList is empty, then lblLettersInList and letterText will
           show nothing */
        if (lblAddedLetters.getText().isEmpty())
            lblAddedLetters.setText("Added letters:");
        lblLettersInList.setText("");
        letterText += itemList.get(itemList.size()-1).getLetter()+" ";
        lblLettersInList.setText(letterText);

        /* if the last added item is the 10th one, then we ask if the user
           wants to begin the game. If they do, startNewGame() method is
           called. Else, removeCurrentItem() is called instead.
        */
        if (itemList.size() >= 10) {

            switch (JOptionPane.showConfirmDialog(
                    this, 
                    "Begin game?",
                    "Start new game", 
                    JOptionPane.YES_NO_OPTION)) {

                case 0:
                    startNewGame();
                    break;

                case 1:
                case 2:
                case -1:
                    removeCurrentItem();
                    break;
            }  
        }         
    }
    
    /**
     * Inserts the user inputs in txtAddQuestion, txtAddAnswer,
     * letterCondition and cmbAddLetter inside cmbCategory's
     * selected file, only if it certifies that that file is
     * a valid one, if values inputted by the users are written
     * in the correct format, if the answer provided matches
     * the letter condition and if the Q and A is not already
     * in the selected category file.
     */
    private void addItemToCategory() {
        
        // if there are no categories in the JComboBox, we return.
        if(cmbCategory.getItemCount() == 0) {
            
            JOptionPane.showMessageDialog(
                    this, 
                    "There are no categories to add the Q and A!\n\n"
                  + "Create a custom one or generate the\n"
                  + "default categories from the app using\n"
                  + "the respective buttons, or add a valid\n"
                  + "category file manually to the \"Categories__\"\n"
                  + "folder.",
                    "Adding items error", 
                    JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        String SelectedCategory = cmbCategory
                                            .getSelectedItem()
                                            .toString();
        
        /* we make sure the category folder is created if it was deleted
           at runtime. */
        Util.createCategoryFolder();
        
        /* we go through all necessary validatiors (you can check the javadoc
           to know what they do. Any invalid instance will cause this method
           to return */
        if(!new Validator()
                .isFile(
                    SelectedCategory, 
                    cmbCategory)
          )
            return; 
        
        if (new Validator(lblValidation)
                .areDefaultOrEmptyValues(txtAddQuestion, txtAddAnswer)
            )
            return;
        
        if (new Validator(lblValidation)
                .isUnsupportedCharacter(txtAddQuestion, txtAddAnswer)
            )
            return;
        
        if(new Validator(lblValidation).maxCharsExceeded(txtAddQuestion)
        || new Validator(lblValidation).maxCharsExceeded(txtAddAnswer)
          )
            return;
        
        if(!new Validator(lblValidation)
                .letterConditionIsMet(letterCondition, cmbAddLetter, txtAddAnswer)
          )
            return;
        
        if(new Validator()
                .areAlreadyInTxtFile(SelectedCategory, 
                                     letterCondition, cmbCategory, cmbAddLetter,
                                     txtAddQuestion, txtAddAnswer)
          )
            return;
        
        /* everything is fine, we initialize the corresponding category text
           file, append the user inputted Item to it using the
           appendQandAtoCategory() method and reload the categories with the
           loadCategories() function */
        File file = new File(".\\Categories__\\" + SelectedCategory + ".txt");

        Util.appendQandAtoCategory(
                file,
                cmbAddLetter.getSelectedItem().toString(),
                letterCondition.getSelection().getActionCommand(),
                txtAddQuestion.getText().trim(),
                txtAddAnswer.getText().trim());
        
        try {
            
            // finally, we reload the categories inside cmbCategory
            loadCategories();
            
        } catch (IOException e) { } 
    }
    
    /**
     * Creates a new Category text file with a name based on the 
     * user's input.
     * <p>
     * If the name includes anything but english letters, numbers
     * or spaces (min 3, max 25), it returns without creating
     * the file.
     */
    private void createCategory() {
        
        File file;
        String createdCategoryName;

        /* Closing the JOptionPane will throw a NullPointerException,
           so we catch it to return out of the method */
        try {
            
            /* we ask for the category name and store it inside
               createdCategoryName variable */
            createdCategoryName = JOptionPane.showInputDialog(
                                this, 
                                "Name the new category.\n\n"
                              + "Use only letters in the english alphabet,\n"
                              + "letters, numbers and spaces (min 3, max\n"
                              + "25 characters).\n\n",
                                "Category name",
                                JOptionPane.DEFAULT_OPTION).trim(); 
            
        } catch (NullPointerException e) {
           
            return;
        }
            
        /* Cancelling the JOptionPane will result in a null value for
           createdCategoryName. If so, we return out of the mothod too. */
        if(createdCategoryName == null) return;
        
        /* we make sure the category folder is created if it was deleted
           at runtime. */
        Util.createCategoryFolder();
        
        /* we validate that the category name follows the correct
           regex pattern, and it is not equal to "README" or "LEEME",
           since those are reserved names for the help files. */
        if(!new Validator().isValidCategoryName(createdCategoryName))
            return;
        
        /* if the name is valid but it contains extra spaces or additional
           invalid characters, we format it correctly and reassign a new,
           one, free of those characters and extra spaces. */
        createdCategoryName = 
                Validator.isValidCategoryPatternName(createdCategoryName);
        
        /* if the string returned by the method above is "wrongformat!",
           it means that the final validation failed, and we need to
           return out of the function. */
        if(createdCategoryName.equals("wrongformat!")) {
            
            Util.displayErrorWrongNamePattern();
            return;
        }

        /* at this point, we can assume that the name for the category
           file is a valid one, so we create it. */
        Util.createCategoryTextFile(createdCategoryName);
        
        try {
            
            /* and we refresh all categories inside cmbCategories, so that
               this new one we have created is shown. */
            loadCategories();
            
        } catch (IOException e) { return; }
     
        JOptionPane.showMessageDialog(
                DoughnutMain.rMain,
                "Category created successfully!",
                "Success!",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Deletes the selected category (cmbCategory's currently selected 
     * item).
     * <p>
     * This method returns if there are no categories to delete. It also
     * asks the user for confirmation before deleting, shows a message
     * informing that the action was successful and refreshes the category
     * list so that the deleted category is no longer shown on it.
     */
    private void deleteCategory() {
        
        // first of all, if there is nothing to delete, we return.
        if(cmbCategory.getItemCount() == 0) {
            
            JOptionPane.showMessageDialog(
                            this, 
                            "There are no categories to delete!\n\n"
                          + "Either create a custom one or generate\n"
                          + "the default categories from the app\n"
                          + "using the respective buttons, add a\n"
                          + "valid category file manually to the\n"
                          + " \"Categories__\" folder.",
                            "Deleting files error", 
                            JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /* we ask for confirmation on the deletion attempt. If it is
           not positive, then we return */
        if(JOptionPane.showConfirmDialog(
                            this, 
                            "Are you sure you want to delete\n\" "
                          + cmbCategory.getSelectedItem().toString()
                          + " \"\ncategory text file?\n\n"
                          + "This action is permanent.\n"
                          + "You will lose all content inside\n"
                          + "the file if you proceed.",
                            "Delete category?",
                            JOptionPane.YES_NO_OPTION) != 0)
            return;
        
        File file;
        
        try {
            
            // we try to read the selected file.
            file = new File( ".\\Categories__\\"
                           + cmbCategory.getSelectedItem().toString()
                           + ".txt");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, 
                            Util.getErrorModifyCategoryFiles(
                                "Doughnut", 
                                "deleteCategory()", 
                                "File"),
                            "Writing files error", 
                            JOptionPane.ERROR_MESSAGE);   
            return;
        }
        
        //if the reading was successful, we delete it.
        file.delete();

        try {
            /* and we refresh the categories in cmbCategories so that the
               deleted file is not shown there anymore */
               loadCategories();
            
        } catch (IOException e) { return; }

        JOptionPane.showMessageDialog(
                this, 
                "Successfully deleted!",
                "Success!",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Reads the "Categories__" folder and displays all category
     * file names found there inside cmbCategory JComboBox.
     */
    private void refreshCategories() {
        
        /* we make sure the category folder is created if it was deleted
           at runtime. */
        Util.createCategoryFolder();
        
        try {
            
            /* and we refresh all categories inside cmbCategories, so that
               this new one we have created is shown. */
            loadCategories();
            
        } catch (IOException e) { return; }
        
        JOptionPane.showMessageDialog(
                this, 
                "Categories reloaded!",
                "Success!",
                JOptionPane.INFORMATION_MESSAGE);
    }
   
    /**
     * Sets itemList to null so that Garbage Collector can dispose it.
     */
    public static void clearItemList() {
        
        itemList = null;
    }
    
    /**
     * Displays the amount of characters left to type vs the 
     * maximum allowed for the Question and Answer JTextFields
     * (txtAddQuestion and txtAddAnswer in DoughnutMain class).
     * <p>
     * The "remaining characters" text is shown in lblCharLimit
     * and its foreground color will be set to green if it is
     * greater than 50% of it maximum allowed, yellow if it is 
     * greater than 25% and less or equal to 50%, orange if it 
     * is less than or equal to 25% but not 0, and red if it is 
     * 0.
     * <p>
     * This method also prevents the user from typing more than
     * the maximum characters allowed.
     * @param txt 
     * txtAddQuestion or txtAddAnswer JTextFields.
     */
    public void displayCharLimit(JTextField txt) {
        
        if (txt.getName().equals("txtAddQuestion")) {
            
            charsLeft = 100f;
            
            // no more than 100 characters can be typed in txtAddQuestion.
            if ((charsLeft - txt.getText().length()) < 0) {
                
                txt.setText(txt.getText().substring(0,100));
            }
            
        } else {
            
            charsLeft = 20f;
            
            // no more than 20 characters can be typed in txtAddAnswer.
            if ((charsLeft - txt.getText().length()) < 0) {
                
                txt.setText(txt.getText().substring(0,20));
            }
        }

        /* if chain to set the foreground color according to the remaining
           characters available to type */
        if (( (charsLeft- txt.getText().length()) / charsLeft ) > 0.5 ) {
            
            lblCharLimit.setForeground(Color.green);
            
        } else if (( (charsLeft- txt.getText().length()) / charsLeft ) > 0.25 ) {
            
            lblCharLimit.setForeground(Color.yellow);
            
        } else if (( (charsLeft- txt.getText().length()) / charsLeft ) > 0 ) {
            
            lblCharLimit.setForeground(Color.orange);
            
        } else {
            
            lblCharLimit.setForeground(Color.red);
        }
        
        // finally, the label is displayed.
        lblCharLimit.setText( "Chars left: " 
                         + (int)(charsLeft - txt.getText().length())
                         );
    }
    
    /**
     * Returns the gameTime variable.
     * @return
     * gameTime variable.
     */
    public static int getTimer() {
        
        return gameTime;
    }
    
    /**
     * Checks if all of the questions, answers, letters and conditions are
     * loaded via user input, and if they aren't, it retrieves them randomly
     * from the assigned text file in Util.getItemsFromText() method.
     * @return 
     * A list of all necessary questions, answers, letters and 
     * conditions for DoughnutGame to load into it's memory.
     */
    public static List<Item> loadQandA() {

        /* if the user have already inputted 10 sets of Letter, condition,
           question and answer manually for an individual game, then we
           sort them out by Letter order and return the list that contains
           them */
        if (itemList.size() == 10) { 
            
            itemList = itemList
                    .stream()
                    .sorted(Comparator.comparing(Item :: getLetter))
                    .collect(Collectors.toList());
            
            return itemList;
        }
        
        /* at this point, we know that the user wants to play a quick game,
           so we need to clear itemList from any manually inputted Item
           object first */
        if(!itemList.isEmpty()) itemList.clear();   

        // we create a list to store all EnumLetter values, and store them.
        List<String> lettersToUse = new ArrayList();
        
        Arrays
                .asList(EnumLetters.values())
                .forEach(item -> lettersToUse.add(item.toString()));

        do {
            
            try {
                
                /* we add one set of Letter, condition, question and answer
                   (an Item object) to the itemList list. The set is taken 
                   from the text file stored in transferCategory as a String, 
                   and will only be selected if the Letter is available inside 
                   that file and is not repeated among the Item objects in
                   itemList. */
                itemList.add(Util.getItemsFromText(
                                    lettersToUse, transferCategory));

                /* once the Item is added, we remove the Letter in it from the
                   lettersToUse list, so that is it not repeated in the next
                   call. */
                if(lettersToUse.contains(
                        itemList.get(itemList.size()-1).getLetter())
                   )
                    lettersToUse.remove(
                            itemList.get(itemList.size()-1).getLetter());

            } catch (Exception e) {
                
                /*If the getItemsFromText() method above cannot resolve due to 
                  a false line that starts with a valid EnumLetter.value(), it 
                  will fail to split the array into a correct itemList object. 
                  Thus, the if clause inside the try block will throw a Null 
                  Pointer Exception, causing the DoughnutGame instance initiated by 
                  startNewGame() to shut down with the app alongside it. 
                  This is the last sanity check, most likely triggered by forced 
                  circumstances. */
                JOptionPane.showMessageDialog(
                        null, 
                        Util.getErrorModifyCategoryFiles(
                            "Doughnut", 
                            "loadQandA()", 
                            "BufferedReader / FileReader / File / String"),
                        "Sanity check crash", 
                        JOptionPane.ERROR_MESSAGE);

                System.exit(1);
            }

        } while (itemList.size() < 10);
       
        /* once all 10 Items are stored inside itemList, we sort them by
           their Letter order and return it. */
        itemList = itemList
                        .stream()
                        .sorted(Comparator.comparing(Item :: getLetter))
                        .collect(Collectors.toList());
       
        return itemList; 
    }
    
    /**
     * Stores the selected item on cmbCategory JComboBox as a string
     * inside the static transferCategory variable (which will be
     * called from a DoughnutGame's instance later), and launches a new
     * game by calling startNewGame() method.
     */
    private void playQuickGame() {
        
        //if there are no categories to select in cmbCategory, we return.
        if(cmbCategory.getItemCount() == 0) {
            
            JOptionPane.showMessageDialog(
                            this, 
                            "There are no categories available to\n"
                          + "play!\n\n"
                          + "Either create a custom one or generate\n"
                          + "the default categories from the app\n"
                          + "using the respective buttons, add a\n"
                          + "valid category file manually to the\n"
                          + "\"Categories__\" folder.",
                            "Quick game error", 
                            JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /* we store the selected category inside a static variable,
           so that we can pull it from a DoughnutGame instance later on,
           even after we have disposed the current DoughnutMain instance */
        transferCategory = cmbCategory.getSelectedItem().toString();
        
        //having done that, we call for the method that begins the game.
        startNewGame();
    }
    
    /**
     * Removes the last element in the itemList variable and displays all
     * components of the second-to-last element inside the corresponding 
     * JComponents.
     * <p>
     * This method removes the last letter added to letterText JLabel, or
     * does nothing if letterText is empty.
     * <p>
     * It also displays the second-to-last Question, Condition, Letter, 
     * and Answer inside itemList before removing the last element of it.
     * <p>
     * If itemList is empty, the default values for all JComponents will
     * be displayed.
     */
    private void removeCurrentItem() {
        
        try {
            
            //we get the previous Item object in the itemList list.
            Item previousItem = itemList.get(itemList.size()-1);

            /* we erase the last two characters from lblLettersInList
              which now displays all added Letters but the one removed. */
            letterText = letterText.substring(0, letterText.length()-2);

            lblLettersInList.setText(letterText);

            /* we retrieve and diplay the previously selected Letter in
            cmbAddLetter */
            cmbAddLetter.setSelectedItem(
                            EnumLetters.valueOf(
                                previousItem.getLetter()));

            /* we retrieve and diplay the previously selected condition in
            letterCondition */
            List <AbstractButton> radButtons = 
                            Collections.list(letterCondition.getElements());
           
            radButtons.forEach(item -> {
                
                if(item.getText().equals(previousItem.getCondition()))
                    
                    item.setSelected(true);
            });
            
            /* we retrieve and diplay the previously typed Question and 
               Answer in txtAddQuestion and txtAddAnswer and set the 
               proper format to them*/
            txtAddQuestion.setText(previousItem.getQuestion());
            txtAddAnswer.setText(previousItem.getAnswer());
            
            Util.setFontStyleSizeColor("Calibri", 0, 16, Color.black,
                                        txtAddQuestion, txtAddAnswer);

            /* after all the process, we can safely remove the last element
               inside itemList */
            itemList.remove(itemList.size()-1);
            
            /* finally, if itemList is empty, we clear the text next to the
               user added letters (the text in lblAddedLetters), and restore
               the default text and format of lblValidation to remove any
               error message it could be displaying */
            if (itemList.isEmpty())
                lblAddedLetters.setText("");
            
            lblValidation.setText(
                    ": : Click on the headers on each "
                  + "section for instructions : :  ");
            Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, new Color(192, 192, 192), Color.BLACK,
                        SwingConstants.RIGHT, lblValidation);

        } catch (Exception exc) {

            /* if there were no previously added items, then an 
               indexOutOfBoundsException will be triggered in the first
               instruction inside the trycatch clause. If so, we handle it
               by setting the default values in each JComponent */
            
            lblValidation.setText(
                    ": : Click on the headers on each "
                  + "section for instructions : :  ");
            Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, new Color(192, 192, 192), Color.BLACK,
                        SwingConstants.RIGHT, lblValidation);
            
            radBeginsWith.setSelected(true);
            
            cmbAddLetter.setSelectedIndex(0);
            
            Util.setText("DEFAULT", txtAddQuestion, txtAddAnswer);
        }  
    }
    
    /**
     * Adds all event listeners to their proper objects.
     */
    private void setEventListeners() {
 
        addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent event) {
                
                if(JOptionPane.showConfirmDialog(
                        mainPanel,
                        "Are you sure you want to leave?\n"
                      + "If so, thanks for playing!",
                        "Leaving so soon? :c",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });
        
        btnAddItem.addActionListener((ActionEvent e) -> {
            btnAddItemActionPerformed(e);
        });
        
        btnAddItemToCategory.addActionListener((ActionEvent e) -> {
            btnAddItemToCategoryActionPerformed(e);
        });
        
        btnCreateCategory.addActionListener((ActionEvent e) -> {
            btnCreateCategoryActionPerformed(e);
        });
        
        btnDeleteCategory.addActionListener((ActionEvent e) -> {
            btnDeleteCategoryActionPerformed(e);
        });
        
        btnRefreshCategories.addActionListener((ActionEvent e) -> {
            btnRefreshCategoryActionPerformed(e);
        });
        
        btnPreviousItem.addActionListener((ActionEvent e) -> {
            btnPreviousItemActionPerformed(e);
        });
        
        btnPlayQuickGame.addActionListener((ActionEvent e) -> {
            btnPlayPresetGameActionPerformed(e);
        });
        
        btnGenerateDefaultCategories.addActionListener((ActionEvent e) -> {
            btnGenerateDefaultCategoriesActionPerformed(e);
        });
        
        cmbAddLetter.addActionListener((ActionEvent e) -> {
            cmbAddLetterActionPerformed(e);
        });
        
        cmbCategory.addActionListener((ActionEvent e) -> {
            cmbCategoryActionPerformed(e);
        });
        
        cmbGameTime.addActionListener((ActionEvent e) -> {
            cmbGameTimeActionPerformed(e);
        });
        
        List<JLabel> labels = 
                Arrays.asList(
                    lblCustomGame, lblGameTime, lblPresetGameOrCustomizeCategories,
                    lblQuickGame, lblCustomizeCategories, lblHelp, lblAyuda,
                    lblAuthor, lblValidation);

            labels.forEach(item -> {

                item.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                        if(item.getText().contains(": : Click on the headers")) {
                            
                            /* if the component is lblValidation but no error
                               is being shown, then all compoenents are painted
                               green to indicate the users where they can click
                               to get the instructions. */
                            labels.forEach(lbl -> lbl.setForeground(Color.green));

                        /* do nothing if lblValidation is currently showing 
                           an error text. */
                        } else if (item.getName()
                                       .equals("lblValidation")
                               && !item.getText()
                                       .contains(": : Click on the headers")) {
                            
                        } else
                            
                            //all other components are colored cyan.
                            item.setForeground(Color.cyan);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                        /* same as above, but this time, all components are
                           resetted to their default foreground color values. */
                        if(item.getText().contains(": : Click on the headers")) {
                         
                            labels.forEach(lbl -> {
                          
                                if (lbl.getName().equals("lblAuthor"))
                                    lbl.setForeground(Color.gray);
                                
                                else
                                    lbl.setForeground(Color.white);
                            });
                        }
                        
                        if(item.getName().equals("lblAuthor"))
                            item.setForeground(Color.gray);
                        
                        /* do nothing if lblValidation is currently showing 
                           an error text. */
                        else if (item.getName()
                                        .equals("lblValidation")
                             && !item.getText()
                                        .contains(": : Click on the headers")) {
                        
                        /* and repaint it gray if it is showing the default
                           text. */
                        } else if (item.getName()
                                        .equals("lblValidation")
                              && item.getText()
                                        .contains(": : Click on the headers")) {
                            item.setForeground(new Color(192, 192, 192));
                            
                        // any other case should repaint the component white.
                        } else
                            item.setForeground(Color.white);
                    }
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        /* lblValidation is the only JLabel on the list with
                           no mouseClicked event assigned to it */
                        if(!item.getName().equals("lblValidation"))
                            Util.triggerMouseEvent(item.getName());
                    }
                });         
            });
        
        txtAddQuestion.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent e) {
                
                txtAddQAndTxtAddAKeyReleased(e, txtAddQuestion);
            }
        });
        
        txtAddAnswer.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent e) {
                
                txtAddQAndTxtAddAKeyReleased(e, txtAddAnswer);
            }
        });      
    }
    
    /**
     * Closes the current DoughnutMain JFrame and initializes a DoughnutGame 
     * object.
     * <p>
     * This method checks if cmbCategory or cmbGameTime options are
     * invalid, and if they are, it will return without initializing 
     * a DoughnutGame object.
     */
    private void startNewGame() {
        
        /* if the user did not manually add 10 sets of Q and A's to start
           a custom game and there are no categories to begin a quick
           game, then we return */
        if (itemList.size() != 10 && new Validator()
                                    .cmbBoxIsEmpty(cmbCategory)) {
            return;
        }
        
        /* if the user did not manually add 10 sets of Q and A's to start
           a custom game and the selected category is not a valid text
           file, we return */
        if (itemList.size() != 10 && 
            !new Validator()
                    .isValidTxtFile(cmbCategory.getSelectedItem().toString(), 
                                    cmbCategory)) {
            return;
        }
        
        /* if the first two characters of the string selected in cmbGameTime 
           JComboBox cannot be converted to an integer value, we return */
        if (!new Validator(lblValidation)
                .isGameTimePrimaryCheck(cmbGameTime.getSelectedItem().toString(),
                            cmbGameTime))                 
            return;
       
        gameTime = Util.getGameTime(
                            cmbGameTime.getSelectedItem().toString()); 
        
        /* the game can be started, so we hide this frame and initialize
           a DoughnutGame instance */
        this.setVisible(false);
        this.setEnabled(false);
        
        try {

            rGUI = new DoughnutGame();
            rGUI.setVisible(true);
            
        } catch (ClassNotFoundException e) {
            
            JOptionPane.showMessageDialog(null, 
                      "Error while trying to read RoscoGUI Class\n"
                    + "This is an internal error in the app, please\n"
                    + "contact the programmer for a solution. \n"
                    + Util.getErrorLocation(
                      "Doughnut", 
                      "startNewGame()", 
                      "rGUI = new RoscoGUI()"),
                      "Class error",
                      JOptionPane.ERROR_MESSAGE);
            
            System.exit(1);
        }
    }
    
    
                /////////////////////////////
                ////// EVENT LISTENERS //////
                /////////////////////////////
    
    private void btnAddItemActionPerformed(ActionEvent e) {
        
        addItem();
    }
    
    private void btnAddItemToCategoryActionPerformed(ActionEvent e) {
        
        addItemToCategory();
    }
    
    private void btnCreateCategoryActionPerformed(ActionEvent e) {
        
        createCategory();
    }
    
    private void btnDeleteCategoryActionPerformed(ActionEvent e) {
        
        deleteCategory();
    }
    
    private void btnRefreshCategoryActionPerformed(ActionEvent e) {
        
        refreshCategories();
    }
    
    private void btnPreviousItemActionPerformed(ActionEvent e) {
        
        removeCurrentItem();
    }
    
    private void btnPlayPresetGameActionPerformed(ActionEvent e) {
        
        playQuickGame();
    } 
    
    private void btnGenerateDefaultCategoriesActionPerformed(ActionEvent e) {

        generateDefaultCategories();
    } 
    
    private void cmbAddLetterActionPerformed(ActionEvent e) {
                        
        /* if there is an error border set on cmbAddLetter, we make it
           go away only if the user selects a Letter that is not already
           set to an Item object in itemList. This serves as a visual
           indicator so as not to repeat the Letter. */
        itemList.forEach(item ->{
            
            if(cmbAddLetter.getSelectedItem() != item.getLetter())
                Util.setDefaultBorder(cmbAddLetter);
        });
    }
    
    private void cmbCategoryActionPerformed(ActionEvent e) {
 
        /* if there was an error border set on cmbCategory, we reset it
           to its default one so that the error border can be reasigned
           if a validator check needs to. */
        Util.setDefaultBorder(cmbCategory);
    }
    
    private void cmbGameTimeActionPerformed(ActionEvent e) {
 
        /* if there was an error border set on cmbGameTime, we reset it
           to its default one only if the actual selected value is a
           valid one. This serves as a visual indicator for the user
           to know that they have selected a correct value.*/
        new Validator().isGameTimeSecondaryCheck(
                            cmbGameTime.getSelectedItem().toString(), 
                            cmbGameTime);    
    }
    
    private void txtAddQAndTxtAddAKeyReleased(KeyEvent e, JTextField txt) {
        
        /* while typing on both txtAddQuestion and txtAddAnswer JTextFields,
           the remaining characters able to be typed will be shown in
           lblCharLimit JLabel with the displayCharLimit() method.
           Additionally, if there was an error border set to them, the
           border will reset to the default one if the user types anything
           that is not the default "Question" or "Answer" values for each
           respective JTextField.
        */
        displayCharLimit(txt);
                     
        itemList.forEach(item ->{

            if(!txt.getText().equals(item.getQuestion()))
                Util.setDefaultBorder(txt);
        });
    }

    /**
     * Sets the focus gained event to txtAddAnswer, txtAddQuestion
     * and cmbAddLetter, radBeginsWith, radContains and radEndsWith 
     * JComponents.
     * <p>
     * This method removes the default "Question" and "Answer" texts
     * inside txtAddQuestion and txtAddAnswer respectively when the
     * user clicks on them. It also sets a default border, to override
     * the error one if it was previously assigned.
     * <p>
     * If the focus was established in cmbAddLetter JComboBox, or any
     * of the radio buttons, then it sets the default border to
     * txtAddQuestion and txtAddAnswer to override the error one.
     * @param e
     * The focus event object.
     */
    @Override
    public void focusGained(FocusEvent e) {
        
        /* if there is an error message displayed in lblValidation, setting
           the focus on each listening component will reset that JLabel to
           its default value and format. */
        if (!lblValidation.getText().startsWith(": : Click on the headers")) {
            
            lblValidation.setText(
                                ": : Click on the headers on each "
                              + "section for instructions : :  ");
            Util.setLabelFontStyleSizeColorBgColorHAlignment(
                        "Calibri", 2, 14, new Color(192, 192, 192), Color.BLACK,
                        SwingConstants.RIGHT, lblValidation);
        }

        /* if the user focuses on txtAddAnswer, JTextField while the default
           "Answer" value is set as its text, then that value will be erased
           so as to leave an empty space for the user to type the Answer,
           the foreground will be changed to a different one from the regular 
           gray, and all other components' borders will reset to default, to
           erase any previously set error border. */
        if(e.getComponent() == txtAddAnswer) {
            
            if (txtAddAnswer.getText().equals("Answer")) {
                
                txtAddAnswer.setText("");
                Util.setFontStyleSizeColor("Calibri", 0, 16, Color.black, txtAddAnswer);               
                Util.setDefaultBorder(txtAddQuestion, cmbAddLetter,
                                       cmbGameTime, cmbCategory);
            }
            
            /* in any case, when focusing in txtAddAnswer, the remaining
               characters to be typed will be displayed inside lblCharLimit. */
            displayCharLimit(txtAddAnswer);

        } else if (e.getComponent() == txtAddQuestion) {
            
            //the same as txtAddAnswer.
            if (txtAddQuestion.getText().equals("Question")) {
                
                txtAddQuestion.setText("");
                Util.setFontStyleSizeColor("Calibri", 0, 16, Color.black, txtAddQuestion);    
                Util.setDefaultBorder(txtAddAnswer, cmbAddLetter,
                                       cmbGameTime, cmbCategory);
            }
            
            //the same as txtAddAnswer.
            displayCharLimit(txtAddQuestion);

        /* focusing on any other component resets the border of the JTextFields
           and JComboBoxes that can be assigned an error border to. */
        } else if (e.getComponent() == cmbAddLetter
                || e.getComponent() == radBeginsWith
                || e.getComponent() == radEndsWith
                || e.getComponent() == radContains) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer,
                                   cmbGameTime, cmbCategory);
        
        /* focusing on cmbGameTime resets the borders of the other components
           designed for the user to input or select values. */
        } else if (e.getComponent() == cmbGameTime) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbCategory);
        
        // the same for cmbCategory.
        } else if (e.getComponent() == cmbCategory) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbGameTime);

        // the same for cmbCategory.
        } else if (e.getComponent() == cmbAddLetter) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbGameTime, cmbCategory);
        }
    }

    /**
     * Sets the focus gained event to txtAddAnswer, txtAddQuestion
     * and cmbCategory JComponents.
     * <p>
     * This method resets the default values "Question" and "Answer"
     * to txtAddQuestion and txtAddAnswer JTextFields respectively
     * if they lose focus while they have no user inputted text in
     * them.
     * <p>
     * It also sets the default border to all mentioned JComponents
     * to override the error ones they could have been previously 
     * assigned.
     * @param e 
     * The focus event object.
     */
    @Override
    public void focusLost(FocusEvent e) {
        
        if(e.getComponent() == txtAddAnswer) {
            
            /* if txtAddAnswer loses focus, nothing will be displayed 
               in lblCharLimit */
            lblCharLimit.setText("");
            
            /* if txtAddAnswer's value is empty when losing focus, we
               display its default "Answer" value and set the format to
               italics and gray foreground color. */
            if (txtAddAnswer.getText().equals("")) {
                
                txtAddAnswer.setText("Answer");
                Util.setFontStyleSizeColor("Calibri", 2, 16, Color.gray, txtAddAnswer);
                Util.setDefaultBorder(txtAddAnswer);
            }
        
        } else if (e.getComponent() == txtAddQuestion) {
            
            //same as txtAddAnswer.
            lblCharLimit.setText("");
            
            //same as txtAddAnswer, but with "Question" as the default value.
            if (txtAddQuestion.getText().equals("")) {
                
                txtAddQuestion.setText("Question");
                Util.setFontStyleSizeColor("Calibri", 2, 16, Color.gray, txtAddQuestion);
                Util.setDefaultBorder(txtAddQuestion);
            }
            
        /* and we repeat the same process as the one on the above focusGained
           method, to assure that if an error border is showing up, it will
           be resetted regardless of the user next focus. This includes the 
           other sections of the GUI). */
        } else if (e.getComponent() == cmbAddLetter
                || e.getComponent() == radBeginsWith
                || e.getComponent() == radEndsWith
                || e.getComponent() == radContains) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer,
                                   cmbGameTime, cmbCategory);
            
        } else if (e.getComponent() == cmbCategory) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbCategory, cmbAddLetter);
        
        } else if (e.getComponent() == cmbGameTime) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbGameTime, cmbAddLetter);
        
        } else if (e.getComponent() == cmbAddLetter) {
            
            Util.setDefaultBorder(txtAddQuestion, txtAddAnswer, cmbGameTime, cmbCategory);
        }
    }
    
    public static void main(String[] args) {
        
        /* we initialize an object of this class and set the focus on
           the "Play on the selected category" button under the "Quick
           game" section. If we do not do this, the focus will be set
           on txtAddQuestion by default (as it is the first focusable
           object), which will trigger its focusGained event, potentially
           confusing the user. */
        rMain = new DoughnutMain();
        rMain.setVisible(true);
        btnPlayQuickGame.requestFocus();
    }
}