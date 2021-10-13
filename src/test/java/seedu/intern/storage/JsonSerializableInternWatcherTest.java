package seedu.intern.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intern.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.commons.util.JsonUtil;
import seedu.intern.model.InternWatcher;
import seedu.intern.testutil.TypicalApplicants;

public class JsonSerializableInternWatcherTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableInternWatcher.class).get();
        InternWatcher internWatcherFromFile = dataFromFile.toModelType();
        InternWatcher typicalPersonsInternWatcher = TypicalApplicants.getTypicalInternWatcher();
        assertEquals(internWatcherFromFile, typicalPersonsInternWatcher);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableInternWatcher.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableInternWatcher dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableInternWatcher.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternWatcher.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
