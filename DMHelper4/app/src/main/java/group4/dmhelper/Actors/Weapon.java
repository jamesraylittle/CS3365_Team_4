package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Weapon extends Model {
    private int playerId;
    private int weaponId;

    public Weapon(int id){
        this.id = id;
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
