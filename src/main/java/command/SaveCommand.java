package command;

import utility.FileWorker;
import utility.CollectionManager;

/** Save command
 * Save collection the the file
 */
public class SaveCommand extends CommandAbstract {
    private final FileWorker fileWorker;
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param fileWorker - used to pack worker in file in XML format
     * @param collectionManager - collection manager, receiver
     */
    public SaveCommand(FileWorker fileWorker, CollectionManager collectionManager) {
        super("Save", "Save collection to the file");
        this.fileWorker = fileWorker;
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        fileWorker.getToXmlFormat(fileWorker.getFilePath());
    }
}
