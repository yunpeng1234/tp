package seedu.intern.model;

import java.util.ArrayList;
import java.util.List;

public class UndoManager extends InternWatcher {
    private List<InternWatcher> watcherStateList;
    private int currStateIndex;

    public UndoManager(InternWatcher initialState) {
        this.currStateIndex = 0;

        this.watcherStateList = new ArrayList<>();
        this.watcherStateList.add(initialState);
    }

    public void commitState() {
        int stateListSize = watcherStateList.size();
        if (currStateIndex != stateListSize - 1) {
            for (int i = currStateIndex + 1; i < stateListSize; i++) {
                watcherStateList.remove(i);
            }
        }

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
