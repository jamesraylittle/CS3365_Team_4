package group4.dmhelper;

import java.util.ArrayList;

import group4.dmhelper.Actors.Actor;

/**
 * I figured that this class would help keep track of actors and objects.  This class should be
 * in charge of saving, loading, creating new characters, deleting characters, etc.
 *
 * There should only be one instance of the GameManager
 *
 * Created by Daniel on 10/19/2015.
 */
public class GameManager {

    ArrayList<Actor> playerList = new ArrayList<Actor>();
    ArrayList<Actor> monsterList = new ArrayList<Actor>();
    ArrayList<Actor> monsterBacklog = new ArrayList<Actor>();   //This is for the DMs who want to create monsters before the game

    ArrayList<Actor> itemBackLog = new ArrayList<Actor>();   //This is for the DMs who want to create monsters before the game

    ArrayList<Actor> initiativeRoll = new ArrayList<Actor>();   //this should be replaced with a circular linked list

    //ArrayList<DATABASE OBJECT> dataBases = new ArrayList<DATABASE OBJECT>();

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing monsters and players
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void createPlayer(){
        //this function should put a new actor in the playerList
    }
    public void deletePlayer(int actorId){//THIS IS NOT THE FUNCTION YOU USE TO KILL THE PLAYERS
        //this function should delete an actor in the playerList
    }
    public void updatePlayer(){
        //in case they get poisoned, etc.
    }

    public void quickCreateMonster(){
        //this function should be able to generate new monsters quickly
    }
    public void createMonster(){
        //this function should put a new actor in the monsterList
    }
    public void deleteMonster(int actorId){//THIS IS NOT THE FUNCTION YOU USE TO KILL THE MONSTER S
        //this function should delete an actor in the monsterList
    }
    public void updateMonster(){
        //in case they get poisoned, etc.
    }

    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#
    //Managing Turns
    //$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#@!$#

    public void battleSequence(){
        /*This function will do a few things
         *
         * it will have a while loop that will keep track of all of all of the active monsters
         * It will manage turns by stepping through the initiativeRoll list
         * It will settle tie-breakers
         * It will be able to be ended.
         *
         */
    }
    public void sortActors(){
        //this should populate the initiativeRoll list based on the actors' initiative
    }
    public void attack(int actorId1, int actorId2){
        // This should be the function that initiates the rollToHit, etc.
    }
    public void killMonster(int actorId){
        //This is the function that should be used when a monster is killed
    }
    public void killPlayer(int actorId){
        //This is the function that should be used when a player is killed
    }
}
