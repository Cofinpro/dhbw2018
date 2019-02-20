package view;

import controller.UserNameIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import persistance.CsvDao;

public class MainFXML extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //todo if player is in top 10
        if (true) {
            UserNameIn.readUserName("Enter name", "Please enter your username",
                    "username:", Board.getInstance().getSeconds().getValue().toString());
        }
        Parent root = FXMLLoader.load(getClass().getResource("scoreView.fxml"));
        primaryStage.setTitle("Highscores");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        CsvDao csvDao = new CsvDao();
        csvDao.writeHighScoresToCsv();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
