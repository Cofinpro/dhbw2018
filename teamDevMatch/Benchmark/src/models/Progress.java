package models;

import javafx.beans.property.DoubleProperty;

import java.util.Observable;

public class Progress extends Observable {
    private double relativeProgress;

    public Progress() {
        this.relativeProgress = 0;
    }

    public void setRelativeProgress(float relativeProgress) {
        this.relativeProgress = relativeProgress;
        setChanged();
        notifyObservers();
    }

    public double getRelativeProgress() {
        return relativeProgress;
    }
}
