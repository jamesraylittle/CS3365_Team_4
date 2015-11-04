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
        Player from = new Player(id);

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setGender(c.getString(1));
                from.setSize(c.getInt(2));
                from.setAlignment(c.getString(3));
                from.setWeight(c.getInt(4));
                from.setReligion(c.getString(5));
                //from.setPlayerAbilityId(c.getInt(6)); This is more than one I believe.
                //from.setEquippedItemsId(c.getInt(7));
                //from.setSkillsId(c.getInt(8));
                //from.setPlayerWeaponsId(c.getInt(9));
                //from.setRaceId(c.getString(10));
                from.setRace(c.getString(10));  //Temporary
                from.setName(c.getString(11));
            } while (c.moveToNext());
        }

       return from;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE);  }

    private ContentValues values(Player p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());

        values.put("gender", p.getGender());
        values.put("size", p.getSize());
        values.put("alignment", p.getAlignment());
        values.put("weight", p.getWeight());
        values.put("religion", p.getReligion());
        //values.put("playerAbilityId", p.getPlayerAbilityId());
        //values.put("equippedItemsId", p.getEquippedItemsId());
        //values.put("skillsId", p.getSkillsId());
        //values.put("playerWeaponsId", p.getPlayerWeaponsId());
        //values.put("raceId", p.getRaceId());
        values.put("raceId", p.getRace());  //Temporary
        values.put("name", p.getName());
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
                //"playerAbilityId integer," +
                //"equippedItemsId integer," +
                //"skillsId integer," +
                //"playerWeaponsId integer," +
                //"raceId integer," +
                "raceId TEXT," +
                "name TEXT" +
                ")";
        database.execSQL(q);
    }
}
