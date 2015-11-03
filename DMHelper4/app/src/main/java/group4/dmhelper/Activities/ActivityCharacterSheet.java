package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import group4.dmhelper.Activities.Popups.PopupEditExperience;
import group4.dmhelper.Activities.Popups.PopupEditHealth;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class ActivityCharacterSheet extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);

        Bundle extras = getIntent().getExtras();
        final String PlayerIdentifier = extras.getString("Identifier");
        populateSpinners();
        setupButtons(PlayerIdentifier);
        editProgressBars(PlayerIdentifier);

        // Button for Submit
        Button Submit = (Button) findViewById(R.id.btn_submit_character_sheet);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText characterName = (EditText) findViewById(R.id.editText_character_name);
                if(!characterName.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed character name to " + characterName.getText());
                }

                EditText playerName = (EditText) findViewById(R.id.editText_player_name);
                if(!playerName.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    final String s = playerName.getText().toString();
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed player name to " + playerName.getText());
                }

                EditText height = (EditText) findViewById(R.id.editText_Height);
                if(!height.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed height to " + height.getText());
                }

                EditText weight = (EditText) findViewById(R.id.editText_Weight);
                if(!weight.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed weight to " + weight.getText());
                }

                EditText religion = (EditText) findViewById(R.id.editText_religion);
                if(!religion.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed religion to " + religion.getText());
                }

                Spinner characterClass = (Spinner) findViewById(R.id.spinner_search_class);
                if(!characterClass.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed class to " + characterClass.getSelectedItem().toString());
                }

                Spinner race = (Spinner) findViewById(R.id.spinner_search_race);
                if(!race.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed race to " + race.getSelectedItem().toString());
                }

                Spinner alignment = (Spinner) findViewById(R.id.spinner_search_alignment);
                if(!alignment.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM current
                {
                    FragmentFeed.feedItems.add(PlayerIdentifier + "Changed alignment to " + alignment.getSelectedItem().toString());
                }

                finish();
            }
        });


    }

    private void editProgressBars(String playerIdentifier) {
        //Actor player = new Actor(playerIdentifer);
        ProgressBar healthbar = (ProgressBar) findViewById(R.id.progressBar_health);
        healthbar.setMax(100);  //TODO GET FROM DATABASE
                                // healthbar.setMax(player.calcMaxHealth());
        healthbar.setProgress(45);  //TODO GET FROM DATABASE
                                // healthbar.setProgress(player.getHealth());
        TextView health = (TextView) findViewById(R.id.txt_health_ratio);
        health.setText(healthbar.getProgress() + "/" + healthbar.getMax());

        ProgressBar xpbar = (ProgressBar) findViewById(R.id.progressBar_experience);
        xpbar.setMax(100); //TODO GET FROM DATABASE
                            // xpbar.setMax(player.calcMaxXp());
        xpbar.setProgress(10);  //TODO GET FROM DATABASE
                            //xpbar.setProgress(
        TextView xp = (TextView) findViewById(R.id.txt_experience_ratio);
        xp.setText(xpbar.getProgress() + "/" + xpbar.getMax());

    }

    private void setupButtons(final String ID){
        // Button for opening skills
        Button skills = (Button) findViewById(R.id.btn_skills);
        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterSkills.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for opening racialclass info
        Button racialclass = (Button) findViewById(R.id.btn_racialclass);
        racialclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterRacialClass.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });
        // Button for Magic
        Button magic = (Button) findViewById(R.id.btn_magic);
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterMagic.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for Feats
        Button feats = (Button) findViewById(R.id.btn_feats);
        feats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterFeats.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });
        // Button for inventory
        Button inventory = (Button) findViewById(R.id.btn_inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterInventory.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for abilities
        Button abilities = (Button) findViewById(R.id.btn_abilities);
        abilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, ActivityCharacterAbilities.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });


        //Button for Editing health
        Button health = (Button) findViewById(R.id.btn_change_health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, PopupEditHealth.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        //Button for Editing xp
        Button xp = (Button) findViewById(R.id.btn_change_experience);
        xp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, PopupEditExperience.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
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
        Spinner Alignment_spinner = (Spinner) findViewById(R.id.spinner_search_alignment);
        ArrayAdapter<CharSequence> AlignmentAdapter = ArrayAdapter
                .createFromResource(this, R.array.Alignments,
                        android.R.layout.simple_spinner_item);
        AlignmentAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Alignment_spinner.setAdapter(AlignmentAdapter);
    }
}

