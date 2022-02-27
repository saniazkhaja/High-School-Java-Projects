/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.simulation.project;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Sania Khaja
 */
public class FXMLDocumentController implements Initializable {

    //have a label that says day on top of the stats. once day==365 change label to yaer total stats.
    //then display the total varaibles
    @FXML
    private Label crimeAmountLabel, statsLabel, badPointsLabel, goodPointsLabel, crimeSolvedPoliceLabel, crimeSolvedGoodSideLabel, criminalsCaughtLabel, injuryLabel, deathLabel, winningSideLabel, populationLabel, scenarioLabel, chanceOfLivingLabel, controlLabel, experienceLabel, experienceNeededLabel, trustWorthinessLabel, righteousnessLabel, humaneLabel, strengthLabel, wisdomLabel, badSideCrimesLabel, supernaturalCrimesLabel, weatherEventsLabel, randomEventsLabel;
    double startTime;
    int crimeCounter = 0;
    int crimeOccuredTotal;
    int count = 0;
    int count2 = 0;
    boolean isBad = false;
    int gender;
    int age;
    int status;
    int family;
    int day;
    int crimeSolvedPoliceTotal;
    int crimeSolvedGoodSideTotal;
    int goodSidePointsTotal;
    int badSidePointsTotal;
    int criminalsCaughtTotal;
    int injuredTotal;
    int deathTotal;
    int population;
    boolean year = false;
    boolean goodSideTest = false;
    boolean badSideTest = false;
    boolean userGainingExperience = false;
    int simulationDay = 0;
    int crimeOccured;
    int righteousness = 20;
    int wisdom = 20;
    int humane = 20;
    int trustWorthiness = 20;
    int control = 20;
    int experience = 0;
    int chanceOfLiving = 500;
    int experienceNeeded = 15;
    int badSideCrimesTotal;
    int supernaturalCrimesTotal;
    boolean betrayal = false;
    int strength = (int) (Math.random() * 25) + 15;
    boolean smallMission = false;
    boolean mediumMission = false;
    boolean bigMission = false;
    boolean mission = false;
    ArrayList<Integer> criminals = new ArrayList<>();
    ArrayList<Integer> criminalPowerUser = new ArrayList<>();
    ArrayList<Integer> criminalPowerStrength = new ArrayList<>();
    ArrayList<Integer> goodPowerUser = new ArrayList<>();
    ArrayList<Integer> goodPowerStrength = new ArrayList<>();
    ArrayList<Integer> cityPeople = new ArrayList<>();
    ArrayList<Integer> goodSideStrength = new ArrayList<>();
    ArrayList<Integer> badSideStrength = new ArrayList<>();
    private int crimeIncrease = 0;
    private int crimeDecrease = 0;
    private int crimeAffectedDay;
    boolean crimeAffected = false;
    int daysAffected;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        day = (int) (Math.random() * 365) + 1;
        statsLabel.setText("Day's Statistics: " + simulationDay);
        start();
        for (int i = 0; i < 100000; i++) {
            gender = (int) (Math.random() * 2) + 1;
            //System.out.println(gender);
            age = (int) (Math.random() * 100) + 1;
            status = (int) (Math.random() * 4) + 1;
            family = (int) (Math.random() * 3) + 1;
            //ystem.out.println(age);
            Person people = new Person(gender, age, status, family);
            people.criminal();
            boolean hasPower = people.getPowers();
            boolean isCriminal = people.getCriminal();
            boolean isCriminalBadSide = people.getCriminalOrganization();
            if (hasPower == true && isCriminal == true) {
                criminals.add(1);//1 is criminal with power, 0 is crimnal without power
                criminalPowerUser.add(1);
                cityPeople.add(2);
                int startingStrength = (int) (Math.random() * 25) + 1;
                Powers power = new Powers(startingStrength);
                int strengthPerson = power.getRandomPeoplePowers();
                criminalPowerStrength.add(strengthPerson);
            } else if (isCriminal == true) {
                if (isCriminalBadSide == true) {
                    criminals.add(2); //not a power user but on bad side
                } else {
                    criminals.add(0);
                }
                cityPeople.add(1);
            } else if (hasPower == true) {
                goodPowerUser.add(1);
                cityPeople.add(3);
                int startingStrengthPerson = (int) (Math.random() * 25) + 1;
                Powers power = new Powers(startingStrengthPerson);
                int strengthPerson = power.getRandomPeoplePowers();
                goodPowerStrength.add(strengthPerson);
            } else {
                cityPeople.add(0);
            }

        }

