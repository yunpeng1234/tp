package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_YEARMONTH_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_YEARMONTH_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADUATION_YEARMONTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INSTITUTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplicants.AMY;
import static seedu.address.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GraduationYearMonth;
import seedu.address.model.person.Institution;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.skills.Skill;
import seedu.address.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple grade - last grade accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_AMY + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple institutions - last institution accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_AMY + INSTITUTION_DESC_BOB
                + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple graduation_year_month - last graduation_year_month accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_AMY + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple courses - last course accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_AMY + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicant));

        // multiple tags - all accepted
        Applicant expectedApplicantMultipleTags = new ApplicantBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedApplicantMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + GRADE_DESC_AMY + INSTITUTION_DESC_AMY + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_AMY, new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_GRADE_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing institution prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + VALID_INSTITUTION_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing course prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB + INSTITUTION_DESC_BOB
                + VALID_COURSE_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing graduation_year_month prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + VALID_GRADUATION_YEARMONTH_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_GRADE_BOB
                + VALID_INSTITUTION_BOB + VALID_COURSE_BOB + VALID_GRADUATION_YEARMONTH_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + GRADE_DESC_BOB + GRADE_DESC_BOB + INSTITUTION_DESC_BOB
                + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid institution
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INVALID_INSTITUTION_DESC + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB
                + TAG_DESC_FRIEND, Institution.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + INVALID_TAG_DESC
                + VALID_TAG_FRIEND, Skill.MESSAGE_CONSTRAINTS);

        // invalid course
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + INVALID_COURSE_DESC + GRADUATION_YEARMONTH_DESC_BOB
                + TAG_DESC_FRIEND, Course.MESSAGE_CONSTRAINTS);

        // invalid graduation year month
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + INVALID_GRADUATION_YEARMONTH_DESC
                + TAG_DESC_FRIEND, GraduationYearMonth.MESSAGE_CONSTRAINTS);

        // invalid grade
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_GRADE_DESC + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_HUSBAND
                + VALID_TAG_FRIEND, Grade.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
