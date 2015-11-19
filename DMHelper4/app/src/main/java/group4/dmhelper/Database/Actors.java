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
                        c.getString(2),
                        c.getInt(3),
                        c.getString(4),
                        c.getInt(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getInt(9),
                        c.getInt(10),
                        c.getInt(11),
                        c.getInt(12),
                        c.getInt(13),
                        c.getInt(14),
                        c.getInt(15),
                        c.getInt(16),
                        c.getString(17),
                        c.getInt(18),
                        c.getString(19),
                        c.getInt(20),
                        c.getInt(21)
                );
            } while (c.moveToNext());
        }

       return from;
    }

    public ArrayList<Actor> getAllActorsByGameId(int gameId) {
        String [] args = new String[] { gameId+"" };
        Cursor c = database.query(TABLE, null, "gameId = ?", args, null, null, null);

        ArrayList<Actor> list = new ArrayList<Actor>();
        if(c.moveToFirst()) {
            do {
                Actor a = new Actor(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getString(4),
                        c.getInt(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getInt(9),
                        c.getInt(10),
                        c.getInt(11),
                        c.getInt(12),
                        c.getInt(13),
                        c.getInt(14),
                        c.getInt(15),
                        c.getInt(16),
                        c.getString(17),
                        c.getInt(18),
                        c.getString(19),
                        c.getInt(20),
                        c.getInt(21)
                );
                list.add(a);
            } while (c.moveToNext());
        }
        return list;
    }

    public ArrayList<Actor> getAllActors() {
        Cursor c = database.query(TABLE, null, null, null, null, null, null);

        ArrayList<Actor> list = new ArrayList<Actor>();
        if(c.moveToFirst()) {
            do {
                Actor a = new Actor(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getString(4),
                        c.getInt(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getInt(9),
                        c.getInt(10),
                        c.getInt(11),
                        c.getInt(12),
                        c.getInt(13),
                        c.getInt(14),
                        c.getInt(15),
                        c.getInt(16),
                        c.getString(17),
                        c.getInt(18),
                        c.getString(19),
                        c.getInt(20),
                        c.getInt(21)
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

        values.put("name", p.getName());
        values.put("gender", p.getGender());
        values.put("size", p.getSize());
        values.put("alignment", p.getAlignment());
        values.put("weight", p.getWeight());
        values.put("height", p.getHeight());
        values.put("religion", p.getReligion());
        values.put("race", p.getRace());
        values.put("speed", p.getSpeed());
        values.put("initiativeMod", p.getInitiativeMod());
        values.put("initiative", p.getInitiative());
        values.put("reflexMod", p.getReflexMod());
        values.put("willMod", p.getWillMod());
        values.put("fortMod", p.getFortMod());
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
                "name TEXT," +
                "gender TEXT," +
                "size integer," +
                "alignment TEXT," +
                "weight integer," +
                "height TEXT," +
                "religion TEXT," +
                "race TEXT," +
                "speed integer," +
                "initiativeMod integer," +
                "initiative integer," +
                "reflexMod integer," +
                "willMod integer," +
                "fortMod integer," +
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

/*
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.religion = religion;
        this.race = race;
        this.speed = speed;
        this.initiativeMod = initiativeMod;
        this.initiative = initiative;
        this.reflexMod = reflexMod;
        this.willMod = willMod;
        this.fortMod = fortMod;
        this.isMonster = isMonster;
        this.inGame = inGame;
        this.imageFile = imageFile;
        this.gameId = gameId;
        this.playerName = playerName;
        this.XP = XP;
        this.health = health;
 */