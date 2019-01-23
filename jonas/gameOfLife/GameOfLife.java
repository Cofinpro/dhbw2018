package gameOfLife;

public class GameOfLife {
    private static GameOfLifeCell[][] gameField;

    public void setGameField(int x, int y) {
        this.gameField = new GameOfLifeCell[x][y];
    }

    public void setGameOfLife(GameOfLifeCell[][] help){
        gameField = help;
    }

    public static GameOfLifeCell[][] getGameField() {
        return getInstance().gameField;
    }

    //Singelton - Pattern
    private static GameOfLife instance;

    private GameOfLife() {
    }

    public static GameOfLife getInstance() {
        if (instance == null) {
            instance = new GameOfLife();
        }
        return instance;
    }
   /* public void consoleSetup() {
        gameField = new GameOfLifeCell[15][15];
        Random random = new Random();



       for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                gameField[row][column] = new GameOfLifeCell(row, column, false);
                double temp = Math.random() * 1;
                if (temp <= 0.2){
                    gameField[row][column].alive = true;
                }
                else{
                    gameField[row][column].alive = false;
                }
            }
        }

    }
    public void print() {
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {

                if (gameField[row][column].alive) {
                    System.out.print("\u25A1 ");
                }
                else{
                    System.out.print("\u25A0 ");
                }

            }
            System.out.println();
        }
    }
    */

   //updates the cells depending on their state and neighbours
    public void nextIteration() {
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column <gameField[row].length; column++) {
                gameField[row][column].getNearbyLiving();
            }
        }
        for (int row = 0; row < gameField.length; row++) {
            for (int column = 0; column < gameField[row].length; column++) {
                gameField[row][column].getState();
            }
        }
    }
   /* public void play(){
        getInstance().print();
        Scanner scanner = new Scanner(System.in);
        String help;
        help = scanner.nextLine();

        while(help.equals("y")){
            System.out.println("----------------------------");
            getInstance().nextIteration();
            getInstance().print();

            System.out.println("");
            System.out.println("Nächste Generation ?");
            help = scanner.nextLine();
        }

    }

    public static void main(String[] args) {
        System.out.println("Willkommen zum Game of Life");
        System.out.println("----------------------------");
        System.out.println(	"\u25A1 gibt eine lebende Zelle an \u25A0 gibt eine tote zelle an");
        getInstance().consoleSetup();

        System.out.println("Nächste Generation ? [y] für ja [n] für nein");
        getInstance().play();

    }


*/
}