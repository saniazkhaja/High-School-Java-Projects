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
public class Powers {
    private int strength;
    private int wisdom;
    private int decisionSkills;
    private int control;
    private int average;
    
    public Powers(int initialStrength){
        strength=initialStrength;
    }
    public int getRandomPeoplePowers(){
        wisdom=(int)(Math.random()*10)+1;
        decisionSkills=(int)(Math.random()*10)+1;
        control=(int)(Math.random()*10)+1;
        average=(wisdom*decisionSkills*control);
        average=(int)(average/10);
        strength=strength+average;
        return strength;
    }
    
    public int getGoodSidePowers(){
        wisdom=(int)(Math.random()*((10-6)+1))+6;
        decisionSkills=(int)(Math.random()*((10-6)+1))+6;
        control=(int)(Math.random()*((10-6)+1))+6;
        average=(wisdom*decisionSkills*control);
        average=(int)(average/10);
        strength=strength+average;
        return strength;  
    }
    
    public int getBadSidePowers(){
        wisdom=(int)(Math.random()*((10-6)+1))+6;
        decisionSkills=(int)(Math.random()*((10-6)+1))+6;
        control=(int)(Math.random()*((10-6)+1))+6;
        average=(wisdom*decisionSkills*control);
        average=(int)(average/10);
        strength=strength+average;
        return strength;
    }
}
