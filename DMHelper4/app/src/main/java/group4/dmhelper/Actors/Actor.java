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


    public Actor() {

    }

    //Various Identifiers

    boolean monster;    //this class will cover both monsters and humans

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Various Player Attributes
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    private String name;
    private String gender;
    int size;
    String alignment;
    int weight;
    String religion;

    int initiativeMod; //this is the modifier plus the roll

    int reflexSave; int reflexMod;
    int willSave;   int willMod;
    int fortSave;   int fortMod;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Player IDs - We may not need objects for any of these
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    int playerAbilitiesId;
    int equippedItemsId;
    ArrayList<Integer> localSkillIds = new ArrayList<Integer>();
    int classTypeId;
    int playerWeaponsId;
    int raceId;

    ArrayList<Integer> spellIds = new ArrayList<Integer>();


    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"get" functions - look through database to find the following items
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void getAbilities() {
    }
    public void getEquippedItems() {
    }
    public void getSkills() {
    }
    public void getClassType() {
    }
    public void getWeapons() {
    }
    public void getRace() {
    }
    public void getSpells() {
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //"calculate" functions
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public int rollToHit(){
        return 0;
    }
    public int calculateDamageEquippedItem(){
        return 0;
    }
}
