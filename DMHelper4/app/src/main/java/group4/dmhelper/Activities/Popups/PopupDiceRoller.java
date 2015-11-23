package group4.dmhelper.Activities.Popups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import group4.dmhelper.Activities.CharacterSheet.ActivityCharacterSheet;
import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Equipment;
import group4.dmhelper.Actors.Skill;
import group4.dmhelper.Actors.Spell;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.Database.Spells;
import group4.dmhelper.R;

public class PopupDiceRoller extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linearLayout;
    private int id, roll;
    private Actor actor;
    private Boolean flag_skill;
    private Boolean flag_weapon;
    private Skill skill_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_dice_roller);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id = (int) b.get("Identifier");
//        actor = new Actor(id, getApplicationContext());
        roll=0;
        flag_skill = flag_weapon = false;
        skill_selected = new Skill();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.5f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.4));

        linearLayout = (LinearLayout) findViewById(R.id.buttonList);

        Button skillList = new Button(this);
        Button useWeapon = new Button(this);
        Button useSpell = new Button(this);
        Button player = new Button(this);

        skillList.setText("Skill List");
        skillList.setId(R.id.btn_skillList);
        skillList.setOnClickListener(this);

        useWeapon.setText("Use Weapon");
        useWeapon.setId(R.id.btn_useWeapon);
        useWeapon.setOnClickListener(this);

        useSpell.setText("Use Spell");
        useSpell.setId(R.id.btn_useSpell);
        useSpell.setOnClickListener(this);

        player.setText("Character Sheet");
        player.setId(R.id.btn_player);
        player.setOnClickListener(this);

        linearLayout.addView(player);
        linearLayout.addView(skillList);
        linearLayout.addView(useWeapon);
        linearLayout.addView(useSpell);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup_dice_roller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Skills q = new Skills(getApplicationContext());
        Equipments eq = new Equipments(getApplicationContext());
        Spells s = new Spells(getApplicationContext());

        ArrayList<String> skillNames = new ArrayList<String>();
        ArrayList<String> equipmentNames = new ArrayList<String>();
        ArrayList<String> spellNames = new ArrayList<String>();

        ArrayList<Skill> dbSkillObjects = q.getAllByPlayerId(id);
        ArrayList<Equipment> dbEquipedObjects = eq.getAllByPlayerId(id);
        ArrayList<Spell> dbSpellObjects = s.getAllByPlayerId(id);

        Intent intent;
        TextView textView;
        EditText diceRoll = new EditText(this);
        switch (v.getId())
        {
            case R.id.btn_skillList:
                linearLayout.removeAllViews();
                for (int i = 0; i < dbSkillObjects.size(); i++) {
                    skillNames.add(dbSkillObjects.get(i).getName());
//                    skill_selected = dbSkillObjects.get(i);
                    Button button = new Button(this);
                    button.setText(dbSkillObjects.get(i).getName());
                    //why doesn't this work?
                    button.setId(dbSkillObjects.get(i).getId());
                    button.setOnClickListener(this);
                    linearLayout.addView(button);
                }
                flag_skill = true;
                break;
            case R.id.btn_useWeapon:
                // TODO: 11/16/2015  find out how to view weapons
                linearLayout.removeAllViews();
                for (int i = 0; i < dbEquipedObjects.size(); i++) {
                    equipmentNames.add(dbEquipedObjects.get(i).getEquipmentName());
                    Button button = new Button(this);
                    button.setText(dbEquipedObjects.get(i).getEquipmentName());
                    //why doesn't this work?
                    button.setId(dbEquipedObjects.get(i).getId());
                    button.setOnClickListener(this);
                    linearLayout.addView(button);
                }
                flag_weapon = true;
                break;
            case R.id.btn_useSpell:
                linearLayout.removeAllViews();
                for (int i = 0; i < dbSpellObjects.size(); i++) {
                    spellNames.add(dbSpellObjects.get(i).getSpellName());
                    Button button = new Button(this);
                    button.setText(dbSpellObjects.get(i).getSpellName());
                    //why doesn't this work?
                    button.setId(dbSpellObjects.get(i).getId());
                    button.setOnClickListener(this);
                    linearLayout.addView(button);
                }
                flag_weapon = true;
                break;
            case R.id.btn_player:
                intent = new Intent(getApplicationContext(), ActivityCharacterSheet.class);
                intent.putExtra("Identifier", id);
                startActivity(intent);
                break;
            case R.id.submit_d20:
//                roll = Integer.parseInt(diceRoll.getText().toString());
                textView = new TextView(this);
                textView.setText(skill_selected.getName());

                TextView mod = new TextView(this);
                mod.setText("Modifiers");

                int mod_ab =0;//= actor.getAbility(); // TODO: 11/16/2015 add this function to actor
                int mod_misc =0;//=actor.getMisc(); // TODO: 11/16/2015  add this function to actor

                TextView ability = new TextView(this);
                ability.setText("Ability" + " " + mod_ab);

                TextView misc = new TextView(this);
                misc.setText("Misc" + " " + mod_misc);

                int skill_total =0;//= actor.calculateRoll(roll,mod_ab,mod_misc); // TODO: 11/16/2015 add this function to actor

                TextView total = new TextView(this);
                total.setText("Total: " + skill_total);

                linearLayout.addView(textView);
                linearLayout.addView(mod);
                linearLayout.addView(ability);
                linearLayout.addView(misc);
                linearLayout.addView(total);
                break;

            case R.id.submit_roll:
                // TODO: 11/16/2015 once weapon is done 
                break;

            default:

                if(flag_skill)
                {
                    linearLayout.removeAllViews();
//                    skill_selected = new Skill(v.getId());
                    textView = new TextView(this);
                    textView.setText(skill_selected.getName());
                    diceRoll.setHint("d20 Roll");
                    Button submit = new Button(this);
                    submit.setText("Submit");
                    submit.setId(R.id.submit_d20);
                    submit.setOnClickListener(this);

                    linearLayout.addView(textView);
                    linearLayout.addView(diceRoll);
                    linearLayout.addView(submit);
                }
                else if(flag_weapon)
                {
                    linearLayout.removeAllViews();
                    // TODO: 11/16/2015 need a weapon selected similar to the skill selected 
                    textView = new TextView(this);
                    textView.setText(skill_selected.getName()); // TODO: 11/16/2015 replace with weapon selected

//                    EditText diceRoll = new EditText(this);
                    diceRoll.setHint("d20 Roll"); // TODO: 11/16/2015 replace this with the roll the weapon has 
//                    roll = Integer.parseInt(diceRoll.getText().toString());

                    Button submit = new Button(this);
                    submit.setText("Submit");
                    submit.setId(R.id.submit_roll);
                    submit.setOnClickListener(this);

                    linearLayout.addView(textView);
                    linearLayout.addView(diceRoll);
                    linearLayout.addView(submit);
                }
                break;
        }
    }

}
