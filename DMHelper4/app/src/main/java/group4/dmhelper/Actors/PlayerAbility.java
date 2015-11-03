package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class PlayerAbility extends Model {
    private int playerId;
    private int strengthAbilityId;
    private int dexAbilityId;
    private int constAbilityId;
    private int intelAbilityId;
    private int wisdomAbilityId;
    private int crismaAbilityId;

    public PlayerAbility(int id, int playerId, int strengthAbilityId, int dexAbilityId, int constAbilityId, int intelAbilityId, int wisdomAbilityId, int crismaAbilityId) {
        this.id = id;
        this.playerId = playerId;
        this.strengthAbilityId = strengthAbilityId;
        this.dexAbilityId = dexAbilityId;
        this.constAbilityId = constAbilityId;
        this.intelAbilityId = intelAbilityId;
        this.wisdomAbilityId = wisdomAbilityId;
        this.crismaAbilityId = crismaAbilityId;
    }
    public PlayerAbility(int playerId, int strengthAbilityId, int dexAbilityId, int constAbilityId, int intelAbilityId, int wisdomAbilityId, int crismaAbilityId) {
        this(0, playerId, strengthAbilityId, dexAbilityId, constAbilityId, intelAbilityId, wisdomAbilityId, crismaAbilityId);
    }
    public PlayerAbility() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public int playerId() { return this.playerId(); }
    public int strengthAbilityId() { return this.strengthAbilityId; }
    public int dexAbilityId() { return this.dexAbilityId; }
    public int constAbilityId() { return this.constAbilityId; }
    public int intelAbilityId() { return this.intelAbilityId; }
    public int wisdomAbilityId() { return this.wisdomAbilityId; }
    public int crismaAbilityId() { return this.crismaAbilityId; }

    public void setPlayerId(int id) { this.playerId = id; }
    public void setStrengthAbilityId(int id) { this.strengthAbilityId = id; }
    public void setDexAbilityId(int id) { this.dexAbilityId = id; }
    public void setConstAbilityId(int id) { this.constAbilityId = id; }
    public void setIntelAbilityId(int id) { this.intelAbilityId = id; }
    public void setWisdomAbilityId(int id) { this.wisdomAbilityId = id; }
    public void setCrismaAbilityId(int id) { this.constAbilityId = id; }
}
