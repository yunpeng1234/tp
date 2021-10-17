package seedu.intern.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.UniqueApplicantList;

/**
 * Wraps all data at the intern-watcher level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class InternWatcher implements ReadOnlyInternWatcher {

    private final UniqueApplicantList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueApplicantList();
    }

    public InternWatcher() {}

    /**
     * Creates an InternWatcher using the Persons in the {@code toBeCopied}
     */
    public InternWatcher(ReadOnlyInternWatcher toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Applicant> applicants) {
        this.persons.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code InternWatcher} with {@code newData}.
     */
    public void resetData(ReadOnlyInternWatcher newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the intern watcher.
     */
    public boolean hasPerson(Applicant applicant) {
        requireNonNull(applicant);
        return persons.contains(applicant);
    }

    /**
     * Adds a applicant to the intern watcher.
     * The applicant must not already exist in the intern watcher.
     */
    public void addPerson(Applicant p) {
        persons.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the intern watcher.
     * The applicant identity of {@code editedPerson} must not be the same as another existing applicant in the
     * intern watcher.
     */
    public void setPerson(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        persons.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code InternWatcher}.
     * {@code key} must exist in the intern watcher.
     */
    public void removePerson(Applicant key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternWatcher // instanceof handles nulls
                && persons.equals(((InternWatcher) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
