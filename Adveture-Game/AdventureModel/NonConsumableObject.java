package AdventureModel;

public class NonConsumableObject extends AdventureObject {

    /**
     * Non-Consumable Adventure Object Constructor
     * ___________________________
     * This constructor sets the name, description, and location of the object.
     *
     * @param name        The name of the Object in the game.
     * @param description One line description of the Object.
     * @param location    The location of the Object in the game.
     */
    public NonConsumableObject(String name, String description, Room location) {
        super(name, description, location);
    }

    /**
     * Getter for objectName
     *
     * @return Name of the object
     */
    @Override
    public String getName() {
        return this.objectName;
    }

    /**
     * Getter for object description
     *
     * @return description of the object
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for object's location
     *
     * @return Room the object is located
     */
    @Override
    public Room getLocation() {
        return this.location;
    }
}
