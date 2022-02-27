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
public class Person {
    private int age;
    private int gender;
    private int status;
    private int family;
    private boolean isCriminal=false;
    private boolean hasPowers=false;
    private int criminalChance;
    private int criminal;
    private int powerChance;
    private int criminalOrganization;
    private boolean criminalOrganize=false;
    
    
    public Person(int personGender, int personAge, int familyStatus, int personFamily){
        age=personAge;
        gender=personGender;
        status=familyStatus;
        family=personFamily;
    }
    
    public void criminal(){
        if (gender==1){ //male. The higher the variable the better, since the higher the number the lower the chnace the person is a criminal
            if (age<15){
                criminalChance=50;
                statusAndFamily();
            }
            if (age>=15 && age<30){
                criminalChance=40;
                statusAndFamily();
            }
            
            if(age>=30 && age<=45){
                criminalChance=45;
                statusAndFamily();
            }
            if (age>45){
                criminalChance=60;
                statusAndFamily();
            }
        }
        if (gender==2){//female
            if (age<15){
                criminalChance=100;
                statusAndFamily();
            }
            if (age>=15 && age<30){
                criminalChance=90;
                statusAndFamily();
            }
            
            if(age>=30 && age<=45){
                criminalChance=95;
                statusAndFamily();
            }
            if (age>45){
                criminalChance=110;
                statusAndFamily();
            }
        }
    }
    
    private void statusAndFamily(){
        if (status==1){
            if (family==3){
                 criminalChance=criminalChance-20; 
            }
            else{
                criminalChance=criminalChance-10; 
            }
            
        }
        if (status==2 || status==3){
            if (family==3){
               criminalChance=criminalChance-15;  
            }
             else{
                criminalChance=criminalChance-5;
                
            }
        }
        if (status==4){
            if (family==3){
                    criminalChance=criminalChance-15;                      
                }
             else{
                criminalChance=criminalChance-5;  
            }
         }
    }
    
    public boolean getCriminal(){
        criminal=(int)(Math.random()*criminalChance)+1;
       //System.out.println(criminalChance);
       
        if (criminal<5){
            isCriminal=true;
            return isCriminal;
        }
        else{
            isCriminal=false;
            return isCriminal;
        }  
    }
    
    public boolean getCriminalOrganization(){
        if (isCriminal==true){
            criminalOrganization= (int)(Math.random()*100)+1;
            if(criminalOrganization>75){
               criminalOrganize=true;
            }
        }
        return criminalOrganize;
    }
    
    public boolean getPowers(){
        powerChance=(int)(Math.random()*10)+1;
        if (powerChance==1){
            hasPowers=true;
            return hasPowers;
        }
        else{
            hasPowers=false;
            return hasPowers;
        }
    }    
}
