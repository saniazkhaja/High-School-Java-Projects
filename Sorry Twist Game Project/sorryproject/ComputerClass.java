/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorryproject;

import java.util.ArrayList;

/**
 *
 * @author Sania Khaja
 */
public class ComputerClass {
    //sliders
    //landing on pawn
    //sliding through panw

    private ArrayList<Integer> compCheckPos = new ArrayList<>();
    private ArrayList<Integer> compChoosePawn = new ArrayList<>();
    private ArrayList<Integer> playerPositions = new ArrayList<>();
    private String Card;
    private String compSlide = "";
    private String compSendBackPlayer = "";
    private String compSendBackItself = "";
    private String compChoice = "";
    private String compDice = "";
    private String compPawnMoved = "";
    private String pickedUpChanceCard = "";

    private boolean green1Out = false;
    private boolean green2Out = false;
    private boolean green3Out = false;
    private boolean green4Out = false;
    private boolean green1Home = false;
    private boolean green2Home = false;
    private boolean green3Home = false;
    private boolean green4Home = false;
    private boolean green1Valid = false;
    private boolean green2Valid = false;
    private boolean green3Valid = false;
    private boolean green4Valid = false;
    private boolean blue1Valid = false;
    private boolean blue2Valid = false;
    private boolean blue3Valid = false;
    private boolean blue4Valid = false;
    private boolean blue1Out = false;
    private boolean blue2Out = false;
    private boolean blue3Out = false;
    private boolean blue4Out = false;
    private boolean green1CantMove = false;
    private boolean green2CantMove = false;
    private boolean green3CantMove = false;
    private boolean green4CantMove = false;
    private boolean green1SendsToStart = false;
    private boolean green2SendsToStart = false;
    private boolean green3SendsToStart = false;
    private boolean green4SendsToStart = false;
    //private boolean sendsToStart = false;
    private int homeCounter = 0;
    //private int counter = 0;
    private int movesLeft;
    private boolean useChoice = false;

    public ComputerClass() {

    }

    public void theCard(ArrayList<Integer> compPos, ArrayList<Integer> copy, String card, ArrayList<Integer> playerPos, boolean blue1, boolean blue2, boolean blue3, boolean blue4) {
        //setting up stuff such as positions
        //reseting some varaibles
        blue1Out = blue1;
        blue2Out = blue2;
        blue3Out = blue3;
        blue4Out = blue4;
        Card = card;
        compCheckPos = compPos;
        compChoosePawn = copy;
        playerPositions = playerPos;
        movesLeft = 0;
        System.out.println("Card" + Card);
        compSlide = "";
        compSendBackPlayer = "";
        compSendBackItself = "";
        compChoice = "";
        compDice = "";
        compPawnMoved = "";
        pickedUpChanceCard = "";
        //figuring out which function to call
        if (Card.substring(0, 2).equals("RN")) {
            noChoiceRegCards();
        } else if (Card.substring(0, 2).equals("RC")) {
            choiceRegCards();
        } else if (Card.substring(0, 2).equals("CN")) {
            noChoiceChanceCards();
        } else if (Card.substring(0, 2).equals("CC")) {
            pickedUpChanceCard = " Picked up chance card.";
            choiceChanceCards();
        }

    }

    private void noChoiceRegCards() { //Seeing which card and the best move to do
        if (Card.equals("RN0")) { //take pawn out from start
            System.out.println("Green1Out" + green1Out + green1Home);
            if (green1Out == false && green1Home == false) { //checking which oawn to take out 
                green1Out = true;
                //green1Valid = true;
                compCheckPos.set(0, 0);
                compCheckPos.set(1, 4);
                special(0, 1);
                compPawnMoved = "Moved pawn 1. ";
            } else if (green2Out == false && green2Home == false) {
                green2Out = true;
                //green2Valid = true;
                compCheckPos.set(2, 0);
                compCheckPos.set(3, 4);
                special(2, 3);
                compPawnMoved = "Moved pawn 2. ";
            } else if (green3Out == false && green3Home == false) {
                green3Out = true;
                //green3Valid = true;
                compCheckPos.set(4, 0);
                compCheckPos.set(5, 4);
                special(4, 5);
                compPawnMoved = "Moved pawn 3. ";
            } else if (green4Out == false && green4Home == false) {
                green4Out = true;
                //green4Valid = true;
                compCheckPos.set(6, 0);
                compCheckPos.set(7, 4);
                special(6, 7);
                compPawnMoved = "Moved pawn 4. ";
            } else {
                compPawnMoved = "Turn skipped. ";
            }
        } else if (Card.equals("RN13")) { //chance card
            pickUpChanceCard();
        } else {
            if (green1Out == false && green2Out == false && green3Out == false && green4Out == false) { //checking if turn is possible
                movesLeft = 0;
                compPawnMoved = "Turn skipped. ";
            } else { //regular moving cards
                movesLeft = Integer.parseInt(Card.substring(2));
                canMove(); //checking if pawn can move
                if (green1CantMove == true && green2CantMove == true && green3CantMove == true && green4CantMove == true) {
                    movesLeft = 0;
                    compPawnMoved = "Turn skipped. ";
                } else {
                    movesLeft = Integer.parseInt(Card.substring(2));
                    chooseBestPawn(); //chosing which pawn to move
                }

            }
        }

    }

