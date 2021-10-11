package seedu.intern.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalApplicants.ALICE;
import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.exceptions.DuplicatePersonException;
import seedu.intern.testutil.ApplicantBuilder;

public class InternWatcherTest {

    private final InternWatcher internWatcher = new InternWatcher();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internWatcher.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internWatcher.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        InternWatcher newData = getTypicalInternWatcher();
        internWatcher.resetData(newData);
        assertEquals(newData, internWatcher);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Applicant> newApplicants = Arrays.asList(ALICE, editedAlice);
        InternWatcherStub newData = new InternWatcherStub(newApplicants);

        assertThrows(DuplicatePersonException.class, () -> internWatcher.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internWatcher.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(internWatcher.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        internWatcher.addPerson(ALICE);
        assertTrue(internWatcher.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        internWatcher.addPerson(ALICE);
        Applicant editedAlice = new ApplicantBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(internWatcher.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internWatcher.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class InternWatcherStub implements ReadOnlyInternWatcher {
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        InternWatcherStub(Collection<Applicant> applicants) {
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Applicant> getPersonList() {
            return applicants;
        }
    }

}
