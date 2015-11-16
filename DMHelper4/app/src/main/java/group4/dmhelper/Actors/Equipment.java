package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Equipment extends Model {
    private int playerId;
    private int equipmentId;

    private int handsHolding; // TODO: 11/15/2015
    private int ammo; // TODO: 11/15/2015

    public Equipment(int id, int playerId, int equipmentId) {
        this.id = id;
        this.playerId = playerId;
        this.equipmentId = equipmentId;
    }

    public Equipment(int playerId, int equipmentId) {
        this.playerId = playerId;
        this.equipmentId = equipmentId;
    }

    public Equipment() {
        this(0,0,0);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }
}
