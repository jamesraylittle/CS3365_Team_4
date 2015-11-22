package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
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
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Game;
import group4.dmhelper.Actors.Skill;
import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Database.Games;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;
import group4.dmhelper.globalVariables;

/**
 * created by Kyle
 */
public class PopupSelectSkill extends Activity {

    List<String> skillNames = new ArrayList<>();
    List<Integer> skillIds = new ArrayList<>();
    ListView skills;
    ListAdapter adapter;
    int PlayerIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_select_players);

        Bundle extras = getIntent().getExtras();
        PlayerIdentifier = extras.getInt("Identifier");

        TextView tv = (TextView)findViewById(R.id.txt_select_player_title);
        tv.setText("Select Skill");
        skills = (ListView) findViewById(R.id.listView_players);
        skills.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        int skillId = skillIds.get(position);
                        String skillName = skillNames.get(position);
                        Skills db = new Skills(getApplicationContext());
                        int k = db.create(new Skill(1,0,skillName,PlayerIdentifier,skillId));
                        Actor a = new Actor(PlayerIdentifier, getApplicationContext());
                        if (a.getName() == null) {
                            a.setName("Unnamed Player");
                        }
                        FragmentFeed.feedItems.add(a.getName() + " learned the skill " + skillName);
                        Toast.makeText(getApplicationContext(), a.getName() + " learned the skill " + skillName,
                                Toast.LENGTH_SHORT).show();
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

        DataBaseHelper readDB = new DataBaseHelper(getApplicationContext());
        Cursor data = readDB.retrieveAllSkills();

        if(data.moveToFirst()) {
            do {
                skillIds.add(data.getInt(0));
                skillNames.add(data.getString(1));
            } while(data.moveToNext());
        }

        Resources r = getResources();
        int pxW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, r.getDisplayMetrics());
        int extra = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        int pxH = y * 7 + extra;

        getWindow().setLayout(pxW, pxH);

        adapter = new ArrayAdapter<>(this.getApplicationContext(),
                R.layout.list_layout, R.id.list_text, skillNames);
        skills.setAdapter(adapter);
    }
}

