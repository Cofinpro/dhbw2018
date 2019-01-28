package controller;

import helper.OutputHelper;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegisterController {
    public void goBack(MouseEvent mouseEvent) throws IOException {
        OutputHelper.setNextScene("loginWindow.fxml");
    }
}
