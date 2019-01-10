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
    public String rollBall(){
        int knockedOutPinsByCurrentBall = 0;
        for(int count=0; count<pinArray.length; count++) {
            int knockDownPin = (int)(Math.random()*2);
            if (knockDownPin == 1 && pinArray[count].pinState == PinState.STANDING) {
                pinArray[count].knockOut();
                knockedOutPinsByCurrentBall++;
            }
        }
        return "" + knockedOutPinsByCurrentBall + " pin/s were knocked out by the current ball";
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
    public void knockDownAllPins(){ // this method mainly exists for testing purposes
        for(int count=0; count<pinArray.length; count++)
            pinArray[count].knockOut();
    }


    public static void main(String[] args) {
        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of pins you want: ");
        int amountPins = scanner.nextInt();

        if(amountPins<1 || amountPins>99)
            throw new IllegalArgumentException("Amount of pins must be between 1 and 99");

        BowlingGame bowlingGame = new BowlingGame(amountPins);

        bowlingGame.reset();
        int countRounds = 0;
        char playAgain = 'y';

        do{
            countRounds++;

            System.out.println("_______________________________________________");
            System.out.println("Round: " + countRounds);
            System.out.println(bowlingGame.rollBall());
            System.out.println(bowlingGame.countStandingPins());
            bowlingGame.visualizeStandingPins(amountPins);

            System.out.println("Do you want to throw the ball again? (y/n) ");
            playAgain = scanner.next().charAt(0);
        }
        while (playAgain == 'y');
    }
}
