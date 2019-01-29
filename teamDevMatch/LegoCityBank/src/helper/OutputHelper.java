package helper;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import views.Main;

import java.io.IOException;
import java.text.DecimalFormat;

public class OutputHelper {
    public static DecimalFormat getDecimalFormatForFigures() {
        return new DecimalFormat("0.00");
    }

    public static void setNextScene(String name) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(name));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Main.PRIMARY_STAGE.setScene(new Scene(root));
    }
}
