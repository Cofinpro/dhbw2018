package BowlingSpiel;

import java.util.Scanner;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

/**
 * Represents a bowling game
 * @author Team DevMatch - Cofinpro AG
 */
public class BowlingGame {

    static Scanner reader = new Scanner(System.in);


    private ArrayList < Pin > pinsArrayList;
    static int amount = 10;
    static String[] invalidArgumentOutput = {
            "invalid input"
    };
    double calulateRows = -0.5 + Math.sqrt(0.25 + 2 * amount);


    public static void main(String[] args) {
        System.out.println("Wollen sie werfen ? [y] für ja [n] für nein");
        String input = reader.nextLine();
        BowlingGame game = new BowlingGame(amount);

        String again = "y";

        do {
            if (input.equals(again)) {

                game.visualizeStandingPins(amount);
                System.out.println("");

                System.out.println("Es wurden " + game.rollBall() + " Pins umgeworfen");
                System.out.println("Es stehen noch " + game.countStandingPins() + " Pins");
                System.out.println("");
                game.visualizeStandingPins(amount);
                System.out.println("----------------------------------------------------");
                System.out.println("Wenn du einen bestimmten Pin umwerfen möchtest y drücken ansonsten n");
                game.knockSpecific();
                game.visualizeStandingPins(amount);
                System.out.println("Wenn du einen bestimmten aufstellen möchtest y drücken ansonsten n");
                game.putSpecificPinBackUp();
                game.visualizeStandingPins(amount);

                if(game.countStandingPins() == 0){
                    System.out.println("");
                    System.out.println("Es wurden alle Pins umgeworfen, Spiel neu starten !");
                    break;
                }

                System.out.println("");
                System.out.println("Willst du nochmal werfen ?");
                System.out.println("");


                input = reader.nextLine();

            } else if (input.equals("n")) {
                System.out.println("Schönen Tag noch !");
            } else {
                System.out.println("Bitte gültige Eingabe");
                input = reader.nextLine();
            }

        } while (input.equals(again));
        if (input.equals(again)!=false){
            System.out.println("Das wars !");
        }
    }
    /**
     * Constucts a BowlingGame with 10 pins
     */
    public BowlingGame() {
        this(10);
    }
    /**
     *Constucts a BowlingGame with a custom amount of pins
     * @param count defines the amount of pins
     */
    public BowlingGame(int count) {
        if (amount < 0 || amount > 10) {
            throw new IllegalArgumentException(); //InvalidArgumentException funktioniert nicht Fehlermeldung must be caught
        }

        pinsArrayList = new ArrayList < > (count);
        for (int i = 0; i < count; i++) {
            pinsArrayList.add(new Pin());
        }
    }
    /**
     *resets the game by putting all pins back up
     */
    public void reset() {
        for (int i = 0; i < pinsArrayList.size(); i++) {
            pinsArrayList.get(i).putPinBackUp();
        }
    }
    /**
     * knocks out a random number of pins
     * @return number of knocked out pins
     */
    public int rollBall() {
        int temp;
        int countForRandom = 0;
        for (int i = 0; i < pinsArrayList.size(); i++) {
            temp = (int)(Math.random() * 2);
            if (pinsArrayList.get(i).getPinState() == PinState.STANDING) {
                if (temp >= 1) {
                    pinsArrayList.get(i).knockOut();
                    countForRandom++;
                }
            }
        }
        return countForRandom;
    }

    /**
     * counts standing pins
     * @return amount of standing pins
     */
    public int countStandingPins() {
        int countStanding = 0;
        for (int i = 0; i < pinsArrayList.size(); i++) {
            if (pinsArrayList.get(i).getPinState() == PinState.STANDING) {
                countStanding++;
            }
        }
        return countStanding;
    }

    public void knockDownEverything() {
        for (int i = 0; i < pinsArrayList.size(); i++) {
            pinsArrayList.get(i).knockOut();
        }
    }
    /**
     * prints out a visualization of the BowlingGame
     */
    public void visualizeStandingPins(int amount) {
        int pinCount = 0;
        for (int i = 0; i < calulateRows; i++) {
            for (int j = 0; j < (calulateRows - i); j++) {
                System.out.print(" ");
            }
            for (int x = 1; x <= i + 1; x++) {
                if (pinsArrayList.get(pinCount).getPinState() == PinState.STANDING) {
                    System.out.print("1 ");
                }
                if (pinsArrayList.get(pinCount).getPinState() == PinState.KNOCKEDOUT) {
                    System.out.print("0 ");
                }
                pinCount++;
            }
            System.out.println();
        }
    }
    public void knockSpecific() {
        Scanner wantToKnock = new Scanner(System.in);
        Scanner pinNumber = new Scanner(System.in);
        int number;

        String answerForKnock = wantToKnock.nextLine();
        if (answerForKnock.equals("y")) {
            System.out.println("Welchen Pin möchtest du umwerfen ? Zahl zwischen 1 und 10 wirft den angegebenen Pin um");
            number = pinNumber.nextInt();
            if(number < 1 || number > 10){
                System.out.println("Bitte beim nächsten mal einen Wert zwischen 1 und 10 eingeben");
                throw new IllegalArgumentException();
            }

            if (pinsArrayList.get(number - 1).getPinState() == PinState.STANDING) {
                pinsArrayList.get(number - 1).knockOut();
            } else {
                System.out.println("Pin liegt bereits");
            }
        } else if (answerForKnock.equals("n")) {
            System.out.println("Dann halt nicht!");
        } else {
            System.out.println("ungültige Eingabe");
        }
    }
    public void putSpecificPinBackUp() {
        Scanner wantToPutBackUp = new Scanner(System.in);
        Scanner pinNumber = new Scanner(System.in);
        int number;

        String answerForPutBackUp = wantToPutBackUp.nextLine();
        if (answerForPutBackUp.equals("y")) {
            System.out.println("Welchen Pin möchtest du aufstellen ? Zahl zwischen 1 und 10 stellt den angegebenen Pin auf");
            number = pinNumber.nextInt();
            if(number < 1 || number > 10){
                System.out.println("Bitte beim nächsten mal einen Wert zwischen 1 und 10 eingeben");
                throw new IllegalArgumentException();
            }

            if (pinsArrayList.get(number - 1).getPinState() == PinState.KNOCKEDOUT) {
                pinsArrayList.get(number - 1).putPinBackUp();
            } else {
                System.out.println("Pin steht bereits");
            }
        } else if (answerForPutBackUp.equals("n")) {
            System.out.println("Dann halt nicht !");
        } else {
            System.out.println("ungültige Eingabe");
        }
    }
}