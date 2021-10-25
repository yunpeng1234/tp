package seedu.intern.logic.commands;

import static seedu.intern.testutil.TypicalApplicants.getTypicalInternWatcher;

import org.junit.jupiter.api.Test;

import seedu.intern.model.Model;
import seedu.intern.model.ModelManager;
import seedu.intern.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalInternWatcher(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternWatcher(), new UserPrefs());

    @Test
    public void execute_changeCommitted_success() {
    }
}
