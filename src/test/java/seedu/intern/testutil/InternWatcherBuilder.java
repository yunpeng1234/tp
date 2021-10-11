package seedu.intern.testutil;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.applicant.Applicant;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class InternWatcherBuilder {

    private InternWatcher internWatcher;

    public InternWatcherBuilder() {
        internWatcher = new InternWatcher();
    }

    public InternWatcherBuilder(InternWatcher internWatcher) {
        this.internWatcher = internWatcher;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public InternWatcherBuilder withApplicant(Applicant applicant) {
        internWatcher.addPerson(applicant);
        return this;
    }

    public InternWatcher build() {
        return internWatcher;
    }
}
