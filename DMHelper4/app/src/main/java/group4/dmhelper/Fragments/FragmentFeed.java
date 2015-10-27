package group4.dmhelper.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.R;

/**
 * Created by Kyle on 10/13/2015.
 */
public class FragmentFeed extends Fragment {

    List<String> feedItems = new ArrayList<>();
    ListView feed;
    ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);
        feed = (ListView)view.findViewById(R.id.listView_gamefeed);
        addFeed("Adventure started");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addFeed(String log) {
        feedItems.add(log);
        updateFeed();
    }

    public void updateFeed() {
        adapter = new ArrayAdapter<>(getContext().getApplicationContext(),
                R.layout.list_layout, R.id.list_text,
                feedItems);
        feed.setAdapter(adapter);
    }
}
