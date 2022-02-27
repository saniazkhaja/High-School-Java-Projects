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
public class GoodSide {
    private int righteousness;
    private int wisdom;
    private int humane;
    private int trustWorthiness;
    private int control;
    private int experience;
    private int strength;
    private int chanceOfLiving;
    private int choice;
    
    
    
    public GoodSide(int userExperience){
      experience=userExperience;  
    }
    public GoodSide(int userChoice, int userStrength, int chanceOfLivingUser, int controlUser,int experienceUser, int humaneUser,int righteousnessUser,int trustWorthinessUser,int wisdomUser){
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
    public GoodSide(){
        
    }
    public String smallMission(){
        return "You see someone is vandalyzing a property. \n"+
                "What will you do?\n"+
                "a. Stop them and try to explain what they are doing is wrong\n"+
                "b. Walk away as if you did not see what was happening \n"+
                "c. Call the police and get them jailed \n";
        
    }
    public String mediumMission(){
        return "You see a guy running with what looks like a lot of jewelry and money. \n"+
                "What will you do?\n"+
                "a. Wait and see what happens. If someone else reacts, you will help. \n"+
                "b. Run behind the person and stop him. Call the police and make sure that \n"+
                "the rightful owners of the stuff get thier things back.\n"+
                "c. Follow him and call the police. Let the police handle the rest.\n";
    }
    public String bigMission(){
        return "You are on a train with a fellow group member. An annoucement is made that \n"
                + "a bomb is going to go off on the train. The person with trigger is being"+
                "controlled by someone else."+
                "What will you do?\n"+
                "a. One member will try to get the people to safety while you try to \n"+
                "stop the person and get them out of their controlled state.\n"+
                "b. You and your group member will first make sure the people are safe \n"+
                "and then deal with the bomb\n"+
                "c. You will try to stop the person with the trigger by any means necessary \n";
    }
    public void choiceSmall(){
        if(choice==1){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving+5;
            control=control+5;
            experience=experience+5;
            humane=humane+10;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+15;
            wisdom=wisdom+5; 
        }
        if(choice==2){
            strength=strength+1;
            chanceOfLiving=chanceOfLiving+1;
            control=control+1;
            experience=experience+1;
            humane=humane+1;
            righteousness=righteousness-5;
            trustWorthiness=trustWorthiness-3;
            wisdom=wisdom+1;   
        }
        if(choice==3){
            strength=strength+5;
            chanceOfLiving=chanceOfLiving+10;
            control=control+3;
            experience=experience+10;
            humane=humane+5;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+10;
            wisdom=wisdom+10;            
        } 
    }
    public void choiceMedium(){
       if(choice==1){
            strength=strength+2;
            chanceOfLiving=chanceOfLiving+1;
            control=control+1;
            experience=experience+5;
            humane=humane+1;
            righteousness=righteousness+1;
            trustWorthiness=trustWorthiness+1;
            wisdom=wisdom+1; 
        }
        if(choice==2){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving+15;
            control=control+10;
            experience=experience+15;
            humane=humane+10;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+15;
            wisdom=wisdom+15;   
        }
        if(choice==3){
            strength=strength+5;
            chanceOfLiving=chanceOfLiving+10;
            control=control+5;
            experience=experience+10;
            humane=humane+5;
            righteousness=righteousness+5;
            trustWorthiness=trustWorthiness+5;
            wisdom=wisdom+5;            
        } 
    }
    public void choiceBig(){
        if(choice==1){
            strength=strength+15;
            chanceOfLiving=chanceOfLiving-15;
            control=control+10;
            experience=experience+15;
            humane=humane+10;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+10;
            wisdom=wisdom+10; 
        }
        if(choice==2){
            strength=strength+10;
            chanceOfLiving=chanceOfLiving-15;
            control=control+10;
            experience=experience+15;
            humane=humane+15;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+10;
            wisdom=wisdom+5;   
        }
        if(choice==3){
            strength=strength+5;
            chanceOfLiving=chanceOfLiving-10;
            control=control+5;
            experience=experience+10;
            humane=humane+5;
            righteousness=righteousness+10;
            trustWorthiness=trustWorthiness+5;
            wisdom=wisdom+5;            
        } 
    }
    public String getGainExperience(){
        experience=experience+5;
        int option=(int)(Math.random()*2)+1;
        if(option==1){
             return "The saftey of the people is very important.\n"
                + " Stopping crime is important, but the safety of the people and city comes first.\n"
                +"Teamwork is also important. Try not leave anyone to die.\n"
                +"Try to do the right and more humane thing.\n"
                     + "If you understand what is required of you, click one of the option buttons."
                     ;
        }
        else{
             return "Your group members go out to catch a criminal.\n"
                + "The criminal has a person as hostage. One member saves the person,\n"
                +"and moves them to a safe spot, while the other members fight the criminal.\n"
                +"They catch the criminal and hand them to the police. While fighting they\n"
                +"tried to cause the least destruction.\n" 
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
