package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an applicant's GraduationYearMonth in Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidGraduationYearMonth(String)}
 */
public class GraduationYearMonth {

    public static final String MESSAGE_CONSTRAINTS =
            "Expected Graduation should be of format MM/yyyy";

    public static final String VALIDATION_REGEX = "[0-9]{2}/[0-9]{4}";

    public final String value;

    /**
     * Constructs a {@code GraduationYearMonth}.
     *
     * @param graduationYearMonth A valid grade.
     */
    public GraduationYearMonth(String graduationYearMonth) {
        requireNonNull(graduationYearMonth);
        checkArgument(isValidGraduationYearMonth(graduationYearMonth), MESSAGE_CONSTRAINTS);
        value = graduationYearMonth;
    }

    /**
     * Returns true if a given string is a valid grade name.
     */
    public static boolean isValidGraduationYearMonth(String test) {
        int holder;
        if (test.matches(VALIDATION_REGEX)) {
            holder = Integer.parseInt(test.split("/")[0]);
        } else {
            return false;
        }
        // month in between 1-12 inclusive
        return test.matches(VALIDATION_REGEX) && holder >= 1 && holder <= 12;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GraduationYearMonth // instanceof handles nulls
                && value.equals(((GraduationYearMonth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
