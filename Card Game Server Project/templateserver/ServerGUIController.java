/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateserver;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.swing.JOptionPane;
import socketfx.Constants;
import socketfx.FxSocketServer;
import socketfx.SocketListener;

/**
 * FXML Controller class
 *
 * @author jtconnor
 */
//ASK WHY IMAGES AREN'T SHOWING UP
//Instruction for the game 
//First connect from the server and then the client
//click start Game on the server and then the client can start the game
//You can match color or number.
//You can match in desceding or asceding order, so one number up or down from the number you can see
public class ServerGUIController implements Initializable {

    @FXML
    private Button sendButton;
    @FXML
    private TextField sendTextField;
    @FXML
    private Button connectButton;
    @FXML
    private Button startButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField portTextField;
    @FXML
    private Label lblMessages, lblServerCards, lblClientCards, lblSpecial, lblSpecial1;
    @FXML
    private GridPane gPaneServer, gPaneClient, gPaneServerDeck, gPaneClientDeck, gPaneMiddle;
    @FXML
    private ImageView imgS0, imgS1, imgS2, imgS3,
            imgC0, imgC1, imgC2, imgC3,
            imgCDeck, imgSDeck, imgM2, imgM1;

    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<String> allCardsDeck = new ArrayList<>();
    ArrayList<ImageView> serverCardsHandImage = new ArrayList<>();
    ArrayList<ImageView> clientCardsHandImage = new ArrayList<>();
    ArrayList<String> serverHandStr = new ArrayList<>();
    ArrayList<String> clientHandStr = new ArrayList<>();
    //ArrayList<ImageView> middleImage = new ArrayList<>();
    ArrayList<Card> serverCardsHand = new ArrayList<>();
    ArrayList<Card> clientCardsHand = new ArrayList<>();
    ArrayList<Card> serverCardsDeck = new ArrayList<>();
    ArrayList<Card> clientCardsDeck = new ArrayList<>();
    ArrayList<String> serverDeckStr = new ArrayList<>();
    ArrayList<String> clientDeckStr = new ArrayList<>();
    ArrayList<String> middlePile1 = new ArrayList<>();
    ArrayList<String> middlePile2 = new ArrayList<>();
    boolean cardClicked = false;
    boolean pileClicked = false;
    int pile;
    int imgClicked;
    boolean match = false;

    String card1 = "";
    String card2 = "";
    int cHandSize;
    boolean clientTurn = false;
    boolean cDeckEmpty = false;
    boolean clear = false;
    boolean win = false;

    Card discard;

//    private String n1, n2;
//    private boolean cClicked,sClicked;
    private final static Logger LOGGER
            = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private boolean isConnected, turn = true, serverUNO = false, clientUNO = false;
    private int counter = 0;
    private String color;
    private boolean gameStarted = false;

    public enum ConnectionDisplayState {

        DISCONNECTED, WAITING, CONNECTED, AUTOCONNECTED, AUTOWAITING
    }

    private FxSocketServer socket;

    private void connect() {
        socket = new FxSocketServer(new FxSocketListener(),
                Integer.valueOf(portTextField.getText()),
                Constants.instance().DEBUG_NONE);
        socket.connect();
    }

    private void displayState(ConnectionDisplayState state) {
//        switch (state) {
//            case DISCONNECTED:
//                connectButton.setDisable(false);
//                sendButton.setDisable(true);
//                sendTextField.setDisable(true);
//                break;
//            case WAITING:
//            case AUTOWAITING:
//                connectButton.setDisable(true);
//                sendButton.setDisable(true);
//                sendTextField.setDisable(true);
//                break;
//            case CONNECTED:
//                connectButton.setDisable(true);
//                sendButton.setDisable(false);
//                sendTextField.setDisable(false);
//                break;
//            case AUTOCONNECTED:
//                connectButton.setDisable(true);
//                sendButton.setDisable(false);
//                sendTextField.setDisable(false);
//                break;
//        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected = false;
        displayState(ConnectionDisplayState.DISCONNECTED);

        Runtime.getRuntime().addShutdownHook(new ShutDownThread());

        /*
         * Uncomment to have autoConnect enabled at startup
         */
//        autoConnectCheckBox.setSelected(true);
//        displayState(ConnectionDisplayState.WAITING);
//        connect();
    }

    class ShutDownThread extends Thread {

