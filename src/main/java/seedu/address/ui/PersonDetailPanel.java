package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import seedu.address.model.person.Applicant;

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
    private Tab interview;

    @FXML
    private Tab statement;

    @FXML
    private Label institution;

    @FXML
    private Label course;

    @FXML
    private Label yearOfGrad;

    @FXML
    private Label grade;

    /**
     * Creates a {@code PersonDetailPanel} with the given {@code ObservableList}.
     */
    public PersonDetailPanel(ObservableList<Applicant> applicantObservableList) {
        super(FXML);
        if (!applicantObservableList.isEmpty()) {
            // To be changed on mouse click
            setAcademicTab(applicantObservableList.get(0).getAcademics());
        } else {
            setAcademicTab(Applicant.getDefaultAcademics());
        }
        // We are not using ListView so we must add our listeners manually
        applicantObservableList.addListener((ListChangeListener<Applicant>) c -> {
            if (!applicantObservableList.isEmpty()) {
                // To be changed on mouse click
                setAcademicTab(applicantObservableList.get(0).getAcademics());
            } else {
                setAcademicTab(Applicant.getDefaultAcademics());
            }
        });
    }

    // TODO: remove this code if unnecessary.
    /*
    void setSkillTab(Set text) {
        AnchorPane temp = (AnchorPane) skill.getContent();
        Label temp1 = (Label) temp.getChildren().get(0);
        temp1.setText(text);
    }
    */

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
    void setAcademicTab(String[] text) {
        //TODO: Might be better to get the Person and populate dynamically instead of having a string array produced.
        institution.setText(text[0]);
        course.setText(text[1]);
        yearOfGrad.setText(text[2]);
        grade.setText(text[3]);
    }
}
