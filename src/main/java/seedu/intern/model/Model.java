package seedu.intern.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.model.applicant.Applicant;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' intern book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' intern book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces intern book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyInternWatcher addressBook);

    /** Returns the AddressBook */
    ReadOnlyInternWatcher getAddressBook();

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the intern book.
     */
    boolean hasPerson(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the intern book.
     */
    void deletePerson(Applicant target);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the intern book.
     */
    void addPerson(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedPerson}.
     * {@code target} must exist in the intern book.
     * The applicant identity of {@code editedPerson} must not be the same as another existing applicant in the intern book.
     */
    void setPerson(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredPersonList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Applicant> predicate);
}
