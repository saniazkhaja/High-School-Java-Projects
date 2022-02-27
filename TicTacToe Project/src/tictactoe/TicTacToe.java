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
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Player1 name.");
        String n1=sc.next();
        System.out.println("Input Player2 name.");
        String n2=sc.next();
        int pturn=0;
        PlayGame myGame=new PlayGame(n1,n2,pturn);
        myGame.runGame();
        
//        int[][] x=new int[3][3];
//        System.out.println(x.length);
//        System.out.println(x[0].length);
//        for (int i=0;i<x.length;i++ ){
//        x[i][i]=10;
//    }
//        for (int i=0;i<x.length; i++){
//            for (int z=0;z<x.length; z++){
//               System.out.print(x[i][z]+" ");
//            }
//            System.out.println(" "); 
//        }
//        System.out.println(" ");
//        for(int[] y:x){
//            for(int z:y){
//                System.out.print(z+" ");
//            }
//            System.out.println(" ");
//        }
    }
    
}
