/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author choudhary1645
 */
public class PlayGame {
    public PlayGame(String n1, String n2,int pturn){
       turn=pturn; 
       names[0]=n1;
       names[1]=n2;
       
       for (int x=0; x<board.length;x++){
            for (int y=0; y<board.length;y++){
                board[x][y]=count;
                count++;
            }
       }        
    }
    public void runGame(){
        while(!checkDone() && !win){
            System.out.println(names[turn%2]+", what is your move?");
            userInput=Integer.parseInt(sc.next());
            count=1;
            for (int x=0; x<board.length;x++){
                for (int y=0; y<board.length;y++){
                while (board[x][y]>9 && count==userInput  || userInput>9){
                    System.out.println("Sorry. This position is already taken or your position does not exist.");
                    System.out.println(names[turn%2]+", what is your move?");
                    userInput=Integer.parseInt(sc.next());
                }
                count++;
            }
       }           
            display();
            checkRow();
            checkColumn();
            checkDiagonal();
            switchTurn();  
        }
   
    }
    public void switchTurn(){
        turn++;
    }
    public boolean checkDone(){
        if (turn ==9){
            System.out.println("It is a tie.");
            return true;
        }
        else{
            return false;
        }
    }
    
    public void display(){
        count=1;
        for (int x=0; x<board.length;x++){
            for (int y=0; y<board.length;y++){
                if (count==userInput){
                    if (turn%2==0){
                        board[x][y]=10;
                        turnSym=10;
                    }
                    else{
                       board[x][y]=11; 
                       turnSym=11;
                    }
                    
                }
                count++;
                System.out.print(board[x][y]+" ");
            }
            System.out.println(" "); 
       }

    }
    
    public void checkRow(){
        for (int x=0; x<board.length;x++){
            int counter1=0;
            for (int y=0; y<board.length;y++){
                if (board[x][y]==turnSym){
                    counter1=counter1+1;
                    if (counter1==3){
                        System.out.println(names[0]+" , you have won;");
                        win=true;
                    }
                }
            }
        }
    }
    
    public void checkColumn(){
        for (int x=0; x<board.length;x++){
            int counter1=0;
            for (int y=0; y<board.length;y++){
                if (board[y][x]==turnSym){
                    counter1=counter1+1;
                    if (counter1==3){
                        System.out.println(names[0]+" , you have won;");
                        win=true;
                    }
                }
            }
        }
        
    }
    
    private void checkDiagonal(){
        int counter1=0;
        int counter2=0;
        int x=board.length-1;
        int z=board.length-1;
        for (int y=0; y<board.length; y++){
            if (board[y][y]==turnSym){
                counter1++;
                if (counter1==3){
                    System.out.println(names[turn%2]+" , you have won;");
                    win=true;
                }
            }
            if (board[y][x]==turnSym){
                counter2++;
                if (counter2==3){
                    System.out.println(names[turn%2]+" , you have won;");
                    win=true;
                }
                x--;
            }
            
        }
        
    }

    private int [][] board=new int[3][3];
    Scanner sc = new Scanner(System.in);
    private String names[]=new String[2];
    String name1, name2;
    int turn;
    int userInput;
    int numbers;
    int turnSym;
    int count=1;
    boolean win=false;
    
}
