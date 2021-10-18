package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SPECIAL;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_Int_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validArgs_all_returnsDeleteCommand() {
        assertParseSuccess(parser, "all", new DeleteCommand(INDEX_SPECIAL));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
