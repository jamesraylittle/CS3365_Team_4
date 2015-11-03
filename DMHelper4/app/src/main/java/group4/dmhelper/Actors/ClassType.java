package group4.dmhelper.Actors;

/**
 * Notes - Should we keep this as a class since the information is already in the database?  Couldn't we just give the Actor the name of his/her class?
 *
 * Notes - on second thought, nevermind.  It will be better to keep this as a seperate class
 *
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class ClassType extends Model {
    private String name;
    private int level;
    private int attack;
    //int fort; we may just do functions for this
    //int ref;
    //int will;
    private int special;
    private int playerId;

    public ClassType(int id, String name, int level, int attack, int special, int playerId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.attack = attack;
        this.special = special;
        this.playerId = playerId;
    }

    public ClassType(String name, int level, int attack, int special, int playerId) {
        this(0, name, level, attack, special, playerId);
    }
    public ClassType() {
        this(0, "", 0, 0, 0, 0);
    }

    public String name() { return this.name; }
    public int level() { return this.level; }
    public int attack() { return this.attack; }
    public int special() { return this.special; }
    public int playerId() { return this.playerId; }

    public void setName(String n) { name = n; }
    public void setLevel(int l) { level = l; }
    public void setAttack(int a) { attack = a; }
    public void setSpecial(int s) { special = s; }
    public void setPlayerId(int id) { playerId = id; }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing Levels
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void levelUp(){
        //This will increment the level, update all of the save modifiers, etc.
    }

}