    public void choiceRegCards() { //figuring out which card and what the computer should do
        boolean moving = false;
        if (Card.equals("RC0")) {
            if (green1Out == false || green2Out == false || green3Out == false || green4Out == false) {
                compChoice = " Chose Choice 2. ";
                boolean sendBack = false;
                for (int i = 0; i < 8; i++) { //checking if own pawn would be sent back
                    if (compCheckPos.get(i) == 0 && compCheckPos.get(i + 1) == 4) {
                        sendBack = true;
                    }
                }
                if (green1Out == false && green1Home == false && sendBack == false) { //deciding which pawn to remove
                    compPawnMoved = "Moved Pawn 1. ";
                    green1Out = true;
                    
                    //green1Valid = true;
                    compCheckPos.set(0, 0);
                    compCheckPos.set(1, 4);
                    special(0, 1);

                } else if (green2Out == false && green2Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 2. ";
                    green2Out = true;
                    //green2Valid = true;
                    compCheckPos.set(2, 0);
                    compCheckPos.set(3, 4);
                    special(2, 3);
                } else if (green3Out == false && green3Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 3. ";
                    green3Out = true;
                    //green3Valid = true;
                    compCheckPos.set(4, 0);
                    compCheckPos.set(5, 4);
                    special(4, 5);
                } else if (green4Out == false && green4Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 4. ";
                    green4Out = true;
                    //green4Valid = true;
                    compCheckPos.set(6, 0);
                    compCheckPos.set(7, 4);
                    special(6, 7);
                } else {
                    //do 1 moves if possible 
                    movesLeft = 1;
                    moving = true;
                    compChoice = " Chose Choice 1. ";
                }

            } else {
                compChoice = " Chose Choice 1. ";
                //do 1 moves if possible 
                movesLeft = 1;
                moving = true;
            }
        } else if (Card.equals("RC1")) {
            if (green1Out == false || green2Out == false || green3Out == false || green4Out == false) {
                compChoice = " Chose Choice 2. ";
                boolean sendBack = false;
                for (int i = 0; i < 8; i++) {
                    if (compCheckPos.get(i) == 0 && compCheckPos.get(i + 1) == 4) {
                        sendBack = true;
                    }
                }
                if (green1Out == false && green1Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 1. ";
                    green1Out = true;
                    //green1Valid = true;
                    compCheckPos.set(0, 0);
                    compCheckPos.set(1, 4);
                    special(0, 1);

                } else if (green2Out == false && green2Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 2. ";
                    green2Out = true;
                    //green2Valid = true;
                    compCheckPos.set(2, 0);
                    compCheckPos.set(3, 4);
                    special(2, 3);
                } else if (green3Out == false && green3Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 3. ";
                    green3Out = true;
                    //green3Valid = true;
                    compCheckPos.set(4, 0);
                    compCheckPos.set(5, 4);
                    special(4, 5);
                } else if (green4Out == false && green4Home == false && sendBack == false) {
                    compPawnMoved = "Moved Pawn 4. ";
                    green4Out = true;
                    //green4Valid = true;
                    compCheckPos.set(6, 0);
                    compCheckPos.set(7, 4);
                    special(6, 7);
                } else {
                    //do 1 moves if possible
                    compChoice = " Chose Choice 1. ";
                    movesLeft = 2;
                    moving = true;
                }

            } else {
                //do 1 moves if possible 
                compChoice = " Chose Choice 1. ";
                movesLeft = 2;
                moving = true;
            }

        } else if (Card.equals("RC2")) { //move 7 steps or pick up chnace card
            //if positions will land on slider or land on other pawn or make a pawn go into safety zone
            //else pick up chnace card
            movesLeft = 7;
            canMove();
            if (green1Out == false && green2Out == false && green3Out == false && green4Out == false) {
                useChoice = true;
                compChoice = " Chose Choice 2. ";
            } else {
                //checking if pawn can move
                if (green1CantMove == true && green2CantMove == true && green3CantMove == true && green4CantMove == true) {
                    useChoice = true;
                    compChoice = " Chose Choice 2. ";
                    movesLeft = 0;
                } else {
                    compChoice = " Chose Choice 1. ";
                    movesLeft = 7;
                    moving = true;
                }

            }
        }

        if (green1Out == false && green2Out == false && green3Out == false && green4Out == false) {
            movesLeft = 0;
            compPawnMoved = "Turn skipped. ";
        } else {
            if (moving == true) {
                canMove();
                if (green1CantMove == true && green2CantMove == true && green3CantMove == true && green4CantMove == true) {
                    movesLeft = 0;
                    compPawnMoved = "Turn skipped. ";
                } else {
                    //movesLeft = Integer.parseInt(Card.substring(2));
                    chooseBestPawn();
                }

            }

        }

    }

    private void checkSendStart(int row) { //checking if a pawn would be sent back to start

        for (int i = 0; i < 8; i += 2) {
            if (i != row) {
                if (compChoosePawn.get(i) == compChoosePawn.get(row) && compChoosePawn.get(i + 1) == compChoosePawn.get(row + 1)) {
                    if ((compCheckPos.get(i) == 6 && compCheckPos.get(i+1) == 2) || (compCheckPos.get(i) == 6 && compCheckPos.get(i+1) == 3) || (compCheckPos.get(i) == 7 && compCheckPos.get(i+1) == 2) || (compCheckPos.get(i) == 7 && compCheckPos.get(i+1) == 3)) {

                    } else {
                        if (row == 0) {
                            green1SendsToStart = true;
                        } else if (row == 2) {
                            green2SendsToStart = true;
                        } else if (row == 4) {
                            green3SendsToStart = true;
                        } else if (row == 6) {
                            green4SendsToStart = true;
                        }
                    }

                }
            }

        }

    }

