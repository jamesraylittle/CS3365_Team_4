package group4.dmhelper.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
 * Created by Kyle on 10/24/2015.
 */
public class ActivitySearchSpells extends Activity {

    DataBaseHelper myDbHelper;
    EditText nameInput;
    Spinner schoolInput, subInput;
    ListView searchResults;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();
    List<String> feed = new ArrayList<>();
    private String[] arraySchool, arraySub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_spell);
        myDbHelper = new DataBaseHelper(this);
        initializeWidgets();
        feed.add("In spell search");
    }

    @Override
     protected void onPause() {
        super.onPause();
        try {
            myDbHelper.close();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            myDbHelper.close();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("feedData", feed.toArray(new String[feed.size()]));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    private void initializeWidgets() {
        //Spinners
        this.arraySchool = new String[] {
                "Any", "Calling", "Charm", "Compulsion", "Creation", "Creation or Calling", "Creation, Healing",
                "Death, Evil", "Figment", "Figment, Glamer", "Glamer", "Healing", "None", "Pattern", "Phantasm",
                "Scrying", "Shadow", "Summoning", "Teleportation"
        };
        subInput = (Spinner) findViewById(R.id.spinner_search_spell_subschool);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySchool);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        subInput.setAdapter(adapter);

        this.arraySub = new String[] {
                "Any", "Abjuration", "Conjuration", "Conjuration, Necromancy", "Divination", "Divination, Enchantment",
                "Enchantment", "Enchantment [Mind-Affecting]", "Evocation", "Illusion", "Necromancy",
                "Necromancy, Illusion (Figment)", "Transmutation", "Universal"
        };
        schoolInput = (Spinner) findViewById(R.id.spinner_search_spell_school);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySub);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        schoolInput.setAdapter(adapter2);

        //EditText
        nameInput = (EditText)findViewById(R.id.editTxt_search_spell_name);

        //ListView
        searchResults = (ListView)findViewById(R.id.listView_search_spell);
    }

    public void doSearchSpells(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch();
    }

    private void performSearch() {
        String name = nameInput.getText().toString();
        String school = schoolInput.getSelectedItem().toString();
        String sub = subInput.getSelectedItem().toString();
        String defaultStart = "select name from spell where name like ? ";
        myDbHelper.openDataBase();
        if (!sub.equals("Any") && !school.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and subschool = ? and school = ? order by name asc",
                            new String[]{"%" + name + "%", sub, school}));
        }
        else if (!sub.equals("Any") && school.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and subschool = ? order by name asc",
                            new String[]{"%" + name + "%", sub}));
        }
        else if (sub.equals("Any") && !school.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and school = ? order by name asc",
                            new String[]{"%" + name + "%", school}));
        }
        else {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "order by name asc",
                            new String[]{"%" + name + "%"}));
        }
        myDbHelper.close();
    }

    private void populateList(Cursor query) {
        if (query.getCount() <= 0) { // If no results, clear the list
            listUsers.clear();
            Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            searchResults.requestFocus();
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
            searchResults.setAdapter(adapter);
        }
    }
}