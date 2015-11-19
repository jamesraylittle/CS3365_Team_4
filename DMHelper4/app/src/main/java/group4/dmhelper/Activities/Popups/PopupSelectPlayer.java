package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
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
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;
import group4.dmhelper.globalVariables;

public class PopupSelectPlayer extends Activity {

    ListView players;
    ListAdapter adapter;
    globalVariables g = globalVariables.getInstance();
    int[] playerIds;
    String[] playerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select_players);
        final String objectName = getIntent().getExtras().getString("objectName");
        final int objectID = getIntent().getExtras().getInt("objectId");
        final int typeID = getIntent().getExtras().getInt("typeId");
        players = (ListView) findViewById(R.id.listView_players);
        players.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        String name = playerNames[position];
                        int playerId = playerIds[position];
                        String feedMessage = "";
                        switch (typeID) {
                            case 0:
                                feedMessage = objectName + " was given to " + name;
                                // TODO  add item to actor/db
                                break;
                            case 1:
                                feedMessage = objectName + " was given to " + name;
                                // TODO  add equipment to actor/db
                                break;
                            case 2:
                                feedMessage = name+" learned the spell "+objectName;
                                // TODO  add spell to actor/db
                                break;
                            default:
                                feedMessage = "Something went wrong adding this object!";
                        }
                        FragmentFeed.feedItems.add(feedMessage);
                        Toast.makeText(getApplicationContext(), feedMessage, Toast.LENGTH_SHORT).show();
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

        Actors actor = new Actors(getApplicationContext());
        ArrayList<Actor> playerss;
        playerss = actor.getAllActorsByGameId(g.getGameId());
        playerIds = new int[playerss.size()];
        playerNames = new String[playerss.size()];
        for (int k = 0; k < playerss.size(); k++) {
            playerIds[k] = playerss.get(k).getId();
            if (playerss.get(k).getName() == null || playerss.get(k).getName() == "") {
                playerNames[k] = "Player"+(k+1);
            }
            else {
                playerNames[k] = playerss.get(k).getName();
            }
        }

        Resources r = getResources();
        int pxW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        int pxH = y * playerNames.length +
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        getWindow().setLayout(pxW, pxH);

        adapter = new ArrayAdapter<>(this.getApplicationContext(),
                R.layout.list_layout, R.id.list_text, playerNames);
        players.setAdapter(adapter);
    }
}