    public void chooseBestPawn() { //choosing best pawn based of if lands in home, lands in safety zone, sends opponent back and lands on slider
        System.out.println("BPos" + compCheckPos);
        green1SendsToStart = false;
        green2SendsToStart = false;
        green3SendsToStart = false;
        green4SendsToStart = false;

        int row = 0;
        int col = 0;
        int originalMovesLeft = movesLeft;
        System.out.println("Orei;" + originalMovesLeft);
        boolean checkGreen1Home = false;
        boolean checkGreen2Home = false;
        boolean checkGreen3Home = false;
        boolean checkGreen4Home = false;
        boolean checkGreen1Safety = false;
        boolean checkGreen2Safety = false;
        boolean checkGreen3Safety = false;
        boolean checkGreen4Safety = false;
        boolean checkGreen1SendOppoBackToStart = false;
        boolean checkGreen2SendOppoBackToStart = false;
        boolean checkGreen3SendOppoBackToStart = false;
        boolean checkGreen4SendOppoBackToStart = false;
        boolean checkGreen1Slide = false;
        boolean checkGreen2Slide = false;
        boolean checkGreen3Slide = false;
        boolean checkGreen4Slide = false;

        for (int pos = 0; pos < 8; pos += 2) {
            boolean canMove = false;
            if (pos == 0 && green1Out == true) {
                row = pos;
                col = pos + 1;
                canMove = true;
            } else if (pos == 2 && green2Out == true) {
                row = pos;
                col = pos + 1;
                canMove = true;
            } else if (pos == 4 && green3Out == true) {
                row = pos;
                col = pos + 1;
                canMove = true;
            } else if (pos == 6 && green4Out == true) {
                row = pos;
                col = pos + 1;
                canMove = true;
            }
            //figuring out where pawn will land
            while (movesLeft > 0 && canMove == true) {
                movesLeft--;
                if (compChoosePawn.get(row) >= 0 && compChoosePawn.get(row) <= 5 && compChoosePawn.get(col) == 2) { //dealing with safety zone
                    compChoosePawn.set(row, compChoosePawn.get(row) + 1);
                    if (pos == 0) {
                        checkGreen1Safety = true;
                    } else if (pos == 2) {
                        checkGreen2Safety = true;
                    } else if (pos == 4) {
                        checkGreen3Safety = true;
                    } else if (pos == 6) {
                        checkGreen4Safety = true;
                    }

                    if (compChoosePawn.get(row) + 1 == 6) { //need to do when reaches home and wins
                        //homeCounter++;
                        
                        if (pos == 0 && movesLeft==0) {
                            checkGreen1Home = true;
                            compCheckPos.set(0, 7);
                        compCheckPos.set(1, 3);
                        } else if (pos == 2 && movesLeft==0) {
                            checkGreen2Home = true;
                            compCheckPos.set(2, 7);
                        compCheckPos.set(3, 3);
                        } else if (pos == 4 && movesLeft==0) {
                            checkGreen3Home = true;
                            compCheckPos.set(4, 7);
                        compCheckPos.set(5, 3);
                        } else if (pos == 6 && movesLeft==0) {
                            checkGreen4Home = true;
                            compCheckPos.set(6, 7);
                        compCheckPos.set(7, 3);
                        }

                    }
                } else if (compChoosePawn.get(row) == 15 && compChoosePawn.get(col) == 0) { //next four are dealing with corners
                    compChoosePawn.set(row, 14);
                    compChoosePawn.set(col, 0);

                } else if (compChoosePawn.get(row) == 0 && compChoosePawn.get(col) == 0) {
                    compChoosePawn.set(row, 0);
                    compChoosePawn.set(col, 1);

                } else if (compChoosePawn.get(row) == 0 && compChoosePawn.get(col) == 15) {

                    compChoosePawn.set(row, 1);
                    compChoosePawn.set(col, 15);

                } else if (compChoosePawn.get(row) == 15 && compChoosePawn.get(col) == 15) {
                    compChoosePawn.set(row, 15);
                    compChoosePawn.set(col, 14);
                } else if (compChoosePawn.get(row) == 0) { //moving pawn forward in different areas. will only for regular no choice cards
                    compChoosePawn.set(col, compChoosePawn.get(col) + 1);
                } else if (compChoosePawn.get(row) == 15) {
                    compChoosePawn.set(col, compChoosePawn.get(col) - 1);
                } else if (compChoosePawn.get(col) == 0) {
                    compChoosePawn.set(row, compChoosePawn.get(row) - 1);
                } else if (compChoosePawn.get(col) == 15) {
                    compChoosePawn.set(row, compChoosePawn.get(row) + 1);
                }

            }
            if (canMove == true) {
                //sending people back tostart and teh slider 
                for (int i = 0; i < 8; i += 2) {
                    if (compChoosePawn.get(row) == playerPositions.get(i) && compChoosePawn.get(col) == playerPositions.get(i + 1)) {
                        if (pos == 0) {
                            checkGreen1SendOppoBackToStart = true;
                        } else if (pos == 2) {
                            checkGreen2SendOppoBackToStart = true;
                        } else if (pos == 4) {
                            checkGreen3SendOppoBackToStart = true;
                        } else if (pos == 6) {
                            checkGreen4SendOppoBackToStart = true;
                        }
                    }
                }
                if (compChoosePawn.get(row) == 14 && compChoosePawn.get(col) == 0) {
                    compChoosePawn.set(row, 11);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) > 11 && playerPositions.get(i) < 14 && playerPositions.get(i + 1) == 0) {
                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) > 11 && compChoosePawn.get(i) < 14 && compChoosePawn.get(i + 1) == 0) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }

                }
                if (compChoosePawn.get(row) == 6 && compChoosePawn.get(col) == 0) {
                    compChoosePawn.set(row, 2);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) > 2 && playerPositions.get(i) < 6 && playerPositions.get(i + 1) == 0) {
                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) > 2 && compChoosePawn.get(i) < 6 && compChoosePawn.get(i + 1) == 0) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }

                }
                if (compChoosePawn.get(row) == 1 && compChoosePawn.get(col) == 15) {
                    compChoosePawn.set(row, 4);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) > 1 && playerPositions.get(i) < 4 && playerPositions.get(i + 1) == 15) {

                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) > 1 && compChoosePawn.get(i) < 4 && compChoosePawn.get(i + 1) == 15) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }
                }
                if (compChoosePawn.get(row) == 9 && compChoosePawn.get(col) == 15) {
                    compChoosePawn.set(row, 13);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) > 9 && playerPositions.get(i) < 13 && playerPositions.get(i + 1) == 15) {

                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) > 9 && compChoosePawn.get(i) < 13 && compChoosePawn.get(i + 1) == 15) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }
                }
                if (compChoosePawn.get(row) == 15 && compChoosePawn.get(col) == 14) {
                    compChoosePawn.set(col, 11);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) == 15 && playerPositions.get(i + 1) > 11 && playerPositions.get(i + 1) < 14) {

                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) == 15 && compChoosePawn.get(i) > 11 && compChoosePawn.get(i + 1) < 14) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }
                }
                if (compChoosePawn.get(row) == 15 && compChoosePawn.get(col) == 6) {
                    compChoosePawn.set(col, 2);
                    checkSendStart(pos);
                    if (pos == 0) {
                        checkGreen1Slide = true;
                    } else if (pos == 2) {
                        checkGreen2Slide = true;
                    } else if (pos == 4) {
                        checkGreen3Slide = true;
                    } else if (pos == 6) {
                        checkGreen4Slide = true;
                    }

                    for (int i = 0; i < 8; i += 2) {
                        if (playerPositions.get(i) == 15 && playerPositions.get(i + 1) > 2 && playerPositions.get(i + 1) < 6) {

                            if (pos == 0) {
                                checkGreen1SendOppoBackToStart = true;
                            } else if (pos == 2) {
                                checkGreen2SendOppoBackToStart = true;
                            } else if (pos == 4) {
                                checkGreen3SendOppoBackToStart = true;
                            } else if (pos == 6) {
                                checkGreen4SendOppoBackToStart = true;
                            }
                        }
                    }
                    for (int i = 0; i < 8; i += 2) {
                        if (i != pos) {
                            if (compChoosePawn.get(i) == 15 && compChoosePawn.get(i) > 2 && compChoosePawn.get(i + 1) < 6) {
                                if (pos == 0) {
                                    checkGreen1Slide = false;
                                } else if (pos == 2) {
                                    checkGreen2Slide = false;
                                } else if (pos == 4) {
                                    checkGreen3Slide = false;
                                } else if (pos == 6) {
                                    checkGreen4Slide = false;
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 8; i += 2) {
                    if (compChoosePawn.get(row) == playerPositions.get(i) && compChoosePawn.get(col) == playerPositions.get(i + 1)) {
                        if (pos == 0) {
                            checkGreen1SendOppoBackToStart = true;
                        } else if (pos == 2) {
                            checkGreen2SendOppoBackToStart = true;
                        } else if (pos == 4) {
                            checkGreen3SendOppoBackToStart = true;
                        } else if (pos == 6) {
                            checkGreen4SendOppoBackToStart = true;
                        }
                    }
                }

            }

        }

        movesLeft = originalMovesLeft;
        System.out.println("MovesLeft" + movesLeft);
        System.out.println("Orei;" + originalMovesLeft);
        System.out.println("APos" + compCheckPos);
        System.out.println("APos" + compChoosePawn);
        canMove();
        //figuring out which pawn to move
        for (int i = 0; i < 8; i += 2) {
            checkSendStart(i);
        }
        if (compCheckPos.get(0) == 0 && compCheckPos.get(1) == 4 && green1SendsToStart == false ) {

            positions(0, 1);

        } else if (compCheckPos.get(2) == 0 && compCheckPos.get(3) == 4 && green2SendsToStart == false) {

            positions(2, 3);

        } else if (compCheckPos.get(4) == 0 && compCheckPos.get(5) == 4 && green3SendsToStart == false) {

            positions(4, 5);

        } else if (compCheckPos.get(6) == 0 && compCheckPos.get(7) == 4 && green4SendsToStart == false) {

            positions(6, 7);

        } else if (checkGreen1Home == true && green1CantMove==false) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (checkGreen2Home == true && green2CantMove==false) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (checkGreen3Home == true && green3CantMove==false) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (checkGreen4Home == true && green4CantMove==false) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        } else if (checkGreen1SendOppoBackToStart == true ) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (checkGreen2SendOppoBackToStart == true) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (checkGreen3SendOppoBackToStart == true) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (checkGreen4SendOppoBackToStart == true) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        } else if (checkGreen1Safety == true && green1SendsToStart == false && green1CantMove==false) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (checkGreen2Safety == true && green2SendsToStart == false && green2CantMove==false) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (checkGreen3Safety == true && green3SendsToStart == false && green3CantMove==false) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (checkGreen4Safety == true && green4SendsToStart == false && green4CantMove==false) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        } else if (checkGreen1Slide == true && green1SendsToStart == false) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (checkGreen2Slide == true && green2SendsToStart == false ) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (checkGreen3Slide == true && green3SendsToStart == false) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (checkGreen4Slide == true && green4SendsToStart == false) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        } else if (green1Out == true && green1SendsToStart == false  && green1CantMove==false) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (green2Out == true && green2SendsToStart == false  && green2CantMove==false) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (green3Out == true && green3SendsToStart == false  && green3CantMove==false) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (green4Out == true && green4SendsToStart == false  && green4CantMove==false) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        } else if (green1Out == true  && green1CantMove==false) {
            compPawnMoved = "Moved pawn 1. ";
            positions(0, 1);
        } else if (green2Out == true  && green2CantMove==false) {
            compPawnMoved = "Moved pawn 2. ";
            positions(2, 3);
        } else if (green3Out == true  && green3CantMove==false) {
            compPawnMoved = "Moved pawn 3. ";
            positions(4, 5);
        } else if (green4Out == true  && green4CantMove==false) {
            compPawnMoved = "Moved pawn 4. ";
            positions(6, 7);
        }

    }

    public String pickUpChanceCard() { //saying that chance card is needed
        if (Card.equals("RN13") || useChoice == true) {
            useChoice = false;
            return "true";
        } else {
            return "false";
        }

    }

    public void swap(int bluePawn, int greenPawn) {
        int compRow = compCheckPos.get(greenPawn);
        int compCol = compCheckPos.get(greenPawn + 1);
        int playerRow = playerPositions.get(bluePawn);
        int playerCol = playerPositions.get(bluePawn + 1);
        playerPositions.set(bluePawn, compRow);
        playerPositions.set(bluePawn + 1, compCol);
        compCheckPos.set(greenPawn, playerRow);
        compCheckPos.set(greenPawn + 1, playerCol);
    }

    public void noChoiceChanceCards() {
//        if (Card.equals("CN0")) {//switch pawn with other player
//            boolean foundSwitch = false;
//            bluePawnValid();
//            greenPawnValid();
//            if (green1Valid == true || green2Valid == true || green3Valid == true || green4Valid == true) {
//                if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) {
//                    for (int b = 0; b < 8; b += 2) {
//                        if (playerPositions.get(b) == 0 && playerPositions.get(b + 1) < 3) {
//                            for (int i = 0; i < 8; i += 2) {
//                                if (compCheckPos.get(i) == 0) {
//                                    System.out.println("Swicyh with apnw");
//                                    foundSwitch = true;
//                                    swap(b, i);
//                                }
//                            }
//                            if (foundSwitch == false) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i + 1) == 15) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                            }
//                            if (foundSwitch == false) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i) == 15) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                            }
//                            if (foundSwitch == false) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i + 1) == 0) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                    if (foundSwitch == false) {
//                        for (int b = 0; b < 8; b += 2) {
//                            if (playerPositions.get(b + 1) == 0) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i) == 0) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i + 1) == 15) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i) == 15) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i + 1) == 0) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//
//                    }
//                    if (foundSwitch == false) {
//                        for (int b = 0; b < 8; b += 2) {
//                            if ((playerPositions.get(b) == 15 && playerPositions.get(b + 1) < 13)) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i) == 0) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i + 1) == 15) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i) == 15) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//
//                    }
//                    if (foundSwitch == false) {
//                        for (int b = 0; b < 8; b += 2) {
//                            if ((playerPositions.get(b) == 15 && playerPositions.get(b + 1) > 13) || playerPositions.get(b + 1) == 15) {
//                                for (int i = 0; i < 8; i += 2) {
//                                    if (compCheckPos.get(i) == 0) {
//                                        System.out.println("Swicyh with apnw");
//                                        foundSwitch = true;
//                                        swap(b, i);
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i + 1) == 15) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//                                if (foundSwitch == false) {
//                                    for (int i = 0; i < 8; i += 2) {
//                                        if (compCheckPos.get(i) == 15 && compCheckPos.get(i + 1) < 13) {
//                                            System.out.println("Swicyh with apnw");
//                                            foundSwitch = true;
//                                            swap(b, i);
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//
//                    }
//                    if (foundSwitch == false) {
//                        if (green1Valid == true) {
//                            if (blue1Valid == true) {
//                                swap(0, 0);
//                            } else if (blue2Valid == true) {
//                                swap(2, 0);
//                            } else if (blue3Valid == true) {
//                                swap(4, 0);
//                            } else if (blue4Valid == true) {
//                                swap(6, 0);
//                            }
//                        } else if (green2Valid == true) {
//                            if (blue1Valid == true) {
//                                swap(0, 2);
//                            } else if (blue2Valid == true) {
//                                swap(2, 2);
//                            } else if (blue3Valid == true) {
//                                swap(4, 2);
//                            } else if (blue4Valid == true) {
//                                swap(6, 2);
//                            }
//                        } else if (green3Valid == true) {
//                            if (blue1Valid == true) {
//                                swap(0, 4);
//                            } else if (blue2Valid == true) {
//                                swap(2, 4);
//                            } else if (blue3Valid == true) {
//                                swap(4, 4);
//                            } else if (blue4Valid == true) {
//                                swap(6, 4);
//                            }
//
//                        } else if (green4Valid == true) {
//                            if (blue1Valid == true) {
//                                swap(0, 6);
//                            } else if (blue2Valid == true) {
//                                swap(2, 6);
//                            } else if (blue3Valid == true) {
//                                swap(4, 6);
//                            } else if (blue4Valid == true) {
//                                swap(6, 6);
//                            }
//
//                        }
//                    }
//
//                } else {
//                    System.out.println("skipped turn sicne cant do anything");
//                }
//            } else {
//                System.out.println("skipped turn sicne cant do anything");
//            }
//
//        } 
        if (Card.equals("CN1")) { //roll dice to detrmine amount of moves
            movesLeft = (int) (Math.random() * (6)) + 1;
            compDice = "Dice number was " + Integer.toString(movesLeft);
            chooseBestPawn();

        } else if (Card.equals("CN2")) { //choose pawn to move to nearest corner
            greenPawnValid();
            if (green1Valid == true || green2Valid == true || green3Valid == true || green4Valid == true) {
                //corners need to add
                choosePawnNearestCorner();

            }
            else{
                compPawnMoved="Turn Skipped.";
            }

        } else if (Card.equals("CN3")) { //send opponent back to start
            bluePawnValid();
            if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) { //move pawn closest to home back to start 
                choosePawnSendBack();
            }
            else{
                compPawnMoved="Turn Skipped.";
            }

        }
    }

    public void choosePawnNearestCorner() { //choosing which pawn to move the nearest corner by looking at the pawns positions
        if (compCheckPos.get(1) == 0 && compCheckPos.get(0) != 0) {
            compPawnMoved = "Moved pawn 1. ";
            compCheckPos.set(0, 0);
            compCheckPos.set(1, 0);
        } else if (compCheckPos.get(3) == 0 && compCheckPos.get(2) != 0) {
            compPawnMoved = "Moved pawn 2. ";
            compCheckPos.set(2, 0);
            compCheckPos.set(3, 0);

        } else if (compCheckPos.get(5) == 0 && compCheckPos.get(4) != 0) {
            compPawnMoved = "Moved pawn 3. ";
            compCheckPos.set(4, 0);
            compCheckPos.set(5, 0);

        } else if (compCheckPos.get(7) == 0 && compCheckPos.get(6) != 0) {
            compPawnMoved = "Moved pawn 4. ";
            compCheckPos.set(6, 0);
            compCheckPos.set(7, 0);

        } else if (compCheckPos.get(0) == 15) {
            compPawnMoved = "Moved pawn 1. ";
            compCheckPos.set(0, 15);
            compCheckPos.set(1, 0);

        } else if (compCheckPos.get(2) == 15) {
            compPawnMoved = "Moved pawn 2. ";
            compCheckPos.set(2, 15);
            compCheckPos.set(3, 0);

        } else if (compCheckPos.get(4) == 15) {
            compPawnMoved = "Moved pawn 3. ";
            compCheckPos.set(4, 15);
            compCheckPos.set(5, 0);

        } else if (compCheckPos.get(6) == 15) {
            compPawnMoved = "Moved pawn 4. ";
            compCheckPos.set(6, 15);
            compCheckPos.set(7, 0);

        } else if (compCheckPos.get(1) == 15) {
            compPawnMoved = "Moved pawn 1. ";
            compCheckPos.set(0, 15);
            compCheckPos.set(1, 15);

        } else if (compCheckPos.get(3) == 15) {
            compPawnMoved = "Moved pawn 2. ";
            compCheckPos.set(2, 15);
            compCheckPos.set(3, 15);

        } else if (compCheckPos.get(5) == 15) {
            compPawnMoved = "Moved pawn 3. ";
            compCheckPos.set(4, 15);
            compCheckPos.set(5, 15);

        } else if (compCheckPos.get(7) == 15) {
            compPawnMoved = "Moved pawn 4. ";
            compCheckPos.set(6, 15);
            compCheckPos.set(7, 15);

        } else if (compCheckPos.get(0) == 0) {
            compPawnMoved = "Moved pawn 1. ";
            compCheckPos.set(0, 0);
            compCheckPos.set(1, 15);

        } else if (compCheckPos.get(2) == 0) {
            compPawnMoved = "Moved pawn 2. ";
            compCheckPos.set(2, 0);
            compCheckPos.set(3, 15);

        } else if (compCheckPos.get(4) == 0) {
            compPawnMoved = "Moved pawn 3. ";
            compCheckPos.set(4, 0);
            compCheckPos.set(5, 15);

        } else if (compCheckPos.get(6) == 0) {
            compPawnMoved = "Moved pawn 4. ";
            compCheckPos.set(6, 0);
            compCheckPos.set(7, 15);

        }

    }

    public void choosePawnSendBack() { //checking which pawn to send back to accdording to players pawn position
        if (playerPositions.get(0) == 15 && playerPositions.get(1) > 12) {
            playerStart(0);
        } else if (playerPositions.get(2) == 15 && playerPositions.get(3) > 12) {
            playerStart(2);
        } else if (playerPositions.get(4) == 15 && playerPositions.get(5) > 12) {
            playerStart(4);
        } else if (playerPositions.get(6) == 15 && playerPositions.get(7) > 12) {
            playerStart(6);
        } else if (playerPositions.get(0) > 7 && playerPositions.get(1) == 15) {
            playerStart(0);
        } else if (playerPositions.get(2) > 7 && playerPositions.get(3) == 15) {
            playerStart(2);
        } else if (playerPositions.get(4) > 7 && playerPositions.get(5) == 15) {
            playerStart(4);
        } else if (playerPositions.get(6) > 7 && playerPositions.get(7) == 15) {
            playerStart(6);
        } else if (playerPositions.get(0) <= 7 && playerPositions.get(1) == 15) {
            playerStart(0);
        } else if (playerPositions.get(2) <= 7 && playerPositions.get(3) == 15) {
            playerStart(2);
        } else if (playerPositions.get(4) <= 7 && playerPositions.get(5) == 15) {
            playerStart(4);
        } else if (playerPositions.get(6) <= 7 && playerPositions.get(7) == 15) {
            playerStart(6);
        } else if (playerPositions.get(0) == 0 && playerPositions.get(1) > 7) {
            playerStart(0);
        } else if (playerPositions.get(2) == 0 && playerPositions.get(3) > 7) {
            playerStart(2);
        } else if (playerPositions.get(4) == 0 && playerPositions.get(5) > 7) {
            playerStart(4);
        } else if (playerPositions.get(6) == 0 && playerPositions.get(7) > 7) {
            playerStart(6);
        } else if (playerPositions.get(0) == 0 && playerPositions.get(1) <= 7) {
            playerStart(0);
        } else if (playerPositions.get(2) == 0 && playerPositions.get(3) <= 7) {
            playerStart(2);
        } else if (playerPositions.get(4) == 0 && playerPositions.get(5) <= 7) {
            playerStart(4);
        } else if (playerPositions.get(6) == 0 && playerPositions.get(7) <= 7) {
            playerStart(6);
        } else if (playerPositions.get(0) <= 7 && playerPositions.get(1) == 0) {
            playerStart(0);
        } else if (playerPositions.get(2) <= 7 && playerPositions.get(3) == 0) {
            playerStart(2);
        } else if (playerPositions.get(4) <= 7 && playerPositions.get(5) == 0) {
            playerStart(4);
        } else if (playerPositions.get(6) <= 7 && playerPositions.get(7) == 0) {
            playerStart(6);
        } else if (playerPositions.get(0) > 7 && playerPositions.get(1) == 0) {
            playerStart(0);
        } else if (playerPositions.get(2) > 7 && playerPositions.get(3) == 0) {
            playerStart(2);
        } else if (playerPositions.get(4) > 7 && playerPositions.get(5) == 0) {
            playerStart(4);
        } else if (playerPositions.get(6) > 7 && playerPositions.get(7) == 0) {
            playerStart(6);
        } else if (playerPositions.get(0) == 15 && playerPositions.get(1) <= 7) {
            playerStart(0);
        } else if (playerPositions.get(2) == 15 && playerPositions.get(3) <= 7) {
            playerStart(2);
        } else if (playerPositions.get(4) == 15 && playerPositions.get(5) <= 7) {
            playerStart(4);
        } else if (playerPositions.get(6) == 15 && playerPositions.get(7) <= 7) {
            playerStart(6);
        } else if (playerPositions.get(0) == 15 && playerPositions.get(1) > 7) {
            playerStart(0);
        } else if (playerPositions.get(2) == 15 && playerPositions.get(3) > 7) {
            playerStart(2);
        } else if (playerPositions.get(4) == 15 && playerPositions.get(5) > 7) {
            playerStart(4);
        } else if (playerPositions.get(6) == 15 && playerPositions.get(7) > 7) {
            playerStart(6);
        }
    }

    public void choiceChanceCards() {
        if (Card.equals("CC0")) { //Move 1 pawn on board to home if 1 or 6 rolled, else move move 1 pawn on baord back to start
            greenPawnValid();
            compChoice = " Chose Choice 2. ";
            System.out.println("Decidded not to use chance card");
        } else if (Card.equals("CC1")) { //if odd rolled move one pwn to the nearest. if even move oawn to nearest back corner
            //if at least one pawn is in row 0 then use card
            if ((compCheckPos.get(0) == 0 && compCheckPos.get(1) != 0) || (compCheckPos.get(2) == 0 && compCheckPos.get(3) != 0) || (compCheckPos.get(4) == 0 && compCheckPos.get(5) != 0) || (compCheckPos.get(6) == 0 && compCheckPos.get(7) != 0)) {
                int diceRoll = (int) (Math.random() * (6)) + 1;
                compDice = "Dice number was " + Integer.toString(diceRoll);
                compChoice = " Chose Choice 1. ";
                if (diceRoll == 2 || diceRoll == 4 || diceRoll == 6) {
                    if (compCheckPos.get(0) == 0 && compCheckPos.get(1) != 0) {
                        compPawnMoved = "Move pawn 1. ";
                        compCheckPos.set(0, 0);
                        compCheckPos.set(1, 0);
                    } else if (compCheckPos.get(2) == 0 && compCheckPos.get(3) != 0) {
                        compPawnMoved = "Move Pawn 2. ";
                        compCheckPos.set(2, 0);
                        compCheckPos.set(3, 0);

                    } else if (compCheckPos.get(4) == 0 && compCheckPos.get(5) != 0) {
                        compPawnMoved = "Move pawn 3. ";
                        compCheckPos.set(4, 0);
                        compCheckPos.set(5, 0);

                    } else if (compCheckPos.get(6) == 0 && compCheckPos.get(7) != 0) {
                        compPawnMoved = "Move pawn 4. ";
                        compCheckPos.set(6, 0);
                        compCheckPos.set(7, 0);

                    }
                } else {//moe to nearest corner
                    greenPawnValid();
                    if (green1Valid == true || green2Valid == true || green3Valid == true || green4Valid == true) {
                        //corners need to add
                        choosePawnNearestCorner();

                    }
                }
            } else {
                compChoice = " Chose Choice 2. ";
                System.out.println("Disnt use card");
            }
            //else dont use card

        } else if (Card.equals("CC2")) { //if odd is rolled yiu can one pawn from start box and put in home box else all pawns in home go back to start box
            if (homeCounter == 0) {
                int diceRoll = (int) (Math.random() * (6)) + 1;
                compDice = "Dice number was " + Integer.toString(diceRoll);
                compChoice = " Chose Choice 1. ";
                if (diceRoll == 1 || diceRoll == 3 || diceRoll == 5) {
                    if (green1Out == false) {
                        compPawnMoved = "Move pawn 1. ";
                        green1Home = true;
                        homeCounter++;
                        compCheckPos.set(0, 7);
                        compCheckPos.set(1, 3);
                    } else if (green2Out == false) {
                        compPawnMoved = "Move pawn 2. ";
                        green2Home = true;
                        homeCounter++;
                        compCheckPos.set(2, 7);
                        compCheckPos.set(3, 3);
                    } else if (green3Out == false) {
                        compPawnMoved = "Move pawn 3. ";
                        green3Home = true;
                        homeCounter++;
                        compCheckPos.set(4, 7);
                        compCheckPos.set(5, 3);
                    } else if (green4Out == false) {
                        compPawnMoved = "Move pawn 4. ";
                        green4Home = true;
                        homeCounter++;
                        compCheckPos.set(6, 7);
                        compCheckPos.set(7, 3);
                    }

                } else {
                    compPawnMoved = "Turn skipped. ";
                }
            } else {
                compChoice = " Chose Choice 2. ";
            }

        } else if (Card.equals("CC3")) {//if odd is rolled bump other player back to start else bump your pawn back to start
            if (green1Out == false && green2Out == false && green3Out == false && green4Out == false) {
                int diceRoll = (int) (Math.random() * (6)) + 1;
                compDice = "Dice number was " + Integer.toString(diceRoll);
                compChoice = " Chose Choice 1. ";
                if (diceRoll == 1 || diceRoll == 3 || diceRoll == 5) {
                    bluePawnValid();
                    if (blue1Valid == true || blue2Valid == true || blue3Valid == true || blue4Valid == true) { //move pawn closest to home back to start 
                        choosePawnSendBack();

                    }
                } else {
                    System.out.println("No pawns to send bakc");
                    compPawnMoved = "Turn skipped. ";
                }

            } else {
                compChoice = " Chose Choice 2. ";
            }

        }

    }

    private void bluePawnValid() { //checking if pawn is not in home start or safety zone for player
        blue1Valid = false;
        blue2Valid = false;
        blue3Valid = false;
        blue4Valid = false;
        if (playerPositions.get(0) == 0 || playerPositions.get(1) == 0 || playerPositions.get(0) == 15 || playerPositions.get(1) == 15) {
            blue1Valid = true;
        }
        if (playerPositions.get(2) == 0 || playerPositions.get(3) == 0 || playerPositions.get(2) == 15 || playerPositions.get(3) == 15) {
            blue2Valid = true;
        }
        if (playerPositions.get(4) == 0 || playerPositions.get(5) == 0 || playerPositions.get(4) == 15 || playerPositions.get(5) == 15) {
            blue3Valid = true;
        }
        if (playerPositions.get(6) == 0 || playerPositions.get(7) == 0 || playerPositions.get(6) == 15 || playerPositions.get(7) == 15) {
            blue4Valid = true;
        }
    }

    public void greenPawnValid() { //checking if pawn is not in home start or safety zoe for computer
        green1Valid = false;
        green2Valid = false;
        green3Valid = false;
        green4Valid = false;
        if (compCheckPos.get(0) == 0 || compCheckPos.get(1) == 0 || compCheckPos.get(0) == 15 || compCheckPos.get(1) == 15) {
            green1Valid = true;
        }
        if (compCheckPos.get(2) == 0 || compCheckPos.get(3) == 0 || compCheckPos.get(2) == 15 || compCheckPos.get(3) == 15) {
            green2Valid = true;
        }
        if (compCheckPos.get(4) == 0 || compCheckPos.get(5) == 0 || compCheckPos.get(4) == 15 || compCheckPos.get(5) == 15) {
            green3Valid = true;
        }
        if (compCheckPos.get(6) == 0 || compCheckPos.get(7) == 0 || compCheckPos.get(6) == 15 || compCheckPos.get(7) == 15) {
            green4Valid = true;
        }
    }

    private void canMove() { //checking if the pawn can move becuase it may be close to home
        System.out.println("Moves Left:" + movesLeft + "canMove");
        green1CantMove = false;
        green2CantMove = false;
        green3CantMove = false;
        green4CantMove = false;
        if(green1Out==false || green1Home==true){
            green1CantMove = true;
        }
        if(green2Out==false || green2Home==true){
            green2CantMove = true;
        }
        if(green3Out==false || green3Home==true){
            green3CantMove = true;
        }
        if(green4Out==false || green4Home==true){
            green4CantMove = true;
        }
        for (int i = 0; i < 8; i += 2) {
            if ((compCheckPos.get(i) > 4 && compCheckPos.get(i + 1) == 0)) {//figuring out if can do move accoiridng to card becuise close to home
                if (movesLeft > 11 || (compCheckPos.get(i) == 3 && movesLeft > 10) || (compCheckPos.get(i) == 2 && movesLeft > 9) || (compCheckPos.get(i) == 1 && movesLeft > 8) || (compCheckPos.get(i) == 0 && movesLeft > 8)) {
                    System.out.println("Cant move");
                    if (i == 0) {
                        green1CantMove = true;
                    }
                    if (i == 2) {
                        green2CantMove = true;
                    }
                    if (i == 4) {
                        green3CantMove = true;
                    }
                    if (i == 6) {
                        green4CantMove = true;
                    }
                }
            } else if ((compCheckPos.get(i) == 0 && compCheckPos.get(i + 1) < 3)) {
                if (movesLeft > 8 || (compCheckPos.get(i + 1) == 1 && movesLeft > 7) || (compCheckPos.get(i + 1) == 2 && movesLeft > 6)) {
                    System.out.println("Cant move");
                    if (i == 0) {
                        green1CantMove = true;
                    }
                    if (i == 2) {
                        green2CantMove = true;
                    }
                    if (i == 4) {
                        green3CantMove = true;
                    }
                    if (i == 6) {
                        green4CantMove = true;
                    }
                }
            } else if (compCheckPos.get(i) < 6 && compCheckPos.get(i + 1) == 2) {
                if (movesLeft > 6 || (compCheckPos.get(i) == 1 && movesLeft > 5) || (compCheckPos.get(i) == 2 && movesLeft > 4) || (compCheckPos.get(i) == 3 && movesLeft > 3) || (compCheckPos.get(i) == 4 && movesLeft > 2) || (compCheckPos.get(i) == 5 && movesLeft > 1)) {
                    System.out.println("Cant move");
                    if (i == 0) {
                        green1CantMove = true;
                    }
                    if (i == 2) {
                        green2CantMove = true;
                    }
                    if (i == 4) {
                        green3CantMove = true;
                    }
                    if (i == 6) {
                        green4CantMove = true;
                    }
                }
            }
        }
    }

    public void positions(int row, int col) { //moving pawn

        System.out.println("Moves Lrft: " + movesLeft);
        boolean canMove = true;
        while (movesLeft > 0) {
            movesLeft--;
            if (compCheckPos.get(row) >= 0 && compCheckPos.get(row) <= 5 && compCheckPos.get(col) == 2) { //dealing with safety zone
                compCheckPos.set(row, compCheckPos.get(row) + 1);
//                if (compCheckPos.get(row) != 0) {
//                    //green1Valid = false;
//                }

                if (compCheckPos.get(row) == 6) { //need to do when reaches home and wins
                    if (row == 0 && col == 1) {
                        green1Out = false;
                        green1Home = true;
                        homeCounter++;
                        compCheckPos.set(0, 7);
                        compCheckPos.set(1, 3);
                    } else if (row == 2 && col == 3) {
                        green2Out = false;
                        green2Home = true;
                        homeCounter++;
                        compCheckPos.set(2, 7);
                        compCheckPos.set(3, 3);
                    } else if (row == 4 && col == 5) {
                        green3Out = false;
                        green3Home = true;
                        homeCounter++;
                        compCheckPos.set(4, 7);
                        compCheckPos.set(5, 3);
                    } else if (row == 6 && col == 7) {
                        green4Out = false;
                        green4Home = true;
                        homeCounter++;
                        compCheckPos.set(6, 7);
                        compCheckPos.set(7, 3);
                    }

                }
                if (homeCounter == 4) {
                    System.out.println("Comp won<<<<");
                }
            } else if (compCheckPos.get(row) == 15 && compCheckPos.get(col) == 0) { //next four are dealing with corners
                compCheckPos.set(row, 14);
                compCheckPos.set(col, 0);

            } else if (compCheckPos.get(row) == 0 && compCheckPos.get(col) == 0) {
                compCheckPos.set(row, 0);
                compCheckPos.set(col, 1);

            } else if (compCheckPos.get(row) == 0 && compCheckPos.get(col) == 15) {

                compCheckPos.set(row, 1);
                compCheckPos.set(col, 15);

            } else if (compCheckPos.get(row) == 15 && compCheckPos.get(col) == 15) {
                compCheckPos.set(row, 15);
                compCheckPos.set(col, 14);
            } else if (compCheckPos.get(row) == 0) { //moving pawn forward in different areas. will only for regular no choice cards
                compCheckPos.set(col, compCheckPos.get(col) + 1);
            } else if (compCheckPos.get(row) == 15) {
                compCheckPos.set(col, compCheckPos.get(col) - 1);
            } else if (compCheckPos.get(col) == 0) {
                compCheckPos.set(row, compCheckPos.get(row) - 1);
            } else if (compCheckPos.get(col) == 15) {
                compCheckPos.set(row, compCheckPos.get(row) + 1);
            }

        }
        special(row, col);
    }

    private void compStart(int i) { //sending computer pawn back to start

        compSendBackItself = "Sent itself back to start.";
        if (i == 0) {
            green1Out = false;
            compCheckPos.set(0, 1);
            compCheckPos.set(1, 5);
        }
        if (i == 2) {
            green2Out = false;
            compCheckPos.set(2, 2);
            compCheckPos.set(3, 5);
        }
        if (i == 4) {
            green3Out = false;
            compCheckPos.set(4, 1);
            compCheckPos.set(5, 4);
        }
        if (i == 6) {
            green4Out = false;
            compCheckPos.set(6, 2);
            compCheckPos.set(7, 4);
        }

    }

    private void special(int row, int col) { //checking if on slider and if on another pawn
        for (int i = 0; i < 8; i += 2) {
            if (compCheckPos.get(row) == playerPositions.get(i) && compCheckPos.get(col) == playerPositions.get(i + 1)) {
                playerStart(i);

                compSendBackPlayer = "Sent player back to start. ";

            }
            if (i != row) {
                if (compCheckPos.get(row) == compCheckPos.get(i) && compCheckPos.get(col) == compCheckPos.get(i + 1)) {
                    if ((compCheckPos.get(row) == 6 && compCheckPos.get(col) == 2) || (compCheckPos.get(row) == 6 && compCheckPos.get(col) == 3) || (compCheckPos.get(row) == 7 && compCheckPos.get(col) == 2) || (compCheckPos.get(row) == 7 && compCheckPos.get(col) == 3)) {

                    } else {
                        compStart(i);
                        compSendBackItself = "Sent itself back to start.";
                    }

                }
            }
        }
        if (compCheckPos.get(row) == 14 && compCheckPos.get(col) == 0) {
            compCheckPos.set(row, 11);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) > 11 && playerPositions.get(i) < 14 && playerPositions.get(i + 1) == 0) {
                    playerStart(i);

                    compSendBackPlayer = "Sent player back to start. ";

                }
                if (i != row) {
                    if (compCheckPos.get(i) > 11 && compCheckPos.get(i) < 14 && compCheckPos.get(i + 1) == 0) {
                        compStart(i);
                        compSendBackItself = "Sent itself back to start.";
                    }

                }
            }

        }
        if (compCheckPos.get(row) == 6 && compCheckPos.get(col) == 0) {
            compCheckPos.set(row, 2);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) > 2 && playerPositions.get(i) < 6 && playerPositions.get(i + 1) == 0) {
                    playerStart(i);

                    compSendBackPlayer = "Sent player back to start. ";

                }
                if (i != row) {
                    if (compCheckPos.get(i) > 2 && compCheckPos.get(i) < 6 && compCheckPos.get(i + 1) == 0) {
                        compStart(i);
                        compSendBackItself = "Sent itself back to start.";
                    }

                }
            }

        }
        if (compCheckPos.get(row) == 1 && compCheckPos.get(col) == 15) {
            compCheckPos.set(row, 4);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) > 1 && playerPositions.get(i) < 4 && playerPositions.get(i + 1) == 15) {
                    playerStart(i);

                    compSendBackPlayer = "Sent player back to start. ";

                }
                if (i != row) {
                    if (compCheckPos.get(i) > 1 && compCheckPos.get(i) < 4 && compCheckPos.get(i + 1) == 15) {
                        compStart(i);
                        compSendBackItself = "Sent itself back to start.";
                    }

                }
            }
        }
        if (compCheckPos.get(row) == 9 && compCheckPos.get(col) == 15) {
            compCheckPos.set(row, 13);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) > 9 && playerPositions.get(i) < 13 && playerPositions.get(i + 1) == 15) {

                    playerStart(i);
                }
                if (i != row) {
                    if (compCheckPos.get(i) > 9 && compCheckPos.get(i) < 13 && compCheckPos.get(i + 1) == 15) {
                        compStart(i);
                    }

                }
            }
        }
        if (compCheckPos.get(row) == 15 && compCheckPos.get(col) == 14) {
            compCheckPos.set(col, 11);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) == 15 && playerPositions.get(i + 1) > 11 && playerPositions.get(i + 1) < 14) {

                    playerStart(i);
                }
                if (i != row) {
                    if (compCheckPos.get(i) == 15 && compCheckPos.get(i + 1) > 11 && compCheckPos.get(i + 1) < 14) {
                        compStart(i);
                    }

                }
            }
        }
        if (compCheckPos.get(row) == 15 && compCheckPos.get(col) == 6) {
            compCheckPos.set(col, 2);
            compSlide = " Slided on slider. ";

            for (int i = 0; i < 8; i += 2) {
                if (playerPositions.get(i) == 15 && playerPositions.get(i + 1) > 2 && playerPositions.get(i + 1) < 6) {

                    playerStart(i);
                }
                if (i != row) {
                    if (compCheckPos.get(i) == 15 && compCheckPos.get(i + 1) > 2 && compCheckPos.get(i + 1) < 6) {
                        compStart(i);
                    }

                }
            }
        }
        for (int i = 0; i < 8; i += 2) {
            if (compCheckPos.get(row) == playerPositions.get(i) && compCheckPos.get(col) == playerPositions.get(i + 1)) {
                playerStart(i);
            }
            if (i != row) {
                if (compCheckPos.get(row) == compCheckPos.get(i) && compCheckPos.get(col) == compCheckPos.get(i + 1)) {
                    if ((compCheckPos.get(row) == 6 && compCheckPos.get(col) == 2) || (compCheckPos.get(row) == 6 && compCheckPos.get(col) == 3) || (compCheckPos.get(row) == 7 && compCheckPos.get(col) == 2) || (compCheckPos.get(row) == 7 && compCheckPos.get(col) == 3)) {

                    } else {
                        compStart(i);
                        compSendBackItself = "Sent itself back to start.";
                    }

                }
            }
        }

    }

    public void playerStart(int i) { //sending a players pawn back to start

        compSendBackPlayer = "Sent player back to start. ";

        if (i == 0) {
            compPawnMoved = "Moved pawn 1 back. ";
            blue1Out = false;
            playerPositions.set(0, 14);
            playerPositions.set(1, 11);

        }
        if (i == 2) {
            compPawnMoved = "Moved pawn 2 back. ";
            blue2Out = false;
            playerPositions.set(2, 13);
            playerPositions.set(3, 11);

        }
        if (i == 4) {
            compPawnMoved = "Moved pawn 3 back. ";
            blue3Out = false;
            playerPositions.set(4, 14);
            playerPositions.set(5, 10);

        }
        if (i == 6) {
            compPawnMoved = "Moved pawn 4 back. ";
            blue4Out = false;
            playerPositions.set(6, 13);
            playerPositions.set(7, 10);

        }
    }

    public ArrayList<Integer> updatedPosition() { //returning computers positions
        return compCheckPos;
    }

    public ArrayList<Integer> updatedPlayerPosition() { //returning players positions
        return playerPositions;
    }

    public boolean bluePawn1Start() { //returning if pawn is still on board
        return blue1Out;
    }

    public boolean bluePawn2Start() { //returning if pawn is still on board
        return blue2Out;
    }

    public boolean bluePawn3Start() { //returning if pawn is still on board
        return blue3Out;
    }

    public boolean bluePawn4Start() { //returning if pawn is still on board
        return blue4Out;
    }

    public boolean greenPawn1Valid(ArrayList<Integer> pos) { //seeing if pawn is valid
        compCheckPos = pos;
        greenPawnValid();
        return green1Valid;
    }

    public boolean greenPawn2Valid(ArrayList<Integer> pos) { //seeing if pawn is valid
        compCheckPos = pos;
        greenPawnValid();
        return green2Valid;
    }

    public boolean greenPawn3Valid(ArrayList<Integer> pos) { //seeing if pawn is valid
        compCheckPos = pos;
        greenPawnValid();
        return green3Valid;
    }

    public boolean greenPawn4Valid(ArrayList<Integer> pos) { //seeing if pawn is valid
        compCheckPos = pos;
        greenPawnValid();
        return green4Valid;
    }

    public void green1Start() { //means that computer pawn was sent back to start by player
        green1Out = false;
    }

    public void green2Start() {
        green2Out = false;
    }

    public void green3Start() {
        green3Out = false;
    }

    public void green4Start() {
        green4Out = false;
    }

    public void change(int num) {  //reseting variables if computer sent back to home
        if (num == 1) {
            green1Valid = false;
            green1Out = false;
        }
        if (num == 2) {
            green2Valid = false;
            green2Out = false;
        }
        if (num == 3) {
            green3Valid = false;
            green3Out = false;
        }
        if (num == 4) {
            green4Valid = false;
            green4Out = false;
        }
    }

    public String compMove() { //saying what computer did 
        return pickedUpChanceCard + compChoice + compDice + " " + compPawnMoved;

    }

    public String compSpecial() { //saying if computer did anything special
        return compSlide + compSendBackPlayer + compSendBackItself;

    }

    public int checkWin() { //how mnay in home box
        return homeCounter;
    }

    public void reset() { //reseting varibles for a new game
        compCheckPos.clear();
        compChoosePawn.clear();
        playerPositions.clear();
        Card = "";
        //compMove = "";
        compSlide = "";
        compSendBackPlayer = "";
        compSendBackItself = "";
        compChoice = "";
        compDice = "";
        compPawnMoved = "";

        green1Out = false;
        green2Out = false;
        green3Out = false;
        green4Out = false;
        green1Home = false;
        green2Home = false;
        green3Home = false;
        green4Home = false;
        green1Valid = false;
        green2Valid = false;
        green3Valid = false;
        green4Valid = false;
        blue1Valid = false;
        blue2Valid = false;
        blue3Valid = false;
        blue4Valid = false;
        blue1Out = false;
        blue2Out = false;
        blue3Out = false;
        blue4Out = false;
        green1CantMove = false;
        green2CantMove = false;
        green3CantMove = false;
        green4CantMove = false;
        green1SendsToStart = false;
        green2SendsToStart = false;
        green3SendsToStart = false;
        green4SendsToStart = false;
        //private boolean sendsToStart = false;
        homeCounter = 0;
        //private int counter = 0;
        movesLeft = 0;
        useChoice = false;
        pickedUpChanceCard = "";
    }

}
