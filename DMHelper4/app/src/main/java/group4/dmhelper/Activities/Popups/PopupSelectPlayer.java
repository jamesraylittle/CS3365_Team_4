package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Search.ActivitySearchItems;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class PopupSelectPlayer extends Activity {

    List<String> playerNames = new ArrayList<>();
    ListView players;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select_players);
        final String item = getIntent().getExtras().getString("item_equipment_name");
        final int itemId = getIntent().getExtras().getInt("item_equipment_id");
        players = (ListView) findViewById(R.id.listView_players);
        players.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        String name = playerNames.get(position);
                        FragmentFeed.feedItems.add(item + " was given to " + name);
                        Toast.makeText(getApplicationContext(), item + " was given to " + name, Toast.LENGTH_SHORT).show();
                        //Item myItem = new Item(playerId, itemId);
                        //Items i = new Items();
                        //i.create(myItem);
                        finish();
                    }
                }
        );

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        TypedValue typedValue = new TypedValue();
        this.getApplicationContext().getTheme().resolveAttribute(android.R.attr.listPreferredItemHeightSmall, typedValue, true);
        int y = getResources().getDimensionPixelSize(typedValue.resourceId);

        playerNames.add("PlayerOne");
        playerNames.add("PlayerTwo");
        playerNames.add("PlayerThree");
        playerNames.add("PlayerFour");
        playerNames.add("PlayerFive");

        Resources r = getResources();
        int pxW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        int pxH = y * playerNames.size() +
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        getWindow().setLayout(pxW, pxH);

        adapter = new ArrayAdapter<>(this.getApplicationContext(),
                R.layout.list_layout, R.id.list_text, playerNames);
        players.setAdapter(adapter);
    }
}

