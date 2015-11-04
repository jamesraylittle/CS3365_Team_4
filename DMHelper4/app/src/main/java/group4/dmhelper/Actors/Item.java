package group4.dmhelper.Actors;

import android.content.Context;

import group4.dmhelper.Database.Items;


public class Item extends Model {
    private int playerId;
    private int itemId;
    private Items items;

    public Item(int id, int playerId, Context context) {
        this.playerId = playerId;
        this.id = id;
        this.items = new Items(context);
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

    public Item getItem() {
        return this.items.retrieve(this.id);
    }
}
