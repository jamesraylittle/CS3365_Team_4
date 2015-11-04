package group4.dmhelper.Actors;

import java.util.ArrayList;

/**
 *
 * Created by Daniel on 10/19/2015.
 * Edited 11/2/2015
 */

public class Actor extends Model{


    public Actor(int playerId) {this.id = playerId;}   //TODO - still needs to populate private variables from database

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
    private int grappleMod;

    private int reflexSave; private int reflexMod;
    private int willSave;   private int willMod;
    private int fortSave;   private int fortMod;


    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Sets and Gets
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

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

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Saving and loading from database
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void pullVariables()         {/*takes variables from database*/} // TODO: 11/2/2015
    public void pushVariables()         {/*writes over current variables in database*/} // TODO: 11/2/2015

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs - We may not need objects for any of these
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ArrayList<Integer> playerAbilityIds = new ArrayList<Integer>();// TODO: 11/4/2015 Create "sets" and "adds"
    private ArrayList<Integer> playerWeaponIds = new ArrayList<Integer>();
    private ArrayList<Integer> itemIds = new ArrayList<Integer>();// // TODO: 11/4/2015 Create "sets" and "adds"
    private ArrayList<Integer> equippedItemIds = new ArrayList<Integer>();
    private ArrayList<Integer> localSkillIds = new ArrayList<Integer>();// TODO: 11/4/2015 Create "sets" and "adds"
    private ArrayList<Integer> spellIds = new ArrayList<Integer>();
    private ArrayList<Integer> featIds = new ArrayList<Integer>();

    private int classTypeId;
    private int raceId;

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
    public boolean populateWeapons()        {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateSpells()         {return true;/*query database - populate ArrayList*/}   //TODO
    public boolean populateFeats()          {return true;/*query database - populate ArrayList*/}   //TODO

    public void getSkills()                     {/*query database - populate ArrayList*/}           //TODO
    public int getSkills(String S)              {return 0;/*search by string - query database*/}    //TODO
    public void setSkills(int i, int value)     {/*query database - populate ArrayList*/}           //TODO
    public void setSkills(String S, int value)  {/*search by string - query database*/}             //TODO

    public void getEquippedItem()           {if(populateEquippedItems())/*query database - populate ArrayList*/;}   //TODO
    public int getEquippedItem(String S)    {if(populateEquippedItems())return 0; else return 0;/*search by string - query database*/}  //TODO

    public void getWeapons()                {if(populateWeapons())/*query database - populate ArrayList*/;} //TODO
    public int getWeapons(String S)         {if(populateWeapons())return 0; else return 0;/*search by string - query database*/}    //TODO

    public void getSpells()                 {if(populateSpells())/*query database - populate ArrayList*/;}  //TODO
    public int getSpells(String S)          {if(populateSpells())return 0; else return 0;/*search by string - query database*/} //TODO

    public void getFeats()                  {if(populateFeats())/*query database - populate ArrayList*/;}   //TODO
    public int getFeats(String S)           {if(populateFeats())return 0; else return 0;/*search by string - query database*/}  //TODO

    public void addEquippedItems(int ID)    {/*add id to database*/}    //TODO
    public void addWeapons(int ID)          {/*add id to database*/}    //TODO
    public void addSpells(int ID)           {/*add id to database*/}    //TODO
    public void addFeats(int ID)            {/*add id to database*/}    //TODO

    public void removeEquippedItems(int ID) {if(populateEquippedItems())/*add id to database*/;}    //TODO
    public void removeWeapons(int ID)       {if(populateWeapons())/*add id to database*/;}  //TODO
    public void removeSpells(int ID)        {if(populateSpells())/*add id to database*/;}   //TODO
    public void removeFeats(int ID)         {if(populateFeats())/*add id to database*/;}    //TODO

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"calculate" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public int calculateAC() {return 0;}            // TODO: 11/2/2015
    public int calculateTouchAC() {return 0;}       // TODO: 11/2/2015
    public int calculateFlatFootedAC() {return 0;}  // TODO: 11/2/2015
    public int rollToHit(){return 0;}               //TODO
    public int calculateDamage(){return 0;}         //TODO
}
