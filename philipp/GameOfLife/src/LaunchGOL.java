import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

public class LaunchGOL extends Application {
    public void start(Stage stage) {
        initializeWindowGame();

        stage.setTitle("Game Of Life [window mode]");
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.setResizable(true);
        stage.sizeToScene();

        OmnisizeableCanvas canvas = new OmnisizeableCanvas();
        Pane pane = new Pane();

        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        pane.getChildren().add(canvas);
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT:
                    GameOfLife.getInstance().nextIteration();
                    canvas.drawLivingCells();
                    canvas.drawGrid();
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initializeWindowGame(){
        TextInputDialog userInputX = new TextInputDialog();
        userInputX.setTitle("User Input");
        userInputX.setHeaderText("Please enter the width of the field");
        userInputX.setContentText("width:");
        Optional<String> x = userInputX.showAndWait();

        TextInputDialog userInputY = new TextInputDialog();
        userInputY.setTitle("User Input");
        userInputY.setHeaderText("Please enter the length of the field");
        userInputY.setContentText("length:");
        Optional<String> y = userInputY.showAndWait();

        if(x.isPresent() && y.isPresent()) {
            int width = Integer.parseInt(x.get());
            int length = Integer.parseInt(y.get());
            GameOfLife.getInstance().setGameField(width, length);
            GameOfLifeCell[][] gf = GameOfLife.getInstance().getGameField();
            for (int row = 0; row < gf.length; row++) {
                for (int column = 0; column < gf[row].length; column++) {
                    gf[row][column] = new GameOfLifeCell(row, column);
                    gf[row][column].alive = false;
                }
            }
            Alert confirmValues = new Alert(Alert.AlertType.INFORMATION);
            confirmValues.setTitle("Success");
            confirmValues.setHeaderText("Values for width and length successfully entered");
            confirmValues.setContentText("width: " + width + ", length: " + length);
            confirmValues.showAndWait();
        }
        else {
            Alert noValueEntered = new Alert(Alert.AlertType.ERROR, "At least one of the values had not been entered!");
            noValueEntered.show();
        }
    }
}
