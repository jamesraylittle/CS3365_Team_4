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

import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Activities.Popups.PopupMonsterInfo;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/16/2015.
 */
public class ActivitySearchMonsters extends Activity {

    DataBaseHelper myDbHelper;
    EditText nameInput, familyInput;
    Spinner typeInput, sizeInput;
    ListView searchResults;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();
    List<String> monsterValues = new ArrayList<>();
    private String[] arraySize, arrayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_monster);
        myDbHelper = new DataBaseHelper(this);
        initializeWidgets();
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
        this.arraySize = new String[] {
                "Any", "Colossal", "Colossal+", "Diminutive", "Fine", "Gargantuan", "Huge",
                "Large", "Medium", "Small", "Tiny"
        };
        sizeInput = (Spinner) findViewById(R.id.spinner_search_monster_size);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySize);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        sizeInput.setAdapter(adapter);

        this.arrayType = new String[] {
                "Any", "Aberration", "Animal", "Construct", "Dragon", "Elemental", "Fey", "Giant", "Humanoid",
                "Magical Beast", "Monstrous Humanoid", "Ooze", "Outsider", "Plant", "Undead", "Vermin"
        };
        typeInput = (Spinner) findViewById(R.id.spinner_search_monster_type);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayType);
        adapter2.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        typeInput.setAdapter(adapter2);

        //EditTexts
        nameInput = (EditText)findViewById(R.id.editTxt_search_monster_name);
        familyInput = (EditText)findViewById(R.id.editTxt_seach_monster_family);

        //ListView
        searchResults = (ListView)findViewById(R.id.listView_search_monster);
        searchResults.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                    String[] mv = getMonsterInfo(searchResults.getItemAtPosition(position).toString());
                    if (mv.equals(null)) {
                        Toast.makeText(getApplicationContext(), "There was an error loading this monster's details.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent = new Intent(ActivitySearchMonsters.this, PopupMonsterInfo.class);
                    intent.putExtra("monster_values", mv);
                    startActivity(intent);
                }
            }
        );
    }

    private String[] getMonsterInfo(String monsterName) {
        List<String> mi = new ArrayList<>();
        myDbHelper.openDataBase();
        Cursor query = myDbHelper.performRawQuery("select * from monster where name = ?", new String[]{monsterName});
        if (query.getCount() != 1) { // if it is not just 1 result, that's an error
            return null;
        }
        else { // Otherwise, populate string array with information
            query.moveToFirst();
            for (int i = 1; i < 33; i++) {
                mi.add(query.getString(i));
            }
            query.close();
        }
        myDbHelper.close();
        return mi.toArray(new String[mi.size()]);
    }

    public void doSearchMonsters(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch();
    }

    private void performSearch() {
        String name = nameInput.getText().toString();
        String family = familyInput.getText().toString();
        String size = sizeInput.getSelectedItem().toString();
        String type = typeInput.getSelectedItem().toString();
        String defaultStart = "select name from monster where name like ? and family like ? ";
        myDbHelper.openDataBase();
        if (!type.equals("Any") && !size.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and type = ? and size = ? order by name asc",
                            new String[]{"%" + name + "%", "%" + family + "%", type, size}));
        }
        else if (!type.equals("Any") && size.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and type = ? order by name asc",
                            new String[]{"%" + name + "%", "%" + family + "%", type}));
        }
        else if (type.equals("Any") && !size.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and size = ? order by name asc",
                            new String[]{"%" + name + "%", "%" + family + "%", size}));
        }
        else {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "order by name asc",
                            new String[]{"%" + name + "%", "%" + family + "%"}));
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
