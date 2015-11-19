package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Item;

import java.util.ArrayList;
/**
 * Created by james
 */
public class Items extends Database implements DAO<Item> {

    private String TABLE = "items";

    public Items(Context context) {
        super(context, "items");
        createTable();
    }

    public int create(Item item) { return super.create(TABLE, values(item)); }

    public void update(Item item) { super.update(TABLE, item, values(item)); }


    public Item retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Item from = new Item();

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setPlayerId(c.getInt(1));
                from.setItemId(c.getInt(2));
            } while (c.moveToNext());
        }

        return from;
    }

    public ArrayList<Item> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + "" };
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<Item> list = new ArrayList<Item>();

        if(c.moveToFirst()) {
            do {
                Item i = new Item(c.getInt(0), c.getInt(1), c.getInt(2));
                list.add(i);
            } while (c.moveToNext());
        }

        return list;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Item item) {
        ContentValues values = new ContentValues();
        if(item.getId() > 0) values.put("id", item.getId());

        values.put("playerId", item.getPlayerId());
        values.put("ptemId", item.getItemId());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "itemId INTEGER" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }

}
