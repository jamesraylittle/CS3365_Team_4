package group4.dmhelper.Actors;

import android.content.Context;

/**
 * Created by Daniel on 11/02/2015.
 */
public class Monster extends Actor {



    public Monster(int id,Context context) {
        super(id, context); setIsMonster(1);
    }

    public Monster(Context context) {
        super(context); setIsMonster(1);
    }

    public void spillXP() {}//
}
