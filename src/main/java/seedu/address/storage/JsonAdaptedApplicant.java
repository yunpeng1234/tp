package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicationStatus;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GraduationYearMonth;
import seedu.address.model.person.Institution;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.skills.Skill;

/**
 * Jackson-friendly version of {@link Applicant}.
 */
class JsonAdaptedApplicant {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String grade;
    private final String institution;
    private final String graduationYearMonth;
    private final String course;
    private final String status;
    private final List<JsonAdaptedSkill> skilled = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("grade") String grade,
                                @JsonProperty("institution") String institution, @JsonProperty("course") String course,
                                @JsonProperty("graduationYearMonth") String graduationYearMonth,
                                @JsonProperty("status") String status,
                                @JsonProperty("tagged") List<JsonAdaptedSkill> skilled) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.institution = institution;
        this.course = course;
        this.graduationYearMonth = graduationYearMonth;
        this.status = status;
        if (skilled != null) {
            this.skilled.addAll(skilled);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        grade = source.getGrade().value;
        institution = source.getInstitution().value;
        course = source.getCourse().value;
        graduationYearMonth = source.getGraduationYearMonth().value;
        status = source.getApplicationStatus().value.toString();
        skilled.addAll(source.getTags().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Applicant toModelType() throws IllegalValueException {
        final List<Skill> personSkills = new ArrayList<>();
        for (JsonAdaptedSkill tag : skilled) {
            personSkills.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        if (institution == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Institution.class.getSimpleName()));
        }
        if (!Institution.isValidInstitution(institution)) {
            throw new IllegalValueException(Institution.MESSAGE_CONSTRAINTS);
        }
        final Institution modelInstitution = new Institution(institution);

        if (graduationYearMonth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GraduationYearMonth.class.getSimpleName()));
        }
        if (!GraduationYearMonth.isValidGraduationYearMonth(graduationYearMonth)) {
            throw new IllegalValueException(GraduationYearMonth.MESSAGE_CONSTRAINTS);
        }
        final GraduationYearMonth modelYearMonth = new GraduationYearMonth(graduationYearMonth);

        if (course == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Course.class.getSimpleName()));
        }
        if (!Course.isValidCourse(course)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(course);

        final ApplicationStatus modelStatus;

        if (status == null) {
            modelStatus = new ApplicationStatus();
        } else if (!ApplicationStatus.isValidStatus(status)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        } else {
            modelStatus = new ApplicationStatus(status);
        }

        final Set<Skill> modelSkills = new HashSet<>(personSkills);

        return new Applicant(modelName, modelPhone, modelEmail, modelGrade,
                modelInstitution, modelCourse, modelYearMonth , modelStatus, modelSkills);

    }

}
