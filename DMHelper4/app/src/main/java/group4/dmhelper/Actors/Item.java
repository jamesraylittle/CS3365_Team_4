package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 */
public class Item extends Model {
    String name;
    String category;
    String subCategory;
    String specialAbility;
    String aura;
    String alignment;

    public Item() {
        this(0, "", "", "", "", "", "");
    }
    public Item(int id, String name, String category, String subCategory, String specialAbility, String aura, String alignment) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.specialAbility = specialAbility;
        this.aura = aura;
        this.alignment = alignment;
    }
    public Item(String name, String category, String subCategory, String specialAbility, String aura, String alignment) {
        this.id = 0;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.specialAbility = specialAbility;
        this.aura = aura;
        this.alignment = alignment;
    }


    public String name() { return name; }
    public String category() { return category; }
    public String subCategory() { return subCategory; }
    public String specialAbility() { return specialAbility; }
    public String aura() { return aura; }
    public String alignment() { return alignment; }


    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }
    public void setSpecialAbility(String ability) { this.specialAbility = ability; }
    public void setAura(String aura) { this.aura = aura; }
    public void setAlignment(String alignment) { this.alignment = alignment; }
}
