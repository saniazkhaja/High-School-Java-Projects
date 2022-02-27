package evil.hangman;

import java.util.ArrayList;     // Used to create ArrayLists dictionary use
import java.util.Scanner;
import java.io.*;               // Used for IOException, File
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {

    ArrayList<String> possibleWords = new ArrayList<>();

    // Declare a dynamically allocated ArrayList of Strings for the dictionary.
    // The dictionary can hold any number of words.
    ArrayList<String> dictionary;
    private File dictionaryFile;

    // Constructor
    Dictionary() {
        // Define the instance of the dictionary ArrayList
        dictionary = new ArrayList<String>();
        // Now fill the dictionary array list with words from the dictionary file
        readInDictionaryWords();
    }//end Constructor

    //---------------------------------------------------------------------------------
    // Read in the words to create the dictionary.
    public void readInDictionaryWords() {

//          
        FileReader r = null;
        try {
            r = new FileReader("src/resources/Dictionary.txt");

        } catch (FileNotFoundException ex) {

            System.out.println("*** Error *** \n"
                    + "Your dictionary file has the wrong name or is "
                    + "in the wrong directory.  \n"
                    + "Aborting program...\n\n");
            System.exit(-1);    // Terminate the program

        }
        Scanner f = new Scanner(r);

        while (f.hasNextLine()) {
            dictionary.add(f.nextLine());
        }

    }//end createDictionary()

    //---------------------------------------------------------------------------------
    // Allow looking up a word in dictionary, returning a value of true or false
    public boolean wordExists(String wordToLookup) {
        if (dictionary.contains(wordToLookup.toUpperCase())) {
            return true;    // words was found in dictionary
        } else {
            return false;   // word was not found in dictionary    
        }
    }//end wordExists

    //---------------------------------------------------------------------------------
    // return number of words in dictionary
    public int numberOfWordsInDictionary() {
        return dictionary.size();
    }

    //---------------------------------------------------------------------------------
    // return word at a particular position in dictionary
    public String wordAtIndex(int index) {
        return dictionary.get(index);
    }

    public int indexOfWord(String wordToLookup) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equalsIgnoreCase(wordToLookup)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList getDictionary() {
        return dictionary;
    }

    //returns an array of words that does not contain the used letters
    //the words will be of a certain length only
    public ArrayList getArrayNoMatch(ArrayList letters, int len) { 
        for (int d = 0; d < dictionary.size(); d++) { //This loop goes through every word in the dictionary 
            boolean match = false; //This boolean sets match to false. Match is used to determine whether the letters in the letters list are found in the conatained word in the dictionary
            if (dictionary.get(d).length() == len) { //This checks if the length of the word in a certain position in the dictionary is equal the wanted length
                for (int w = 0; w < dictionary.get(d).length(); w++) { //This loop goes through the length of the word, which would be every letter in the word
                    for (int j = 0; j < letters.size(); j++) { //This loops goes through all the letters in the letters array. 
                        if (letters.get(j).equals((dictionary.get(d)).substring(w, w + 1))) { //This checks whether the letter from the letter array matches the letter in the word from the dictionary
                            match = true; //This sets match to true, since a letter from the letter list was found in the word from the dictionary
                            break; //Breaks out of loop since match is found. There is no point in checking the rest of the letters.
                        }
                    }
                    if(match==true){ //checks if a match was found 
                        break;//Breaks out of loop since match is found. There is no point in checking the rest of the word
                    }
                }
                if (match == false) { //if match is not found
                    possibleWords.add(dictionary.get(d)); //adds the word that a match was not found to a possible words list.
                }
            }
        }

        return possibleWords;//returns all the words that are possible
    }
    
    public ArrayList getArrayNoMat(ArrayList letters, int len) {
        for (int d = 0; d < dictionary.size(); d++) { //This loop goes through every word in the dictionary 
            boolean match = false; //This boolean sets match to false. Match is used to determine whether the letters in the letters list are found in the conatained word in the dictionary
            if (dictionary.get(d).length() == len) { //This checks if the length of the word in a certain position in the dictionary is equal the wanted length
                for (int w = 0; w < dictionary.get(d).length(); w++) { //This loop goes through the length of the word, which would be every letter in the word
                        if (letters.contains((dictionary.get(d)).substring(w, w + 1))) { //It checks if the leeters are found in the word
                            match = true; //This sets match to true, since a letter from the letter list was found in the word from the dictionary
                            break; //Breaks out of loop since match is found. There is no point in checking the rest of the letters.
                        }
                    if(match==true){ //checks if a match was found 
                        break;//Breaks out of loop since match is found. There is no point in checking the rest of the word
                    }
                }
                if (match == false) { //if match is not found
                    possibleWords.add(dictionary.get(d)); //adds the word that a match was not found to a possible words list.
                }
            }
        }

        return possibleWords;//returns all the words that are possible
    }


}//end class Dictionary
