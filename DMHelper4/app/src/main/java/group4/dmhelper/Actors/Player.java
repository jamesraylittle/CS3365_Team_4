package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 * Updated by Daniel 11/3/2015
 */
public class Player extends Model {

    private String gender;
    private int size;
    private String alignment;
    private int weight;
    private String religion;
    private int playerAbilityId;
    private int equippedItemsId;
    private int skillsId;
    private int playerWeaponsId;
    private int raceId;
    private String name;

    // TODO: 11/3/2015 This object needs to have abilities, weapons, etc in order to populate everything for the player

    public Player(int id, String gender, int size, String alignment, int weight, String religion, int playerAbilitiesId, int equippedItems, int skillsId, int classTypeId, int playerWeaponsId, int raceId, String name) {
        this.id = id;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.religion = religion;
        this.playerAbilityId = playerAbilitiesId;
        this.equippedItemsId = equippedItems;
        this.skillsId = skillsId;
        this.playerWeaponsId = playerWeaponsId;
        this.raceId = raceId;
        this.name = name;
    }
    public Player(String gender, int size, String alignment, int weight, String religion, int playerAbilitiesId, int equippedItems, int skillsId, int classTypeId, int playerWeaponsId, int raceId, String name) {
        //super(0);
        this.id = 0;
        this.gender = gender;
        this.size = size;
        this.alignment = alignment;
        this.weight = weight;
        this.religion = religion;
        this.playerAbilityId = playerAbilitiesId;
        this.equippedItemsId = equippedItems;
        this.skillsId = skillsId;
        this.playerWeaponsId = playerWeaponsId;
        this.raceId = raceId;
        this.name = name;
    }

    public Player() {
        this(0, "", 0, "", 0, "", 0, 0, 0, 0, 0, 0, "");
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Leveling Up
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    public void levelUp() {}// TODO: 11/2/2015 This should have it's own activity associated with it.


    public String gender() { return this.gender; }
    public int size() { return this.size; }
    public String alignment() { return this.alignment; }
    public int weight() { return this.weight; }
    public String religion() { return this.religion; }
    public int playerAbilityId() { return this.playerAbilityId; }
    public int equippedItemsId() { return this.equippedItemsId; }
    public int skillsId() { return this.skillsId; }
    public int playerWeaponsId() { return this.playerWeaponsId; }
    public int raceId() { return this.raceId; }
    public String name() { return this.name; }

    //TODO: get relation objects using ids i.e. getSkills() : List[Skills]


    public void setGender(String gender) { this.gender = gender; }
    public void setSize(int size) { this.size = size; }
    public void setAlignment(String alignment) { this.alignment = alignment; }
    public void setWeight(int weight) { this.weight = weight; }
    public void setReligion(String religion) { this.religion = religion; }
    public void setPlayerAbilityId(int id) { this.playerAbilityId = id; }
    public void setEquippedItemsId(int id) { this.equippedItemsId = id; }
    public void setSkillsId(int id) { this.skillsId = id; }
    public void setPlayerWeaponsId(int id) { this.playerWeaponsId = id; }
    public void setRaceId(int id) { this.raceId = id; }
    public void setName(String name) { this.name = name; }

}
