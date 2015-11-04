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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getStrengthAbilityId() {
        return strengthAbilityId;
    }

    public void setStrengthAbilityId(int strengthAbilityId) {
        this.strengthAbilityId = strengthAbilityId;
    }

    public int getDexAbilityId() {
        return dexAbilityId;
    }

    public void setDexAbilityId(int dexAbilityId) {
        this.dexAbilityId = dexAbilityId;
    }

    public int getConstAbilityId() {
        return constAbilityId;
    }

    public void setConstAbilityId(int constAbilityId) {
        this.constAbilityId = constAbilityId;
    }

    public int getIntelAbilityId() {
        return intelAbilityId;
    }

    public void setIntelAbilityId(int intelAbilityId) {
        this.intelAbilityId = intelAbilityId;
    }

    public int getWisdomAbilityId() {
        return wisdomAbilityId;
    }

    public void setWisdomAbilityId(int wisdomAbilityId) {
        this.wisdomAbilityId = wisdomAbilityId;
    }

    public int getCrismaAbilityId() {
        return crismaAbilityId;
    }

    public void setCrismaAbilityId(int crismaAbilityId) {
        this.crismaAbilityId = crismaAbilityId;
    }
}
