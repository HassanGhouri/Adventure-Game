package AdventureModel;

public class ConsumableObject extends AdventureObject{

    /**
     * The effect on the players health this object has
     */
    public double healthEffect;

    /**
     * True if the consumable effects player health over time
     */
    public boolean timedEffector;

    /**
     * The health effect on player every 10 seconds
     */
    public double timeScalar;

    /**
     * Consumable Adventure Object Constructor for timed healthEffectors
     * ___________________________
     * This constructor sets the name, description, and location of the object.
     *
     * @param name        The name of the Object in the game.
     * @param description One line description of the Object.
     * @param location    The location of the Object in the game.
     * @param healthEffect the amount the object affects health immediately.
     * @param timeScalar The health effect on player every 10 seconds
     */
    public ConsumableObject(String name, String description, Room location, double healthEffect, double timeScalar) {
        super(name, description, location);
        this.healthEffect = healthEffect;
        this.timedEffector = true;
        this.timeScalar = timeScalar;
    }

    /**
     * Consumable Adventure Object Constructor for immediate health effectors
     * ___________________________
     * This constructor sets the name, description, and location of the object.
     *
     * @param name        The name of the Object in the game.
     * @param description One line description of the Object.
     * @param location    The location of the Object in the game.
     * @param healthEffect the amount the object affects health immediately.
     */
    public ConsumableObject(String name, String description, Room location, double healthEffect) {
        super(name, description, location);
        this.healthEffect = healthEffect;
    }

    @Override
    public String getName() {
        return this.objectName;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Room getLocation() {
        return this.location;
    }
}
