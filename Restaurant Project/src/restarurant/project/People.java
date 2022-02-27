/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restarurant.project;

/**
 *
 * @author choudhary1645
 */
public class People { 
    private int hungerLevel;
    private int foodPreference;
    private int foodTimes;
    private int personAge;
    private int personBirthday;
    private int personTip;
    private int orderCancel;
    private int date;
    private int personMood;
    private int birthdayLiar;
    private int personSick;
    private int holidays;
    //getting information from controller
    public People(int hunger, int preference, int timeOfDay, int age, int birthday, int tip, int cancelOrder, int day, int mood, int liar, int sick, int holiday){
        hungerLevel=hunger;
        foodPreference=preference;
        foodTimes=timeOfDay;
        personAge=age;
        personBirthday=birthday;
        personTip=tip;
        orderCancel=cancelOrder;
        date=day;
        personMood=mood;
        birthdayLiar=liar;
        personSick=sick;
        holidays=holiday;
    }
    public int availableBuy(String availability){ //preference is there if food is available.
        if (availability.equals("Food not Available")){
            foodPreference=0;
            return foodPreference;   
        }
        else{
            return foodPreference;   
        }
            
    }

    public int getHunger(){//uses multiple factors like holiday seasona and sicknesss to determine hunger
        hungerLevel=hungerLevel*foodTimes;
        if (personAge>=65){//age determines how often people get sick
            if(personSick==1 || personSick==2 || personSick==3 || personSick==4){
                if (hungerLevel>3){
                    hungerLevel=hungerLevel-2;
                }
            }            
        }
        else{
            if(personSick==1 || personSick==2){
                if (hungerLevel>3){
                    hungerLevel=hungerLevel-2;
                }
            }   
        }
        if(holidays<=10){//10 holidays a year
            hungerLevel=hungerLevel+2;
        }
        return hungerLevel;
    }   
    public int getFoodType(){ //if sick then person will not have iceCream
        if (personAge>=65){//age determines how often a perosn gets sick
            if(personSick==1 || personSick==2 || personSick==3 || personSick==4){
                foodPreference=(int)(Math.random()*2)+1;
            }            
        }
        else{
            if(personSick==1 || personSick==2){
                foodPreference=(int)(Math.random()*2)+1;
            }   
        }
        return foodPreference;
    }
    public int getAge(){//gets age for senior discounts
        return personAge;
    }
    public int getBirthday(){//gets the persons birthday
        if (birthdayLiar==100){//checks to if person lied about there birthday to get free treat
            personBirthday=date;
        }
        return personBirthday;
    }
    public int getTip(){//the mood of the person determines how much of a tip they will give from what they would have given
        if (personMood==1){
            personTip=personTip+1;
        }
        else if(personMood==2){
            personTip=personTip+0;
        }
        else{
            personTip=0;
        }
        return personTip;
    }
    public int getCancelOrder(){ //used to detrmine if order is canceled.
        return orderCancel;
    }
    public int getDate(){//checks for the date.
        if (date>365){
            date=1;
        }
        return date;
    }
    
    public String getHolidays(){//checks to see if it is a holiday
        if (holidays<=10){
            return "true";
        }
        else{
            return "false";
        }
    }
    
}
