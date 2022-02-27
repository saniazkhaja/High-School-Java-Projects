/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthelloreversi.project;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Sania Khaja
 */
public class FXMLDocumentController implements Initializable {

    private ImageView[][] btn = new ImageView[6][6];
    private Label[][] label = new Label[6][6];
    int turn = 1;
    boolean validMove = false;
    boolean playing = false;
    int validRow = 0;
    int validColumn = 0;
    Image validPiece = new Image(("resources/Valid.jpg"));
    Image blankPiece = new Image(("resources/Blank.jpg"));
    int blackCounter;
    int whiteCounter;
    int compRow;
    int compColumn;

    @FXML
    private AnchorPane aPane;

    @FXML
    private GridPane gPane;

    @FXML
    private Label blackLabel, whiteLabel, winnerLabel;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                //Initializing 2D buttons with values i,j
                btn[i][j] = new ImageView();
                btn[i][j].setImage(new Image("resources/Blank.jpg"));
                btn[i][j].setFitHeight(50);
                btn[i][j].setFitWidth(50);
                gPane.add(btn[i][j], j, i);
                label[i][j] = new Label(String.valueOf("N"));

            }
        }
        btn[2][2].setImage(new Image("resources/Black.jpg"));
        btn[2][2].setFitHeight(50);
        btn[2][2].setFitWidth(50);
        label[2][2] = new Label(String.valueOf("B"));
        btn[3][3].setImage(new Image("resources/Black.jpg"));
        btn[3][3].setFitHeight(50);
        btn[3][3].setFitWidth(50);
        label[3][3] = new Label(String.valueOf("B"));
        btn[2][3].setImage(new Image("resources/WHITE.JPG"));
        btn[2][3].setFitHeight(50);
        btn[2][3].setFitWidth(50);
        label[2][3] = new Label(String.valueOf("W"));
        btn[3][2].setImage(new Image("resources/WHITE.JPG"));
        btn[3][2].setFitHeight(50);
        btn[3][2].setFitWidth(50);
        label[3][2] = new Label(String.valueOf("W"));

        gPane.setGridLinesVisible(true);

        gPane.setVisible(true);
        countPieces();
        checkPossibleMoves();

        EventHandler z = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                int row = GridPane.getRowIndex(((ImageView) t.getSource()));
                int column = GridPane.getColumnIndex(((ImageView) t.getSource()));

                if (turn == 1 && label[row][column].getText().equals("P")) {
                    playing = true;
                    Image piece = new Image(("resources/Black.jpg"));
                    ((ImageView) t.getSource()).setImage(piece);
                    String letterPiece = "B";
                    label[row][column] = new Label(String.valueOf(letterPiece));
                    changePieces(row, column, piece, letterPiece);
                    countPieces();
                    turn = 2;
                    validMove = false;
                    playing = false;
                    checkPossibleMoves();
                    //possible();
                }
                if (turn == 2) {
                    computer();
                }

            }

        };
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                btn[i][j].setOnMouseClicked(z);

            }
        }

    }

    private void changePieces(int row, int column, Image piece, String letterPiece) {
        boolean matchColumnDown = false;
        boolean matchColumnUp = false;
        boolean matchRowRight = false;
        boolean matchRowLeft = false;
        boolean matchDiagonalDownRight = false;
        boolean matchDiagonalUpLeft = false;
        boolean matchDiagonalUpRight = false;
        boolean matchDiagonalDownLeft = false;
        boolean oppisiteColorFound = false;

        int tempRowDown = row;
        int tempRowUp = row;
        int tempColumnRight = column;
        int tempColumnLeft = column;
        int tempDiagonalRowDownRight = row;
        int tempDiagonalColumnDownRight = column;
        int tempDiagonalRowUpLeft = row;
        int tempDiagonalColumnUpLeft = column;
        int tempDiagonalRowUpRight = row;
        int tempDiagonalColumnUpRight = column;
        int tempDiagonalRowDownLeft = row;
        int tempDiagonalColumnDownLeft = column;
        String oppisiteLetter = "B";

        if (letterPiece.equals("B")) {
            oppisiteLetter = "W";
        }
        for (int i = row + 1; i < 6; i++) {
            tempRowDown++;
            if (label[i][column].getText().equals(letterPiece)) {
                matchColumnDown = true;
                break;
            }
            if (label[i][column].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }
        }

        if (matchColumnDown == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchColumnDown == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            for (int i = row; i <= tempRowDown; i++) {
                btn[i][column].setImage((piece));
                btn[i][column].setFitHeight(50);
                btn[i][column].setFitWidth(50);
                label[i][column] = new Label(String.valueOf(letterPiece));
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            tempRowUp--;
            if (label[i][column].getText().equals(letterPiece)) {
                matchColumnUp = true;
                break;
            }
            if (label[i][column].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }
        }

        if (matchColumnUp == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchColumnUp == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            for (int i = row; i >= tempRowUp; i--) {
                btn[i][column].setImage((piece));
                btn[i][column].setFitHeight(50);
                btn[i][column].setFitWidth(50);
                label[i][column] = new Label(String.valueOf(letterPiece));
            }

        }

        ///////////
        for (int i = column + 1; i < 6; i++) {
            tempColumnLeft++;
            if (label[row][i].getText().equals(letterPiece)) {
                matchRowLeft = true;
                break;
            }
            if (label[row][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }

        }

        if (matchRowLeft == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchRowLeft == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            for (int i = column; i <= tempColumnLeft; i++) {
                btn[row][i].setImage((piece));
                btn[row][i].setFitHeight(50);
                btn[row][i].setFitWidth(50);
                label[row][i] = new Label(String.valueOf(letterPiece));
            }
        }

        for (int i = column - 1; i >= 0; i--) {
            tempColumnRight--;
            if (label[row][i].getText().equals(letterPiece)) {
                matchRowRight = true;
                break;
            }
            if (label[row][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }

        }

        if (matchRowRight == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchRowRight == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            for (int i = column; i >= tempColumnRight; i--) {
                btn[row][i].setImage((piece));
                btn[row][i].setFitHeight(50);
                btn[row][i].setFitWidth(50);
                label[row][i] = new Label(String.valueOf(letterPiece));
            }
        }

        ///////////////
        for (int i = column + 1; i < 6; i++) {
            if (tempDiagonalRowDownRight == 5) {
                break;
            }
            tempDiagonalColumnDownRight++;
            tempDiagonalRowDownRight++;

            if (label[tempDiagonalRowDownRight][i].getText().equals(letterPiece)) {
                matchDiagonalDownRight = true;
                break;
            }
            if (label[tempDiagonalRowDownRight][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }

        }
        if (matchDiagonalDownRight == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;

        }

        if (matchDiagonalDownRight == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            tempDiagonalRowDownRight = row;
            for (int i = column + 1; i <= tempDiagonalColumnDownRight; i++) {
                if (tempDiagonalRowDownRight == 5) {
                    break;
                }
                tempDiagonalRowDownRight++;
                btn[tempDiagonalRowDownRight][i].setImage((piece));
                btn[tempDiagonalRowDownRight][i].setFitHeight(50);
                btn[tempDiagonalRowDownRight][i].setFitWidth(50);
                label[tempDiagonalRowDownRight][i] = new Label(String.valueOf(letterPiece));
            }
        }

        for (int i = column - 1; i >= 0; i--) {
            if (tempDiagonalRowUpLeft == 0) {
                break;
            }
            tempDiagonalColumnUpLeft--;
            tempDiagonalRowUpLeft--;
            if (label[tempDiagonalRowUpLeft][i].getText().equals(letterPiece)) {
                matchDiagonalUpLeft = true;
                break;
            }
            if (label[tempDiagonalRowUpLeft][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }
        }

        if (matchDiagonalUpLeft == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchDiagonalUpLeft == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            tempDiagonalRowUpLeft = row;
            for (int i = column - 1; i >= tempDiagonalColumnUpLeft; i--) {
                if (tempDiagonalRowUpLeft == 0) {
                    break;
                }
                tempDiagonalRowUpLeft--;
                btn[tempDiagonalRowUpLeft][i].setImage((piece));
                btn[tempDiagonalRowUpLeft][i].setFitHeight(50);
                btn[tempDiagonalRowUpLeft][i].setFitWidth(50);
                label[tempDiagonalRowUpLeft][i] = new Label(String.valueOf(letterPiece));
            }
        }

        ///////////////
        for (int i = column + 1; i < 6; i++) {
            if (tempDiagonalRowUpRight == 0) {
                break;
            }
            tempDiagonalColumnUpRight++;
            tempDiagonalRowUpRight--;

            if (label[tempDiagonalRowUpRight][i].getText().equals(letterPiece)) {
                matchDiagonalUpRight = true;
                break;
            }
            if (label[tempDiagonalRowUpRight][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }

        }

        if (matchDiagonalUpRight == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchDiagonalUpRight == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            tempDiagonalRowUpRight = row;
            for (int i = column + 1; i <= tempDiagonalColumnUpRight; i++) {
                if (tempDiagonalRowUpRight == 0) {
                    break;
                }
                tempDiagonalRowUpRight--;
                btn[tempDiagonalRowUpRight][i].setImage((piece));
                btn[tempDiagonalRowUpRight][i].setFitHeight(50);
                btn[tempDiagonalRowUpRight][i].setFitWidth(50);
                label[tempDiagonalRowUpRight][i] = new Label(String.valueOf(letterPiece));
            }
        }

        for (int i = column - 1; i >= 0; i--) {
            if (tempDiagonalRowDownLeft == 5) {
                break;
            }
            tempDiagonalColumnDownLeft--;
            tempDiagonalRowDownLeft++;
            if (label[tempDiagonalRowDownLeft][i].getText().equals(letterPiece)) {
                matchDiagonalDownLeft = true;
                break;
            }
            if (label[tempDiagonalRowDownLeft][i].getText().equals(oppisiteLetter)) {
                oppisiteColorFound = true;
            } else {
                oppisiteColorFound = false;
                break;
            }
        }

        if (matchDiagonalDownLeft == true && oppisiteColorFound == true) {
            validMove = true;

        }
        if (playing == false) {
            oppisiteColorFound = false;
        }
        if (matchDiagonalDownLeft == true && oppisiteColorFound == true && playing == true) {
            oppisiteColorFound = false;
            tempDiagonalRowDownLeft = row;
            for (int i = column - 1; i >= tempDiagonalColumnDownLeft; i--) {
                if (tempDiagonalRowDownLeft == 5) {
                    break;
                }
                tempDiagonalRowDownLeft++;
                btn[tempDiagonalRowDownLeft][i].setImage((piece));
                btn[tempDiagonalRowDownLeft][i].setFitHeight(50);
                btn[tempDiagonalRowDownLeft][i].setFitWidth(50);
                label[tempDiagonalRowDownLeft][i] = new Label(String.valueOf(letterPiece));
            }
        }

        if (validMove == false && playing == false) {
            label[validRow][validColumn] = new Label(String.valueOf("N"));
        }
        if (validMove == true && playing == false) {
            label[validRow][validColumn] = new Label(String.valueOf("P"));
            if (turn == 1) {
                btn[validRow][validColumn].setImage((validPiece));
                btn[validRow][validColumn].setFitHeight(50);
                btn[validRow][validColumn].setFitWidth(50);
            }
            validMove = false;
        }

    }

    private void checkPossibleMoves() {
        playing = false;
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                validRow = i;
                validColumn = j;
                if (label[i][j].getText().equals("N") || label[i][j].getText().equals("P")) {
                    label[i][j] = new Label(String.valueOf("N"));
                    btn[i][j].setImage((blankPiece));
                    btn[i][j].setFitHeight(50);
                    btn[i][j].setFitWidth(50);

                    if (turn == 1) {
                        Image piece = new Image(("resources/Black.jpg"));
                        String letterPiece = "B";
                        label[i][j] = new Label(String.valueOf("B"));
                        changePieces(i, j, piece, letterPiece);

                    }
                    if (turn == 2) {
                        Image piece = new Image(("resources/WHITE.JPG"));
                        String letterPiece = "W";
                        label[i][j] = new Label(String.valueOf("W"));
                        changePieces(i, j, piece, letterPiece);

                    }

                }

            }
        }
    }

    private void countPieces() {
        blackCounter = 0;
        whiteCounter = 0;
        boolean done = false;
        int counter = 0;
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                if (label[i][j].getText().equals("B")) {
                    blackCounter++;
                }
                if (label[i][j].getText().equals("W")) {
                    whiteCounter++;
                }
            }
        }
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                if (label[i][j].getText().equals("P") || label[i][j].getText().equals("N")) {
                    counter++;
                    break;
                }
            }
        }
        if (counter == 0) {
            System.out.println("Game over.");
            if (blackCounter > whiteCounter) {
                winnerLabel.setText("You won!");
            } else {
                winnerLabel.setText("You Lost.");
            }
        }
        blackLabel.setText(Integer.toString(blackCounter));
        whiteLabel.setText(Integer.toString(whiteCounter));

    }

    public void computer() {
        if (label[0][0].getText().equals("P")) {
            compRow = 0;
            compColumn = 0;
        } else if (label[0][5].getText().equals("P")) {
            compRow = 0;
            compColumn = 5;
        } else if (label[5][0].getText().equals("P")) {
            compRow = 5;
            compColumn = 0;
        } else if (label[5][5].getText().equals("P")) {
            compRow = 5;
            compColumn = 5;
        } else if (label[0][2].getText().equals("P")) {
            compRow = 0;
            compColumn = 2;
        } else if (label[0][3].getText().equals("P")) {
            compRow = 0;
            compColumn = 3;
        } else if (label[5][2].getText().equals("P")) {
            compRow = 5;
            compColumn = 2;
        } else if (label[5][3].getText().equals("P")) {
            compRow = 5;
            compColumn = 3;
        } else if (label[2][0].getText().equals("P")) {
            compRow = 2;
            compColumn = 0;
        } else if (label[3][0].getText().equals("P")) {
            compRow = 3;
            compColumn = 0;
        } else if (label[2][5].getText().equals("P")) {
            compRow = 2;
            compColumn = 5;
        } else if (label[3][5].getText().equals("P")) {
            compRow = 3;
            compColumn = 5;
        } else {
            while (label[compRow][compColumn].getText().equals("B") || label[compRow][compColumn].getText().equals("N") || label[compRow][compColumn].getText().equals("W")) {
                compRow = (int) (Math.random() * 6);
                compColumn = (int) (Math.random() * 6);
                if (label[compRow][compColumn].getText().equals("P")) {
                    break;
                }
            }

        }
        if (label[compRow][compColumn].getText().equals("P")) {
            System.out.println("Row:" + compRow);
            System.out.println("Colmun:" + compColumn);
            playing = true;
            Image piece = new Image(("resources/WHITE.JPG"));
            btn[compRow][compColumn].setImage(piece);
            String letterPiece = "W";
            label[compRow][compColumn] = new Label(String.valueOf(letterPiece));
            changePieces(compRow, compColumn, piece, letterPiece);
            countPieces();
            turn = 1;
            validMove = false;
            playing = false;
            checkPossibleMoves();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
