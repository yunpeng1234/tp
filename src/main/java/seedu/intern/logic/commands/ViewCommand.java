package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;


/**
 * User can view applicant details in Intern Watcher.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the applicant details identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";


    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Displayed Applicant details: %1$s";

    private final Selection targetSelection;

    public ViewCommand(Selection targetSelection) {
        this.targetSelection = targetSelection;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredPersonList();

        if (targetSelection.hasIndex()) {
            if (targetSelection.getIndexZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        if (targetSelection.hasExtraConditionFlag()) {
            Applicant applicantToView = lastShownList.get(targetSelection.getIndexZeroBased());
            model.displayApplicant(applicantToView, true);
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, applicantToView), false, false, true);
        } else {
            Applicant applicantToView = lastShownList.get(targetSelection.getIndexZeroBased());
            model.displayApplicant(applicantToView, false);
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, applicantToView), false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetSelection.equals(((ViewCommand) other).targetSelection)); // state check
    }
}