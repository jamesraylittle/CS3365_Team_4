package group4.dmhelper.Actors;

import android.content.Context;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 * Updated by Daniel 11/3/2015
 */
public class Player extends Actor {

    public Player(int playerId, Context context) {
        super(playerId, context); setIsMonster(0);
    }

    public Player(Context context) {
        super(context); setIsMonster(0);
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Leveling Up
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    public void levelUp() {}// TODO: 11/2/2015 This should have it's own activity associated with it.

    //TODO: get relation objects using ids i.e. getSkills() : List[Skills]
}
