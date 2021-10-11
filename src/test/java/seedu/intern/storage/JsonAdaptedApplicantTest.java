package seedu.intern.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;

public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INSTITUTION = "NU$@";
    private static final String INVALID_GRADUATION_YEAR_MONTH = "13/2021";
    private static final String INVALID_COURSE = "C0mpter $cience";
    private static final String INVALID_STATUS = "OOP";
    private static final String INVALID_GRADE = "4.500";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GRADE = BENSON.getGrade().toString();
    private static final String VALID_INSTITUTION = BENSON.getInstitution().toString();
    private static final String VALID_GRADUATION_YEAR_MONTH = BENSON.getGraduationYearMonth().toString();
    private static final String VALID_COURSE = BENSON.getCourse().toString();
    private static final String VALID_STATUS = BENSON.getApplicationStatus().toString();
    private static final List<JsonAdaptedSkill> VALID_TAGS = BENSON.getSkills().stream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(null,
                VALID_PHONE, VALID_EMAIL, VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_GRADUATION_YEAR_MONTH, VALID_GRADE,
                        VALID_INSTITUTION, VALID_COURSE, VALID_STATUS, VALID_TAGS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                null, VALID_EMAIL, VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME,
                        VALID_PHONE, INVALID_EMAIL, VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                        VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, null, VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME,
                        VALID_PHONE, VALID_EMAIL, INVALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                        VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_INSTITUTION, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidInstitution_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, INVALID_INSTITUTION, VALID_COURSE, VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);

        String expectedMessage = Institution.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullInstitution_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_GRADE, null, VALID_COURSE,
                VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Institution.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGraduationYearMonth_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, VALID_INSTITUTION, VALID_COURSE, INVALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);

        String expectedMessage = GraduationYearMonth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCourse_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, VALID_INSTITUTION, INVALID_COURSE, VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);

        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGraduationYearMonth_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_GRADE, VALID_INSTITUTION, VALID_COURSE,
                null, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GraduationYearMonth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCourse_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_GRADE, VALID_INSTITUTION,
                null, VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, VALID_INSTITUTION, VALID_COURSE, VALID_GRADUATION_YEAR_MONTH, INVALID_STATUS, VALID_TAGS);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedSkill> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedSkill(INVALID_TAG));
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_GRADE, VALID_INSTITUTION, VALID_COURSE, VALID_GRADUATION_YEAR_MONTH, VALID_STATUS, invalidTags);

        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
