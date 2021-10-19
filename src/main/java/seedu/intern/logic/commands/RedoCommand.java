package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Undone command has been redone";
    public static final String MESSAGE_NO_UNDO = "No command to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRedoAvailable()) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }

        model.redoInternWatcher();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
