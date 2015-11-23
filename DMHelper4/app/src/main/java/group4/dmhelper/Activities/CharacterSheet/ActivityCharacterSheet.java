package group4.dmhelper.Activities.CharacterSheet;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;
/**
 * Created by Mose
 */
public class ActivityCharacterSheet extends AppCompatActivity {

    private int PlayerIdentifier;
    private Actor player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);

        Bundle extras = getIntent().getExtras();
        PlayerIdentifier = extras.getInt("Identifier");
        player = new Actor(PlayerIdentifier, getApplicationContext());
        populateSpinners();
        setupButtons(PlayerIdentifier);
        editProgressBars();
        populateFillIn();

        LinearLayout health=(LinearLayout) findViewById(R.id.healthBarLayout);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, PopupEditHealth.class);
                intent.putExtra("Identifier", PlayerIdentifier);
                startActivity(intent);
            }
        });

        LinearLayout xp=(LinearLayout) findViewById(R.id.xpBarLayout);
        xp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, PopupEditExperience.class);
                intent.putExtra("Identifier", PlayerIdentifier);
                startActivity(intent);
            }
        });
        ImageButton playerIcon = (ImageButton) findViewById(R.id.charPictureButton);
        String image = player.getImageFile();
        if (image != null) {
            int imageResource = getResources().getIdentifier(image, "drawable", getPackageName());
            playerIcon.setImageResource(imageResource);
        }
        submitData();

    }

    private void submitData() {
        // Button for Submit
        Button Submit = (Button) findViewById(R.id.btn_submit_character_sheet);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText characterName = (EditText) findViewById(R.id.editText_character_name);
                if (!characterName.getText().toString().equals(""))
                {
                    String database = player.getName();
                    String inText = characterName.getText().toString();
                    if( database== null || !database.equals(inText)) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed character name to " + inText);
                        player.setName(inText);
                    }
                }

                EditText playerName = (EditText) findViewById(R.id.editText_player_name);
                if (!playerName.getText().toString().equals(""))
                {
                    String database = player.getPlayerName();
                    String inText = playerName.getText().toString();
                    if( database== null || !database.equals(inText)) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed player name to " + playerName.getText());
                        player.setPlayerName(inText);
                    }
                }

                EditText height = (EditText) findViewById(R.id.editText_Height);
                if (!height.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(player.getPlayerName() + " Changed height to " + height.getText());
                }

                EditText weight = (EditText) findViewById(R.id.editText_Weight);
                if (!weight.getText().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(player.getPlayerName() + " Changed weight to " + weight.getText());
                }

                EditText religion = (EditText) findViewById(R.id.editText_religion);
                if (!religion.getText().toString().equals(""))
                {
                    String database = player.getReligion();
                    String inText = religion.getText().toString();
                    if( database== null || !database.equals(inText)) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed player name to " + playerName.getText());
                        player.setReligion(inText);
                    }
                }

                Spinner characterClass = (Spinner) findViewById(R.id.spinner_search_class);
                if (!characterClass.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(player.getPlayerName() + " Changed class to " + characterClass.getSelectedItem().toString());
                }

                Spinner race = (Spinner) findViewById(R.id.spinner_search_race);
                if (!race.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM CURRENT
                {
                    FragmentFeed.feedItems.add(player.getPlayerName() + " Changed race to " + race.getSelectedItem().toString());
                }

                Spinner alignment = (Spinner) findViewById(R.id.spinner_search_alignment);
                if (!alignment.getSelectedItem().toString().equals("")) //TODO CHECK IF DIFFERENT FROM current
                {
                    FragmentFeed.feedItems.add(player.getPlayerName() + " Changed alignment to " + alignment.getSelectedItem().toString());
                }
                player.pushToDatabase();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        editProgressBars();
        // Fix the Players Icon on Resume
        ImageButton playerIcon = (ImageButton) findViewById(R.id.charPictureButton);
        String image = player.getImageFile();
        if (image != null) {
            int imageResource = getResources().getIdentifier(image, "drawable", getPackageName());
            playerIcon.setImageResource(imageResource);
        }
    }

    private void populateFillIn() {
        String databaseString;
        //Sets CharacterName
        databaseString = player.getName();
        if(databaseString != null)
        {
            EditText characterName = (EditText) findViewById(R.id.editText_character_name);
            characterName.setText(databaseString);
        }

        //Sets PlayerName
        databaseString = player.getPlayerName();
        if(databaseString != null)
        {
            EditText playerName = (EditText) findViewById(R.id.editText_player_name);
            playerName.setText(databaseString);
        }

        //Sets religion
        databaseString = player.getReligion();
        if(databaseString != null)
        {
            EditText religion = (EditText) findViewById(R.id.editText_religion);
            religion.setText(databaseString);
        }

//        //Sets Height
//        float database = player.getWeight();
//        if(database)
//        {
//            EditText religion = (EditText) findViewById(R.id.editText_Weight);
//            religion.setText(databaseString);
//        }
        //sets weight
//        float database = player.getWeight();
//        if(database !=0)
//        {
//            EditText weight = (EditText) findViewById(R.id.editText_Weight);
//            weight.setText(Float.toString(database));
//        }
    }

    private void editProgressBars() {
        player.pullFromDatabase();
        ProgressBar healthbar = (ProgressBar) findViewById(R.id.progressBar_health);
        healthbar.setMax(100);  //TODO calc the max health
        healthbar.setProgress(player.getHealth());
        TextView health = (TextView) findViewById(R.id.txt_health_ratio);
        health.setText(healthbar.getProgress() + "/" + healthbar.getMax());

        ProgressBar xpbar = (ProgressBar) findViewById(R.id.progressBar_experience);
        Resources res = getResources();
        int[] levels = res.getIntArray(R.array.Levels);
        xpbar.setMax(190000); //Cap Level
        for(int i = 0; i <levels.length ; i++) {
            if(levels[i] > player.getXP())
            {
                xpbar.setMax(levels[i]);
                EditText level = (EditText) findViewById(R.id.editText_Level);
                level.setText("" + ++i);
                break;
            }
        }
        xpbar.setProgress(player.getXP());
        TextView xp = (TextView) findViewById(R.id.txt_experience_ratio);
        xp.setText(xpbar.getProgress() + "/" + xpbar.getMax());

    }

    private void setupButtons(final int ID){
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

        // Button for charPicture
        ImageButton charPic = (ImageButton) findViewById(R.id.charPictureButton);
        charPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterSheet.this, PopupEditCharPic.class);
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

