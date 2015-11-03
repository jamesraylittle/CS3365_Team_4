package group4.dmhelper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import group4.dmhelper.Actors.Model;


/**
 * Created by james.
 */


abstract class Database extends SQLiteOpenHelper {

    private String DB_NAME = "";
    protected SQLiteDatabase database;
    protected final Context context;

    public Database(Context context, String DB_NAME) {

        super(context, DB_NAME + ".db", null, 1);
        this.context = context;
        this.DB_NAME = DB_NAME + ".db";
        database = context.openOrCreateDatabase(this.DB_NAME, context.MODE_APPEND, null);

    }

    @Override
    public synchronized void close() {
        if(database != null) database.close();
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public void onCreate(SQLiteDatabase db) { }

    public int create(String table, ContentValues values) {
        return (int)database.insert(table, null, values);
    }

    public void update(String table, Model m, ContentValues values) {
        database.update(table, values, "id = ?", new String[]{m.id() + ""});
    }


    public int count(String table) {
        Cursor c = database.query(table, null, null, null, null, null, null);
        return c.getCount();
    }

    public void delete(int id, String table) {
        database.delete(table, "id = ?", new String[] { id+"" });
    }

    /**
     * doesExists checks to see if the database exists or not
     * this is a protected method.
     * @return true if it does, false otherwise.
     */


}
