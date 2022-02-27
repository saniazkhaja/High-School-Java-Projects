/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restarurant.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author choudhary1645
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label labelFriesBought,labelIceCreamBought, labelPizzaBought, labelProfit ;
    People people1;
    People people2;
    People people3;
    Food pizza;
    Food fries;
    Food iceCream;
    private double overallProfit;
    private int pizzaBought;
    private int friesBought;
    private int iceCreamBought;
    private double overallTip;
    private double total;
    private int holiday;
    private int theDay=(int)(Math.random()*365)+1;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        theDay=theDay+1;
        holiday=(int)(Math.random()*365)+1;
        
        people1=new People((int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*100)+15, (int)(Math.random()*365)+1, (int)(Math.random()*3), (int)(Math.random()*50)+1,theDay,(int)(Math.random()*3)+1,(int)(Math.random()*100)+1, (int)(Math.random()*365)+1, holiday);
        people2=new People((int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*100)+15, (int)(Math.random()*365)+1, (int)(Math.random()*3), (int)(Math.random()*50)+1, theDay,(int)(Math.random()*3)+1, (int)(Math.random()*100)+1, (int)(Math.random()*365)+1, holiday);
        people3=new People((int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*3)+1, (int)(Math.random()*100)+15, (int)(Math.random()*365)+1, (int)(Math.random()*3), (int)(Math.random()*50)+1, theDay,(int)(Math.random()*3)+1, (int)(Math.random()*100)+1, (int)(Math.random()*365)+1, holiday);
      
        pizza=new Food(2.10,0.45,(int)(Math.random()*25)+1,(int)(Math.random()*100)+1 );
        fries=new Food(1.50,0.30,(int)(Math.random()*25)+1, (int)(Math.random()*100)+1);
        iceCream=new Food(1.25,0.50, (int)(Math.random()*25)+1, (int)(Math.random()*100)+1);
        
        pizzaBought=0;
        friesBought=0;
        iceCreamBought=0;
        total=0;
        labelPizzaBought.setText("");
        labelFriesBought.setText("");
        labelIceCreamBought.setText("");
        labelProfit.setText("");
        
        if (people1.getFoodType()==1 &&people1.availableBuy(pizza.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people1.getFoodType()==2 &&people1.availableBuy(fries.foodAvailibility())==0){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people1.getFoodType()==3 &&people1.availableBuy(iceCream.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        
        
        if (people1.availableBuy(pizza.foodAvailibility())==1){
            pizza.getProfit(people1.getHunger(), people1.getHolidays());
            pizza.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people1.getBirthday());
            System.out.println("Tip:"+pizza.tip(people1.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people1.getBirthday(), people1.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
            
            System.out.println("Age: "+people1.getAge());
            System.out.println("Profit from item:"+pizza.discountAge(people1.getAge()));
            pizza.cancelOrder(people1.getCancelOrder());
            pizzaBought=pizzaBought+pizza.boughtAmount();
            total=total+pizza.overallProfit();
        }
        
        
        if (people1.availableBuy(fries.foodAvailibility())==2){             
            fries.getProfit(people1.getHunger(), people1.getHolidays());
            fries.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people1.getBirthday());
            System.out.println("Tip:"+fries.tip(people1.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people1.getBirthday(), people1.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
      
            System.out.println("Age: "+people1.getAge());
            System.out.println("Profit from item:"+fries.discountAge(people1.getAge()));
            fries.cancelOrder(people1.getCancelOrder());
            friesBought=friesBought+fries.boughtAmount();
            total=total+fries.overallProfit();
            
        }
        
        if (people1.availableBuy(iceCream.foodAvailibility())==3){
            iceCream.getProfit(people1.getHunger(), people1.getHolidays());
            iceCream.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people1.getBirthday());
            System.out.println("Tip:"+iceCream.tip(people1.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people1.getBirthday(), people1.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
     
            System.out.println("Age: "+people1.getAge());          
            System.out.println("Profit from item:"+iceCream.discountAge(people1.getAge()));
            iceCream.cancelOrder(people1.getCancelOrder());
            iceCreamBought=iceCreamBought+iceCream.boughtAmount();
            total=total+iceCream.overallProfit();
        }

        System.out.println("Pizza: " +pizzaBought);
        System.out.println("Fries: " +friesBought);
        System.out.println("IceCream: " +iceCreamBought);
        System.out.println("Total: "+total);
        
        
        //new Person
        if (people2.getFoodType()==1 &&people2.availableBuy(pizza.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people2.getFoodType()==2 &&people2.availableBuy(fries.foodAvailibility())==0){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people2.getFoodType()==3 &&people2.availableBuy(iceCream.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        
        
        if (people2.availableBuy(pizza.foodAvailibility())==1){
            pizza.getProfit(people2.getHunger(), people2.getHolidays());
            pizza.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people2.getBirthday());
            System.out.println("Tip:"+pizza.tip(people2.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people2.getBirthday(), people2.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
            
            System.out.println("Age: "+people2.getAge());
            System.out.println("Profit from item:"+pizza.discountAge(people2.getAge()));
            pizza.cancelOrder(people2.getCancelOrder());
            pizzaBought=pizzaBought+pizza.boughtAmount();
            total=total+pizza.overallProfit();
        }
        
        
        if (people2.availableBuy(fries.foodAvailibility())==2){             
            fries.getProfit(people2.getHunger(), people2.getHolidays());
            fries.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people2.getBirthday());
            System.out.println("Tip:"+fries.tip(people2.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people2.getBirthday(), people2.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
      
            System.out.println("Age: "+people2.getAge());
            System.out.println("Profit from item:"+fries.discountAge(people2.getAge()));
            fries.cancelOrder(people2.getCancelOrder());
            friesBought=friesBought+fries.boughtAmount();
            total=total+fries.overallProfit();
            
        }
        
        if (people2.availableBuy(iceCream.foodAvailibility())==3){
            iceCream.getProfit(people2.getHunger(), people2.getHolidays());
            iceCream.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people2.getBirthday());
            System.out.println("Tip:"+iceCream.tip(people2.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people2.getBirthday(), people2.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
     
            System.out.println("Age: "+people2.getAge());          
            System.out.println("Profit from item:"+iceCream.discountAge(people2.getAge()));
            iceCream.cancelOrder(people2.getCancelOrder());
            iceCreamBought=iceCreamBought+iceCream.boughtAmount();
            total=total+iceCream.overallProfit();
        }

        System.out.println("Pizza: " +pizzaBought);
        System.out.println("Fries: " +friesBought);
        System.out.println("IceCream: " +iceCreamBought);
        System.out.println("Total: "+total);
        
        //new Person
        if (people3.getFoodType()==1 &&people3.availableBuy(pizza.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people3.getFoodType()==2 &&people3.availableBuy(fries.foodAvailibility())==0){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        if (people3.getFoodType()==3 &&people3.availableBuy(iceCream.foodAvailibility())==0 ){
            System.out.println("No Order, since the item they wanted is not there.");
        }
        
        
        if (people3.availableBuy(pizza.foodAvailibility())==1){
            pizza.getProfit(people3.getHunger(), people3.getHolidays());
            pizza.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people3.getBirthday());
            System.out.println("Tip:"+pizza.tip(people3.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people3.getBirthday(), people3.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
            
            System.out.println("Age: "+people3.getAge());
            System.out.println("Profit from item:"+pizza.discountAge(people3.getAge()));
            pizza.cancelOrder(people3.getCancelOrder());
            pizzaBought=pizzaBought+pizza.boughtAmount();
            total=total+pizza.overallProfit();
        }
        
        
        if (people3.availableBuy(fries.foodAvailibility())==2){             
            fries.getProfit(people3.getHunger(), people3.getHolidays());
            fries.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people3.getBirthday());
            System.out.println("Tip:"+fries.tip(people3.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people3.getBirthday(), people3.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
      
            System.out.println("Age: "+people3.getAge());
            System.out.println("Profit from item:"+fries.discountAge(people3.getAge()));
            fries.cancelOrder(people1.getCancelOrder());
            friesBought=friesBought+fries.boughtAmount();
            total=total+fries.overallProfit();
            
        }
        
        if (people3.availableBuy(iceCream.foodAvailibility())==3){
            iceCream.getProfit(people3.getHunger(), people3.getHolidays());
            iceCream.foodWasted();
            System.out.println("Current Day:"+theDay+" BirthDay:"+people3.getBirthday());
            System.out.println("Tip:"+iceCream.tip(people3.getTip()));
            
            int iceCreamBirthday=iceCream.birthday(people3.getBirthday(), people3.getDate());
            if(iceCreamBirthday==1){
                total=total-1.25;
            }
            iceCreamBought=iceCreamBought+iceCreamBirthday;
     
            System.out.println("Age: "+people3.getAge());          
            System.out.println("Profit from item:"+iceCream.discountAge(people3.getAge()));
            iceCream.cancelOrder(people3.getCancelOrder());
            iceCreamBought=iceCreamBought+iceCream.boughtAmount();
            total=total+iceCream.overallProfit();
        }

        System.out.println("Pizza: " +pizzaBought);
        System.out.println("Fries: " +friesBought);
        System.out.println("IceCream: " +iceCreamBought);
        System.out.println("Total: "+total);

    }  
    @FXML
    private void overallProfit(){
        total=Math.round(total*100.00)/100.00;
        labelPizzaBought.setText(Integer.toString(pizzaBought));
        labelFriesBought.setText(Integer.toString(friesBought));
        labelIceCreamBought.setText(Integer.toString(iceCreamBought));
        labelProfit.setText(Double.toString(total));
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
