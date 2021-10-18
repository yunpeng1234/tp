package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.util.CollectionUtil;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.CombineFiltersPredicate;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.skills.Skill;

/**
 * Filters and lists all applicants in Inter Watcher whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter description \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter must be provided.";

    private final FilterDescriptor filterDescriptor;
    private final CombineFiltersPredicate predicate;

    /**
     * @param filterDescriptor details to filter the applicant with
     */
    public FilterCommand(FilterDescriptor filterDescriptor) {
        this.filterDescriptor = new FilterDescriptor(filterDescriptor);
        this.predicate = new CombineFiltersPredicate(this.filterDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && filterDescriptor.equals(((FilterCommand) other).filterDescriptor)); // state check
    }

    /**
     * Stores the details to Filter the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class FilterDescriptor {
        private Grade grade;
        private Set<Institution> institutions;
        private GraduationYearMonth graduationYearMonth;
        private Set<Course> courses;
        private Set<ApplicationStatus> statuses;
        private Set<Skill> skills;

        public FilterDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skill} is used internally.
         */
        public FilterDescriptor(FilterDescriptor toCopy) {
            setGrade(toCopy.grade);
            setInstitutions(toCopy.institutions);
            setGraduationYearMonth(toCopy.graduationYearMonth);
            setCourses(toCopy.courses);
            setApplicationStatuses(toCopy.statuses);
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is Filtered.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(grade, institutions, graduationYearMonth, courses, statuses, skills);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        public void setGraduationYearMonth(GraduationYearMonth graduationYearMonth) {
            this.graduationYearMonth = graduationYearMonth;
        }

        public Optional<GraduationYearMonth> getGraduationYearMonth() {
            return Optional.ofNullable(graduationYearMonth);
        }

        public void setInstitutions(Set<Institution> institutions) {
            this.institutions = (institutions != null) ? new HashSet<>(institutions) : null;
        }

        public Optional<Set<Institution>> getInstitutions() {
            return (institutions != null) ? Optional.of(Collections.unmodifiableSet(institutions)) : Optional.empty();
        }

        public void setCourses(Set<Course> courses) {
            this.courses = (courses != null) ? new HashSet<>(courses) : null;
        }

        public Optional<Set<Course>> getCourses() {
            return (courses != null) ? Optional.of(Collections.unmodifiableSet(courses)) : Optional.empty();
        }

        public void setApplicationStatuses(Set<ApplicationStatus> statuses) {
            this.statuses = (statuses != null) ? new HashSet<>(statuses) : null;
        }

        public Optional<Set<ApplicationStatus>> getApplicationStatuses() {
            return (statuses != null) ? Optional.of(Collections.unmodifiableSet(statuses)) : Optional.empty();
        }

        /**
         * Sets {@code skill} to this object's {@code skill}.
         * A defensive copy of {@code skill} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skill} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterDescriptor)) {
                return false;
            }

            // state check
            FilterDescriptor e = (FilterDescriptor) other;

            return getGrade().equals(e.getGrade())
                    && getInstitutions().equals(e.getInstitutions())
                    && getGraduationYearMonth().equals(e.getGraduationYearMonth())
                    && getCourses().equals(e.getCourses())
                    && getApplicationStatuses().equals(e.getApplicationStatuses())
                    && getSkills().equals(e.getSkills());
        }

        @Override
        public String toString() {
            return "FilterDescriptor{"
                    + ", grade=" + grade
                    + ", institution=" + institutions
                    + ", graduation year month=" + graduationYearMonth
                    + ", course=" + courses
                    + ", application status=" + statuses
                    + ", skill=" + skills + '}';
        }

    }
}
