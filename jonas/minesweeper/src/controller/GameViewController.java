package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import sun.applet.Main;

import java.io.IOException;

public class GameViewController extends GridPane{

    public GameViewController(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/gameView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
