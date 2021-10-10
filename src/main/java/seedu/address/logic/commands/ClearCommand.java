package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.InternWatcher;
import seedu.address.model.Model;

/**
 * Clears the entries from Intern Watcher.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new InternWatcher());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
