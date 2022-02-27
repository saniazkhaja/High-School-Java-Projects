/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temclient;

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
import socketfx.FxSocketClient;
import socketfx.SocketListener;

/**
 * FXML Controller class
 *
 * @author jtconnor
 */
public class ClientGUIController implements Initializable {

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
    private TextField hostTextField;
    @FXML
    private Label lblName1, lblName2, lblName3, lblName4, lblMessages,  lblServerCards, lblClientCards, lblSpecial, lblSpecial1;
    @FXML
    private ImageView imgS0, imgS1, imgS2, imgS3, imgS4, imgS5, imgS6, imgS7, imgS8, imgS9,
            imgC0, imgC1, imgC2, imgC3, imgC4, imgC5, imgC6, imgC7, imgC8, imgC9,
            imgCDeck, imgSDeck, imgM1, imgM2;
    @FXML
    private GridPane gPaneServer, gPaneClient, gPaneServerDeck, gPaneClientDeck, gPaneMiddle;

    ArrayList<ImageView> serverCardsHandImage = new ArrayList<>();
    ArrayList<ImageView> clientCardsHandImage = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<String> deckStr = new ArrayList<>();
    ArrayList<Card> clientCardsHand = new ArrayList<>();
    ArrayList<String> clientHandStr = new ArrayList<>();
    ArrayList<Card> clientCardsDeck = new ArrayList<>();
    ArrayList<String> clientDeckStr = new ArrayList<>();
    boolean cardClicked = false;
    boolean pileClicked = false;
    boolean win = false;
    int pile;
    int imgClicked;
    Card discard;

    private final static Logger LOGGER
            = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private boolean isConnected, turn, serverUNO = false, clientUNO = false;

    public enum ConnectionDisplayState {

        DISCONNECTED, WAITING, CONNECTED, AUTOCONNECTED, AUTOWAITING
    }

    private FxSocketClient socket;

