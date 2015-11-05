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
                ct.setClassId(c.getInt(1));
                ct.setClass_tableId(c.getInt(2));
                ct.setPlayerId(c.getInt(3));
            } while (c.moveToNext());
        }

        return ct;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "class INTEGER," +
                "class_table INTEGER," +
                "player INTEGER," +
                ")";
        database.execSQL(q);
    }

    private ContentValues values(ClassType c) {
        ContentValues v = new ContentValues();
        if(c.getId() > 0) v.put("id", c.getId());

        v.put("class", c.getClassId());
        v.put("class_table", c.getClass_tableId());
        v.put("player", c.getPlayerId());
        return v;
    }

}
