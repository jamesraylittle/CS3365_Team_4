package group4.dmhelper.Activities.CharacterSheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Search.ActivitySearchSpells;
import group4.dmhelper.Actors.Game;
import group4.dmhelper.Actors.Spell;
import group4.dmhelper.Database.Games;
import group4.dmhelper.Database.Spells;
import group4.dmhelper.R;
/**
 *  Created By Mose
 *  Edited by Kyle
 */
public class ActivityCharacterMagic extends AppCompatActivity {

    public static List<String> spellNames = new ArrayList<>();
    ListView spells;
    ListAdapter adapter;
    int PlayerIdentifier;
    ArrayList<Spell> s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_magic);

        Bundle extras = getIntent().getExtras();
        PlayerIdentifier = extras.getInt("Identifier");

        spells = (ListView)findViewById(R.id.listView_Magic);

        // Button for opening item info
        final Button searchFeats = (Button) findViewById(R.id.btn_search_Magic_CS);
        searchFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterMagic.this, ActivitySearchSpells.class);
                intent.putExtra("Identifier", PlayerIdentifier);
                startActivity(intent);
            }
        });

        spells.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Spell selectedSpell = s.get(position);
                final Spells ss = new Spells(getApplicationContext());

                new AlertDialog.Builder(ActivityCharacterMagic.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want delete the spell \"" + selectedSpell.getSpellName() + "\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ss.delete(selectedSpell.getId());
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
        spellNames.clear();
        Spells q = new Spells(getApplicationContext());
        s = q.getAllByPlayerId(PlayerIdentifier);
        for (int i = 0; i < s.size(); i++) {
            spellNames.add(s.get(i).getSpellName());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_layout, R.id.list_text, spellNames);
        spells.setAdapter(adapter);
    }
}