    private void connect() {
        socket = new FxSocketClient(new FxSocketListener(),
                hostTextField.getText(),
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
        startButton.setDisable(true);

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
            //System.out.println("message received client");
            //System.out.println(line);
            //lblMessages.setText(line);
            if (line.equals("Start")) { //can click start game
                startButton.setDisable(false);
            }
            if (line.substring(0, 4).equals("Deal")) { //getting hand at begginning
                deck.add(new Card(line.substring(4)));
                deckStr.add(line.substring(4));
            }
            if (line.substring(0, 4).equals("Deck")) { //getting client deck
                clientCardsDeck.add(new Card(line.substring(4)));
                clientDeckStr.add(line.substring(4));
            }
            if (line.substring(0, 4).equals("Mid1")) { //setting middle image
                imgM1.setImage(new Image(new Card(line.substring(4)).getCardPath()));
            }
            if (line.substring(0, 4).equals("Mid2")) { //setting middle image
                imgM2.setImage(new Image(new Card(line.substring(4)).getCardPath()));
            }
            if (line.substring(0, 4).equals("SHan")) { //setting image of other player
                for (ImageView x : serverCardsHandImage) {
                    x.setImage(null);
                }
                if (line.substring(4).equals("1")) {
                    imgS0.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("2")) {
                    imgS0.setImage(new Image("resources/Back.png"));
                    imgS1.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("3")) {
                    imgS0.setImage(new Image("resources/Back.png"));
                    imgS1.setImage(new Image("resources/Back.png"));
                    imgS2.setImage(new Image("resources/Back.png"));
                }
                if (line.substring(4).equals("4")) {
                    imgS0.setImage(new Image("resources/Back.png"));
                    imgS1.setImage(new Image("resources/Back.png"));
                    imgS2.setImage(new Image("resources/Back.png"));
                    imgS3.setImage(new Image("resources/Back.png"));
                }
            }
            if (line.substring(0, 4).equals("Matc")) { //checking for match
                if (line.substring(4).equals("false")) {
                    cardClicked = false;
                    pileClicked = false;
                }
                if (line.substring(4).equals("true")) { //setting images if there is a match
                    if (cardClicked == true && pileClicked == true) {
                        cardClicked = false;
                        pileClicked = false;
                        if (pile == 0) {
                            imgM1.setImage(new Image(discard.getCardPath()));
                            socket.sendMessage("M1ad" + clientHandStr.get(imgClicked));
                            //middlePile1.add(serverHandStr.get(imgClicked));
                            clientHandStr.remove(imgClicked);
                            clientCardsHand.remove(imgClicked);
                        }
                        if (pile == 1) {
                            imgM2.setImage(new Image(discard.getCardPath()));
                            socket.sendMessage("M2ad" + clientHandStr.get(imgClicked));
                            //middlePile2.add(serverHandStr.get(imgClicked));
                            clientHandStr.remove(imgClicked);
                            clientCardsHand.remove(imgClicked);
                        }

                        for (ImageView x : clientCardsHandImage) {
                            x.setImage(null);
                        }

                        for (int i = 0; i < clientCardsHand.size(); i++) {
                            clientCardsHandImage.get(i).setImage(new Image(clientCardsHand.get(i).getCardPath()));
                        }
//                    socket.sendMessage("Mid1" + middlePile1.get(middlePile1.size() - 1));
//                    socket.sendMessage("Mid2" + middlePile2.get(middlePile2.size() - 1));
                        socket.sendMessage("CImg" + clientCardsHand.size());
                        if (clientCardsHand.isEmpty() && clientCardsDeck.isEmpty()) {
                            win = true;
                            lblMessages.setText("You won!!!!!");
                            
                            socket.sendMessage("SLost");

                        }

                    }
                }
            }
            if (line.equals("Empty")) { //setting no cards
                imgSDeck.setImage(null);
            }
            if (line.equals("CWon")) { //client won
                
                lblMessages.setText("You won!!!!");
                System.out.println("You have wonnnnnnn!!!!!");
                win = true;
            }
            if (line.equals("CLost")) { //client lost
                lblMessages.setText("You lost.");
                System.out.println("You have lost");
                
                win = true;
            }
            if (line.substring(0, 3).equals("Add")) { //adding acrds to deck
                System.out.println("Old size Add:" + clientDeckStr.size());
                clientDeckStr.add(line.substring(3));
                clientCardsDeck.add(new Card(line.substring(3)));
                imgCDeck.setImage(new Image("resources/BackT.png"));
                System.out.println("New size Add:" + clientDeckStr.size());
                lblSpecial1.setText("You have gained two cards");
                lblClientCards.setText(Integer.toString(clientDeckStr.size()));
                socket.sendMessage("CDS"+Integer.toString(clientDeckStr.size()));
            }
            if (line.substring(0, 4).equals("Remo")) { //removing cards in deck
                System.out.println("Old size Reomve:" + clientDeckStr.size());
                clientDeckStr.remove(Integer.parseInt(line.substring(4)));
                clientCardsDeck.remove(Integer.parseInt(line.substring(4)));
                System.out.println("Old size Remove:" + clientDeckStr.size());
                lblSpecial1.setText("You have lost two cards");
                if (clientDeckStr.size() == 0) {
                    imgCDeck.setImage(null);
                }
                lblClientCards.setText(Integer.toString(clientDeckStr.size()));
                socket.sendMessage("CDS"+Integer.toString(clientDeckStr.size()));
            }
            if(line.substring(0,4).equals("SDec")){ //how many cards are in server deck
                lblServerCards.setText(line.substring(4));
                lblClientCards.setText(Integer.toString(clientCardsDeck.size()));
            }
            if(line.equals("Wild")){ //lets client know there is a wild card in the middle
                lblSpecial.setText("Wildcard in middle! Put any card on top!");
            }
            if(line.equals("NWil")){ //no more wild card in the middle
                lblSpecial.setText("");
            }
            if(line.equals("Blan")){
                lblSpecial1.setText("");
            }
            if(line.equals("Rese")){
                resetButton.setDisable(false);
            }
        }

        @Override
        public void onClosedStatus(boolean isClosed) {

        }
    }

    @FXML
    private void handleSendMessageButton(ActionEvent event) {//important
        if (!sendTextField.getText().equals("")) {
            String x = sendTextField.getText();
            socket.sendMessage(x);
            System.out.println("sent message client");

        }
        if (sendTextField.getText().equals("Start")) {
            connectButton.setDisable(false);
        }

    }

    @FXML
    private void handleConnectButton(ActionEvent event) {
        connectButton.setDisable(true);
        displayState(ConnectionDisplayState.WAITING);
        connect();
    }

