package koordinatensystem;

public class CoordinatePoint {

    int xValue;
    int yValue;

    public CoordinatePoint(int xValue, int yValue){
        this.xValue = xValue;
        this.yValue = yValue;
    }
    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }
}
