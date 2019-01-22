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

    public void getNearbyLiving(){
        int nearbyLiving = 0;

        GameOfLifeCell[][] cell = GameOfLife.getInstance().getGameField();
        //diagonal besdies cell
        if (xPos > 0 && yPos > 0 && cell[xPos-1][yPos-1].alive){
            nearbyLiving++;
        }
        if (xPos > 0 && cell[xPos-1][yPos].alive){
            nearbyLiving++;
        }
        if (xPos > 0 && yPos < cell.length-1&& cell[xPos-1][yPos+1].alive){
            nearbyLiving++;
        }
        if (yPos > 0 && cell[xPos][yPos-1].alive){
            nearbyLiving++;
        }
        //above or below cell in no specific corner
        if (yPos < cell.length-1 && cell[xPos][yPos+1].isAlive()){
            nearbyLiving++;
        }
        if (yPos >0&&cell[xPos+1][yPos-1].isAlive()){
            nearbyLiving++;
        }

        //right or left besides cell in no specific corner
        if (xPos >0 && cell[xPos+1][yPos-1].isAlive() ){
            nearbyLiving++;
        }
        if (xPos < cell.length-1 && cell[xPos+1][yPos].isAlive()){
            nearbyLiving++;
        }

        this.nearbyLiving = nearbyLiving;

    }
}
