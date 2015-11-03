package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Item;

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
                from.setName(c.getString(1));
                from.setCategory(c.getString(2));
                from.setSubCategory(c.getString(3));
                from.setSpecialAbility(c.getString(4));
                from.setAura(c.getString(5));
                from.setAlignment(c.getString(6));
            } while (c.moveToNext());
        }

        return from;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Item item) {
        ContentValues values = new ContentValues();
        if(item.id() > 0) values.put("id", item.id());

        values.put("name", item.name());
        values.put("category", item.category());
        values.put("subCategory", item.subCategory());
        values.put("specialAbility", item.specialAbility());
        values.put("aura", item.aura());
        values.put("alignment", item.alignment());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "name TEXT," +
                "category TEXT," +
                "subCategory TEXT," +
                "specialAbility TEXT," +
                "aura TEXT," +
                "alignment TEXT" +
                ")";
        database.execSQL(q);
    }


}
