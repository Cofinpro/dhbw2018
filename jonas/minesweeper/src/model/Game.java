package model;

public class Game {

    private static Game instance;

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

}
