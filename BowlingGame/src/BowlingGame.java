import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a bowling game
 * @author Team DevMatch - Cofinpro AG
 */
public class BowlingGame {

    private final double PROBABILY_OF_KNOCKING_OUT_A_PIN;
    private ArrayList<Pin> pins;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to a new Bowling Game");
        BowlingGame bg = null;
        //while bg isn't set up, try setting it up
        while (bg == null) {
            System.out.println("*****************************");
            try {
                System.out.print("With how many pins do you want to play? (range 1-100, if instead, you want to provide the number of rows, write for example r6 for six rows): ");
                String input = scanner.nextLine();
                int pinCount;
                if (input.startsWith("r")) {
                    input = input.substring(1, input.length());
                    int inputNumber = Integer.parseInt(input);
                    pinCount = BowlingGame.getPinCountByRowCount(inputNumber);
                    System.out.println(inputNumber + " filled rows consist of " + pinCount + " pins.");
                } else {
                    pinCount = Integer.parseInt(input);
                }
                System.out.print("How high should the change of knocking out a pin be? (e.g. 0.5): ");
                input = scanner.nextLine();
                double inputProbability = Double.parseDouble(input);
                bg = new BowlingGame(pinCount, inputProbability);
            } catch (NumberFormatException nfe) {
                System.out.println("Your input is not in the right format. Numbers should be something like \"1234\" and probabilities should be something like \"0.5\".");
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage() + " Try again.");
            }
        }
        // play as long as there are standing pins
        boolean gameRunning = true;
        while (gameRunning) {
            bg.visualizeStandingPins();
            System.out.print("If you want to throw a ball, just press enter. If you want to end the game press x and then enter.");
            String input = scanner.nextLine();
            System.out.println("*****************************");
            if (input.equals("")) {
                System.out.println(bg.rollBall() + " pins knocked out by rolling one ball.");
                int standingPins = bg.countStandingPins();
                if (standingPins > 1) {
                    System.out.println("There are still " + standingPins + " pins standing.");
                } else if (standingPins == 1) {
                    System.out.println("There is still one pin standing. You are almost done.");
                } else {
                    System.out.println("All pins have been knocked out. You finished the game");
                    gameRunning = false;
                }
            } else if (input.equalsIgnoreCase("x")) {
                gameRunning = false;
            } else {
                System.out.println("Unrecogniseable input. Try again.");
            }
        }
        System.out.println("Good bye. Come back soon.");
    }
    /**
     * Constucts a BowlingGame with 10 pins
     */
    public BowlingGame() {
        this(10, 0.5);
    }

    /**
     *Constucts a BowlingGame with a custom amount of pins and custom probability of knocking out a pin
     * @param count defines the amount of pins (range 1 - 100)
     * @param probabiltyOfKnockingOutAPin the probability of a standing pin being knocked out by rolling a ball
     */
    public BowlingGame(int count, double probabiltyOfKnockingOutAPin) {
        if (count < 1 || count > 100) {
            throw new IllegalArgumentException("The number of pins should be between 1 and 100 but is " + count);
        }
        if (probabiltyOfKnockingOutAPin < 0 || probabiltyOfKnockingOutAPin > 1) {
            throw new IllegalArgumentException("The probability of knocking out a pin should be between 0.0 and 1.0 but is " + probabiltyOfKnockingOutAPin);
        }
        pins = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            pins.add(new Pin());
        }
        PROBABILY_OF_KNOCKING_OUT_A_PIN = probabiltyOfKnockingOutAPin;
    }

    /**
     *resets the game by putting all pins back up
     */
    public void reset() {
        for(int i = 0; i < pins.size(); i++) {
            pins.get(i).putPinBackUp();
        }
    }

    /**
     * knocks out a random number of pins
     * @return number of knocked out pins
     */
    public int rollBall() {
        int knockedOutBallCount = 0;
        Random r = new Random();
        for(int i = 0; i < pins.size(); i++) {
            if (pins.get(i).getPinState() == PinState.KNOCKEDOUT) {
                continue;
            }
            double randomValue = r.nextDouble();
            if (randomValue <= PROBABILY_OF_KNOCKING_OUT_A_PIN) {
                pins.get(i).knockOut();
                knockedOutBallCount++;
            }
        }
        return knockedOutBallCount;
    }

    /**
     * counts standing pins
     * @return amount of standing pins
     */
    public int countStandingPins() {
        int count = 0;
        for(int i = 0; i < pins.size(); i++) {
            if (pins.get(i).getPinState() == PinState.STANDING) {
                count++;
            }
        }
        return count;
    }

    /**
     * prints out a visualization of the BowlingGame
     */
    public void visualizeStandingPins() {
        int pinIndex = 0;
        int currentRow = 1;
        int pinsDisplayedInCurrentRow = 0;
        int fullRowCount = getRowCount();
        while (pinIndex < pins.size()) {
            for(int i = currentRow; i < fullRowCount; i++) {
                System.out.print(" ");
            }
            while (pinsDisplayedInCurrentRow < currentRow && pins.size() > pinIndex) {
                display(pins.get(pinIndex));
                pinsDisplayedInCurrentRow++;
                pinIndex++;
            }
            System.out.println();
            currentRow++;
            pinsDisplayedInCurrentRow = 0;
        }
    }

    private void display(Pin pin) {
        if (pin.getPinState() == PinState.STANDING) {
            System.out.print("0 ");
        } else {
            System.out.print("8 ");
        }
    }

    /**
     *
     * Calculates the number of rows that used to display all pins
     * @return the number of rows
     */
    public int getRowCount() {
        int rowCount = 0;
        for (int i = 0; i < pins.size(); ) {
            rowCount++;
            i += rowCount;
        }
        return rowCount;
    }

    /**
     * Calcultes the number of pins that fit into a certein amount of rows that are build in the typical bowling structure
     * @param rowCount the number of rows
     * @return the number of pins that fit into the given rows
     */
    public static int getPinCountByRowCount(int rowCount) {
        return (rowCount * (rowCount +1))/2;
    }
}
