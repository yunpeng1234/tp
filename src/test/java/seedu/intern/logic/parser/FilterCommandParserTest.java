package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.*;
import static seedu.intern.logic.commands.FilterCommand.MESSAGE_NOT_FILTERED;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalApplicants.AMY;
import static seedu.intern.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.AddCommand;
import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.NameContainsKeywordsPredicate;
import seedu.intern.testutil.ApplicantBuilder;
import seedu.intern.testutil.FilterApplicantDescriptorBuilder;


import java.util.Arrays;
import java.util.logging.Filter;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_NOT_FILTERED, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsAll_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusGrade_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusInstitution_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusApplicationStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusCourses_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusGradYearMonth_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusSkills_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validGrade_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validInstitution_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validApplicationStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validCourses_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withCourses(VALID_COURSE_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COURSE_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validGradYearMonth_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADUATION_YEARMONTH_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validSkill_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SKILL_DESC_PYTHON,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiInstitution_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB, VALID_INSTITUTION_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB + INSTITUTION_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiApplicationStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_BOB, VALID_STATUS_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STATUS_DESC_BOB + STATUS_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiCourses_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withCourses(VALID_COURSE_BOB, VALID_COURSE_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COURSE_DESC_BOB + COURSE_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiSkill_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withSkills(VALID_SKILL_PYTHON, VALID_SKILL_JAVA)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SKILL_DESC_PYTHON + SKILL_DESC_JAVA,
                new FilterCommand(expected));
    }
}
