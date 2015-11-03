package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.PlayerAbility;

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
                p.setStrengthAbilityId(c.getInt(1));
                p.setDexAbilityId(c.getInt(2));
                p.setConstAbilityId(c.getInt(3));
                p.setIntelAbilityId(c.getInt(4));
                p.setWisdomAbilityId(c.getInt(5));
                p.setCrismaAbilityId(c.getInt(6));
            } while (c.moveToNext());
        }
        return p;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(PlayerAbility p) {
        ContentValues values = new ContentValues();
        if(p.id() > 0) values.put("id", p.id());

        values.put("playerId", p.playerId());
        values.put("strengthAbilityId", p.strengthAbilityId());
        values.put("dexAbilityId", p.dexAbilityId());
        values.put("constAbilityId", p.constAbilityId());
        values.put("intelAbilityId", p.intelAbilityId());
        values.put("wisdomAbilityId", p.wisdomAbilityId());
        values.put("crismaAbilityId", p.crismaAbilityId());

        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "strengthAbilityId INTEGER," +
                "dexAbilityId INTEGER," +
                "constAbilityId INTEGER," +
                "intelAbilityId INTEGER," +
                "wisdomAbilityId INTEGER," +
                "crismaAbilityId INTEGER" +
                ")";
        database.execSQL(q);
    }

}