    @FXML
    private void startGame(ActionEvent event) { //setting images 
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

        clientCardsHandImage.add(imgC0);
        clientCardsHandImage.add(imgC1);
        clientCardsHandImage.add(imgC2);
        clientCardsHandImage.add(imgC3);
        serverCardsHandImage.add(imgS0);
        serverCardsHandImage.add(imgS1);
        serverCardsHandImage.add(imgS2);
        serverCardsHandImage.add(imgS3);

        for (int i = 0; i < 4; i++) {
            int y = (int) (Math.random() * (deck.size() - 1));
            clientCardsHandImage.get(i).setImage(new Image(deck.get(y).getCardPath()));
            //System.out.println(deck.get(y).getCardPath());
            clientHandStr.add(deckStr.remove(y));
            clientCardsHand.add(deck.remove(y));
        }
        lblClientCards.setText("24");
        lblServerCards.setText("24");
    }

    @FXML
    private void handleDiscard(MouseEvent event) { //getting rid of card to the middle 
        
        if (win == false) {
            imgClicked = GridPane.getColumnIndex((ImageView) event.getSource());
            if (imgClicked < clientCardsHand.size()) {
                cardClicked = true;
                discard = clientCardsHand.get(imgClicked);
            }

            if (cardClicked == true && pileClicked == true) { //sending card and pile to server to check for match
                socket.sendMessage("CSiz" + clientCardsHand.size());
                for (int p = 0; p < clientHandStr.size(); p++) {
                    socket.sendMessage("CHan" + clientHandStr.get(p));
                }
                socket.sendMessage("PosC" + imgClicked);
                socket.sendMessage("PosM" + pile);
                for (int i = 0; i < clientDeckStr.size(); i++) {
                    socket.sendMessage("CDec" + clientDeckStr.get(i));
                }
            }
            //((ImageView) event.getSource());
        }
    }

    @FXML
    private void handleDiscard1(MouseEvent event) { 
        
        if (win == false) {
            pile = GridPane.getColumnIndex((ImageView) event.getSource());
            pileClicked = true;

            if (cardClicked == true && pileClicked == true) {
                socket.sendMessage("CSiz" + clientCardsHand.size());
                for (int p = 0; p < clientHandStr.size(); p++) {
                    socket.sendMessage("CHan" + clientHandStr.get(p));
                }
                socket.sendMessage("PosC" + imgClicked);
                socket.sendMessage("PosM" + pile);
                for (int i = 0; i < clientDeckStr.size(); i++) {
                    socket.sendMessage("CDec" + clientDeckStr.get(i));
                }

            }
            //((ImageView) event.getSource());
        }
    }

    @FXML
    private void handleGetCard(MouseEvent event) { //getting card from deck
        
        if (win == false) {
            if (clientCardsHand.size() == 4) {
                System.out.println("You must discard first!");
            } else {
                if (clientCardsDeck.size() > 0) { 
                    int temp = (int) (Math.random() * (clientCardsDeck.size() - 1)); //getting card from deck
                    clientCardsHandImage.get(clientCardsHand.size()).setImage(new Image(clientCardsDeck.get(temp).getCardPath()));
                    clientHandStr.add(clientDeckStr.remove(temp));
                    clientCardsHand.add(clientCardsDeck.remove(temp));
                    lblClientCards.setText(Integer.toString(clientCardsDeck.size()));
                    socket.sendMessage("CDS"+Integer.toString(clientCardsDeck.size()));

                } else {
                    System.out.println("There are no more cards");
                }

                if (clientCardsDeck.isEmpty()) {
                    imgCDeck.setImage(null);
                    socket.sendMessage("Empty");
                }
            }
            socket.sendMessage("CImg" + clientCardsHand.size());

        }
        
    }
   @FXML
    private void reset(ActionEvent event){
         resetButton.setDisable(true);
    deck.clear();
    serverCardsHandImage.clear();
    clientCardsHandImage.clear();
    clientHandStr.clear();
    //ArrayList<ImageView> middleImage = new ArrayList<>();
    clientCardsHand.clear();
    clientCardsDeck.clear();
    clientDeckStr.clear();
   cardClicked = false;
    pileClicked = false;
   
    win = false;
    //startButton.setDisable(false);
    
   
    deckStr.clear();
    
   clientHandStr.clear();
   socket.sendMessage("Rese");
   lblSpecial.setText("");
    lblSpecial1.setText("");
    lblMessages.setText("");
    
    }

}
