package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Weapon extends Model {
    private int playerId;
    private int weaponId;

    public Weapon(int id, int playerId, int weaponId) {
        this.id = id;
        this.playerId = playerId;
        this.weaponId = weaponId;
    }

    public Weapon(int playerId, int weaponId) {
        this.playerId = playerId;
        this.weaponId = weaponId;
    }

    public Weapon() {
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
