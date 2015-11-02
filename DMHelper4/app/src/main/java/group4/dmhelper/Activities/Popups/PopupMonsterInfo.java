package group4.dmhelper.Activities.Popups;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import group4.dmhelper.Fragments.FragmentFeed;
import group4.dmhelper.Fragments.FragmentGame;
import group4.dmhelper.R;

/**
 * Created by Kyle on 10/31/2015.
 */
public class PopupMonsterInfo extends Activity {

    String[] monsterInfo;
    TextView name, family, size, type, descriptor, alignment, challenge_rating, treasure, organization,
             space, reach, speed, initiative, hit_dice, armor_class, grapple, base_attack, attack,
             full_attack, special_attack, abilities, skills, saves, environment, bonus_feats, feats,
             epic_feats, advancement, special_qualities, special_abilities;

    /*============================================
    monsterInfo[0] = family
    monsterInfo[1] = name
    monsterInfo[2] = alternate name
    monsterInfo[3] = size
    monsterInfo[4] = type
    monsterInfo[5] = descriptor
    monsterInfo[6] = hit dice
    monsterInfo[7] = initiative
    monsterInfo[8] = speed
    monsterInfo[9] = armor class
    monsterInfo[10] = base attack
    monsterInfo[11] = grapple
    monsterInfo[12] = attack
    monsterInfo[13] = full attack
    monsterInfo[14] = space
    monsterInfo[15] = reach
    monsterInfo[16] = special attack
    monsterInfo[17] = special qualities
    monsterInfo[18] = saves
    monsterInfo[19] = abilities
    monsterInfo[20] = skills
    monsterInfo[21] = bonus feats
    monsterInfo[22] = feats
    monsterInfo[23] = epic feats
    monsterInfo[24] = environment
    monsterInfo[25] = organization
    monsterInfo[26] = challenge rating
    monsterInfo[27] = treasure
    monsterInfo[28] = alignment
    monsterInfo[29] = advancement
    monsterInfo[30] = level adjustment
    monsterInfo[31] = special abilities
    ============================================*/

    private void setTextViews() {
        monsterInfo = getIntent().getExtras().getStringArray("monster_values");
        name = (TextView)findViewById(R.id.info_monster_name);
        name.setText(monsterInfo[1]);
        family = (TextView)findViewById(R.id.info_monster_family);
        family.setText(monsterInfo[0]);
        size = (TextView)findViewById(R.id.info_monster_size);
        size.setText(monsterInfo[3]);
        type = (TextView)findViewById(R.id.info_monster_type);
        type.setText(monsterInfo[4]);
        environment = (TextView)findViewById(R.id.info_monster_environment);
        environment.setText(monsterInfo[24]);
        descriptor = (TextView)findViewById(R.id.info_monster_descriptor);
        descriptor.setText(monsterInfo[5]);
        alignment = (TextView)findViewById(R.id.info_monster_alignment);
        alignment.setText(monsterInfo[28]);
        challenge_rating = (TextView)findViewById(R.id.info_monster_challenge_rating);
        challenge_rating.setText(monsterInfo[26]);
        treasure = (TextView)findViewById(R.id.info_monster_treasure);
        treasure.setText(monsterInfo[27]);
        organization = (TextView)findViewById(R.id.info_monster_organization);
        organization.setText(monsterInfo[25]);
        space = (TextView)findViewById(R.id.info_monster_space);
        space.setText(monsterInfo[14]);
        reach = (TextView)findViewById(R.id.info_monster_reach);
        reach.setText(monsterInfo[15]);
        speed = (TextView)findViewById(R.id.info_monster_speed);
        speed.setText(monsterInfo[8]);
        initiative = (TextView)findViewById(R.id.info_monster_initiative);
        initiative.setText(monsterInfo[7]);
        hit_dice = (TextView)findViewById(R.id.info_monster_hit_dice);
        hit_dice.setText(monsterInfo[6]);
        armor_class = (TextView)findViewById(R.id.info_monster_armor_class);
        armor_class.setText(monsterInfo[9]);
        grapple = (TextView)findViewById(R.id.info_monster_grapple);
        grapple.setText(monsterInfo[11]);
        base_attack = (TextView)findViewById(R.id.info_monster_base_attack);
        base_attack.setText(monsterInfo[10]);
        attack = (TextView)findViewById(R.id.info_monster_attack);
        attack.setText(monsterInfo[12]);
        full_attack = (TextView)findViewById(R.id.info_monster_full_attack);
        full_attack.setText(monsterInfo[13]);
        special_attack = (TextView)findViewById(R.id.info_monster_special_attack);
        special_attack.setText(monsterInfo[16]);
        abilities = (TextView)findViewById(R.id.info_monster_abilities);
        abilities.setText(monsterInfo[19]);
        skills = (TextView)findViewById(R.id.info_monster_skills);
        skills.setText(monsterInfo[20]);
        saves = (TextView)findViewById(R.id.info_monster_saves);
        saves.setText(monsterInfo[18]);
        bonus_feats = (TextView)findViewById(R.id.info_monster_bonus_feats);
        bonus_feats.setText(monsterInfo[21]);
        feats = (TextView)findViewById(R.id.info_monster_feats);
        feats.setText(monsterInfo[22]);
        epic_feats = (TextView)findViewById(R.id.info_monster_epic_feats);
        epic_feats.setText(monsterInfo[23]);
        advancement = (TextView)findViewById(R.id.info_monster_advancement);
        advancement.setText(monsterInfo[29]);
        special_qualities = (TextView)findViewById(R.id.info_monster_special_qualities);
        special_qualities.setText(monsterInfo[17]);
        special_abilities = (TextView)findViewById(R.id.info_monster_special_abilities);
        special_abilities.setText(monsterInfo[31]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_monster_info);
        setPopupDimensions();
        setTextViews();
    }

    private void setPopupDimensions() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .73));
    }

    public void doCloseMonsterInfoPopup(View view) {
        PopupMonsterInfo.this.finish();
    }

    public void doAddMonster(View view) {
        FragmentFeed.feedItems.add(monsterInfo[1] + " was added to the game");
        Toast.makeText(getApplicationContext(), monsterInfo[1]+" Added to Game", Toast.LENGTH_LONG).show();
        FragmentGame.monsters.add(monsterInfo);
        PopupMonsterInfo.this.finish();
    }
}
