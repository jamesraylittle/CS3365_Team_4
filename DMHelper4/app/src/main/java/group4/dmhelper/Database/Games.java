package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import group4.dmhelper.Actors.Game;

/**
 * Created by Daniel
 */
public class Games extends Database implements DAO<Game> {
    private String TABLE = "Games";

    public Games(Context context) {
        super(context, "Games");
        createTable();
    }

    public int create(Game p) { return super.create(TABLE, values(p)); }

    public void update(Game p) { super.update(TABLE, p, values(p)); }

    public Game retrieve(int id) {
        String [] args = new String[] { id+"" };
        Cursor c = database.query(TABLE, null, "id = ?", args, null, null, null);
        Game from = new Game(id);
        if (c.moveToFirst()) {
            do {
                from.setId(c.getInt(0));
                from.setName(c.getString(1));
                from.setNumPlayers(c.getInt(2));
            } while (c.moveToNext());
        }

       return from;
    }

    public void delete(int id) { super.delete(id, TABLE); }

    public int count() { return super.count(TABLE);  }

    private ContentValues values(Game p) {
        ContentValues values = new ContentValues();
        if(p.getId() > 0) values.put("id", p.getId());
        values.put("name", p.getName());
        values.put("numPlayers", p.getNumPlayers());
        return values;
    }

    private void createTable() {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE+" (" +
                "id integer primary key AUTOINCREMENT," +
                "name TEXT," +
                "numPlayers integer" +
                ")";
        database.execSQL(q);
    }

    public void dropTable(){
        database.execSQL("DROP TABLE " + TABLE);
    }

}