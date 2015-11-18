package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group4.dmhelper.Activities.Search.ActivitySearchSpells;
import group4.dmhelper.R;

public class ActivityMonsterMagic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_magic);

        Bundle extras = getIntent().getExtras();
        final String MonsterIdentifier = extras.getString("Identifier");
        //Actor player = new Actor(PlayerIdentifier);
        //player.getSpells();
        //TODO POPULATE LISTVIEW

        // Button for opening item info
        final Button searchFeats = (Button) findViewById(R.id.btn_search_Magic_CS);
        searchFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterMagic.this, ActivitySearchSpells.class);
                intent.putExtra("Identifier", MonsterIdentifier);
                startActivity(intent);
            }
        });
    }
}
