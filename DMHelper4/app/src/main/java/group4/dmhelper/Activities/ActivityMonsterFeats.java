package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group4.dmhelper.Activities.Search.ActivitySearchFeats;
import group4.dmhelper.R;

public class ActivityMonsterFeats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_feats);

        Bundle extras = getIntent().getExtras();
        final String MonsterIdentifier = extras.getString("Identifier");
        //Actor player = new Actor(PlayerIdentifier);
        //player.getFeats();
        //TODO POPULATE LISTVIEW

        // Button for opening racialclass info
        final Button searchFeats = (Button) findViewById(R.id.btn_search_feats_CS);
        searchFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMonsterFeats.this, ActivitySearchFeats.class);
                intent.putExtra("Identifier", MonsterIdentifier);
                startActivity(intent);
            }
        });
    }


}
