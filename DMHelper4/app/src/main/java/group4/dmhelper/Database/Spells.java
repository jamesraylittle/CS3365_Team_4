package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Spell;

/**
 * Created by james
 */
public class Spells extends Database implements DAO<Spell> {

    private String TABLE = "spells";

    public Spells(Context context) {
        super(context, "spells");
        createTable();
    }

    public int create(Spell spell) { return super.create(TABLE, values(spell)); }

    public void update(Spell spell) { super.update(TABLE, spell, values(spell)); }


    public Spell retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Spell from = new Spell();

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setPlayerId(c.getInt(1));
                from.setSpellId(c.getInt(2));
            } while (c.moveToNext());
        }

        return from;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Spell spell) {
        ContentValues values = new ContentValues();
        if(spell.getId() > 0) values.put("id", spell.getId());

        values.put("Player Id", spell.getPlayerId());
        values.put("Spell Id", spell.getSpellId());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "Player Id INTEGER," +
                "Spell Id INTEGER," +
                ")";
        database.execSQL(q);
    }


}
