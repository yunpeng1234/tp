package seedu.intern.model.applicant;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.intern.commons.util.StringUtil;
import seedu.intern.logic.commands.FilterCommand.FilterDescriptor;
import seedu.intern.model.skills.Skill;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches any of the keywords given.
 */
public class CombineFiltersPredicate implements Predicate<Applicant> {
    private final FilterDescriptor filterDescriptor;
    private boolean result;

    /**
     * Constructor for a CombineFilterPredicate
     * @param filterDescriptor a FilterDescriptor that has details of the filters
     */
    public CombineFiltersPredicate(FilterDescriptor filterDescriptor) {
        this.filterDescriptor = filterDescriptor;
        result = true;
    }

    @Override
    public boolean test(Applicant applicant) {
        result = true;
        Optional<Grade> grade = filterDescriptor.getGrade();
        Optional<Set<Institution>> institutions = filterDescriptor.getInstitutions();
        Optional<GraduationYearMonth> graduationYearMonth = filterDescriptor.getGraduationYearMonth();
        Optional<Set<List<String>>> courses = filterDescriptor.getCourses();
        Optional<Set<ApplicationStatus>> statuses = filterDescriptor.getApplicationStatuses();
        Optional<Set<Skill>> skills = filterDescriptor.getSkills();

        grade.ifPresent(gradeContent ->
                setResult(result && Float.compare(Float.parseFloat(applicant.getGrade().value),
                                Float.parseFloat(gradeContent.value)) >= 0));
        institutions.ifPresent(institutionsContent ->
                setResult(result && institutionsContent.stream().anyMatch(institution ->
                        StringUtil.containsWordIgnoreCase(applicant.getInstitution().value, institution.value))));
        graduationYearMonth.ifPresent(graduationContent ->
                setResult(result && GraduationYearMonth.compareDate(applicant.getGraduationYearMonth().value,
                        graduationContent.value) < 0));
        courses.ifPresent(coursesContent ->
                setResult(result && coursesContent.stream().anyMatch(courseFilter ->
                        courseFilter.stream().allMatch(courseKeyword ->
                                StringUtil.containsWordIgnoreCase(applicant.getCourse().value, courseKeyword)))));
        statuses.ifPresent(statusesContent ->
                setResult(result && statusesContent.stream().anyMatch(status ->
                        StringUtil.containsWordIgnoreCase(applicant.getApplicationStatus().value.name(),
                                status.value.name()))));
        skills.ifPresent(skillsContent ->
                setResult(result && applicant.getSkills().containsAll(skillsContent)));

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CombineFiltersPredicate // instanceof handles nulls
                && filterDescriptor.equals(((CombineFiltersPredicate) other).filterDescriptor)); // state check
    }

    private void setResult(boolean newResult) {
        result = newResult;
    }

}
