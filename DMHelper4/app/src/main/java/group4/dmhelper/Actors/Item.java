package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Item extends Model {
    private int playerId;
    private int itemId;

    public Item(int id, int playerId, int itemId) {
        this.id = id;
        this.playerId = playerId;
        this.itemId = itemId;
    }

    public Item(int playerId, int itemId) {
        this.playerId = playerId;
        this.itemId = itemId;
    }

    public Item() {
        this(0,0,0);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
