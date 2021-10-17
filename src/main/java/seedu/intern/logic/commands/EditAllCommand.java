package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.util.CollectionUtil;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;

/**
 * Edits the details of all currently displayed applicants in Intern Watcher.
 */
public class EditAllCommand extends Command {

    public static final String COMMAND_WORD = "editAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates all currently displayed applicants with the"
            + "provided fields."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_STATUS + "STATUS]\n";

    // TODO: update this message
    public static final String MESSAGE_EDIT_ALL_SUCCESS = "Edited currently displayed applicants: ";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n";

    private final EditAllDescriptor editAllDescriptor;

    /**
     * @param editAllDescriptor details to update all applicants with
     */
    public EditAllCommand(EditAllDescriptor editAllDescriptor) {
        this.editAllDescriptor = new EditAllDescriptor(editAllDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredPersonList();
        int addSuccesses = 0;

        // TODO: Catch failures to update?
        for (Applicant applicantToEdit : lastShownList) {
            Applicant editedApplicant = createEditedApplicant(applicantToEdit, editAllDescriptor);
            model.setApplicant(applicantToEdit, editedApplicant);
            addSuccesses++;
        }

        String result = addSuccesses + " of " + lastShownList.size() + " applicants updated\n";

        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_ALL_SUCCESS, result));
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code EditAllDescriptor}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit,
                                                EditAllDescriptor editAllDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = applicantToEdit.getName();
        Phone updatedPhone = applicantToEdit.getPhone();
        Email updatedEmail = applicantToEdit.getEmail();
        Grade updatedGrade = applicantToEdit.getGrade();
        Institution updatedInstitution = applicantToEdit.getInstitution();
        GraduationYearMonth updatedGraduationYearMonth = applicantToEdit.getGraduationYearMonth();
        Course updatedCourse = applicantToEdit.getCourse();
        Set<Skill> updatedSkills = applicantToEdit.getSkills();

        ApplicationStatus updatedStatus = editAllDescriptor.getApplicationStatus()
                .orElse(applicantToEdit.getApplicationStatus());

        return new Applicant(updatedName, updatedPhone, updatedEmail,
                updatedGrade, updatedInstitution, updatedCourse,
                updatedGraduationYearMonth, updatedStatus, updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAllCommand)) {
            return false;
        }

        // state check
        EditAllCommand e = (EditAllCommand) other;
        return editAllDescriptor.equals(e.editAllDescriptor);
    }

    public String toString() {
        return editAllDescriptor.toString();
    }

    /**
     * Stores the details to update all applicants with. Each non-empty field value will replace the
     * corresponding field value of all currently filtered applicants.
     */
    public static class EditAllDescriptor {
        private ApplicationStatus status;

        public EditAllDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAllDescriptor(EditAllDescriptor toCopy) {
            setApplicationStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(status);
        }

        public void setApplicationStatus(ApplicationStatus status) {
            this.status = status;
        }

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAllDescriptor)) {
                return false;
            }

            // state check
            EditAllDescriptor e = (EditAllDescriptor) other;

            return getApplicationStatus().equals(e.getApplicationStatus());
        }

        @Override
        public String toString() {
            return "EditAllDescriptor{" + "status=" + status + '}';
        }

    }
}
