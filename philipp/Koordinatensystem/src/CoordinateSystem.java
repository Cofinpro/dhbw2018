import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class CoordinateSystem extends Application {
    double maxX;
    double maxY;
    String labelX = "x-axis";
    String labelY = "y-axis";

    Scanner scanner = new Scanner(System.in);
    ArrayList<CoordinatePoint> pointsArrayList = new ArrayList<CoordinatePoint>();

    @Override
    public void start(Stage stage) {
        //reads in the values given by user
        readMax();
        readPoints();
        //window settings
        stage.setTitle("visual output");
        stage.setMinWidth(100);
        stage.setMinHeight(100);
        stage.setResizable(true);
        stage.sizeToScene();

        ResizeableCanvas canvas = new ResizeableCanvas(this);
        Pane pane = new Pane();

        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        pane.getChildren().add(canvas);
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void readMax() {

        System.out.print("Please enter the maximum x-value: ");
        maxX = scanner.nextDouble();
        System.out.print("Please enter the maximum y-value: ");
        maxY = scanner.nextDouble();
    }

    public void readPoints() {

        char readNewPoint;
        double xCoord;
        double yCoord;
        System.out.println("Do you want to create random points (r) or enter them yourself (e)? ");
        char randomOrSelf = scanner.next().charAt(0);

        if(randomOrSelf == 'e') {
            do {
                System.out.println("please enter the coordinates of your point");
                System.out.print("X= ");
                xCoord = scanner.nextDouble();
                System.out.print("Y= ");
                yCoord = scanner.nextDouble();
                pointsArrayList.add(new CoordinatePoint(xCoord, yCoord));

                System.out.println("Do you want to add a new point? (y): ");
                readNewPoint = scanner.next().charAt(0);
            }
            while (readNewPoint == 'y');
        }
        if(randomOrSelf == 'r') {
            System.out.println("How many points would you like to be generated? ");
            int numberOfPoints = scanner.nextInt();

            for (int count = 0; count < numberOfPoints; count++) {
                double randX = Math.round(100.0*(Math.random()*maxX*2-maxX))/100.0;
                double randY = Math.round(100.0*(Math.random()*maxY*2-maxY))/100.0;
                pointsArrayList.add(new CoordinatePoint(randX, randY));
            }
        }
    }
}

