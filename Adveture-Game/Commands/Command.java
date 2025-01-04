package Commands;

import AdventureModel.AdventureGame;

import java.io.Serializable;

/**
 * The base abstract class for commands in the Adventure Game.
 * Commands are actions that can be executed in the game.
 * Each command must implement the execute() method to perform its action
 * and the undo() method to reverse the action if needed.
 */

public abstract class Command implements Serializable {

    /**
     * The AdventureGame model associated with this command.
     */
    private AdventureGame model;

    /**
     * Constructs a new Command with the specified AdventureGame model.
     *
     * @param model The AdventureGame model to associate with this command.
     */
    public Command(AdventureGame model){
        this.model = model;
    }

    /**
     * Executes the command's action in the game.
     *
     */
    public abstract void execute();

    /**
     * Undoes the command's action in the game, reverting it to the previous state.
     * This method should be implemented to reverse the effects of the execute() method.
     */
    public abstract void undo();
}

