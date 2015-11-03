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

    public int create(Item item) {
        return (int)database.insert(TABLE, null, createContentValues(item));
    }

    public void update(Item item) {
        String[] args = new String[] { item.id()+""};
        ContentValues values = createContentValues(item);
        database.update(TABLE, values, "id = ?", args);
    }


    public Item retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);

        Item from = null;

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    from = new Item(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));
                } while (c.moveToNext());
            }
        } else from = new Item();

        return from;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues createContentValues(Item item) {
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
