/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.simulation.project;

/**
 *
 * @author choudhary1645
 */
public class BadSide {
    private int righteousness;
    private int wisdom;
    private int humane;
    private int trustWorthiness;
    private int control;
    private int experience;
    private int strength;
    private int chanceOfLiving;
    private int choice;
    
    public BadSide(int userExperience){
      experience=userExperience;  
    }
    public BadSide(int userChoice, int userStrength, int chanceOfLivingUser, int controlUser,int experienceUser, int humaneUser,int righteousnessUser,int trustWorthinessUser,int wisdomUser){
        choice=userChoice;
        strength=userStrength;
        chanceOfLiving=chanceOfLivingUser;
        control=controlUser;
        experience=experienceUser;
        humane=humaneUser;
        righteousness=righteousnessUser;
        trustWorthiness=trustWorthinessUser;
        wisdom=wisdomUser;
    }
    public BadSide(){
        
    }
    public String smallMission(){
        return "You are on a mission to kill a certain person.\n"
                + "This person is innocent, but knows too much information "+
                "regarding the bad side.\n"+
                "What will you do?\n"+
                "a. Convince the person to hide away and leave the city.\n"+
                "b. Kill them in a secluded area with your power and hide the evidence.\n"+
                "c. Use manupulation on the person so they will kill themselves. \n";
    }
    public String mediumMission(){
        return "You are on a mission to rob a bank.\n"+
                "What will you do?\n"+
                "a. Use your powers to force or trick the person to give you the money.\n"+
                "b. Take people hostage and make demands.\n"+
                "c. Get the information and use your power to kill to leave no evidence \n";
    }
    public String bigMission(){
        return "You are on mission to annihilate an enemy organization with a .\n"
                + "fellow partner. This is a very dangerous mission."+
                "What will you do?\n"+
                "a. Combine your's and your partners strength and work together \n"+
                "to survive and to sucessfully complete the mission.\n"+
                "b. Work alone and desregard your partner. Your feel like your partner \n"+
                "will be a burden.\n"+
                "c. Use your partner only when necessary. Control them to do what you want. \n";
    }
    public void choiceSmall(){
        if(choice==1){
            strength=strength+1;
            chanceOfLiving=chanceOfLiving-10;
            control=control+1;
            experience=experience+5;
            humane=humane+10;
            righteousness=righteousness+10;
            if(trustWorthiness>=10){
                trustWorthiness=trustWorthiness-5;
            }
            else{
                trustWorthiness=trustWorthiness-1;
            }
            if(wisdom>=10){
                wisdom=wisdom-5; 
            }
            else{
               wisdom=wisdom-1; 
            }
 
        }
        if(choice==2){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving+20;
            control=control+10;
            experience=experience+10;
            if(humane>=10){
                humane=humane-5;
            }
            else{
               humane=humane-1; 
            }
            if(righteousness>=10){
                righteousness=righteousness-5;
            }
            else{
                righteousness=righteousness-1;
            }          
            trustWorthiness=trustWorthiness+15;
            wisdom=wisdom+10;   
        }
        if(choice==3){
            strength=strength+1;
            chanceOfLiving=chanceOfLiving+10;
            control=control+10;
            experience=experience+1;
            if(humane>=10){
                humane=humane-5;
            }
            else{
               humane=humane-1; 
            }
            if(righteousness>=10){
                righteousness=righteousness-5;
            }
            else{
                righteousness=righteousness-1;
            }   
            trustWorthiness=trustWorthiness+10;
            wisdom=wisdom+15;            
        } 
    }
    public void choiceMedium(){
        if(choice==1){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving+15;
            control=control+10;
            experience=experience+10;
            if(humane>=10){
                humane=humane-5;
            }
            else{
               humane=humane-1; 
            }
            if(righteousness>=10){
                righteousness=righteousness-5;
            }
            else{
                righteousness=righteousness-1;
            }   
            trustWorthiness=trustWorthiness+5;
            wisdom=wisdom+10; 
        }
        if(choice==2){
            strength=strength+5;
            chanceOfLiving=chanceOfLiving+5;
            control=control+5;
            experience=experience+5;
            humane=humane+5;
            righteousness=righteousness+5;
            trustWorthiness=trustWorthiness+1;
            wisdom=wisdom+1;   
        }
        if(choice==3){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving+15;
            control=control+10;
            experience=experience+10;
            humane=humane+1;
            if(humane>=10){
                humane=humane-5;
            }
            else{
               humane=humane-1; 
            }
            if(righteousness>=10){
                righteousness=righteousness-5;
            }
            else{
                righteousness=righteousness-1;
            }   
            wisdom=wisdom+10;            
        } 
    }
    public void choiceBig(){
        if(choice==1){
            strength=strength+20;
            chanceOfLiving=chanceOfLiving-20;
            control=control+15;
            experience=experience+10;
            humane=humane-1;
            righteousness=righteousness-1;
            trustWorthiness=trustWorthiness+20;
            wisdom=wisdom+20; 
        }
        if(choice==2){
            strength=strength+5;
            chanceOfLiving=chanceOfLiving-30;
            control=control+10;
            experience=experience+5;
            humane=humane-1;
            righteousness=righteousness-1;
            trustWorthiness=trustWorthiness-10;
            wisdom=wisdom+5;   
        }
        if(choice==3){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving-15;
            control=control+10;
            experience=experience+10;
            humane=humane-1;
            righteousness=righteousness-1;
            trustWorthiness=trustWorthiness+10;
            wisdom=wisdom+10;            
        } 
    }
    public String getGainExperience(){
        experience=experience+5;
        int option=(int)(Math.random()*2)+1;
        if(option==1){
             return "Completing the mission is the most important, no matter the consequences.\n"
                + "Being smart will help you survive. Being too righteous and humane,\n"
                +"will be considered as a negative thing. Teamwork is also important, if you \n"
                +"want to survive. Loyalty is important. Your strength matters more in this group.\n"
                     +"Being more cruel and emotionless will help you.\n"
                     + "If you understand what is required of you, click one of the option buttons."
                     ;
        }
        else{
             return "Some of group members has to go out to complete a mission.\n"
                + "They have to kill the police, to get some items. \n"
                +"Without any hesitance, they kill the police and leave no trails behind.\n"
                +"They quickly collect what they needed and walk out as if nothing happened.\n"
                +"They show no emotion or regret.\n" 
                + "If you understand what is required of you, click one of the option buttons."    ;
             
        }
    }
    public int getExperience(){
        return experience;
    }
    public int getRighteousness(){
        return righteousness;
    }
    public int getWisdom(){
        return  wisdom;
    }
    public int getHumane(){
        return humane;
    }
    public int getTrustWorthiness(){
        return trustWorthiness;
    }
    public int getControl(){
        return control;
    }
    public int getStrength(){
        return strength;
    }
    public int getChanceOfLiving(){
        return chanceOfLiving;
    }
     
    
}
