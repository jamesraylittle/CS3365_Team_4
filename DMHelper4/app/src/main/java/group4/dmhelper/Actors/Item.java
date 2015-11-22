package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Item extends Model {
    private int playerId;
    private int itemId;
    private String itemName;

    public Item(int id, int playerId, int itemId, String itemName) {
        this.id = id;
        this.playerId = playerId;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public Item(int playerId, int itemId, String itemName) {
        this.playerId = playerId;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public Item(int id) {
        this.id = id;
    }

    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
