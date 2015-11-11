package group4.dmhelper.Actors;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.ClassTypes;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Database.Feats;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Database.PlayerAbilities;
import group4.dmhelper.Database.Races;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.Database.Spells;

/**
 *
 * Created by Daniel on 10/19/2015.
 * Edited 11/2/2015
 */

public class Actor extends Model implements Comparable<Actor>{

    Context context;

    public Actor(int id, String name, String gender, int size, String alignment, float weight, String religion, int speed, int initiativeMod, int grappleMod, int reflexSave, int reflexMod, int willSave, int willMod, int fortSave, int fortMod, Context context) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.religion = religion;
        this.speed = speed;
        this.initiativeMod = initiativeMod;
        this.grappleMod = grappleMod;
        this.reflexSave = reflexSave;
        this.reflexMod = reflexMod;
        this.willSave = willSave;
        this.willMod = willMod;
        this.fortSave = fortSave;
        this.fortMod = fortMod;
        this.context = context;
    }

    public Actor(String name, String gender, int size, String alignment, float weight, String religion, int speed, int initiativeMod, int grappleMod, int reflexSave, int reflexMod, int willSave, int willMod, int fortSave, int fortMod, Context context) {
        this.name = name;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.religion = religion;
        this.speed = speed;
        this.initiativeMod = initiativeMod;
        this.grappleMod = grappleMod;
        this.reflexSave = reflexSave;
        this.reflexMod = reflexMod;
        this.willSave = willSave;
        this.willMod = willMod;
        this.fortSave = fortSave;
        this.fortMod = fortMod;
        this.context = context;
    }

    public Actor(int id) {
        this.id = id;
    }

    public Actor(int id, Context context) { //Saving/Loading
        this.id = id;
        this.context = context;

        dClass = new ClassTypes(context);
        dEquipments = new Equipments(context);
        dItems = new Items(context);
        dFeats = new Feats(context);
        dPlayerAbilities = new PlayerAbilities(context);
        dRaces = new Races(context);
        dSkills = new Skills(context);
        dSpells = new Spells(context);

        /*
        classTypeId = dClass.retrieve(id);
        equippedItemIds.add(dEquipments.retrieve(id));//for(all ids)
        itemIds.add(dItems.retrieve(id));//for(all ids)
        featIds.add(dFeats.retrieve(id));
        playerAbilityIds = dPlayerAbilities.retrieve(id);
        raceId = dRaces.retrieve(id);
        for(int i=0;i<40;i++)skillIds.add(dSkills.retrieve(id));
        spellIds.add(dSpells.retrieve(id));
        //*/
    }

    @Override
    public int compareTo(Actor actor) {
        if (this.initiative > actor.getInitiative()) return 1;
        else if (this.initiative < actor.getInitiative()) return -1;
        else return 0;
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Various Player Attributes
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private String name;
    private String gender;
    private int size;
    private String alignment;
    private float weight;
    private String religion;

    private int speed;
    private int initiativeMod; //this is the modifier plus the roll

    private int initiative; //this is the modifier plus the roll
    private int grappleMod;

    private int reflexSave; private int reflexMod;
    private int willSave;   private int willMod;
    private int fortSave;   private int fortMod;

    private int isMonster;
    private int inGame;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Sets and Gets
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public int getInitiative() {
        return initiative;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String getReligion() {
        return religion;
    }
    public void setReligion(String religion) {
        this.religion = religion;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getInitiativeMod() {
        return initiativeMod;
    }
    public void setInitiativeMod(int initiativeMod) {
        this.initiativeMod = initiativeMod;
    }
    public int getGrappleMod() {
        return grappleMod;
    }
    public void setGrappleMod(int grappleMod) {
        this.grappleMod = grappleMod;
    }
    public int getReflexSave() {
        return reflexSave;
    }
    public void setReflexSave(int reflexSave) {
        this.reflexSave = reflexSave;
    }
    public int getReflexMod() {
        return reflexMod;
    }
    public void setReflexMod(int reflexMod) {
        this.reflexMod = reflexMod;
    }
    public int getWillSave() {
        return willSave;
    }
    public void setWillSave(int willSave) {
        this.willSave = willSave;
    }
    public int getWillMod() {
        return willMod;
    }
    public void setWillMod(int willMod) {
        this.willMod = willMod;
    }
    public int getFortSave() {
        return fortSave;
    }
    public void setFortSave(int fortSave) {
        this.fortSave = fortSave;
    }
    public int getFortMod() {
        return fortMod;
    }
    public void setFortMod(int fortMod) {
        this.fortMod = fortMod;
    }

    public int getInGame() {
        return inGame;
    }

    public void setInGame(int inGame) {
        this.inGame = inGame;
    }

    public int getIsMonster() {
        return isMonster;
    }

    public void setIsMonster(int isMonster) {
        this.isMonster = isMonster;
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Saving and loading from database
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void pullVariables()         {/*takes variables from database*/} // TODO: 11/2/2015
    public void pushVariables()         {/*writes over current variables in database*/} // TODO: 11/2/2015

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //DAOs
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ClassTypes dClass;
    private Equipments dEquipments;
    private Items dItems;
    private Feats dFeats;
    private PlayerAbilities dPlayerAbilities;
    private Races dRaces;
    private Skills dSkills;
    private Spells dSpells;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs - We may not need objects for any of these
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ClassType classTypeId = new ClassType();
    private ArrayList<Equipment> equippedItemIds = new ArrayList<Equipment>();
    private ArrayList<Item> itemIds = new ArrayList<Item>();
    private ArrayList<Feat> featIds = new ArrayList<Feat>();
    private PlayerAbility playerAbilityIds = new PlayerAbility();
    private Race raceId = new Race();
    private ArrayList<Skill> skillIds = new ArrayList<Skill>();    //Make 40 of these
    private ArrayList<Spell> spellIds = new ArrayList<Spell>();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"get" and "set" functions - look through database to find the following items
    //"get uses known database id to search the value from the database
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    //public int getAbility(int i)        {if(i!=NULL)return 0; else return 0;}
    public int getStr()         {return 0;/*query database*/}   //TODO
    public int getDex()         {return 0;/*query database*/}   //TODO
    public int getCon()         {return 0;/*query database*/}   //TODO
    public int getInt()         {return 0;/*query database*/}   //TODO
    public int getWis()         {return 0;/*query database*/}   //TODO
    public int getCha()         {return 0;/*query database*/}   //TODO

    public int getClassType()   {return 0;/*query database*/}   //TODO
    public int getRace()        {return 0;/*query database*/}   //TODO

    public void setStr()    {/*query database*/}    //TODO
    public void setDex()    {/*query database*/}    //TODO
    public void setCon()    {/*query database*/}    //TODO
    public void setInt()    {/*query database*/}    //TODO
    public void setWis()    {/*query database*/}    //TODO
    public void setCha()    {/*query database*/}    //TODO

    public void setClassType(String value)  {/*add id to database*/}    //TODO
    public void setRace(String value)       {/*add id to database*/}    //TODO

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"add" and "remove" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public boolean populateAbilities()      {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateSkills()         {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateEquippedItems()  {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateSpells()         {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateFeats()          {return true;/*query database - populate ArrayList*/}   //TODO

    public int getSkill(int i)                  {if(i>=0&&i<40)return dSkills.retrieve(skillIds.get(i).getSkillId()).getBaseScore(); else return -1;}
    public void setSkill(int i, int value)      {if(i>=0&&i<40){skillIds.get(i).setBaseScore(value); dSkills.update(skillIds.get(i));}}
    public int getSkillMod(int i)               {if(i>=0&&i<40)return dSkills.retrieve(skillIds.get(i).getSkillId()).getMiscBonus(); else return -1;}
    public void setSkillMod(int i, int value)   {if(i>=0&&i<40){skillIds.get(i).setMiscBonus(value); dSkills.update(skillIds.get(i));}}

    public void getEquippedItem()           {if(populateEquippedItems())/*query database - populate ArrayList*/;}   //TODO
    public int getEquippedItem(String S)    {if(populateEquippedItems())return 0; else return 0;/*search by string - query database*/}  //TODO

    public void getSpells()                 {if(populateSpells())/*query database - populate ArrayList*/;}  //TODO
    public int getSpells(String S)          {if(populateSpells())return 0; else return 0;/*search by string - query database*/} //TODO

    public void getFeats()                  {if(populateFeats())/*query database - populate ArrayList*/;}   //TODO
    public int getFeats(String S)           {if(populateFeats())return 0; else return 0;/*search by string - query database*/}  //TODO

    public void addEquippedItems(int ID)    {/*add id to database*/}    //TODO
    public void addSpells(int ID)           {/*add id to database*/}    //TODO
    public void addFeats(int ID)            {/*add id to database*/}    //TODO

    public void removeEquippedItems(int ID) {if(populateEquippedItems())/*add id to database*/;}    //TODO
    public void removeSpells(int ID)        {if(populateSpells())/*add id to database*/;}   //TODO
    public void removeFeats(int ID)         {if(populateFeats())/*add id to database*/;}    //TODO

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"calculate" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void rollInitiative() {initiative = initiativeMod + 10/*replace 10 with activity*/;}            // TODO: 11/10/2015 An activity needs to be connected to this
    public int calculateAC() {return 0;}            // TODO: 11/2/2015
    public int calculateTouchAC() {return 0;}       // TODO: 11/2/2015
    public int calculateFlatFootedAC() {return 0;}  // TODO: 11/2/2015
    public int rollToHit(){return 0;}               //TODO
    public int calculateDamage(){return 0;}         //TODO

}
