package seedu.address.ui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

import java.util.Set;

public class PersonDetail extends UiPart<Region> {
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


    public PersonDetail(ObservableList<Person> personObservableList) {
        super(FXML);
        if (!personObservableList.isEmpty()) {
            setAcademicTab(personObservableList.get(0).getAcademics());
        }
//        setSkillTab(person.getTags());

    }


//    void setSkillTab(Set text) {
//        AnchorPane temp = (AnchorPane) skill.getContent();
//        Label temp1 = (Label) temp.getChildren().get(0);
//        temp1.setText(text);
//    }


    void setAcademicTab(String[] text) {
        institution.setText(text[0]);
        course.setText(text[1]);
        yearOfGrad.setText(text[2]);
        grade.setText(text[3]);
    }
}
