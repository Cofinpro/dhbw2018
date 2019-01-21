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
        userInputX.setHeaderText("Please enter the amount of rows of the field");
        userInputX.setContentText("rows:");
        Optional<String> x = userInputX.showAndWait();

        TextInputDialog userInputY = new TextInputDialog();
        userInputY.setTitle("User Input");
        userInputY.setHeaderText("Please enter the amount of columns of the field");
        userInputY.setContentText("columns:");
        Optional<String> y = userInputY.showAndWait();

        if(x.isPresent() && y.isPresent()) {
            int rows = Integer.parseInt(x.get());
            int columns = Integer.parseInt(y.get());
            GameOfLife.getInstance().setGameField(rows, columns);
            GameOfLifeCell[][] gf = GameOfLife.getInstance().getGameField();
            for (int row = 0; row < gf.length; row++) {
                for (int column = 0; column < gf[row].length; column++) {
                    gf[row][column] = new GameOfLifeCell(row, column);
                    gf[row][column].alive = false;
                }
            }
            Alert confirmValues = new Alert(Alert.AlertType.INFORMATION);
            confirmValues.setTitle("Success");
            confirmValues.setHeaderText("amounts of rows and columns successfully entered");
            confirmValues.setContentText("rows: " + rows + ", columns: " + columns);
            confirmValues.showAndWait();
        }
        else {
            Alert noValueEntered = new Alert(Alert.AlertType.ERROR, "At least one of the values had not been entered!");
            noValueEntered.show();
        }
    }
}
