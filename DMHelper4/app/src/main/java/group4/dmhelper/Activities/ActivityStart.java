package group4.dmhelper.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import group4.dmhelper.R;

public class ActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void newAdventure(View view)
    {
        Intent intent = new Intent(this, ActivityGame.class);
        startActivity(intent);
    }

    public void loadAdventure(View view)
    {
        //STEALING THIS BUTTON TO TEST
        //TODO FIX THIS
        Intent intent = new Intent(this, ActivityCharacterSheet.class);
        startActivity(intent);
    }
}
