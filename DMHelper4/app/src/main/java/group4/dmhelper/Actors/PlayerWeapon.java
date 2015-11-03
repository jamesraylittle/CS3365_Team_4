package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class PlayerWeapon extends Item {
    private int playerId;


    public PlayerWeapon() {
        this(0, 0, "", "", "", "", "", "");
    }
    public PlayerWeapon(int id, int playerId, String name, String category, String subCategory, String specialAbility, String aura, String alignment) {
        super(id, name, category, subCategory, specialAbility, aura, alignment);
        this.playerId = playerId;
    }
    public PlayerWeapon(int playerId, String name, String category, String subCategory, String specialAbility, String aura, String alignment) {
        super(0, name, category, subCategory, specialAbility, aura, alignment);
        this.playerId = playerId;
    }

    public int playerId() { return this.playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }

}
