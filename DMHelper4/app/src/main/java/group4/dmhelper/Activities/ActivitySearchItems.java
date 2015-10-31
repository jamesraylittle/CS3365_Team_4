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
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/16/2015.
 */
public class ActivitySearchItems extends Activity {

    DataBaseHelper myDbHelper;
    EditText nameInput;
    Spinner catInput, subInput, specialInput;
    ListView searchResults;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();
    private String[] arrayCat, arraySub, arraySpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_item);
        myDbHelper = new DataBaseHelper(this);
        initializeWidgets();
        FragmentFeed.feedItems.add("testItems");
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
        /////////////category/////////////
        this.arrayCat = new String[] {
                "Any", "Armor", "Armor, Shield", "Artifact", "Cursed", "Oil", "Potion", "Potion, Oil", "Psicrown",
                "Ring", "Rod", "Scroll", "Shield", "Staff", "Universal Items", "Wand", "Weapon", "Wondrous"
        };
        catInput = (Spinner) findViewById(R.id.spinner_search_item_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayCat);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        catInput.setAdapter(adapter);
        /////////////subcategory/////////////
        this.arraySub = new String[] {
                "Any", "Arcane", "Divine", "Epic", "Epic, Psionic", "Major Artifact",
                "Minor Artifact", "None", "Psionic", "Psionic, Minor Artifact", "Shield"
        };
        subInput = (Spinner) findViewById(R.id.spinner_search_item_subcategory);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySub);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        subInput.setAdapter(adapter2);
        /////////////special ability/////////////
        this.arraySpecial = new String[] {
                "Any", "Yes", "No"
        };
        specialInput = (Spinner) findViewById(R.id.spinner_search_item_specialability);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySpecial);
        adapter3.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        specialInput.setAdapter(adapter3);

        //EditText
        nameInput = (EditText)findViewById(R.id.editTxt_search_item_name);

        //ListView
        searchResults = (ListView)findViewById(R.id.listView_search_item);
    }

    public void doSearchItems(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch();
    }

    private void performSearch() {
        String name = nameInput.getText().toString();
        String cat = catInput.getSelectedItem().toString();
        String sub = subInput.getSelectedItem().toString();
        String spec = specialInput.getSelectedItem().toString();
        String defaultStart = "select name from item where name like ? ";
        myDbHelper.openDataBase();
        if (!cat.equals("Any") && !sub.equals("Any") && !spec.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and subcategory = ? and category = ? and special_ability = ? order by name asc",
                    new String[]{"%" + name + "%", sub, cat, spec}));
        }
        else if (!cat.equals("Any") && !sub.equals("Any") && spec.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and subcategory = ? and category = ? order by name asc",
                    new String[]{"%" + name + "%", sub, cat}));
        }
        else if (!cat.equals("Any") && sub.equals("Any") && !spec.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and special_ability = ? and category = ? order by name asc",
                    new String[]{"%" + name + "%", spec, cat}));
        }
        else if (cat.equals("Any") && !sub.equals("Any") && !spec.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                            defaultStart + "and special_ability = ? and subcategory = ? order by name asc",
                            new String[]{"%" + name + "%", spec, sub}));
        }
        else if (!cat.equals("Any") && sub.equals("Any") && spec.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and category = ? order by name asc",
                            new String[]{"%" + name + "%", cat}));
        }
        else if (cat.equals("Any") && !sub.equals("Any") && spec.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and subcategory = ? order by name asc",
                            new String[]{"%" + name + "%", sub}));
        }
        else if (cat.equals("Any") && sub.equals("Any") && !spec.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and special_ability = ? order by name asc",
                            new String[]{"%" + name + "%", spec}));
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
