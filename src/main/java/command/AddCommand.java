package command;

import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import utility.CollectionManager;
import utility.WorkerFactory;

/** Add command
 * Read worker from console and add it to the collection
 */
public class AddCommand extends CommandAbstract {
    private final WorkerFactory factory;
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param factory - factory fow worker class
     * @param collectionManager - collection manager, receiver
     */
    public AddCommand(WorkerFactory factory, CollectionManager collectionManager) {
        super("add {element}", "Add new element to collection.");
        this.factory = factory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        try {
            collectionManager.add(factory.getWorkerFromConsole());
        } catch (IncorrectValueException | NullFieldException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
