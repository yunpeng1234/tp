package seedu.intern.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intern.model.InternWatcher;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Applicant[] getSamplePersons() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Grade("4.50"), new Institution("NTU"),
                    new Course("Computer Science"), new GraduationYearMonth("06-2020"), getTagSet("friends")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Grade("4.60"),
                    new Institution("SUTD"), new Course("Computer Science"), new GraduationYearMonth("06-2020"),
                    getTagSet("colleagues", "friends")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Grade("4.70"), new Institution("YALE"),
                    new Course("Computer Science"), new GraduationYearMonth("06-2020"), getTagSet("neighbours")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Grade("4.80"),
                    new Institution("NTU"), new Course("Computer Science"),
                    new GraduationYearMonth("06-2020"), getTagSet("family")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Grade("4.90"), new Institution("SUSS"),
                    new Course("Computer Science"), new GraduationYearMonth("06-2020"), getTagSet("classmates")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Grade("5.00"), new Institution("SMU"),
                    new Course("Computer Science"), new GraduationYearMonth("06-2020"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyInternWatcher getSampleAddressBook() {
        InternWatcher sampleAb = new InternWatcher();
        for (Applicant sampleApplicant : getSamplePersons()) {
            sampleAb.addPerson(sampleApplicant);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Skill> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
