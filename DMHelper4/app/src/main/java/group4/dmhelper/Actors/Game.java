package group4.dmhelper.Actors;


/**
 * Created by Daniel on 10/16/2015
 */
public class Game extends Model {
    private String name;
    private int numPlayers;

    public Game(int id) {
        this.id = id;
    }

    public Game(String name, int numPlayers) {
        this.name = name;
        this.numPlayers = numPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
