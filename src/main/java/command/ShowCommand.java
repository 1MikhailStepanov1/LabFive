package command;

import data.Worker;
import utility.CollectionManager;
import utility.WorkerToUser;

import java.util.Collection;

/** Show command
 * Show all elements of the collection
 */
public class ShowCommand extends CommandAbstract {
    private final CollectionManager collectionManager;
    private final WorkerToUser workerToUser;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     * @param workerToUser - used to show worker to user
     */
    public ShowCommand(CollectionManager collectionManager, WorkerToUser workerToUser) {
        super("Show", "Show all collection`s elements into strings");
        this.collectionManager = collectionManager;
        this.workerToUser = workerToUser;
    }

    @Override
    public void exe(String arg) {
        Collection<Worker> collection = collectionManager.getCollection();
        if (collection.size() == 0) {
            System.out.println("There is no elements in collection.");
        }
        for (Worker w : collection) {
            workerToUser.WorkerToConsole(w);
        }
    }
}
