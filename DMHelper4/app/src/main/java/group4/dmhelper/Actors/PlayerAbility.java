package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class PlayerAbility extends Model {
    private int playerId;
    private int strengthAbility;
    private int dexAbility;
    private int constAbility;
    private int intelAbility;
    private int wisdomAbility;
    private int crismaAbility;

    public PlayerAbility(int id, int playerId, int strengthAbility, int dexAbility, int constAbility, int intelAbility, int wisdomAbility, int crismaAbility) {
        this.id = id;
        this.playerId = playerId;
        this.strengthAbility = strengthAbility;
        this.dexAbility = dexAbility;
        this.constAbility = constAbility;
        this.intelAbility = intelAbility;
        this.wisdomAbility = wisdomAbility;
        this.crismaAbility = crismaAbility;
    }
    public PlayerAbility(int playerId, int strengthAbility, int dexAbility, int constAbility, int intelAbility, int wisdomAbility, int crismaAbility) {
        this(0, playerId, strengthAbility, dexAbility, constAbility, intelAbility, wisdomAbility, crismaAbility);
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

    public int getStrengthAbility() {
        return strengthAbility;
    }

    public void setStrengthAbility(int strengthAbility) {
        this.strengthAbility = strengthAbility;
    }

    public int getDexAbility() {
        return dexAbility;
    }

    public void setDexAbility(int dexAbility) {
        this.dexAbility = dexAbility;
    }

    public int getConstAbility() {
        return constAbility;
    }

    public void setConstAbility(int constAbility) {
        this.constAbility = constAbility;
    }

    public int getIntelAbility() {
        return intelAbility;
    }

    public void setIntelAbility(int intelAbility) {
        this.intelAbility = intelAbility;
    }

    public int getWisdomAbility() {
        return wisdomAbility;
    }

    public void setWisdomAbility(int wisdomAbility) {
        this.wisdomAbility = wisdomAbility;
    }

    public int getCrismaAbility() {
        return crismaAbility;
    }

    public void setCrismaAbility(int crismaAbility) {
        this.crismaAbility = crismaAbility;
    }
}
