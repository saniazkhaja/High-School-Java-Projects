/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rock.paper.and.scissors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author choudhary1645
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView imgPic, comImgPic;
    @FXML
    private Label losesLabel, winsLabel, tiesLabel;
    List<Integer> peopleArray = new ArrayList<>();
    List<Integer> computerArray = new ArrayList<>();

    int ties = 0;
    int wins = 0;
    int loses = 0;
    int easy = 0;
    int hard = 0;
    int hardCounter = 0;
    int computerInput;

    @FXML
    private void easy() {//checks to see if user chose easy
        easy = easy + 1;
    }

    @FXML
    private void hard() {//checks to see if user chose hard level
        hard = hard + 1;
    }

    @FXML
    private void Rock(ActionEvent event) {
        hardCounter = hardCounter + 1; //need at least 5 for data to start to check for patterns.
        imgPic.setImage(new Image("resources/Rock.jpg"));//shows user chose rock
        if (hard == 1 && hardCounter > 5) { //checks if user can move to hard level
            PlayGame hardGame = new PlayGame(peopleArray, computerArray); //passing arrays to class, to figure out the computers move
            computerInput = hardGame.hardLevel();
            if (computerInput == -1) { //no matches found so randomly chooses 
                computerInput = (int) (Math.random() * 3) + 1;
            }
        }
        if (easy == 1 || ((hardCounter <= 5) && hard == 1)) {//for easy level, or trying to collect data for hard level
            computerInput = (int) (Math.random() * 3) + 1;
        }
        PlayGame game = new PlayGame(1, computerInput, peopleArray, computerArray);
        String playGameOutput = game.easyLevel();//this decides if there was a win, lose or tie
        if (computerInput == 1) {//displays what computer chose
            comImgPic.setImage(new Image("resources/Rock.jpg"));
        } else if (computerInput == 2) {
            comImgPic.setImage(new Image("resources/Paper.jpg"));
        } else {
            comImgPic.setImage(new Image("resources/Scissors.jpg"));
        }
        if (playGameOutput.equals("tie")) {//keeps track of wins ties and loses
            ties = ties + 1;
        } else if (playGameOutput.equals("win")) {
            wins = wins + 1;
        } else {
            loses = loses + 1;
        }
        peopleArray.add(1); //adding what the player chose to array after everything has been completed
        computerArray.add(1);
        System.out.println(peopleArray);
        System.out.println(computerArray);
        System.out.println("Ties:" + ties + "  Wins:" + wins + "  Loses:" + loses);
        tiesLabel.setText(Integer.toString(ties)); //displaying ties wins and loses
        winsLabel.setText(Integer.toString(wins));
        losesLabel.setText(Integer.toString(loses));

    }

    @FXML
    private void Paper(ActionEvent event) { //similar to rock function, except the user chose paper, so 2 is passed to classes 
        hardCounter = hardCounter + 1;
        imgPic.setImage(new Image("resources/Paper.jpg"));
        if (hard == 1 && hardCounter > 5) {
            PlayGame hardGame = new PlayGame(peopleArray, computerArray);
            computerInput = hardGame.hardLevel();
            if (computerInput == -1) {
                computerInput = (int) (Math.random() * 3) + 1;
            }
        }
        if (easy == 1 || ((hardCounter < 5) && hard == 1)) {
            computerInput = (int) (Math.random() * 3) + 1;
        }
        PlayGame game = new PlayGame(2, computerInput, peopleArray, computerArray);
        String playGameOutput = game.easyLevel();
        if (computerInput == 1) {
            comImgPic.setImage(new Image("resources/Rock.jpg"));
        } else if (computerInput == 2) {
            comImgPic.setImage(new Image("resources/Paper.jpg"));
        } else {
            comImgPic.setImage(new Image("resources/Scissors.jpg"));
        }
        if (playGameOutput.equals("tie")) {
            ties = ties + 1;
        } else if (playGameOutput.equals("win")) {
            wins = wins + 1;
        } else {
            loses = loses + 1;
        }
        peopleArray.add(2);
        computerArray.add(2);
        System.out.println(peopleArray);
        System.out.println(computerArray);
        System.out.println("Ties:" + ties + "  Wins:" + wins + "  Loses:" + loses);
        tiesLabel.setText(Integer.toString(ties));
        winsLabel.setText(Integer.toString(wins));
        losesLabel.setText(Integer.toString(loses));

    }

    @FXML
    private void Scissors(ActionEvent event) {//similar to rock function, except the user chose scissors, so 3 is passed to classes 
        hardCounter = hardCounter + 1;
        imgPic.setImage(new Image("resources/Scissors.jpg"));
        if (hard == 1 && hardCounter > 5) {
            PlayGame hardGame = new PlayGame(peopleArray, computerArray);
            computerInput = hardGame.hardLevel();
            if (computerInput == -1) {
                computerInput = (int) (Math.random() * 3) + 1;
            }
        }
        if (easy == 1 || ((hardCounter < 5) && hard == 1)) {
            computerInput = (int) (Math.random() * 3) + 1;
        }
        PlayGame game = new PlayGame(3, computerInput, peopleArray, computerArray);
        String playGameOutput = game.easyLevel();
        if (computerInput == 1) {
            comImgPic.setImage(new Image("resources/Rock.jpg"));
        } else if (computerInput == 2) {
            comImgPic.setImage(new Image("resources/Paper.jpg"));
        } else {
            comImgPic.setImage(new Image("resources/Scissors.jpg"));
        }
        if (playGameOutput.equals("tie")) {
            ties = ties + 1;
        } else if (playGameOutput.equals("win")) {
            wins = wins + 1;
        } else {
            loses = loses + 1;
        }
        peopleArray.add(3);
        computerArray.add(3);
        System.out.println(peopleArray);
        System.out.println(computerArray);
        System.out.println("Ties:" + ties + "  Wins:" + wins + "  Loses:" + loses);
        tiesLabel.setText(Integer.toString(ties));
        winsLabel.setText(Integer.toString(wins));
        losesLabel.setText(Integer.toString(loses));

    }

    @FXML
    private void reset() { //resetes all variables and arrays so a new game can be played
        computerArray.clear();
        peopleArray.clear();
        ties = 0;
        wins = 0;
        loses = 0;
        easy = 0;
        hard = 0;
        hardCounter = 0;
        tiesLabel.setText("");
        winsLabel.setText("");
        losesLabel.setText("");
        imgPic.setImage(new Image("resources/Blank.jpg"));
        comImgPic.setImage(new Image("resources/Blank.jpg"));;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
