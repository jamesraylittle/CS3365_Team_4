package group4.dmhelper.Actors;

import android.content.Context;
import java.util.ArrayList;

import group4.dmhelper.Database.Actors;
import group4.dmhelper.Database.ClassTypes;
import group4.dmhelper.Database.DataBaseHelper;
import group4.dmhelper.Database.Equipments;
import group4.dmhelper.Database.Feats;
import group4.dmhelper.Database.Games;
import group4.dmhelper.Database.Items;
import group4.dmhelper.Database.MonsterTypes;
import group4.dmhelper.Database.PlayerAbilities;
//import group4.dmhelper.Database.Races;
import group4.dmhelper.Database.Skills;
import group4.dmhelper.Database.Spells;
import group4.dmhelper.globalVariables;

/**
 *
 * Created by Daniel on 10/19/2015.
 * Edited 11/2/2015
 * Edited 11/19/2015
 */

public class Actor extends Model implements Comparable<Actor>{

    globalVariables gv = globalVariables.getInstance();
    DataBaseHelper dbh;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Various Player Attributes
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private String name;
    private String gender;
    private int size;
    private String alignment;
    private String weight;
    private String height;
    private String religion;
    private String race;

    private int speed;
    private int initiativeMod; //this is the modifier plus the roll

    private int initiative; //this is the modifier plus the roll
    //private int grappleMod; //calc

    private int reflexSave; //calc
    private int reflexMod;
    private int willSave;   //calc
    private int willMod;
    private int fortSave;   //calc
    private int fortMod;

    private int isMonster;
    private int inGame;

    private String imageFile;
    private int gameId;

    private String playerName;
    private int XP;
    private int health;

    private String className;

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
    private MonsterTypes dMonster;

    Context context;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Constructors
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public Actor(String name, String gender, int size, String alignment, String weight, String height, String religion, String race, int speed, int initiativeMod, int initiative, int reflexMod, int willMod, int fortMod, int isMonster, int inGame, String imageFile, int gameId, String playerName, int XP, int health, String className) {
        this.name = name;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.height = height;
        this.religion = religion;
        this.race = race;
        this.speed = speed;
        this.initiativeMod = initiativeMod;
        this.initiative = initiative;
        this.reflexMod = reflexMod;
        this.willMod = willMod;
        this.fortMod = fortMod;
        this.isMonster = isMonster;
        this.inGame = inGame;
        this.imageFile = imageFile;
        this.gameId = gameId;
        this.playerName = playerName;
        this.XP = XP;
        this.health = health;
        this.className = className;
    }

