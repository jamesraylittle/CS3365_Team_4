package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Equipment;
import group4.dmhelper.Actors.Feat;

/**
 * Created by james.
 */
public class Equipments extends Database implements DAO<Equipment> {
    private String TABLE = "Equipments";

    public Equipments(Context context) {
        super(context, "Equipments");
        createTable();
    }

    public int create(Equipment p) { return super.create(TABLE, values(p)); }

    public void update(Equipment p) { super.update(TABLE, p, values(p)); }

    public Equipment retrieve(int id) {    // TODO: 11/4/2015 @james is this a player id?
        String args[] = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);

        Equipment p = new Equipment();

        if (c.moveToFirst()) {
            do {
                p.setId(c.getInt(0));
                p.setPlayerId(c.getInt(1));
                p.setEquipmentId(c.getInt(2));
            } while (c.moveToNext());
        }

        return p;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Equipment p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());
        values.put("playerId", p.getPlayerId());
        values.put("weaponId", p.getEquipmentId());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "weaponId INTEGER" +
                ")";
        database.execSQL(q);
    }

    public ArrayList<Equipment> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + "" };
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<Equipment> list = new ArrayList<Equipment>();

        if(c.moveToFirst()) {
            do {
                Equipment i = new Equipment(c.getInt(0),c.getInt(1) ,c.getInt(2));
                list.add(i);
            } while (c.moveToNext());
        }

        return list;
    }

}
