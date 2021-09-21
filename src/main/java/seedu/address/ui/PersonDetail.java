package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class PersonDetail extends UiPart<Region> {
    @FXML
    Tab skill,academic,interview,statement;

    private static final String FXML = "PersonDetail.fxml";

    public PersonDetail() {
        super(FXML);
    }

    /*  public PersonDetail(Person person) {
        super(FXML);
        setSkillTab(person.getSkill)
        setInterviewTab(person.getInterview)
        setStatementTab(person.getStatement)
        setAcademicTab(person.getAcademic)
    }


    void setSkillTab(String text) {
        AnchorPane temp = (AnchorPane) skill.getContent();
        Label temp1 = (Label) temp.getChildren().get(0);
        temp1.setText(text);
    }


    void setInterviewTab(String text) {
        AnchorPane temp = (AnchorPane) interview.getContent();
        Label temp1 = (Label) temp.getChildren().get(0);
        temp1.setText(text);
    }
    void setStatementTab(String text) {
        AnchorPane temp = (AnchorPane) statement.getContent();
        Label temp1 = (Label) temp.getChildren().get(0);
        temp1.setText(text);
    }
    void setAcademicTab(String text) {
        AnchorPane temp = (AnchorPane) academic.getContent();
        Label temp1 = (Label) temp.getChildren().get(0);
        temp1.setText(text);
    }
    */
}
