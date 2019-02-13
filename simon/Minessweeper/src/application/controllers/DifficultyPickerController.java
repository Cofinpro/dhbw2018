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
                Difficulty.values())
        );
        Settings settings = Settings.getInstance();
        this.getSelectionModel().select(settings.getDifficulty());
        settings.getDifficultyProperty().bind(getSelectionModel().selectedItemProperty());
    }

    void chooseDefault() {
        this.getSelectionModel().select(Settings.DEFAULT_DIFFICULTY);
    }
}
