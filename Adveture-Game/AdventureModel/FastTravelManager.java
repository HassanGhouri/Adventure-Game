package AdventureModel;

import Commands.Command;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manages the execution and undoing of fast travel commands in the Adventure Game.
 * The FastTravelManager keeps track of executed commands and provides functionality
 * to execute new commands and undo the last executed command.
 */
public class FastTravelManager implements Serializable {

    /**
     * List to store the executed fast travel commands.
     */
    private ArrayList<Command> commands = new ArrayList<>();

    /**
     * Constructs a new FastTravelManager.
     */
    public FastTravelManager() {
    }

    /**
     * Executes the specified fast travel command.
     * Adds the command to the list of executed commands and calls the command's execute() method.
     *
     * @param command The fast travel command to be executed.
     */
    public void execute(Command command) {
        commands.add(command);
        command.execute();
    }

    /**
     * Undoes the last executed fast travel command.
     * Removes the last command from the list and calls its undo() method to revert the action.
     * Does nothing if there are no executed commands.
     */
    public void undo() {
        if (!commands.isEmpty()) {
            Command command = commands.get(commands.size() - 1);
            command.undo();
            commands.remove(command);
        }
    }

    /**
     * Returns the list of executed fast travel commands.
     *
     * @return The list of executed fast travel commands.
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }
}
