package group4softwareengineer.dmhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CharacterSheet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheet);

        //Populate Class Tables
        Spinner Class_spinner = (Spinner) findViewById(R.id.Class_spinner);

        ArrayAdapter<CharSequence> ClassAdapter = ArrayAdapter
                .createFromResource(this, R.array.DnDClasses,
                        android.R.layout.simple_spinner_item);

        ClassAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Class_spinner.setAdapter(ClassAdapter);

        //Populate Race Spinner
        Spinner Race_spinner = (Spinner) findViewById(R.id.Race_Spinner);
        ArrayAdapter<CharSequence> RaceAdapter = ArrayAdapter
                .createFromResource(this, R.array.DnDRaces,
                        android.R.layout.simple_spinner_item);
        RaceAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Race_spinner.setAdapter(RaceAdapter);

        //Populate Race Spinner
        Spinner Allignment_spinner = (Spinner) findViewById(R.id.Alignment_Spinner);
        ArrayAdapter<CharSequence> AllignmentAdapter = ArrayAdapter
                .createFromResource(this, R.array.DnDAllignment,
                        android.R.layout.simple_spinner_item);
        AllignmentAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Allignment_spinner.setAdapter(AllignmentAdapter);

        // Button for Editing Health
        Button health = (Button) findViewById(R.id.healthButton);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharacterSheet.this, EditHealthPopup.class));
            }
        });

        //Button for Editing xp
        Button xp = (Button) findViewById(R.id.xpButton);
        xp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(CharacterSheet.this, EditXpPopup.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_sheet, menu);
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
}
