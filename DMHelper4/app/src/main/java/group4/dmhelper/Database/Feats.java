package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import group4.dmhelper.Actors.Feat;

/**
 * Created by james
 */
public class Feats extends Database implements DAO<Feat> {

    private String TABLE = "feat";

    public Feats(Context context) {
        super(context, "feat");
        createTable();
    }

    public int create(Feat f) { return super.create(TABLE, values(f)); }

    public void update(Feat f) { super.update(TABLE, f, values(f)); }


    public Feat retrieve(int id) {
        String[] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Feat from = new Feat();

        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setPlayerId(c.getInt(1));
                from.setFeatId(c.getInt(2));
                from.setFeatName(c.getString(3));
            } while (c.moveToNext());
        }

        return from;
    }

    public ArrayList<Feat> getAllByPlayerId(int playerId) {
        String args[] = new String[] { playerId + ""};
        Cursor c = database.query(TABLE, null, "playerId = ?", args, null, null, null);

        ArrayList<Feat> list = new ArrayList();
        if(c.moveToFirst()) {
            do {
                Feat s = new Feat(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
                list.add(s);
            } while(c.moveToNext());
        }
        return list;
    }


    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE); }

    private ContentValues values(Feat feat) {
        ContentValues values = new ContentValues();
        if(feat.getId() > 0) values.put("id", feat.getId());
        values.put("playerId", feat.getPlayerId());
        values.put("featId", feat.getFeatId());
        values.put("featName", feat.getFeatName());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "playerId INTEGER," +
                "featId INTEGER," +
                "featName TEXT" +
                ")";
        database.execSQL(q);
    }


    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }
    
}
