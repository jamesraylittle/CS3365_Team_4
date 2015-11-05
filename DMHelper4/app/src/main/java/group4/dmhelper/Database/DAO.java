package group4.dmhelper.Database;

import group4.dmhelper.Actors.Feat;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Actors.Model;

/**
 * Created by james on 10/27/15.
 */
public interface DAO<T> {
    int create(T t);
    T retrieve(int id);
    void update(T t);
    void delete(int id);
    int count();
}