        @Override
        public void run() {
            if (socket != null) {
                if (socket.debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                    LOGGER.info("ShutdownHook: Shutting down Server Socket");
                }
                socket.shutdown();
            }
        }
    }

    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {

            if (line.equals("Start")) { //checking if client also started
                gameStarted = true;
                for (int m = 0; m < middlePile1.size(); m++) { //sending the middle pile to the client
                    socket.sendMessage("Mid1" + middlePile1.get(m));
                }
                for (int m = 0; m < middlePile2.size(); m++) {
                    socket.sendMessage("Mid2" + middlePile2.get(m));
                }
            }
            if (line.substring(0, 4).equals("CSiz")) { //checking to see how many cards are in client hand
                clientHandStr.clear();
                clientTurn = true;
                cHandSize = Integer.parseInt(line.substring(4));
            }
            if (line.substring(0, 4).equals("CHan")) { //client hand cards
                clientHandStr.add(line.substring(4));
            }
            if (line.substring(0, 3).equals("CDS")) { //Size of client deck
                lblClientCards.setText(line.substring(3));
            }
            if (line.substring(0, 4).equals("PosC")) { //getting the card in hand
                card1 = clientHandStr.get(Integer.parseInt(line.substring(4)));
            }
            if (line.substring(0, 4).equals("PosM")) { //getting pile which client wants to put card
                pile = Integer.parseInt(line.substring(4));
                matching();
            }
            if (line.substring(0, 4).equals("M1ad")) { //adding image and card to the middle
                middlePile1.add(line.substring(4));
                imgM1.setImage(new Image(new Card(line.substring(4)).getCardPath()));
                if (middlePile1.get(middlePile1.size() - 1).equals("W0") || middlePile1.get(middlePile1.size() - 1).equals("W1") || middlePile2.get(middlePile2.size() - 1).equals("W0") || middlePile2.get(middlePile2.size() - 1).equals("W1")) {
                    lblSpecial.setText("Wildcard in middle! Put any card on top!");
                    socket.sendMessage("Wild");
                } else {
                    lblSpecial.setText("");
                    socket.sendMessage("NWil");
                }
                if (!middlePile1.get((middlePile1.size() - 1)).substring(1).equals("12") && !middlePile2.get((middlePile2.size() - 1)).substring(1).equals("12")) {
                    lblSpecial1.setText("");
                    socket.sendMessage("Blan");
                }
            }
            if (line.substring(0, 4).equals("M2ad")) { //adding image and card to the middle
                middlePile2.add(line.substring(4));
                imgM2.setImage(new Image(new Card(line.substring(4)).getCardPath()));
                if (middlePile1.get(middlePile1.size() - 1).equals("W0") || middlePile1.get(middlePile1.size() - 1).equals("W1") || middlePile2.get(middlePile2.size() - 1).equals("W0") || middlePile2.get(middlePile2.size() - 1).equals("W1")) {
                    lblSpecial.setText("Wildcard in middle! Put any card on top!");
                    socket.sendMessage("Wild");
                } else {
                    lblSpecial.setText("");
                    socket.sendMessage("NWil");
                }
                if (!middlePile1.get((middlePile1.size() - 1)).substring(1).equals("12") && !middlePile2.get((middlePile2.size() - 1)).substring(1).equals("12")) {
                    lblSpecial1.setText("");
                    socket.sendMessage("Blan");
                }
            }
            if (line.substring(0, 4).equals("CImg")) { //updating the screen with the clients hand
                for (ImageView x : clientCardsHandImage) {
                    x.setImage(null);
                }
                if (line.substring(4).equals("1")) {
                    imgC0.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("2")) {
                    imgC0.setImage(new Image("resources/Back.png"));
                    imgC1.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("3")) {
                    imgC0.setImage(new Image("resources/Back.png"));
                    imgC1.setImage(new Image("resources/Back.png"));
                    imgC2.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("4")) {
                    imgC0.setImage(new Image("resources/Back.png"));
                    imgC1.setImage(new Image("resources/Back.png"));
                    imgC2.setImage(new Image("resources/Back.png"));
                    imgC3.setImage(new Image("resources/Back.png"));
                }
            }
            if (line.equals("Empty")) { //sets picture to empty
                imgCDeck.setImage(null);
                cDeckEmpty = true;
            }
            if (line.substring(0, 4).equals("CDec")) { //getting the deck
                if (clear == false) {
                    clientDeckStr.clear();
                    clientCardsDeck.clear();
                    clear = true;
                }
                clientDeckStr.add(line.substring(4));
                clientCardsDeck.add(new Card(line.substring(4)));

            }
            if (line.equals("SLost")) { //checking for server loss
                win = true;
                lblMessages.setText("You lost.");
                resetButton.setDisable(false);
            }
            if(line.equals("Rese")){
                startButton.setDisable(false);
            }
        }

