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
import group4.dmhelper.Activities.Search.ActivitySearchSpells;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.Fragments.FragmentGame;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/31/2015.
 */
public class PopupSpellInfo extends Activity {

    String[] spellInfo;
    int playerId;
    String playerName;
    TextView name, altname, school, subschool, descriptor, spellcraft_dc, level, components,
             casting_time, range, target, area, effect, duration, saving_throw, spell_resistance,
             short_description, to_develop, material_components, arcane_material_components,
             focus, xp_cost, arcane_focus, wizard_focus, verbal_components,
             sorcerer_focus, bard_focus, cleric_focus, druid_focus;

    /*============================================
    spellInfo[0] = name
    spellInfo[1] = alternate name
    spellInfo[2] = school
    spellInfo[3] = subschool
    spellInfo[4] = descriptor
    spellInfo[5] = spellcraft dc
    spellInfo[6] = level
    spellInfo[7] = components
    spellInfo[8] = casting time
    spellInfo[9] = range
    spellInfo[10] = target
    spellInfo[11] = area
    spellInfo[12] = effect
    spellInfo[13] = duration
    spellInfo[14] = saving throw
    spellInfo[15] = spell resistance
    spellInfo[16] = short description
    spellInfo[17] = to develop
    spellInfo[18] = material components
    spellInfo[19] = arcane material components
    spellInfo[20] = focus
    spellInfo[21] = description
    spellInfo[22] = xp cost
    spellInfo[23] = arcane focus
    spellInfo[24] = wizard focus
    spellInfo[25] = verbal components
    spellInfo[26] = sorcerer focus
    spellInfo[27] = bard focus
    spellInfo[28] = cleric focus
    spellInfo[29] = druid focus
    spellInfo[30] = id
    ============================================*/

    private void setTextViews() {
        spellInfo = getIntent().getExtras().getStringArray("spell_values");
        name = (TextView)findViewById(R.id.info_spell_name);
        name.setText(spellInfo[0]);
        altname = (TextView)findViewById(R.id.info_spell_altname);
        altname.setText(spellInfo[1]);
        school = (TextView)findViewById(R.id.info_spell_school);
        school.setText(spellInfo[2]);
        subschool = (TextView)findViewById(R.id.info_spell_sub_school);
        subschool.setText(spellInfo[3]);
        descriptor = (TextView)findViewById(R.id.info_spell_descriptor);
        descriptor.setText(spellInfo[4]);
        spellcraft_dc = (TextView)findViewById(R.id.info_spell_spellcraft_dc);
        spellcraft_dc.setText(spellInfo[5]);
        level = (TextView)findViewById(R.id.info_spell_level);
        level.setText(spellInfo[6]);
        components = (TextView)findViewById(R.id.info_spell_components);
        components.setText(spellInfo[7]);
        casting_time = (TextView)findViewById(R.id.info_spell_casting_time);
        casting_time.setText(spellInfo[8]);
        range = (TextView)findViewById(R.id.info_spell_range);
        range.setText(spellInfo[9]);
        target = (TextView)findViewById(R.id.info_spell_target);
        target.setText(spellInfo[10]);
        area = (TextView)findViewById(R.id.info_spell_area);
        area.setText(spellInfo[11]);
        effect = (TextView)findViewById(R.id.info_spell_effect);
        effect.setText(spellInfo[12]);
        duration = (TextView)findViewById(R.id.info_spell_duration);
        duration.setText(spellInfo[13]);
        saving_throw = (TextView)findViewById(R.id.info_spell_saving_throw);
        saving_throw.setText(spellInfo[14]);
        spell_resistance = (TextView)findViewById(R.id.info_spell_spell_resistance);
        spell_resistance.setText(spellInfo[15]);
        short_description = (TextView)findViewById(R.id.info_spell_short_description);
        short_description.setText(spellInfo[16]);
        to_develop = (TextView)findViewById(R.id.info_spell_to_develop);
        to_develop.setText(spellInfo[17]);
        material_components = (TextView)findViewById(R.id.info_spell_material_components);
        material_components.setText(spellInfo[18]);
        arcane_material_components = (TextView)findViewById(R.id.info_spell_arcane_material_components);
        arcane_material_components.setText(spellInfo[19]);
        focus = (TextView)findViewById(R.id.info_spell_focus);
        focus.setText(spellInfo[20]);
        xp_cost = (TextView)findViewById(R.id.info_spell_xp_cost);
        xp_cost.setText(spellInfo[22]);
        arcane_focus = (TextView)findViewById(R.id.info_spell_arcane_focus);
        arcane_focus.setText(spellInfo[23]);
        wizard_focus = (TextView)findViewById(R.id.info_spell_wizard_focus);
        wizard_focus.setText(spellInfo[24]);
        verbal_components = (TextView)findViewById(R.id.info_spell_verbal_components);
        verbal_components.setText(spellInfo[25]);
        sorcerer_focus = (TextView)findViewById(R.id.info_spell_sorcerer_focus);
        sorcerer_focus.setText(spellInfo[26]);
        bard_focus = (TextView)findViewById(R.id.info_spell_bard_focus);
        bard_focus.setText(spellInfo[27]);
        cleric_focus = (TextView)findViewById(R.id.info_spell_cleric_focus);
        cleric_focus.setText(spellInfo[28]);
        druid_focus = (TextView)findViewById(R.id.info_spell_druid_focus);
        druid_focus.setText(spellInfo[29]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_spell_info);
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

    public void doCloseSpellInfoPopup(View view) {
        PopupSpellInfo.this.finish();
    }

    public void doAddSpell(View view) {
        int spellId = Integer.parseInt(spellInfo[31]);
        if (playerId == 0) {
            Intent intent = new Intent(PopupSpellInfo.this, PopupSelectPlayer.class);
            intent.putExtra("objectName", spellInfo[0]);
            intent.putExtra("objectId", spellId);
            intent.putExtra("typeId", 2); //0 for item, 1 for equipment, 2 for spells
            startActivity(intent);
            return;
        }
        else {
            Actor a = new Actor(playerId, this.getApplicationContext());
            playerName = a.getName();
            if (playerName == null) {
                playerName = "Unnamed Player";
            }
            FragmentFeed.feedItems.add(playerName + " learned the spell " + spellInfo[0]);
            Toast.makeText(getApplicationContext(), playerName + " learned the spell " + spellInfo[0], Toast.LENGTH_SHORT).show();
            // TODO add spell to actor/db
            PopupSpellInfo.this.finish();
            ActivitySearchSpells.spellSearchActivity.finish();
        }
    }
}
