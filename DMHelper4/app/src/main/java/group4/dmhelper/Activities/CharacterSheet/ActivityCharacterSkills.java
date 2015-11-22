package group4.dmhelper.Activities.CharacterSheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import group4.dmhelper.Activities.Popups.PopupSelectSkill;
import group4.dmhelper.Activities.Search.ActivitySearchSpells;
import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Skill;
import group4.dmhelper.Database.Feats;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.R;
/**
 * Created by Mose
 */
public class ActivityCharacterSkills extends AppCompatActivity {

    public static List<String> skillNames = new ArrayList<>();
    ListView skills;
    ListAdapter adapter;
    int PlayerIdentifier;
    ArrayList<Skill> dbSkillObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_skills);

        PlayerIdentifier = getIntent().getExtras().getInt("Identifier");

        skills = (ListView)findViewById(R.id.listView_skills);

        skills.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {

                final Skill selectedSkill = dbSkillObjects.get(position);
                final Skills ss = new Skills(getApplicationContext());

                new AlertDialog.Builder(ActivityCharacterSkills.this, R.style.AlertStyle)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setIconAttribute(android.R.attr.alertDialogIcon)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to remove the skill \"" + selectedSkill.getName() + "\"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ss.delete(selectedSkill.getId());
                                refillList();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    public void doAddSkill(View v) {
        Intent intent = new Intent(ActivityCharacterSkills.this, PopupSelectSkill.class);
        intent.putExtra("Identifier", PlayerIdentifier);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refillList();
    }

    private void refillList() {
        skillNames.clear();
        Skills q = new Skills(getApplicationContext());
        dbSkillObjects = q.getAllByPlayerId(PlayerIdentifier);
        for (int i = 0; i < dbSkillObjects.size(); i++) {
            skillNames.add(dbSkillObjects.get(i).getName());
        }
        adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_layout, R.id.list_text, skillNames);
        skills.setAdapter(adapter);
    }
}
