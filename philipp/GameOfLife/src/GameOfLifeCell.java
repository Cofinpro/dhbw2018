public class GameOfLifeCell {

    private boolean alive;
    private int x;
    private int y;
    private int livingNeighbors;

    public GameOfLifeCell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public GameOfLifeCell(int x, int y, int livingNeighbors, boolean alive){
        this.x = x;
        this.y = y;
        this.livingNeighbors = livingNeighbors;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLivingNeighbors() {
        return livingNeighbors;
    }

    public void setLivingNeighbors(int livingNeighbors) {
        this.livingNeighbors = livingNeighbors;
    }

    public void checkAlive(){
        if (this.alive){
            if (livingNeighbors < 2)
                this.alive = false;
            else if (livingNeighbors > 3)
                this.alive = false;
        }
        else{
            if(livingNeighbors == 3)
                this.alive = true;
        }
    }

    public void countLivingNeighbors(){
        int livingNeighbors = 0;
        GameOfLifeCell[][] gF = GameOfLife.getGameField();

        if(isUpLeftCornerCellAlive(gF))
            livingNeighbors++;
        if(isAboveCellAlive(gF))
            livingNeighbors++;
        if(isUpRightCornerCellAlive(gF))
            livingNeighbors++;
        if(isLeftCellAlive(gF))
            livingNeighbors++;
        if(isRightCellAlive(gF))
            livingNeighbors++;
        if(isDownLeftCornerCellAlive(gF))
            livingNeighbors++;
        if(isBeneathCellAlive(gF))
            livingNeighbors++;
        if(isDownRightCornerCellAlive(gF))
            livingNeighbors++;

        this.livingNeighbors = livingNeighbors;
    }

    private boolean isDownRightCornerCellAlive(GameOfLifeCell[][] gF) {
        return x < gF.length - 1 && y < gF[x].length - 1 && gF[x+1][y + 1].alive;
    }

    private boolean isBeneathCellAlive(GameOfLifeCell[][] gF) {
        return x<gF.length-1 && gF[x+1][y].alive;
    }

    private boolean isDownLeftCornerCellAlive(GameOfLifeCell[][] gF) {
        return x < gF.length - 1 && y > 0 && gF[x+1][y - 1].alive;
    }

    private boolean isRightCellAlive(GameOfLifeCell[][] gF) {
        return y< gF.length-1 && gF[x][y+1].alive;
    }

    private boolean isLeftCellAlive(GameOfLifeCell[][] gF) {
        return y > 0 && gF[x][y-1].alive;
    }

    private boolean isUpRightCornerCellAlive(GameOfLifeCell[][] gF) {
        return x > 0 && y < gF[x].length - 1 && gF[x-1][y + 1].alive;
    }

    private boolean isAboveCellAlive(GameOfLifeCell[][] gF) {
        return x > 0 && gF[x-1][y].alive;
    }

    private boolean isUpLeftCornerCellAlive(GameOfLifeCell[][] gF) {
        return x > 0 && y > 0 && gF[x-1][y - 1].alive;
    }
}
