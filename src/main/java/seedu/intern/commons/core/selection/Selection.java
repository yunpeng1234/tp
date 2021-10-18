package seedu.intern.commons.core.selection;

public class Selection {
    private final String MESSAGE_MISSING_INDEX = "Selection does not contain an Index.";
    private final String MESSAGE_MISSING_FLAG = "Selection does not contain an All Flag.";
    private final Index index;
    private final Boolean isSelectAll;

    private Selection(Index index, Boolean isSelectAll) {
        this.index = index;
        this.isSelectAll = isSelectAll;
    }

    public int getIndexOneBased() {
        if (hasIndex()) {
            return this.index.getOneBased();
        } else {
            throw new NullPointerException(MESSAGE_MISSING_INDEX);
        }
    }

    public int getIndexZeroBased() {
        if (hasIndex()) {
            return this.index.getZeroBased();
        } else {
            throw new NullPointerException(MESSAGE_MISSING_INDEX);
        }
    }

    public boolean getAllFlag() {
        if (hasAllFlag()) {
            return isSelectAll;
        } else {
            throw new NullPointerException(MESSAGE_MISSING_FLAG);
        }
    }

    public boolean hasIndex() {
        return this.index != null;
    }

    public boolean hasAllFlag() {
        return this.isSelectAll != null;
    }

    public static Selection fromAllFlag(boolean flag) {
        return new Selection(null, flag);
    }

    public static Selection fromIndex(Index index) {
        return new Selection(index, null);
    }
}
