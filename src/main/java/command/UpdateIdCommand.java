package command;

import data.Worker;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import utility.CollectionManager;
import utility.WorkerFactory;


/** Update id command
 * Update element with indicated id
 */
public class UpdateIdCommand extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final WorkerFactory workerFactory;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     * @param workerFactory - factory for worker class
     */
    public UpdateIdCommand(CollectionManager collectionManager, WorkerFactory workerFactory) {
        super("Update id {element}", "Update element with indicated id");
        this.collectionManager = collectionManager;
        this.workerFactory = workerFactory;
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
        if (worker == null){
            System.out.println("Worker with detected id wasn't found.");
            return;
        }else {collectionManager.remove(worker);}

        long temp = workerFactory.getId();
        workerFactory.setStartId(id - 1);
        try {
            collectionManager.add(workerFactory.getWorkerFromConsole());
        } catch (IncorrectValueException | NullFieldException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        workerFactory.setStartId(temp);

    }
}
