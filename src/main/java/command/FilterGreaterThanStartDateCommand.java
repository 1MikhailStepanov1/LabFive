package command;

import data.Worker;
import utility.CollectionManager;
import utility.WorkerToUser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Filter greater than start date command
 * Show in console elements with value of field startDate, which is bigger than indicated one
 */
public class FilterGreaterThanStartDateCommand extends CommandAbstract {
    private final WorkerToUser workerToUser;
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param workerToUser - used to show worker in normal to user type
     * @param collectionManager - collection manager, receiver
     */
    public FilterGreaterThanStartDateCommand(WorkerToUser workerToUser, CollectionManager collectionManager) {
        super("Filter_greater_than_start_date", "Show elements with value of field \"StartDate\", which is bigger than indicated one");
        this.workerToUser = workerToUser;
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        if (arg.isEmpty()){
            System.out.println("Argument is required.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        ZonedDateTime temporary = ZonedDateTime.parse(arg, formatter);
        for (Worker worker : collectionManager.getCollection()) {
            if (worker.getStartDate().compareTo(temporary) < 0) {
                workerToUser.WorkerToConsole(worker);
            }
        }
    }
}
