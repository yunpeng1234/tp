package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.Model;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.ReadOnlyUserPrefs;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.testutil.ApplicantBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Applicant validApplicant = new ApplicantBuilder().build();

        CommandResult commandResult = new AddCommand(validApplicant).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplicant), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplicant), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Applicant validApplicant = new ApplicantBuilder().build();
        AddCommand addCommand = new AddCommand(validApplicant);
        ModelStub modelStub = new ModelStubWithPerson(validApplicant);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Applicant alice = new ApplicantBuilder().withName("Alice").build();
        Applicant bob = new ApplicantBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different applicant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternWatcherFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternWatcherFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternWatcher(ReadOnlyInternWatcher newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternWatcher getInternWatcher() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplicant(Applicant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicant(Applicant target, Applicant editedApplicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Applicant> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single applicant.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Applicant applicant;

        ModelStubWithPerson(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSameApplicant(applicant);
        }
    }

    /**
     * A Model stub that always accept the applicant being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Applicant> personsAdded = new ArrayList<>();

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return personsAdded.stream().anyMatch(applicant::isSameApplicant);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            requireNonNull(applicant);
            personsAdded.add(applicant);
        }

        @Override
        public ReadOnlyInternWatcher getInternWatcher() {
            return new InternWatcher();
        }
    }

}