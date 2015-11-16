package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Feat extends Model {
    private int playerId;
    private int featId;

    public Feat(int id, int playerId, int itemId) {
        this.id = id;
        this.playerId = playerId;
        this.featId = itemId;
    }

    public Feat(int playerId, int itemId) {
        this.playerId = playerId;
        this.featId = itemId;
    }

    public Feat() {
        this(0,0,0);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getFeatId() {
        return featId;
    }

    public void setFeatId(int itemId) {
        this.featId = itemId;
    }
}
