package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 * Updated by Daniel 11/3/2015
 */
public class Player extends Actor {

    // TODO: 11/3/2015 This object needs to have abilities, weapons, etc in order to populate everything for the player
    public Player(int actorId) {
        super(actorId);
        this.id = actorId;
        //TODO - still needs to populate private variables from database
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Leveling Up
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    public void levelUp() {}// TODO: 11/2/2015 This should have it's own activity associated with it.

    //TODO: get relation objects using ids i.e. getSkills() : List[Skills]
}
