package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Player;
import group4.dmhelper.GameManager;

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
        Actor from = null;
        if (c.moveToFirst()) {
            do {
                from = new Actor(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getInt(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getInt(8), //1
                        c.getInt(9),
                        c.getString(10),
                        c.getInt(11),
                        c.getString(12),
                        c.getInt(13),
                        c.getInt(14));
            } while (c.moveToNext());
        }

       return from;
    }

    public ArrayList<Actor> getAllActors() {
        Cursor c = database.query(TABLE, null, null, null, null, null, null);

        ArrayList<Actor> list = new ArrayList<Actor>();
        if(c.moveToFirst()) {
            do {
                Actor a = new Actor(
                       c.getInt(0),
                       c.getString(1),
                       c.getInt(2),
                       c.getString(3),
                       c.getInt(4),
                       c.getString(5),
                       c.getString(6),
                       c.getString(7),
                       c.getInt(8), //1
                       c.getInt(9),
                       c.getString(10),
                       c.getInt(11),
                       c.getString(12),
                       c.getInt(13),
                       c.getInt(14)
                );
                list.add(a);
            } while (c.moveToNext());
        }
        return list;
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
        values.put("race", p.getRace());
        values.put("name", p.getName());
        values.put("isMonster", p.getIsMonster());
        values.put("inGame", p.getInGame());
        values.put("imageFile", p.getImageFile());
        values.put("gameId", p.getGameId());

        values.put("playerName", p.getPlayerName());
        values.put("XP", p.getXP());
        values.put("health", p.getHealth());
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
                "race TEXT," +
                "name TEXT," +
                "isMonster integer," +
                "inGame integer," +
                "imageFile TEXT," +
                "gameId integer," +
                "playerName TEXT," +
                "XP integer," +
                "health integer" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }
}