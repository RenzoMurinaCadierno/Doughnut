# Doughnut
My first formal attempt at programming (Java - J.D.K. 8.0). It's a basic but fully customizable trivia game. It's documented in the help files accesible in the main GUI, and the code is completely commentated. Free and open-source, use it as you wish. But please, don't forget to mention me as the author, as I'm trying to launch my programming career little by little, and that helps me lots! Thanks for reading!


                              ::::::::::
                            ::          ::
                          ::              ::
                          ::   DOUGHNUT   ::
                          ::              ::
                            ::          ::
                              ::::::::::


                          ::  Help  file  ::


1. Instructions
    1.1. Main graphical user interface (first screen)
    1.2. Main functionalities
        1.2.1 Custom category-less game
        1.2.2 Quick game
        1.2.3 Customize categories
2. About the game
3. About the author


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1. Insctructions
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

This is a simple question-and-answer game, built with NetBeans IDE and using J.D.K. 8.0.

The questions appear on screen alongside the designed letter and condition for the answer. The conditions can be 'Begins with', 'Contains' or 'Ends with'. For example:

Condition: Begins with
Letter: D
Question: It is said to be man's best friend.
Answer: Dog

That is to say, the answer to type begins with D, and in this example it is Dog.

Once you have chosen a gameplay option -either a custom or a quick one, the questions will appear on screen and a timer will start running. You need to answer in the designed box, and you can pass the question without answering it using the pass button or by pressing the Tab key. If you reach the end of the cicle of questions with some or all of them unanswered (passed) and if the timer is still running, the game will repeat those unanswered questions, ignoring the ones that were answered correctly or incorrectly.

When the time is up or when all questions were answered, the game ends and a result screen shows up, which replicates the question list. You can click on each Letter and the associated Question, condition, true answer, answer you have given and status for it (correct, incorrect, passed or unanswered) will appear on screen. Additionally, the final score and options to play again or exit the game will be displayed too.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1.1. Main graphical user interface (first screen)
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

The starting GUI is divided into three main headers, which we will explain later in their own sections:

> 'Custom category-less game'
> 'Quick game'
> 'Customize categories'

Furthermore, there are four other sub-headers:

> The top header, above 'Custom category-less game', which tells you you where to click to get in-game instructions on what to do and where to do it. Error messages are also displayed here.

> The Total game time for any game you wish to play header, that 'Contains' a list with the different options for the complete game time (for the 10 sets of questions in total, not for each individual one). Either you decide to play a custom game or a quick one, you need to select the total game time which will tell the in-game counter where to start ticking.

> 'Categories to play a quick game or to customize' is the category list from where you select a file either to play a quick game or to add a set of question, condition, letter and answer to add to a category text file (we will explain this later in the 'Custom category-less game' section).

