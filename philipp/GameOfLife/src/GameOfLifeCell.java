public class GameOfLifeCell {
    boolean alive;
    int x;
    int y;
    int livingNeighbors;

    public GameOfLifeCell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void checkAlive(){
        if (this.alive){
            if (livingNeighbors < 2)
                this.alive = false;
            else if (livingNeighbors > 3)
                this.alive = false;
            else
                this.alive = true;
        }
        else{
            if(livingNeighbors == 3)
                this.alive = true;
        }
    }

    public void countLivingNeighbors(){
        int livingNeighbors = 0;

        GameOfLifeCell[][] gF = GameOfLife.getInstance().gameField;

        if(x>0 && y>0 && gF[x-1][y-1].alive)
            livingNeighbors++;
        if(x>0 && gF[x-1][y].alive)
            livingNeighbors++;
        if(x>0 && y<gF[x].length-1 && gF[x-1][y+1].alive)
            livingNeighbors++;
        if(y>0 && gF[x][y-1].alive)
            livingNeighbors++;
        if(y<gF[x].length-1 && gF[x][y+1].alive)
            livingNeighbors++;
        if(x<gF.length-1 && y<0 && gF[x+1][y-1].alive)
            livingNeighbors++;
        if(x<gF.length-1 && gF[x+1][y].alive)
            livingNeighbors++;
        if(x<gF.length-1 && y<gF[x].length-1 && gF[x+1][y+1].alive)
            livingNeighbors++;

        this.livingNeighbors = livingNeighbors;
    }
}
