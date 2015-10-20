package group4.dmhelper.Actors;

/**
 * Notes - Should we keep this as a class since the information is already in the database?  Couldn't we just give the Actor the name of his/her class?
 *
 * Notes - on second thought, nevermind.  It will be better to keep this as a seperate class
 *
 * Created by Daniel on 10/19/2015.
 */
public class ClassType {
    String name;
    int level;
    int attack;
    //int fort; we may just do functions for this
    //int ref;
    //int will;
    int special;
    int playerId;

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing Levels
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void levelUp(){
        //This will increment the level, update all of the save modifiers, etc.
    }

}
