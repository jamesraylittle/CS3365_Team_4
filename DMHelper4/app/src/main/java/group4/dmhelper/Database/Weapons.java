package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Weapon;

/**
 * Created by james.
 */
public class Weapons extends Database implements DAO<Weapon> {
    private String TABLE = "Weapons";

    public Weapons(Context context) {
        super(context, "Weapons");
        createTable();
    }

    public int create(Weapon p) { return super.create(TABLE, values(p)); }

    public void update(Weapon p) { super.update(TABLE, p, values(p)); }

    public Weapon retrieve(int id) {    // TODO: 11/4/2015 @james is this a player id?
        String args[] = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);

        Weapon p = new Weapon(id);

        if (c.moveToFirst()) {
            do {
                p.setId(c.getInt(0));
                p.setPlayerId(c.getInt(1));
                p.setWeaponId(c.getInt(2));
            } while (c.moveToNext());
        }

        return p;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Weapon p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());
        values.put("playerId", p.getPlayerId());
        values.put("weaponId", p.getWeaponId());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "weaponId INTEGER," +
                ")";
        database.execSQL(q);
    }
}
