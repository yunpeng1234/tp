package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.skills.Skill;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Grade grade;
    private final Institution institution;
    private final GraduationYearMonth graduationYearMonth;
    private final Course course;
    private final ApplicationStatus status;
    private final Set<Skill> skills = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Grade grade,
                  Institution institution, Course course, GraduationYearMonth graduationYearMonth, Set<Skill> skills) {
        this(name, phone, email, address, grade, institution, course, graduationYearMonth,
                new ApplicationStatus(ApplicationStatus.DEFAULT_STATUS), skills);
    }

    /**
     * Overloaded constructor for creating candidates with default status
     */
    public Person(Name name, Phone phone, Email email, Address address, Grade grade,
                  Institution institution, Course course, GraduationYearMonth graduationYearMonth,
                  ApplicationStatus status, Set<Skill> skills) {
        requireAllNonNull(name, phone, email, address, grade, status, institution, course, graduationYearMonth, skills);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.institution = institution;
        this.graduationYearMonth = graduationYearMonth;
        this.course = course;
        this.status = status;
        this.skills.addAll(skills);
    }

    public Name getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Course getCourse() {
        return course;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public ApplicationStatus getApplicationStatus() {
        return status;
    }

    public GraduationYearMonth getGraduationYearMonth() {
        return graduationYearMonth;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Skill> getTags() {
        return Collections.unmodifiableSet(skills);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGrade().equals(getGrade())
                && otherPerson.getInstitution().equals(getInstitution())
                && otherPerson.getGraduationYearMonth().equals(getGraduationYearMonth())
                && otherPerson.getCourse().equals(getCourse())
                && otherPerson.getApplicationStatus().equals(getApplicationStatus())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, grade, institution, course, graduationYearMonth, status, skills);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Status: ")
                .append(getApplicationStatus())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Grade: ")
                .append(getGrade())
                .append("; Institution: ")
                .append(getInstitution())
                .append("; Course: ")
                .append(getCourse())
                .append("; Graduation Year Month: ")
                .append((getGraduationYearMonth()));

        Set<Skill> skills = getTags();
        if (!skills.isEmpty()) {
            builder.append("; Tags: ");
            skills.forEach(builder::append);
        }
        return builder.toString();
    }

}
