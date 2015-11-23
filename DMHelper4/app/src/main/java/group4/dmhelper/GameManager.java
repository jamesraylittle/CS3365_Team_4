package group4.dmhelper;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import group4.dmhelper.Actors.Actor;
import group4.dmhelper.Actors.Item;
import group4.dmhelper.Actors.Monster;
import group4.dmhelper.Actors.Player;
import group4.dmhelper.Database.Actors;

/**
 *
 * There should only be one instance of the GameManager
 *
 * Created by Daniel on 10/19/2015.
 */
public class GameManager {

    int gameId;

    private GameManager() {}

    private static GameManager instance;    //Singleton structure

    public static GameManager getInstance() {   //only one instance
        if (instance != null) return instance;
        else return new GameManager();
    }

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    Actors dActors = new Actors(context);

    ArrayList<Player> playerList = new ArrayList<Player>();
    ArrayList<Monster> monsterList = new ArrayList<Monster>();
    ArrayList<Actor> monsterBacklog = new ArrayList<Actor>();   //This is for the DMs who want to create monsters before the game
    ArrayList<Item> itemBackLog = new ArrayList<Item>();   //This is for the DMs who want to create items before the game
    ArrayList<Actor> initiativeRoll = new ArrayList<Actor>();   //this should be replaced with a circular linked list

    //ArrayList<DATABASE OBJECT> dataBases = new ArrayList<DATABASE OBJECT>();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing monsters and players
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public Actor getActor(int actorId) {
        for(int i = 0; i < initiativeRoll.size(); i++)
            if(initiativeRoll.get(i).getId() == actorId) return initiativeRoll.get(i);
        return null;
    }

    public void removeActor(int actorId) {
        Actor p = getActor(actorId);
        initiativeRoll.remove(p);
        p = null;
        if(getPlayer(actorId) == null);//success note;
    }

    public Player getPlayer(int playerId) {
        for(int i = 0; i < playerList.size(); i++)
            if(playerList.get(i).getId() == playerId) return playerList.get(i);
        return null;
    }

    public void createPlayer() {
        //this function should put a new actor in the playerList
        Player p = new Player(context);
        dActors.create(p);
        //Activity editing all data goes here.
        p.pushToDatabase();
        playerList.add(p);
    }

    public void deletePlayer(int actorId) {//THIS IS NOT THE FUNCTION YOU USE TO KILL THE PLAYERS
        //this function should delete an actor in the playerList
        dActors.delete(actorId);
        Player p = getPlayer(actorId);
        playerList.remove(p);
        p = null;
        if(getPlayer(actorId) == null);//success note;
    }

    public Monster getMonster(int monsterId) {
        for(int i = 0; i < monsterList.size(); i++)
            if(monsterList.get(i).getId() == monsterId) return monsterList.get(i);
        return null;
    }

    public void createMonster() {
        Monster m = new Monster(context);
        dActors.create(m);
        //Activity editing all data goes here.
        m.pushToDatabase();
        monsterList.add(m);
    }

    public void deleteMonster(int actorId) {//THIS IS NOT THE FUNCTION YOU USE TO KILL THE MONSTERS
        //this function should delete an actor in the monsterList
        dActors.delete(actorId);
        Monster m = getMonster(actorId);
        monsterList.remove(m);
        m = null;
        if(getMonster(actorId) == null);//success note;
    }

    public void updateMonster() {
        //in case they get poisoned, etc.
        // TODO: 11/11/2015 Activity...? 
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing Turns
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void battleSequence() {
        /*This function will do a few things
         *
         * it will have a while loop that will keep track of all of all of the active monsters
         * It will manage turns by stepping through the initiativeRoll list
         * It will settle tie-breakers
         * It will be able to be ended.
         *
         */

        sortActors();

        while (numEnemies() > 0) {
            for (int i = 0; i < initiativeRoll.size(); i++) {
                actorTurn(initiativeRoll.get(i));
            }
        }
    }

    private void actorTurn(Actor actor) {
        // TODO: 11/10/2015 An activity/window needs to be tied to this.
        //1 moveaction
        //1 standardaction
        //list spells
        //list weapons
        //inf quick actions
        //list spells
    }

    private Boolean attack(Actor actor1, Actor actor2, int iRoll) {
        return actor1.rollToHit() + iRoll > actor2.calculateAC();
    }

    private ArrayList<Boolean> attack(Actor actor1, Actor actor2, ArrayList<Integer> iRolls) {
        ArrayList<Boolean> bSuccess = new ArrayList<Boolean>();
        for(int i=0;i<iRolls.size();i++) {
            bSuccess.add(actor1.rollToHit() + iRolls.get(i) > actor2.calculateAC());
        }
        return bSuccess;
    }

    public void killMonster(int actorId) {
        //This is the function that should be used when a monster is killed
        
        // TODO: 11/11/2015 Remove Items
        // TODO: 11/11/2015 Activity to "spill" items
        deleteMonster(actorId);
    }

    public void killPlayer(int actorId) {
        //This is the function that should be used when a player is killed

        // TODO: 11/11/2015 Remove Items
        // TODO: 11/11/2015 Activity to "spill" items
        deletePlayer(actorId);
    }

    private void sortActors() {
        //this should populate the initiativeRoll list based on the actors' initiative
        if (initiativeRoll.isEmpty()) initiativeRoll.clear();
        initiativeRoll.addAll(playerList);
        initiativeRoll.addAll(monsterList);
        Collections.sort(initiativeRoll);
    }

    public int numEnemies() {
        //checks to see if there are enemies left
        int iReturn = 0;
        for (int i = 0; i < monsterList.size(); i++)
            if (initiativeRoll.contains(monsterList.get(i))) iReturn++;
        return iReturn;
    }
}
