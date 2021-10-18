package seedu.intern.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.intern.logic.commands.CommandTestUtil.DESC_ALL_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.DESC_ALL_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.EditAllCommand.EditAllDescriptor;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.testutil.ApplicantBuilder;
import seedu.intern.testutil.EditAllDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAllCommandTest {

    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditAllDescriptor descriptor = new EditAllDescriptorBuilder(editedApplicant).build();
        EditAllCommand editAllCommand = new EditAllCommand(descriptor);

        String expectedMessage = String.format(EditAllCommand.MESSAGE_EDIT_ALL_SUCCESS,
                model.getFilteredPersonList().size(),
                model.getFilteredPersonList().size());

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());
        for (Applicant applicant : model.getFilteredPersonList()) {
            Applicant editedCurrentApplicant = new ApplicantBuilder(applicant)
                    .withApplicationStatus(editedApplicant.getApplicationStatus().toString()).build();
            expectedModel.setApplicant(applicant, editedCurrentApplicant);
        }


        assertCommandSuccess(editAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // edit all currently only has 1 field that can be edited, so this test is identical to the first.
        EditAllDescriptor descriptor = new EditAllDescriptorBuilder().withApplicationStatus(VALID_STATUS_AMY).build();
        EditAllCommand editAllCommand = new EditAllCommand(descriptor);

        String expectedMessage = String.format(EditAllCommand.MESSAGE_EDIT_ALL_SUCCESS,
                model.getFilteredPersonList().size(),
                model.getFilteredPersonList().size());

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());
        for (Applicant applicant : model.getFilteredPersonList()) {
            Applicant editedCurrentApplicant = new ApplicantBuilder(applicant)
                    .withApplicationStatus(VALID_STATUS_AMY).build();
            expectedModel.setApplicant(applicant, editedCurrentApplicant);
        }

        assertCommandSuccess(editAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAllCommand editAllCommand = new EditAllCommand(new EditAllDescriptor());

        String expectedMessage = String.format(EditAllCommand.MESSAGE_EDIT_ALL_SUCCESS,
                model.getFilteredPersonList().size(),
                model.getFilteredPersonList().size());

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());

        assertCommandSuccess(editAllCommand, model, expectedMessage, expectedModel);
    }
    //TODO: Add filtered list success test

    @Test
    public void equals() {
        final EditAllCommand standardCommand = new EditAllCommand(DESC_ALL_AMY);

        // same values -> returns true
        EditAllDescriptor copyDescriptor = new EditAllDescriptor(DESC_ALL_AMY);
        EditAllCommand commandWithSameValues = new EditAllCommand(copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ClearCommand(), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditAllCommand(DESC_ALL_BOB), standardCommand);
    }

}
