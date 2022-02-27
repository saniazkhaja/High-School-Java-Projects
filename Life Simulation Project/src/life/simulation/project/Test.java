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


public class Test {
    private boolean isBad;
    private int righteousness;
    private int wisdom;
    private int humane;
    private int trustWorthiness;
    private int control;
    private int experience;
    private int strength;
    private int chanceOfLiving;
    private int experienceNeeded;
    private int choice;
    private boolean betrayal;
    
    public Test(int userChoice, int startingStrength, boolean side, boolean betray, int chanceOfLivingUser, int controlUser,int experienceUser,int experienceNeededUser, int humaneUser,int righteousnessUser,int trustWorthinessUser,int wisdomUser ){
        isBad=side;
        choice=userChoice;
        strength=startingStrength;
        betrayal=betray;
        chanceOfLiving=chanceOfLivingUser;
        control=controlUser;
        experience=experienceUser;
        experienceNeeded=experienceNeededUser;
        humane=humaneUser;
        righteousness=righteousnessUser;
        trustWorthiness=trustWorthinessUser;
        wisdom=wisdomUser;
    }
    public Test(){
        
    }
    public Test(int userChoice, int startingStrength){
        choice=userChoice;
        strength=startingStrength;
    }
    
    public String goodSideTest(){
        return("You enter a building with your the new good members you have met today.\n"
                + "You find a diamond on the floor."+
                "The good members walk by and do not notice it.\n"+
                "What will you do?\n"+
                "a. Keep the diamond and silently walk out of the building as if nothing happened.\n"+
                "b. Call the members and ask them what to do.\n"+
                "c. Call the members and say that we should find the right owner. \n");
    }
    
    public void goodSideChoice(){
        if(betrayal==true){         
            trustWorthiness=10;
            righteousness=10;
            humane=10;
            experienceNeeded=experienceNeeded+10;
            experience=0;
            chanceOfLiving=400;
        }
        if (choice==1){
            trustWorthiness=trustWorthiness-5;
            righteousness=righteousness-5;
            experienceNeeded=experienceNeeded+10;
            humane=humane-5;
        }
        else if(choice==2){
            trustWorthiness=trustWorthiness+5;
            righteousness=righteousness+5;
            experienceNeeded=experienceNeeded-5;
            humane=humane+5;
        }
        else{
             trustWorthiness=trustWorthiness+10;
             righteousness=righteousness+10;
             experienceNeeded=experienceNeeded-10;
             humane=humane+10;
             
        }
        
    }
    
    public String badSideTest(){
        return("You want to join the bad side. You will have to go on a mission and survive\n"
                + "to become a member. You have to collect an item."+
                "You reach the place and an old \n"+
                "women is betweem you and the item. What will you do?\n"+
                "a. Kill the women to get to the item.\n"+
                "b. Kindly ask the women to move, pleading to her that you need the item.\n"+
                "c. Trick the women into thinking you want to help her and take the item. \n");     
    }
    
    public void badSideChoice(){
        if(betrayal==true){
            trustWorthiness=10;
            righteousness=10;
            humane=10;
            experienceNeeded=experienceNeeded+10;
            experience=0;
            chanceOfLiving=450;
        }
        if (choice==1){
            trustWorthiness=trustWorthiness+5;
            righteousness=righteousness-5;
            experience=experience+5;
            experienceNeeded=experienceNeeded-5;
            humane=humane-5;
        }
        else if(choice==2){
            trustWorthiness=trustWorthiness-5;
            righteousness=righteousness+10;
            experienceNeeded=experienceNeeded+5;
            humane=humane+10;
            experience=experience+5;
            chanceOfLiving=chanceOfLiving-25;
        }
        else{
             trustWorthiness=trustWorthiness+10;
             righteousness=righteousness-5;
             experienceNeeded=experienceNeeded-10;
             humane=humane-5;
             experience=experience+5;
             wisdom=wisdom+10;
        }
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
    public int getExperience(){
        return experience;
    }
    public int getStrength(){
        return strength;
    }
    public int getChanceOfLiving(){
        return chanceOfLiving;
    }
     public int getExperienceNeeded(){
        return experienceNeeded;
    }
 
}
