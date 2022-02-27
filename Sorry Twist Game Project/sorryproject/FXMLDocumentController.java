/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorryproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Sania Khaja
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label movesLabel, diceLabel, sabatogeLabel, turnLabel, playerHomeLabel, compHomeLabel, compLabel, compSpecialLabel;
    @FXML
    private TextField pawnSabatoge;
    private ImageView[][] pictures = new ImageView[16][16];
    private Label[][] behindScenes = new Label[16][16];
    @FXML
    private ImageView imgCard;
    @FXML
    private GridPane gPane, cPane;
    @FXML
    private Button startButton, choice1Button, choice2Button, regCardButton, chanceCardButton, submitButton, diceButton, movePawn1Button, movePawn2Button, movePawn3Button, movePawn4Button, resetingButton;

    CardClass cards = new CardClass();
    ComputerClass comp = new ComputerClass();
    double startTime;

    ArrayList<Integer> pawnPositions = new ArrayList<>();
    ArrayList<Integer> computerPawnPositions = new ArrayList<>();
    ArrayList<Integer> computerPP = new ArrayList<>();

    Image blueBackground = new Image("resources/BlueBackground.jpg");
    Image whiteBackground = new Image(("resources/WhiteBackground.jpg"));
    Image darkBlueBackground = new Image("resources/DarkBlueBackground.jpg");
    Image greenBackground = new Image("resources/GreenBackground.jpg");
    Image bluePawn1 = new Image(("resources/BluePawn1.png"));
    Image bluePawn2 = new Image(("resources/BluePawn2.png"));
    Image bluePawn3 = new Image(("resources/BluePawn3.png"));
    Image bluePawn4 = new Image(("resources/BluePawn4.png"));
    Image greenPawn1 = new Image("resources/GreenPawn1.png");
    Image greenPawn2 = new Image("resources/GreenPawn2.png");
    Image greenPawn3 = new Image("resources/GreenPawn3.png");
    Image greenPawn4 = new Image("resources/GreenPawn4.png");
    Image circle = new Image(("resources/Circle.png"));
    Image midLR = new Image("resources/MidSliderLeftRight.png");
    Image midUD = new Image("resources/MidSliderUpDown.png");
    Image upArrow = new Image("resources/UpArrow.png");
    Image downArrow = new Image("resources/DownArrow.png");
    Image rightArrow = new Image("resources/RightArrow.png");
    Image leftArrow = new Image("resources/LeftArrow.png");

    boolean blue1Out = false;
    boolean blue2Out = false;
    boolean blue3Out = false;
    boolean blue4Out = false;

    boolean blue1Home = false;
    boolean blue2Home = false;
    boolean blue3Home = false;
    boolean blue4Home = false;
    boolean userSabatogeComp = false;

    boolean blue1Valid = false;
    boolean blue2Valid = false;
    boolean blue3Valid = false;
    boolean blue4Valid = false;

    int counter = 0;
    int homeCounter = 0;
    int movesLeft = 0;
    int diceNum = 0;
    int turn = 1;
    boolean continuing = false;
    boolean move1Pawn = false;
    int countPawns = 0;
    int tempSabatoge;

    String userInputSabatoge;
    String Card;

    @FXML
    private void resetButton(ActionEvent event) { //reseting buttons, images , classes to play another game
        startTime = -1;
        imgCard.setImage(null);
        regCardButton.setDisable(false);
        chanceCardButton.setDisable(true);
        submitButton.setDisable(true);
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        diceButton.setDisable(true);

        movePawn1Button.setDisable(true);
        movePawn2Button.setDisable(true);
        movePawn3Button.setDisable(true);
        movePawn4Button.setDisable(true);
        
        turnLabel.setText("Player");
        movesLabel.setText("");
        diceLabel.setText("");
        sabatogeLabel.setText("");
        compLabel.setText("");
        compSpecialLabel.setText("");
        playerHomeLabel.setText("");
        compHomeLabel.setText("");
        comp.reset();
        cards.resetCards();

        pawnPositions.clear();
        computerPawnPositions.clear();
        computerPP.clear();

        blue1Out = false;
        blue2Out = false;
        blue3Out = false;
        blue4Out = false;

        blue1Home = false;
        blue2Home = false;
        blue3Home = false;
        blue4Home = false;
        userSabatogeComp = false;

        blue1Valid = false;
        blue2Valid = false;
        blue3Valid = false;
        blue4Valid = false;

        counter = 0;
        homeCounter = 0;
        movesLeft = 0;
        diceNum = 0;
        turn = 1;
        continuing = false;
        move1Pawn = false;
        countPawns = 0;
        //tempSabatoge=0;
        computerPawnPositions.add(1);
        computerPawnPositions.add(5);
        computerPawnPositions.add(2);
        computerPawnPositions.add(5);
        computerPawnPositions.add(1);
        computerPawnPositions.add(4);
        computerPawnPositions.add(2);
        computerPawnPositions.add(4);

        computerPP.add(1);
        computerPP.add(5);
        computerPP.add(2);
        computerPP.add(5);
        computerPP.add(1);
        computerPP.add(4);
        computerPP.add(2);
        computerPP.add(4);

        pawnPositions.add(14);
        pawnPositions.add(11);
        pawnPositions.add(13);
        pawnPositions.add(11);
        pawnPositions.add(14);
        pawnPositions.add(10);
        pawnPositions.add(13);
        pawnPositions.add(10);

        for (int i = 0; i < pictures.length; i++) {
            for (int j = 0; j < pictures.length; j++) {
                if (i == 0 || i == 15 || j == 0 || j == 15) {
                    pictures[i][j].setImage(whiteBackground);
                }
                if (i > 9 && i < 15 && j == 13) {
                    pictures[i][j].setImage(darkBlueBackground);
                }
                if (j == 2 && i > 0 && i < 6) {
                    pictures[i][j].setImage(greenBackground);
                }

            }
        }
        pictures[8][13].setImage(darkBlueBackground);
        pictures[9][13].setImage(darkBlueBackground);
        pictures[9][12].setImage(darkBlueBackground);
        pictures[8][12].setImage(darkBlueBackground);

        pictures[6][3].setImage(greenBackground);
        pictures[7][3].setImage(greenBackground);
        pictures[6][2].setImage(greenBackground);
        pictures[7][2].setImage(greenBackground);

        pictures[14][11].setImage(bluePawn1);
        pictures[13][11].setImage(bluePawn2);
        pictures[14][10].setImage(bluePawn3);
        pictures[13][10].setImage(bluePawn4);

        pictures[1][5].setImage(greenPawn1);
        pictures[2][5].setImage(greenPawn2);
        pictures[1][4].setImage(greenPawn3);
        pictures[2][4].setImage(greenPawn4);

        pictures[15][14].setImage(leftArrow);
        pictures[15][13].setImage(midLR);
        pictures[15][12].setImage(midLR);
        pictures[15][11].setImage(circle);

        pictures[15][6].setImage(leftArrow);
        pictures[15][5].setImage(midLR);
        pictures[15][4].setImage(midLR);
        pictures[15][3].setImage(midLR);
        pictures[15][2].setImage(circle);

        pictures[14][0].setImage(upArrow);
        pictures[13][0].setImage(midUD);
        pictures[12][0].setImage(midUD);
        pictures[11][0].setImage(circle);

        pictures[6][0].setImage(upArrow);
        pictures[5][0].setImage(midUD);
        pictures[4][0].setImage(midUD);
        pictures[3][0].setImage(midUD);
        pictures[2][0].setImage(circle);

        pictures[0][1].setImage(rightArrow);
        pictures[0][2].setImage(midLR);
        pictures[0][3].setImage(midLR);
        pictures[0][4].setImage(circle);

        pictures[0][9].setImage(rightArrow);
        pictures[0][10].setImage(midLR);
        pictures[0][11].setImage(midLR);
        pictures[0][12].setImage(midLR);
        pictures[0][13].setImage(circle);

        pictures[1][15].setImage(downArrow);
        pictures[2][15].setImage(midUD);
        pictures[3][15].setImage(midUD);
        pictures[4][15].setImage(circle);

        pictures[9][15].setImage(downArrow);
        pictures[10][15].setImage(midUD);
        pictures[11][15].setImage(midUD);
        pictures[12][15].setImage(midUD);
        pictures[13][15].setImage(circle);

        gPane.setGridLinesVisible(true);
        gPane.setVisible(true);

        for (int i = 0; i < 14; i++) {
            cards.addRegCards("RN" + i);
            if (i < 4) {
                cards.addChanceCards("CC" + i);
                if (i != 0) {
                    cards.addChanceCards("CN" + i);
                }

                //cards.addChanceCards("CN0");
            }
            if (i < 3) {
                cards.addRegCards("RC" + i);
                cards.addRegCards("RC0");
                cards.addRegCards("RN13");
            }

        }

    }

    @FXML
    private void start(ActionEvent event) { //setting up board and cards in card class
        resetingButton.setDisable(false);
        turnLabel.setText("Player");
        regCardButton.setDisable(false);
        startButton.setDisable(true);
        chanceCardButton.setDisable(true);
        submitButton.setDisable(true);
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        diceButton.setDisable(true);

        movePawn1Button.setDisable(true);
        movePawn2Button.setDisable(true);
        movePawn3Button.setDisable(true);
        movePawn4Button.setDisable(true);

        computerPawnPositions.add(1); //setting up all positions for computer
        computerPawnPositions.add(5);
        computerPawnPositions.add(2);
        computerPawnPositions.add(5);
        computerPawnPositions.add(1);
        computerPawnPositions.add(4);
        computerPawnPositions.add(2);
        computerPawnPositions.add(4);

        computerPP.add(1); //backup array for computer positions
        computerPP.add(5);
        computerPP.add(2);
        computerPP.add(5);
        computerPP.add(1);
        computerPP.add(4);
        computerPP.add(2);
        computerPP.add(4);

        pawnPositions.add(14); //setting up players positions
        pawnPositions.add(11);
        pawnPositions.add(13);
        pawnPositions.add(11);
        pawnPositions.add(14);
        pawnPositions.add(10);
        pawnPositions.add(13);
        pawnPositions.add(10);

        for (int i = 0; i < pictures.length; i++) { //setting up board
            for (int j = 0; j < pictures.length; j++) {
                pictures[i][j] = new ImageView();
                pictures[i][j].setImage(blueBackground);
                pictures[i][j].setFitHeight(30);
                pictures[i][j].setFitWidth(30);
                gPane.add(pictures[i][j], j, i);
                behindScenes[i][j] = new Label(String.valueOf("N"));
                if (i == 0 || i == 15 || j == 0 || j == 15) {
                    pictures[i][j].setImage(whiteBackground);
                }
                if (i > 9 && i < 15 && j == 13) {
                    pictures[i][j].setImage(darkBlueBackground);
                }
                if (j == 2 && i > 0 && i < 6) {
                    pictures[i][j].setImage(greenBackground);
                }

            }
        }
        pictures[8][5].setImage(new Image("resources/S.png"));
        pictures[8][6].setImage(new Image("resources/O.jpg"));
        pictures[8][7].setImage(new Image("resources/R.jpg"));
        pictures[8][8].setImage(new Image("resources/R.jpg"));
        pictures[8][9].setImage(new Image("resources/Y.jpg"));
        pictures[8][13].setImage(darkBlueBackground);
        pictures[9][13].setImage(darkBlueBackground);
        pictures[9][12].setImage(darkBlueBackground);
        pictures[8][12].setImage(darkBlueBackground);

        pictures[6][3].setImage(greenBackground);
        pictures[7][3].setImage(greenBackground);
        pictures[6][2].setImage(greenBackground);
        pictures[7][2].setImage(greenBackground);

        pictures[14][11].setImage(bluePawn1);
        pictures[13][11].setImage(bluePawn2);
        pictures[14][10].setImage(bluePawn3);
        pictures[13][10].setImage(bluePawn4);

        pictures[1][5].setImage(greenPawn1);
        pictures[2][5].setImage(greenPawn2);
        pictures[1][4].setImage(greenPawn3);
        pictures[2][4].setImage(greenPawn4);

        pictures[15][14].setImage(leftArrow);
        pictures[15][13].setImage(midLR);
        pictures[15][12].setImage(midLR);
        pictures[15][11].setImage(circle);

        pictures[15][6].setImage(leftArrow);
        pictures[15][5].setImage(midLR);
        pictures[15][4].setImage(midLR);
        pictures[15][3].setImage(midLR);
        pictures[15][2].setImage(circle);

        pictures[14][0].setImage(upArrow);
        pictures[13][0].setImage(midUD);
        pictures[12][0].setImage(midUD);
        pictures[11][0].setImage(circle);

        pictures[6][0].setImage(upArrow);
        pictures[5][0].setImage(midUD);
        pictures[4][0].setImage(midUD);
        pictures[3][0].setImage(midUD);
        pictures[2][0].setImage(circle);

        pictures[0][1].setImage(rightArrow);
        pictures[0][2].setImage(midLR);
        pictures[0][3].setImage(midLR);
        pictures[0][4].setImage(circle);

        pictures[0][9].setImage(rightArrow);
        pictures[0][10].setImage(midLR);
        pictures[0][11].setImage(midLR);
        pictures[0][12].setImage(midLR);
        pictures[0][13].setImage(circle);

        pictures[1][15].setImage(downArrow);
        pictures[2][15].setImage(midUD);
        pictures[3][15].setImage(midUD);
        pictures[4][15].setImage(circle);

        pictures[9][15].setImage(downArrow);
        pictures[10][15].setImage(midUD);
        pictures[11][15].setImage(midUD);
        pictures[12][15].setImage(midUD);
        pictures[13][15].setImage(circle);

        gPane.setGridLinesVisible(true);
        gPane.setVisible(true);
        //setting up cards
        for (int i = 0; i < 14; i++) {
            cards.addRegCards("RN" + i);
            if (i < 4) {
                cards.addChanceCards("CC" + i);
                if (i != 0) {
                    cards.addChanceCards("CN" + i);
                }

                //cards.addChanceCards("CN0");
            }
            if (i < 3) {
                cards.addRegCards("RC" + i);
                cards.addRegCards("RC0");
                cards.addRegCards("RN13");
            }

        }

    }

    @FXML
    private void regularCard(ActionEvent event) { 
        compLabel.setText("");
        compSpecialLabel.setText("");
        sabatogeLabel.setText("");
        diceLabel.setText("");
        Card = cards.getRegCard(); //picking up a card
        regCardButton.setDisable(true);
        imgCard.setImage(new Image("resources/" + Card + ".png")); //showing card
        // System.out.println("CArd:" + Card.substring(1, 2) + Card);
        //System.out.println("");

        if (Card.substring(1, 2).equals("C")) { //choice cards. enable choice buttons
            //System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            choice1Button.setDisable(false);
            choice2Button.setDisable(false);
            continuing = true;
        }
        if (Card.equals("RN13")) { //pick up chance card
            chanceCardButton.setDisable(false);
            regCardButton.setDisable(true);
            continuing = true;
        }
        if (Card.equals("RN0")) { //can move pawns out of the start box
            if (blue1Out == false && blue1Home == false) { //figuring out which pawn to remove form start box. Next 4 statements
                blue1Out = true;
                counter = 1;
                board(bluePawn1, 0, 1);
                movesLeft = 0;
                movesLabel.setText("");
                special(0, 1);
                //System.out.println(movesLeft);
            } else if (blue2Out == false && blue2Home == false) {
                blue2Out = true;
                counter = 2;
                board(bluePawn2, 2, 3);
                movesLeft = 0;
                movesLabel.setText("");
                special(2, 3);
                //System.out.println(movesLeft);
            } else if (blue3Out == false && blue3Home == false) {
                blue3Out = true;
                counter = 3;
                board(bluePawn3, 4, 5);
                movesLeft = 0;
                movesLabel.setText("");
                special(4, 5);
                //System.out.println(movesLeft);
            } else if (blue4Out == false && blue4Home == false) {
                blue4Out = true;
                counter = 4;
                board(bluePawn4, 6, 7);
                movesLeft = 0;
                movesLabel.setText("");
                special(6, 7);
                //System.out.println(movesLeft);
            }
            //System.out.println(movesLeft);
            turn = 2;
            computer();
        }
        if (blue1Out == false && blue2Out == false && blue3Out == false && blue4Out == false) { //no pawns are on the board
            movesLeft = 0;
        } else {
            if (continuing == false) { //regular moving card
                movesLeft = Integer.parseInt(Card.substring(2)); //getting moves 
                move1Pawn = true;
            }
        }
        movesLabel.setText(Integer.toString(movesLeft)); //showing how many moves the player has

        //which pawns can be played
        canMove();
        
    }

    private void canMove() { //checking if pawn can move
        boolean blue1Disabled = false;
        boolean blue2Disabled = false;
        boolean blue3Disabled = false;
        boolean blue4Disabled = false;
        if (movesLeft == 0 || blue1Out == false || blue1Home == true) { //disabling buttons if cant use that pawn
            movePawn1Button.setDisable(true);
            blue1Disabled = true;
        } else {
            movePawn1Button.setDisable(false);
        }
        if (movesLeft == 0 || blue2Out == false || blue2Home == true) {
            movePawn2Button.setDisable(true);
            blue2Disabled = true;
        } else {
            movePawn2Button.setDisable(false);
        }
        if (movesLeft == 0 || blue3Out == false || blue3Home == true) {
            movePawn3Button.setDisable(true);
            blue3Disabled = true;
        } else {
            movePawn3Button.setDisable(false);
        }
        if (movesLeft == 0 || blue4Out == false || blue4Home == true) {
            movePawn4Button.setDisable(true);
            blue4Disabled = true;
        } else {
            movePawn4Button.setDisable(false);
        }

        for (int i = 0; i < 8; i += 2) { //looking at each pawn 
            if ((pawnPositions.get(i) > 11 && pawnPositions.get(i + 1) == 15)) {//figuring out if can do move accoiridng to card because close to home
                if (movesLeft > 11 || (pawnPositions.get(i) == 13 && movesLeft > 10) || (pawnPositions.get(i) == 14 && movesLeft > 9) || (pawnPositions.get(i) == 15 && movesLeft > 8)) {
                    if (i == 0) {
                        movePawn1Button.setDisable(true);
                        blue1Disabled = true;
                    }
                    if (i == 2) {
                        movePawn2Button.setDisable(true);
                        blue2Disabled = true;
                    }
                    if (i == 4) {
                        movePawn3Button.setDisable(true);
                        blue3Disabled = true;
                    }
                    if (i == 6) {
                        movePawn4Button.setDisable(true);
                        blue4Disabled = true;
                    }
                }
            } else if ((pawnPositions.get(i) == 15 && pawnPositions.get(i + 1) > 12)) { //figuring out if can do move accoiridng to card because close to home
                if (movesLeft > 8 || (pawnPositions.get(i + 1) == 14 && movesLeft > 7) || (pawnPositions.get(i + 1) == 13 && movesLeft > 6)) {
                    if (i == 0) {
                        movePawn1Button.setDisable(true);
                        blue1Disabled = true;
                    }
                    if (i == 2) {
                        movePawn2Button.setDisable(true);
                        blue2Disabled = true;
                    }
                    if (i == 4) {
                        movePawn3Button.setDisable(true);
                        blue3Disabled = true;
                    }
                    if (i == 6) {
                        movePawn4Button.setDisable(true);
                        blue4Disabled = true;
                    }
                }
            } else if (pawnPositions.get(i) > 9 && pawnPositions.get(i + 1) == 13) { //figuring out if can do move accoiridng to card because close to home
                if (movesLeft > 6 || (pawnPositions.get(i) == 14 && movesLeft > 5) || (pawnPositions.get(i) == 13 && movesLeft > 4) || (pawnPositions.get(i) == 12 && movesLeft > 3) || (pawnPositions.get(i) == 11 && movesLeft > 2) || (pawnPositions.get(i) == 10 && movesLeft > 1)) {
                    if (i == 0) {
                        movePawn1Button.setDisable(true);
                        blue1Disabled = true;
                    }
                    if (i == 2) {
                        movePawn2Button.setDisable(true);
                        blue2Disabled = true;
                    }
                    if (i == 4) {
                        movePawn3Button.setDisable(true);
                        blue3Disabled = true;
                    }
                    if (i == 6) {
                        movePawn4Button.setDisable(true);
                        blue4Disabled = true;
                    }
                }
            }
        }
        if (blue4Disabled == true && blue3Disabled == true && blue2Disabled == true && blue1Disabled == true && continuing == false) { //checking to see of turn should be skipped
            movesLeft = 0;
            movesLabel.setText(Integer.toString(movesLeft));
            if(!Card.equals("RN0")){ 
                sabatogeLabel.setText("Turn skipped since no valid moves"); //no possible moves so telling user
            }
            
            turn = 2;
            computer();
        }
//        else if (movesLeft == 0 && continuing == false) {//swicthing turns
//            turn = 2;
//            computer();
//        }
    }

    @FXML
    private void chanceCard(ActionEvent event) {
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        regCardButton.setDisable(true);
        chanceCardButton.setDisable(true);

        Card = cards.getChanceCard();
        imgCard.setImage(new Image("resources/" + Card + ".png"));
        if (Card.substring(0, 2).equals("CC")) { //choice cards. enable choice buttons
            choice1Button.setDisable(false);
            choice2Button.setDisable(false);
        } else if (Card.equals("CN1")) { //disabling dice
            diceButton.setDisable(false);
        } //else if (Card.equals("CN0")) {
        //            bluePawnValid();
        //            if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
        //                if (comp.greenPawn4Valid(computerPawnPositions) == true || comp.greenPawn3Valid(computerPawnPositions) == true || comp.greenPawn2Valid(computerPawnPositions) == true || comp.greenPawn1Valid(computerPawnPositions) == true) {
        //                    submitButton.setDisable(false);
        //                    sabatogeLabel.setText("Input computer pawn you want to switch with.");
        //                } else {
        //                    sabatogeLabel.setText("Turn skipped since no possible moves.");
        //                    turn = 2;
        //                    computer();
        //                }
        //            } else {
        //                sabatogeLabel.setText("Turn skipped since no possible moves.");
        //                turn = 2;
        //                computer();
        //            }
        //
        //            //userInputSabatoge needed for further decided
        //            //swicthing pawn
        //} 
        else if (Card.equals("CN2")) {
            bluePawnValid(); //deciding if player will be able to do card
            if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                submitButton.setDisable(false);//chose pawn to move to nearest corner
            } else { //turn being skipped
                sabatogeLabel.setText("Turn skipped since no possible moves.");
                turn = 2;
                computer();

            }
            //userInputSabatoge needed for further decided
        } else if (Card.equals("CN3")) {
            //seeing if any of the computers pawns can be put back to start
            if (comp.greenPawn4Valid(computerPawnPositions) == true || comp.greenPawn3Valid(computerPawnPositions) == true || comp.greenPawn2Valid(computerPawnPositions) == true || comp.greenPawn1Valid(computerPawnPositions) == true) {
                submitButton.setDisable(false);

            } else { //turn skipped
                sabatogeLabel.setText("Turn skipped since no possible moves.");
                turn = 2;
                computer();
            }
            
        }

    }

    @FXML
    private void choice1(ActionEvent event) { //choice 1 on card
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        if (Card.substring(0, 2).equals("RC")) { //regular choce cards
            continuing = false; //choice now made
            if (Card.equals("RC0")) { 
                movesLeft = 1;
                move1Pawn = true;
                canMove(); //checking if can move any pawn and disabling button accordingly
                movesLabel.setText(Integer.toString(movesLeft));
            } else if (Card.equals("RC1")) {
                movesLeft = 2;
                move1Pawn = true;
                canMove(); //checking if can move any pawn and disabling button accordingly
                movesLabel.setText(Integer.toString(movesLeft));
            } else if (Card.equals("RC2")) {
                movesLeft = 7;
                move1Pawn = true;
                canMove(); //checking if can move any pawn and disabling button accordingly

                movesLabel.setText(Integer.toString(movesLeft));
            }

//            if (movesLeft == 0 && continuing == false) {//swicthing turns
//                turn = 2;
//                computer();
//            }
        }
        if (Card.substring(0, 2).equals("CC")) { //chance choice so dice needed to determine the scenario
            diceButton.setDisable(false);
        }
    }

    @FXML
    private void choice2(ActionEvent event) { //choice 2 chosen
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        if (Card.substring(0, 2).equals("RC")) {
            if (Card.equals("RC0") || Card.equals("RC1")) { 
                if (blue1Out == false && blue1Home == false) { //checking which pawn to remove from start box. Next four statements
                    blue1Out = true;
                    counter = 1;
                    board(bluePawn1, 0, 1); //putting pawn on board
                    special(0, 1); //checking for pawns being sent back to start
                } else if (blue2Out == false && blue2Home == false) {
                    blue2Out = true;
                    counter = 2;
                    board(bluePawn2, 2, 3);
                    special(2, 3);
                } else if (blue3Out == false && blue3Home == false) {
                    blue3Out = true;
                    counter = 3;
                    board(bluePawn3, 4, 5);
                    special(4, 5);
                } else if (blue4Out == false && blue4Home == false) {
                    blue4Out = true;
                    counter = 4;
                    board(bluePawn4, 6, 7);
                    special(6, 7);
                } else {
                    sabatogeLabel.setText("Turn skipped since no valid moves");
                }
                //System.out.println("yay. can move apwn out");
                continuing = false;
                movesLeft = 0;
                if (movesLeft == 0 && continuing == false) {//swicthing turns
                    turn = 2;
                    computer();
                }
            }
            if (Card.equals("RC2")) {
                //pick up chance card
                chanceCardButton.setDisable(false);
                continuing = true;
            }

        }
        if (Card.substring(0, 2).equals("CC")) { //do not use card so turn changes
            turn = 2;
            computer();
        }
    }

    @FXML
    private void dice(ActionEvent event) {
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        chanceCardButton.setDisable(true);
        diceNum = cards.diceNum();
        diceLabel.setText(Integer.toString(diceNum));
        if (Card.equals("CN1")) { //destermines moves
            continuing = false;
            movesLeft = diceNum;
            movesLabel.setText(Integer.toString(movesLeft));
            move1Pawn = true;
            canMove();
        }
        if (Card.equals("CC0")) { 
            //move pawn to home
            bluePawnValid(); //checking if any moves can be done. then disables button if a move can be done
            if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                submitButton.setDisable(false);
            } else {
                sabatogeLabel.setText("Turn skipped since no possible moves to make");
                turn = 2;
                computer();

            }

        }
        if (Card.equals("CC1")) { //destermines moves
            bluePawnValid(); //need pawn on board to be able to actually use card
            if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                submitButton.setDisable(false);
            } else {
                sabatogeLabel.setText("Turn skipped since no possible moves to make");
                turn = 2;
                computer();

            }

        }
        if (Card.equals("CC2")) { 
            if (diceNum == 1 || diceNum == 3 || diceNum == 5) {
                //move one pawn to from satrt to home
                //checking if move can be done
                if (blue1Out == false || blue2Out == false || blue3Out == false || blue4Out == false) {
                    submitButton.setDisable(false);
                } else {
                    sabatogeLabel.setText("Turn skipped since no possible moves to make");
                    turn = 2;
                    computer();

                }
            } else {
                if (blue1Home == true) { //sending pawns in home box back to start
                    resetImages(0, 1);
                    pictures[14][11].setImage(bluePawn1);
                    blue1Out = false;
                    pawnPositions.set(0, 14);
                    pawnPositions.set(1, 11);
                    submitButton.setDisable(false);

                } else if (blue2Home == true) {
                    resetImages(2, 3);
                    pictures[13][11].setImage(bluePawn2);
                    blue2Out = false;
                    pawnPositions.set(2, 13);
                    pawnPositions.set(3, 11);
                    submitButton.setDisable(false);

                } else if (blue3Home == true) {
                    resetImages(4, 5);
                    pictures[14][10].setImage(bluePawn3);
                    blue3Out = false;
                    pawnPositions.set(4, 14);
                    pawnPositions.set(5, 10);
                    submitButton.setDisable(false);

                } else if (blue4Home == true) {
                    resetImages(6, 7);
                    pictures[13][10].setImage(bluePawn4);
                    blue4Out = false;
                    pawnPositions.set(6, 13);
                    pawnPositions.set(7, 10);
                    submitButton.setDisable(false);

                } else {
                    sabatogeLabel.setText("Turn skipped since no valid moves");
                }
                turn = 2;
                computer();
            }

        }
        if (Card.equals("CC3")) { //destermines moves
            if (diceNum == 1 || diceNum == 3 || diceNum == 5) {
                //move one pawn of comp to start 
                //checking if a computer pawn can be sent back to start
                if (comp.greenPawn1Valid(computerPawnPositions) == true || comp.greenPawn2Valid(computerPawnPositions) == true || comp.greenPawn3Valid(computerPawnPositions) == true || comp.greenPawn4Valid(computerPawnPositions) == true) {
                    submitButton.setDisable(false);
                } else {
                    sabatogeLabel.setText("Turn skipped since no possible moves to make");
                    turn = 2;
                    computer();

                }
            } else {
                bluePawnValid(); //checking if you can bump a pawn of yours back to start. if not turn is skipped
                if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                    submitButton.setDisable(false);
                } else {
                    sabatogeLabel.setText("Turn skipped since no possible moves to make");
                    turn = 2;
                    computer();
                }

            }

        }

    }

    private void bluePawnValid() { //seeing if pawn is not in safety zone or start box or home box
        blue1Valid = false;
        blue2Valid = false;
        blue3Valid = false;
        blue4Valid = false;
        if (pawnPositions.get(0) == 0 || pawnPositions.get(1) == 0 || pawnPositions.get(0) == 15 || pawnPositions.get(1) == 15) {
            blue1Valid = true;
        }
        if (pawnPositions.get(2) == 0 || pawnPositions.get(3) == 0 || pawnPositions.get(2) == 15 || pawnPositions.get(3) == 15) {
            blue2Valid = true;
        }
        if (pawnPositions.get(4) == 0 || pawnPositions.get(5) == 0 || pawnPositions.get(4) == 15 || pawnPositions.get(5) == 15) {
            blue3Valid = true;
        }
        if (pawnPositions.get(6) == 0 || pawnPositions.get(7) == 0 || pawnPositions.get(6) == 15 || pawnPositions.get(7) == 15) {
            blue4Valid = true;
        }
    }

    @FXML
    private void submit(ActionEvent event) {
        userInputSabatoge = pawnSabatoge.getText();

        if (userInputSabatoge.equals("1") || userInputSabatoge.equals("2") || userInputSabatoge.equals("3") || userInputSabatoge.equals("4")) { //making sure user puts in a valid input.
            sabatogeLabel.setText("");
//            if (Card.equals("CN0")) { //swicth pawn with other player
//                bluePawnValid();
//                if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
//                    if (comp.greenPawn4Valid(computerPawnPositions) == false && comp.greenPawn3Valid(computerPawnPositions) == false && comp.greenPawn2Valid(computerPawnPositions) == false && comp.greenPawn1Valid(computerPawnPositions) == false) {
//                        turn = 2;
//                        computer();
//                    }
//                    if (comp.greenPawn1Valid(computerPawnPositions) == true) {
////                        System.out.println("Comp" + userSabatogeComp);
////                        System.out.println("User" + userInputSabatoge);
////                        System.out.println("Valid" + blue1Valid);
////                        System.out.println("Compnum:" + tempSabatoge);
//                        if (userInputSabatoge.equals("1") && userSabatogeComp == false) {
//                            userSabatogeComp = true;
//                            tempSabatoge = 1;
//                            sabatogeLabel.setText("Input your pawn you want to switch with.");
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("1") && blue1Valid == true && tempSabatoge == 1) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue1Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            // System.out.println("WORKSSKSKSKSK");
//                            int compRow = computerPawnPositions.get(0);
//                            int compCol = computerPawnPositions.get(1);
//                            int playerRow = pawnPositions.get(0);
//                            int playerCol = pawnPositions.get(1);
//                            pawnPositions.set(0, compRow);
//                            pawnPositions.set(1, compCol);
//                            computerPawnPositions.set(0, playerRow);
//                            computerPawnPositions.set(1, playerCol);
//                            pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
//                            pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("2") && blue2Valid == true && tempSabatoge == 1) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue2Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(0);
//                            int compCol = computerPawnPositions.get(1);
//                            int playerRow = pawnPositions.get(2);
//                            int playerCol = pawnPositions.get(3);
//                            pawnPositions.set(2, compRow);
//                            pawnPositions.set(3, compCol);
//                            computerPawnPositions.set(0, playerRow);
//                            computerPawnPositions.set(1, playerCol);
//
//                            pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
//                            pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("3") && blue3Valid == true && tempSabatoge == 1) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue3Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(0);
//                            int compCol = computerPawnPositions.get(1);
//                            int playerRow = pawnPositions.get(4);
//                            int playerCol = pawnPositions.get(5);
//                            pawnPositions.set(4, compRow);
//                            pawnPositions.set(5, compCol);
//                            computerPawnPositions.set(0, playerRow);
//                            computerPawnPositions.set(1, playerCol);
//
//                            pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
//                            pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("4") && blue4Valid == true && tempSabatoge == 1) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue4Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(0);
//                            int compCol = computerPawnPositions.get(1);
//                            int playerRow = pawnPositions.get(6);
//                            int playerCol = pawnPositions.get(7);
//                            pawnPositions.set(6, compRow);
//                            pawnPositions.set(7, compCol);
//                            computerPawnPositions.set(0, playerRow);
//                            computerPawnPositions.set(1, playerCol);
//
//                            pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
//                            pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
//                            turn = 2;
//                            computer();
//                        }
//                    }
//                    if (comp.greenPawn2Valid(computerPawnPositions) == true) {
//                        if (userInputSabatoge.equals("2") && userSabatogeComp == false) {
//                            userSabatogeComp = true;
//                            tempSabatoge = 2;
//                            sabatogeLabel.setText("Input your pawn you want to switch with.");
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("1") && blue1Valid == true && tempSabatoge == 2) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue1Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(2);
//                            int compCol = computerPawnPositions.get(3);
//                            int playerRow = pawnPositions.get(0);
//                            int playerCol = pawnPositions.get(1);
//                            pawnPositions.set(0, compRow);
//                            pawnPositions.set(1, compCol);
//                            computerPawnPositions.set(2, playerRow);
//                            computerPawnPositions.set(3, playerCol);
//
//                            pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
//                            pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("2") && blue2Valid == true && tempSabatoge == 2) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue2Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(2);
//                            int compCol = computerPawnPositions.get(3);
//                            int playerRow = pawnPositions.get(2);
//                            int playerCol = pawnPositions.get(3);
//                            pawnPositions.set(2, compRow);
//                            pawnPositions.set(3, compCol);
//                            computerPawnPositions.set(2, playerRow);
//                            computerPawnPositions.set(3, playerCol);
//                            pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
//                            pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("3") && blue3Valid == true && tempSabatoge == 2) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue3Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(2);
//                            int compCol = computerPawnPositions.get(3);
//                            int playerRow = pawnPositions.get(4);
//                            int playerCol = pawnPositions.get(5);
//                            pawnPositions.set(4, compRow);
//                            pawnPositions.set(5, compCol);
//                            computerPawnPositions.set(2, playerRow);
//                            computerPawnPositions.set(3, playerCol);
//                            pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
//                            pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("4") && blue4Valid == true && tempSabatoge == 2) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue4Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(2);
//                            int compCol = computerPawnPositions.get(3);
//                            int playerRow = pawnPositions.get(6);
//                            int playerCol = pawnPositions.get(7);
//                            pawnPositions.set(6, compRow);
//                            pawnPositions.set(7, compCol);
//                            computerPawnPositions.set(2, playerRow);
//                            computerPawnPositions.set(3, playerCol);
//                            pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
//                            pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
//                            turn = 2;
//                            computer();
//                        }
//
//                    }
//                    if (comp.greenPawn3Valid(computerPawnPositions) == true) {
//                        if (userInputSabatoge.equals("3") && userSabatogeComp == false) {
//                            userSabatogeComp = true;
//                            tempSabatoge = 3;
//                            sabatogeLabel.setText("Input your pawn you want to switch with.");
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("1") && blue1Valid == true && tempSabatoge == 3) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue1Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(4);
//                            int compCol = computerPawnPositions.get(5);
//                            int playerRow = pawnPositions.get(0);
//                            int playerCol = pawnPositions.get(1);
//                            pawnPositions.set(0, compRow);
//                            pawnPositions.set(1, compCol);
//                            computerPawnPositions.set(4, playerRow);
//                            computerPawnPositions.set(5, playerCol);
//                            pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
//                            pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("2") && blue2Valid == true && tempSabatoge == 3) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue2Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(4);
//                            int compCol = computerPawnPositions.get(5);
//                            int playerRow = pawnPositions.get(2);
//                            int playerCol = pawnPositions.get(3);
//                            pawnPositions.set(2, compRow);
//                            pawnPositions.set(3, compCol);
//                            computerPawnPositions.set(4, playerRow);
//                            computerPawnPositions.set(5, playerCol);
//                            pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
//                            pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("3") && blue3Valid == true && tempSabatoge == 3) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue3Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(4);
//                            int compCol = computerPawnPositions.get(5);
//                            int playerRow = pawnPositions.get(4);
//                            int playerCol = pawnPositions.get(5);
//                            pawnPositions.set(4, compRow);
//                            pawnPositions.set(5, compCol);
//                            computerPawnPositions.set(4, playerRow);
//                            computerPawnPositions.set(5, playerCol);
//                            pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
//                            pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("4") && blue4Valid == true && tempSabatoge == 3) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue4Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(4);
//                            int compCol = computerPawnPositions.get(5);
//                            int playerRow = pawnPositions.get(6);
//                            int playerCol = pawnPositions.get(7);
//                            pawnPositions.set(6, compRow);
//                            pawnPositions.set(7, compCol);
//                            computerPawnPositions.set(4, playerRow);
//                            computerPawnPositions.set(5, playerCol);
//                            pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
//                            pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
//                            turn = 2;
//                            computer();
//                        }
//                    }
//                    if (comp.greenPawn4Valid(computerPawnPositions) == true) {
//                        if (userInputSabatoge.equals("4") && userSabatogeComp == false) {
//                            userSabatogeComp = true;
//                            tempSabatoge = 4;
//                            sabatogeLabel.setText("Input your pawn you want to switch with.");
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("1") && blue1Valid == true && tempSabatoge == 4) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue1Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(6);
//                            int compCol = computerPawnPositions.get(7);
//                            int playerRow = pawnPositions.get(0);
//                            int playerCol = pawnPositions.get(1);
//                            pawnPositions.set(0, compRow);
//                            pawnPositions.set(1, compCol);
//                            computerPawnPositions.set(6, playerRow);
//                            computerPawnPositions.set(7, playerCol);
//                            pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
//                            pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("2") && blue2Valid == true && tempSabatoge == 4) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue2Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(6);
//                            int compCol = computerPawnPositions.get(7);
//                            int playerRow = pawnPositions.get(2);
//                            int playerCol = pawnPositions.get(3);
//                            pawnPositions.set(2, compRow);
//                            pawnPositions.set(3, compCol);
//                            computerPawnPositions.set(6, playerRow);
//                            computerPawnPositions.set(7, playerCol);
//                            pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
//                            pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("3") && blue3Valid == true && tempSabatoge == 4) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue3Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(6);
//                            int compCol = computerPawnPositions.get(7);
//                            int playerRow = pawnPositions.get(4);
//                            int playerCol = pawnPositions.get(5);
//                            pawnPositions.set(4, compRow);
//                            pawnPositions.set(5, compCol);
//                            computerPawnPositions.set(6, playerRow);
//                            computerPawnPositions.set(7, playerCol);
//                            pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
//                            pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
//                            turn = 2;
//                            computer();
//                        } else if (userSabatogeComp = true && userInputSabatoge.equals("4") && blue4Valid == true && tempSabatoge == 4) {
//                            System.out.println("Comp" + userSabatogeComp);
//                            System.out.println("User" + userInputSabatoge);
//                            System.out.println("Valid" + blue4Valid);
//                            System.out.println("Compnum:" + tempSabatoge);
//                            int compRow = computerPawnPositions.get(6);
//                            int compCol = computerPawnPositions.get(7);
//                            int playerRow = pawnPositions.get(6);
//                            int playerCol = pawnPositions.get(7);
//                            pawnPositions.set(6, compRow);
//                            pawnPositions.set(7, compCol);
//                            computerPawnPositions.set(6, playerRow);
//                            computerPawnPositions.set(7, playerCol);
//                            pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
//                            pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
//                            turn = 2;
//                            computer();
//                        }
//                    }
//
//                } else {
//                    turn = 2;
//                    computer();
//                }
//
//            } 
            if (Card.equals("CN2")) {
                //choose pawn to move to nearest corner
                bluePawnValid(); //checking for valid input
                if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                    if (userInputSabatoge.equals("1") && blue1Valid == true) { //checking what user inputed and if valid move
                        sabatogeLabel.setText("");
                        nearestCorner(0, 1); //moving pawn to nearest corner
                        turn = 2;
                        computer();
                    } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                        sabatogeLabel.setText("");
                        nearestCorner(2, 3);
                        turn = 2;
                        computer();
                    } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                        sabatogeLabel.setText("");
                        nearestCorner(4, 5);
                        turn = 2;
                        computer();
                    } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                        sabatogeLabel.setText("");
                        nearestCorner(6, 7);
                        turn = 2;
                        computer();
                    } else { //not valid input
                        sabatogeLabel.setText("Invalid Move");
                    }
                } else { //turn skipped
                    sabatogeLabel.setText("Turn skipped since no valid moves");
                    turn = 2;
                    computer();
                }

            } else if (Card.equals("CN3")) {
                //send opponent pawn back to start
                if (comp.greenPawn1Valid(computerPawnPositions) == true) { //checking if possible to send opponet back to start
                    if (userInputSabatoge.equals("1")) {
                        sabatogeLabel.setText("");
                        comp.change(1);
                        pictures[1][5].setImage(greenPawn1);

                        compHome(0);
                        //System.out.println("Pawn sent back home");
                        turn = 2;
                        computer();
                    }

                } else if (comp.greenPawn2Valid(computerPawnPositions) == true) {
                    if (userInputSabatoge.equals("2")) {
                        sabatogeLabel.setText("");
                        comp.change(2);
                        pictures[2][5].setImage(greenPawn2);

                        compHome(2);
                        //System.out.println("Pawn sent back home");
                        turn = 2;
                        computer();
                    } 

                } else if (comp.greenPawn3Valid(computerPawnPositions) == true) {
                    if (userInputSabatoge.equals("3")) {
                        sabatogeLabel.setText("");
                        comp.change(3);
                        pictures[1][4].setImage(greenPawn3);

                        compHome(4);
                        //System.out.println("Pawn sent back home");
                        turn = 2;
                        computer();
                    }

                } else if (comp.greenPawn4Valid(computerPawnPositions) == true) {
                    if (userInputSabatoge.equals("4")) {
                        sabatogeLabel.setText("");
                        comp.change(4);
                        pictures[2][4].setImage(greenPawn4);
                        compHome(6);
                        //System.out.println("Pawn sent back home");
                        turn = 2;
                        computer();
                    } 

                } else {
                    sabatogeLabel.setText("Turn skipped since no valid moves");
                    turn = 2;
                    computer();
                }
                if (comp.greenPawn1Valid(computerPawnPositions) == false && userInputSabatoge.equals("1")) { //checking for invalid move
                    sabatogeLabel.setText("Invalid Move");
                }
                if (comp.greenPawn2Valid(computerPawnPositions) == false && userInputSabatoge.equals("2")) {
                    sabatogeLabel.setText("Invalid Move");
                }
                if (comp.greenPawn3Valid(computerPawnPositions) == false && userInputSabatoge.equals("3")) {
                    sabatogeLabel.setText("Invalid Move");
                }
                if (comp.greenPawn4Valid(computerPawnPositions) == false && userInputSabatoge.equals("4")) {
                    sabatogeLabel.setText("Invalid Move");
                }

            } else if (Card.equals("CC0")) { //destermines moves
                if (diceNum == 1 || diceNum == 6) {
                    //move pawn to home
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                        //moving panw to home
                        if (userInputSabatoge.equals("1") && blue1Valid == true) { 
                            sabatogeLabel.setText("");
                            resetImages(0, 1); //reseting images
                            homeCounter++;
                            blue1Home = true;
                            pictures[8][12].setImage(bluePawn1);
                            pawnPositions.set(0, 8);
                            pawnPositions.set(1, 12);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(2, 3);
                            pictures[8][12].setImage(bluePawn2);
                            homeCounter++;
                            blue2Home = true;
                            pawnPositions.set(2, 8);
                            pawnPositions.set(3, 12);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(4, 5);
                            pictures[8][12].setImage(bluePawn3);
                            homeCounter++;
                            blue3Home = true;
                            pawnPositions.set(4, 8);
                            pawnPositions.set(5, 12);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(6, 7);
                            pictures[8][12].setImage(bluePawn4);
                            homeCounter++;
                            blue4Home = true;
                            pawnPositions.set(6, 8);
                            pawnPositions.set(7, 12);
                            turn = 2;
                            computer();
                        } else {
                            sabatogeLabel.setText("Invalid Move");
                        }
                        if (homeCounter == 4) {
                            playerHomeLabel.setText(Integer.toString(homeCounter));
                            System.out.println("You win");
                            sabatogeLabel.setText("You Won!");
                            compLabel.setText("I have lost");
                        }
                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        computer();
                    }
                } else {
                    //put one pawn on board back to start
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                        if (userInputSabatoge.equals("1") && blue1Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(0, 1);
                            pictures[14][11].setImage(bluePawn1);
                            blue1Out = false;
                            pawnPositions.set(0, 14);
                            pawnPositions.set(1, 11);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(2, 3);
                            pictures[13][11].setImage(bluePawn2);
                            blue2Out = false;
                            pawnPositions.set(2, 13);
                            pawnPositions.set(3, 11);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(4, 5);
                            pictures[14][10].setImage(bluePawn3);
                            blue3Out = false;
                            pawnPositions.set(4, 14);
                            pawnPositions.set(5, 10);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(6, 7);
                            pictures[13][10].setImage(bluePawn4);
                            blue4Out = false;
                            pawnPositions.set(6, 13);
                            pawnPositions.set(7, 10);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else {
                            sabatogeLabel.setText("Invalid move");
                        }
                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                }
            }

            if (Card.equals("CC1")) { //destermines moves
                if (diceNum == 1 || diceNum == 3 || diceNum == 5) {
                    //move one pawn to nearest forwarf corner 
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                        if (userInputSabatoge.equals("1") && blue1Valid == true) {
                            sabatogeLabel.setText("");
                            nearestCorner(0, 1);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                            sabatogeLabel.setText("");
                            nearestCorner(2, 3);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                            sabatogeLabel.setText("");
                            nearestCorner(4, 5);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                            sabatogeLabel.setText("");
                            nearestCorner(6, 7);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else {
                            sabatogeLabel.setText("Invalid move");
                        }
                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                } else {
                    //move one pawn to nearest back corner 
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                        if (userInputSabatoge.equals("1") && blue1Valid == true) {
                            sabatogeLabel.setText("");
                            backCorner(0, 1);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                            sabatogeLabel.setText("");
                            backCorner(2, 3);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                            sabatogeLabel.setText("");
                            backCorner(4, 5);
                            turn = 2;
                            computer();
                        } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                            sabatogeLabel.setText("");
                            backCorner(6, 7);
                            turn = 2;
                            computer();
                        } else {
                            sabatogeLabel.setText("Invalid move");
                        }
                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                }
            }
            if (Card.equals("CC2")) { //destermines moves
                if (diceNum == 1 || diceNum == 3 || diceNum == 5) {
                    //move one pawn to from satrt to home
                    if (blue1Out == false || blue2Out == false || blue3Out == false || blue4Out == false) {
                        if (userInputSabatoge.equals("1") && blue1Out == false) {
                            sabatogeLabel.setText("");
                            resetImages(0, 1);
                            homeCounter++;
                            blue1Home = true;
                            blue1Out=true;
                            pictures[8][12].setImage(bluePawn1);
                            pawnPositions.set(0, 8);
                            pawnPositions.set(1, 12);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("2") && blue2Out == false) {
                            sabatogeLabel.setText("");
                            resetImages(2, 3);
                            pictures[8][12].setImage(bluePawn2);
                            homeCounter++;
                            blue2Home = true;
                            blue2Out=true;
                            pawnPositions.set(2, 8);
                            pawnPositions.set(3, 12);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("3") && blue3Out == false) {
                            sabatogeLabel.setText("");
                            resetImages(4, 5);
                            pictures[8][12].setImage(bluePawn3);
                            homeCounter++;
                            blue3Home = true;
                            blue3Out=true;
                            pawnPositions.set(4, 8);
                            pawnPositions.set(5, 12);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("4") && blue4Out == false) {
                            sabatogeLabel.setText("");
                            resetImages(6, 7);
                            pictures[8][12].setImage(bluePawn4);
                            homeCounter++;
                            blue4Home = true;
                            blue4Out=true;
                            pawnPositions.set(6, 8);
                            pawnPositions.set(7, 12);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else {
                            sabatogeLabel.setText("Invalid move");
                        }
                        if (homeCounter == 4) {
                            playerHomeLabel.setText(Integer.toString(homeCounter));
                            System.out.println("You win");
                            sabatogeLabel.setText("You won!");
                            compLabel.setText("I have lost");
                            
                        }

                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                } else {
                    //move all pawns in home to start box
                    if (blue1Home == true) {
                        resetImages(0, 1);
                        pictures[14][11].setImage(bluePawn1);
                        blue1Out = false;
                        pawnPositions.set(0, 14);
                        pawnPositions.set(1, 11);
                        submitButton.setDisable(false);

                    } else if (blue2Home == true) {
                        resetImages(2, 3);
                        pictures[13][11].setImage(bluePawn2);
                        blue2Out = false;
                        pawnPositions.set(2, 13);
                        pawnPositions.set(3, 11);
                        submitButton.setDisable(false);

                    } else if (blue3Home == true) {
                        resetImages(4, 5);
                        pictures[14][10].setImage(bluePawn3);
                        blue3Out = false;
                        pawnPositions.set(4, 14);
                        pawnPositions.set(5, 10);
                        submitButton.setDisable(false);

                    } else if (blue4Home == true) {
                        resetImages(6, 7);
                        pictures[13][10].setImage(bluePawn4);
                        blue4Out = false;
                        pawnPositions.set(6, 13);
                        pawnPositions.set(7, 10);
                        submitButton.setDisable(false);

                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                    }
                    turn = 2;
                    computer();

                }
            }
            if (Card.equals("CC3")) { //destermines moves
                if (diceNum == 1 || diceNum == 3 || diceNum == 5) {
                    //move one pawn of comp to start 
                    if (comp.greenPawn1Valid(computerPawnPositions) == true) {
                        if (userInputSabatoge.equals("1")) {
                            sabatogeLabel.setText("");
                            comp.change(1);
                            pictures[1][5].setImage(greenPawn1);

                            compHome(0);
                            //System.out.println("Pawn sent back home");
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        }

                    } else if (comp.greenPawn2Valid(computerPawnPositions) == true) {
                        if (userInputSabatoge.equals("2")) {
                            sabatogeLabel.setText("");
                            comp.change(2);
                            pictures[2][5].setImage(greenPawn2);

                            compHome(2);
                            //System.out.println("Pawn sent back home");
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        }

                    } else if (comp.greenPawn3Valid(computerPawnPositions) == true) {
                        if (userInputSabatoge.equals("3")) {
                            sabatogeLabel.setText("");
                            comp.change(3);
                            pictures[1][4].setImage(greenPawn3);

                            compHome(4);
                            System.out.println("Pawn sent back home");
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        }

                    } else if (comp.greenPawn4Valid(computerPawnPositions) == true) {
                        if (userInputSabatoge.equals("4")) {
                            sabatogeLabel.setText("");
                            comp.change(4);
                            pictures[2][4].setImage(greenPawn4);
                            compHome(6);
                            System.out.println("Pawn sent back home");
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        }
                        if (comp.greenPawn1Valid(computerPawnPositions) == false && userInputSabatoge.equals("1")) {
                            sabatogeLabel.setText("Invalid Move");
                        }
                        if (comp.greenPawn2Valid(computerPawnPositions) == false && userInputSabatoge.equals("2")) {
                            sabatogeLabel.setText("Invalid Move");
                        }
                        if (comp.greenPawn3Valid(computerPawnPositions) == false && userInputSabatoge.equals("3")) {
                            sabatogeLabel.setText("Invalid Move");
                        }
                        if (comp.greenPawn4Valid(computerPawnPositions) == false && userInputSabatoge.equals("4")) {
                            sabatogeLabel.setText("Invalid Move");
                        }

                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                } else {
                    //move one pawn of yours abck to start
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
                        if (userInputSabatoge.equals("1") && blue1Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(0, 1);
                            pictures[14][11].setImage(bluePawn1);
                            blue1Out = false;
                            pawnPositions.set(0, 14);
                            pawnPositions.set(1, 11);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("2") && blue2Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(2, 3);
                            pictures[13][11].setImage(bluePawn2);
                            blue2Out = false;
                            pawnPositions.set(2, 13);
                            pawnPositions.set(3, 11);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("3") && blue3Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(4, 5);
                            pictures[14][10].setImage(bluePawn3);
                            blue3Out = false;
                            pawnPositions.set(4, 14);
                            pawnPositions.set(5, 10);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();

                        } else if (userInputSabatoge.equals("4") && blue4Valid == true) {
                            sabatogeLabel.setText("");
                            resetImages(6, 7);
                            pictures[13][10].setImage(bluePawn4);
                            blue4Out = false;
                            pawnPositions.set(6, 13);
                            pawnPositions.set(7, 10);
                            submitButton.setDisable(false);
                            turn = 2;
                            computer();
                        } else {
                            sabatogeLabel.setText("Invalid move");
                        }
                    } else {
                        sabatogeLabel.setText("Turn skipped since no valid moves");
                        turn = 2;
                        submitButton.setDisable(false);
                        computer();
                    }
                }
            }
        } else {
            sabatogeLabel.setText("Invalid Input");
           
        }

    }

    private void backCorner(int row, int col) { //moving pawns to nearest back corner
        resetImages(row, col);
        if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(row) == 0) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(row) == 15) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 15);

        }
        if (row == 0) { //setting new images
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn1);
        } else if (row == 2) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn2);
        } else if (row == 4) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn3);
        } else if (row == 6) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn4);
        }

    }

    private void nearestCorner(int row, int col) { //moving pawn to nearest corner
        resetImages(row, col);
        //looking at positions of pawn and then moving to nearest corner
        if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(row) == 0) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 15);

        } else if (pawnPositions.get(row) == 15) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(col) == 0) {
            pawnPositions.set(row, 0);
            pawnPositions.set(col, 0);

        } else if (pawnPositions.get(col) == 15) {
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 15);

        }
        if (row == 0) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn1);
        } else if (row == 2) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn2);
        } else if (row == 4) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn3);
        } else if (row == 6) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(bluePawn4);
        }
    }

    private void playerStart(int i) { //sending pawn back to start
        sabatogeLabel.setText("Sent own pawn back to start");
        if (i == 0) {
            resetImages(i, i + 1);
            blue1Out = false;
            pawnPositions.set(0, 14);
            pawnPositions.set(1, 11);
        }
        if (i == 2) {
            resetImages(i, i + 1);
            blue2Out = false;
            pawnPositions.set(2, 13);
            pawnPositions.set(3, 11);

        }
        if (i == 4) {
            resetImages(i, i + 1);
            blue3Out = false;
            pawnPositions.set(4, 14);
            pawnPositions.set(5, 10);

        }
        if (i == 6) {
            resetImages(i, i + 1);
            blue4Out = false;
            pawnPositions.set(6, 13);
            pawnPositions.set(7, 10);

        }
    }

    private void special(int row, int col) { //looking at sliders, sharing spot, sliding through spot
        for (int i = 0; i < 8; i += 2) { //looking at each pawn
            //looking if pawns are sharing a spot
            if (pawnPositions.get(row) == computerPawnPositions.get(i) && pawnPositions.get(col) == computerPawnPositions.get(i + 1)) {
                compHome(i);
            }
            if (row != i) {
                if (pawnPositions.get(row) == pawnPositions.get(i) && pawnPositions.get(col) == pawnPositions.get(i + 1)) {
                    if(pawnPositions.get(row)==8 && pawnPositions.get(col)==12){
                        System.out.println("dont send back");
                    }
                    else{
                        playerStart(i);
                    }  
                }
            }
        }
        //looking at different sliders
        if (pawnPositions.get(row) == 14 && pawnPositions.get(col) == 0) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(row, 11);
            pictures[14][0].setImage(upArrow);
            if (row == 0) {
                pictures[11][0].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[11][0].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[11][0].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[11][0].setImage(bluePawn4);
            }
            //checking of pawns are being slided throgh so the can be sent back to home
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) > 11 && computerPawnPositions.get(i) < 14 && computerPawnPositions.get(i + 1) == 0) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midUD);
                    compHome(i);
                   
                }
                if (row != i) {
                    if (pawnPositions.get(i) > 11 && pawnPositions.get(i) < 14 && pawnPositions.get(i + 1) == 0) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midUD);
                        playerStart(i);
                    }

                }
            }

        }
        if (pawnPositions.get(row) == 6 && pawnPositions.get(col) == 0) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(row, 2);
            pictures[6][0].setImage(upArrow);
            if (row == 0) {
                pictures[2][0].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[2][0].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[2][0].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[2][0].setImage(bluePawn4);
            }
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) > 2 && computerPawnPositions.get(i) < 6 && computerPawnPositions.get(i + 1) == 0) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midUD);
                    compHome(i);
                    
                }
                if (row != i) {
                    if (pawnPositions.get(i) > 2 && pawnPositions.get(i) < 6 && pawnPositions.get(i + 1) == 0) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midUD);
                        playerStart(i);
                    }

                }
            }

        }
        if (pawnPositions.get(row) == 1 && pawnPositions.get(col) == 15) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(row, 4);
            pictures[1][15].setImage(downArrow);
            if (row == 0) {
                pictures[4][15].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[4][15].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[4][15].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[4][15].setImage(bluePawn4);
            }
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) > 1 && computerPawnPositions.get(i) < 4 && computerPawnPositions.get(i + 1) == 15) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midUD);
                    compHome(i);
                   
                }
                if (row != i) {
                    if (pawnPositions.get(i) > 1 && pawnPositions.get(i) < 4 && pawnPositions.get(i + 1) == 15) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midUD);
                        playerStart(i);
                    }

                }
            }
        }
        if (pawnPositions.get(row) == 9 && pawnPositions.get(col) == 15) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(row, 13);
            pictures[9][15].setImage(downArrow);
            if (row == 0) {
                pictures[13][15].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[13][15].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[13][15].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[13][15].setImage(bluePawn4);
            }
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) > 9 && computerPawnPositions.get(i) < 13 && computerPawnPositions.get(i + 1) == 15) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midUD);
                    
                    compHome(i);
                    
                }
                if (row != i) {
                    if (pawnPositions.get(i) > 9 && pawnPositions.get(i) < 13 && pawnPositions.get(i + 1) == 15) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midUD);
                        playerStart(i);
                    }

                }
            }
        }
        if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 1) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(col, 4);
            pictures[0][1].setImage(rightArrow);
            if (row == 0) {
                pictures[0][4].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[0][4].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[0][4].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[0][4].setImage(bluePawn4);
            }
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) == 0 && computerPawnPositions.get(i + 1) > 1 && computerPawnPositions.get(i + 1) < 4) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midLR);
                    compHome(i);
                }
                if (row != i) {
                    if (pawnPositions.get(i) == 0 && pawnPositions.get(i + 1) > 1 && pawnPositions.get(i + 1) < 4) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midLR);
                        playerStart(i);
                    }

                }
            }
        }
        if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 9) {
            sabatogeLabel.setText("Slided on slider");
            pawnPositions.set(col, 13);
            pictures[0][9].setImage(rightArrow);
            if (row == 0) {
                pictures[0][13].setImage(bluePawn1);
            }
            if (row == 2) {
                pictures[0][13].setImage(bluePawn2);
            }
            if (row == 4) {
                pictures[0][13].setImage(bluePawn3);
            }
            if (row == 6) {
                pictures[0][13].setImage(bluePawn4);
            }
            for (int i = 0; i < 8; i += 2) {
                if (computerPawnPositions.get(i) == 0 && computerPawnPositions.get(i + 1) > 9 && computerPawnPositions.get(i + 1) < 13) {
                    pictures[computerPawnPositions.get(i)][computerPawnPositions.get(i + 1)].setImage(midLR);
                    compHome(i);
                }
                if (row != i) {
                    if (pawnPositions.get(i) == 0 && pawnPositions.get(i + 1) > 9 && pawnPositions.get(i + 1) < 13) {
                        pictures[pawnPositions.get(i)][pawnPositions.get(i + 1)].setImage(midLR);
                        playerStart(i);
                    }

                }
            }
        }
        for (int i = 0; i < 8; i += 2) {
            if (pawnPositions.get(row) == computerPawnPositions.get(i) && pawnPositions.get(col) == computerPawnPositions.get(i + 1)) {
                compHome(i);
            }
            if (row != i) {
                if (pawnPositions.get(row) == pawnPositions.get(i) && pawnPositions.get(col) == pawnPositions.get(i + 1)) {
                    if(pawnPositions.get(row)==8 && pawnPositions.get(col)==12){
                        
                    }
                    else{
                        playerStart(i);
                    }  
                }
            }
        }

    }

    private void compHome(int i) {  //sending computer back to start
        sabatogeLabel.setText("Sent opponent back to start");
        //resetImages(i, i + 1);
        resetCompImages(i, i + 1);
        if (i == 0) {
            pictures[1][5].setImage(greenPawn1);
            computerPawnPositions.set(0, 1);
            computerPawnPositions.set(1, 5);
            comp.green1Start();
        }
        if (i == 2) {
            pictures[2][5].setImage(greenPawn2);
            computerPawnPositions.set(2, 2);
            computerPawnPositions.set(3, 5);
            comp.green2Start();
        }
        if (i == 4) {
            pictures[1][4].setImage(greenPawn3);
            computerPawnPositions.set(4, 1);
            computerPawnPositions.set(5, 4);
            comp.green3Start();
        }
        if (i == 6) {
            pictures[2][4].setImage(greenPawn4);
            computerPawnPositions.set(6, 2);
            computerPawnPositions.set(7, 4);
            comp.green4Start();
        }

    }

    private void resetImages(int row, int col) { //reseting the players images 
        if (pawnPositions.get(row) > 9 && pawnPositions.get(row) < 15 && pawnPositions.get(col) == 13) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(darkBlueBackground);
        } else if (pawnPositions.get(col) == 2 && pawnPositions.get(row) > 0 && pawnPositions.get(row) < 6) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(greenBackground);
        } else if (pawnPositions.get(col) == 12 && (pawnPositions.get(row) == 9 || pawnPositions.get(row) == 8)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(darkBlueBackground);
        } else if ((pawnPositions.get(row) == 13 && pawnPositions.get(col) == 11) || (pawnPositions.get(row) == 14 && pawnPositions.get(col) == 11) || (pawnPositions.get(row) == 13 && pawnPositions.get(col) == 10) || (pawnPositions.get(row) == 14 && pawnPositions.get(col) == 10)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(darkBlueBackground);
        } else if (pawnPositions.get(col) == 3 && (pawnPositions.get(row) == 6 || pawnPositions.get(row) == 7)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(greenBackground);
        } else if ((pawnPositions.get(row) == 15 && pawnPositions.get(col) == 11) || (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 2) || (pawnPositions.get(row) == 11 && pawnPositions.get(col) == 0) || (pawnPositions.get(row) == 2 && pawnPositions.get(col) == 0) || (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 4) || (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 13) || (pawnPositions.get(row) == 4 && pawnPositions.get(col) == 15) || (pawnPositions.get(row) == 13 && pawnPositions.get(col) == 15)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(circle);
        } else if ((pawnPositions.get(row) == 15 && pawnPositions.get(col) == 14) || (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 16)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(leftArrow);
        } else if ((pawnPositions.get(row) == 14 && pawnPositions.get(col) == 0) || (pawnPositions.get(row) == 6 && pawnPositions.get(col) == 0)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(upArrow);
        } else if ((pawnPositions.get(row) == 0 && pawnPositions.get(col) == 1) || (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 9)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(rightArrow);
        } else if ((pawnPositions.get(row) == 1 && pawnPositions.get(col) == 15) || (pawnPositions.get(row) == 9 && pawnPositions.get(col) == 15)) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(downArrow);
        } else if (((pawnPositions.get(row) == 15) && (pawnPositions.get(col) == 13 || pawnPositions.get(col) == 12 || pawnPositions.get(col) == 5 || pawnPositions.get(col) == 4 || pawnPositions.get(col) == 3)) || ((pawnPositions.get(row) == 0) && (pawnPositions.get(col) == 2 || pawnPositions.get(col) == 3 || pawnPositions.get(col) == 10 || pawnPositions.get(col) == 11 || pawnPositions.get(col) == 12))) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(midLR);
        } else if (((pawnPositions.get(col) == 0) && (pawnPositions.get(row) == 13 || pawnPositions.get(row) == 12 || pawnPositions.get(row) == 5 || pawnPositions.get(row) == 4 || pawnPositions.get(row) == 3)) || ((pawnPositions.get(col) == 15) && (pawnPositions.get(row) == 2 || pawnPositions.get(row) == 3 || pawnPositions.get(row) == 10 || pawnPositions.get(row) == 11 || pawnPositions.get(row) == 12))) {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(midUD);
        } else {
            pictures[pawnPositions.get(row)][pawnPositions.get(col)].setImage(whiteBackground);
        }

    }

    @FXML
    private void movePawn1(ActionEvent event) { //moving pawn one
        if (blue1Out == true && movesLeft > 0) {
            board(bluePawn1, 0, 1); //moving pawn
            movesLeft--;
            countPawns++;
            if (move1Pawn == true && countPawns == 1) { //diablimng other pawns since only one pawn can be used
                movePawn2Button.setDisable(true);
                movePawn3Button.setDisable(true);
                movePawn4Button.setDisable(true);
            }

            if (movesLeft == 0) { //changing turn
                for (int i = 0; i < 8; i += 2) {
                    special(i, i + 1);
                }
                turn = 2;
                computer();
            }
        }

        movesLabel.setText(Integer.toString(movesLeft));

    }

    @FXML
    private void movePawn2(ActionEvent event) { //mves pawn 2
        if (blue2Out == true && movesLeft > 0) {
            board(bluePawn2, 2, 3);
            movesLeft--;
            countPawns++;
            if (move1Pawn == true && countPawns == 1) {
                movePawn1Button.setDisable(true);
                movePawn3Button.setDisable(true);
                movePawn4Button.setDisable(true);
            }
            if (movesLeft == 0) {
                for (int i = 0; i < 8; i += 2) {
                    special(i, i + 1);
                }
                turn = 2;
                computer();
            }
        }
        movesLabel.setText(Integer.toString(movesLeft));

    }

    @FXML
    private void movePawn3(ActionEvent event) { //moving the thrid pawn
        if (blue3Out == true && movesLeft > 0) {
            board(bluePawn3, 4, 5);
            movesLeft--;
            countPawns++;
            if (move1Pawn == true && countPawns == 1) {
                movePawn1Button.setDisable(true);
                movePawn2Button.setDisable(true);
                movePawn4Button.setDisable(true);
            }
            if (movesLeft == 0) {
                for (int i = 0; i < 8; i += 2) {
                    special(i, i + 1);
                }
                turn = 2;
                computer();
            }
        }
        movesLabel.setText(Integer.toString(movesLeft));

    }

    @FXML
    private void movePawn4(ActionEvent event) { //moving the forth pawn
        if (blue4Out == true && movesLeft > 0) {
            board(bluePawn4, 6, 7);
            movesLeft--;
            countPawns++;
            if (move1Pawn == true && countPawns == 1) {
                movePawn1Button.setDisable(true);
                movePawn2Button.setDisable(true);
                movePawn3Button.setDisable(true);
            }
            if (movesLeft == 0) {
                for (int i = 0; i < 8; i += 2) {
                    special(i, i + 1);
                }
                turn = 2;
                computer();
            }
        }
        movesLabel.setText(Integer.toString(movesLeft));

    }

    private void board(Image pawn, int row, int col) { //moving pawn on board
        if (blue1Out == true && counter == 1) { // removing pawn from start box. next four if statemnts
            pictures[15][11].setImage(pawn);
            pictures[14][11].setImage(darkBlueBackground);
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 11);
            counter = 0;
        } else if (blue2Out == true && counter == 2) {
            pictures[15][11].setImage(pawn);
            pictures[13][11].setImage(darkBlueBackground);
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 11);
            counter = 0;
        } else if (blue3Out == true && counter == 3) {
            pictures[15][11].setImage(pawn);
            pictures[14][10].setImage(darkBlueBackground);
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 11);
            counter = 0;
        } else if (blue4Out == true && counter == 4) {
            pictures[15][11].setImage(pawn);
            pictures[13][10].setImage(darkBlueBackground);
            pawnPositions.set(row, 15);
            pawnPositions.set(col, 11);
            counter = 0;
        } else { //pawn is on the board
            if (pawnPositions.get(row) > 8 && pawnPositions.get(col) == 13) { //in saftey zone
                pawnPositions.set(row, pawnPositions.get(row) - 1);
                pictures[pawnPositions.get(row)][13].setImage(pawn);
                if (pawnPositions.get(row) + 1 == 15) {
                    pictures[pawnPositions.get(row) + 1][13].setImage(midLR);
                } else {
                    pictures[pawnPositions.get(row) + 1][13].setImage(darkBlueBackground);
                }
                if (pawnPositions.get(row) == 9) {
                    System.out.println("You have reached home!");
                    if (row == 0) {
                        blue1Home = true;
                    }
                    if (row == 2) {
                        blue2Home = true;
                    }
                    if (row == 4) {
                        blue3Home = true;
                    }
                    if (row == 6) {
                        blue4Home = true;
                    }
                    homeCounter++;
                    pictures[8][12].setImage(pawn);
                    pictures[pawnPositions.get(row)][13].setImage(darkBlueBackground);
                    pawnPositions.set(row, 8);
                    pawnPositions.set(col, 12);
                    if (homeCounter == 4) { //dealing with win 
                        playerHomeLabel.setText(Integer.toString(homeCounter));
                        regCardButton.setDisable(true);
                        System.out.println("You have won");
                        sabatogeLabel.setText("You won!");
                        compLabel.setText("I have lost");
                    }
                }
            } else if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 0) { //next four deal with corners
                pictures[14][0].setImage(pawn);
                pictures[15][0].setImage(whiteBackground);
                pawnPositions.set(row, 14);
                pawnPositions.set(col, 0);

            } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 0) {
                pictures[0][1].setImage(pawn);
                pictures[0][0].setImage(whiteBackground);
                pawnPositions.set(row, 0);
                pawnPositions.set(col, 1);

            } else if (pawnPositions.get(row) == 0 && pawnPositions.get(col) == 15) {
                pictures[1][15].setImage(pawn);
                pictures[0][15].setImage(whiteBackground);
                pawnPositions.set(row, 1);
                pawnPositions.set(col, 15);

            } else if (pawnPositions.get(row) == 15 && pawnPositions.get(col) == 15) {
                pictures[15][14].setImage(pawn);
                pictures[15][15].setImage(whiteBackground);
                pawnPositions.set(row, 15);
                pawnPositions.set(col, 14);

            } else { //deals with moving forward
                if (pawnPositions.get(row) == 15) {
                    pawnPositions.set(col, pawnPositions.get(col) - 1);
                    pictures[15][pawnPositions.get(col)].setImage(pawn);

                    if (pawnPositions.get(col) + 1 == 6 || pawnPositions.get(col) + 1 == 14) {
                        pictures[15][pawnPositions.get(col) + 1].setImage(leftArrow);
                    } else if (pawnPositions.get(col) + 1 == 2 || pawnPositions.get(col) + 1 == 11) {
                        pictures[15][pawnPositions.get(col) + 1].setImage(circle);
                    } else if ((pawnPositions.get(col) + 1 > 2 && pawnPositions.get(col) + 1 < 6) || (pawnPositions.get(col) + 1 > 11 && pawnPositions.get(col) + 1 < 14)) {
                        pictures[15][pawnPositions.get(col) + 1].setImage(midLR);
                    } else {
                        pictures[15][pawnPositions.get(col) + 1].setImage(whiteBackground);
                    }

                } else if (pawnPositions.get(row) == 0) {
                    pawnPositions.set(col, pawnPositions.get(col) + 1);
                    pictures[0][pawnPositions.get(col)].setImage(pawn);

                    if (pawnPositions.get(col) - 1 == 1 || pawnPositions.get(col) - 1 == 9) {
                        pictures[0][pawnPositions.get(col) - 1].setImage(rightArrow);
                    } else if (pawnPositions.get(col) - 1 == 4 || pawnPositions.get(col) - 1 == 13) {
                        pictures[0][pawnPositions.get(col) - 1].setImage(circle);
                    } else if ((pawnPositions.get(col) - 1 > 1 && pawnPositions.get(col) - 1 < 4) || (pawnPositions.get(col) - 1 > 9 && pawnPositions.get(col) - 1 < 13)) {
                        pictures[0][pawnPositions.get(col) - 1].setImage(midLR);
                    } else {
                        pictures[0][pawnPositions.get(col) - 1].setImage(whiteBackground);
                    }

                } else if (pawnPositions.get(col) == 0) {
                    pawnPositions.set(row, pawnPositions.get(row) - 1);
                    pictures[pawnPositions.get(row)][0].setImage(pawn);
                    pictures[pawnPositions.get(row) + 1][0].setImage(whiteBackground);
                    //pawnPositions.set(1,pawnPositions.get(1)-1);

                    if (pawnPositions.get(row) + 1 == 14 || pawnPositions.get(row) + 1 == 6) {
                        pictures[pawnPositions.get(row) + 1][0].setImage(upArrow);
                    } else if (pawnPositions.get(row) + 1 == 11 || pawnPositions.get(row) + 1 == 2) {
                        pictures[pawnPositions.get(row) + 1][0].setImage(circle);
                    } else if ((pawnPositions.get(row) + 1 > 2 && pawnPositions.get(row) + 1 < 6) || (pawnPositions.get(row) + 1 > 11 && pawnPositions.get(row) + 1 < 14)) {
                        pictures[pawnPositions.get(row) + 1][0].setImage(midUD);
                    } else {
                        pictures[pawnPositions.get(row) + 1][0].setImage(whiteBackground);
                    }

                } else if (pawnPositions.get(col) == 15) {
                    pawnPositions.set(row, pawnPositions.get(row) + 1);
                    pictures[pawnPositions.get(row)][15].setImage(pawn);
                    pictures[pawnPositions.get(row) - 1][15].setImage(whiteBackground);

                    if (pawnPositions.get(row) - 1 == 1 || pawnPositions.get(row) - 1 == 9) {
                        pictures[pawnPositions.get(row) - 1][15].setImage(downArrow);
                    } else if (pawnPositions.get(row) - 1 == 4 || pawnPositions.get(row) - 1 == 13) {
                        pictures[pawnPositions.get(row) - 1][15].setImage(circle);
                    } else if ((pawnPositions.get(row) - 1 > 1 && pawnPositions.get(row) - 1 < 4) || (pawnPositions.get(row) - 1 > 9 && pawnPositions.get(row) - 1 < 13)) {
                        pictures[pawnPositions.get(row) - 1][15].setImage(midUD);
                    } else {
                        pictures[pawnPositions.get(row) - 1][15].setImage(whiteBackground);
                    }
                    //pawnPositions.set(1,pawnPositions.get(1)-1);

                }

            }

        }
        //in the case that while moving pawn the other pawn gets covered up

        pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
        pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
        pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
        pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
        pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
        pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
        pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
        pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);

    }

    private void resetCompImages(int row, int col) { //resting computer images 
        if (computerPawnPositions.get(row) > 9 && computerPawnPositions.get(row) < 15 && computerPawnPositions.get(col) == 13) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(darkBlueBackground);
        } else if (computerPawnPositions.get(col) == 2 && computerPawnPositions.get(row) > 0 && computerPawnPositions.get(row) < 6) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(greenBackground);
        } else if (computerPawnPositions.get(col) == 12 && (computerPawnPositions.get(row) == 9 || computerPawnPositions.get(row) == 8)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(darkBlueBackground);
        } else if (computerPawnPositions.get(col) == 3 && (computerPawnPositions.get(row) == 6 || computerPawnPositions.get(row) == 7)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(greenBackground);
        } else if ((computerPawnPositions.get(row) == 1 && computerPawnPositions.get(col) == 4) || (computerPawnPositions.get(row) == 2 && computerPawnPositions.get(col) == 4) || (computerPawnPositions.get(row) == 2 && computerPawnPositions.get(col) == 5) || (computerPawnPositions.get(row) == 1 && computerPawnPositions.get(col) == 5)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(greenBackground);
        } else if ((computerPawnPositions.get(row) == 15 && computerPawnPositions.get(col) == 11) || (computerPawnPositions.get(row) == 15 && computerPawnPositions.get(col) == 2) || (computerPawnPositions.get(row) == 11 && computerPawnPositions.get(col) == 0) || (computerPawnPositions.get(row) == 2 && computerPawnPositions.get(col) == 0) || (computerPawnPositions.get(row) == 0 && computerPawnPositions.get(col) == 4) || (computerPawnPositions.get(row) == 0 && computerPawnPositions.get(col) == 13) || (computerPawnPositions.get(row) == 4 && computerPawnPositions.get(col) == 15) || (computerPawnPositions.get(row) == 13 && computerPawnPositions.get(col) == 15)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(circle);
        } else if ((computerPawnPositions.get(row) == 15 && computerPawnPositions.get(col) == 14) || (computerPawnPositions.get(row) == 15 && computerPawnPositions.get(col) == 16)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(leftArrow);
        } else if ((computerPawnPositions.get(row) == 14 && computerPawnPositions.get(col) == 0) || (computerPawnPositions.get(row) == 6 && computerPawnPositions.get(col) == 0)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(upArrow);
        } else if ((computerPawnPositions.get(row) == 0 && computerPawnPositions.get(col) == 1) || (computerPawnPositions.get(row) == 0 && computerPawnPositions.get(col) == 9)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(rightArrow);
        } else if ((computerPawnPositions.get(row) == 1 && computerPawnPositions.get(col) == 15) || (computerPawnPositions.get(row) == 9 && computerPawnPositions.get(col) == 15)) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(downArrow);
        } else if (((computerPawnPositions.get(row) == 15) && (computerPawnPositions.get(col) == 13 || computerPawnPositions.get(col) == 12 || computerPawnPositions.get(col) == 5 || computerPawnPositions.get(col) == 4 || computerPawnPositions.get(col) == 3)) || ((computerPawnPositions.get(row) == 0) && (computerPawnPositions.get(col) == 2 || computerPawnPositions.get(col) == 3 || computerPawnPositions.get(col) == 10 || computerPawnPositions.get(col) == 11 || computerPawnPositions.get(col) == 12))) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(midLR);
        } else if (((computerPawnPositions.get(col) == 0) && (computerPawnPositions.get(row) == 13 || computerPawnPositions.get(row) == 12 || computerPawnPositions.get(row) == 5 || computerPawnPositions.get(row) == 4 || computerPawnPositions.get(row) == 3)) || ((computerPawnPositions.get(col) == 15) && (computerPawnPositions.get(row) == 2 || computerPawnPositions.get(row) == 3 || computerPawnPositions.get(row) == 10 || computerPawnPositions.get(row) == 11 || computerPawnPositions.get(row) == 12))) {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(midUD);
        } else {
            pictures[computerPawnPositions.get(row)][computerPawnPositions.get(col)].setImage(whiteBackground);
            //System.out.println("yayay computer reseting");
        }

    }

    public void computer() { //dealing with computer code
        pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
        pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
        pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
        pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
        turnLabel.setText("Computer");
        for (int i = 0; i < 8; i++) { //backup array
            computerPP.set(i, computerPawnPositions.get(i));
        }
        playerHomeLabel.setText(Integer.toString(homeCounter));

        System.out.println(movesLeft);
        //reseting buttons
        movesLeft = 0;
        tempSabatoge = 0;
        userSabatogeComp = false;
        continuing = false;
        move1Pawn = false;
        countPawns = 0;
        regCardButton.setDisable(true);
        chanceCardButton.setDisable(true);
        submitButton.setDisable(true);
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        diceButton.setDisable(true);
        movePawn1Button.setDisable(true);
        movePawn2Button.setDisable(true);
        movePawn3Button.setDisable(true);
        movePawn4Button.setDisable(true);
        if (homeCounter == 4) { //checking for win
            playerHomeLabel.setText(Integer.toString(homeCounter));
            regCardButton.setDisable(true);
            sabatogeLabel.setText("You Won! To play again you can reset.");
            compLabel.setText("I have lost");
            startTime = -1;
        } else { //start computer turn
            playerHomeLabel.setText(Integer.toString(homeCounter));
            startTime = System.nanoTime();
        }

        start();

    }

    public void start() { //timer. Callinnh computer class
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (startTime > 0) {
                    int x = (int) (Math.floor(startTime / 1000000000));
                    int y = (int) (now / 1000000000);
                    int seconds = y - x;

//                    double z = now-startTime;
//                    System.out.println(z);
                    if (seconds > 2.5) {
                        diceLabel.setText("");
                        sabatogeLabel.setText("");
                        String compCard;
                        //resetimg images
                        resetCompImages(0, 1); 
                        resetCompImages(2, 3);
                        resetCompImages(4, 5);
                        resetCompImages(6, 7);
                        resetImages(0, 1); 
                        resetImages(2, 3);
                        resetImages(4, 5);
                        resetImages(6, 7);
                        //pictures[computerPawnPositions.get(row)][15].setImage(whiteBackground);
                        compCard = cards.getRegCard();
                        imgCard.setImage(new Image("resources/" + compCard + ".png")); //showing the card the computer pciked up
                        System.out.println("Compyter Turn" + compCard);
                        System.out.println("Before" + computerPawnPositions);
                        comp.theCard(computerPawnPositions, computerPP, compCard, pawnPositions, blue1Out, blue2Out, blue3Out, blue4Out); //sendimng card to class
                        if (comp.pickUpChanceCard().equals("true")) {
                            System.out.println("Picked Up chanc card");
                            compCard = cards.getChanceCard();
                            imgCard.setImage(new Image("resources/" + compCard + ".png"));
                            comp.theCard(computerPawnPositions, computerPP, compCard, pawnPositions, blue1Out, blue2Out, blue3Out, blue4Out);
                        }
                        //getting updated psoitions
                        computerPawnPositions = comp.updatedPosition();
                        pawnPositions = comp.updatedPlayerPosition();
                        if (comp.checkWin() == 4) { //checking for computer win
                            compHomeLabel.setText(Integer.toString(homeCounter));
                            compLabel.setText("I have won!");
                            sabatogeLabel.setText("You lost. To play again you can reset");
                            regCardButton.setDisable(true);
                            System.out.println("Comp Won");
                        }
                        else{
                            compHomeLabel.setText(Integer.toString(comp.checkWin()));
                        }
                        blue1Out = comp.bluePawn1Start(); //updating just in case oppinent was sent back
                        blue2Out = comp.bluePawn2Start();
                        blue3Out = comp.bluePawn3Start();
                        blue4Out = comp.bluePawn4Start();
                        System.out.println("after" + computerPawnPositions);
                        //reseting images
                        pictures[computerPawnPositions.get(0)][computerPawnPositions.get(1)].setImage(greenPawn1);
                        pictures[computerPawnPositions.get(2)][computerPawnPositions.get(3)].setImage(greenPawn2);
                        pictures[computerPawnPositions.get(4)][computerPawnPositions.get(5)].setImage(greenPawn3);
                        pictures[computerPawnPositions.get(6)][computerPawnPositions.get(7)].setImage(greenPawn4);
                        if (blue1Out == false) {
                            resetImages(0, 1);
                            pawnPositions.set(0, 14);
                            pawnPositions.set(1, 11);

                        } else if (blue2Out == false) {
                            resetImages(2, 3);

                            pawnPositions.set(2, 13);
                            pawnPositions.set(3, 11);

                        } else if (blue3Out == false) {
                            resetImages(4, 5);
                            pawnPositions.set(4, 14);
                            pawnPositions.set(5, 10);

                        } else if (blue4Out == false) {
                            resetImages(6, 7);
                            pawnPositions.set(6, 13);
                            pawnPositions.set(7, 10);

                        }
                        //reseting images
                        pictures[pawnPositions.get(0)][pawnPositions.get(1)].setImage(bluePawn1);
                        pictures[pawnPositions.get(2)][pawnPositions.get(3)].setImage(bluePawn2);
                        pictures[pawnPositions.get(4)][pawnPositions.get(5)].setImage(bluePawn3);
                        pictures[pawnPositions.get(6)][pawnPositions.get(7)].setImage(bluePawn4);
                        compLabel.setText(comp.compMove()); //showing what computer did
                        compSpecialLabel.setText(comp.compSpecial());
                        //System.out.println("moves"+movesLeft);
                        movesLabel.setText(Integer.toString(movesLeft));
                        //sabatogeLabel.setText(Integer.toString(movesLeft));
                        // System.out.println("moveafter"+movesLeft);
                        turn = 1;
                        regCardButton.setDisable(false);
                        turnLabel.setText("Player"); //players turn
                        startTime = -1; //getting out of statement
                        //chanceCardButton.setDisable(false);
                    }

                }
            }
        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chanceCardButton.setDisable(true);
        submitButton.setDisable(true);
        choice1Button.setDisable(true);
        choice2Button.setDisable(true);
        diceButton.setDisable(true);

        movePawn1Button.setDisable(true);
        movePawn2Button.setDisable(true);
        movePawn3Button.setDisable(true);
        movePawn4Button.setDisable(true);
        regCardButton.setDisable(true);
        resetingButton.setDisable(true);

    }

}
