package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.index.Index;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;

/**
 * Deletes an applicant identified using it's displayed index from Intern Watcher.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Applicant: %1$s";

    public static final String MESSAGE_DELETE_ALL_SUCCESS = " %1$s Applicants deleted!";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if(targetIndex.getZeroBased() == -1) {
            int length = lastShownList.size();
            for(int i = 0; i < length; i++ ) {
                Applicant applicantToDelete = lastShownList.get(0);
                model.deleteApplicant(applicantToDelete);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_ALL_SUCCESS, String.valueOf(length)));
        } else {
            Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deleteApplicant(applicantToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
