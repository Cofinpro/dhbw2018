import java.util.Scanner;

/**
 * Represents a bowling game
 * @author Team DevMatch - Cofinpro AG
 */
public class BowlingGame {

    private Pin[] pinArray;
    /**
     * Constucts a BowlingGame with 10 pins
     */
    public BowlingGame(){
        this(10);
    }

    /**
     *Constucts a BowlingGame with a custom amount of pins
     * @param count defines the amount of pins
     */
    public BowlingGame(int count){
        pinArray = new Pin[count];
        for(int amount=0; amount<count; amount++) {
            pinArray[amount] = new Pin();
        }
    }

    /**
     *resets the game by putting all pins back up
     */
    public void reset(){
        for(int count=0; count<pinArray.length; count++){
            pinArray[count].putPinBackUp();
        }
    }

    /**
     * knocks out a random number of pins
     * @return number of knocked out pins
     */
    public String rollBall(int probabilityForKnockOut){
        int knockedOutPinsByCurrentBall = 0;
        for(int count=0; count<pinArray.length; count++) {
            if ((knockDownPin(probabilityForKnockOut)) && pinArray[count].pinState == PinState.STANDING) {
                pinArray[count].knockOut();
                knockedOutPinsByCurrentBall++;
            }
        }
        return "" + knockedOutPinsByCurrentBall + " pin/s were knocked out by the current ball";
    }

    /**
     * Calculates weather a pin will be knocked down dependent on the probability
     * @param probabilityForKnockOut the given probability for a knockout
     * @return boolean weather the pin will be knocked down
     */
    public boolean knockDownPin(int probabilityForKnockOut){
        double random = Math.random();
        double probability = probabilityForKnockOut/100.0;
        if(random<probability)
            return true;
        else
            return false;
    }

    /**
     * counts standing pins
     * @return amount of standing pins
     */

    public String countStandingPins(){
        int standingPins = 0;
        for(int count=0; count<pinArray.length; count++){
            if(pinArray[count].getPinState() == PinState.STANDING)
                standingPins++;
        }
        return "" + standingPins + " pin/s are still standing";
    }

    /**
     * prints out a visualization of the BowlingGame
     */
    public void visualizeStandingPins(int amountPins){
        int tableRows = (int) (-1/2 + Math.sqrt(1/4 + 2*amountPins));//pq to calculate the tableRows (visualization)
        if((-1/2 + Math.sqrt(1/4 + 2*amountPins)%1.0 != 0)) // round up if result is double (eg. result: 1.2 would be rounded to 1
            tableRows++;                                    // now a special row is added for the pins that dont fill the entire last row
        int pinCount = 0;

        System.out.println("View on pins from top");
        for(int countRow=0; countRow<tableRows; countRow++){
            for(int countSpaces=0; countSpaces<(tableRows - countRow); countSpaces++)
                System.out.print(" ");
            for(int countColumn=1; countColumn<=countRow+1; countColumn++){
                if(pinCount>=amountPins)
                    break;
                if(pinArray[pinCount].getPinState() == PinState.STANDING)
                    System.out.print("o ");
                if(pinArray[pinCount].getPinState() == PinState.KNOCKEDOUT)
                    System.out.print("8 ");
                pinCount++;
            }
            System.out.println();
        }
    }

    /**
     * Knocks down specific pin
     * @param pinNumber defines the number of the pin that will be knocked
     */
    public void knockDownSpecificPin(int pinNumber){
        pinArray[pinNumber-1].knockOut(); // -1 because PC counts from 0 and user from 1
    }

    /**
     * puts up specific pin
     * @param pinNumber defines the number of the pin that will be put up
     */
    public void getUpSpecificPin(int pinNumber){
        pinArray[pinNumber-1].putPinBackUp();
    }


    public static void main(String[] args) {
        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of pins you want: ");
        int amountPins = scanner.nextInt();
        System.out.println("Now enter the probability for knocking down a pin (in %): ");
        int probabilityForKnockOut = scanner.nextInt();
        System.out.println("Start normal game (y) / knock down specific pin (s) / get up specific pin (u) / end game (n)?: ");
        char playAgain = scanner.next().charAt(0);

        if(amountPins<1 || amountPins>99)
            throw new IllegalArgumentException("Amount of pins must be between 1 and 99");

        BowlingGame bowlingGame = new BowlingGame(amountPins);

        bowlingGame.reset();
        int countRounds = 0;

        do{
            countRounds++;

            System.out.println("_______________________________________________");
            System.out.println("Round: " + countRounds);
            if(playAgain == 'y') {
                System.out.println(bowlingGame.rollBall(probabilityForKnockOut));
                System.out.println(bowlingGame.countStandingPins());
            }
            if(playAgain == 's') {
                System.out.println("Which pin do you want to knock down? (number): ");
                int knockDownPinNumber = scanner.nextInt();
                // you could test whether the pin exists / is already knocked down
                bowlingGame.knockDownSpecificPin(knockDownPinNumber);
            }
            if(playAgain == 'u') {
                System.out.println("Which pin do you want to put back up? (number): ");
                int getUpPinNumber = scanner.nextInt();
                // you could test whether the pin exists / is already standing
                bowlingGame.getUpSpecificPin(getUpPinNumber);
            }

            bowlingGame.visualizeStandingPins(amountPins);

            System.out.println("Throw ball (y) | knock specific (s) | get up specific (u) | end game (n)? ");
            playAgain = scanner.next().charAt(0);
        }
        while (playAgain == 'y' || playAgain == 's' || playAgain == 'u');
        scanner.close();
    }
}