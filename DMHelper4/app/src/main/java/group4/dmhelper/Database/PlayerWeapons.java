package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.PlayerWeapon;

/**
 * Created by james.
 */
public class PlayerWeapons extends Database implements DAO<PlayerWeapon> {
    private String TABLE = "playerWeapons";

    public PlayerWeapons(Context context) {
        super(context, "playerWeapons");
        createTable();
    }

    public int create(PlayerWeapon p) {
        return (int)database.insert(TABLE, null, createContentValues(p));
    }

    public void update(PlayerWeapon p) {
        database.update(TABLE, createContentValues(p), "id = ?", new String[] { p.id()+""});
    }

    public PlayerWeapon retrieve(int id) {
        String args[] = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);

        PlayerWeapon p = new PlayerWeapon();

        if (c.moveToFirst()) {
            do {
                p.setId(c.getInt(0));
                p.setPlayerId(c.getInt(1));
                p.setName(c.getString(2));
                p.setCategory(c.getString(3));
                p.setSubCategory(c.getString(4));
                p.setSpecialAbility(c.getString(5));
                p.setAura(c.getString(6));
                p.setAlignment(c.getString(7));
            } while (c.moveToNext());
        }

        return p;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues createContentValues(PlayerWeapon p) {
        ContentValues values = new ContentValues();
        if(p.id() > 0) values.put("id", p.id());
        values.put("playerId", p.playerId());
        values.put("name", p.name());
        values.put("category", p.category());
        values.put("subCategory", p.subCategory());
        values.put("specialAbility", p.specialAbility());
        values.put("aura", p.aura());
        values.put("alignment", p.alignment());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId integer," +
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
