package group4.dmhelper.Activities.CharacterSheet;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import group4.dmhelper.Activities.Popups.PopupEditCharPic;
import group4.dmhelper.Activities.Popups.PopupEditExperience;
import group4.dmhelper.Activities.Popups.PopupEditHealth;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;
/**
 * Created by Mose
 */
public class ActivityCharacterSheet extends AppCompatActivity {

    private int PlayerIdentifier;
    private Actor player;
    Spinner Race_spinner, Alignment_spinner, Class_spinner;
    ArrayAdapter<CharSequence> RaceAdapter, AlignmentAdapter;
    ArrayAdapter<String> ClassAdapter;

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

    @Override
    protected void onPause() {
        super.onPause();
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
                if (!height.getText().toString().equals(""))
                {
                    String database = player.getHeight();
                    String inText = height.getText().toString();
                    if( database== null || !database.equals(inText)) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed character height to " + inText);
                        player.setHeight(inText);
                    }
                }

                EditText weight = (EditText) findViewById(R.id.editText_Weight);
                if (!weight.getText().toString().equals(""))
                {
                    String database = player.getWeight();
                    String inText = weight.getText().toString();
                    if( database== null || !database.equals(inText)) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed character weight to " + inText);
                        player.setWeight(inText);
                    }
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
                if (!characterClass.getSelectedItem().toString().equals(""))
                {
                    if(player.getClassName() == null || !player.getClassName().equals(characterClass.getSelectedItem().toString())) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed class to " + characterClass.getSelectedItem().toString());
                        player.setRace(characterClass.getSelectedItem().toString());
                    }
                }

                Spinner race = (Spinner) findViewById(R.id.spinner_search_race);
                if (!race.getSelectedItem().toString().equals(""))
                {
                    if(player.getRace() == null || !player.getRace().equals(race.getSelectedItem().toString())) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed race to " + race.getSelectedItem().toString());
                        player.setRace(race.getSelectedItem().toString());
                    }
                }

                Spinner alignment = (Spinner) findViewById(R.id.spinner_search_alignment);
                if (!alignment.getSelectedItem().toString().equals(""))
                {
                    if(player.getAlignment() == null || !player.getAlignment().equals(alignment.getSelectedItem().toString())) {
                        FragmentFeed.feedItems.add(player.getPlayerName() + " Changed alignment to " + alignment.getSelectedItem().toString());
                        player.setAlignment(alignment.getSelectedItem().toString());
                    }
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

        //sets class
        databaseString = player.getClassName(); //TODO fix this
        Log.d("num44","Class name: "+player.getClassName());
        if (databaseString != null) {
            int spinnerPosition = ClassAdapter.getPosition(databaseString);
            Class_spinner.setSelection(spinnerPosition);
        }

        //sets race
        databaseString = player.getRace();
        if (databaseString != null) {
            int spinnerPosition = RaceAdapter.getPosition(databaseString);
            Race_spinner.setSelection(spinnerPosition);
        }

        //sets alignment
        databaseString = player.getAlignment();
        if (databaseString != null) {
            int spinnerPosition = AlignmentAdapter.getPosition(databaseString);
            Alignment_spinner.setSelection(spinnerPosition);
        }

        //Sets Height
        databaseString = player.getHeight();
        if(databaseString != null)
        {
            Log.d("num44","Wight: "+databaseString);
            EditText height = (EditText) findViewById(R.id.editText_Height);
            height.setText(databaseString);
        }

        //sets weight
        databaseString = player.getWeight();
        if(databaseString != null)
        {
            Log.d("num44","Height: "+databaseString);
            EditText weight = (EditText) findViewById(R.id.editText_Weight);
            weight.setText(databaseString);
        }

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
        Class_spinner = (Spinner) findViewById(R.id.spinner_search_class);
        DataBaseHelper readDB = new DataBaseHelper(getApplicationContext());
        ArrayList<String> classNames = readDB.retireveAllClassNames();
        ClassAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, classNames);
        ClassAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Class_spinner.setAdapter(ClassAdapter);
        readDB.close();

        //Populate Race Spinner
        Race_spinner = (Spinner) findViewById(R.id.spinner_search_race);
        RaceAdapter = ArrayAdapter
                .createFromResource(this, R.array.Races,
                        android.R.layout.simple_spinner_item);
        RaceAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Race_spinner.setAdapter(RaceAdapter);

        //Populate Alignment Spinner
        Alignment_spinner = (Spinner) findViewById(R.id.spinner_search_alignment);
        AlignmentAdapter = ArrayAdapter
                .createFromResource(this, R.array.Alignments,
                        android.R.layout.simple_spinner_item);
        AlignmentAdapter.setDropDownViewResource(R.layout.spinner_layout_dropdown);
        Alignment_spinner.setAdapter(AlignmentAdapter);
    }
}

