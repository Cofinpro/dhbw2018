import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class LaunchGOL extends Application {
    public void start(Stage stage) {
        initializeWindowGame();

        stage.setTitle("Game Of Life [window mode]");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMinHeight(200);
        stage.setMinWidth(300);
        stage.setResizable(true);
        stage.sizeToScene();

        OmnisizeableCanvas canvas = new OmnisizeableCanvas();
        VBox vBox = new VBox();
        HBox buttonHbox = new HBox();

        Button buttonPrevious = new Button();
        buttonPrevious.setText("previous iteration");
        buttonPrevious.setOnAction(event -> {
            GameOfLife.getInstance().previousIteration();
            canvas.drawLivingCells();
            canvas.drawGrid();
        });

        Button buttonNext = new Button();
        buttonNext.setText("next iteration");
        buttonNext.setOnAction(event -> {
            GameOfLife.getInstance().nextIteration();
            canvas.drawLivingCells();
            canvas.drawGrid();
        });

        buttonHbox.getChildren().addAll(buttonPrevious, buttonNext);
        vBox.getChildren().add(canvas); //verticalBox displays canvas above buttonHorizontalBox
        vBox.getChildren().add(buttonHbox);

        canvas.widthProperty().bind(vBox.widthProperty());
        canvas.heightProperty().bind(vBox.heightProperty().subtract(buttonNext.heightProperty()));

        Scene scene = new Scene(vBox);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT:
                    GameOfLife.getInstance().nextIteration();
                    canvas.drawLivingCells();
                    canvas.drawGrid();
                    break;
                case LEFT:
                    GameOfLife.getInstance().previousIteration();
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
                    gf[row][column].setAlive(false);
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
