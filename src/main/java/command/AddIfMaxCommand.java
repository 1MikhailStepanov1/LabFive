package command;

import data.Worker;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import utility.CollectionManager;
import utility.WorkerFactory;

/** Add if max command
 * Read worker from console and add it to the collection, if new element`s value is bigger than element`s maximum in collection
 */
public class AddIfMaxCommand extends CommandAbstract {
    private final WorkerFactory factory;
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param factory - factory for worker class
     * @param collectionManager - collection manager, receiver
     */
    public AddIfMaxCommand(WorkerFactory factory, CollectionManager collectionManager) {
        super("Add_if_max", "Add new element to the collection, if new element`s value is bigger than element`s maximum in collection");
        this.factory = factory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        try {
            Worker worker = factory.getWorkerFromConsole();
            if (worker.compareTo(collectionManager.getMax()) < 0) {
                collectionManager.add(worker);
                System.out.println("Worker has been added.");
            } else {
                System.out.println("Entered worker isn't greater than max from collection.");
            }
        } catch (IncorrectValueException | NullFieldException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
