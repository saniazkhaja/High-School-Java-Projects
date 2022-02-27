/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays.project;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author choudhary1645
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
     List<String> items= new ArrayList<>();
     String[] letters=new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r","s", "t", "u", "v", "w", "x","y","z"};
     
    @FXML
    private void items() {
        String input=JOptionPane.showInputDialog("What item do want to check if it is in the list?");

                if(items.contains(input)){ //checks if item is in array. if it is the says in array
                    System.out.println("The item is already in the list.");
                    System.out.println(items);
                }
                else{ //if item is not in array adds the item to array
                    System.out.println("Item is not in the list");
                    items.add(input);
                    System.out.println(items);                 
        }
        
    }
    
    @FXML
    private void wrapAround(){
        String startLetter=JOptionPane.showInputDialog("What letter do you want to start with in the alphabet?");
        
        for(int i=0; i<26;i++){ //goes through each letter
            if(letters[i].equals(startLetter)){ //checks where the users input starts
                for(int j=i; j<26; j++){  //starts at where the user inputs letter is
                    System.out.print(letters[j]+" "); //print letters from users starting point
                    if (j==25){ //if the loop has reached the last letter of the array, will loop around to the beggining
                        for (int g=0;g<i; g++){ //loops around and prints rest of the letters before the input letter
                             System.out.print(letters[g]+" ");
                        }
                    }
                }
            }
        }      
        
    }
    
    @FXML
    private void numberCalculations(){
        double add=0.00;
        double mean=0.00;
        int mode=0;
        int maxCount=0;
        int countMode=0; //counts how many of the same number there is
        List<Integer> modeList=new ArrayList<>(); //an array to the modes. helps get more han one mode
        List<Integer> randomNumbers= new ArrayList<>();
        List<Integer> frequency= new ArrayList<>();
        
        for (int i = 0; i < 10; i++) { //picks random numbers and adds that to an array
            int randomNum=(int)(1+Math.random()*10);
            randomNumbers.add(randomNum);
        }
        System.out.println("Original Array: "+randomNumbers);
        for (int j=0; j<randomNumbers.size(); j++){ //adds all the numbers in the array together
            add=add+randomNumbers.get(j);
        }
        mean=(add/randomNumbers.size()); //divides by how many numbers in array to get mean
        System.out.println("Mean: "+mean);
        

        for (int y=0; y<randomNumbers.size(); y++){//goes through each number in array
            int value=randomNumbers.get(y); 
            countMode=0;
            for (int z=0; z<randomNumbers.size(); z++){ //checks the one number compared to he rest of the numbers in the array
                if (randomNumbers.get(z)==value){ //checks for the same number
                    countMode=countMode+1;
                }
                
                if (countMode==maxCount){ //assumes that number is mode if reaches the same value as the max mode
                    if (!modeList.contains(value)){
                        modeList.add(value); //adds to array for the mode numbers
                    } 
                }
         
                else if (countMode>maxCount){ 
                    modeList.clear(); //clears array since now the count mode is geater than max count
                    mode=value; //now the mode is the value
                    maxCount=countMode; //new max count
                                     
                }     
        }
            
            frequency.add(countMode); //before moving to next number in the array, it adds the count of that number
   
    }
        System.out.println("Mode List: "+modeList);
        System.out.println("Mode Occurance:"+ maxCount); 
        System.out.println("Frequency: "+frequency);//how many times that number appears in the array
        
    }
    
    @FXML
    private void numberSortAddSub(){
        int[] randomNumbers=new int[10];
        int sum=0;
        boolean add=true;
        
        for (int i=0; i<randomNumbers.length; i++){ //used to generate random numbers
            randomNumbers[i]=(int)(1+Math.random()*10);
        }
        System.out.println("Original Array: "+Arrays.toString(randomNumbers));
        
        for (int k : randomNumbers) { //does addition and subtraction for each item in the array
            if (!add){ //uses boolean to switch between addition and subtraction
                sum=sum-k; 
                add=true;
            }
            else{ //sum starts with 0, so it adds 0 plus the first number in array then switches the sign
                sum=sum+k;
                add=false;
            }
            
        }  
        System.out.println("Alternating signs sum: "+sum);
        
        for (int j=0; j<randomNumbers.length/2; j++){//to reverse array. Goes to half the array and swithes values
            int temp=randomNumbers[j]; //sets temp to what the value at that point is in the array
            randomNumbers[j]=randomNumbers[randomNumbers.length-1-j]; //sets the value to the corressponding value from the other half of the array
            randomNumbers[randomNumbers.length-1-j]=temp;           
        }
        System.out.println("Reverse Array: "+ Arrays.toString(randomNumbers));
        
        
        for (int a=0; a<randomNumbers.length; a++){ //orders numbers from least to greatest to help find the min and max
            for (int b=a+1; b<randomNumbers.length; b++){ //compares to the value to the value next to it
                if (randomNumbers[a]>randomNumbers[b]){ //switches value if the value is greater than the value ahead of it
                    int temp=randomNumbers[a];
                    randomNumbers[a]= randomNumbers[b];
                    randomNumbers[b]=temp;
                }
            }
            
        }
        System.out.println("Min to Max: "+ Arrays.toString(randomNumbers));
        System.out.println("Min:"+randomNumbers[0]); //position 0 has the min value
        System.out.println("Max:"+randomNumbers[randomNumbers.length-1]);//the last postion would have th max value
    }
    
    @FXML
    private void lettersShuffle(){
        List<String> lettersShuffle= new ArrayList<>(Arrays.asList(letters)); //this array is a shuffle with swapping values
        List<String> lettersArray=lettersShuffle; //this keeps the original letters so that it can be shuffled
        List<String> lettersAdd= new ArrayList<>(); //this is for adding values to new array 

        for(int i=0;i<10000; i++){ //swaps values by randomly choosing two numbers and swapping them
            int random1=(int)(Math.random()*26); //randomly choosing position to swap
            int random2=(int)(Math.random()*26);
            String temp=lettersShuffle.get(random1); //gets the value of the random position
            lettersShuffle.set(random1,lettersShuffle.get(random2)); // swaps positions 
            lettersShuffle.set(random2,temp); 
 
        }
        System.out.println("Swapping Shuffle Array: "+lettersShuffle);
        
        for (int j=0; j<26;j++ ){ //adds a random letter to new array to shuffle
            int randomNum=(int)(Math.random()*lettersArray.size()); //randomly chooses a value 
            lettersAdd.add(lettersArray.remove(randomNum));  //adds removed value to new array
        }
        System.out.println("Adding values to new Array: "+lettersAdd);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
