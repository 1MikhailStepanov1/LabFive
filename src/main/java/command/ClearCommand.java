package command;

import utility.CollectionManager;

/** Clear command
 * Remove all elements from collection
 */
public class ClearCommand extends CommandAbstract {
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("Clear", "Clear collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        collectionManager.clear();
        System.out.println("Collection has been cleared.");
    }
}
