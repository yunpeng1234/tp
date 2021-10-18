package seedu.intern.ui;

import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.model.applicant.Applicant;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Applicant> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Applicant> applicantList) {
        super(FXML);
        personListView.setItems(applicantList);
        personListView.setCellFactory((listView) -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Applicant} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);
            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(applicant, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Adds a listener that will be called when a different {@code PersonCard} is selected.
     *
     * @param listener
     */
    public void addSelectedListener(ChangeListener<Applicant> listener) {
        personListView.getSelectionModel().selectedItemProperty().addListener(listener);
    }
}