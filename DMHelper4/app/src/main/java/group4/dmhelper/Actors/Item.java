package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Item extends Model {
    private int playerId;
    private int itemId;

    public Item(int playerId) {
        this.id = playerId;
        /*This should take all of the information from the database*/
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
