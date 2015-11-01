package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import group4.dmhelper.Activities.Popups.PopupEditExperience;
import group4.dmhelper.Activities.Popups.PopupEditHealth;
import group4.dmhelper.R;

public class ActivityCharacterSheet extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);
        populateSpinners();
        setupButtons();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_character_sheet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupButtons(){
        // Button for opening skills
        Button skills = (Button) findViewById(R.id.btn_skills);
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCharacterSheet.this, ActivityCharacterSkills.class));
            }
        });

        // Button for opening racialclass info
        Button racialclass = (Button) findViewById(R.id.btn_racialclass);
        racialclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCharacterSheet.this, ActivityCharacterRacialClass.class));
            }
        });
        // Button for Magic
        Button magic = (Button) findViewById(R.id.btn_magic);
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCharacterSheet.this, ActivityCharacterMagic.class));
            }
        });

        // Button for Feats
        Button feats = (Button) findViewById(R.id.btn_feats);
        feats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCharacterSheet.this, ActivityCharacterFeats.class));
            }
        });
        // Button for inventory
        Button inventory = (Button) findViewById(R.id.btn_inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCharacterSheet.this, ActivityCharacterInventory.class));
            }
        });

        //Button for Editing health
        Button health = (Button) findViewById(R.id.btn_change_health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(ActivityCharacterSheet.this, PopupEditHealth.class));
            }
        });

        //Button for Editing xp
        Button xp = (Button) findViewById(R.id.btn_change_experience);
        xp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(ActivityCharacterSheet.this, PopupEditExperience.class));
            }
        });

    }
    private void populateSpinners(){
        //Populate Class Tables
        Spinner Class_spinner = (Spinner) findViewById(R.id.spinner_search_class);
        ArrayAdapter<CharSequence> ClassAdapter = ArrayAdapter
                .createFromResource(this, R.array.Classes,
                        android.R.layout.simple_spinner_item);
        ClassAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Class_spinner.setAdapter(ClassAdapter);

        //Populate Race Spinner
        Spinner Race_spinner = (Spinner) findViewById(R.id.spinner_search_race);
        ArrayAdapter<CharSequence> RaceAdapter = ArrayAdapter
                .createFromResource(this, R.array.Races,
                        android.R.layout.simple_spinner_item);
        RaceAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Race_spinner.setAdapter(RaceAdapter);

        //Populate Race Spinner
        Spinner Allignment_spinner = (Spinner) findViewById(R.id.spinner_search_allignment);
        ArrayAdapter<CharSequence> AllignmentAdapter = ArrayAdapter
                .createFromResource(this, R.array.Allignments,
                        android.R.layout.simple_spinner_item);
        AllignmentAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Allignment_spinner.setAdapter(AllignmentAdapter);
    }
}

