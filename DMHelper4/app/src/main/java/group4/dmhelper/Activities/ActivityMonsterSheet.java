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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import group4.dmhelper.Activities.Popups.PopupEditCharPic;
import group4.dmhelper.Activities.Popups.PopupEditExperience;
import group4.dmhelper.Activities.Popups.PopupEditHealth;
import group4.dmhelper.Actors.Monster;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

public class ActivityMonsterSheet extends AppCompatActivity {

    private String MonsterIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_sheet);

        Bundle extras = getIntent().getExtras();
        MonsterIdentifier = extras.getString("Identifier");
        populateSpinners();
        setupButtons(MonsterIdentifier);
        editProgressBars(MonsterIdentifier);

        LinearLayout health=(LinearLayout) findViewById(R.id.healthBarLayout);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, PopupEditHealth.class);
                intent.putExtra("Identifier", MonsterIdentifier);
                startActivity(intent);
            }
        });

        LinearLayout xp=(LinearLayout) findViewById(R.id.xpBarLayout);
        xp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, PopupEditExperience.class);
                intent.putExtra("Identifier", MonsterIdentifier);
                startActivity(intent);
            }
        });

        // Button for Submit
        Button Submit = (Button) findViewById(R.id.btn_submit_monster_sheet);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText monsterName = (EditText) findViewById(R.id.editText_monster_name);
                if (!monsterName.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed monster name to " + monsterName.getText());
                }

                EditText height = (EditText) findViewById(R.id.editText_Height);
                if (!height.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed height to " + height.getText());
                }

                EditText weight = (EditText) findViewById(R.id.editText_Weight);
                if (!weight.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed weight to " + weight.getText());
                }

                EditText religion = (EditText) findViewById(R.id.editText_religion);
                if (!religion.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed religion to " + religion.getText());
                }

                Spinner monsterClass = (Spinner) findViewById(R.id.spinner_search_class);
                if (!monsterClass.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed class to " + monsterClass.getSelectedItem().toString());
                }

                Spinner race = (Spinner) findViewById(R.id.spinner_search_race);
                if (!race.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed race to " + race.getSelectedItem().toString());
                }

                Spinner alignment = (Spinner) findViewById(R.id.spinner_search_alignment);
                if (!alignment.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM current
                {
                    FragmentFeed.feedItems.add(MonsterIdentifier + "Changed alignment to " + alignment.getSelectedItem().toString());
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
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterSkills.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for opening racialclass info
        Button racialclass = (Button) findViewById(R.id.btn_racialclass);
        racialclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterRacialClass.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });
        // Button for Magic
        Button magic = (Button) findViewById(R.id.btn_magic);
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterMagic.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for Feats
        Button feats = (Button) findViewById(R.id.btn_feats);
        feats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterFeats.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });
        // Button for inventory
        Button inventory = (Button) findViewById(R.id.btn_inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterInventory.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for abilities
        Button abilities = (Button) findViewById(R.id.btn_abilities);
        abilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, ActivityMonsterAbilities.class);
                intent.putExtra("Identifier", ID);
                startActivity(intent);
            }
        });

        // Button for charPicture
        ImageButton charPic = (ImageButton) findViewById(R.id.charPictureButton);
        charPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterSheet.this, PopupEditCharPic.class);
                //intent.putExtra("Identifier", ID);
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

