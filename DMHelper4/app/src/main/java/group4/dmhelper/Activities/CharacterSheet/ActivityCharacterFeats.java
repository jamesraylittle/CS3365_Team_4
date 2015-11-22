package group4.dmhelper.Activities.CharacterSheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Search.ActivitySearchFeats;
import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Spell;
import group4.dmhelper.Database.Feats;
import group4.dmhelper.Database.Spells;
import group4.dmhelper.R;
/**
 * Created by Mose
 * Edited by Kyle
 */
public class ActivityCharacterFeats extends AppCompatActivity {

    public static List<String> featNames = new ArrayList<>();
    ListView feats;
    ListAdapter adapter;
    int PlayerIdentifier;
    ArrayList<Feat> dbFeatObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_feats);

        PlayerIdentifier = getIntent().getExtras().getInt("Identifier");

        feats = (ListView)findViewById(R.id.listView_feats);

        final Button searchFeats = (Button) findViewById(R.id.btn_search_feats_CS);
        searchFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterFeats.this, ActivitySearchFeats.class);
                intent.putExtra("Identifier", PlayerIdentifier);
                startActivity(intent);
            }
        });

        feats.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Feat selectedFeat = dbFeatObjects.get(position);
                final Feats ss = new Feats(getApplicationContext());

                new AlertDialog.Builder(ActivityCharacterFeats.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to remove the feat \"" + selectedFeat.getFeatName() + "\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ss.delete(selectedFeat.getId());
                                refillList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refillList();
    }

    private void refillList() {
        featNames.clear();
        Feats q = new Feats(getApplicationContext());
        dbFeatObjects = q.getAllByPlayerId(PlayerIdentifier);
        for (int i = 0; i < dbFeatObjects.size(); i++) {
            featNames.add(dbFeatObjects.get(i).getFeatName());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_layout, R.id.list_text, featNames);
        feats.setAdapter(adapter);
    }
}
