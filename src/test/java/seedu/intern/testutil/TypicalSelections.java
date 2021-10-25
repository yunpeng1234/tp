package seedu.intern.testutil;

import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import seedu.intern.commons.core.selection.Selection;

public class TypicalSelections {
    public static final Selection SELECTION_FIRST_PERSON = Selection.fromIndex(INDEX_FIRST_PERSON);
    public static final Selection SELECTION_SECOND_PERSON = Selection.fromIndex(INDEX_SECOND_PERSON);
    public static final Selection SELECTION_THIRD_PERSON = Selection.fromIndex(INDEX_THIRD_PERSON);
    public static final Pair<Index, Boolean> PAIR_FIRST_PERSON_TOGGLE = new Pair<>(INDEX_FIRST_PERSON, true);
    public static final Pair<Index, Boolean> PAIR_SECOND_PERSON_TOGGLE = new Pair<>(INDEX_SECOND_PERSON, true);
    public static final Pair<Index, Boolean> PAIR_THIRD_PERSON_TOGGLE = new Pair<>(INDEX_THIRD_PERSON, true);
    public static final Selection SELECTION_ALL = Selection.fromAllFlag();
}
