package command;

import data.Worker;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import utility.CollectionManager;
import utility.WorkerFactory;

/** Remove lower command
 * Remove all elements of the collection which are lower than indicated one
 */
public class RemoveLowerCommand extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final WorkerFactory workerFactory;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     * @param workerFactory - factory for worker class
     */
    public RemoveLowerCommand(CollectionManager collectionManager, WorkerFactory workerFactory) {
        super("Remove_lower {element}", "Delete all elements from collection which are smaller than indicated one");
        this.collectionManager = collectionManager;
        this.workerFactory = workerFactory;
    }

    @Override
    public void exe(String arg) {
        try {
            Worker worker = workerFactory.getWorkerFromConsole();
            for (Worker w : collectionManager.getCollection()) {
                if (worker.compareTo(w) < 0) {
                    collectionManager.remove(w);
                }
            }
            System.out.println("All greater elements have been removed.");
        } catch (NullFieldException | IncorrectValueException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

