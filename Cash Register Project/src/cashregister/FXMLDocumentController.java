/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cashregister;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author choudhary1645
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label subLabel, totalLabel,overallChangeLabel;
    @FXML
    private Label hunderedLabel, twentyLabel, tenLabel, fiveLabel, oneLabel;
    @FXML
    private Label pennyLabel, quarterLabel, dimeLabel, nickelLabel;
    
    private double subTotalItems;
    private double totalItems;
    private int burgerAmount,friesAmount, drinkAmount, pizzaAmount; 
    //sets random price
    private static final double randomBurgerPrice=(double)(Math.random()*((5-4)+1))+4;
    private static final double randomFriesPrice=(double)(Math.random()*((2-0.99)+1))+0.99;
    private static final double randomDrinkPrice=(double)(Math.random()*((3-2)+1))+2;
    private static final double randomPizzaPrice=(double)(Math.random()*((1.5-0.5)+1))+0.5;
    
    public static final double PENNYVALUE=0.01;
    public static final double NICKELVALUE=0.05;
    public static final double DIMEVALUE=0.10;
    public static final double QUARTERVALUE=0.25;
    public static final double ONEDOLLARVALUE=1;
    public static final double FIVEDOLLARVALUE=5;
    public static final double TENDOLLARVALUE=10;
    public static final double TWENTYDOLLARVALUE=20;
    public static final double HUNDEREDDOLLARVALUE=100;
    
   //This method uses the price and amount of burgers you want and passes this to the subTotal method. 
    @FXML
    private void burger(){
        burgerAmount=Integer.parseInt(JOptionPane.showInputDialog("How many burgers do you want."));
        double burgerPrice= (Math.round(randomBurgerPrice *burgerAmount * 100) / 100.0);
        subTotal(burgerPrice);
        System.out.println("Price for "+burgerAmount+" burgers: $"+burgerPrice);
        subLabel.setText(Double.toString(subTotalItems));
}
    //This method does the same thing as the burger method, but it is a different item. 
    @FXML
    private void fries(){
        friesAmount=Integer.parseInt(JOptionPane.showInputDialog("How many fries do you want."));
        double friesPrice= (Math.round(randomFriesPrice *friesAmount * 100) / 100.0);
        subTotal(friesPrice);
        System.out.println("Price for "+friesAmount+" fries: $"+friesPrice);
        subLabel.setText(Double.toString(subTotalItems));
}
    //Same as burger method.
    @FXML
    private void drink(){
        drinkAmount=Integer.parseInt(JOptionPane.showInputDialog("How many drinks do you want."));
        double drinkPrice= (Math.round(randomDrinkPrice * drinkAmount* 100) / 100.0);
        subTotal(drinkPrice);
        System.out.println("Price for "+drinkAmount+" drinks: $"+drinkPrice);
        subLabel.setText(Double.toString(subTotalItems));
}
    //Another item.
    @FXML
    private void pizza(){
        pizzaAmount=Integer.parseInt(JOptionPane.showInputDialog("How many pizza slices do you want."));
        double pizzaPrice= (Math.round(randomPizzaPrice * pizzaAmount* 100) / 100.0);
        subTotal(pizzaPrice);
        System.out.println("Price for "+pizzaAmount+" pizza slices: $"+pizzaPrice);
        subLabel.setText(Double.toString(subTotalItems));
}
   //This adds the tax to the subtotal and puts it in the label.
    @FXML
    private void total(){
        totalItems= Math.round((subTotalItems*1.07)*100.0)/100.0;
        totalLabel.setText("$"+Double.toString(totalItems));
}
    //This adds the prices of the items. 
    @FXML
    private double subTotal(double price){
        subTotalItems=Math.round((subTotalItems+=price)*100.0)/100.0;
        return subTotalItems;
    }
    //This method checks to see if your payment is enough and shows our change.
    @FXML
    private void pay(){
       double payTotal= Double.parseDouble(JOptionPane.showInputDialog("How much do you want to pay."));
       if (payTotal<totalItems){
           System.out.println("Sorry. This is not enough money for the items you purchased.");
       }
       else{ 
           double changeNeeded= Math.round((payTotal-totalItems)*100)/100.0;
           overallChangeLabel.setText("$"+Double.toString(changeNeeded));
           double hunderedCount=Math.floor(changeNeeded/HUNDEREDDOLLARVALUE);
           changeNeeded= changeNeeded%HUNDEREDDOLLARVALUE;
           hunderedLabel.setText(Double.toString(hunderedCount));
           double twentyCount= Math.floor(changeNeeded/TWENTYDOLLARVALUE);
           changeNeeded= changeNeeded%TWENTYDOLLARVALUE;
           twentyLabel.setText(Double.toString(twentyCount));
           double tenCount=Math.floor(changeNeeded/TENDOLLARVALUE);
           changeNeeded= changeNeeded%TENDOLLARVALUE;
           tenLabel.setText(Double.toString(tenCount));
           double fiveCount=Math.floor(changeNeeded/FIVEDOLLARVALUE);
           changeNeeded= changeNeeded%FIVEDOLLARVALUE;
           fiveLabel.setText(Double.toString(fiveCount));
           double oneCount=Math.floor(changeNeeded/ONEDOLLARVALUE);
           changeNeeded= changeNeeded%ONEDOLLARVALUE;
           oneLabel.setText(Double.toString(oneCount));
           double quarterCount=Math.floor(changeNeeded/QUARTERVALUE);
           changeNeeded= changeNeeded%QUARTERVALUE;
           quarterLabel.setText(Double.toString(quarterCount));
           double dimeCount=Math.floor(changeNeeded/DIMEVALUE);
           changeNeeded= changeNeeded%DIMEVALUE;
           dimeLabel.setText(Double.toString(dimeCount));
           double nickelCount=Math.floor(changeNeeded/NICKELVALUE);
           changeNeeded= changeNeeded%NICKELVALUE;
           nickelLabel.setText(Double.toString(nickelCount));
           double pennyCount=Math.round(changeNeeded/PENNYVALUE);
           changeNeeded= changeNeeded%PENNYVALUE;
           pennyLabel.setText(Double.toString(pennyCount));
             
       }       
    }
    //reets labels and variables
    @FXML
    private void reset(){
         subTotalItems=0.00;
         totalItems=0.00;
         burgerAmount=0;
         friesAmount=0;
         pizzaAmount=0;
         drinkAmount=0;
         subLabel.setText("");
         totalLabel.setText("");
         overallChangeLabel.setText("");
         hunderedLabel.setText("");
         twentyLabel.setText("");
         tenLabel.setText("");
         fiveLabel.setText("");
         oneLabel.setText("");
         pennyLabel.setText("");
         quarterLabel.setText("");
         dimeLabel.setText("");
         nickelLabel.setText("");        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
