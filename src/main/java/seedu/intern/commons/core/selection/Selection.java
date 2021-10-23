package seedu.intern.commons.core.selection;

public class Selection {
    private static final String MESSAGE_MISSING_INDEX = "Selection does not contain an Index.";
    private static final String MESSAGE_MISSING_FLAG = "Selection does not contain an Extra Condition Flag.";
    private final Index index;
    private final Boolean isExtraCondition;

    private Selection(Index index, Boolean isExtraCondition) {
        this.index = index;
        this.isExtraCondition = isExtraCondition;
    }

    public Index getIndex() {
        return index;
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

    public boolean getExtraConditionFlag() {
        if (hasExtraConditionFlag()) {
            return isExtraCondition;
        } else {
            throw new NullPointerException(MESSAGE_MISSING_FLAG);
        }
    }

    public boolean hasIndex() {
        return this.index != null;
    }

    public boolean hasExtraConditionFlag() {
        return this.isExtraCondition != null;
    }

    public static Selection fromExtraConditionFlag(boolean flag) {
        return new Selection(null, flag);
    }

    public static Selection fromIndex(Index index) {
        return new Selection(index, null);
    }

    public static Selection fromIndexAndToggle(Index index, boolean isExtraCondition) {
        return new Selection(index, isExtraCondition);
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
                || this.hasExtraConditionFlag() != otherSelection.hasExtraConditionFlag()) {
            return false;
        }

        if (this.hasIndex() && this.getIndexOneBased() != otherSelection.getIndexOneBased()) {
            return false;
        } else if (this.hasExtraConditionFlag()
                && this.getExtraConditionFlag() != otherSelection.getExtraConditionFlag()) {
            return false;
        } else {
            return true;
        }
    }
}
