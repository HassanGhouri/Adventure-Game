package Commands;

import AdventureModel.AdventureGame;
import AdventureModel.Room;

import java.io.Serializable;

/**
 * A command class representing fast travel in the Adventure Game.
 * This command allows the player to quickly move to a specified destination room.
 * It extends the base Command class and implements the execute() and undo() methods.
 */
public class FastTravelCommand extends Command implements Serializable {

    /**
     * The AdventureGame model associated with this command.
     */
    private AdventureGame model;

    /**
     * The destination room for fast travel.
     */
    private Room destination;

    /**
     * The original room before fast travel, used for undoing the action.
     */
    private Room original;

    /**
     * Constructs a new FastTravelCommand with the specified AdventureGame model and destination room.
     *
     * @param model      The AdventureGame model to associate with this command.
     * @param destination The destination room for fast travel.
     */
    public FastTravelCommand(AdventureGame model, Room destination) {
        super(model);
        this.model = model;
        this.original = model.player.getCurrentRoom();
        this.destination = destination;
    }

    /**
     * Executes the fast travel command by setting the player's current room to the destination,
     * if the destination is a previously visited room.
     */
    @Override
    public void execute() {
        if (model.getVisitedRooms().containsValue(destination)) {
            this.model.player.setCurrentRoom(destination);
        }
    }

    /**
     * Undoes the fast travel command by setting the player's current room back to the original room.
     */
    @Override
    public void undo() {
        this.model.player.setCurrentRoom(this.original);
    }
}

