package seedu.intern.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code InternWatcher} that keeps track of a list of its previous states.
 */
public class VersionedInternWatcher extends InternWatcher {
    private List<ReadOnlyInternWatcher> watcherStateList;
    private int currStatePointer;

    /**
     * Initialises a VersionedInternWatcher with the initial state.
     * @param initialState
     */
    public VersionedInternWatcher(ReadOnlyInternWatcher initialState) {
        super(initialState);

        this.currStatePointer = 0;
        this.watcherStateList = new ArrayList<>();
        this.watcherStateList.add(new InternWatcher(initialState));
    }

    /**
     * Saves a copy of the current InternWatcher state to the watcherStateList.
     */
    public void commitState() {
        int listSize = watcherStateList.size();
        watcherStateList.subList(currStatePointer + 1, listSize).clear();
        watcherStateList.add(new InternWatcher(this));
        currStatePointer++;
    }

    /**
     * Restores the InternWatcher to a previous state in the watcherStateList.
     */
    public void undo() {
        if (canUndo()) {
            currStatePointer--;
            resetData(watcherStateList.get(currStatePointer));
        }
    }

    /**
     * Restores the InternWatcher to a previously undone state in the watcherStateList.
     */
    public void redo() {
        if (canRedo()) {
            currStatePointer++;
            resetData(watcherStateList.get(currStatePointer));
        }
    }

    /**
     * Return true if there is a previous state to restore in the watcherStateList.
     * @return if undo is possible.
     */
    public boolean canUndo() {
        return currStatePointer > 0;
    }

    /**
     * Return true if there is a previously undone state to restore in the watcherStateList.
     * @return if redo is possible.
     */
    public boolean canRedo() {
        return currStatePointer < watcherStateList.size() - 1;
    }
}
