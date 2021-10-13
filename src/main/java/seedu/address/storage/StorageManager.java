package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternWatcher;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternWatcherStorage internWatcherStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternWatcherStorage internWatcherStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.internWatcherStorage = internWatcherStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getInternWatcherFilePath() {
        return internWatcherStorage.getInternWatcherFilePath();
    }

    @Override
    public Optional<ReadOnlyInternWatcher> readInternWatcher() throws DataConversionException, IOException {
        return readInternWatcher(internWatcherStorage.getInternWatcherFilePath());
    }

    @Override
    public Optional<ReadOnlyInternWatcher> readInternWatcher(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internWatcherStorage.readInternWatcher(filePath);
    }

    @Override
    public void saveInternWatcher(ReadOnlyInternWatcher internWatcher) throws IOException {
        saveInternWatcher(internWatcher, internWatcherStorage.getInternWatcherFilePath());
    }

    @Override
    public void saveInternWatcher(ReadOnlyInternWatcher internWatcher, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internWatcherStorage.saveInternWatcher(internWatcher, filePath);
    }

}
