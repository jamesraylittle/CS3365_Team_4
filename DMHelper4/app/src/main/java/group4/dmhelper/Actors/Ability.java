package group4.dmhelper.Actors;

import java.util.ArrayList;

/**
 * This is a generic class for things with a base score and misc modifiers
 * Created by Daniel on 10/19/2015.
 */
public class Ability {
    int baseScore;
    ArrayList<Integer> miscBonus = new ArrayList<Integer>();

    public int calculate(){
        return 0;
    }
}
