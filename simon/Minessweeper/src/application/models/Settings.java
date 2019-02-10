package application.models;

import application.enums.Difficulty;
import javafx.beans.property.SimpleObjectProperty;

public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private SimpleObjectProperty<Difficulty> difficultyProperty = new SimpleObjectProperty<>();

    private Settings() {
    }

    public Difficulty getDifficulty() {
        return difficultyProperty.get();
    }

    public SimpleObjectProperty<Difficulty> getDifficultyProperty() {
        return difficultyProperty;
    }

    public void setDifficulty(Difficulty difficultySimpleObjectProperty) {
        this.difficultyProperty.set(difficultySimpleObjectProperty);
    }
}
