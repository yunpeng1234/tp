package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GraduationYearMonth;
import seedu.address.model.person.Institution;
import seedu.address.model.person.Name;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Phone;
import seedu.address.model.skills.Skill;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditApplicantDescriptorBuilder {

    private EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setGrade(applicant.getGrade());
        descriptor.setInstitution(applicant.getInstitution());
        descriptor.setGraduationYearMonth(applicant.getGraduationYearMonth());
        descriptor.setCourse(applicant.getCourse());
        descriptor.setTags(applicant.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    /**
     * Sets the {@code Institution} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withInstitution(String institution) {
        descriptor.setInstitution(new Institution(institution));
        return this;
    }

    /**
     * Sets the {@code GraduationYearMonth} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withGraduationYearMonth(String graduationYearMonth) {
        descriptor.setGraduationYearMonth(new GraduationYearMonth(graduationYearMonth));
        return this;
    }
    /**
     * Sets the {@code Course} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withCourse(String course) {
        descriptor.setCourse(new Course(course));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditApplicantDescriptorBuilder withTags(String... tags) {
        Set<Skill> skillSet = Stream.of(tags).map(Skill::new).collect(Collectors.toSet());
        descriptor.setTags(skillSet);
        return this;
    }

    public EditApplicantDescriptor build() {
        return descriptor;
    }
}
