/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.simulation.project;

import java.util.ArrayList;

/**
 *
 * @author Sania Khaja
 */
public class Crimes {

    int crimeSeverity = 0;
    int crimeAmount = (int) (Math.random() * ((35 - 30) + 1)) + 30;
    int crimeSolvedPolice = 0;
    int crimeSolvedGoodSide = 0;
    int day;
    int chancePrecipitaion;
    int temperature;
    int bigEvents;
    int informationLeak;
    int weaponsImported;
    int informationRight;
    int weatherSeverity;
    int death = 1;
    int crimeType;
    int injured = 2;
    int badPoints;
    int goodPoints;
    int criminalsCaught;
    int supernaturalCriminals;
    int badSideCrimes;
    boolean winter = false;
    boolean spring = false;
    boolean summer = false;
    boolean fall = false;
    boolean precipitation = false;
    boolean severeWeather = false;
    boolean holiday = false;
    boolean highTemperature = false;
    boolean lowTemperature = false;
    boolean rightInfo = false;
    boolean bigEvent = false;
    boolean weapons = false;
    String whichHoliday;
    int powerUserStopsCrime;
    boolean powerUserStopCrime = false;
    ArrayList<Integer> criminalsArray = new ArrayList<>();
    ArrayList<Integer> criminalPowerUserArray = new ArrayList<>();
    ArrayList<Integer> criminalPowerStrengthArray = new ArrayList<>();
    ArrayList<Integer> cityPeople = new ArrayList<>();
    ArrayList<Integer> goodPowerUserArray = new ArrayList<>();
    ArrayList<Integer> goodPowerStrengthArray = new ArrayList<>();
    private int crimeIncrease;
    private int crimeDecrease;

    public Crimes(int theDay, ArrayList<Integer> criminalArray, ArrayList<Integer> criminalPower, ArrayList<Integer> criminalPowerStrength, ArrayList<Integer> city, ArrayList<Integer> goodPowerUser, ArrayList<Integer> goodPowerStrength, int crimeInc, int crimeDec) {
        day = theDay;
        criminalsArray = criminalArray;
        criminalPowerUserArray = criminalPower;
        criminalPowerStrengthArray = criminalPowerStrength;
        cityPeople = city;
        goodPowerUserArray = goodPowerUser;
        goodPowerStrengthArray = goodPowerStrength;
        crimeIncrease=crimeInc;
        crimeDecrease=crimeDec;
        crimeAmount=crimeAmount+crimeIncrease-crimeDecrease;
    }

