package group4.dmhelper.Activities.CharacterSheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.R;
/**
 * Created by Mose
 * Edited by Kyle
 */
public class ActivityCharacterRacialClass extends AppCompatActivity {

    String className;
    TextView title, type, alignment, hit_dice, class_skills, skill_points, proficiencies, spell_type,
             epic_feat_base_level, epic_feat_interval, epic_feat_list, req_race,
             req_skill, req_feat, req_spells, req_psionics, req_epic_feat, req_special;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_racial_class);
        className = getIntent().getExtras().getString("className");
        DataBaseHelper readDB = new DataBaseHelper(getApplicationContext());
        setTextViews(readDB.retrieveClassByName(className));
        readDB.close();
    }

    private void setTextViews(String[] values) {
        title = (TextView)findViewById(R.id.txt_char_classinfo_title);
        String curTitle = title.getText().toString();
        curTitle = curTitle + " - " + className;
        title.setText(curTitle);
        type = (TextView)findViewById(R.id.txt_char_class_type);
        type.setText(values[1]);
        alignment = (TextView)findViewById(R.id.txt_char_class_alignment);
        alignment.setText(values[2]);
        hit_dice = (TextView)findViewById(R.id.txt_char_class_hitdice);
        hit_dice.setText(values[3]);
        class_skills = (TextView)findViewById(R.id.txt_char_class_classskills);
        class_skills.setText(values[4]);
        skill_points = (TextView)findViewById(R.id.txt_char_class_skillpoints);
        skill_points.setText(values[5]);
        proficiencies = (TextView)findViewById(R.id.txt_char_class_proficiencies);
        proficiencies.setText(values[8]);
        spell_type = (TextView)findViewById(R.id.txt_char_class_spelltype);
        spell_type.setText(values[9]);
        epic_feat_base_level = (TextView)findViewById(R.id.txt_char_class_epicfeatbaselevel);
        epic_feat_base_level.setText(values[10]);
        epic_feat_interval = (TextView)findViewById(R.id.txt_char_class_epicfeatinterval);
        epic_feat_interval.setText(values[11]);
        epic_feat_list = (TextView)findViewById(R.id.txt_char_class_epicfeatlist);
        epic_feat_list.setText(values[12]);
        req_race = (TextView)findViewById(R.id.txt_char_class_reqrace);
        req_race.setText(values[14]);
        req_skill = (TextView)findViewById(R.id.txt_char_class_reqskill);
        req_skill.setText(values[17]);
        req_feat = (TextView)findViewById(R.id.txt_char_class_reqfeat);
        req_feat.setText(values[18]);
        req_spells = (TextView)findViewById(R.id.txt_char_class_reqspell);
        req_spells.setText(values[19]);
        req_psionics = (TextView)findViewById(R.id.txt_char_class_reqpsionic);
        req_psionics.setText(values[21]);
        req_epic_feat = (TextView)findViewById(R.id.txt_char_class_reqepic);
        req_epic_feat.setText(values[22]);
        req_special = (TextView)findViewById(R.id.txt_char_class_reqspecial);
        req_special.setText(values[23]);
    }
}
