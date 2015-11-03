package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Ability;
import group4.dmhelper.Actors.Race;

/**
 * Created by james.
 */
public class Races extends Database implements DAO<Race> {
    private String TABLE = "races";

    public Races(Context context) {
        super(context, "races");
        createTable();
    }

    public int create(Race r) { return super.create(TABLE, values(r)); }

    public void update(Race r) { super.update(TABLE, r, values(r)); }

    public Race retrieve(int id) {
        String[] args = new String[] {id+""};
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Race r = new Race();

        if (c.moveToFirst()) {
            do {
                r.setId(c.getInt(0));
                r.setName(c.getString(1));
            } while (c.moveToNext());
        }

        return r;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Race r) {
        ContentValues values = new ContentValues();
        if(r.id() > 0) values.put("id", r.id());
        values.put("name", r.name());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "name TEXT" +
                ")";
        database.execSQL(q);
    }
}
