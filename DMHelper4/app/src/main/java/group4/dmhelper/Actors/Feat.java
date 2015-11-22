package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Feat extends Model {
    private int playerId;
    private int featId;
    private String featName;

    public Feat(int id, int playerId, int itemId, String featName) {
        this.id = id;
        this.playerId = playerId;
        this.featId = itemId;
        this.featName = featName;
    }

    public Feat(int playerId, int itemId, String featName) {
        this.playerId = playerId;
        this.featId = itemId;
        this.featName = featName;
    }

    public Feat() {
        this(0,0,0,"");
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

    public String getFeatName() {
        return featName;
    }
    public void setFeatName(String featName) {
        this.featName = featName;
    }
}
