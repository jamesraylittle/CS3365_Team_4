package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import group4.dmhelper.Activities.Search.ActivitySearchEquipment;
import group4.dmhelper.Activities.Search.ActivitySearchFeats;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Equipment;
import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Database.Feats;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/31/2015.
 */
public class PopupFeatInfo extends Activity {

    String[] featInfo;
    TextView name, type, multiple, stack, choice, prereq, normal, special;
    int playerId;
    String playerName;

    /*============================================
    featInfo[0] = name
    featInfo[1] = type
    featInfo[2] = multiple
    featInfo[3] = stack
    featInfo[4] = choice
    featInfo[5] = prereq
    featInfo[7] = normal
    featInfo[8] = special
    featInfo[9] = id
    ============================================*/

    private void setTextViews() {
        featInfo = getIntent().getExtras().getStringArray("feat_values");
        name = (TextView)findViewById(R.id.info_feat_name);
        name.setText(featInfo[0]);
        type = (TextView)findViewById(R.id.info_feat_type);
        type.setText(featInfo[1]);
        multiple = (TextView)findViewById(R.id.info_feat_multiple);
        multiple.setText(featInfo[2]);
        stack = (TextView)findViewById(R.id.info_feat_stack);
        stack.setText(featInfo[3]);
        choice = (TextView)findViewById(R.id.info_feat_choice);
        choice.setText(featInfo[4]);
        prereq = (TextView)findViewById(R.id.info_feat_prereq);
        prereq.setText(featInfo[5]);
        normal = (TextView)findViewById(R.id.info_feat_normal);
        normal.setText(featInfo[7]);
        special = (TextView)findViewById(R.id.info_feat_special);
        special.setText(featInfo[8]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_feat_info);
        setPopupDimensions();
        setTextViews();
        playerId = getIntent().getExtras().getInt("playerID");
    }

    private void setPopupDimensions() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .73));
    }

    public void doCloseFeatInfoPopup(View view) {
        PopupFeatInfo.this.finish();
    }

    public void doAddFeat(View view) {
        int featId = Integer.parseInt(featInfo[9]);
        if (playerId == 0) {
            Intent intent = new Intent(PopupFeatInfo.this, PopupSelectPlayer.class);
            intent.putExtra("objectName", featInfo[0]);
            intent.putExtra("objectId", featId);
            intent.putExtra("typeId", 3); //0 for item, 1 for equipment, 2 for spells, 3 for feats
            startActivity(intent);
            return;
        }
        else {
            Actor a = new Actor(playerId, this.getApplicationContext());
            playerName = a.getName();
            if (playerName == null) {
                playerName = "Unnamed Player";
            }
            FragmentFeed.feedItems.add(playerName + " gained feat " + featInfo[0]);
            Toast.makeText(getApplicationContext(), playerName + " gained feat " + featInfo[0], Toast.LENGTH_SHORT).show();
            Feats dbFeat = new Feats(getApplicationContext());
            int e = dbFeat.create(new Feat(playerId, featId, featInfo[0]));
            PopupFeatInfo.this.finish();
            ActivitySearchFeats.featSearchActivity.finish();
        }
    }
}
