package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Ability;

/**
 * Created by james
 */
public class Abilities extends Database implements DAO<Ability> {
    private String TABLE = "abilities";

    public Abilities(Context context) {
        super(context, "abilities");
        createTable();
    }

    public int create(Ability a) {
        return (int)database.insert(TABLE, null, createContentValues(a));
    }

    public void update(Ability a) {
        database.update(TABLE, createContentValues(a), "id = ?", new String[]{a.id()+""});
    }

    public Ability retrieve(int id) {
        String[] args = new String[] {id+""};
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Ability a = new Ability();

        if (c.moveToFirst()) {
            do {
                a.setId(c.getInt(0));
                a.setBaseScore(c.getInt(1));
                a.setMiscBonus(c.getInt(2));
                a.setName(c.getString(3));
            } while (c.moveToNext());
        }

        return a;
    }

    public void delete(int id) {
        database.delete(TABLE, "id = ?", new String[] {id + ""});
    }

    public int count() { return super.count(TABLE); }

    private ContentValues createContentValues(Ability a) {
        ContentValues values = new ContentValues();
        if(a.id() > 0) values.put("id", a.id());

        values.put("baseScore", a.baseScore());
        values.put("miscBonus", a.miscBonus());
        values.put("name", a.name());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "baseScore INTEGER," +
                "miscBonus INTEGER," +
                "name TEXT" +
                ")";
        database.execSQL(q);
    }

}