        for (int i = 0; i < 13; i++) {
            int startingStrengthGroup = (int) (Math.random() * 25) + 5;
            Powers power = new Powers(startingStrengthGroup);
            int strengthGroup = power.getGoodSidePowers();
            goodSideStrength.add(strengthGroup);
        }
        for (int i = 0; i < 13; i++) {
            int startingStrengthGroup = (int) (Math.random() * 25) + 5;
            Powers power = new Powers(startingStrengthGroup);
            int strengthGroup = power.getBadSidePowers();
            badSideStrength.add(strengthGroup);
        }

    }

    @FXML
    private void makeYear() {
        simulationDay = 364;
    }

    @FXML
    private void betrayal() {
        if (betrayal == true) {
            scenarioLabel.setText("Sorry but you have already betrayed once");
        } else {
            betrayal = true;
            isBad = !isBad;
            Test userTest = new Test();
            if (isBad == true) {
                badSideTest = true;
                scenarioLabel.setText(userTest.badSideTest());
            } else {
                goodSideTest = true;
                scenarioLabel.setText(userTest.goodSideTest());
            }

        }

    }

    @FXML
    private void gainExperience() {
        userGainingExperience = true;
        if (isBad == true) {
            BadSide gainExperienceBad = new BadSide(experience);
            scenarioLabel.setText(gainExperienceBad.getGainExperience());
            experience = gainExperienceBad.getExperience();
        } else {
            GoodSide gainExperienceGood = new GoodSide(experience);
            scenarioLabel.setText(gainExperienceGood.getGainExperience());
            experience = gainExperienceGood.getExperience();
        }
        experienceLabel.setText(Integer.toString(experience));
    }

    @FXML
    private void smallMission() {

        if (experience >= experienceNeeded) {
            smallMission = true;
            mission = true;
            crimeAffected = true;
            if (isBad == true) {
                BadSide userMission = new BadSide();
                scenarioLabel.setText(userMission.smallMission());
            } else {
                GoodSide userMission = new GoodSide();
                scenarioLabel.setText(userMission.smallMission());
            }
        } else {
            scenarioLabel.setText("Sorry, but you need more experience.");
        }

    }

    @FXML
    private void mediumMission() {
        if (experience >= experienceNeeded) {
            mediumMission = true;
            mission = true;
            crimeAffected = true;
            if (isBad == true) {
                BadSide userMission = new BadSide();
                scenarioLabel.setText(userMission.mediumMission());
            } else {
                GoodSide userMission = new GoodSide();
                scenarioLabel.setText(userMission.mediumMission());
            }
        } else {
            scenarioLabel.setText("Sorry, but you need more experience.");
        }

    }

    @FXML
    private void bigMission() {

        if (experience >= experienceNeeded) {
            bigMission = true;
            mission = true;
            crimeAffected = true;
            if (isBad == true) {
                BadSide userMission = new BadSide();
                scenarioLabel.setText(userMission.bigMission());
            } else {
                GoodSide userMission = new GoodSide();
                scenarioLabel.setText(userMission.bigMission());
            }
        } else {
            scenarioLabel.setText("Sorry, but you need more experience.");
        }

    }

    @FXML
    private void goodSide(ActionEvent event) {
        isBad = false;
        goodSideTest = true;
        Test userTest = new Test();
        startTime = System.nanoTime();
        scenarioLabel.setText(userTest.goodSideTest());
        chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
        controlLabel.setText(Integer.toString(control));
        experienceLabel.setText(Integer.toString(experience));
        experienceNeededLabel.setText(Integer.toString(experienceNeeded));
        humaneLabel.setText(Integer.toString(humane));
        righteousnessLabel.setText(Integer.toString(righteousness));
        strengthLabel.setText(Integer.toString(strength));
        trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
        wisdomLabel.setText(Integer.toString(wisdom));

    }

    @FXML
    private void badSide(ActionEvent event) {
        isBad = true;
        badSideTest = true;
        Test userTest = new Test();
        startTime = System.nanoTime();
        scenarioLabel.setText(userTest.badSideTest());
        chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
        controlLabel.setText(Integer.toString(control));
        experienceLabel.setText(Integer.toString(experience));
        experienceNeededLabel.setText(Integer.toString(experienceNeeded));
        humaneLabel.setText(Integer.toString(humane));
        righteousnessLabel.setText(Integer.toString(righteousness));
        strengthLabel.setText(Integer.toString(strength));
        trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
        wisdomLabel.setText(Integer.toString(wisdom));
    }

    @FXML
    private void option1() {
        Test userTest = new Test(1, strength, isBad, betrayal, chanceOfLiving, control, experience, experienceNeeded, humane, righteousness, trustWorthiness, wisdom);
        if (goodSideTest == true || badSideTest == true) {
            if (goodSideTest == true) {
                goodSideTest = false;
                userTest.goodSideChoice();

            }
            if (badSideTest == true) {
                badSideTest = false;
                userTest.badSideChoice();
                badSidePointsTotal++;
                deathTotal++;
            }
            chanceOfLiving = userTest.getChanceOfLiving();
            chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
            control = userTest.getControl();
            controlLabel.setText(Integer.toString(control));
            experience = userTest.getExperience();
            experienceLabel.setText(Integer.toString(experience));
            experienceNeeded = userTest.getExperienceNeeded();
            experienceNeededLabel.setText(Integer.toString(experienceNeeded));
            humane = userTest.getHumane();
            humaneLabel.setText(Integer.toString(humane));
            righteousness = userTest.getRighteousness();
            righteousnessLabel.setText(Integer.toString(righteousness));
            strength = userTest.getStrength();
            strengthLabel.setText(Integer.toString(strength));
            trustWorthiness = userTest.getTrustWorthiness();
            trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
            wisdom = userTest.getWisdom();
            wisdomLabel.setText(Integer.toString(wisdom));

        }

        if (userGainingExperience == true) {
            userGainingExperience = false;
        }

        if (mission == true) {
            mission = false;
            if (isBad == true) {
                BadSide userMission = new BadSide(1, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected=2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeIncrease = crimeIncrease + 1;
                    badSidePointsTotal = badSidePointsTotal + 1;
                }
                if (mediumMission == true) {
                    daysAffected=4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeIncrease = crimeIncrease + 5;
                    badSidePointsTotal = badSidePointsTotal + 5;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeIncrease = crimeIncrease + 7;
                    badSidePointsTotal = badSidePointsTotal + 20;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            } else {
                GoodSide userMission = new GoodSide(1, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected = 2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeDecrease = crimeDecrease + 3;
                    goodSidePointsTotal = goodSidePointsTotal + 5;
                }
                if (mediumMission == true) {
                    daysAffected = 4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeDecrease = crimeDecrease + 1;
                    goodSidePointsTotal = goodSidePointsTotal + 1;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeDecrease = crimeDecrease + 7;
                    goodSidePointsTotal = goodSidePointsTotal + 20;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            }

        }

        scenarioLabel.setText("");

    }

    @FXML
    private void option2() {
        Test userTest = new Test(2, strength, isBad, betrayal, chanceOfLiving, control, experience, experienceNeeded, humane, righteousness, trustWorthiness, wisdom);
        if (goodSideTest == true || badSideTest == true) {
            if (goodSideTest == true) {
                goodSideTest = false;
                userTest.goodSideChoice();
            }
            if (badSideTest == true) {
                badSideTest = false;
                userTest.badSideChoice();
                deathTotal++;
            }
            chanceOfLiving = userTest.getChanceOfLiving();
            chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
            control = userTest.getControl();
            controlLabel.setText(Integer.toString(control));
            experience = userTest.getExperience();
            experienceLabel.setText(Integer.toString(experience));
            experienceNeeded = userTest.getExperienceNeeded();
            experienceNeededLabel.setText(Integer.toString(experienceNeeded));
            humane = userTest.getHumane();
            humaneLabel.setText(Integer.toString(humane));
            righteousness = userTest.getRighteousness();
            righteousnessLabel.setText(Integer.toString(righteousness));
            strength = userTest.getStrength();
            strengthLabel.setText(Integer.toString(strength));
            trustWorthiness = userTest.getTrustWorthiness();
            trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
            wisdom = userTest.getWisdom();
            wisdomLabel.setText(Integer.toString(wisdom));

        }

        if (userGainingExperience == true) {
            userGainingExperience = false;
        }

        if (mission == true) {
            mission = false;
            if (isBad == true) {
                BadSide userMission = new BadSide(2, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected = 2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeIncrease = crimeIncrease + 3;
                    badSidePointsTotal = badSidePointsTotal + 10;
                }
                if (mediumMission == true) {
                    daysAffected = 4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeIncrease = crimeIncrease + 2;
                    badSidePointsTotal = badSidePointsTotal + 5;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeIncrease = crimeIncrease + 5;
                    badSidePointsTotal = badSidePointsTotal + 10;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            } else {
                GoodSide userMission = new GoodSide(2, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected = 2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeDecrease = crimeDecrease + 1;
                    goodSidePointsTotal = goodSidePointsTotal + 1;
                }
                if (mediumMission == true) {
                    daysAffected = 4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeDecrease = crimeDecrease + 5;
                    goodSidePointsTotal = goodSidePointsTotal + 10;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeDecrease = crimeDecrease + 6;
                    goodSidePointsTotal = goodSidePointsTotal + 15;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            }

        }

        scenarioLabel.setText("");
    }

    @FXML
    private void option3() {
        Test userTest = new Test(3, strength, isBad, betrayal, chanceOfLiving, control, experience, experienceNeeded, humane, righteousness, trustWorthiness, wisdom);
        if (goodSideTest == true || badSideTest == true) {
            if (goodSideTest == true) {
                goodSideTest = false;
                userTest.goodSideChoice();
            }
            if (badSideTest == true) {
                badSideTest = false;
                userTest.badSideChoice();
                badSidePointsTotal++;
                deathTotal++;
            }
            chanceOfLiving = userTest.getChanceOfLiving();
            chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
            control = userTest.getControl();
            controlLabel.setText(Integer.toString(control));
            experience = userTest.getExperience();
            experienceLabel.setText(Integer.toString(experience));
            experienceNeeded = userTest.getExperienceNeeded();
            experienceNeededLabel.setText(Integer.toString(experienceNeeded));
            humane = userTest.getHumane();
            humaneLabel.setText(Integer.toString(humane));
            righteousness = userTest.getRighteousness();
            righteousnessLabel.setText(Integer.toString(righteousness));
            strength = userTest.getStrength();
            strengthLabel.setText(Integer.toString(strength));
            trustWorthiness = userTest.getTrustWorthiness();
            trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
            wisdom = userTest.getWisdom();
            wisdomLabel.setText(Integer.toString(wisdom));

        }

        if (userGainingExperience == true) {
            userGainingExperience = false;
        }

        if (mission == true) {
            mission = false;
            if (isBad == true) {
                BadSide userMission = new BadSide(3, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected = 2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeIncrease = crimeIncrease + 4;
                    badSidePointsTotal = badSidePointsTotal + 10;
                }
                if (mediumMission == true) {
                    daysAffected = 4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeIncrease = crimeIncrease + 6;
                    badSidePointsTotal = badSidePointsTotal + 10;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeIncrease = crimeIncrease + 6;
                    badSidePointsTotal = badSidePointsTotal + 15;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            } else {
                GoodSide userMission = new GoodSide(3, strength, chanceOfLiving, control, experience, humane, righteousness, trustWorthiness, wisdom);
                if (smallMission == true) {
                    daysAffected = 2;
                    smallMission = false;
                    userMission.choiceSmall();
                    crimeDecrease = crimeDecrease + 3;
                    goodSidePointsTotal = goodSidePointsTotal + 5;
                }
                if (mediumMission == true) {
                    daysAffected = 4;
                    mediumMission = false;
                    userMission.choiceMedium();
                    crimeDecrease = crimeDecrease + 4;
                    goodSidePointsTotal = goodSidePointsTotal + 10;
                }
                if (bigMission == true) {
                    daysAffected = 7;
                    bigMission = false;
                    userMission.choiceBig();
                    crimeDecrease = crimeDecrease + 6;
                    goodSidePointsTotal = goodSidePointsTotal + 15;
                }
                chanceOfLiving = userMission.getChanceOfLiving();
                chanceOfLivingLabel.setText(Integer.toString(chanceOfLiving));
                control = userMission.getControl();
                controlLabel.setText(Integer.toString(control));
                experience = userMission.getExperience();
                experienceLabel.setText(Integer.toString(experience));
                humane = userMission.getHumane();
                humaneLabel.setText(Integer.toString(humane));
                righteousness = userMission.getRighteousness();
                righteousnessLabel.setText(Integer.toString(righteousness));
                strength = userMission.getStrength();
                strengthLabel.setText(Integer.toString(strength));
                trustWorthiness = userMission.getTrustWorthiness();
                trustWorthinessLabel.setText(Integer.toString(trustWorthiness));
                wisdom = userMission.getWisdom();
                wisdomLabel.setText(Integer.toString(wisdom));
            }

        }

        scenarioLabel.setText("");
    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (startTime > 0 && year == false) {
                    int x = (int) (Math.floor(startTime / 1000000000));
                    int y = (int) (now / 1000000000);
                    int seconds = y - x;
                    double z = now - startTime;

                    if (seconds % 5 == 1 && count == 0) {
                        day++;
                        simulationDay++;
                        statsLabel.setText("Day's Statistics: " + simulationDay);
                        count2 = 0;
                        if (day == 366) {
                            day = 1;
                        }
                        if (crimeAffected == true) {
                            crimeAffectedDay++;
                        }
                        if (crimeAffectedDay > daysAffected) {
                            crimeAffected = false;
                            crimeIncrease = 0;
                            crimeDecrease = 0;
                            crimeAffectedDay = 0;
                            daysAffected=0;
                        }

                        count++;
                        Crimes crime = new Crimes(day, criminals, criminalPowerUser, criminalPowerStrength, cityPeople, goodPowerUser, goodPowerStrength, crimeIncrease, crimeDecrease);
                        crime.weather();
                        crime.randomEvents();
                        crime.crimeStats();
                        crimeOccured = crime.getCrimeAmount();
                        crimeOccuredTotal = crimeOccuredTotal + crimeOccured;
                        int crimeSolvedPolice = crime.getCrimeSolvedPolice();
                        crimeSolvedPoliceTotal = crimeSolvedPoliceTotal + crimeSolvedPolice;
                        int crimeSolvedGoodSide = crime.getCrimeSolvedAgency();
                        crimeSolvedGoodSideTotal = crimeSolvedGoodSideTotal + crimeSolvedGoodSide;
                        goodSidePointsTotal = goodSidePointsTotal + crime.getGoodSidePoints();
                        badSidePointsTotal = badSidePointsTotal + crime.getBadSidePoints();
                        int criminalsCaught = crime.getCriminalsCaught();
                        criminalsCaughtTotal = criminalsCaughtTotal + criminalsCaught;
                        int injured = crime.getInjuredAmount();
                        injuredTotal = injuredTotal + injured;
                        int death = crime.getDeathAmount();
                        deathTotal = deathTotal + death;
                        int badSideCrimeAmount = crime.getBadSideCrimes();
                        badSideCrimesTotal = badSideCrimesTotal + badSideCrimeAmount;
                        int supernaturalCrimes = crime.getSupernaturalCrimes();
                        supernaturalCrimesTotal = supernaturalCrimesTotal + supernaturalCrimes;
                        int deathCounter = 0;
                        //
                        for (int d = 0; d < cityPeople.size(); d++) {
                            if (cityPeople.get(d) == 0) {
                                deathCounter++;
                                cityPeople.remove(d);
                            }
                            if (deathCounter == death) {
                                break;
                            }
                        }

                        cityPeople = crime.getCityPeopleArray();
                        criminals = crime.getCriminalArray();
                        criminalPowerStrength = crime.getCriminalPowerStrengthArray();
                        criminalPowerUser = crime.getCriminalPowerUserArray();

                        crimeAmountLabel.setText(Integer.toString(crimeOccured));
                        crimeSolvedPoliceLabel.setText(Integer.toString(crimeSolvedPolice));
                        crimeSolvedGoodSideLabel.setText(Integer.toString(crimeSolvedGoodSide));
                        criminalsCaughtLabel.setText(Integer.toString(criminalsCaught));
                        injuryLabel.setText(Integer.toString(injured));
                        deathLabel.setText(Integer.toString(death));
                        badPointsLabel.setText(Integer.toString(badSidePointsTotal));
                        goodPointsLabel.setText(Integer.toString(goodSidePointsTotal));
                        populationLabel.setText(Integer.toString(cityPeople.size()));
                        badSideCrimesLabel.setText(Integer.toString(badSideCrimeAmount));
                        supernaturalCrimesLabel.setText(Integer.toString(supernaturalCrimes));
                        weatherEventsLabel.setText(crime.getWeatherEvents());
                        randomEventsLabel.setText(crime.getRandomEvents());

                    }
                    if (seconds % 5 > 1 && count2 == 0) {
                        int peopleMove;
                        crimeCounter = 0;
                        count = 0;

                        count2++;
                        if (crimeOccured > 40) {
                            peopleMove = 25;
                        } else if (crimeOccured < 30) {
                            peopleMove = 50;
                        } else {
                            peopleMove = 37;
                        }
                        for (int i = 0; i < peopleMove; i++) {
                            gender = (int) (Math.random() * 2) + 1;
                            age = (int) (Math.random() * 100) + 1;
                            status = (int) (Math.random() * 4) + 1;
                            family = (int) (Math.random() * 3) + 1;
                            Person people = new Person(gender, age, status, family);
                            people.criminal();
                            boolean hasPower = people.getPowers();
                            boolean isCriminal = people.getCriminal();
                            boolean isCriminalBadSide = people.getCriminalOrganization();
                            if (hasPower == true && isCriminal == true) {
                                criminals.add(1);//1 is criminal with power, 0 is crimnal without power
                                criminalPowerUser.add(1);
                                cityPeople.add(2);
                                int startingStrength = (int) (Math.random() * 25) + 1;
                                Powers power = new Powers(startingStrength);
                                int strength = power.getRandomPeoplePowers();
                                criminalPowerStrength.add(strength);
                            } else if (isCriminal == true) {
                                if (isCriminalBadSide == true) {
                                    criminals.add(2); //not a power suer but on bad side
                                } else {
                                    criminals.add(0);
                                }
                                cityPeople.add(1);
                            } else if (hasPower == true) {
                                goodPowerUser.add(1);
                                cityPeople.add(3);
                                int startingStrength = (int) (Math.random() * 25) + 1;
                                Powers power = new Powers(startingStrength);
                                int strength = power.getRandomPeoplePowers();
                                goodPowerStrength.add(strength);
                            } else {
                                cityPeople.add(0);
                            }
                        }

                        if (simulationDay == 365) {
                            year = true;
                            statsLabel.setText("Year's Statistics");
                            crimeAmountLabel.setText(Integer.toString(crimeOccuredTotal));
                            crimeSolvedPoliceLabel.setText(Integer.toString(crimeSolvedPoliceTotal));
                            crimeSolvedGoodSideLabel.setText(Integer.toString(crimeSolvedGoodSideTotal));
                            criminalsCaughtLabel.setText(Integer.toString(criminalsCaughtTotal));
                            injuryLabel.setText(Integer.toString(injuredTotal));
                            deathLabel.setText(Integer.toString(deathTotal));
                            badSideCrimesLabel.setText(Integer.toString(badSideCrimesTotal));
                            supernaturalCrimesLabel.setText(Integer.toString(supernaturalCrimesTotal));
                            weatherEventsLabel.setText("");
                            randomEventsLabel.setText("");

                            if (goodSidePointsTotal >= badSidePointsTotal) {
                                if (isBad == true) {
                                    winningSideLabel.setText("Your side was not successful in completing thier missions");
                                } else {
                                    winningSideLabel.setText("Your side won!!!! You were succesfully able to limit crime!");
                                }
                            } else {
                                if (isBad == true) {
                                    winningSideLabel.setText("Your side won!!!! You were succesfully able to make the bad side win!");
                                } else {
                                    winningSideLabel.setText("Your side was not successful in limiting crime");
                                }

                            }
                        }
                        
                    }

                }

            }

        }.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
