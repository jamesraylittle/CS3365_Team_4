package group4.dmhelper.Actors;
import java.util.ArrayList;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Skill extends Ability implements Comparable<Skill> {
    private int playerId;   //read/write DB
    private int skillId;    //read only DB
    private int baseScore;
    private int miscBonus;
    private String name;

    public Skill(int id, int baseScore, int miscBonus, String name, int playerId, int skillId) {
        this.id = id;
        this.baseScore = baseScore;
        this.miscBonus = miscBonus;
        this.name = name;
        this.playerId = playerId;
        this.skillId = skillId;
    }

    public Skill(int baseScore, int miscBonus, String name, int playerId, int skillId) {
        this.baseScore = baseScore;
        this.miscBonus = miscBonus;
        this.name = name;
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

    public int getId() { return id; }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public int getSkillId() { return skillId; }
    public void setSkillId(int skillId) {this.skillId = skillId;}

    public int getBaseScore() {return baseScore;}
    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public int getMiscBonus() {
        return miscBonus;
    }
    public void setMiscBonus(int miscBonus) {
        this.miscBonus = miscBonus;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
