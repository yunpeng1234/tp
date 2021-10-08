package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternWatcher;
import seedu.address.model.ReadOnlyInternWatcher;
import seedu.address.model.person.Applicant;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableInternWatcher {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedApplicant> applicant = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableInternWatcher(@JsonProperty("persons") List<JsonAdaptedApplicant> applicant) {
        this.applicant.addAll(applicant);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableInternWatcher(ReadOnlyInternWatcher source) {
        applicant.addAll(source.getPersonList().stream().map(JsonAdaptedApplicant::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternWatcher toModelType() throws IllegalValueException {
        InternWatcher internWatcher = new InternWatcher();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicant) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (internWatcher.hasPerson(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            internWatcher.addPerson(applicant);
        }
        return internWatcher;
    }

}
