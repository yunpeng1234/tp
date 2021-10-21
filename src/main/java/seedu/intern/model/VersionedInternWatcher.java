package seedu.intern.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code InternWatcher} that keeps track of a list of its previous states.
 */
public class VersionedInternWatcher extends InternWatcher {
    private List<ReadOnlyInternWatcher> watcherStateList;
    private int currStateIndex;

    /**
     * Initialises a VersionedInternWatcher with the initial state.
     * @param initialState
     */
    public VersionedInternWatcher(ReadOnlyInternWatcher initialState) {
        super(initialState);

        this.currStateIndex = 0;
        this.watcherStateList = new ArrayList<>();
        this.watcherStateList.add(new InternWatcher(initialState));
    }

    /**
     * Saves a copy of the current InternWatcher state to the list of states.
     */
    public void commitState() {
        int listSize = watcherStateList.size();
        watcherStateList.subList(currStateIndex + 1, listSize).clear();
        watcherStateList.add(new InternWatcher(this));
        currStateIndex++;
    }

    /**
     * Reverts the InternWatcher to the previous state.
     */
    public void undo() {
        if (canUndo()) {
            currStateIndex--;
            resetData(watcherStateList.get(currStateIndex));
        }
    }

    /**
     * Restores the InternWatcher to the state before the previous undo action.
     */
    public void redo() {
        if (canRedo()) {
            currStateIndex++;
            resetData(watcherStateList.get(currStateIndex));
        }
    }

    /**
     * Return true if there is a previous state to revert to.
     * @return if undo is possible.
     */
    public boolean canUndo() {
        return currStateIndex > 0;
    }

    /**
     * Return true if there is a previous undone state to restore.
     * @return if redo is possible.
     */
    public boolean canRedo() {
        return currStateIndex < watcherStateList.size() - 1;
    }
}
