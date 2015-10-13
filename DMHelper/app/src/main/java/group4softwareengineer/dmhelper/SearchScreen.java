package group4softwareengineer.dmhelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/13/2015.
 */
public class SearchScreen extends Activity {

    DataBaseHelper myDbHelper;
    SQLiteDatabase db;
    EditText search;
    ListView lvUsers;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lvUsers = (ListView)findViewById(R.id.lvUsers);

        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }

        search = (EditText)findViewById(R.id.editText);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    performSearch(search.getText().toString());
                    return false;
                }
                return false;
            }
        });
    }

    private void performSearch(String input) {
        myDbHelper.openDataBase();

        Cursor query = myDbHelper.performContainsSearch("name", "monster", "name", "name", "ASC", input);

        if (query.getCount() <= 0) { // If no results, clear the list
            listUsers.clear();
            return;
        }
        else { // Otherwise, populate the list with the results
            listUsers.clear();
            Toast.makeText(getApplicationContext(), query.getCount() + " results found.", Toast.LENGTH_LONG).show();
            query.moveToFirst();
            do { listUsers.add(query.getString(0)); }
            while (query.moveToNext());
            query.close();
            adapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.list_layout, R.id.list_text,
                    listUsers);
            lvUsers.setAdapter(adapter);
        }

        myDbHelper.close();
    }
}
