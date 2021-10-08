package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.address.model.person.Applicant;
import seedu.address.model.skills.Skill;

/**
 * A utility class for Person.
 */
public class ApplicantUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Applicant applicant) {
        return AddCommand.COMMAND_WORD + " " + getApplicantDetails(applicant);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getApplicantDetails(Applicant applicant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + applicant.getName().fullName + " ");
        sb.append(PREFIX_PHONE + applicant.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + applicant.getEmail().value + " ");
        sb.append(PREFIX_GRADE + applicant.getGrade().value + " ");
        sb.append(PREFIX_INSTITUTION + applicant.getInstitution().value + " ");
        sb.append(PREFIX_COURSE + applicant.getCourse().value + " ");
        sb.append(PREFIX_GRADUATIONYEARMONTH + applicant.getGraduationYearMonth().value + " ");
        applicant.getTags().stream().forEach(
            s -> sb.append(PREFIX_SKILL + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditApplicantDescriptorDetails(EditApplicantDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGrade().ifPresent(grade -> sb.append(PREFIX_GRADE).append(grade.value).append(" "));
        descriptor.getInstitution().ifPresent(institution -> sb.append(PREFIX_INSTITUTION)
                .append(institution.value).append(" "));
        descriptor.getCourse().ifPresent(course -> sb.append(PREFIX_COURSE).append(course.value).append(" "));
        descriptor.getGraduationYearMonth().ifPresent(graduationYearMonth ->
                sb.append(PREFIX_GRADUATIONYEARMONTH).append(graduationYearMonth.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Skill> skills = descriptor.getTags().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL);
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