> The footer, that contains the ::Help:: and ::Ayuda:: labels, which on click will create this file you are reading now (on its english and spanish versions, depending on which label you click on. The footer 'Contains' also my contact information if you hit the 'Designed by Max' text.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1.2. Main functionalities
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

As we mentioned in the previous section, three main headers exist which allocate the prime functionalities of the program:
 
> 'Custom category-less game'
> 'Quick game'
> 'Customize categories'

The first and second one allow you to play the game, while the last one will let you configure the category files.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1.2.1. 'Custom category-less game'
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

On this section of the GUI you can generate 10 custom questions manually, without the need of being stuck to a certain category. These questions are one-time-only, meaning that once the game is over, whatever you have typed is lost.

To load a set of question, condition, letter and answer, you need to type the question in the textbox that displays Question in gray color. Then, you have to select the condition for the letter and answer ('Begins with', 'Contains' or 'Ends with') by clicking on the corresponding item option. After that, you choose which letter will be used to link the condition and the answer, on the list next to the condition options. Finally, you type the answer in the textbox that displays Answer in gray color.

Clicking on the 'Add set of Q and A' button will retrieve everything you have done above and store it into memoty. Below the 'Back to previous set' button, a list with the currently inserted Letters will show up, so that you can keep track of which ones you have already used up.

If we wish to delete the last set we have added, then we can click on the 'Back to previous set'. This action will also bring back on screen everything you had on that set, as a safety measure if you want to add it back or change something and re-add it.

It is important that you select the desired full game time befor adding the whole 10 sets to memory. You can do so by choosing the game time option from the Total game time for any game you wish to play section. Go to the previous section if you need more information on it.

Once you have added 10 sets with different Letters each and you have chosen the total game time, the program will ask you if you wish to begin the game. If you do not, then the last added set will be removed from memory and everything typed and selected in it will be displayed on screen for you to re-add it, so that the program can ask you again.

If you select OK, then the game will begin. Keep in mind that the timer will start up immediately, so be ready!

Rules to add sets of Letter, condition, question and answer:

> You cannot add empty fields or only spaces to the Question and Answer text fields.
> You cannot type Question or Pregunta on the Question text field, or Answer or Respuesta on the Answer text field. Those are the default values, so they are not allowed.
> You cannot repeat previously added letters.
> You cannot repeat any combination of: question and condition, answer and condition, or question and answer.
> The questions have a limit of 100 characters each, and the answers, 20.
> If you choose the condition 'Contains', then the typed answer cannot begin with or end with the specified letter.
> The answers must respect the selected letter and condition.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1.2.2 'Quick game'
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

When you start the program up, it automatically creates a folder named 'Categories__' in the same path as the one you launched it. Each category text file that hold the sets of letter, question, answer and condition is stored there. If a category file contains at least 10 sets with different Letters assigned to them, then that file is elegible to play a quick game.

To start a quick game up, you simply have to select one of those category files which are displayed in the drop menu under the 'Categories to play a quick game or to customize' section, select the full game time under the Total game time for any game you wish to play  section and click on the 'Play on the selected category' button.

If the category file is valid, the game will begin immediately. If it is not, an error message will show up on screen indicating what failed and how to deal with it.

The category drop menu if empty by default. To fill it up, you can generate the default categories (explained in the next paragraph), create a custom text file in the 'Customize categories' header, or add a valid category text file (.txt) manually to the self-generated 'Categories__' folder. Remember that the file must contain at least 10 sets with different Letters assigned to them. There is no limit on the amount of sets a category file can store.

The program is able to generate default categories, which are included into the code. To do so, you need to click on the Generate/reload default categories button. Doing so will trigger
their creation, and they will automatically appear in the drop menu, which is where they have to be selected to play a quick game.

Most sets in the default categories were extracted from LaffGaff's website (http://laffgaff.com/free-trivia-questions-and-answers/), I've only edited and translated them to Spanish and added some, additional sets to complete the category. So, full credits to them.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
1.2.2 'Customize categories'
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

> 'Add to category'

Following up with what was described in the first paragraph of the revious section, the program can permanently add a set of letter, condition, question and answer from the game directly to a text file inside the 'Categories__' folder.

To do so, you need to type a question and answer on their respective fields under the Custom category-less section, as well as selecting the letter and the condition (just as if we were to add a set to a one-time-only game. However, instead of clicking on the 'Add set of Q and A' button, you select a category you wish to store the set from the list under the 'Categories to play a quick game or to customize' header, and click on the 'Add to category' button.

If there were no errors, then everything typed and selected above will be added to that category file. We can open that file up (inside the Categories folder and we will find the set we have just added on the last line.

> 'Create category'

Using this option we can create a text file (.txt) for a new category, to be stored inside the 'Categories__' folder.

The name must contain from 3 to 25 characters (english letters, numbers and spaces, and cannot be named 'README' or 'LEEME' as those are reserved names for the help files, stored inside the same folder.

If the creation was successful, the file will now be displayed inside the categories drop menu.

> Delete a category

If we select a category on the drop menu under the 'Categories to play a quick game or to customize' and click on the 'Delete category' button, you can delete that category file permanently.

*WARNING*
This action is permanent. Once you delete the file, it is not sent, for example, to the Recycle Bin if you are using Windows.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
2. About the game
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

This game was created as a first try at programming in Java and with Object Oriented Programming's concept.

The idea is not that different from any question-and-answer game, but was considered ideal to integrate each basic and essential concepts of OOP, as well as managing file system and user interaction, flow control with exception handling, static variables and method usage wherever necessary, and so on.

The whole application was constructed with 8 classes and 1 Enum.

1. The main GUI (DoughnutMain class, the entry point of the program), which allows the user to load the sets of letter, condition, question and answer, to select the game time, to choose categories to play a quick game and to configure them.
The code for this interface was written by hand, and that is why it looks different than the other JForms in the program. Only NetBeans text editor was used to code it, without the assistance of JForm's standard design. The decision to do it this was mostly supported as a way to practise to understand how a Form is designed from scratch, how the components and listeners are added and how they interact with each other, how variables and methods are ordered, as well as when and where to create and instantiate objects and other characteristics. At all times, the simplest and/or most efficient expression was tried to be used, coded by hand without the help of any framework or tools provided by NetBeans IDE to ease the procedure.

2. The game GUI (DoughtnutGame class), which extracts the collected information in the previous interface, creates finalItem and CountDown objects (explained later) and controls the flow of the game itself. This class is the one responsible for displaying the questions, letters and condition on screen, read the user inputs for the answers and act as a consequence, handling each correct, incorrect and passed questions accordingly.

3. The CountDown class, designed to create a parallel thread next to the main one. This class keeps track of the remaining total game time in seconds and counts down by one as they go by. Objects of this class themselves by a command emitted from the game GUI class if there are
no more questions left to be answered. The CountDown class is the one that instantiates and initializes GameOver objects.

4. GameOver class displays a JForm that serves as a GUI for the results.
The Letters, questions and conditions shown in the game GUI are replicated here, and the answers given by the users, the real answers and the status condition for them (correct, incorrect, passed and  unanswered) follow along. This class commands its objects to pull the
list that contains all finalItem objects from the game instance before disposing it, and will use it to generate all the data mentioned above. The final result score is also displayed on screen, with the individual scores for each status. Finally, the JForm of this class is set to offer the options to exit the game or to play again. The latter will cause an instantiation and initialization of a new main class object, effectively restarting the game.

5. Item class allows the construction of objects capable to store the set of Letter to use in a question, the answer Condition, the Question and Answer to it (LCQA set). The main class can instance and initialize objects of this class each time the user inputs a LCQA set manually to play a 'Custom category-less game', or when the user chooses the 'Quick game' option (in that case, the Item object is called ten times to extract ten LCQA sets from the selected category file and store them in a list which will later be retrieved by the game class.

6. FinalItem class inherits from Item and adds two new attributes which are capable of storing the answer given by the user while in game and the status for that answer (correct, incorrect, passed or unanswered). Objects of this class replicate the LCQA sets in the form of Item objets inside the list that stores them, and modify their own attributes as the user responds in execution time. This objects are retrieved by a GameOver instance to complete its own GUI's data.

7. Validator class, as the name indicates, is the one that contains all methods used to validate any user input, creation of files, file reading, exception handling, sanity checks and any other instructions that might need to behave properly before proceeding with the flow.
Objects created by this class interact with methods in other classes -especially the ones allocated in Util- to manipulate the format of some JComponents in other GUIs to, as examples, set default or error borders to them, or to change the format of the font to notify the user visually that there is an error or that everything is OK. 
The constructor of this class can either be an empty one or accept a label as a parameter. That JLabel is the one used in the main class to display error messages on screen.

8. Util class is meant to hold all static methods and variables to be reused throughout the whole program. Given the fact that this class is not designed to be instanciated (there are no Util objects), it is the natural host for all final and static variables, so as they do not consume more memory than required. The quantity and functionality of the methods this class holds is extense, which is why if you are interested in knowing what they do, it is  better for you to check the code yourself. Do not worry, it is fully commented.

> Enum:

9. The only Enum this application uses was designed to hold the values of the english letters of the alphabet (A-Z), and make them immutable. Those values are used to complete the drop list of letters to choose when creating a manual game, and to compare the letters inside the category text files to make sure the characters are the correct ones.


:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
3. About the author
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

My name is Renzo Nahuel Murina Cadierno, pleasure to meet you. I have a university degree in Business Administration and an M.B.A in Business Management. I have been an academic researcher for two years and I am actually working for the my State's Judiciary.

Counting from the current month (July/2019), six months ago I fell in love with my true passion: programming. I have assisted and approved several courses on Java, Javascript and Python branches, and I plan on doing many more. To this date, I have designed some mini applications in Python that help me automatize my everyday routine at work and at home. I am also designing and programming a NodeJS server alongside a MongoDB database which will serve to bring my own website up that will serve both as a way of contacting me, checking up on my progress, and as a main, free and open source repository for anyone who is interested in downloading my applications to use them and/or to check the code up. As far as Java goes, this program is my first try at the language.

Mi dream is freelance programming, but to archieve it I am yet to learn and practise much more. As they say, better late than never, and that is why I try hard each and every day to read a little more and write some lines of code to succeed. My foresight is to take the international certification exams for the three mentioned languages, and it is of my enormous pleasure getting to know about task automation and Machine Learning (Python), about big-scaled and highly-replicable applications (Java) and back-end server and NoSQL management (JS, NodeJS and MongoDB). Of course, not only getting to know them, but also doing them.

I am an independent learner, but at the same time I take full courses linked to everything mentioned and with a great smile drawn on my face I try to occupy a great chunk of my free time programming given that, of course, it is my passion.

If you wish to consider it, with pleasure I am open to job offers.

My contact mail is the following one:

           -----------------------------------------------
                     nmcadierno@hotmail.com
           -----------------------------------------------

Thank you very much for reading, and for giving me the opportunity to show you this modest first try at a program designed in Java. 

You are the best!
