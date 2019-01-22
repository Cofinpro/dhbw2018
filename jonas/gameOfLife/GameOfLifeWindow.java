package gameOfLife;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class GameOfLifeWindow extends Application {
    ResizableCanvas canvas = new ResizableCanvas();

    public static void main(String[] args) {
        GameOfLife.getInstance().setGameField(15,15);
        Random random = new Random();

        GameOfLifeCell[][] help = GameOfLife.getInstance().getGameField();


        for (int row = 0; row < help.length; row++) {
            for (int column = 0; column < help[row].length; column++) {
                help[row][column] = new GameOfLifeCell(row, column, false);
                double temp = Math.random() * 1;
                if (temp <= 0.1){
                    help[row][column].alive = true;
                }
                else{
                    help[row][column].alive = false;
                }
            }
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("GAME OF LIFE");
        primaryStage.setResizable(true);


        Pane pane = new Pane();
        canvas = new ResizableCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());


        pane.getChildren().add(canvas);

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT:
                    GameOfLife.getInstance().nextIteration();
                    canvas.colorChange();
                    canvas.drawLines();
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }
  /*  public void drawGame(GraphicsContext gc){

        GameOfLifeCell[][] cells = GameOfLife.getInstance().getGameField();


        gc.setStroke(Color.BLACK);
        for (int i = 0; i < cells.length;i++){
            gc.strokeLine(0,(canvas.getHeight()/15)*(i+1),canvas.getWidth(),(canvas.getHeight()/15)*(i+1));
        }
        for (int j = 0; j < cells.length;j++){
            gc.strokeLine((canvas.getWidth()/15)*(j+1),0,(canvas.getHeight()/15)*(j+1),canvas.getWidth());
        }

    }*/
}
