package koordinatensystem;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class outputCoordinateSystem extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int arrowLength = 10;

        Scanner question = new Scanner(System.in);
        System.out.println("Willst du dein Koordinatensystem mit eigenen Eigenschaften versehen ? [j] für ja [n] für nein");
        String answer = question.nextLine();
        if (answer.equals("n")|| answer.equals("N")){

            CoordinateSystem cSystem = new CoordinateSystem();
            primaryStage.setTitle("Koordinatensystem");
            Group root = new Group();

            int canvasWidth = cSystem.maxX*2;
            int canvasHeight = cSystem.maxY*2;
            Canvas canvas = new Canvas(canvasWidth, canvasHeight);
            root.getChildren().add(canvas);


            Line xAxisPos = new Line(canvasWidth-cSystem.maxX,canvasHeight-cSystem.maxY,canvasWidth,canvasHeight-cSystem.maxY);
            Line xAxisNeg = new Line(canvasWidth-cSystem.maxX,canvasHeight-cSystem.maxY,-canvasWidth,canvasHeight-cSystem.maxY);
            Line yAxisPos = new Line(canvasWidth-cSystem.maxX,canvasHeight-cSystem.maxY,canvasWidth-cSystem.maxX,canvasHeight);
            Line yAxisNeg = new Line(canvasWidth-cSystem.maxX,canvasHeight-cSystem.maxY,canvasWidth-cSystem.maxX,-canvasHeight);
            Line arrowTopLeftHalf = new Line(canvasWidth-cSystem.maxX,0,canvasWidth-cSystem.maxX-arrowLength,arrowLength);
            Line arrowTopRightHalf = new Line(canvasWidth-cSystem.maxX,0,canvasWidth-cSystem.maxX+arrowLength,arrowLength);
            Line arrowRightTopHalf = new Line(canvasWidth,canvasHeight-cSystem.maxY,canvasWidth-arrowLength,canvasHeight-cSystem.maxY+arrowLength);
            Line arrowRightBottomHalf = new Line(canvasWidth,canvasHeight-cSystem.maxY,canvasWidth-arrowLength,canvasHeight-cSystem.maxY-arrowLength);

            for (int i = 0; i < canvasWidth && i < canvasHeight;i+=cSystem.spaceX){
                Line lineX = new Line(i,0,i,canvasWidth);
                lineX.setStroke(Color.LIGHTGRAY);
                Line lineY = new Line(0,i,canvasHeight,i);
                lineY.setStroke(Color.LIGHTGRAY);
                root.getChildren().addAll(lineX,lineY);
            }

            for (int i = 0; i < canvasWidth-1;i+=cSystem.spaceX){
                Line lineSpaceX = new Line(i,(canvasWidth-cSystem.maxY+cSystem.spaceX*0.3),i,(canvasWidth-cSystem.maxY-cSystem.spaceX*0.3));
                root.getChildren().addAll(lineSpaceX);
            }
            for (int i = 0; i < canvasHeight-1;i+=cSystem.spaceY){
                Line lineSpaceY = new Line((canvasHeight-cSystem.maxX-cSystem.spaceY*0.3),i,(canvasHeight-cSystem.maxX+cSystem.spaceY*0.3),i);
                root.getChildren().addAll(lineSpaceY);
            }

            int max = 600;
            int min = -300;

            Scanner pointsQuestion = new Scanner(System.in);
            System.out.println("Wie viele Punkte möchten sie einlesen ? Diese werden zufällig generiert");
            int answerPointsQuestion = pointsQuestion.nextInt();
            CoordinatePoint[] coordinatePoints = new CoordinatePoint[answerPointsQuestion];

            Circle[] circles = new Circle[coordinatePoints.length];
            Text[] texts = new Text[coordinatePoints.length];
            for (int i= 0; i < coordinatePoints.length;i++){

                coordinatePoints[i] = new CoordinatePoint((int)((Math.random()*max)+min),(int)((Math.random()*max)+min));
                circles[i] = new Circle((canvasWidth-cSystem.maxX+coordinatePoints[i].getxValue()),cSystem.maxY-coordinatePoints[i].getyValue(),2);
                texts[i] = new Text(canvasWidth-cSystem.maxX+coordinatePoints[i].getxValue()-10,cSystem.maxY-coordinatePoints[i].getyValue()-10,"P "+i+": ("+coordinatePoints[i].getxValue()+"|"+coordinatePoints[i].getyValue()+")");
                root.getChildren().addAll(circles[i],texts[i]);
            }

            root.getChildren().addAll(xAxisPos,xAxisNeg,yAxisPos,yAxisNeg,arrowTopLeftHalf,arrowTopRightHalf,arrowRightTopHalf,arrowRightBottomHalf);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        else if (answer.equals("j")|| answer.equals("J")){
            Scanner sc = new Scanner(System.in);
            System.out.println("Bitte Wert für x- Achse eingeben");
            int maxX = sc.nextInt();
            System.out.println("Bitte Wert für y-Achse eingeben");
            int maxY = sc.nextInt();
            System.out.println("Bitte Koordinatenabstand der x-Achse eingeben");
            int spaceX = sc.nextInt();
            System.out.println("Bitte Koordinatenabstand der y-Achse eingeben");
            int spaceY = sc.nextInt();
            CoordinateSystem cSystemInput = new CoordinateSystem(maxX,maxY,spaceX,spaceY);


            primaryStage.setTitle("Koordinatensystem");
            Group root = new Group();


            int canvasWidth = maxX*2;
            int canvasHeight = maxY*2;
            Canvas canvas = new Canvas(canvasWidth, canvasHeight);
            root.getChildren().add(canvas);


            Line xAxisPos = new Line(canvasWidth-maxX,canvasHeight-maxY,canvasWidth,maxY);
            Line xAxisNeg = new Line(canvasWidth-maxX,canvasHeight-maxY,-canvasWidth,canvasHeight-maxY);
            Line yAxisPos = new Line(canvasWidth-maxX,canvasHeight-maxY,canvasWidth-maxX,canvasHeight);
            Line yAxisNeg = new Line(canvasWidth-maxX,canvasHeight-maxY,canvasWidth-maxX,-canvasHeight);
            Line arrowTopLeftHalf = new Line(canvasWidth-maxX,0,canvasWidth-maxX-arrowLength,arrowLength);
            Line arrowTopRightHalf = new Line(canvasWidth-maxX,0,canvasWidth-maxX+arrowLength,arrowLength);
            Line arrowRightTopHalf = new Line(canvasWidth,canvasHeight-maxY,canvasWidth-arrowLength,canvasHeight-maxY+arrowLength);
            Line arrowRightBottomHalf = new Line(canvasWidth,canvasHeight-maxY,canvasWidth-arrowLength,canvasHeight-maxY-arrowLength);

            for (int i = 0; i < canvasWidth && i < canvasHeight;i+=spaceX){
                Line lineX = new Line(i,0,i,canvasWidth);
                lineX.setStroke(Color.LIGHTGRAY);
                Line lineY = new Line(0,i,canvasHeight,i);
                lineY.setStroke(Color.LIGHTGRAY);
                root.getChildren().addAll(lineX,lineY);
            }

            for (int i = 0; i < canvasWidth;i+=spaceX){
                Line lineSpaceX = new Line(i,(canvasWidth-maxY+spaceX*0.3),i,(canvasWidth-maxY-spaceX*0.3));
                root.getChildren().addAll(lineSpaceX);
            }
            for (int i = 0; i < canvasHeight;i+=spaceY){
                Line lineSpaceY = new Line((canvasHeight-maxX-spaceY*0.3),i,(canvasHeight-maxX+spaceY*0.3),i);
                root.getChildren().addAll(lineSpaceY);
            }

            int helpMaxX = maxX*2;
            int helpMaxY = -maxY;

            Scanner pointsQuestion = new Scanner(System.in);
            System.out.println("Wie viele Punkte möchten sie einlesen ? Diese werden zufällig generiert");
            int answerPointsQuestion = pointsQuestion.nextInt();
            CoordinatePoint[] coordinatePoints = new CoordinatePoint[answerPointsQuestion];

            Circle[] circles = new Circle[coordinatePoints.length];
            Text[] texts = new Text[coordinatePoints.length];
            for (int i= 0; i < coordinatePoints.length;i++){

                coordinatePoints[i] = new CoordinatePoint((int)((Math.random()*helpMaxX)+helpMaxY),(int)((Math.random()*helpMaxX)+helpMaxY));
                circles[i] = new Circle((canvasWidth-maxX+coordinatePoints[i].getxValue()),maxY-coordinatePoints[i].getyValue(),2);
                texts[i] = new Text(canvasWidth-maxX+coordinatePoints[i].getxValue()-10,maxY-coordinatePoints[i].getyValue()-10,"P "+i+": ("+coordinatePoints[i].getxValue()+"|"+coordinatePoints[i].getyValue()+")");
                root.getChildren().addAll(circles[i],texts[i]);
            }



            root.getChildren().addAll(xAxisPos,xAxisNeg,yAxisPos,yAxisNeg,arrowTopLeftHalf,arrowTopRightHalf,arrowRightTopHalf,arrowRightBottomHalf);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }
        else {
            System.out.println("ungültige Eingabe");
        }


    }

}

