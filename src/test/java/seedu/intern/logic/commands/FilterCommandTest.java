package seedu.intern.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;
import seedu.intern.model.applicant.CombineFiltersPredicate;
import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.testutil.FilterApplicantDescriptorBuilder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.intern.logic.commands.CommandTestUtil.*;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_BOB;
import static seedu.intern.testutil.TypicalApplicants.*;
import static seedu.intern.testutil.TypicalApplicants.FIONA;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_gradeOnly_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("4.80").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_skillOnly_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withSkills("python").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_gradYearMonthOnly_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGraduationYearMonth("06/2022").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_courseOnly_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withCourses("Computer").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_statusOnly_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus("REJECTED").build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_allFields_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("4.80")
                .withInstitution("SUTD")
                .withApplicationStatus("ACCEPTED")
                .withCourses("PHILOSOPHY")
                .withGraduationYearMonth("06/2025")
                .build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_allFields_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FilterApplicantDescriptor filterCondition = new FilterApplicantDescriptorBuilder()
                .withGrade("5.00")
                .withInstitution("SUTD")
                .withApplicationStatus("ACCEPTED")
                .withCourses("PHILOSOPHY")
                .withGraduationYearMonth("06/2025")
                .build();
        CombineFiltersPredicate predicate = new CombineFiltersPredicate(filterCondition);
        FilterCommand command = new FilterCommand(filterCondition);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }
}
