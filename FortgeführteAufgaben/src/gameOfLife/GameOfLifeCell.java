package gameOfLife;

public class GameOfLifeCell {
    int xPos;
    int yPos;
    boolean alive;
    int nearbyLiving;

    public GameOfLifeCell(int xPos, int yPos, boolean alive){
        this.xPos = xPos;
        this.yPos = yPos;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void  setAlive(boolean alive){
        this.alive = alive;
    }

    //upates the cell depending on surrounding alive/dead cells
    public void updateState(){
        if (this.alive){
            if (nearbyLiving < 2){
                this.alive = false;
            }
            if (nearbyLiving > 3){
                this.alive = false;
            }
            if (nearbyLiving ==2 || nearbyLiving ==3){
                this.alive = true;
            }
        }
        if (this.alive == false){
            if (nearbyLiving == 3){
                this.alive = true;
            }
        }
    }

    //counts nearby living cells
    public int getNearbyLiving(){
        int nearbyLiving = 0;

        GameOfLifeCell[][] cell = GameOfLife.getInstance().getGameField();
        //diagonal left above cell
        if (isDiagonalLeftAboveAlive(xPos > 0, xPos - 1, cell)){
            nearbyLiving++;
        }

        //diagonal right above cell
        if(isDiagonalRightAboveAlive(cell, xPos < cell.length - 1, xPos + 1)){
            nearbyLiving++;
        }

        //diagonal left below cell
        if (isDiagonalLeftBelowAlive(cell, xPos > 0, xPos - 1)){
            nearbyLiving++;
        }

        //diagonal right below cell
        if(isDiagonalRightBelowAlive(cell, xPos < cell.length - 1, xPos + 1)){
            nearbyLiving++;
        }

        //above cell
        if (isAboveCellAlive(yPos, xPos, yPos - 1, cell)){
            nearbyLiving++;
        }

        //beneath cell
        if (isBeneathCellAlive(cell[xPos])){
            nearbyLiving++;
        }

        //left besides cell
        if (isAboveCellAlive(xPos, xPos-1, yPos, cell)){
            nearbyLiving++;
        }

        //right besides cell
        if(isRightBesidesCellAlive(cell, xPos < cell.length - 1, xPos + 1, yPos)){
            nearbyLiving++;
        }

        this.nearbyLiving = nearbyLiving;
        return nearbyLiving;
    }

    private boolean isRightBesidesCellAlive(GameOfLifeCell[][] cell, boolean b, int i, int yPos) {
        return b && cell[i][yPos].alive;
    }


    private boolean isBeneathCellAlive(GameOfLifeCell[] gameOfLifeCells) {
        return yPos < gameOfLifeCells.length-1 && gameOfLifeCells[yPos+1].isAlive();
    }

    private boolean isDiagonalRightBelowAlive(GameOfLifeCell[][] cell, boolean b, int i) {
        return b && yPos > 0 && cell[i][yPos - 1].alive;
    }

    private boolean isDiagonalLeftBelowAlive(GameOfLifeCell[][] cell, boolean b, int i) {
        return b && yPos < cell[xPos].length - 1 && cell[i][yPos + 1].alive;
    }

    private boolean isDiagonalRightAboveAlive(GameOfLifeCell[][] cell, boolean b, int i) {
        return b && yPos < cell[xPos].length - 1 && cell[i][yPos + 1].alive;
    }

    private boolean isDiagonalLeftAboveAlive(boolean b, int i, GameOfLifeCell[][] cell) {
        return b && yPos > 0 && cell[i][yPos - 1].alive;
    }

    private boolean isAboveCellAlive(int yPos, int xPos, int i, GameOfLifeCell[][] cell) {
        return yPos > 0 && cell[xPos][i].alive;
    }

}