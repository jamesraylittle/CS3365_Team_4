package group4.dmhelper.Actors;

import java.util.ArrayList;

/**
 * It seems the attributes have IDs inside the database itself.  We may take out the classes that are just generic, such as skill, race, etc...
 *
 * On the other hand, we could have the actor create all of these classes and the classes themselves could be delegated the parsing code.
 *
 * Either way, it shouldn't be necessary for each individual class to have its own table.
 *
 * Created by Daniel on 10/19/2015.
 */

public class Actor extends Model{


    public Actor(int i) {id = i;}

    //Various Identifiers

    boolean monster;    //this class will cover both monsters and humans

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Various Player Attributes
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private String name;
    private String gender;
    private int size;
    private String alignment;
    private float weight;
    private String religion;

    private ArrayList<Integer> playerAbilities = new ArrayList<Integer>();

    private int speed;
    private int initiativeMod; //this is the modifier plus the roll
    private int grappleMod;

    private int reflexSave; private int reflexMod;
    private int willSave;   private int willMod;
    private int fortSave;   private int fortMod;

    private int AC;

    private ArrayList<Integer> localSkills = new ArrayList<Integer>();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Sets and Gets
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public String getName()         {return name;}
    public String getGender()       {return gender;}
    public int getSize()            {return size;}
    public String getAlignment()    {return alignment;}
    public float getWeight()        {return weight;}
    public String getReligion()     {return religion;}

    public int getAbility(int i)    {if(i>0 && i<playerAbilities.size()) return playerAbilities.get(i);else return 0;}

    public int getSpeed()           {return speed;}
    public int getInitiativeMod()   {return initiativeMod;}
    public int getGrappleMod()      {return grappleMod;}

    public int getReflexSave()      {return reflexSave;}
    public int getWillSave()        {return willSave;}
    public int getFortSave()        {return fortSave;}

    public int getReflexMod()       {return reflexMod;}
    public int getWillMod()         {return willMod;}
    public int getFortMod()         {return fortMod;}

    public int getAC()              {return AC;}


    public void setName(String value)       {name = value;}
    public void setGender(String value)     {gender = value;}
    public void setSize(int value)          {size = value;}
    public void setAlignment(String value)  {alignment = value;}
    public void setWeight(int value)        {weight = value;}
    public void setReligion(String value)   {religion = value;}

    public void setAbility(int i, int value)    {if(i>0 && i<playerAbilities.size()) playerAbilities.set(i, value);}

    public void setSpeed(int value)         {speed = value;}
    public void setInitiativeMod(int value) {initiativeMod = value;}
    public void setGrappleMod(int value)    {grappleMod = value;}

    public void setReflexSave(int value)    {reflexSave = value;}
    public void setWillSave(int value)      {willSave = value;}
    public void setFortSave(int value)      {fortSave = value;}

    public void setReflexMod(int value)     {reflexMod = value;}
    public void setWillMod(int value)       {willMod = value;}
    public void setFortMod(int value)       {fortMod = value;}

    public void setAC(int value)            {AC = value;}

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs - We may not need objects for any of these
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private ArrayList<Integer> equippedItemIds = new ArrayList<Integer>();
    private ArrayList<Integer> playerWeaponIds = new ArrayList<Integer>();
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

    public int rollToHit(){return 0;}   //TODO
    public int calculateDamageEquippedItem(){return 0;}     //TODO
}