        @Override
        public void onClosedStatus(boolean isClosed) {

        }
    }

    @FXML
    private void handleSendMessageButton(ActionEvent event) {
        if (!sendTextField.getText().equals("")) {
            socket.sendMessage(sendTextField.getText());
            //System.out.println("Message sent server");
        }

    }

    @FXML
    private void handleConnectButton(ActionEvent event) {
        connectButton.setDisable(true);
        displayState(ConnectionDisplayState.WAITING);
        connect();
    }

    @FXML
    private void startGame(ActionEvent event) { //setting all images
        //socket.sendMessage("SerSize"+Integer.toString(serverCardsDeck.size()));
       resetButton.setDisable(true);
        startButton.setDisable(true);
        imgS0.setImage(new Image("resources/Back.png"));
        imgS1.setImage(new Image("resources/Back.png"));
        imgS2.setImage(new Image("resources/Back.png"));
        imgS3.setImage(new Image("resources/Back.png"));
        imgSDeck.setImage(new Image("resources/Back.png"));
        imgC0.setImage(new Image("resources/BackT.png"));
        imgC1.setImage(new Image("resources/BackT.png"));
        imgC2.setImage(new Image("resources/BackT.png"));
        imgC3.setImage(new Image("resources/BackT.png"));
        imgCDeck.setImage(new Image("resources/BackT.png"));
        imgM1.setImage(new Image("resources/Back.png"));
        imgM2.setImage(new Image("resources/Back.png"));
        socket.sendMessage("Start");

        serverCardsHandImage.add(imgS0);
        serverCardsHandImage.add(imgS1);
        serverCardsHandImage.add(imgS2);
        serverCardsHandImage.add(imgS3);
        clientCardsHandImage.add(imgC0);
        clientCardsHandImage.add(imgC1);
        clientCardsHandImage.add(imgC2);
        clientCardsHandImage.add(imgC3);
        //setting all cards
        for (int i = 0; i < 14; i++) { //settting cards to deck array
            deck.add(new Card("Y" + Integer.toString(i)));
            allCardsDeck.add("Y" + i);
            deck.add(new Card("B" + Integer.toString(i)));
            allCardsDeck.add("B" + i);
            deck.add(new Card("G" + Integer.toString(i)));
            allCardsDeck.add("G" + i);
            deck.add(new Card("R" + Integer.toString(i)));
            allCardsDeck.add("R" + i);

        }
        deck.add(new Card("W" + Integer.toString(0)));
        allCardsDeck.add("W" + 0);
        deck.add(new Card("W" + Integer.toString(1)));
        allCardsDeck.add("W" + 1);
        System.out.println(allCardsDeck);

        //server hand cards being set
        for (int i = 0; i < 4; i++) {
            int y = (int) (Math.random() * (allCardsDeck.size() - 1));
            //System.out.println("posution of card:" + y);
            serverCardsHandImage.get(i).setImage(new Image(deck.get(y).getCardPath()));
            //System.out.println(deck.get(y).getCardPath());
            serverHandStr.add(allCardsDeck.get(y));
            serverCardsHand.add(deck.remove(y));
            allCardsDeck.remove(y);

        }
        //setting middle cards
        int middle = (int) (Math.random() * (allCardsDeck.size() - 1));
        int middle1 = (int) (Math.random() * (allCardsDeck.size() - 1));
        imgM1.setImage(new Image(deck.get(middle).getCardPath()));
        middlePile1.add(allCardsDeck.remove(middle));
        deck.remove(middle);
        imgM2.setImage(new Image(deck.get(middle1).getCardPath()));
        middlePile2.add(allCardsDeck.remove(middle1));
        deck.remove(middle1);
        //System.out.println("Before:" + middlePile2);

        for (int d = 0; d < 24; d++) { //server deck being set
            int ran = (int) (Math.random() * (allCardsDeck.size() - 1));
            serverCardsDeck.add(new Card(allCardsDeck.get(ran)));
            serverDeckStr.add(allCardsDeck.get(ran));
            deck.remove(ran);
            allCardsDeck.remove(ran);
        }
        for (int s = 0; s < 24; s++) { //client deck being set
            int ran = (int) (Math.random() * (allCardsDeck.size() - 1));
            clientCardsDeck.add(new Card(allCardsDeck.get(ran)));
            clientDeckStr.add(allCardsDeck.get(ran));
            deck.remove(ran);
            allCardsDeck.remove(ran);
        }
        for (int i = 0; i < allCardsDeck.size(); i++) { //dealing cards for client
            socket.sendMessage("Deal" + allCardsDeck.get(i));
        }
        for (int c = 0; c < clientDeckStr.size(); c++) { //dealing deck for client
            socket.sendMessage("Deck" + clientDeckStr.get(c));
        }

        System.out.println(deck);
        lblServerCards.setText(Integer.toString(serverCardsDeck.size()));
        lblClientCards.setText(Integer.toString(clientCardsDeck.size()));
        //System.out.println(allCardsDeck.size());
    }

    @FXML
    private void handleDiscard(MouseEvent event) { //putting card in the middle
        if (gameStarted == true && win == false) {

            imgClicked = GridPane.getColumnIndex((ImageView) event.getSource());
            //System.out.println(imgClicked);
            if (imgClicked < serverCardsHand.size()) { //makes sure they cant click on blank tile
                cardClicked = true;
                discard = serverCardsHand.get(imgClicked);
            }

            if (cardClicked == true && pileClicked == true) { //checks both cards are clicked
                card1 = serverHandStr.get(imgClicked);
                matching(); //checks if match is valid
                if (match == true) { //if there is a match
                    pileClicked = false;
                    cardClicked = false;
                    match = false;

                    if (pile == 0) { //setting card to middle
                        imgM1.setImage(new Image(discard.getCardPath()));
                        middlePile1.add(serverHandStr.get(imgClicked));
                        serverHandStr.remove(imgClicked);
                        serverCardsHand.remove(imgClicked);
                    }
                    if (pile == 1) {//setting card to middle
                        imgM2.setImage(new Image(discard.getCardPath()));
                        middlePile2.add(serverHandStr.get(imgClicked));
                        serverHandStr.remove(imgClicked);
                        serverCardsHand.remove(imgClicked);
                    }
                    //checking if wildcard is in the middle
                    if (middlePile1.get(middlePile1.size() - 1).equals("W0") || middlePile1.get(middlePile1.size() - 1).equals("W1") || middlePile2.get(middlePile2.size() - 1).equals("W0") || middlePile2.get(middlePile2.size() - 1).equals("W1")) {
                        lblSpecial.setText("Wildcard in middle! Put any card on top!");
                        socket.sendMessage("Wild");
                    } else { //if not wildcard in middle setting label as blank
                        lblSpecial.setText("");
                        socket.sendMessage("NWil");
                    }
                    if (!middlePile1.get((middlePile1.size() - 1)).substring(1).equals("12") && !middlePile2.get((middlePile2.size() - 1)).substring(1).equals("12")) {
                        lblSpecial1.setText("");
                        socket.sendMessage("Blan");
                    }

                    for (ImageView x : serverCardsHandImage) {
                        x.setImage(null);
                    }

                    for (int i = 0; i < serverCardsHand.size(); i++) { //reseting card images in hand
                        serverCardsHandImage.get(i).setImage(new Image(serverCardsHand.get(i).getCardPath()));
                    }
                    socket.sendMessage("Mid1" + middlePile1.get(middlePile1.size() - 1)); //sending updated information to client
                    socket.sendMessage("Mid2" + middlePile2.get(middlePile2.size() - 1));
                    socket.sendMessage("SHan" + serverHandStr.size());
                    //socket.sendMessage("SerSize"+Integer.toString(serverCardsDeck.size()));
                    lblServerCards.setText(Integer.toString(serverCardsDeck.size()));
                    if (serverCardsHand.isEmpty() && serverCardsDeck.isEmpty()) { //checking for win
                        resetButton.setDisable(false);
                        lblMessages.setText("You won!!!!");
                        socket.sendMessage("CLost");
                    }
//                    if (clientCardsHand.isEmpty() && clientCardsDeck.isEmpty()) {
//                        lblMessages.setText("You lost.");
//                        socket.sendMessage("CWon");
//
//                    }
                }

            }

        }

        //((ImageView) event.getSource());
    }

    @FXML
    private void handleDiscard1(MouseEvent event) { //discarding card to middle
        if (gameStarted == true && win == false) {
            pile = GridPane.getColumnIndex((ImageView) event.getSource());
            //System.out.println(pile);
            pileClicked = true;

            if (cardClicked == true && pileClicked == true) { //making sure card and middle is clicked
                card1 = serverHandStr.get(imgClicked);
                matching();
                if (match == true) {
                    pileClicked = false;
                    cardClicked = false;
                    match = false;

                    if (pile == 0) {
                        imgM1.setImage(new Image(discard.getCardPath()));
                        middlePile1.add(serverHandStr.get(imgClicked));
                        serverHandStr.remove(imgClicked);
                        serverCardsHand.remove(imgClicked);
                    }
                    if (pile == 1) {
                        imgM2.setImage(new Image(discard.getCardPath()));
                        middlePile2.add(serverHandStr.get(imgClicked));
                        serverHandStr.remove(imgClicked);
                        serverCardsHand.remove(imgClicked);
                    }
                    if (middlePile1.get(middlePile1.size() - 1).equals("W0") || middlePile1.get(middlePile1.size() - 1).equals("W1") || middlePile2.get(middlePile2.size() - 1).equals("W0") || middlePile2.get(middlePile2.size() - 1).equals("W1")) {
                        lblSpecial.setText("Wildcard in middle! Put any card on top!");
                        socket.sendMessage("Wild");
                    } else {
                        lblSpecial.setText("");
                        socket.sendMessage("NWil");
                    }
                    if (!middlePile1.get((middlePile1.size() - 1)).substring(1).equals("12") && !middlePile2.get((middlePile2.size() - 1)).substring(1).equals("12")) {
                        lblSpecial1.setText("");
                        socket.sendMessage("Blan");
                    }

                    for (ImageView x : serverCardsHandImage) {
                        x.setImage(null);
                    }

                    for (int i = 0; i < serverCardsHand.size(); i++) {
                        serverCardsHandImage.get(i).setImage(new Image(serverCardsHand.get(i).getCardPath()));
                    }
                    socket.sendMessage("Mid1" + middlePile1.get(middlePile1.size() - 1));
                    socket.sendMessage("Mid2" + middlePile2.get(middlePile2.size() - 1));
                    socket.sendMessage("SHan" + serverHandStr.size());
                    if (serverCardsHand.isEmpty() && serverCardsDeck.isEmpty()) {
                        win = true;
                        lblMessages.setText("You won!!!!");
                        socket.sendMessage("CLost");
                        resetButton.setDisable(false);
                    }
//                    if (clientCardsHand.isEmpty() && clientCardsDeck.isEmpty()) {
//                        win=true;
//                        lblMessages.setText("You lost.");
//                        socket.sendMessage("CWon");
//
//                    }

                }

            }

        }

        //((ImageView) event.getSource());
    }

    @FXML
    private void handleGetCard(MouseEvent event) { //getting card from deck
        if (win == false) { //getting card for server from deck
            if (serverCardsHand.size() == 4) {
                System.out.println("You must discard first!");
            } else {
                if (serverCardsDeck.size() > 0) { //adding cards to hand from deck
                    int temp = (int) (Math.random() * (serverCardsDeck.size() - 1));
                    serverCardsHandImage.get(serverCardsHand.size()).setImage(new Image(serverCardsDeck.get(temp).getCardPath()));
                    serverHandStr.add(serverDeckStr.remove(temp));
                    serverCardsHand.add(serverCardsDeck.remove(temp));
                    socket.sendMessage("SDec" + Integer.toString(serverCardsDeck.size()));
                    lblServerCards.setText(Integer.toString(serverCardsDeck.size()));

                } else {
                    System.out.println("There are no more cards");
                }

                if (serverCardsDeck.isEmpty()) { //no more cards to get
                    imgSDeck.setImage(null);
                    socket.sendMessage("Empty");
                }
            }
            socket.sendMessage("SHan" + serverCardsHand.size());
        }
    }

    public void matching() { //checking for the different matching
        clear = false;
        System.out.println("Middle Pile1:" + middlePile1);
        System.out.println("Middle Pile2:" + middlePile2);
        match = false;
        if (pile == 0) { //checking for which middle pile
            card2 = middlePile1.get(middlePile1.size() - 1);
        }
        if (pile == 1) {
            card2 = middlePile2.get(middlePile2.size() - 1);
        }
        //checking for wildcard in the middle
        if (middlePile1.get(middlePile1.size() - 1).equals("W0") || middlePile1.get(middlePile1.size() - 1).equals("W1") || middlePile2.get(middlePile2.size() - 1).equals("W0") || middlePile2.get(middlePile2.size() - 1).equals("W1")) {
            lblSpecial.setText("Wildcard in middle! Put any card on top!");
            socket.sendMessage("Wild");
        } else {
            lblSpecial.setText("");
            socket.sendMessage("NWil");
        }
        if (!middlePile1.get((middlePile1.size() - 1)).substring(1).equals("12") && !middlePile2.get((middlePile2.size() - 1)).substring(1).equals("12")) {
            lblSpecial1.setText("");
            socket.sendMessage("Blan");
        }
        if (card1.substring(0, 1).equals("W") || card2.substring(0, 1).equals("W")) { //wildcard so any card can be put
            match = true;
            System.out.println("Wildcard");

            lblSpecial.setText("Wildcard in middle! Put any card on top!");

            socket.sendMessage("Wild");
        }
        //regular matching with color
        if (card1.substring(0, 1).equals(card2.substring(0, 1))) {
            match = true;
        }
        //matching with number
        if (card1.substring(1).equals(card2.substring(1))) {
            match = true;
        }
        //sandwicth matching
        if (middlePile1.size() >= 2) {
            if (pile == 0) {
                card2 = middlePile1.get(middlePile1.size() - 2);
            }
        }
        if (middlePile2.size() >= 2) {
            if (pile == 1) {
                card2 = middlePile2.get(middlePile2.size() - 2);
            }
        }

        if (card1.substring(0, 1).equals(card2.substring(0, 1))) { //checking for match of color
            match = true;
        }
        if (card1.substring(1).equals(card2.substring(1))) { //checking for match of number
            match = true;
        }
        //assending and descending matching
        int descedingCard2;
        int ascendingCard2;
        if (pile == 0) {
            descedingCard2 = Integer.parseInt(middlePile1.get(middlePile1.size() - 1).substring(1)) - 1;
            card2 = Integer.toString(descedingCard2);
            System.out.println("Decedsbng" + card2);
        }
        if (pile == 1) {
            descedingCard2 = Integer.parseInt(middlePile2.get(middlePile2.size() - 1).substring(1)) - 1;
            card2 = Integer.toString(descedingCard2);
            System.out.println("Decedsbng" + card2);
        }

        if (card1.substring(1).equals(card2)) {
            match = true;
            System.out.println("order match is true");
        }
        if (pile == 0) {
            ascendingCard2 = Integer.parseInt(middlePile1.get(middlePile1.size() - 1).substring(1)) + 1;
            card2 = Integer.toString(ascendingCard2);
            System.out.println("Decedsbng" + card2);
        }
        if (pile == 1) {
            ascendingCard2 = Integer.parseInt(middlePile2.get(middlePile2.size() - 1).substring(1)) + 1;
            card2 = Integer.toString(ascendingCard2);
            System.out.println("Decedsbng" + card2);
        }
        if (card1.substring(1).equals(card2)) {
            match = true;
            System.out.println("order match is true");
        }

        if (match == false) {
            cardClicked = false;
            pileClicked = false;
        }

        //lblClientCards.setText(Integer.toString(clientCardsDeck.size()-1));
        //+2 cards
        if (clientTurn == true) { //client puts down +2
            if (match == true && card1.substring(1).equals("12")) {
                System.out.println("Before +2:" + serverCardsDeck.size());
                if (clientCardsDeck.size() > 2) {
                    //shifting cards to respective decks
                    int cardShift1 = (int) (Math.random() * (clientCardsDeck.size() - 1));
                    lblSpecial1.setText("You have gained two cards");
                    socket.sendMessage("Remo" + cardShift1);
                    serverDeckStr.add(clientDeckStr.remove(cardShift1));
                    serverCardsDeck.add(clientCardsDeck.remove(cardShift1));

                    int cardShift2 = (int) (Math.random() * (clientCardsDeck.size() - 1));
                    socket.sendMessage("Remo" + cardShift2);
                    serverDeckStr.add(clientDeckStr.remove(cardShift2));
                    serverCardsDeck.add(clientCardsDeck.remove(cardShift2));
                    imgSDeck.setImage(new Image("resources/Back.png"));
                    //lblClientCards.setText(Integer.toString(clientCardsDeck.size()));

                } else if (clientCardsDeck.size() > 1) {
                    int cardShift1 = (int) (Math.random() * (clientCardsDeck.size() - 1));

                    lblSpecial1.setText("You have gained one card");
                    socket.sendMessage("Remo" + cardShift1);
                    serverDeckStr.add(clientDeckStr.remove(cardShift1));
                    serverCardsDeck.add(clientCardsDeck.remove(cardShift1));
                    imgSDeck.setImage(new Image("resources/Back.png"));
                    //lblClientCards.setText(Integer.toString(clientCardsDeck.size()));

                }
                if (clientCardsDeck.size() == 0) {
                    imgCDeck.setImage(null);
                }
                System.out.println("Adding to deck:" + serverCardsDeck.size());

            }
            socket.sendMessage("Matc" + match);
            clientTurn = false;
        } else {//server puts down +2 card
            if (match == true && card1.substring(1).equals("12")) {
                System.out.println("Before +2:" + serverCardsDeck.size());
                if (cDeckEmpty == true) {
                    cDeckEmpty = false;
                    imgCDeck.setImage(new Image("resources/BackT.png"));
                }
                if (serverCardsDeck.size() > 2) { //losing cards
                    //shifting cards to respective decks
                    int cardShift1 = (int) (Math.random() * (serverCardsDeck.size() - 1));
                    socket.sendMessage("Add" + serverDeckStr.remove(cardShift1));
                    lblSpecial1.setText("You have lost two cards");
                    serverCardsDeck.remove(cardShift1);
                    int cardShift2 = (int) (Math.random() * (serverCardsDeck.size() - 1));
                    socket.sendMessage("Add" + serverDeckStr.remove(cardShift2));
                    serverCardsDeck.remove(cardShift2);
                    //lblClientCards.setText(Integer.toString(clientCardsDeck.size()+2));

                } else if (serverCardsDeck.size() > 1) {
                    int cardShift1 = (int) (Math.random() * (serverCardsDeck.size() - 1));
                    lblSpecial1.setText("You have lost two cards");
                    socket.sendMessage("Add" + serverDeckStr.remove(cardShift1));
                    serverCardsDeck.remove(cardShift1);
                    //lblClientCards.setText(Integer.toString(clientCardsDeck.size()+1));

                }
                System.out.println("Remove:" + serverCardsDeck.size());
                if (serverCardsDeck.size() == 0) {
                    imgSDeck.setImage(null);
                    socket.sendMessage("Empty");

                }
            }

        }
        socket.sendMessage("SDec" + Integer.toString(serverCardsDeck.size()));
        lblServerCards.setText(Integer.toString(serverCardsDeck.size()));

    }
    @FXML
    private void reset(ActionEvent event){
        resetButton.setDisable(true);
    deck.clear();
    allCardsDeck.clear();
    serverCardsHandImage.clear();
    clientCardsHandImage.clear();
    serverHandStr.clear();
    clientHandStr.clear();
    //ArrayList<ImageView> middleImage = new ArrayList<>();
    serverCardsHand.clear();
    clientCardsHand.clear();
    serverCardsDeck.clear();
    clientCardsDeck.clear();
    serverDeckStr.clear();
    clientDeckStr.clear();
    middlePile1.clear();
    middlePile2.clear();
   cardClicked = false;
    pileClicked = false;
   
    match = false;

    card1 = "";
    card2 = "";
    
    clientTurn = false;
    cDeckEmpty = false;
    clear = false;
    win = false;
    //startButton.setDisable(false);
        System.out.println("Ayayyayaydcghdfjgvdfhdfjkgjkhgkjdbhgjkdhfgjk");
    socket.sendMessage("Rese");
    lblSpecial.setText("");
    lblSpecial1.setText("");
    lblMessages.setText("");

    }

}
