/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evil.hangman;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rcortez
 */
public class gamePlay 
{
    String word;
    String guess;
    boolean correctGuess=false;
    ArrayList<String> wordBlanks = new ArrayList<>();
    int correct;
    public gamePlay(String theWord, String userGuess, ArrayList<String> blanks){ //setting each parameter to s global variable to be used throughout the class
        word=theWord;
        guess=userGuess;
        wordBlanks=blanks;
        userGuess(); //calling userGuess method to preform some additional checks
    }
    public void userGuess(){
        for(int i=0; i<word.length();i++){ //looking through each letter in the word
            if(guess.equalsIgnoreCase(word.substring(i,i+1))){ //checks if the guess matches a letter in the word
                correctGuess=true; //saying that that the word did contain the guess
                wordBlanks.set(i,guess); //changing the blank to the to the guess
                correct++; //counting how many of the guess letter were found in the word
            }
        }
    }
    public boolean getMatch(){ //returning if the guess was found in word
        return correctGuess;
    }
    public ArrayList<String> getBlanks(){//returning the updated blanks 
        return wordBlanks;
    }
    public int correctLetters(){//returns how many times that letter was found in word
        return correct;
    }
   
    
}
