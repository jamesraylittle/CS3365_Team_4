package group4softwareengineer.dmhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 9/22/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/group4softwareengineer.dmhelper/databases/";
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

    private void copyDataBase() throws IOException{
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

    public Cursor performStartsWithSearch(String columns, String tables, String whereColumn, String orderColumn, String orderType, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {input+"%"};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" "+orderType, SelectionArgs);

        return query;
    }

    public Cursor performStartsWithSearch(String columns, String tables, String whereColumn, String orderColumn, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {input+"%"};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" ASC", SelectionArgs);

        return query;
    }

    public Cursor performEndsWithSearch(String columns, String tables, String whereColumn, String orderColumn, String orderType, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {"%"+input};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" "+orderType, SelectionArgs);

        return query;
    }

    public Cursor performEndsWithSearch(String columns, String tables, String whereColumn, String orderColumn, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {"%"+input};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" ASC", SelectionArgs);

        return query;
    }

    public Cursor performContainsSearch(String columns, String tables, String whereColumn, String orderColumn, String orderType, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {"%"+input+"%"};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" "+orderType, SelectionArgs);

        return query;
    }

    public Cursor performContainsSearch(String columns, String tables, String whereColumn, String orderColumn, String input) {
        String[] SelectionArgs;
        Cursor query;

        SelectionArgs = new String[] {"%"+input+"%"};

        query = myDataBase.rawQuery("SELECT "+columns+" FROM "+tables+" WHERE "+whereColumn+" LIKE ? ORDER BY "+orderColumn+" ASC", SelectionArgs);

        return query;
    }

}
