package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class MonsterType extends Model {
    private int playerId;   //This is to distinguish this monster from the rest of the actors
    private int monsterId;
    private int HD;

    public MonsterType(int id, int playerId, int monsterId, int HD) {
        this.id = id;
        this.playerId = playerId;
        this.monsterId = monsterId;
        this.HD = HD;
    }

    public MonsterType(int playerId, int monsterId, int HD) {
        this.playerId = playerId;
        this.monsterId = monsterId;
        this.HD = HD;
    }

    public MonsterType() {}

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getMonsterId() {
        return monsterId;
    }
    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }
    public int getHD() {
        return HD;
    }
    public void setHD(int HD) {
        this.HD = HD;
    }
}
