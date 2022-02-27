/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evil.hangman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author rcortez
 */
public class SampleController implements Initializable {

    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> namesList = new ArrayList<String>();
    private ObservableList displayList = FXCollections.observableArrayList();

    ArrayList<String> picts = new ArrayList<>();
    ArrayList<String> usedLetters = new ArrayList<>();
    ArrayList<String> wordBlanks = new ArrayList<>();
    ArrayList<String> possibleWords = new ArrayList<>();
    String word = "";
    String name = "";
    int wrongGuess;
    int correctGuess;
    int length;
    Boolean notValid = false;
    int score = 0;

    @FXML
    private Label lblWord, lblWL, lblWinLoss, lblValid, lblTurns;
    @FXML
    private ImageView imgHang;
    @FXML
    private TextField userInput;
    @FXML
    private TextField userLength;
    @FXML
    private TextField userName;
    @FXML
    private ListView topList;

    @FXML
    private void handleStart(ActionEvent event) {
        picts.clear(); //clearing the hangman picture
        name = userName.getText(); //getting the users name from the textfield
        length = Integer.parseInt(userLength.getText()); //getting the length of the word the user wants
        picts.add("resources/hangman1.png"); //adding all the pictures to an array
        picts.add("resources/hangman2.png");
        picts.add("resources/hangman3.png");
        picts.add("resources/hangman4.png");
        picts.add("resources/hangman5.png");
        picts.add("resources/hangman6.png");
        picts.add("resources/hangman7.png");
        picts.add("resources/hangman8.png");
        picts.add("resources/hangman9.png");
        picts.add("resources/hangman10.png");
        picts.add("resources/hangman11.png");
        picts.add("resources/hangman12.png");
        picts.add("resources/hangman13.png");
        picts.add("resources/hangman14.png");
        for (int i = 0; i < length; i++) {//adding blanks to an array according to the length the user wanted
            wordBlanks.add("_");
        }
        lblWord.setText(String.join(" ", wordBlanks)); //displaying the blanks on the screen
    }

    @FXML
    private void handleGuess(ActionEvent event) {
        notValid = false; //reseting variable to false each time button is pressed
        Dictionary testDic = new Dictionary(); //creating an instance of the dictionary class
        String guess = userInput.getText().toUpperCase(); //getting the users guess from the textfield and making their input uppercase
        char checkLetter = guess.charAt(0); //looks at the charcter of the letter the user inputs
        if (guess.length() > 1) { //checks to see that user put only one letter. 
            notValid = true; //the users input is not valid
            lblValid.setText("Can only input one letter. Please try again.");//tells the user why thier input is not valid
        } else if (!(checkLetter >= 65 && checkLetter <= 90)) { //checks to see if user put in a letter and not another key
            notValid = true;//the users input is not valid
            lblValid.setText("Can only input letters. Please try again.");//tells the user why thier input is not valid
        }
        for (int i = 0; i < usedLetters.size(); i++) {//goes through each letter in the usedLetters array
            if (usedLetters.get(i).equals(guess)) {//This checks if the user has already inputed this letter before
                notValid = true;//the users input is not valid
                lblValid.setText("Already inputed letter. Please try again.");//tells the user why thier input is not valid
            }
        }

        if (notValid == false) { //will only execute code if the user input was valid
            lblValid.setText(""); //setting valid label to nothing
            usedLetters.add(guess);//adds the users guess to the used letters array
            lblWL.setText(String.join(", ", usedLetters));//displaying the used letters
            if (word.equals("")) { //if word has not been chosen yet. The word is still blank
                if (testDic.getArrayNoMatch(usedLetters, length).size() == 0) {//checks to see if there would be no possible words 
                    System.out.println(possibleWords);
                    int chooseWord = (int) (Math.random() * possibleWords.size() - 1); //chooses a word since now no more words can be eliminated
                    word = possibleWords.get(chooseWord); //sets a word
                    System.out.println("The word:" + word);
                } else {//words can still be eliminated
                    possibleWords = testDic.getArrayNoMatch(usedLetters, length); //assigns possible words to an array
                    System.out.println(possibleWords); 
                }
            }

            gamePlay guessing = new gamePlay(word, guess, wordBlanks); //new instance of gamePlat class
            boolean correct = guessing.getMatch(); //assigns true or false to correct
            wordBlanks = guessing.getBlanks(); //gets updated blanks from gamePlay class
            if (correct == false) { //means that the users guess was not found in the word
                wrongGuess++; //adds to varible wrongGuess
                lblTurns.setText(Integer.toString(wrongGuess) + " out of 14 turns"); //shows user how many turns they have used
                if (wrongGuess == 14) { //checks if all the turns are used
                    calculateScore(); //calculates the users score for the game
                    scores(); //sorting through the scores to print top 5
                    lblWinLoss.setText("You lost. Score:"+score); //Shows score and you lost
                }
                for (int i = 0; i < wrongGuess; i++) { //Going through each wrong guess
                    imgHang.setImage(new Image(picts.get(i))); //setting updated hangman images
                }
            } else { //means that the users guess was found in the word
                correctGuess += guessing.correctLetters(); //adds how many letters were corect to total letters correct
                if (correctGuess == word.length()) { //means that user guessed all the letters in the word
                    calculateScore(); //calculates score
                    scores();//sorting through top 5
                    lblWinLoss.setText("You won! Score:"+score); //shows score and that you won
                }
                lblWord.setText(String.join(" ", wordBlanks)); //updating wordBlanks on the screen
            }

        }
 
    }

