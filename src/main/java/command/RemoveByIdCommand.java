package command;

import data.Worker;
import utility.CollectionManager;

/**
 * Remove by id command
 * Remove element of the cllection with indicated id
 */
public class RemoveByIdCommand extends CommandAbstract {
    private final CollectionManager collectionManager;

    /**
     * Command constructor
     *
     * @param collectionManager - collection manager, receiver
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("Remove by id {id}", "Delete element with indicated id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Argument is required.");
            return;
        }
        long id;
        try {
            id = Long.parseLong(arg);
        } catch (NumberFormatException exception) {
            System.out.println("Input isn't id.");
            return;
        }
        Worker worker = collectionManager.getById(id);
        if (worker == null) {
            System.out.println("Worker with detected id wasn't found.");
        } else {
            collectionManager.remove(worker);
            System.out.println("Element has been removed.");
        }
    }
}
