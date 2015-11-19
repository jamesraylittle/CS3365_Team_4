package group4.dmhelper.Activities.Search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Popups.PopupItemInfo;
import group4.dmhelper.Database.DataBaseHelper;
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
    List<Integer> listIds = new ArrayList<>();
    String playerId, playerName;
    private String[] arrayCat, arraySub, arraySpecial;
    public static Activity itemSearchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_item);
        itemSearchActivity = this;
        myDbHelper = new DataBaseHelper(this);
        initializeWidgets();
        try {
            playerId = getIntent().getExtras().getString("Identifier");
            playerName = getIntent().getExtras().getString("playerName");
        }
        catch (Exception e) {}
    }

    @Override
    protected void onPause() {
        super.onPause();
        try { myDbHelper.close(); }
        catch(SQLException sqle){ throw sqle; }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try { myDbHelper.close(); }
        catch(SQLException sqle){ throw sqle; }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try { myDbHelper.openDataBase(); }
        catch(SQLException sqle){ throw sqle; }
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
        searchResults.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        String[] iv = getItemInfo(listIds.get(position));
                        if (iv.equals(null)) {
                            Toast.makeText(getApplicationContext(), "There was an error loading the item details.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(ActivitySearchItems.this, PopupItemInfo.class);
                        intent.putExtra("item_values", iv);
                        intent.putExtra("playerID",playerId);
                        intent.putExtra("playerName",playerName);
                        startActivity(intent);
                    }
                }
        );
    }

    private String[] getItemInfo(int itemId) {
        List<String> ii = new ArrayList<>();
        myDbHelper.openDataBase();
        Cursor query = myDbHelper.performRawQuery("select * from item where _id = "+itemId, new String[]{});
        if (query.getCount() != 1) { // if it is not just 1 result, that's an error
            return null;
        }
        else { // Otherwise, populate string array with information
            query.moveToFirst();
            for (int i = 1; i < 12; i++) {
                ii.add(query.getString(i));
            }
            ii.add(""+query.getInt(0));
            query.close();
        }
        myDbHelper.close();
        return ii.toArray(new String[ii.size()]);
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
        String defaultStart = "select _id, name from item where name like ? ";
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
            listIds.clear();
            Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            searchResults.requestFocus();
            return;
        }
        else { // Otherwise, populate the list with the results
            listUsers.clear();
            Toast.makeText(getApplicationContext(), query.getCount() + " results found.", Toast.LENGTH_LONG).show();
            query.moveToFirst();
            do {
                listUsers.add(query.getString(1));
                listIds.add(query.getInt(0));
            }
            while (query.moveToNext());
            query.close();
            adapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.list_layout, R.id.list_text, listUsers);
            searchResults.setAdapter(adapter);
        }
    }
}
