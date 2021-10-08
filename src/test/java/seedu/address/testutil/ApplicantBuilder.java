package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Applicant;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GraduationYearMonth;
import seedu.address.model.person.Institution;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.skills.Skill;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GRADE = "4.50";
    public static final String DEFAULT_INSTITUTION = "NTU";
    @SuppressWarnings("SpellCheckingInspection")
    public static final String DEFAULT_GRADUATIONYEARMONTH = "06/2024";
    public static final String DEFAULT_COURSE = "Computer Science";

    private Name name;
    private Phone phone;
    private Email email;
    private Grade grade;
    private Institution institution;
    private GraduationYearMonth graduationYearMonth;
    private Course course;
    private Set<Skill> skills;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        grade = new Grade(DEFAULT_GRADE);
        institution = new Institution(DEFAULT_INSTITUTION);
        graduationYearMonth = new GraduationYearMonth(DEFAULT_GRADUATIONYEARMONTH);
        course = new Course(DEFAULT_COURSE);
        skills = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        grade = applicantToCopy.getGrade();
        institution = applicantToCopy.getInstitution();
        graduationYearMonth = applicantToCopy.getGraduationYearMonth();
        course = applicantToCopy.getCourse();
        skills = new HashSet<>(applicantToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public ApplicantBuilder withTags(String ... tags) {
        this.skills = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code Institution} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withInstitution(String institution) {
        this.institution = new Institution(institution);
        return this;
    }
    /**
     * Sets the {@code GraduationYearMonth} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withGraduationYearMonth(String graduationYearMonth) {
        this.graduationYearMonth = new GraduationYearMonth(graduationYearMonth);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public ApplicantBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }


    public Applicant build() {
        return new Applicant(name, phone, email, grade, institution, course, graduationYearMonth, skills);
    }
}
