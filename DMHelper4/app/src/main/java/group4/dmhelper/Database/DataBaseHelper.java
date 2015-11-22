package group4.dmhelper.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kyle on 9/22/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/group4.dmhelper/databases/";
    private static String DB_NAME = "dnd35";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME+".db", null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            Log.e("DB", "DB Already exists, not going to recreate.");
        }else{
            Log.e("DB", "DB doesn't exist, creating it now.");
            SQLiteDatabase db = this.getReadableDatabase();
            if (db.isOpen()){
                db.close();
            }
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME+".db";
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){}
        if(checkDB != null) checkDB.close();
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME + ".mp3");
        String outFileName = DB_PATH + DB_NAME + ".db";
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[19456];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME+".db";
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null) myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor performRawQuery(String queryInput, String[] SelectionArgs) {
        Cursor query;
        query = myDataBase.rawQuery(queryInput, SelectionArgs);
        return query;
    }

    public Cursor generalQuery(String table) {
        Cursor query;
        String[] SelectionArgs = new String[]{};
        String queryText = "Select * from "+table;
        query = myDataBase.rawQuery(queryText, SelectionArgs);
        return query;
    }

    public Cursor generalQuery(String table, int id) {
        Cursor query;
        String[] SelectionArgs = new String[]{""+id};
        String queryText = "Select * from "+table+" where _id = ?";
        query = myDataBase.rawQuery(queryText, SelectionArgs);
        return query;
    }

    public String[] retrieveClassByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from class where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveClassTableByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from class_table where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveDomainByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from domain where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveEquipmentByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from equipment where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveFeatByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from feat where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveItemByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from item where _id = ?", SelectionArgs);
        Log.d("num44","query'd - " + query.getColumnCount());
        return getCursorData(query);
    }

    public String[] retrieveMonsterByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from monster where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrievePowerByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from power where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveSkillByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from skill where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public String[] retrieveSpellByID(int id) {
        this.openDataBase();
        String[] SelectionArgs = new String[]{""+id};
        Cursor query = myDataBase.rawQuery("Select * from spell where _id = ?", SelectionArgs);
        return getCursorData(query);
    }

    public Cursor retrieveAllSkills() {
        this.openDataBase();
        String[] SelectionArgs = new String[]{};
        Cursor query = myDataBase.rawQuery("Select _id, name from skill", SelectionArgs);
        return query;
    }

    private String[] getCursorData(Cursor c) {
        String[] data = new String[c.getColumnCount()-2];
        if (c.moveToFirst()) {
            for (int i = 1; i < c.getColumnCount()-1; i++) {
                data[i-1] = c.getString(i);
            }
        }
        return data;
    }
}
