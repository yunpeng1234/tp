package seedu.intern.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.intern.logic.commands.EditAllCommand;
import seedu.intern.logic.commands.EditAllCommand.EditAllDescriptor;
import seedu.intern.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAllCommand object
 */
public class EditAllCommandParser implements Parser<EditAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditAllCommand
     * and returns an EditAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @return
     */
    public EditAllCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);
        ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        EditAllDescriptor editAllDescriptor = new EditAllDescriptor();

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editAllDescriptor
                    .setApplicationStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (!editAllDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAllCommand.MESSAGE_NOT_EDITED + EditAllCommand.MESSAGE_USAGE);
        }

        return new EditAllCommand(editAllDescriptor);
    }
}
