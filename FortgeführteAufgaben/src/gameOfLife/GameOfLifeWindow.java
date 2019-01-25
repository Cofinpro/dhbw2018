package gameOfLife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameOfLifeWindow extends Application {
    ResizableCanvas canvas = new ResizableCanvas();

    public static void main(String[] args) {
        GameOfLife.getInstance().setGameField(15,15);

        GameOfLifeCell[][] help = GameOfLife.getInstance().getGameField();

        //generates cell that are dead by default but can randomly turn alive
        for (int row = 0; row < help.length; row++) {
            for (int column = 0; column < help[row].length; column++) {
                help[row][column] = new GameOfLifeCell(row, column, false);
                double temp = Math.random() * 1;
                if (temp <= 0.1){
                    help[row][column].setAlive(true);
                }
                else{
                    help[row][column].setAlive(false);
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
                case ENTER:
                    GameOfLife.getInstance().nextIteration();
                    canvas.colorChange();
                    canvas.drawLines();
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}