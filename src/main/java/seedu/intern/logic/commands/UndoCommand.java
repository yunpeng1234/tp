package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Command has been undone: %s";
    public static final String MESSAGE_NO_UNDO = "No command to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isUndoAvailable()) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }

        String command = model.undo();
        return new CommandResult(String.format(MESSAGE_SUCCESS, command));
    }
}
