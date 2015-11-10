package group4.dmhelper.Actors;

// TODO: 11/4/2015  

/**
 * Created by Daniel on 10/19/2015.
 * Updated by James.
 *
 * DOES NOT HAVE DATABASE
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
