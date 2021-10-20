package seedu.intern.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedInternWatcher extends InternWatcher {
    private List<ReadOnlyInternWatcher> watcherStateList;
    private int currStateIndex;

    public VersionedInternWatcher(ReadOnlyInternWatcher initialState) {
        super(initialState);

        this.currStateIndex = 0;
        this.watcherStateList = new ArrayList<>();
        this.watcherStateList.add(new InternWatcher(initialState));
    }

    public void commitState() {
        int listSize = watcherStateList.size();
        watcherStateList.subList(currStateIndex + 1, listSize).clear();
        watcherStateList.add(new InternWatcher(this));
        currStateIndex++;
    }

    public void undo() {
        if (canUndo()) {
            currStateIndex--;
            resetData(watcherStateList.get(currStateIndex));
        }
    }

    public void redo() {
        if (canRedo()) {
            currStateIndex++;
            resetData(watcherStateList.get(currStateIndex));
        }
    }

    public boolean canUndo() {
        return currStateIndex > 0;
    }

    public boolean canRedo() {
        return currStateIndex < watcherStateList.size() - 1;
    }
}
