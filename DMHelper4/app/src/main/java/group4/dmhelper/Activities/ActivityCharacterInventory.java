package group4.dmhelper.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group4.dmhelper.Activities.Search.ActivitySearchItems;
import group4.dmhelper.R;

public class ActivityCharacterInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_inventory);

        Bundle extras = getIntent().getExtras();
        final String PlayerIdentifier = extras.getString("Identifier");
        //Actor player = new Actor(PlayerIdentifier);
        //player.getItems();
        //TODO POPULATE LISTVIEW

        // Button for opening item info
        final Button searchFeats = (Button) findViewById(R.id.btn_search_inventory_CS);
        searchFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCharacterInventory.this, ActivitySearchItems.class);
                intent.putExtra("Identifier", PlayerIdentifier);
                intent.putExtra("playerName", PlayerIdentifier);
                startActivity(intent);
            }
        });
}
}
