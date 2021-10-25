package seedu.intern.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intern.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intern.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Index;
import seedu.intern.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.testutil.ApplicantBuilder;
import seedu.intern.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredPersonList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Applicant lastApplicant = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        ApplicantBuilder personInList = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withSkills(VALID_SKILL_JAVA).build();

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withSkills(VALID_SKILL_JAVA).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditApplicantDescriptor());
        Applicant editedApplicant = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(applicantInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new InternWatcher(model.getInternWatcher()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredPersonList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit applicant in filtered list into a duplicate in intern book
        Applicant applicantInList = model.getInternWatcher().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder(applicantInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of intern book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of intern book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternWatcher().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditApplicantDescriptor copyDescriptor = new EditApplicantDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
