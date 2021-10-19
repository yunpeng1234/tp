package seedu.intern.commons.core.selection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SelectionTest {

    @Test
    public void createOneBasedSelection() {
        // invalid selection
        assertThrows(IndexOutOfBoundsException.class, () -> Selection.fromIndex(Index.fromOneBased(0)));

        // check equality using the same base
        assertEquals(1, Selection.fromIndex(Index.fromOneBased(1)).getIndexOneBased());
        assertEquals(5, Selection.fromIndex(Index.fromOneBased(5)).getIndexOneBased());

        // convert from one-based selection to zero-based selection
        assertEquals(0, Selection.fromIndex(Index.fromOneBased(1)).getIndexZeroBased());
        assertEquals(4, Selection.fromIndex(Index.fromOneBased(5)).getIndexZeroBased());
    }

    @Test
    public void createZeroBasedSelection() {
        // invalid selection
        assertThrows(IndexOutOfBoundsException.class, () -> Selection.fromIndex(Index.fromOneBased(-1)));

        // check equality using the same base
        assertEquals(0, Selection.fromIndex(Index.fromZeroBased(0)).getIndexZeroBased());
        assertEquals(5, Selection.fromIndex(Index.fromZeroBased(5)).getIndexZeroBased());

        // convert from zero-based selection to one-based selection
        assertEquals(1, Selection.fromIndex(Index.fromZeroBased(0)).getIndexOneBased());
        assertEquals(6, Selection.fromIndex(Index.fromZeroBased(5)).getIndexOneBased());
    }

    @Test
    public void equals() {
        final Selection fifthPersonselection = Selection.fromIndex(Index.fromOneBased(5));

        // same values -> returns true
        assertTrue(fifthPersonselection.equals(Selection.fromIndex(Index.fromOneBased(5))));
        assertTrue(fifthPersonselection.equals(Selection.fromIndex(Index.fromZeroBased(4))));

        // same object -> returns true
        assertTrue(fifthPersonselection.equals(fifthPersonselection));

        // null -> returns false
        assertFalse(fifthPersonselection.equals(null));

        // different types -> returns false
        assertFalse(fifthPersonselection.equals(5.0f));

        // different selection -> returns false
        assertFalse(fifthPersonselection.equals(Selection.fromIndex(Index.fromZeroBased(1))));
    }
}
