package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Feat;

/**
 * Created by james
 */
public class Actors extends Database implements DAO<Actor> {
    private String TABLE = "actors";

    public Actors(Context context) {
        super(context, "actors");
        createTable();
    }

    public int create(Actor p) { return super.create(TABLE, values(p)); }

    public void update(Actor p) { super.update(TABLE, p, values(p)); }

    public Actor retrieve(int id) {
        String [] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Actor from = new Actor(id);

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setGender(c.getString(1));
                from.setSize(c.getInt(2));
                from.setAlignment(c.getString(3));
                from.setWeight(c.getInt(4));
                from.setReligion(c.getString(5));
                //from.setActorAbilityId(c.getInt(6)); This is more than one I believe.
                //from.setEquippedItemsId(c.getInt(7));
                //from.setSkillsId(c.getInt(8));
                //from.setActorWeaponsId(c.getInt(9));
                //from.setRaceId(c.getString(10));
                from.setRace(c.getString(10));  //Temporary
                from.setName(c.getString(11));
            } while (c.moveToNext());
        }

       return from;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE);  }

    private ContentValues values(Actor p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());

        values.put("gender", p.getGender());
        values.put("size", p.getSize());
        values.put("alignment", p.getAlignment());
        values.put("weight", p.getWeight());
        values.put("religion", p.getReligion());
        //values.put("actorAbilityId", p.getActorAbilityId());
        //values.put("equippedItemsId", p.getEquippedItemsId());
        //values.put("skillsId", p.getSkillsId());
        //values.put("actorWeaponsId", p.getActorWeaponsId());
        //values.put("raceId", p.getRaceId());
        values.put("race", p.getRace());  //Temporary
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
                //"actorAbilityId integer," +
                //"equippedItemsId integer," +
                //"skillsId integer," +
                //"actorWeaponsId integer," +
                //"raceId integer," +
                "race TEXT," +
                "name TEXT" +
                ")";
        database.execSQL(q);
    }
}
