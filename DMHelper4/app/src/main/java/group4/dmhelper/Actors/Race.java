package group4.dmhelper.Actors;

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 */
public class Race extends Model {
    private String name;

    public Race(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Race(String name) {
        this(0, name);
    }
    public Race() {
        this(0, "");
    }

    public String name() { return name; }

    public void setName(String name) { this.name = name; }
}
