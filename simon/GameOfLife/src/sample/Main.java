package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.GameOfLife;


public class Main extends Application {

    private GameOfLifeView canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("game of life");
        VBox vBox = new VBox();
        canvas = new GameOfLifeView();
        vBox.getChildren().add(canvas);
        canvas.widthProperty().bind(vBox.widthProperty());
        canvas.heightProperty().bind(vBox.heightProperty());
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.show();
        canvas.display();
        setupControls(scene);
    }

    private void setupControls(Scene scene) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case RIGHT:
                    GameOfLife.getInstance().moveToNextSituation();
                    canvas.display();
                    break;
                case LEFT:
                    GameOfLife.getInstance().tryToMoveToLastSituation();
                    canvas.display();
                    break;
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
