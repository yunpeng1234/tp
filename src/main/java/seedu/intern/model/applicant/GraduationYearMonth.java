package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

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

    /**
     * Returns 0 if values of two dates are equivalent, 1 if date 1 is later than date 2 and -1 otherwise.
     * @param date1 first date for comparison
     * @param date2 second date for comparison
     * @return an integer indicating the comparison result
     */
    public static int compareDate(String date1, String date2) {
        if (date1 == date2 || date1.compareTo(date2) == 0) {
            return 0;
        }
        int year1 = Integer.parseInt(date1.split("/")[1]);
        int month1 = Integer.parseInt(date1.split("/")[0]);
        int year2 = Integer.parseInt(date2.split("/")[1]);
        int month2 = Integer.parseInt(date2.split("/")[0]);
        if (year1 > year2) {
            return 1;
        } else if (year1 == year2 && month1 > month2) {
            return 1;
        } else {
            return -1;
        }
    }
}