    public Actor(int id, String name, String gender, int size, String alignment, String weight, String height, String religion, String race, int speed, int initiativeMod, int initiative, int reflexMod, int willMod, int fortMod, int isMonster, int inGame, String imageFile, int gameId, String playerName, int XP, int health, String className) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.height = height;
        this.religion = religion;
        this.race = race;
        this.speed = speed;
        this.initiativeMod = initiativeMod;
        this.initiative = initiative;
        this.reflexMod = reflexMod;
        this.willMod = willMod;
        this.fortMod = fortMod;
        this.isMonster = isMonster;
        this.inGame = inGame;
        this.imageFile = imageFile;
        this.gameId = gameId;
        this.playerName = playerName;
        this.XP = XP;
        this.health = health;
        this.className = className;
    }

    public Actor(int id, Context context) {

        this.id = id;
        this.context = context;

        dActor = new Actors(context);

        dClass = new ClassTypes(context);
        dEquipments = new Equipments(context);
        dItems = new Items(context);
        dFeats = new Feats(context);
        dPlayerAbilities = new PlayerAbilities(context);
        dSkills = new Skills(context);
        dSpells = new Spells(context);
        dMonster = new MonsterTypes(context);

        classTypeId = new ClassType();
        playerAbilityIds = new PlayerAbility();

       // for(int i=0;i<40;i++)skillIds.add(new Skill(id, i+1));

        pullFromDatabase();
    }

    //height and weight and class string change

    public Actor(Context context) { //Saving/Loading

        this("","",4,"Neutral","150","5'5\"","","Human",30,0,1,0,0,0,0,1,"",0,"",0,4,"Barbarian");

        dbh = new DataBaseHelper(context);
        this.gameId = gv.getGameId();

        dActor = new Actors(context);

        this.context = context;

        gameId = gv.getGameId();

        dClass = new ClassTypes(context);
        dEquipments = new Equipments(context);
        dItems = new Items(context);
        dFeats = new Feats(context);
        dPlayerAbilities = new PlayerAbilities(context);
        dSkills = new Skills(context);
        dSpells = new Spells(context);
        dMonster = new MonsterTypes(context);

        this.id = dActor.create(this);

        classTypeId = new ClassType(id,-1,-1);
        classTypeId.id = dClass.create(classTypeId);

        monsterTypeId = new MonsterType(id,0,0);
        monsterTypeId.id = dMonster.create(monsterTypeId);

        playerAbilityIds = new PlayerAbility(id,10,10,10,10,10,10);
        playerAbilityIds.id = dPlayerAbilities.create(playerAbilityIds);

        for(int i=0;i<40;i++) {
            skillIds.add(new Skill(0, 0, "", i + 1, 0));
            skillIds.get(i).id = dSkills.create(skillIds.get(i));
        }

        pushToDatabase();
    }

    public void equalsDatabaseValues(Actor a) {
        this.id = a.getId();
        this.name = a.getName();
        this.gender = a.getGender();
        this.size = a.getSize();
        this.alignment = a.getAlignment();
        this.weight = a.getWeight();
        this.height = a.getHeight();
        this.religion = a.getReligion();
        this.race = a.getRace();
        this.speed = a.getSpeed();
        this.initiativeMod = a.getInitiativeMod();
        this.initiative = a.getInitiative();
        this.reflexMod = a.getReflexMod();
        this.willMod = a.getWillMod();
        this.fortMod = a.getFortMod();
        this.isMonster = a.getIsMonster();
        this.inGame = a.getInGame();
        this.imageFile = a.getImageFile();
        this.gameId = a.getGameId();
        this.playerName = a.getPlayerName();
        this.XP = a.getXP();
        this.health = a.getHealth();
    }

    //public Actor() {}

    public void dropAllTables() {
        Games g = new Games(context);
        g.dropTable();
        dActor.dropTable();
        dClass.dropTable();
        dEquipments.dropTable();
        dItems.dropTable();
        dFeats.dropTable();
        dPlayerAbilities.dropTable();
        dSkills.dropTable();
        dSpells.dropTable();
        dMonster.dropTable();
    }

    @Override
    public int compareTo(Actor actor) {
        if (this.initiative > actor.getInitiative()) return 1;
        else if (this.initiative < actor.getInitiative()) return -1;
        else return 0;
    }


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
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
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
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getXP() {
        return XP;
    }
    public void setXP(int XP) {
        this.XP = XP;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
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
        populateMonsterType();

    }

    public void pushToDatabase(){
        dActor.update(this);

        for(int i=0;i<skillIds.size();i++) dSkills.update(skillIds.get(i));
        for(int i=0;i<equippedItemIds.size();i++) dEquipments.update(equippedItemIds.get(i));
        for(int i=0;i<itemIds.size();i++) dItems.update(itemIds.get(i));
        for(int i=0;i<spellIds.size();i++) dSpells.update(spellIds.get(i));
        for(int i=0;i<featIds.size();i++) dFeats.update(featIds.get(i));

        dClass.update(classTypeId);//
        dPlayerAbilities.update(playerAbilityIds);

    }

    public boolean populateSkills()         {ArrayList o=dSkills.getAllByPlayerId(id);skillIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateEquippedItems()  {ArrayList o=dEquipments.getAllByPlayerId(id);equippedItemIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateItems()          {ArrayList o=dItems.getAllByPlayerId(id);equippedItemIds.equals(o);return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateSpells()         {ArrayList o=dSpells.getAllByPlayerId(id);spellIds.equals(o);return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateFeats()          {ArrayList o=dFeats.getAllByPlayerId(id);featIds.equals(o); return o.isEmpty();}// writes over current variables with those from the database

    public boolean populateClassType()      {ArrayList<ClassType> o=dClass.getAllByPlayerId(id);if(!o.isEmpty())classTypeId = o.get(0); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populatePlayerAbility()  {ArrayList<PlayerAbility> o=dPlayerAbilities.getAllByPlayerId(id);if(!o.isEmpty())playerAbilityIds = o.get(0); return o.isEmpty();}// writes over current variables with those from the database
    public boolean populateMonsterType()    {ArrayList<MonsterType> o=dMonster.getAllByPlayerId(id);if(!o.isEmpty())monsterTypeId = o.get(0); return o.isEmpty();}// writes over current variables with those from the database
    //public boolean populateRace()           {ArrayList<Race> o=dRaces.getAllByPlayerId(id);if(!o.isEmpty())raceId = o.get(0); return o.isEmpty();}// writes over current variables with those from the database

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ArrayList<Equipment> equippedItemIds = new ArrayList<Equipment>();
    private ArrayList<Item> itemIds = new ArrayList<Item>();
    private ArrayList<Feat> featIds = new ArrayList<Feat>();//
    private ArrayList<Skill> skillIds = new ArrayList<Skill>();    //Make 40 of these
    private ArrayList<Spell> spellIds = new ArrayList<Spell>();

    private ClassType classTypeId = new ClassType();
    private MonsterType monsterTypeId = new MonsterType();
    private PlayerAbility playerAbilityIds = new PlayerAbility();
    //private Race raceId = new Race();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"get" and "set" functions for id objects
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public MonsterType getMonsterTypeId() {return monsterTypeId;}
    public void setMonsterTypeId(MonsterType monsterTypeId) {this.monsterTypeId = monsterTypeId;}
    public ClassType getClassTypeId()     {return classTypeId;}
    public void setClassTypeId(ClassType value)  {classTypeId = value;}

    public int getStrength()    {return playerAbilityIds.getStrengthAbility();}
    public int getDexterity()   {return playerAbilityIds.getDexAbility();}
    public int getConstitution(){return playerAbilityIds.getConstAbility();}
    public int getIntel()       {return playerAbilityIds.getIntelAbility();}
    public int getWisdom()      {return playerAbilityIds.getWisdomAbility();}
    public int getCharisma()    {return playerAbilityIds.getCrismaAbility();}

    public void setStrength(int value)      {playerAbilityIds.setStrengthAbility(value);}
    public void setDexterity(int value)     {playerAbilityIds.setDexAbility(value);}
    public void setConstitution(int value)  {playerAbilityIds.setConstAbility(value);}
    public void setWisdom(int value)        {playerAbilityIds.setWisdomAbility(value);}
    public void setIntel(int value)         {playerAbilityIds.setIntelAbility(value);}
    public void setCharisma(int value)      {playerAbilityIds.setCrismaAbility(value);}

    public int getSTR() {if(this.getStrength()>=10)return (this.getStrength()-10)/2; else return (this.getStrength()-11)/2;}
    public int getDEX() {if(this.getDexterity()>=10)return (this.getDexterity()-10)/2; else return (this.getDexterity()-11)/2;}
    public int getCON() {if(this.getConstitution()>=10)return (this.getConstitution()-10)/2; else return (this.getConstitution()-11)/2;}
    public int getWIS() {if(this.getIntel()>=10)return (this.getIntel()-10)/2; else return (this.getIntel()-11)/2;}
    public int getINT() {if(this.getWisdom()>=10)return (this.getWisdom()-10)/2; else return (this.getWisdom()-11)/2;}
    public int getCHA() {if(this.getCharisma()>=10)return (this.getCharisma()-10)/2; else return (this.getCharisma()-11)/2;}

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"add" and "remove" functions for id objects
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#


    public ArrayList<Skill> getSkillIds() { return skillIds;}
    public void setSkillIds(ArrayList<Skill> skillIds) { this.skillIds = skillIds;}
    public ArrayList<Equipment> getEquippedItemIds() { return equippedItemIds;}
    public void setEquippedItemIds(ArrayList<Equipment> equippedItemIds) { this.equippedItemIds = equippedItemIds;}
    public ArrayList<Item> getItemIds() {return itemIds;}
    public void setItemIds(ArrayList<Item> itemIds) { this.itemIds = itemIds; }
    public ArrayList<Feat> getFeatIds() { return featIds;}
    public void setFeatIds(ArrayList<Feat> featIds) { this.featIds = featIds;}
    public ArrayList<Spell> getSpellIds() { return spellIds;}
    public void setSpellIds(ArrayList<Spell> spellIds) { this.spellIds = spellIds;}

    public Skill getSkill(int ID)   {
        for(int i=0;i<40;i++)
            if(skillIds.get(i).id == ID)
                return skillIds.get(i);
        return null;
    }

    /*
    public void setSkill(int i, int value)      {if(i>=0&&i<40){skillIds.get(i).setBaseScore(value); dSkills.update(skillIds.get(i));}}
    public int getSkillMod(int i)               {if(i>=0&&i<40)return dSkills.retrieve(skillIds.get(i).getSkillId()).getMiscBonus(); else return -1000;}
    public void setSkillMod(int i, int value)   {if(i>=0&&i<40){skillIds.get(i).setMiscBonus(value); dSkills.update(skillIds.get(i));}}//*/

    public Item getItem(int ID)                 {return dItems.retrieve(ID);}
    public Equipment getEquippedItem(int ID)    {return dEquipments.retrieve(ID);}
    public Spell getSpell(int ID)               {return dSpells.retrieve(ID);}
    public Feat getFeat(int ID)                 {return dFeats.retrieve(ID);}

    public void addItems(int ID)                {Item e = new Item(this.id,ID,dbh.retrieveItemByID(ID)[1]); dItems.create(e);}
    public void addEquippedItems(int ID)        {Equipment e = new Equipment(this.id,ID,dbh.retrieveEquipmentByID(ID)[1],0); dEquipments.create(e);}
    public void addSpells(int ID)               {Spell s = new Spell(this.id,ID,dbh.retrieveSpellByID(ID)[1]); dSpells.create(s);}
    public void addFeats(int ID)                {Feat f = new Feat(this.id,ID,dbh.retrieveFeatByID(ID)[1]); dFeats.create(f);}

    /*
    public void removeItems(int ID)         {dEquipments.delete(ID);}
    public void removeEquippedItems(int ID) {dEquipments.delete(ID);}
    public void removeSpells(int ID)        {dSpells.delete(ID);}
    public void removeFeats(int ID)         {dFeats.delete(ID);}//*/

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"calculate" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void calculateSaves(){}

    public int rollInitiative(int iRoll) {initiative = iRoll+initiativeMod; return initiative;}
    public int calculateBAB(){return 0;}// TODO: 11/15/2015 returns first value in BAB list
    public int rollToHit(){return calculateBAB();}// TODO: 11/15/2015 dx+BAB+STR

    //*
    public int calculateAC() {return 0;}// TODO: 11/11/2015  Need to figure out calculation for this.
    public int calculateTouchAC() {return 0;}// TODO: 11/11/2015 Need to figure out calculation for this.
    public int calculateFlatFootedAC() {return 0;}// TODO: 11/11/2015 Need to figure out calculation for this.

    public int calculateDamage(){return 0;}// TODO: 11/15/2015 (roll + str/2*(number of hands))

    public int rollToHitRanged(){return 0;}// TODO: 11/15/2015 bab+dx+DEX+RANGED MOD
    public int calculateRangeMod(){return -2;}// TODO: 11/15/2015 -2*(range/range_increment)
    public int calculateDamageRanged(){return 0;}// TODO: 11/15/2015 1dx

    public int numOfAttacks(){return (calculateBAB() - 1)/5 + 1;}// TODO: 11/15/2015 Num of attacks - based off of bab

    public int grappleCheck(){return 0;}// TODO: 11/15/2015 Bab + Str
    //*/

    public int calculateMaxHealth(){
        if(classTypeId.getClassId() == 0) return 10;
        String sHd = dbh.retrieveClassByID(classTypeId.getClassId())[4];
        int iHd = Integer.parseInt(sHd.substring(1));
        String sLvl = dbh.retrieveClassTableByID(classTypeId.getClass_tableId())[2];
        int iLvl = Integer.parseInt(sLvl);
        return (iHd/2+1)*(iLvl-1)+iHd+getCON()*iLvl;
    }

    public int calculateSkill(int ID){
        String key_ability = dbh.retrieveSkillByID(ID)[3];
        int abilityBonus = 0;
        switch(key_ability) {
            case "Str": abilityBonus = getSTR(); break;
            case "Dex": abilityBonus = getDEX(); break;
            case "Con": abilityBonus = getCON(); break;
            case "Wis": abilityBonus = getWIS(); break;
            case "Int": abilityBonus = getINT(); break;
            case "Cha": abilityBonus = getCHA(); break;
        }
        return getSkill(ID).getBaseScore() + abilityBonus;
    }

    public int calculateSkillPoints(){return 0;}// TODO: 11/15/2015 4*(skill_points+int_mod) @ level 1 and skill_points+int_mod

    public int roll(int i){return 0;}// TODO: 11/15/2015 NEEDS AN ACTIVITY FOR ROLLING DIE 
    public int getRollNum(String s){return 0;}// TODO: 11/15/2015

    public int getCritRoll(){return 20;}// TODO: 11/18/2015 returns lowest crit roll

    public int getDamage(ArrayList<Integer> critHits, ArrayList<Integer> hits, int numOfHands){
        int sum=0;

        for(int i=0;i<critHits.size();i++)sum += (critHits.get(i)+getSTR()/2*numOfHands)*2;
        for(int i=0;i<hits.size();i++)sum += hits.get(i)+getSTR()/2*numOfHands;

        return sum;
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //activity functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

}
