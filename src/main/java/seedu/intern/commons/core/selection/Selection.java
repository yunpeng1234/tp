package seedu.intern.commons.core.selection;

public class Selection {
    private static final String MESSAGE_MISSING_INDEX = "Selection does not contain an Index.";
    private static final String MESSAGE_MISSING_FLAG = "Selection does not contain an All Flag.";
    private final Index index;
    private final Boolean isSelectAll;

    private Selection(Index index, Boolean isSelectAll) {
        this.index = index;
        this.isSelectAll = isSelectAll;
    }

    public int getIndexOneBased() {
        if (this.hasIndex()) {
            return this.index.getOneBased();
        } else {
            throw new NullPointerException(MESSAGE_MISSING_INDEX);
        }
    }

    public int getIndexZeroBased() {
        if (this.hasIndex()) {
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Selection)) { // instanceof handles nulls
            return false;
        }

        Selection otherSelection = (Selection) other;

        if (this.hasIndex() != otherSelection.hasIndex()
                || this.hasAllFlag() != otherSelection.hasAllFlag()) {
            return false;
        }

        if (this.hasIndex() && this.getIndexOneBased() != otherSelection.getIndexOneBased()) {
            return false;
        } else if (this.hasAllFlag() && this.getAllFlag() != otherSelection.getAllFlag()) {
            return false;
        } else {
            return true;
        }
    }
}
