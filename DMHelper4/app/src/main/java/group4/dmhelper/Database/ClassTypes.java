package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.ClassType;

/**
 * Created by james.
 */
public class ClassTypes extends Database implements DAO<ClassType> {
    private String TABLE = "classTypes";

    public ClassTypes(Context context) {
        super(context, "classTypes");
        createTable();
    }

    public int create(ClassType c) { return super.create(TABLE, values(c)); }

    public void update(ClassType c) { super.update(TABLE, c, values(c)); }

    public ClassType retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        ClassType ct = new ClassType();

        if (c.moveToFirst()) {
            do {
                ct.setId(c.getInt(0));
                ct.setName(c.getString(1));
                ct.setLevel(c.getInt(2));
                ct.setAttack(c.getInt(3));
                ct.setSpecial(c.getInt(4));
                ct.setPlayerId(c.getInt(5));
            } while (c.moveToNext());
        }

        return ct;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "name TEXT," +
                "level INTEGER," +
                "attack INTEGER," +
                "special INTEGER," +
                "playerId INTEGER" +
                ")";
        database.execSQL(q);
    }

    private ContentValues values(ClassType c) {
        ContentValues v = new ContentValues();
        if(c.id() > 0) v.put("id", c.id());

        v.put("name", c.name());
        v.put("level", c.level());
        v.put("attack", c.attack());
        v.put("special", c.special());
        v.put("playerId", c.playerId());
        return v;
    }

}
