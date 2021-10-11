package seedu.intern.ui;

import java.util.Comparator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.skills.Skill;

/**
 * Panel containing the details of a Person
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetail.fxml";

    @FXML
    private Tab skill;

    @FXML
    private Tab academic;


    @FXML
    private Label institution;

    @FXML
    private Label course;

    @FXML
    private Label yearOfGrad;

    @FXML
    private Label grade;

    @FXML
    private FlowPane skills;

    /**
     * Creates a {@code PersonDetailPanel}.
     */
    public PersonDetailPanel() {
        super(FXML);
        setAcademicTab(Applicant.getDefaultAcademics());
        setSkillTab(null);
    }

    /**
     * Sets the detail panel to display the {@code Applicant} specified.
     *
     * @param applicant Applicant to be displayed
     */
    public void showApplicant(Applicant applicant) {
        if (applicant != null) {
            setSkillTab(applicant.getSkills());
            setAcademicTab(applicant.getAcademics());
        } else {
            setAcademicTab(Applicant.getDefaultAcademics());
            setSkillTab(null);
        }
    }

    /**
     * Sets the Academic Tab as per the String array provided.
     * The array must contain 4 elements, containing,
     *
     * @param text String array containing:
     *             First index: Institution
     *             Second Index: Course
     *             Thrid Index: Year of Graduation
     *             Fourth index: Grade
     */
    private void setAcademicTab(String[] text) {

        //TODO: Might be better to get the Person and populate dynamically instead of having a string array produced.
        institution.setText(text[0]);
        course.setText(text[1]);
        yearOfGrad.setText(text[2]);
        grade.setText(text[3]);
    }

    /**
     * Sets the Skills Tab as per the set provided.
     * The set can contain any number of skills.
     *
     * @param skillSet Set of skills to be displayed
     */
    private void setSkillTab(Set<Skill> skillSet) {
        skills.getChildren().clear();
        if (skillSet == null) {
            return;
        }

        skillSet.stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));

    }
}
