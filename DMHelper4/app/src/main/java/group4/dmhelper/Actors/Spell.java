package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Spell extends Model {
    private int playerId;
    private int spellId;

    public Spell(int id) {
        this.id = id;
        /*This should take all of the information from the database*/
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }
}
