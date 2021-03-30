package command;

import data.Worker;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import utility.CollectionManager;
import utility.WorkerFactory;

/** Remove greater command
 * Remove all elements of the collection which are bigger than indicated one
 */
public class RemoveGreaterCommand extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final WorkerFactory workerFactory;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     * @param workerFactory - factory for worker class
     */
    public RemoveGreaterCommand(CollectionManager collectionManager, WorkerFactory workerFactory) {
        super("Remove_greater {element}", "Delete all elements from collection which are bigger than indicated one");
        this.collectionManager = collectionManager;
        this.workerFactory = workerFactory;
    }

    @Override
    public void exe(String arg) {
        try {
            Worker worker = workerFactory.getWorkerFromConsole();
            for (Worker w : collectionManager.getCollection()) {
                if (worker.compareTo(w) > 0) {
                    collectionManager.remove(w);
                }
            }
            System.out.println("All greater elements have been removed.");
        } catch (NullFieldException | IncorrectValueException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