    @FXML
    private void reset() {//resets all the variables so new game can be played
        usedLetters.clear();
        word = "";
        picts.clear();
        wordBlanks.clear();
        possibleWords.clear();
        wrongGuess = 0;
        correctGuess = 0;
        length = 0;
        notValid = false;
        lblWord.setText("");
        lblWinLoss.setText("");
        lblWL.setText("");
        lblValid.setText("");
        imgHang.setImage(null);
        score = 0;
        topList.getItems().clear();
        namesList.clear();
        list.clear();
    }
    
    public void calculateScore(){
        for(int i=0; i<wordBlanks.size(); i++){ //goes through each letter in the word
            //all of the if statements are determining how many points each letter should be assigned
            //gets the blanks are in each position and sees if that position contains that letter
            if(wordBlanks.get(i).equals("A") || wordBlanks.get(i).equals("E") || wordBlanks.get(i).equals("I") || wordBlanks.get(i).equals("O") || wordBlanks.get(i).equals("U") || wordBlanks.get(i).equals("L") || wordBlanks.get(i).equals("N") || wordBlanks.get(i).equals("S") || wordBlanks.get(i).equals("T") || wordBlanks.get(i).equals("R")){
                score++;
            }
            if(wordBlanks.get(i).equals("D") || wordBlanks.get(i).equals("G")){
                score=score+2;
            }
            if(wordBlanks.get(i).equals("B") || wordBlanks.get(i).equals("C") || wordBlanks.get(i).equals("M") || wordBlanks.get(i).equals("P")){
                score=score+3;
            }
            if(wordBlanks.get(i).equals("F") || wordBlanks.get(i).equals("H") || wordBlanks.get(i).equals("V") || wordBlanks.get(i).equals("W") || wordBlanks.get(i).equals("Y") ){
                score=score+4;
            }
            if(wordBlanks.get(i).equals("K")){
                score=score+5;
            }
            if(wordBlanks.get(i).equals("J") || wordBlanks.get(i).equals("X")){
                score=score+8;
            }
            if(wordBlanks.get(i).equals("Q") || wordBlanks.get(i).equals("Z")){
                score=score+10;
            }        
        }
        score=score+(int)(score*(length*0.25));//a length multiplier. The longer the length the higher the score added
        if(wrongGuess==14){ //this means the user did not guess the word
            int close=word.length()-correctGuess; //seeing how many letters left to guess
            if(close==1 && word.length()<6){ //Checks if user was close to guessing word and the length is less than 6
                score=score+(int)(score*0.1); //A small score adder since you were close to guessing the word
            }
            if(close==2 && word.length()>=6){ //if the word is long then the user can have to letters taht still had to be guessed
                score=score+(int)(score*0.1); //A small score adder since the user was close to guessing the word
            }
        }
        if(correctGuess==word.length()){ //This means the user guessed the word successfully
            //a small token for guessing the word
            if(wrongGuess<=5){ //if they guessed the word in 5 or less chances
                score=score+(int)(score*0.25); //get a higher added score
            }
            else if(wrongGuess<=10){ //if the user guesses 10 or less and 6 or more wrong letters
                score=score+(int)(score*0.2); //get a miny additional score
            }
            else if(wrongGuess<14){ //if the guesses less than 14 wrong letters and 10 or more letters 
                score=score+(int)(score*0.15); //get a miny additional score added
            }
            
        }
       
    }

    public void scores() {
        list.add(Integer.toString(score)); //add the users score to the list of scores
        namesList.add(name); //add name to the names List
        try {
            FileReader reader = new FileReader("src/resources/file.txt");
            FileReader reader1 = new FileReader("src/resources/file_1.txt");
            Scanner in = new Scanner(reader);
            Scanner in1 = new Scanner(reader1);
            while (in.hasNextLine()) { //getting all the scores and names from the textFile 
                String scores = in.nextLine();
                String theNames = in1.nextLine();
                int temp = 0;
            //putting the scores in order with designated name. insertion sort        
            for (int j = 0; j < list.size(); j++) { //going through each score in the score list
                if (Integer.parseInt(scores) > Integer.parseInt(list.get(j))) { //checking if the score from the textFile is greater than the position in the list
                    temp = j + 1; //the score is then supposed to go in the postion after j in the list
                }

            }
                list.add(temp,scores); //setting score in correct place
                namesList.add(temp,theNames); //setiing name in correct place
            }
            //getting last 5 scores since those are the higest scores
            int counter = 0;
            for (int z = list.size() - 1; z > 0; z--) { //starting at end of the list and going backwards
                counter++;
                if (counter <= 5) { 
                    displayList.add(namesList.get(z) + "    " + list.get(z)); //geting score in postion so it can be displayed
                    topList.setItems(displayList);//displaying scores in listview

                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("SOMETHING HAS GONE HORRIBLY WRONG WE'RE ALL GONNA DIE!");
        }
        String outFile = "src/resources/file.txt";
        String outFile1 = "src/resources/file_1.txt";
        try { //saving names and scores in the textFile
            PrintWriter out = new PrintWriter(outFile);
            PrintWriter out1 = new PrintWriter(outFile1);
            for (int i = 0; i < list.size(); i++) {
                out.println(list.get(i));
                out1.println(namesList.get(i));
            }
            out.close();
            out1.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Something went wrong!");
        }
        

    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
