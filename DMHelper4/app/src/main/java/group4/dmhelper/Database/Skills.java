package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Skill;

/**
 * Created by james
 *
 * This class needs to get the skill ids somehow
 */
public class Skills extends Database implements DAO<Skill> {
    private String TABLE = "abilities";
    private int skillId;

    public Skills(Context context) {
        super(context, "abilities");
        createTable();
    }

    public int create(Skill a) { return super.create(TABLE, values(a)); }

    public void update(Skill a) { super.update(TABLE, a, values(a));
    }

    public Skill retrieve(int id) {
        String[] args = new String[]{id + ""};
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Skill a = new Skill(id, skillId);

        if (c.moveToFirst()) {
            do {
                a.setId(c.getInt(0));
                a.setBaseScore(c.getInt(1));
                a.setMiscBonus(c.getInt(2));
                a.setName(c.getString(3));
                a.setPlayerId(c.getInt(4));
                a.setSkillId(c.getInt(5));
            } while (c.moveToNext());
        }

        return a;
    }

    public ArrayList<Skill> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<Skill> list = new ArrayList<Skill>();
        if(c.moveToFirst()) {
            do {
                Skill s = new Skill(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3), c.getInt(4), c.getInt(5));
                list.add(s);
            } while(c.moveToNext());
        }
        return list;
    }

    public void delete(int id) {
        super.delete(id, TABLE);
    }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Skill a) {
        ContentValues values = new ContentValues();
        if(a.getId() > 0) values.put("id", a.getId());

        values.put("baseScore", a.getBaseScore());
        values.put("miscBonus", a.getMiscBonus());
        values.put("name", a.getName());
        values.put("playerId", a.getSkillId());
        values.put("skillId", a.getSkillId());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "baseScore INTEGER," +
                "miscBonus INTEGER," +
                "name TEXT," +
                "playerId INTEGER," +
                "skillId INTEGER" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }

}
