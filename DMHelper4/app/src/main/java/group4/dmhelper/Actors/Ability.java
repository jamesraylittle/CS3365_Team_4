package group4.dmhelper.Actors;

import java.util.ArrayList;

/**
 * This is a generic class for things with a base score and misc modifiers
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Ability extends Model {
    int baseScore;
    int miscBonus;
    String name;
    //ArrayList<Integer> miscBonus = new ArrayList<Integer>();

    public Ability(int playerId) {
        this.id = playerId;
        /*This should take all of the information from the database and fill the local variables*/
    }

    public int getBaseScore() { return this.baseScore; }
    public int getMiscBonus() { return this.miscBonus; }
    public String getName() { return this.name; }

    public void setBaseScore(int b) { this.baseScore = b; }
    public void setMiscBonus(int m) { this.miscBonus = m; }
    public void setName(String name) { this.name = name; }

    public int calculate(){
        return 0;
    }
}
