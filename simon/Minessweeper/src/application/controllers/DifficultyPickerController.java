package application.controllers;

import application.Main;
import application.enums.Difficulty;
import application.models.Settings;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.util.Set;

public class DifficultyPickerController extends ChoiceBox<Difficulty> {
    public DifficultyPickerController() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/difficultyPicker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setTooltip(new Tooltip("Difficulty"));
        setItems(FXCollections.observableArrayList(
                Difficulty.EASY, Difficulty.MEDIUM, Difficulty.DIFFICULT)
        );
        Settings settings = Settings.getInstance();
        settings.getDifficultyProperty().bind(getSelectionModel().selectedItemProperty());
    }
}
