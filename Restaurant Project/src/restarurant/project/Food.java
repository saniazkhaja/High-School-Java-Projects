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
public class Food {
    private double foodPrice;
    private double foodCostMake;
    private double profit;
    private double overallProfit=0;
    private int pizzaBought=0;
    private int friesBought=0;
    private int iceCreamBought=0;
    double seniorDiscount;
    int bought;
    double theTip;
    double costToMake;
    double wastedFood;
    double foodNotAvailable;
    //making variables global to be used throughtout class and to get values from Controller
    
    public Food(double price, double costMake, int waste, int notAvailable){
        foodPrice=price; 
        foodCostMake=costMake;
        wastedFood=waste;
        foodNotAvailable=notAvailable;
    }
    public String foodAvailibility(){ //checks to see if food is available
        if(foodNotAvailable==100){
            return "Food not Available";
        }
        else{
            return "Food Available";
        }
    }
    
    public void foodWasted(){//for food that may have been dropped or made wrong
        if(wastedFood==25){
            overallProfit=overallProfit-foodPrice-foodCostMake;
        }
    }
    
    public int birthday(int birthday, int date){//checks if it is the persons birthday so they can get a free icecream
        if (birthday==date){
            iceCreamBought=iceCreamBought+1;
        }
        return iceCreamBought;   
    }
    
    
    public double tip(double tip){//adds to yip to overall profit
        theTip=tip;
        overallProfit=overallProfit+tip;
        return theTip;
        
    }
    
    
    public void getProfit(int hunger, String holiday){//checks for holiday to get discount and then calculates profit from that one food
        overallProfit=0;
        bought=0;
        bought=bought+hunger;
        double order= Math.round((foodPrice*hunger)*100.00)/100.00; //multilies cost and hunger to see how much must be paid by the costumer
        costToMake= Math.round((foodCostMake*hunger)*100.00)/100.00;//determines how the food cost to make
        if(holiday.equals("true")){
            profit=Math.round((order-costToMake)*100.00)/100.00;
            profit=Math.round(((profit)-(profit*0.05))*100.00)/100.00;
        }
        else{
            profit=Math.round((order-costToMake)*100.00)/100.00; //finds profit for the one order
        }
        
    }
    
    public double discountAge(int age){//discount for senior people. 
        if (age>=65){
            profit=Math.round(((profit)-(profit*0.10))*100.00)/100.00;
            overallProfit=overallProfit+profit;
            return profit;
        }
        else{
            overallProfit=overallProfit+profit;
            return Math.round(profit*100.00)/100.00;
        }
       
    }
    
    public void cancelOrder(int cancel){//this is if someone does not pay for thier food or cancels order. this is the biggest loss for the company since a lot could have been bought
        if (cancel==50){
            overallProfit=overallProfit-profit-costToMake-theTip;
        }        
    }
    
    public int boughtAmount(){//how much of the item was bought
        return bought;
    }
    public double overallProfit(){//what the profit is including discounts, dropped food and canceled order
        return overallProfit;
    
}
    
           
}
