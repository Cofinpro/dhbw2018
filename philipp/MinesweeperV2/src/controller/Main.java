package controller;

import model.Difficulty;

public class Main {


    public static void main(String[] args) {
        setSettingsFromUserInput();

    }

    private static void setSettingsFromUserInput() {
        Difficulty[] difficulties = Difficulty.values();
        String[] difNames = new String[difficulties.length+1];
        difNames[0] = "Select Difficulty";
        for (int i = 1; i < difficulties.length+1; i++) {
            difNames[i] = difficulties[i-1].getDifName();
        }

        String[] superMineAmounts = {"Select Super-Mine amount", "0", "1", "2", "3"};

        UserSelectionController.getUserInput("Difficulty & Super-Mines", difNames, superMineAmounts);
    }

}
