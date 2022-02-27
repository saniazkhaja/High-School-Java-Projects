/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rock.paper.and.scissors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Sania Khaja
 */
public class PlayGame {
     int userInput;
     int computerInput;
     List<Integer> peopleArray;
     List<Integer> computerArray;
     List<Integer> nextValueArray=new ArrayList<>();
     List<Integer> modeList=new ArrayList<>();
     List<Integer> frequency=new ArrayList<>();
     int match=0;
     int mode=0;
     int maxCount=0;
     
     
    
    public PlayGame(int user, int computer, List<Integer>peopleArr,List<Integer>computerArr){
        userInput=user;
        computerInput=computer;
        peopleArray=peopleArr;
        computerArray=computerArr;
    }
    public PlayGame(List<Integer>peopleArr,List<Integer>computerArr){
        peopleArray=peopleArr;
        computerArray=computerArr;
    }
    
    public String easyLevel(){//checking for tie, win and lose
       if (computerInput==userInput){
           return "tie";
       }
       else if (userInput-computerInput%3==1 ){
           return "win";
       }
       else{
           return "lose";
       }
 
    }
    
    public Integer hardLevel(){
            nextValueArray.clear();
            int length;
            int maxFrequencyLength=0;
            int maxFrequency=0;
            for (int z=3;z<peopleArray.size()/2;z++) //goes through each length till half the array
            {//does not make sense to check past half the array since for lengths past that the frequency would be 1
                int frequencyCounter=0;
                length=z;
                for (int i=0;i<computerArray.size()-length;i++){//checking for pattern for entire array except for the last values since they would always match
                match=0;
                for (int j=0;j<length;j++){
                    if (computerArray.get(i+j)==peopleArray.get(peopleArray.size()+j-length)){//checking for match of one element
                        match=match+1;
                        if (match==length){//checks to see if match continues through the length
                            frequencyCounter=frequencyCounter+1;
                        }
                    }
                }
            }
                if(frequencyCounter>=maxFrequency){//if frequency amount is the same or greater, that length will be used
                    maxFrequencyLength=length;//getting the max length with highest frequency
                    maxFrequency=frequencyCounter; //changing max frequency
                    System.out.println("FCounter"+maxFrequency);
                    System.out.println("MaxFLength"+maxFrequencyLength);
                }
            }
            //max length was found. Tryig to find the next value that the player played after this length match
            for (int i=0;i<computerArray.size()-maxFrequencyLength;i++){ //goes throigh computer array
                match=0;
                for (int j=0;j<maxFrequencyLength;j++){
                    if (computerArray.get(i+j)==peopleArray.get(peopleArray.size()+j-maxFrequencyLength)){//checks to for a match
                        match=match+1;
                        if (match==maxFrequencyLength){//checks if matches in row are the same as length
                            System.out.println("Matched values in the array");
                            nextValueArray.add(computerArray.get(i+maxFrequencyLength));//adding the next value after the length match
                            System.out.println("Next value "+nextValueArray);
                        }
                    }
                }
            }

        if (nextValueArray.size()==0){//if there is nothing in the next value array, there was no matches
            modeList.clear();
            return -1;
        }
        else{
            //finding which next vale repeated the most
            for (int y=0; y<nextValueArray.size(); y++ ){
                int value=nextValueArray.get(y); 
                int countMode=0;
                for (int z=0; z<nextValueArray.size(); z++){
                    if (nextValueArray.get(z)==value){ 
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
            }
            if (modeList.size()>1){ //if there is more than one mode, then randomly chooses which move to go with, since there is an equal chance they will go with either move
                int randomIndex = (int)(Math.random() * modeList.size());
                mode=modeList.get(randomIndex);
            }
            modeList.clear();
            nextValueArray.clear();
            if (mode==1){ //returns what the computer should choose to counter the players move
                return 2;
            }
            else if (mode==2){
                return 3;
            }
            else{
                return 1;
            }
            
        }
        
    }
    
}
