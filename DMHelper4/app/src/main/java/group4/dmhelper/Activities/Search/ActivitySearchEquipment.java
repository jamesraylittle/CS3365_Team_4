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

import group4.dmhelper.Activities.Popups.PopupEquipmentInfo;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/24/2015.
 */
public class ActivitySearchEquipment extends Activity {

    DataBaseHelper myDbHelper;
    EditText nameInput;
    Spinner catInput, subInput, famInput;
    ListView searchResults;
    ListAdapter adapter;
    int playerId;
    List<String> listItems = new ArrayList<>();
    private String[] arrayCat, arraySub, arrayFam;
    public static Activity equipmentSearchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_equipment);
        equipmentSearchActivity = this;
        myDbHelper = new DataBaseHelper(this);
        initializeWidgets();
        try {
            playerId = getIntent().getExtras().getInt("Identifier");
        }
        catch (Exception e) {}
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
                "Any", "Armor", "Exotic Weapons", "Item", "Martial Weapons", "Service", "Simple Weapons"
        };
        catInput = (Spinner) findViewById(R.id.spinner_search_equipment_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayCat);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);

        catInput.setAdapter(adapter);
        /////////////subcategory/////////////
        this.arraySub = new String[] {
                "Any", "Adventuring Gear", "Ammunition", "Clothing", "Extras", "Food, Drink, and Lodging",
                "Heavy armor", "Light Melee Weapons", "Light armor", "Medium armor", "Mounts and Related Gear",
                "None", "One-Handed Melee Weapons", "Ranged Weapons", "Shields", "Special Substances and Items",
                "Spellcasting and Services", "Tools and Skill Kits", "Transport", "Two-Handed Melee Weapons",
                "Unarmed Attacks"
        };
        subInput = (Spinner) findViewById(R.id.spinner_search_equipment_subcategory);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySub);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        subInput.setAdapter(adapter2);

        /////////////special ability/////////////
        this.arrayFam = new String[] {
                "Any", "Armor and Shields", "Goods and Services", "Trade Goods", "Weapons"
        };
        famInput = (Spinner) findViewById(R.id.spinner_search_equipment_family);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayFam);
        adapter3.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        famInput.setAdapter(adapter3);

        //EditText
        nameInput = (EditText)findViewById(R.id.editTxt_search_equipment_name);

        //ListView
        searchResults = (ListView)findViewById(R.id.listView_search_equipment);
        searchResults.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        String[] ev = getEquipmentInfo(listItems.get(position));
                        if (ev.equals(null)) {
                            Toast.makeText(getApplicationContext(), "There was an error loading the item details.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(ActivitySearchEquipment.this, PopupEquipmentInfo.class);
                        intent.putExtra("equipment_values", ev);
                        intent.putExtra("playerID",playerId);
                        startActivity(intent);
                    }
                }
        );
    }

    private String[] getEquipmentInfo(String equipmentName) {
        List<String> ei = new ArrayList<>();
        myDbHelper.openDataBase();
        Cursor query = myDbHelper.performRawQuery("select * from equipment where name = ?", new String[]{equipmentName});
        if (query.getCount() != 1) { // if it is not just 1 result, that's an error
            return null;
        }
        else { // Otherwise, populate string array with information
            query.moveToFirst();
            for (int i = 1; i < 18; i++) {
                ei.add(query.getString(i));
            }
            ei.add(""+query.getInt(0));
            query.close();
        }
        myDbHelper.close();
        return ei.toArray(new String[ei.size()]);
    }

    public void doSearchEquipment(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch();
    }

    private void performSearch() {
        String name = nameInput.getText().toString();
        String cat = catInput.getSelectedItem().toString();
        String sub = subInput.getSelectedItem().toString();
        String fam = famInput.getSelectedItem().toString();
        String defaultStart = "select name from equipment where name like ? ";
        myDbHelper.openDataBase();
        if (!cat.equals("Any") && !sub.equals("Any") && !fam.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and subcategory = ? and category = ? and family = ? order by name asc",
                    new String[]{"%" + name + "%", sub, cat, fam}));
        }
        else if (!cat.equals("Any") && !sub.equals("Any") && fam.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and subcategory = ? and category = ? order by name asc",
                    new String[]{"%" + name + "%", sub, cat}));
        }
        else if (!cat.equals("Any") && sub.equals("Any") && !fam.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and family = ? and category = ? order by name asc",
                    new String[]{"%" + name + "%", fam, cat}));
        }
        else if (cat.equals("Any") && !sub.equals("Any") && !fam.equals("Any")) {
            populateList( myDbHelper.performRawQuery(
                    defaultStart + "and family = ? and subcategory = ? order by name asc",
                    new String[]{"%" + name + "%", fam, sub}));
        }
        else if (!cat.equals("Any") && sub.equals("Any") && fam.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and category = ? order by name asc",
                            new String[]{"%" + name + "%", cat}));
        }
        else if (cat.equals("Any") && !sub.equals("Any") && fam.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and subcategory = ? order by name asc",
                            new String[]{"%" + name + "%", sub}));
        }
        else if (cat.equals("Any") && sub.equals("Any") && !fam.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and family = ? order by name asc",
                            new String[]{"%" + name + "%", fam}));
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
            listItems.clear();
            Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            searchResults.requestFocus();
            return;
        }
        else { // Otherwise, populate the list with the results
            listItems.clear();
            //Toast.makeText(getApplicationContext(), query.getCount() + " results found.", Toast.LENGTH_LONG).show();
            query.moveToFirst();
            do { listItems.add(query.getString(0)); }
            while (query.moveToNext());
            query.close();
            adapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.list_layout, R.id.list_text,
                    listItems);
            searchResults.setAdapter(adapter);
        }
    }
}
