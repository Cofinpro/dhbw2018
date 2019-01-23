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

    //upates the cell depending on surrounding alive/dead cells
    public void getState(){
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
        if (xPos > 0 && yPos > 0 && cell[xPos-1][yPos-1].alive){
            nearbyLiving++;
        }

        //diagonal right above cell
        if(xPos<cell.length-1 && yPos<cell[xPos].length-1 && cell[xPos+1][yPos+1].alive){
            nearbyLiving++;
        }

        //diagonal left below cell
        if (xPos > 0 && yPos < cell[xPos].length-1&& cell[xPos-1][yPos+1].alive){
            nearbyLiving++;
        }

        //diagonal right below cell
        if(xPos<cell.length-1 && yPos>0 && cell[xPos+1][yPos-1].alive){
            nearbyLiving++;
        }

        //above cell
        if (yPos > 0 && cell[xPos][yPos-1].alive){
            nearbyLiving++;
        }

        //beneath cell
        if (yPos < cell[xPos].length-1 && cell[xPos][yPos+1].isAlive()){
            nearbyLiving++;
        }

        //left besides cell
        if (xPos > 0 && cell[xPos-1][yPos].alive){
            nearbyLiving++;
        }

        //right besides cell
        if(xPos<cell.length-1 && cell[xPos+1][yPos].alive){
            nearbyLiving++;
        }

        this.nearbyLiving = nearbyLiving;
        return nearbyLiving;
    }
}