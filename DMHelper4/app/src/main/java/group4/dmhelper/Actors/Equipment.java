package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Equipment extends Model {
    private int playerId;
    private int weaponId;

    public Equipment(int id, int playerId, int equipmentId) {
        this.id = id;
        this.playerId = playerId;
        this.weaponId = weaponId;
    }

    public Equipment(int playerId, int equipmentId) {
        this.playerId = playerId;
        this.weaponId = weaponId;
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

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }
}
