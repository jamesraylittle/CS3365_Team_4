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

import group4.dmhelper.Activities.Search.ActivitySearchEquipment;
import group4.dmhelper.Activities.Search.ActivitySearchItems;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Equipment;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/31/2015.
 */
public class PopupEquipmentInfo extends Activity {

    String[] equipmentInfo;
    String playerName;
    TextView name, family, category, subcategory, cost, damage_s, armor_shield_bonus,
             maximum_dex_bonus, damage_m, weight, critical, armor_check_penalty,
             arcane_spell_failure_check, range_increment, speed_30, type, speed_20;
    int playerId;

    /*============================================
    equipmentInfo[0] = name
    equipmentInfo[1] = family
    equipmentInfo[2] = category
    equipmentInfo[3] = subcategory
    equipmentInfo[4] = cost
    equipmentInfo[5] = damage s
    equipmentInfo[6] = armor shield bonus
    equipmentInfo[7] = maximum dex bonus
    equipmentInfo[8] = damage m
    equipmentInfo[9] = weight
    equipmentInfo[10] = critical
    equipmentInfo[11] = armor check penalty
    equipmentInfo[12] = arcane spell failure check
    equipmentInfo[13] = range increment
    equipmentInfo[14] = speed 30
    equipmentInfo[15] = type
    equipmentInfo[16] = speed 20
    equipmentInfo[17] = id
    ============================================*/

    private void setTextViews() {
        equipmentInfo = getIntent().getExtras().getStringArray("equipment_values");
        name = (TextView)findViewById(R.id.info_equipment_name);
        name.setText(equipmentInfo[0]);
        family = (TextView)findViewById(R.id.info_equipment_family);
        family.setText(equipmentInfo[1]);
        category = (TextView)findViewById(R.id.info_equipment_category);
        category.setText(equipmentInfo[2]);
        subcategory = (TextView)findViewById(R.id.info_equipment_subcategory);
        subcategory.setText(equipmentInfo[3]);
        cost = (TextView)findViewById(R.id.info_equipment_cost);
        cost.setText(equipmentInfo[4]);
        damage_s = (TextView)findViewById(R.id.info_equipment_damage_s);
        damage_s.setText(equipmentInfo[5]);
        armor_shield_bonus = (TextView)findViewById(R.id.info_equipment_armor_shield_bonus);
        armor_shield_bonus.setText(equipmentInfo[6]);
        maximum_dex_bonus = (TextView)findViewById(R.id.info_equipment_maximum_dex_bonus);
        maximum_dex_bonus.setText(equipmentInfo[7]);
        damage_m = (TextView)findViewById(R.id.info_equipment_damage_m);
        damage_m.setText(equipmentInfo[8]);
        weight = (TextView)findViewById(R.id.info_equipment_weight);
        weight.setText(equipmentInfo[9]);
        critical = (TextView)findViewById(R.id.info_equipment_critical);
        critical.setText(equipmentInfo[10]);
        armor_check_penalty = (TextView)findViewById(R.id.info_equipment_armor_check_penalty);
        armor_check_penalty.setText(equipmentInfo[11]);
        arcane_spell_failure_check = (TextView)findViewById(R.id.info_equipment_arcane_spell_failure_chance);
        arcane_spell_failure_check.setText(equipmentInfo[12]);
        range_increment = (TextView)findViewById(R.id.info_equipment_range_increment);
        range_increment.setText(equipmentInfo[13]);
        speed_30 = (TextView)findViewById(R.id.info_equipment_speed_30);
        speed_30.setText(equipmentInfo[14]);
        type = (TextView)findViewById(R.id.info_equipment_type);
        type.setText(equipmentInfo[15]);
        speed_20 = (TextView)findViewById(R.id.info_equipment_speed_20);
        speed_20.setText(equipmentInfo[16]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_equipment_info);
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

    public void doCloseEquipmentInfoPopup(View view) {
        PopupEquipmentInfo.this.finish();
    }

    public void doAddEquipment(View view) {
        int equipmentId = Integer.parseInt(equipmentInfo[17]);
        if (playerId == 0) {
            Intent intent = new Intent(PopupEquipmentInfo.this, PopupSelectPlayer.class);
            intent.putExtra("objectName", equipmentInfo[0]);
            intent.putExtra("objectId", equipmentId);
            intent.putExtra("typeId", 1); //0 for item, 1 for equipment, 2 for spells
            startActivity(intent);
            return;
        }
        else {
            Actor a = new Actor(playerId, this.getApplicationContext());
            playerName = a.getName();
            if (playerName == null) {
                playerName = "Unnamed Player";
            }
            FragmentFeed.feedItems.add(equipmentInfo[0] + " was given to " + playerName);
            Toast.makeText(getApplicationContext(), equipmentInfo[0] + " was given to " + playerName, Toast.LENGTH_SHORT).show();
            Equipments dbEquip = new Equipments(getApplicationContext());
            int e =dbEquip.create(new Equipment(playerId, equipmentId, equipmentInfo[0], 0));
            PopupEquipmentInfo.this.finish();
            ActivitySearchEquipment.equipmentSearchActivity.finish();
        }
    }
}
