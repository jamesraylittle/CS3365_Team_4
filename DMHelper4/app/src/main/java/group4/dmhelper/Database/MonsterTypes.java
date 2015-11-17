package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.MonsterType;

/**
 * Created by Daniel.
 */
public class MonsterTypes extends Database implements DAO<MonsterType> {
    private String TABLE = "MonsterTypes";

    public MonsterTypes(Context context) {
        super(context, "MonsterTypes");
        createTable();
    }

    public int create(MonsterType c) { return super.create(TABLE, values(c)); }

    public void update(MonsterType c) { super.update(TABLE, c, values(c)); }

    public MonsterType retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        MonsterType ct = new MonsterType();

        if (c.moveToFirst()) {
            do {
                ct.setId(c.getInt(0));
                ct.setPlayerId(c.getInt(2));
                ct.setMonsterId(c.getInt(3));
                ct.setHD(c.getInt(4));
            } while (c.moveToNext());
        }

        return ct;
    }

    public ArrayList<MonsterType> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<MonsterType> list = new ArrayList<MonsterType>();
        MonsterType oC = null;
        if(c.moveToFirst()) {
            do {
                oC = new MonsterType(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3));
                list.add(oC);
            } while(c.moveToNext());
        }
        return list;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "monsterId INTEGER," +
                "hd INTEGER" +
                ")";
        database.execSQL(q);
    }

    private ContentValues values(MonsterType c) {
        ContentValues v = new ContentValues();
        if(c.getId() > 0) v.put("id", c.getId());

        v.put("playerId", c.getPlayerId());
        v.put("monsterId", c.getMonsterId());
        v.put("hd", c.getHD());
        return v;
    }

}
