package group4.dmhelper.Actors;

import android.util.Log;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Equipment extends Model {
    private int playerId;
    private int equipmentId;
    private String equipmentName;
    private int isEquipped;

    public Equipment(int id, int playerId, int equipmentId, String equipmentName, int isEquipped) {
        this.id = id;
        this.playerId = playerId;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.isEquipped = isEquipped;
    }

    public Equipment(int playerId, int equipmentId, String equipmentName, int isEquipped) {
        this.playerId = playerId;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.isEquipped = isEquipped;
    }

    public Equipment() {
        this(0, 0, 0, "", 0);
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

    public String getEquipmentName() {
        return equipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getIsEquipped() {
        return isEquipped;
    }
    public void setIsEquipped(int isEquipped) {
        this.isEquipped = isEquipped;
    }
}
