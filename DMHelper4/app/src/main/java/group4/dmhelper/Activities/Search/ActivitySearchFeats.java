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

import group4.dmhelper.Activities.Popups.PopupFeatInfo;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/16/2015.
 */
public class ActivitySearchFeats extends Activity {

    DataBaseHelper myDbHelper;
    EditText nameInput;
    Spinner typeInput;
    ListView searchResults;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();
    private String[] arraySize;
    //private final String tagName = "android:switcher:" + R.id.viewpager + ":" + 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_feat);
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
        //Spinner
        this.arraySize = new String[] {
                "Any", "Divine", "Divine, Epic", "Epic", "Epic, Psionic", "General", "General, Fighter",
                "Item Creation", "Item Creation, Epic", "Metamagic", "Metamagic, Epic", "Metapsionic",
                "Psionic", "Special", "Type of Feat", "Wild, Epic"
        };
        typeInput = (Spinner) findViewById(R.id.spinner_search_feat_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arraySize);
        adapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        typeInput.setAdapter(adapter);

        //EditText
        nameInput = (EditText)findViewById(R.id.editText_search_feat_name);

        //ListView
        searchResults = (ListView)findViewById(R.id.listView_search_feat);
        searchResults.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        String[] ev = getFeatInfo(listUsers.get(position));
                        if (ev.equals(null)) {
                            Toast.makeText(getApplicationContext(), "There was an error loading the item details.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(ActivitySearchFeats.this, PopupFeatInfo.class);
                        intent.putExtra("feat_values", ev);
                        startActivity(intent);
                    }
                }
        );
    }

    private String[] getFeatInfo(String featName) {
        List<String> ei = new ArrayList<>();
        myDbHelper.openDataBase();
        Cursor query = myDbHelper.performRawQuery("select * from feat where name = ?", new String[]{featName});
        if (query.getCount() != 1) { // if it is not just 1 result, that's an error
            return null;
        }
        else { // Otherwise, populate string array with information
            query.moveToFirst();
            for (int i = 1; i < 10; i++) {
                ei.add(query.getString(i));
            }
            query.close();
        }
        myDbHelper.close();
        return ei.toArray(new String[ei.size()]);
    }

    public void doSearchFeats(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        performSearch();
    }

    private void performSearch() {
        String name = nameInput.getText().toString();
        String type = typeInput.getSelectedItem().toString();
        String defaultStart = "select name from feat where name like ? ";
        myDbHelper.openDataBase();
        if (type.equals("Any")) {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "order by name asc",
                            new String[]{"%" + name + "%"}));
        }
        else {
            populateList(
                    myDbHelper.performRawQuery(defaultStart + "and type = ? order by name asc",
                            new String[]{"%" + name + "%", type}));
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
