package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.HOON;
import static seedu.address.testutil.TypicalApplicants.IDA;
import static seedu.address.testutil.TypicalApplicants.getTypicalInternWatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InternWatcher;
import seedu.address.model.ReadOnlyInternWatcher;

public class JsonInternWatcherStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternWatcher_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternWatcher(null));
    }

    private java.util.Optional<ReadOnlyInternWatcher> readInternWatcher(String filePath) throws Exception {
        return new JsonInternWatcherStorage(Paths.get(filePath))
                .readInternWatcher(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternWatcher("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternWatcher("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternWatcher("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternWatcher("invalidAndValidPersonAddressBook.json"));
    }

//    @Test
//    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
//        Path filePath = testFolder.resolve("TempAddressBook.json");
//        InternWatcher original = getTypicalInternWatcher();
//        JsonInternWatcherStorage jsonInternWatcherStorage = new JsonInternWatcherStorage(filePath);
//
//        // Save in new file and read back
//        jsonInternWatcherStorage.saveInternWatcher(original, filePath);
//        ReadOnlyInternWatcher readBack = jsonInternWatcherStorage.readInternWatcher(filePath).get();
//        assertEquals(original, new InternWatcher(readBack));
//
//        // Modify data, overwrite exiting file, and read back
//        original.addPerson(HOON);
//        original.removePerson(ALICE);
//        jsonInternWatcherStorage.saveInternWatcher(original, filePath);
//        readBack = jsonInternWatcherStorage.readInternWatcher(filePath).get();
//        assertEquals(original, new InternWatcher(readBack));
//
//        // Save and read without specifying file path
//        original.addPerson(IDA);
//        jsonInternWatcherStorage.saveInternWatcher(original); // file path not specified
//        readBack = jsonInternWatcherStorage.readInternWatcher().get(); // file path not specified
//        assertEquals(original, new InternWatcher(readBack));
//
//    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyInternWatcher addressBook, String filePath) {
        try {
            new JsonInternWatcherStorage(Paths.get(filePath))
                    .saveInternWatcher(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new InternWatcher(), null));
    }
}
