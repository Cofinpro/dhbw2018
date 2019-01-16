package koordinatensystem;

public class CoordinateSystem {

    int maxX;
    int maxY;
    int spaceX;
    int spaceY;

    public CoordinateSystem(int maxX, int maxY,int spaceX, int spaceY){
        this.maxX = maxX;
        this.maxY = maxY;
        this.spaceX = spaceX;
        this.spaceY = spaceY;
    }
    public CoordinateSystem(){
        maxX = 300;
        maxY = 300;
        spaceX = 10;
        spaceY = 10;
    }
}
