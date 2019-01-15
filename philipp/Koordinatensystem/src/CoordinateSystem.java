import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class CoordinateSystem extends Application {
    double maxX;
    double maxY;
    String labelX;
    String labelY;

    static ArrayList<CoordinatePoint> pointsArrayList = new ArrayList<CoordinatePoint>();

    public CoordinateSystem(){
        maxX = 400;
        maxY = 400;
        labelX = "X-axis";
        labelY = "Y-axis";
    }

    @Override
    public void start(Stage stage) {
        CoordinateSystem coordinateSystem = new CoordinateSystem();
        stage.setTitle("visual output");
        Canvas canvas = new Canvas();
        canvas.setWidth(coordinateSystem.maxX*2);
        canvas.setHeight(coordinateSystem.maxY*2);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //prints the x and y axis
        gc.strokeLine(0,maxY,maxX*2,maxY);
        gc.strokeLine(maxX,maxY*2,maxX,0);

        //calculates the necessary distance between two markers
        double markerSpaceX = maxX/5;
        double markerSpaceY = maxY/5;

        //prints the markers
        for(double countX = 0; countX<maxX*2; countX += markerSpaceX) { //prints left markers first
            gc.strokeLine(countX, maxY + markerSpaceY / 10, countX, maxY - markerSpaceY / 10);
        }

        for(double countY = maxY*2; countY>0; countY -= markerSpaceY) { //prints lower markers first
            gc.strokeLine(maxX + markerSpaceX / 10, countY, maxX - markerSpaceX / 10, countY);
        }

        //prints the axis labels with ~5% offset
        gc.strokeText(labelX,maxX*1.85,maxY*0.95);
        gc.strokeText(labelY,maxX*1.05,maxY*0.05);

        //prints the points from the pointsArrayList
        gc.setFill(Color.RED);
        for (int pointCount=0; pointCount<pointsArrayList.size(); pointCount++){
            double xTemp = pointsArrayList.get(pointCount).xValue+maxX;
            double yTemp = maxY-pointsArrayList.get(pointCount).yValue;
            gc.fillOval(xTemp, yTemp,maxX*0.02, maxY*0.02);
            gc.strokeText("P1: (" + pointsArrayList.get(pointCount).xValue + " | "+
                    pointsArrayList.get(pointCount).yValue + ")",
                    xTemp + markerSpaceX/10,yTemp - markerSpaceY/10);
        }

        stage.setScene(new Scene(new Pane(canvas)));
        stage.show();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char readNewPoint;
        double xCoord;
        double yCoord;

        do {
            System.out.print("please enter the coordinates of your point\nX= ");
            xCoord = scanner.nextDouble();
            System.out.print("Y= ");
            yCoord = scanner.nextDouble();
            pointsArrayList.add(new CoordinatePoint(xCoord,yCoord));

            System.out.println("Do you want to add a new point? (y/n): ");
            readNewPoint = scanner.next().charAt(0);
        }
        while (readNewPoint == 'y');

        launch(args);
    }
}
