package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.ClassType;
import group4.dmhelper.Actors.Feat;

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

    public ArrayList<ClassType> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<ClassType> list = new ArrayList<ClassType>();
        ClassType oC = null;
        if(c.moveToFirst()) {
            do {
                oC = new ClassType(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3));
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
