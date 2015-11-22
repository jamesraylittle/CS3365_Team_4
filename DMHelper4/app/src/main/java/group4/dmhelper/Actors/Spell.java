package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Spell extends Model {
    private int playerId;
    private int spellId;
    private String spellName;

    public Spell(int id, int playerId, int spellId, String spellName) {
        this.id = id;
        this.playerId = playerId;
        this.spellId = spellId;
        this.spellName = spellName;
    }

    public Spell(int playerId, int spellId, String spellName) {
        this.playerId = playerId;
        this.spellId = spellId;
        this.spellName = spellName;
    }

    public Spell() {
        this(0, 0, 0, "");
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

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }
}
