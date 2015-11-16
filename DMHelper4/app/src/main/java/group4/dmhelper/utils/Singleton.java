package group4.dmhelper.utils;

/**
 * Created by james on 10/27/15.
 */
public class Singleton {
    private static Singleton instance = null;

    protected Singleton() { }

    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}
