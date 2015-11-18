package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.PlayerAbility;
import group4.dmhelper.Actors.Skill;

/**
 * Created by james.
 */
public class PlayerAbilities extends Database implements DAO<PlayerAbility> {
    private String TABLE = "playerAbilities";

    public PlayerAbilities(Context context) {
        super(context, "playerAbilities");
        createTable();
    }

    public int create(PlayerAbility p) { return super.create(TABLE, values(p)); }

    public void update(PlayerAbility p) { super.update(TABLE, p, values(p)); }

    public PlayerAbility retrieve(int id) {
        String[] args = new String[] {id + ""};
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        PlayerAbility p = new PlayerAbility();

        if (c.moveToFirst()) {
            do {
                p.setId(c.getInt(0));
                p.setPlayerId(c.getInt(1));
                p.setStrengthAbility(c.getInt(2));
                p.setDexAbility(c.getInt(3));
                p.setConstAbility(c.getInt(4));
                p.setIntelAbility(c.getInt(5));
                p.setWisdomAbility(c.getInt(6));
                p.setCrismaAbility(c.getInt(7));
            } while (c.moveToNext());
        }
        return p;
    }

    public ArrayList<PlayerAbility> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<PlayerAbility> list = new ArrayList<PlayerAbility>();
        if(c.moveToFirst()) {
            do {
                PlayerAbility s = new PlayerAbility(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7));
                list.add(s);
            } while(c.moveToNext());
        }
        return list;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(PlayerAbility p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());

        values.put("playerId", p.getPlayerId());
        values.put("strengthAbility", p.getStrengthAbility());
        values.put("dexAbility", p.getDexAbility());
        values.put("constAbility", p.getConstAbility());
        values.put("intelAbility", p.getIntelAbility());
        values.put("wisdomAbility", p.getWisdomAbility());
        values.put("crismaAbility", p.getCrismaAbility());

        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "strengthAbility INTEGER," +
                "dexAbility INTEGER," +
                "constAbility INTEGER," +
                "intelAbility INTEGER," +
                "wisdomAbility INTEGER," +
                "crismaAbility INTEGER" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }
}
