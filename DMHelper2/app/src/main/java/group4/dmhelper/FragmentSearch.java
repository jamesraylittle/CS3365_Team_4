package group4.dmhelper;

/**
 * Created by Kyle on 10/13/2015.
 */
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class FragmentSearch extends Fragment {

    DataBaseHelper myDbHelper;
    EditText search;
    ListView lvUsers;
    ListAdapter adapter;
    List<String> listUsers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lvUsers = (ListView)getView().findViewById(R.id.lvUsers);

        myDbHelper = new DataBaseHelper(getActivity());
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

        search = (EditText)getView().findViewById(R.id.editText);
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
            Toast.makeText(getActivity().getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            return;
        }
        else { // Otherwise, populate the list with the results
            listUsers.clear();
            Toast.makeText(getActivity().getApplicationContext(), query.getCount() + " results found.", Toast.LENGTH_LONG).show();
            query.moveToFirst();
            do { listUsers.add(query.getString(0)); }
            while (query.moveToNext());
            query.close();
            adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                    R.layout.list_layout, R.id.list_text,
                    listUsers);
            lvUsers.setAdapter(adapter);
        }

        myDbHelper.close();
    }
}