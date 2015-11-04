package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Skill extends Ability {
    private int playerId;   //read/write DB
    private int skillId;    //read only DB

    public Skill(int playerId,int skillId) {
        super(playerId);
        this.playerId = playerId;
        this.skillId = skillId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
}
