package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.ActivityGame;
import group4.dmhelper.Activities.ActivityStart;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Game;
import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.Games;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;
import group4.dmhelper.globalVariables;

/**
 * created by Kyle
 */
public class PopupSelectGame extends Activity {

    List<String> gameNames = new ArrayList<>();
    ListView games;
    ListAdapter adapter;
    ArrayList<Game> gameList;
    globalVariables gv = globalVariables.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select_players);

        TextView tv = (TextView)findViewById(R.id.txt_select_player_title);
        tv.setText("Select Adventure");
        games = (ListView) findViewById(R.id.listView_players);
        games.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        Game selectedGame = gameList.get(position);
                        int numPlayers = selectedGame.getNumPlayers();
                        int gameId = selectedGame.getId();
                        int playerIds[] = new int[numPlayers];

                        Actors actor = new Actors(getApplicationContext());
                        ArrayList<Actor> players;
                        players = actor.getAllActorsByGameId(gameId);
                        for (int k = 0; k < players.size(); k++) {
                            playerIds[k] = players.get(k).getId();
                        }

                        gv.setGameId(gameId);

                        Intent intent = new Intent(PopupSelectGame.this, ActivityGame.class);
                        intent.putExtra("numplayers", numPlayers);
                        intent.putExtra("playerids", playerIds);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        games.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Game selectedGame = gameList.get(position);
                final Games g = new Games(getApplicationContext());

                new AlertDialog.Builder(PopupSelectGame.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want delete the adventure \"" +selectedGame.getName()+"\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                g.delete(selectedGame.getId());
                                finish();
                                Intent intent = new Intent(PopupSelectGame.this, PopupSelectGame.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        TypedValue typedValue = new TypedValue();
        this.getApplicationContext().getTheme().resolveAttribute(android.R.attr.listPreferredItemHeightSmall, typedValue, true);
        int y = getResources().getDimensionPixelSize(typedValue.resourceId);

        Games g = new Games(getApplicationContext());
        gameList = g.retrieveAll();
        if (gameList.size() == 0) {
            Toast.makeText(getApplicationContext(), "No games found", Toast.LENGTH_SHORT).show();
            finish();
        }
        for (int i = 0; i < gameList.size(); i++) {
            gameNames.add(gameList.get(i).getName());
        }
        Resources r = getResources();
        int pxW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        int pxH;
        int extra = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        if (gameNames.size() < 8) {
            pxH = y * gameNames.size() + extra;
        }
        else {
            pxH = y * 7 + extra;
        }
        getWindow().setLayout(pxW, pxH);

        adapter = new ArrayAdapter<>(this.getApplicationContext(),
                R.layout.list_layout, R.id.list_text, gameNames);
        games.setAdapter(adapter);
    }
}

