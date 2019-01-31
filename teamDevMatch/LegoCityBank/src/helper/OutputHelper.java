package helper;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import models.Admin;
import models.CustomerManager;
import models.MetalAccount;
import views.Main;

import javax.xml.soap.Text;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputHelper {
    public static DecimalFormat getDecimalFormatForFigures() {
        return new DecimalFormat("###,##0.00#############################");
    }

    public static void setNextScene(String name) throws IOException {
        if (name.equals("bankAccountView.fxml") && CustomerManager.getInstance().getInspectedBankAccount().getClass() == MetalAccount.class) {
            name = "metalAccountView.fxml";
        }
        if (name.equals("dashboardWindow.fxml") && CustomerManager.getInstance().getLoggedInUser().getClass() == Admin.class) {
            name = "dashboardWindowAdmin.fxml";
        }
        Parent root = FXMLLoader.load(Main.class.getResource(name));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Main.PRIMARY_STAGE.setScene(new Scene(root));
        Main.PRIMARY_STAGE.setX(0);
        Main.PRIMARY_STAGE.setY(0);
    }

    public static void validateMoneyValueInput(TextField textField, TextField errorTextField) {
        String input = textField.getText();
        String validInput = input.replaceAll("[^0-9|.|,]", "");
        if (!validInput.equals(input)) {
            textField.setText(validInput);
            errorTextField.setText("Bitte nur Ziffern eintragen.");
            errorTextField.setVisible(true);
        } else {
            Pattern pattern = Pattern.compile("[.|,]");
            Matcher matcher = pattern.matcher(validInput);
            matcher.find(); //find the first . or ,
            //if matcher finds a second . or , something is wrong
            if (matcher.find()) {
                Platform.runLater(() -> {
                    textField.setText("");
                    errorTextField.setText("Es darf maximal ein Komma oder Punkt vorkommen");
                    errorTextField.setVisible(true);
                });
            } else if (Pattern.compile("^[0-9]*[.|,][0-9]{3,}$").matcher(input).matches()) {
                Platform.runLater(() -> {
                    textField.setText(validInput.substring(0, validInput.length()-1));
                    errorTextField.setText("Es sind maximal zwei Nachkommastellen erlaubt");
                    errorTextField.setVisible(true);
                });
            } else {
                errorTextField.setVisible(false);
            }
        }
    }
}
