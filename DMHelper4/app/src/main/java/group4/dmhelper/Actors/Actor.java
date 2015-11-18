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


    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //DAOs
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private Actors dActor;

    private ClassTypes dClass;
    private Equipments dEquipments;
    private Items dItems;
    private Feats dFeats;
    private PlayerAbilities dPlayerAbilities;
    private Skills dSkills;
    private Spells dSpells;

    Context context;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Constructors
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

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

    public Actor(int id, Context context) {
        this.id = id;
        this.context = context;

        dClass = new ClassTypes(context);
        dEquipments = new Equipments(context);
        dItems = new Items(context);
        dFeats = new Feats(context);
        dPlayerAbilities = new PlayerAbilities(context);
        //dRaces = new Races(context);
        dSkills = new Skills(context);
        dSpells = new Spells(context);

        pullFromDatabase();
    }

    public Actor(Context context) { //Saving/Loading

        dActor = new Actors(context);

        this.context = context;

        dClass = new ClassTypes(context);
        dEquipments = new Equipments(context);
        dItems = new Items(context);
        dFeats = new Feats(context);
        dPlayerAbilities = new PlayerAbilities(context);
        dSkills = new Skills(context);
        dSpells = new Spells(context);

        this.id = dActor.create(this);

        classTypeId = new ClassType();
        playerAbilityIds = new PlayerAbility();

        for(int i=0;i<40;i++)skillIds.add(new Skill(id, i+1));

        //dropAllTables();

    }

    public void equalsDatabaseValues(Actor a) {
        this.gender = a.gender;
        this.size = a.size;
        this.alignment = a.alignment;
        this.weight = a.weight;
        this.religion = a.religion;
        this.race = a.race;
        this.name = a.name;
        this.isMonster = a.isMonster;
        this.inGame = a.inGame;
        this.imageFile = a.imageFile;
    }

    //public Actor() {}

    public void dropAllTables() {
        dActor.dropTable();
        dClass.dropTable();
        dEquipments.dropTable();
        dItems.dropTable();
        dFeats.dropTable();
        dPlayerAbilities.dropTable();
        dSkills.dropTable();
        dSpells.dropTable();
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
    private String race;

    private int speed;
    private int initiativeMod; //this is the modifier plus the roll

    private int initiative; //this is the modifier plus the roll
    private int grappleMod;

    private int reflexSave; private int reflexMod;
    private int willSave;   private int willMod;
    private int fortSave;   private int fortMod;

    private int isMonster;
    private int inGame;

    private String imageFile;
    private int gameId;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Sets and Gets
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public int getInitiative() {
        return initiative;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    //In Database
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
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
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
    public String getImageFile() {
        return imageFile;
    }
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    //Calculated
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

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Saving and loading from database
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void pullFromDatabase(){//writes over all values in actor object with those in DB

        this.equalsDatabaseValues(dActor.retrieve(id));

        populateSkills();
        populateEquippedItems();
        populateItems();
        populateSpells();
        populateFeats();

        populateClassType();
        populatePlayerAbility();
        //populateRace();

    }

    public void pushToDatabase(){
        dActor.update(this);

        for(int i=0;i<skillIds.size();i++) dSkills.update(skillIds.get(i));
        for(int i=0;i<equippedItemIds.size();i++) dEquipments.update(equippedItemIds.get(i));
        for(int i=0;i<itemIds.size();i++) dItems.update(itemIds.get(i));
        for(int i=0;i<spellIds.size();i++) dSpells.update(spellIds.get(i));
        for(int i=0;i<featIds.size();i++) dFeats.update(featIds.get(i));

        dClass.update(classTypeId);
        dPlayerAbilities.update(playerAbilityIds);

    }

    public boolean populateSkills()         {ArrayList o=dSkills.getAllByPlayerId(id);skillIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateEquippedItems()  {ArrayList o=dEquipments.getAllByPlayerId(id);equippedItemIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateItems()          {ArrayList o=dItems.getAllByPlayerId(id);equippedItemIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateSpells()         {ArrayList o=dSpells.getAllByPlayerId(id);spellIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateFeats()          {ArrayList o=dFeats.getAllByPlayerId(id);featIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database

    public boolean populateClassType()      {ArrayList<ClassType> o=dClass.getAllByPlayerId(id);if(!o.isEmpty())classTypeId = o.get(0); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populatePlayerAbility()  {ArrayList<PlayerAbility> o=dPlayerAbilities.getAllByPlayerId(id);if(!o.isEmpty())playerAbilityIds = o.get(0); return o.isEmpty();}// writes over current variables with those from the database
    //public boolean populateRace()           {ArrayList<Race> o=dRaces.getAllByPlayerId(id);if(!o.isEmpty())raceId = o.get(0); return o.isEmpty();}// writes over current variables with those from the database

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs - We may not need objects for any of these
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ArrayList<Equipment> equippedItemIds = new ArrayList<Equipment>();
    private ArrayList<Item> itemIds = new ArrayList<Item>();
    private ArrayList<Feat> featIds = new ArrayList<Feat>();
    private ArrayList<Skill> skillIds = new ArrayList<Skill>();    //Make 40 of these
    private ArrayList<Spell> spellIds = new ArrayList<Spell>();

    private ClassType classTypeId = new ClassType();
    private MonsterType monsterTypeId = new MonsterType();  // TODO: 11/17/2015  
    private PlayerAbility playerAbilityIds = new PlayerAbility();
    //private Race raceId = new Race();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"get" and "set" functions - look through database to find the following items
    //"get uses known database id to search the value from the database
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    //public int getAbility(int i)        {if(i!=NULL)return 0; else return 0;}
    public int getStr() {return playerAbilityIds.getStrengthAbility();}
    public int getDex() {return playerAbilityIds.getDexAbility();}
    public int getCon() {return playerAbilityIds.getConstAbility();}
    public int getInt() {return playerAbilityIds.getIntelAbility();}
    public int getWis() {return playerAbilityIds.getWisdomAbility();}
    public int getCha() {return playerAbilityIds.getCrismaAbility();}

    public ClassType getClassType()     {return classTypeId;}

    //public Race getRace()               {return raceId;}

    public void setStr(int value)    {playerAbilityIds.setStrengthAbility(value);}
    public void setDex(int value)    {playerAbilityIds.setDexAbility(value);}
    public void setCon(int value)    {playerAbilityIds.setConstAbility(value);}
    public void setWis(int value)    {playerAbilityIds.setWisdomAbility(value);}
    public void setInt(int value)    {playerAbilityIds.setIntelAbility(value);}
    public void setCha(int value)    {playerAbilityIds.setCrismaAbility(value);}

    public void setClassType(ClassType value)  {classTypeId = value;}

    //public void setRace(String value)       {/*add id to database*/}

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"add" and "remove" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#


    public ArrayList<Skill> getSkillIds() {
        return skillIds;
    }
    public void setSkillIds(ArrayList<Skill> skillIds) {
        this.skillIds = skillIds;
    }

    public int getSkill(int i)                  {if(i>=0&&i<40)return dSkills.retrieve(skillIds.get(i).getSkillId()).getBaseScore(); else return -1000;}
    public void setSkill(int i, int value)      {if(i>=0&&i<40){skillIds.get(i).setBaseScore(value); dSkills.update(skillIds.get(i));}}
    public int getSkillMod(int i)               {if(i>=0&&i<40)return dSkills.retrieve(skillIds.get(i).getSkillId()).getMiscBonus(); else return -1000;}
    public void setSkillMod(int i, int value)   {if(i>=0&&i<40){skillIds.get(i).setMiscBonus(value); dSkills.update(skillIds.get(i));}}

    public Equipment getEquippedItem(int ID)    {return dEquipments.retrieve(ID);}
    public Spell getSpell(int ID)               {return dSpells.retrieve(ID);}
    public Feat getFeat(int ID)                 {return dFeats.retrieve(ID);}

    public void addEquippedItems(int ID)    {Equipment e = new Equipment(this.id,ID); dEquipments.create(e);}
    public void addSpells(int ID)           {Spell s = new Spell(this.id,ID); dSpells.create(s);}
    public void addFeats(int ID)            {Feat f = new Feat(this.id,ID); dFeats.create(f);}

    public void removeEquippedItems(int ID) {dEquipments.delete(ID);}
    public void removeSpells(int ID)        {dSpells.delete(ID);}
    public void removeFeats(int ID)         {dSpells.delete(ID);}

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"calculate" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void calculateSaves(){}

    public void rollInitiative() {initiative = initiativeMod + roll(20)/*replace 10 with roll activity*/;}            // TODO: 11/10/2015 An activity needs to be connected to this

    public int calculateAC() {return 0;}// TODO: 11/11/2015  Need to figure out calculation for this.
    public int calculateTouchAC() {return 0;}// TODO: 11/11/2015 Need to figure out calculation for this.
    public int calculateFlatFootedAC() {return 0;}// TODO: 11/11/2015 Need to figure out calculation for this.

    public int rollToHit(){return calculateBAB();}// TODO: 11/15/2015 dx+BAB+STR
    public int calculateDamage(){return 0;}// TODO: 11/15/2015 1 handed = (1dx + str/2*(number of hands))

    public int rollToHitRanged(){return 0;}// TODO: 11/15/2015 bab+dx+DEX+RANGED MOD
    public int calculateRangeMod(){return -2;}// TODO: 11/15/2015 -2*(range/range_increment)
    public int calculateDamageRanged(){return 0;}// TODO: 11/15/2015 1dx

    public int calculateBAB(){return 0;}// TODO: 11/15/2015 returns first value in BAB list
    public int numOfAttacks(){return (calculateBAB() - 1)/5 + 1;}// TODO: 11/15/2015 Num of attacks - based off of bab

    public int grappleCheck(){return 0;}// TODO: 11/15/2015 Bab + Str
    
    public int calculateHealth(){return 0;}// TODO: 11/15/2015 (.5*hit_die+1)*(level-1) + hit_die + const_mod*level 
    
    public int calculateSkillPoints(){return 0;}// TODO: 11/15/2015 4*(skill_points+int_mod) @ level 1 and skill_points+int_mod

    public int roll(int i){return 0;}// TODO: 11/15/2015 NEEDS AN ACTIVITY FOR ROLLING DIE 
    public int getRollNum(String s){return 0;}// TODO: 11/15/2015

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //activity functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

}
