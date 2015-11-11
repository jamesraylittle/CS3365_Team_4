package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import group4.dmhelper.Activities.Search.ActivitySearchItems;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.Fragments.FragmentGame;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/31/2015.
 */
public class PopupItemInfo extends Activity {

    int playerId;
    String playerName;
    String[] itemInfo;
    TextView name, category, subcategory, special_ability, aura, caster_level, price,
             manifest_level, prereq, cost, weight;

    /*============================================
    itemInfo[0] = name
    itemInfo[1] = category
    itemInfo[2] = subcategory
    itemInfo[3] = special ability
    itemInfo[4] = aura
    itemInfo[5] = caster level
    itemInfo[6] = price
    itemInfo[7] = manifest level
    itemInfo[8] = pre req
    itemInfo[9] = cost
    itemInfo[10] = weight
    itemInfo[11] = reference
    itemInfo[12] = id
    ============================================*/

    private void setTextViews() {
        itemInfo = getIntent().getExtras().getStringArray("item_values");
        name = (TextView)findViewById(R.id.info_item_name);
        name.setText(itemInfo[0]);
        category = (TextView)findViewById(R.id.info_item_category);
        category.setText(itemInfo[1]);
        subcategory = (TextView)findViewById(R.id.info_item_subcategory);
        subcategory.setText(itemInfo[2]);
        special_ability = (TextView)findViewById(R.id.info_item_special_ability);
        special_ability.setText(itemInfo[3]);
        aura = (TextView)findViewById(R.id.info_item_aura);
        aura.setText(itemInfo[4]);
        caster_level = (TextView)findViewById(R.id.info_item_caster_level);
        caster_level.setText(itemInfo[5]);
        price = (TextView)findViewById(R.id.info_item_price);
        price.setText(itemInfo[6]);
        manifest_level = (TextView)findViewById(R.id.info_item_manifest_level);
        manifest_level.setText(itemInfo[7]);
        prereq = (TextView)findViewById(R.id.info_item_prereq);
        prereq.setText(itemInfo[8]);
        cost = (TextView)findViewById(R.id.info_item_cost);
        cost.setText(itemInfo[9]);
        weight = (TextView)findViewById(R.id.info_item_weight);
        weight.setText(itemInfo[10]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_item_info);
        setPopupDimensions();
        setTextViews();
        if (getIntent().getExtras().getString("playerID") != null) {
            playerId = 1;
        }
        playerName = getIntent().getExtras().getString("playerName");
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

    public void doCloseItemInfoPopup(View view) {
        PopupItemInfo.this.finish();
    }

    public void doAddItem(View view) {
        int itemId = Integer.parseInt(itemInfo[11]);
        if (playerId == 0) {
            Intent intent = new Intent(PopupItemInfo.this, PopupSelectPlayer.class);
            intent.putExtra("item_equipment_name", itemInfo[0]);
            intent.putExtra("item_equipment_id", itemId);
            startActivity(intent);
            return;
        }
        else {
            FragmentFeed.feedItems.add(itemInfo[0] + " was given to " + playerName);
            Toast.makeText(getApplicationContext(), itemInfo[0] + " was given to " + playerName, Toast.LENGTH_SHORT).show();
           // Item myItem = new Item(playerId, itemId);
           // Items i = new Items(this.getApplicationContext());
           // i.create(myItem);
            PopupItemInfo.this.finish();
            ActivitySearchItems.itemSearchActivity.finish();
        }
    }
}
