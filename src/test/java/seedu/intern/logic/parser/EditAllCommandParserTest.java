package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.EditAllCommand;
import seedu.intern.logic.commands.EditAllCommand.EditAllDescriptor;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.testutil.EditAllDescriptorBuilder;

public class EditAllCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAllCommand.MESSAGE_USAGE);

    private EditAllCommandParser parser = new EditAllCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "",
                EditAllCommand.MESSAGE_NOT_EDITED + EditAllCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_STATUS_DESC , ApplicationStatus.MESSAGE_CONSTRAINTS); // invalid status
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = STATUS_DESC_AMY;

        EditAllDescriptor descriptor = new EditAllDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY).build();
        EditAllCommand expectedCommand = new EditAllCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = STATUS_DESC_BOB + STATUS_DESC_AMY;

        EditAllDescriptor descriptor = new EditAllDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY).build();
        EditAllCommand expectedCommand = new EditAllCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = INVALID_STATUS_DESC + STATUS_DESC_AMY;

        EditAllDescriptor descriptor = new EditAllDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_AMY).build();
        EditAllCommand expectedCommand = new EditAllCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