    public void weather() {
        chancePrecipitaion = (int) (Math.random() * 30) + 1;
        weatherSeverity = (int) (Math.random() * 15) + 1;

        if (day < 59 || day >= 334) {//winter
            crimeAmount = crimeAmount - (int) (crimeAmount * 0.2);
            winter = true;
            temperature = (int) (Math.random() * ((40 - 0) + 1)) + 0;
            if (chancePrecipitaion < 5) {
                precipitation = true;
                if (weatherSeverity == 1) {
                    severeWeather = true;
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.5);
                } else {
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.25);
                }
            }
            if (temperature < 30) {
                lowTemperature = true;
                crimeAmount = crimeAmount - (int) (crimeAmount * 0.1);
            }

        }
        if (day >= 59 && day < 151) {//spring
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.1);
            spring = true;
            temperature = (int) (Math.random() * ((80 - 40) + 1)) + 40;
            if (chancePrecipitaion < 10) {
                precipitation = true;
                if (weatherSeverity == 1) {
                    severeWeather = true;
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.50);
                } else {
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.25);
                }
            }
            if (temperature > 70) {
                highTemperature = true;
                crimeAmount = crimeAmount + (int) (crimeAmount * 0.2);
            }
        }
        if (day >= 151 && day < 242) {//summer
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.20);
            summer = true;
            temperature = (int) (Math.random() * ((100 - 70) + 1)) + 70;
            if (chancePrecipitaion < 8) {
                precipitation = true;
                if (weatherSeverity == 1) {
                    severeWeather = true;
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.45);
                } else {
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.3);
                }
            }
            if (temperature >= 80) {
                highTemperature = true;
                crimeSeverity = crimeSeverity + 15;
                crimeAmount = crimeAmount + (int) (crimeAmount * 0.5);
            }
        }
        if (day <= 333 && day >= 242) {//fall
            crimeAmount = crimeAmount - (int) (crimeAmount * 0.1);
            fall = true;
            temperature = (int) (Math.random() * ((70 - 30) + 1)) + 30;
            if (chancePrecipitaion < 7) {
                precipitation = true;
                if (weatherSeverity == 1) {
                    severeWeather = true;
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.4);
                } else {
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.2);
                }

            }
            if (temperature <= 40) {
                lowTemperature = true;
                crimeAmount = crimeAmount - (int) (crimeAmount * 0.25);
            }
            if (temperature > 50) {
                crimeAmount = crimeAmount + (int) (crimeAmount * 0.15);
            }
        }

    }

    public void randomEvents() {
        bigEvents = (int) (Math.random() * 15) + 1;
        informationLeak = (int) (Math.random() * 5) + 1;
        weaponsImported = (int) (Math.random() * 15) + 1;
        powerUserStopsCrime = (int) (Math.random() * 5) + 1;
        int badSidePowerUser = (int) (Math.random() * 15) + 1;

        if (badSidePowerUser == 1) {
            badPoints = badPoints + 15;
        }

        if (informationLeak == 1) {
            informationRight = (int) (Math.random() * 2) + 1;
            int criminalCaught = (int) (Math.random() * 2) + 1;
            if (informationRight == 1) { //information is correct
                if (criminalCaught == 1) {
                    rightInfo = true;
                    crimeAmount = crimeAmount - (int) (crimeAmount * 0.2);
                    int criminalType = criminalsArray.get((int) (Math.random() * criminalsArray.size() - 1));
                    if (criminalType == 1) {
                        crimeSolvedGoodSide++;
                        goodPoints = goodPoints + 10;
                    } else {
                        crimeSolvedPolice++;
                    }
                    criminalsCaught++;
                    for (int j = 0; j < criminalsArray.size(); j++) {
                        if (criminalsArray.get(j) == criminalType) {
                            criminalsArray.remove(j);
                            break;
                        }
                    }
                }
            } else {
                crimeAmount++;
            }
        }

        if (powerUserStopsCrime == 1) {
            powerUserStopCrime = true;
            int powerUserStrength = goodPowerStrengthArray.get((int) (Math.random() * goodPowerStrengthArray.size() - 1));
            int criminalType = criminalsArray.get((int) (Math.random() * criminalsArray.size() - 1));
            if (powerUserStrength < 50) {
                crimeAmount--;
                if (criminalType == 1) {
                    int criminalPowerUser = (int) (Math.random() * criminalPowerUserArray.size() - 1);
                    int criminalStrength = criminalPowerStrengthArray.get(criminalPowerUser);
                    if (criminalStrength < powerUserStrength) {
                        criminalsCaught++;
                        for (int j = 0; j < criminalsArray.size(); j++) {
                            if (criminalsArray.get(j) == 1) {
                                criminalsArray.remove(j);
                                for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                    if (criminalPowerStrengthArray.get(z) == criminalStrength) {
                                        criminalPowerUserArray.remove(z);
                                        criminalPowerStrengthArray.remove(z);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                } else if (criminalType == 2) {
                    crimeAmount--;
                    int caught = (int) (Math.random() * 10) + 1;
                    int badSideDeathChance = (int) (Math.random() * 2) + 1;
                    if (caught == 1) {
                        criminalsCaught++;
                        for (int j = 0; j < criminalsArray.size(); j++) {
                            if (criminalsArray.get(j) == 2) {
                                criminalsArray.remove(j);
                                if (badSideDeathChance == 1) {
                                    //remove criminal from city
                                    for (int z = 0; z < cityPeople.size(); z++) {
                                        if (cityPeople.get(z) == 2) {
                                            cityPeople.remove(z);
                                            death++;
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }

                    }

                } else {
                    crimeAmount--;
                    int caught = (int) (Math.random() * 2) + 1;
                    if (caught == 1) {
                        criminalsCaught++;
                        for (int j = 0; j < criminalsArray.size(); j++) {
                            if (criminalsArray.get(j) == 0) {
                                criminalsArray.remove(j);
                                break;
                            }
                        }
                    }

                }
            }
            if (powerUserStrength > 50) {
                crimeAmount--;
                if (criminalType == 1) {
                    int criminalPowerUser = (int) (Math.random() * criminalPowerUserArray.size() - 1);
                    int criminalStrength = criminalPowerStrengthArray.get(criminalPowerUser);
                    if (criminalStrength < powerUserStrength) {
                        criminalsCaught++;
                        for (int j = 0; j < criminalsArray.size(); j++) {
                            if (criminalsArray.get(j) == 1) {
                                criminalsArray.remove(j);
                                for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                    if (criminalPowerStrengthArray.get(z) == criminalStrength) {
                                        criminalPowerUserArray.remove(z);
                                        criminalPowerStrengthArray.remove(z);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                } else if (criminalType == 2) {
                    crimeAmount--;
                    int caught = (int) (Math.random() * 5) + 1;
                    int badSideDeathChance = (int) (Math.random() * 2) + 1;
                    if (caught == 1) {
                        criminalsCaught++;
                        for (int j = 0; j < criminalsArray.size(); j++) {
                            if (criminalsArray.get(j) == 2) {
                                criminalsArray.remove(j);
                                if (badSideDeathChance == 1) {
                                    //remove criminal from city
                                    for (int z = 0; z < cityPeople.size(); z++) {
                                        if (cityPeople.get(z) == 2) {
                                            cityPeople.remove(z);
                                            death++;
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }

                    }

                } else {
                    crimeAmount--;
                    criminalsCaught++;
                    for (int j = 0; j < criminalsArray.size(); j++) {
                        if (criminalsArray.get(j) == 0) {
                            criminalsArray.remove(j);
                            break;
                        }
                    }

                }
            }
        }

        if (bigEvents == 1) {
            bigEvent = true;
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.3);
        }
        if (weaponsImported == 1) {
            weapons = true;
            crimeSeverity = crimeSeverity + 10;
            death = death + 2;
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.05);
        }
        if (day < 3 || day > 355) {//Christams and New Years
            holiday = true;
            whichHoliday = "Christmas and New Years time ";
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.5);
        }
        if (day == 147) {//memorial day
            holiday = true;
            whichHoliday = "Memorial Day ";
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.25);
        }
        if (day == 245) {//labor day
            holiday = true;
            whichHoliday = "Labor Day ";
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.25);
        }
        if (day > 325 && day < 338) {//thanksgiving
            holiday = true;
            whichHoliday = "Thanksgiving Time and Black Friday ";
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.40);
        }
        if (day == 185) {//july 4th
            holiday = true;
            whichHoliday = "Independance Day ";
            crimeAmount = crimeAmount + (int) (crimeAmount * 0.25);
        }

    }


    //use crime severity that affects a random number on if more or less people will die.
    public void crimeStats() {
        for (int i = 0; i <= crimeAmount; i++) {
            crimeType = (int) (Math.random() * 100) + 1;
            int criminalType = criminalsArray.get((int) (Math.random() * criminalsArray.size() - 1));
            if (criminalType == 1) {
                crimeSeverity = crimeSeverity + 10;
                //good side will solve
            }

            if (crimeType > 95) { //higher chance of being caught because it is a bigger crime. done in public
                if (crimeSeverity == 10) {
                    death = death + (int) (death * 0.6);
                    injured = injured + (int) (injured * 0.4);
                } else if (crimeSeverity == 15) {
                    death = death + (int) (death * 0.65);
                    injured = injured + (int) (injured * 0.45);
                } else if (crimeSeverity > 15) {
                    death = death + (int) (death * 0.75);
                    injured = injured + (int) (injured * 0.55);
                } else {
                    death = death + 1;
                    injured = injured + 1;
                }
                if (criminalType == 1) {
                    supernaturalCriminals++;
                    int criminalPowerUser = (int) (Math.random() * criminalPowerUserArray.size() - 1);
                    int criminalStrength = criminalPowerStrengthArray.get(criminalPowerUser);
                    int caught = (int) (Math.random() * 3) + 1;

                    if (criminalStrength < 50) {
                        int solvingCrimeChance = (int) (Math.random() * 2) + 1;

                        if (solvingCrimeChance == 1) {
                            crimeSolvedGoodSide++;
                            goodPoints = goodPoints + 5;
                            if (caught > 1) {
                                goodPoints = goodPoints + 10;
                                criminalsCaught++;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) < 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }

                        }
                    }
                    if (criminalStrength >= 50) {
                        int solvingCrimeChance = (int) (Math.random() * 3) + 1;

                        if (solvingCrimeChance == 1) {
                            crimeSolvedGoodSide++;
                            goodPoints = goodPoints + 5;
                            if (caught == 1) {
                                criminalsCaught++;
                                goodPoints = goodPoints + 10;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) >= 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    //good side will solve
                } else if (criminalType == 2) {
                    badPoints++;
                    badSideCrimes++;
                    int solvingCrimeChance = (int) (Math.random() * 10) + 1;
                    int badSideDeathChance = (int) (Math.random() * 2) + 1;
                    int caught = (int) (Math.random() * 15) + 1;

                    if (solvingCrimeChance == 10) {
                        crimeSolvedPolice++;
                        if (caught == 10) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 2) {
                                    criminalsArray.remove(j);
                                    if (badSideDeathChance == 1) {
                                        //remove criminal from city
                                        for (int z = 0; z < cityPeople.size(); z++) {
                                            if (cityPeople.get(z) == 2) {
                                                cityPeople.remove(z);
                                                death++;
                                                break;
                                            }
                                        }

                                    }
                                    break;
                                }
                            }

                        }
                    }

                    //less chance of being caught since in bad side
                    //higher chance of dying, if unsucessful in completing mission for criminal
                } else {
                    int solvingCrimeChance = (int) (Math.random() * 2) + 1;
                    int caught = (int) (Math.random() * 3) + 1;

                    if (solvingCrimeChance == 1) {
                        crimeSolvedPolice++;
                        if (caught == 1) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 0) {
                                    criminalsArray.remove(j);
                                    break;
                                }
                            }
                        }
                    }

                }

            } else if (crimeType > 50 && crimeType <= 95) { //lower solving rate. 
                int deathChance = (int) (Math.random() * 10) + 1;
                if (deathChance == 10) {
                    if (crimeSeverity == 10) {
                        death = death + (int) (death * 0.45);
                        injured = injured + (int) (injured * 0.3);
                    } else if (crimeSeverity == 15) {
                        death = death + (int) (death * 0.55);
                        injured = injured + (int) (injured * 0.35);
                    } else {
                        death = death + (int) (death * 0.65);
                        injured = injured + (int) (injured * 0.45);
                    }

                }
                ///////////
                ///////////change chances
                if (criminalType == 1) {
                    supernaturalCriminals++;
                    int criminalPowerUser = (int) (Math.random() * criminalPowerUserArray.size() - 1);
                    int criminalStrength = criminalPowerStrengthArray.get(criminalPowerUser);
                    int caught = (int) (Math.random() * 3) + 1;

                    if (criminalStrength < 50) {//less smart
                        int solvingCrimeChance = (int) (Math.random() * 5) + 1;

                        if (solvingCrimeChance == 5) {
                            crimeSolvedGoodSide++;
                            goodPoints = goodPoints + 5;
                            if (caught > 1) {
                                criminalsCaught++;
                                goodPoints = goodPoints + 10;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) < 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (criminalStrength >= 50) {
                        int solvingCrimeChance = (int) (Math.random() * 8) + 1;

                        if (solvingCrimeChance > 3) {
                            crimeSolvedGoodSide++;
                            goodPoints = goodPoints + 5;
                            if (caught == 1) {
                                criminalsCaught++;
                                goodPoints = goodPoints + 10;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) >= 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    //good side will solve
                } else if (criminalType == 2) {
                    badPoints++;
                    badSideCrimes++;
                    int solvingCrimeChance = (int) (Math.random() * 15) + 1;
                    int badSideDeathChance = (int) (Math.random() * 25) + 1;
                    int caught = (int) (Math.random() * 15) + 1;

                    if (solvingCrimeChance == 15) {
                        crimeSolvedPolice++;
                        if (caught == 15) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 2) {
                                    criminalsArray.remove(j);
                                    if (badSideDeathChance == 1) {
                                        for (int z = 0; z < cityPeople.size(); z++) {
                                            if (cityPeople.get(z) == 2) {
                                                cityPeople.remove(z);
                                                death++;
                                                break;
                                            }
                                        }
                                        //remove criminal from city
                                    }
                                    break;
                                }
                            }

                        }
                    }

                    //less chance of being caught since in bad side
                    //higher chance of dying, if unsucessful in completing mission for criminal
                } else {
                    int solvingCrimeChance = (int) (Math.random() * 5) + 1;
                    int caught = (int) (Math.random() * 5) + 1;

                    if (solvingCrimeChance == 5) {
                        crimeSolvedPolice++;
                        if (caught == 5) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 0) {
                                    criminalsArray.remove(j);
                                    break;
                                }
                            }
                        }
                    }

                }

            } else {
                int deathChance = (int) (Math.random() * 50) + 1;
                if (deathChance == 10) {
                    if (crimeSeverity == 10) {
                        death = death + (int) (death * 0.35);
                        injured = injured + (int) (injured * 0.15);
                    } else if (crimeSeverity == 15) {
                        death = death + (int) (death * 0.45);
                        injured = injured + (int) (injured * 0.25);
                    } else {
                        death = death + (int) (death * 0.55);
                        injured = injured + (int) (injured * 0.35);
                    }

                }
                
                if (criminalType == 1) {
                    supernaturalCriminals++;
                    int criminalPowerUser = (int) (Math.random() * criminalPowerUserArray.size() - 1);
                    int criminalStrength = criminalPowerStrengthArray.get(criminalPowerUser);
                    int caught = (int) (Math.random() * 3) + 1;

                    if (criminalStrength < 50) {
                        int solvingCrimeChance = (int) (Math.random() * 5) + 1;

                        if (solvingCrimeChance == 5) {
                            crimeSolvedGoodSide++;
                            goodPoints = goodPoints + 5;
                            if (caught > 1) {
                                criminalsCaught++;
                                goodPoints = goodPoints + 5;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) < 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (criminalStrength >= 50) {
                        int solvingCrimeChance = (int) (Math.random() * 10) + 1;

                        if (solvingCrimeChance == 10) {
                            crimeSolvedGoodSide++;
                            if (caught == 1) {
                                criminalsCaught++;
                                for (int j = 0; j < criminalsArray.size(); j++) {
                                    if (criminalsArray.get(j) == 1) {
                                        criminalsArray.remove(j);
                                        for (int z = 0; z < criminalPowerUserArray.size(); z++) {
                                            if (criminalPowerUserArray.get(z) >= 50) {
                                                criminalPowerUserArray.remove(z);
                                                criminalPowerStrengthArray.remove(z);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    //good side will solve
                } else if (criminalType == 2) {
                    badPoints++;
                    badSideCrimes++;
                    int solvingCrimeChance = (int) (Math.random() * 20) + 1;
                    int badSideDeathChance = (int) (Math.random() * 2) + 1;
                    int caught = (int) (Math.random() * 20) + 1;

                    if (solvingCrimeChance == 20) {
                        crimeSolvedPolice++;
                        if (caught == 20) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 2) {
                                    criminalsArray.remove(j);
                                    if (badSideDeathChance > 10) {
                                        for (int z = 0; z < cityPeople.size(); z++) {
                                            if (cityPeople.get(z) == 2) {
                                                cityPeople.remove(z);
                                                death++;
                                                break;
                                            }
                                        }
                                        //remove criminal from city
                                    }
                                }
                                break;
                            }

                        }
                    }

                    //less chance of being caught since in bad side
                    //higher chance of dying, if unsucessful in completing mission for criminal
                } else {
                    int solvingCrimeChance = (int) (Math.random() * 7) + 1;
                    int caught = (int) (Math.random() * 7) + 1;

                    if (solvingCrimeChance == 7) {
                        crimeSolvedPolice++;
                        if (caught == 1) {
                            criminalsCaught++;
                            for (int j = 0; j < criminalsArray.size(); j++) {
                                if (criminalsArray.get(j) == 0) {
                                    criminalsArray.remove(j);
                                    break;
                                }
                            }
                        }
                    }

                }
            }

        }
    }

    public int getCrimeAmount() {
        return crimeAmount;
    }

    public int getCrimeSolvedPolice() {
        return crimeSolvedPolice;
    }

    public int getCrimeSolvedAgency() {
        return crimeSolvedGoodSide;
    }

    public int getCriminalsCaught() {
        return criminalsCaught;
    }

    public int getGoodSidePoints() {
        return goodPoints;
    }

    public int getBadSidePoints() {
        return badPoints;
    }

    public int getDeathAmount() {
        return death;
    }

    public int getInjuredAmount() {
        return injured;
    }

    public ArrayList<Integer> getCriminalArray() {
        return criminalsArray;
    }

    public ArrayList<Integer> getCriminalPowerUserArray() {
        return criminalPowerUserArray;
    }

    public ArrayList<Integer> getCriminalPowerStrengthArray() {
        return criminalPowerStrengthArray;
    }

    public ArrayList<Integer> getCityPeopleArray() {
        return cityPeople;
    }

    public int getBadSideCrimes() {
        return badSideCrimes;
    }

    public int getSupernaturalCrimes() {
        return supernaturalCriminals;
    }

    public String getWeatherEvents() {
        if (winter == true) {
            if (lowTemperature == true && precipitation == true) {
                if (severeWeather == true) {
                    return "It is winter, really cold and snowing heavily so crime decreased.";
                } else {
                    return "It is winter, really cold and snowing so crime decreased.";
                }

            } else if (lowTemperature == true && precipitation == false) {
                return "It is winter and really cold so crime decreased.";
            } else {
                return "It is winter so crime decreased.";
            }

        } else if (spring == true) {
            if (highTemperature == true && precipitation == true) {
                if (severeWeather == true) {
                    return "It is spring, raining heavily and hot so crime increased.";
                } else {
                    return "It is spring, raining and hot so crime increased.";
                }

            } else if (highTemperature == true && precipitation == false) {
                return "It is spring and hot so crime increased.";
            } else {
                return "It is spring so crime increased.";
            }

        } else if (summer == true) {
            if (highTemperature == true && precipitation == true) {
                if (severeWeather == true) {
                    return "It is summer, raining heavily and really hot so crime increased";
                } else {
                    return "It is summer, raining and really hot so crime increased";
                }

            } else if (highTemperature == true && precipitation == false) {
                return "It is summer and really hot so crime increased";
            } else {
                return "It is summer so crime increased";
            }

        } else {
            if (lowTemperature == true && precipitation == true) {
                if (severeWeather == true) {
                    return "It is fall, cold and raining heavily so crime decreased.";
                } else {
                    return "It is fall, cold and raining so crime decreased.";
                }

            } else if (lowTemperature == true && precipitation == false) {
                return "It is fall and cold so crime decreased.";
            } else {
                return "It is fall so crime decreased.";
            }

        }
    }

    public String getRandomEvents() {
        String holidayCrime = "";
        String rightInfoCrime = "";
        String weaponsCrime = "";
        String bigEventCrime = "";
        String powerStopCrime = "";

        if (holiday == true) {
            holidayCrime = "It is " + whichHoliday + "so crime increased.\n";
        }
        if (rightInfo == true) {
            rightInfoCrime = "Their was an information leak and the criminal was caught, so crime decreased.\n";
        }
        if (weapons == true) {
            weaponsCrime = "Weapons were imported, so crime and death rate increased.\n";
        }
        if (bigEvent == true) {
            bigEventCrime = "Their is a big event, so crime increased.\n";
        }
        if (powerUserStopCrime == true) {
            powerStopCrime = "A power user stopped a crime from occuring.\n";
        }

        String randomEvents = holidayCrime + rightInfoCrime + weaponsCrime + bigEventCrime + powerStopCrime;
        return randomEvents;
    }

//    public int getCrimeSolvedPolice(){
//        
//    }
}
