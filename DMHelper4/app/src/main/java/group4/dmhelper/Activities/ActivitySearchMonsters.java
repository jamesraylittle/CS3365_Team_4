package group4.dmhelper.Activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/16/2015.
 */
public class ActivitySearchMonsters extends Activity {

    DataBaseHelper myDbHelper;
    EditText search;
    ListView lvUsers;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();
    private String[] arraySize, arrayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_seach_monster);

        this.arraySize = new String[] {
                "Any", "Colossal", "Colossal+", "Diminutive", "Fine", "Gargantuan", "Huge",
                "Large", "Medium", "Small", "Tiny"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner_seach_monster_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySize);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        s.setAdapter(adapter);

        this.arrayType = new String[] {
                "Any", "Aberration", "Animal", "Construct", "Dragon", "Elemental", "Fey", "Giant", "Humanoid",
                "Magical Beast", "Monstrous Humanoid", "Ooze", "Outsider", "Plant", "Undead", "Vermin"
        };
        Spinner d = (Spinner) findViewById(R.id.spinner_search_monster_size);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayType);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        d.setAdapter(adapter2);

        lvUsers = (ListView)findViewById(R.id.listView_search_monster);

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

        search = (EditText)findViewById(R.id.editTxt_search_monster_name);
    }

    public void doSearchMonsters(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch(search.getText().toString());
    }

    private void performSearch(String input) {
        myDbHelper.openDataBase();

        Cursor query = myDbHelper.performContainsSearch("name", "monster", "name", "name", "ASC", input);

        if (query.getCount() <= 0) { // If no results, clear the list
            listUsers.clear();
            Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
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
