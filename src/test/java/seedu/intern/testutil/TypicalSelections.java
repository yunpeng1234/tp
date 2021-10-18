package seedu.intern.testutil;

import seedu.intern.commons.core.selection.Index;
import seedu.intern.commons.core.selection.Selection;

import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_THIRD_PERSON;


public class TypicalSelections {
    public static final Selection SELECTION_FIRST_PERSON = Selection.fromIndex(INDEX_FIRST_PERSON);
    public static final Selection SELECTION_SECOND_PERSON = Selection.fromIndex(INDEX_SECOND_PERSON);
    public static final Selection SELECTION_THIRD_PERSON = Selection.fromIndex(INDEX_THIRD_PERSON);
    public static final Selection SELECTION_ALL = Selection.fromAllFlag(true);
}
