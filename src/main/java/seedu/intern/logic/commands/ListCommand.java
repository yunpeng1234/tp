package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.intern.model.Model;

/**
 * Lists all applicants in Intern Watcher to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
