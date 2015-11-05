package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Spell extends Model {
    private int playerId;
    private int spellId;

    public Spell(int id, int playerId, int spellId) {
        this.id = id;
        this.playerId = playerId;
        this.spellId = spellId;
    }

    public Spell(int playerId, int spellId) {
        this.playerId = playerId;
        this.spellId = spellId;
    }

    public Spell() {
        this(0, 0, 0);
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
