package group4.dmhelper;

import android.app.Application;

/**
 * Created by Kyle on 10/31/2015.
 */
public class globalVariables extends Application {
    private static globalVariables instance;
    private int gameId = 0;
    private int[] playerIDs;
    private String[] playerNames;

    private globalVariables(){}

    public int getGameId() { return gameId; }
    public void setGameId(int id) { this.gameId = id; }

    public static synchronized globalVariables getInstance() {
        if (instance==null) {
            instance = new globalVariables();
        }
        return instance;
    }

    @Override public void onCreate() {
        super.onCreate();
        gameId = 0;
    }
}