package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Player;

/**
 * Created by james
 */
public class Players extends Database implements DAO<Player> {
    private String TABLE = "players";

    public Players(Context context) {
        super(context, "players");
        createTable();
    }

    public int create(Player p) { return super.create(TABLE, values(p)); }

    public void update(Player p) { super.update(TABLE, p, values(p)); }

    public Player retrieve(int id) {
        String [] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Player from = new Player();

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setGender(c.getString(1));
                from.setSize(c.getInt(2));
                from.setAlignment(c.getString(3));
                from.setWeight(c.getInt(4));
                from.setReligion(c.getString(5));
                from.setPlayerAbilitiyId(c.getInt(6));
                from.setEquippedItemsId(c.getInt(7));
                from.setSkillsId(c.getInt(8));
                from.setPlayerWeaponsId(c.getInt(9));
                from.setRaceId(c.getInt(10));
                from.setName(c.getString(11));
            } while (c.moveToNext());
        }

       return from;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE);  }

    private ContentValues values(Player p) {
        ContentValues values = new ContentValues();
        if(p.id() > 0) values.put("id", p.id());

        values.put("gender", p.gender());
        values.put("size", p.size());
        values.put("alignment", p.alignment());
        values.put("weight", p.weight());
        values.put("religion", p.religion());
        values.put("playerAbilityId", p.playerAbilitiyId());
        values.put("equippedItemsId", p.equippedItemsId());
        values.put("skillsId", p.skillsId());
        values.put("playerWeaponsId", p.playerWeaponsId());
        values.put("raceId", p.raceId());
        values.put("name", p.name());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "gender TEXT," +
                "size integer," +
                "alignment TEXT," +
                "weight integer," +
                "religion TEXT," +
                "playerAbilityId integer," +
                "equippedItemsId integer," +
                "skillsId integer," +
                "playerWeaponsId integer," +
                "raceId integer," +
                "name TEXT" +
                ")";
        database.execSQL(q);
    }
}
