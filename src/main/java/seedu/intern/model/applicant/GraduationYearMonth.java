package seedu.intern.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.AppUtil.checkArgument;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Represents an applicant's GraduationYearMonth in Intern Watcher.
 * Guarantees: immutable; is valid as declared in {@link #isValidGraduationYearMonth(String)}
 */
public class GraduationYearMonth {

    public static final String MESSAGE_CONSTRAINTS =
            "Expected Graduation Year Month should be valid and be of format MM/yyyy";

    public static final String VALIDATION_REGEX = "[0-9]{2}/[0-9]{4}";

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

    public final YearMonth yearMonth;

    /**
     * Constructs a {@code GraduationYearMonth}.
     *
     * @param graduationYearMonth A valid grade.
     */
    public GraduationYearMonth(String graduationYearMonth) {
        requireNonNull(graduationYearMonth);
        checkArgument(isValidGraduationYearMonth(graduationYearMonth), MESSAGE_CONSTRAINTS);
        yearMonth = YearMonth.parse(graduationYearMonth, dateTimeFormatter);
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
        return yearMonth.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GraduationYearMonth // instanceof handles nulls
                && yearMonth.equals(((GraduationYearMonth) other).yearMonth)); // state check
    }

    @Override
    public int hashCode() {
        return yearMonth.hashCode();
    }

    /**
     * Returns true should this YearMonth is before the specified year-month.
     *
     * @param other specified YearMonth for comparison
     * @return if this happens earlier than other
     */
    public boolean isBefore(GraduationYearMonth other) {
        return yearMonth.isBefore(other.yearMonth);
    }
}
