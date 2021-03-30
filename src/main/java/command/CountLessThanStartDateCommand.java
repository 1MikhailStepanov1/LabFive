package command;

import data.Worker;
import utility.CollectionManager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Count less than start date command
 *  Count and show amount of elements with field StartDAte which is lower than indicated one
 */
public class CountLessThanStartDateCommand extends CommandAbstract {
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     */
    public CountLessThanStartDateCommand(CollectionManager collectionManager) {
        super("Count_less_than_start_date", "Show amount of elements with field \"StartDate\" which is lower than indicated one");
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Argument is required.");
            return;
        }
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu H:mm:ss z");
        ZonedDateTime temporary = ZonedDateTime.parse(arg, formatter);
        for (Worker worker : collectionManager.getCollection()) {
            if (worker.getStartDate().compareTo(temporary) > 0) {
                count++;
            }
        }
        System.out.println("Answer: " + count);
    }
}
