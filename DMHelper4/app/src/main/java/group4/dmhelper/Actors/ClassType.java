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
    private int playerId;
    private int classId;
    private int class_tableId;  //This represents level

    public ClassType(int id, int playerId, int classId, int class_tableId) {
        this.id = id;
        this.playerId = playerId;
        this.classId = classId;
        this.class_tableId = class_tableId;
    }

    public ClassType(int playerId, int classId, int class_tableId) {
        this.playerId = playerId;
        this.classId = classId;
        this.class_tableId = class_tableId;
    }

    public ClassType() {
        this(0,0,0,0);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClass_tableId() {
        return class_tableId;
    }

    public void setClass_tableId(int class_tableId) {
        this.class_tableId = class_tableId;
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing Levels
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void levelUp(){
        //This will increment the level, update all of the save modifiers, etc.
    }

}
