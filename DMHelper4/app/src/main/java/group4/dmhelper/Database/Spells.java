package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Skill;
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
                from.setSpellName(c.getString(3));
            } while (c.moveToNext());
        }

        return from;
    }

    public ArrayList<Spell> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<Spell> list = new ArrayList<Spell>();
        if(c.moveToFirst()) {
            do {
                Spell s = new Spell(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
                list.add(s);
            } while(c.moveToNext());
        }
        return list;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Spell spell) {
        ContentValues values = new ContentValues();
        if(spell.getId() > 0) values.put("id", spell.getId());

        values.put("playerId", spell.getPlayerId());
        values.put("spellId", spell.getSpellId());
        values.put("spellName", spell.getSpellName());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "spellId INTEGER," +
                "spellName TEXT" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }

}
