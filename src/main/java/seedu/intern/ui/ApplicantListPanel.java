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
public class ApplicantListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ApplicantListPanel.class);

    @FXML
    private ListView<Applicant> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ApplicantListPanel(ObservableList<Applicant> applicantList) {
        super(FXML);
        personListView.setItems(applicantList);
        personListView.setCellFactory((listView) -> new ApplicantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Applicant} using a {@code PersonCard}.
     */
    class ApplicantListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);
            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicantCard(applicant, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Selects the provided applicant.
     *
     * @param applicant applicant to select.
     */
    public void selectApplicant(Applicant applicant) {
        personListView.getSelectionModel().select(applicant);
    }

    /**
     * Adds a listener that will be called when a different {@code PersonCard} is selected.
     *
     * @param listener listener to be added to the ListView
     */
    public void addSelectedListener(ChangeListener<Applicant> listener) {
        personListView.getSelectionModel().selectedItemProperty().addListener(listener);
    }
}