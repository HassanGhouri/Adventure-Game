package AdventureModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class Player implements Serializable {
    /**
     * The current room that the player is located in.
     */
    private Room currentRoom;

    /**
     * The health of the player. Game ends when this reaches 0.
     */
    private double health;

    /**
     * The list of items that the player is carrying at the moment.
     */
    public ArrayList<AdventureObject> inventory;

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom) {
        this.inventory = new ArrayList<AdventureObject>();
        this.currentRoom = currentRoom;
        this.health = 100;
    }

    /**
     * This method adds an object into players inventory if the object is present in
     * the room and returns true. If the object is not present in the room, the method
     * returns false.
     *
     * @param object name of the object to pick up
     * @return true if picked up, false otherwise
     */
    public boolean takeObject(String object){
        if(this.currentRoom.checkIfObjectInRoom(object)){
            AdventureObject object1 = this.currentRoom.getObject(object);
            this.currentRoom.removeGameObject(object1);
            this.addToInventory(object1);
            return true;
        } else {
            return false;
        }
    }


    /**
     * checkIfObjectInInventory
     * __________________________
     * This method checks to see if an object is in a player's inventory.
     *
     * @param s the name of the object
     * @return true if object is in inventory, false otherwise
     */
    public boolean checkIfObjectInInventory(String s) {
        for(int i = 0; i<this.inventory.size();i++){
            if(this.inventory.get(i).getName().equals(s)) return true;
        }
        return false;
    }


    /**
     * This method drops an object in the players inventory and adds it to the room.
     * If the object is not in the inventory, the method does nothing.
     *
     * @param s name of the object to drop
     */
    public void dropObject(String s) {
        for(int i = 0; i<this.inventory.size();i++){
            if(this.inventory.get(i).getName().equals(s)) {
                this.currentRoom.addGameObject(this.inventory.get(i));
                this.inventory.remove(i);
            }
        }
    }

    /**
     * Setter method for the current room attribute.
     *
     * @param currentRoom The location of the player in the game.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom.visit();
        this.currentRoom = currentRoom;
    }

    /**
     * This method adds an object to the inventory of the player.
     *
     * @param object Prop or object to be added to the inventory.
     */
    public void addToInventory(AdventureObject object) {
        this.inventory.add(object);
    }


    /**
     * Getter method for the current room attribute.
     *
     * @return current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Getter method for string representation of inventory.
     *
     * @return ArrayList of names of items that the player has.
     */
    public ArrayList<String> getInventory() {
        ArrayList<String> objects = new ArrayList<>();
        for (AdventureObject adventureObject : this.inventory) {
            objects.add(adventureObject.getName());
        }
        return objects;
    }

    /**
     * Getter method for player health
     *
     * @return the health of the player
     */
    public double getHealth(){
        return this.health;
    }

    /**
     * Setter for player health
     *
     * @param health the health value to set player health to
     */
    public void setHealth(double health){
        this.health = health;
    }

    /**
     *
     * @param healthEffect the amount to effect the health by.
     *                     Negative will reduce health (Damage), Positive will increase health (Heal)
     *
     * @return returns true if the player dies, false otherwise
     */
    public boolean effectHealth(double healthEffect){
        this.health += healthEffect;
        if(this.health > 100){
            this.health = 100;
        }
        if(this.health <= 0) {
            this.health = 0;
            return true;
        }
        return false;
    }

    /**
     * This method consumes an object in the players inventory and removes it from the game.
     * If the object is not in the inventory, the method does nothing.
     *
     * @param s name of the object to drop
     */
    public void consumeObject(String s) {
        for (AdventureObject adventureObject : this.inventory) {
            if (adventureObject.getName().equals(s)) {
                if (adventureObject instanceof ConsumableObject) {
                    effectHealth(((ConsumableObject) adventureObject).healthEffect);
                    this.inventory.remove(adventureObject);
                    break;
                }
            }
        }
    }
}
