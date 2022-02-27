/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorryproject;

import java.util.ArrayList;

/**
 *
 * @author Sania Khaja
 */
public class CardClass {
    ArrayList<String> regCards= new ArrayList<>();
    ArrayList<String> discardReg= new ArrayList<>();
    
    ArrayList<String> chanceCards= new ArrayList<>();
    ArrayList<String> discardChance= new ArrayList<>();
    
    public void addRegCards(String card){ //setting up cards
        regCards.add(card);
        regCards.add(card);
        regCards.add(card);
        //regCards.add("RC0");
    }
    
    public void addChanceCards(String card){ //setting up chance cards
        chanceCards.add(card);
        chanceCards.add(card);
    }
   
    public String getRegCard(){ 
        if(regCards.size()==0){ //adding cards back to deck if no more cards
            for(int i=0; i<discardReg.size(); i++){
                regCards.add(discardReg.get(i));
            }
            discardReg.clear();
        }
        int randomCard=(int) (Math.random() * (regCards.size() - 1)); 
        discardReg.add(regCards.remove(randomCard)); //getting a random card
        return discardReg.get(discardReg.size() - 1);
    }
    
    public String getChanceCard(){
        if(chanceCards.size()==0){ //adding cards back to deck if no cards in deck
            for(int i=0; i<discardChance.size(); i++){
                chanceCards.add(discardChance.get(i));
            }
            discardChance.clear();
        }
        int randomCard=(int) (Math.random() * (chanceCards.size() - 1)); //getting a random card
        discardChance.add(chanceCards.remove(randomCard));
        return discardChance.get(discardChance.size() - 1);
    }
    public int diceNum(){ //choosing random number
        return (int) (Math.random() * (6))+1;
    }
    
    public void resetCards(){ //resteting decks by clearing it 
        regCards.clear();
        discardReg.clear();
        chanceCards.clear();
        discardChance.clear();
    }
   
}
