package group4.dmhelper.Activities.CharacterSheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import group4.dmhelper.Activities.Popups.PopupEditExperience;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.R;
/**
 * Created by Mose
 */
public class ActivityCharacterAbilities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_abilities);
        Bundle extras = getIntent().getExtras();
        final int PlayerIdentifier = extras.getInt("Identifier");
        Actor player = new Actor(PlayerIdentifier,getApplicationContext());

        fillInAbilities(player);

        Button submit = (Button) findViewById(R.id.btn_submit_abilities);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readChanges();
                finish();
            }

            private void readChanges() {
                Actor player = new Actor(PlayerIdentifier,getApplicationContext());
                int databaseValue, inputtedValue = 0;
                //Check Strength
                databaseValue = player.getSTR();
                EditText strength = (EditText) findViewById(R.id.text_input_strength);

                if(!strength.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(strength.getText().toString());
                    if (inputtedValue != databaseValue) {
                        player.setStrength(inputtedValue);
                    }
                }

                //Check Dexterity
                databaseValue = player.getDEX();
                EditText dexterity = (EditText) findViewById(R.id.text_input_dexterity);
                if(!dexterity.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(dexterity.getText().toString());
                    if (inputtedValue != databaseValue) {
                        player.setDexterity(inputtedValue);
                    }
                }

                //Check Constitution
                databaseValue = player.getCON();
                EditText constitution = (EditText) findViewById(R.id.text_input_constitution);
                if(!constitution.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(constitution.getText().toString());
                    if (inputtedValue != databaseValue) {
                        player.setConstitution(inputtedValue);
                    }
                }

                //Check Intelligence
                databaseValue = player.getINT();
                EditText intelligence = (EditText) findViewById(R.id.text_input_intelligence);
                if(!intelligence.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(intelligence.getText().toString());
                    if (inputtedValue != databaseValue) {
                        player.setIntel(inputtedValue);
                    }
                }

                //Check Wisdom
                databaseValue = player.getWIS();
                EditText wisdom = (EditText) findViewById(R.id.text_input_wisdom);
                if(!wisdom.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(wisdom.getText().toString());
                    if (inputtedValue != databaseValue) {
                        player.setWisdom(inputtedValue);
                    }
                }

                //Check Charisma
                databaseValue = player.getCHA();
                EditText charisma = (EditText) findViewById(R.id.text_input_charisma);
                if(!charisma.getText().toString().equals("")) {
                    inputtedValue = Integer.parseInt(charisma.getText().toString());
                    if(inputtedValue != databaseValue)
                    {
                        player.setCharisma(inputtedValue);
                    }
                }
                player.pushToDatabase();
            }
        });

    }

    private void fillInAbilities(Actor player) {
        int databaseString;
        //Sets Strength
        databaseString = player.getSTR();
        if(databaseString >= 0)
        {
            EditText strength = (EditText) findViewById(R.id.text_input_strength);
            strength.setText("" + databaseString);
        }
        //Sets Dexterity
        databaseString = player.getDEX();
        if(databaseString >= 0)
        {
            EditText dexterity = (EditText) findViewById(R.id.text_input_dexterity);
            dexterity.setText("" + databaseString);
        }

        //Sets Constitution
        databaseString = player.getCON();
        if(databaseString >= 0)
        {
            EditText constitution = (EditText) findViewById(R.id.text_input_constitution);
            constitution.setText("" + databaseString);
        }

        //Sets Intelligence
        databaseString = player.getINT();
        if(databaseString >= 0)
        {
            EditText intelligence = (EditText) findViewById(R.id.text_input_intelligence);
            intelligence.setText("" + databaseString);
        }

        //Sets Wisdom
        databaseString = player.getWIS();
        if(databaseString >= 0)
        {
            EditText wisdom = (EditText) findViewById(R.id.text_input_wisdom);
            wisdom.setText("" + databaseString);
        }

        //Sets Charisma
        databaseString = player.getCHA();
        if(databaseString >= 0)
        {
            EditText charisma = (EditText) findViewById(R.id.text_input_charisma);
            charisma.setText("" + databaseString);
        }
    }
}
