package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's application status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class ApplicationStatus {
    public enum Status {
        APPLIED, RECEIVED, SCHEDULED, INTERVIEWED, OFFERED, ACCEPTED, REJECTED
    }

    public static final Status DEFAULT_STATUS = Status.APPLIED;

    public static final String MESSAGE_CONSTRAINTS = "Status only supports the current statuses:"
            + Status.APPLIED.name() + ", " + Status.RECEIVED.name() + ", " + Status.SCHEDULED.name() + ", "
            + Status.INTERVIEWED.name() + ", " + Status.OFFERED.name() + ", " + Status.ACCEPTED.name() + ", "
            + Status.REJECTED.name();
    public static final String VALIDATION_REGEX = Status.APPLIED.name() + "|" + Status.RECEIVED.name() + "|"
            + Status.SCHEDULED.name() + "|" + Status.INTERVIEWED.name() + "|" + Status.OFFERED.name() + "|"
            + Status.ACCEPTED.name() + "|" + Status.REJECTED.name();
    public final Status value;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param status A valid status string.
     */
    public ApplicationStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = Status.valueOf(status);
    }

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param status A valid status.
     */
    public ApplicationStatus(Status status) {
        requireNonNull(status);
        value = status;
    }

    /**
     * Constructs a {@code ApplicationStatus}.
     */
    public ApplicationStatus() {
        value = DEFAULT_STATUS;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationStatus // instanceof handles nulls
                && value == ((ApplicationStatus) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
