package group4.dmhelper.Actors;
import java.util.ArrayList;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Skill extends Ability implements Comparable<Skill> {
    private int playerId;   //read/write DB
    private int skillId;    //read only DB

    public Skill(int id, int baseScore, int miscBonus, String name, int playerId, int skillId) {
        super(id, baseScore, miscBonus, name);
        this.playerId = playerId;
        this.skillId = skillId;
    }

    public Skill(int baseScore, int miscBonus, String name, int playerId, int skillId) {
        super(baseScore, miscBonus, name);
        this.playerId = playerId;
        this.skillId = skillId;
    }

    public Skill(int playerId, int skillId) {
        this.playerId = playerId;
        this.skillId = skillId;
    }

    public Skill() {
        this(0, 0, 0, "", 0, 0);
    }

    @Override
    public int compareTo(Skill skill) {
        return this.baseScore - skill.getBaseScore();
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
